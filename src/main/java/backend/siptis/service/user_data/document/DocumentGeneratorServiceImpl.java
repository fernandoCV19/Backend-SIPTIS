package backend.siptis.service.user_data.document;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.DocumentType;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.editors_and_reviewers.*;
import backend.siptis.model.entity.project_management.Project;
import backend.siptis.model.entity.user_data.Document;
import backend.siptis.model.entity.user_data.UserCareer;
import backend.siptis.model.entity.user_data.UserInformation;
import backend.siptis.model.pjo.dto.document.DocumentaryRecordDto;
import backend.siptis.model.pjo.dto.document.LetterGenerationRequestDTO;
import backend.siptis.model.pjo.dto.document.ReportDocumentDTO;
import backend.siptis.model.repository.auth.SiptisUserRepository;
import backend.siptis.model.repository.editors_and_reviewers.*;
import backend.siptis.model.repository.project_management.ProjectRepository;
import backend.siptis.model.repository.user_data.DocumentRepository;
import backend.siptis.model.repository.user_data.UserInformationRepository;
import backend.siptis.service.auth.siptis_user_services.SiptisUserServiceCareerDirectorOperations;
import backend.siptis.service.cloud.CloudManagementService;
import backend.siptis.service.user_data.document.generation_tools.DocumentaryRecordTool;
import backend.siptis.service.user_data.document.generation_tools.LetterTool;
import backend.siptis.service.user_data.document.generation_tools.ReportTool;
import backend.siptis.service.user_data.document.generation_tools.SolvencyTool;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class DocumentGeneratorServiceImpl implements DocumentGeneratorService {
    private final CloudManagementService nube;
    private final SiptisUserRepository siptisUserRepository;
    private final UserInformationRepository userInformationRepository;
    private final ProjectRepository projectRepository;
    private final DocumentRepository documentRepository;
    private final ProjectTribunalRepository projectTribunalRepository;
    private final ProjectTeacherRepository projectTeacherRepository;
    private final ProjectTutorRepository projectTutorRepository;
    private final ProjectSupervisorRepository projectSupervisorRepository;
    private final ProjectStudentRepository projectStudentRepository;
    private final SiptisUserServiceCareerDirectorOperations siptisUserServiceCareerDirectorOperations;

    @Override
    public ServiceAnswer getAllDocumentsFromUser(long idUser) {
        Optional<SiptisUser> oUser = siptisUserRepository.findOneById(idUser);
        if (oUser.isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_FOUND).data(null).build();
        }
        SiptisUser user = oUser.get();
        List<Document> documents = user.getDocuments().stream().toList();
        if (documents.isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NO_DOCUMENTS).data(null).build();
        }
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(documents).build();
    }

    @Override
    public ServiceAnswer getAllDocumentsFromProject(long idProject) {
        Optional<Project> oProject = projectRepository.findById(idProject);
        if (oProject.isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_FOUND).data(null).build();
        }
        Project project = oProject.get();
        List<Document> documents = new ArrayList<>();
        Collection<ProjectStudent> students = project.getStudents();
        for (ProjectStudent student : students) {
            SiptisUser user = student.getStudent();
            documents = Stream.concat(documents.stream(), user.getDocuments().stream()).toList();
        }
        if (documents.isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NO_DOCUMENTS).data(null).build();
        }
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(documents).build();
    }

    @Override
    public ServiceAnswer deleteDocument(long idDocument) {
        Optional<Document> oDocument = documentRepository.findById(idDocument);
        if (oDocument.isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_FOUND).data(null).build();
        }
        Document document = oDocument.get();
        String path = document.getPath();
        nube.deleteObject(path);
        documentRepository.delete(document);
        documentRepository.flush();
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.DOCUMENT_DELETED).data(document).build();
    }

    @Override
    public ServiceAnswer generateReport(ReportDocumentDTO reportDocumentDTO, Long idUser, Long idProject) {
        Optional<Project> optionalProject = projectRepository.findById(idProject);
        if (optionalProject.isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ERROR).data(null).build();
        }
        Project project = optionalProject.get();
        List<String> tutors = userInformationRepository.getTutorsNames(idProject);
        String teacherCompleteName = userInformationRepository.getTeachersNames(idProject).get(0);
        String title = project.getName();
        Optional<SiptisUser> oUser = siptisUserRepository.findOneById(idUser);

        if (oUser.isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_FOUND).data(null).build();
        }
        SiptisUser user = oUser.get();
        String postulant = user.getUserInformation().getNames() + ' ' + user.getUserInformation().getLastNames();
        ReportTool reportTool = new ReportTool();
        Integer reportNumber = reportDocumentDTO.getReportNumber();
        String filename = reportTool.generate(postulant, Integer.toString(reportNumber), title, tutors, teacherCompleteName, reportDocumentDTO.getDescription());
        String key = nube.uploadDocumentToCloud(filename);

        backend.siptis.model.entity.user_data.Document document = new backend.siptis.model.entity.user_data.Document();
        document.setPath(key);
        document.setType(DocumentType.REPORT.toString());
        document.setDescription(reportDocumentDTO.getShortDescription());
        document.setSiptisUser(user);
        document.setDate(LocalDateTime.now());
        documentRepository.save(document);

        projectRepository.save(project);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.DOCUMENT_GENERATED).data(key).build();
    }

    @Override
    public ServiceAnswer generateSolvency(long idUser) {
        Optional<SiptisUser> oUser = siptisUserRepository.findOneById(idUser);

        if (oUser.isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_FOUND).data(null).build();
        }
        SiptisUser user = oUser.get();
        UserInformation info = user.getUserInformation();
        String name = info.getNames() + " " + info.getLastNames();
        String ci = info.getCi();
        UserCareer career = null;
        String careerName = "";
        for (UserCareer userCareer : user.getCareer()) {
            career = userCareer;
        }
        if (career != null)
            careerName = career.getName();

        Path location = null;
        try {
            location = blueprintRetrieve("CertificadoSolvencia.pdf");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        SolvencyTool solvencyTool = new SolvencyTool(location);
        String filename = solvencyTool.generate(name, ci, careerName);

        String key = nube.uploadDocumentToCloud(filename);
        backend.siptis.model.entity.user_data.Document document = new backend.siptis.model.entity.user_data.Document();
        document.setPath(key);
        document.setType(DocumentType.FORM.toString());
        document.setDescription("Formulario de Solvencia");
        document.setSiptisUser(user);
        document.setDate(LocalDateTime.now());
        documentRepository.save(document);

        return ServiceAnswer.builder().serviceMessage(ServiceMessage.DOCUMENT_GENERATED).data(key).build();
    }

    @Override
    public ServiceAnswer generateDocumentaryRecord(DocumentaryRecordDto documentaryRecordDto, Long idUser, Long idProject) {

        Optional<Project> optionalProject = projectRepository.findById(idProject);
        if (optionalProject.isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ERROR).data(null).build();
        }
        Project project = optionalProject.get();
        String modality = project.getModality().getName();
        List<String> tutors = userInformationRepository.getTutorsNames(idProject);
        String title = project.getName();

        Optional<SiptisUser> oUser = siptisUserRepository.findOneById(idUser);

        if (oUser.isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_FOUND).data(null).build();
        }
        SiptisUser user = oUser.get();
        UserInformation info = user.getUserInformation();
        String authorLastNames = info.getLastNames();
        String authorNames = info.getNames();
        UserCareer career = null;
        String careerName = "";
        for (UserCareer userCareer : user.getCareer()) {
            career = userCareer;
        }
        if (career != null)
            careerName = career.getName();

        Path location = null;
        try {
            location = blueprintRetrieve("FichaDocumental.pdf");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        DocumentaryRecordTool documentaryRecordTool = new DocumentaryRecordTool(location);

        String filename = documentaryRecordTool.generate(documentaryRecordDto, modality, careerName, authorNames, authorLastNames, tutors, title);
        String key = nube.uploadDocumentToCloud(filename);
        backend.siptis.model.entity.user_data.Document document = new backend.siptis.model.entity.user_data.Document();
        document.setPath(key);
        document.setType(DocumentType.RECORD.toString());
        document.setDescription("Ficha documental");
        document.setDate(LocalDateTime.now());
        document.setSiptisUser(user);
        documentRepository.save(document);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.DOCUMENT_GENERATED).data(key).build();
    }

    private Path blueprintRetrieve(String blueprintName) throws IOException {
        ByteArrayOutputStream document = nube.getObject("Plantillas/" + blueprintName);
        Path tempDir = Files.createTempDirectory("directory");
        Path tempFilePath = tempDir.resolve(blueprintName);
        byte[] bytes = document.toByteArray();
        Files.write(tempFilePath, bytes);

        return tempFilePath;
    }


    @Override
    public ServiceAnswer teacherTribunalRequest(LetterGenerationRequestDTO dto) throws IOException {
        LetterTool letterTool = new LetterTool();
        Optional<Project> projectOptional = projectRepository.findById(dto.getProjectId());
        if (projectOptional.isEmpty())
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_FOUND).data(null).build();
        Project project = projectOptional.get();
        String projectName = project.getName();
        Collection<ProjectStudent> students = project.getStudents();
        ProjectTeacher teacher = projectTeacherRepository.findByTeacherIdAndProjectId(dto.getUserId(), dto.getProjectId());
        if (teacher == null)
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_FOUND).data(null).build();
        if (teacher.getAccepted() == null || !teacher.getAccepted())
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_APPROVED).data(null).build();
        SiptisUser user = teacher.getTeacher();
        String teacherName = user.getFullName();
        String key = "";
        for (ProjectStudent projectStudent : students) {
            String studentName = projectStudent.getStudent().getFullName();
            Set<UserCareer> career = projectStudent.getStudent().getCareer();
            String careerName = career.iterator().next().getName();
            String directorName = siptisUserServiceCareerDirectorOperations.getCareerDirectorName(careerName);
            if (directorName == null)
                ServiceAnswer.builder().serviceMessage(ServiceMessage.NO_CURRENT_DIRECTOR).data(null).build();
            String filename = letterTool.generateTribunalRequest(studentName, directorName, careerName, projectName, teacherName);
            key = nube.uploadLetterToCloud(filename, projectName);
            Optional<Document> oDocument = documentRepository.findDocumentByPath(key);
            Document document;
            if (oDocument.isEmpty()) {
                document = new Document();
            } else {
                document = oDocument.get();
            }
            document.setPath(key);
            document.setType(DocumentType.LETTER.toString());
            document.setDescription("Carta de Conformidad y solicitud de asignación de tribunales, emitida por el docente de Taller de Grado 2 encargado del proyecto.");
            document.setDate(LocalDateTime.now());
            document.setSiptisUser(projectStudent.getStudent());
            documentRepository.save(document);
        }

        return ServiceAnswer.builder().serviceMessage(ServiceMessage.DOCUMENT_GENERATED).data(key).build();
    }

    @Override
    public ServiceAnswer tutorTribunalRequest(LetterGenerationRequestDTO dto) throws IOException {
        LetterTool letterTool = new LetterTool();
        Optional<Project> projectOptional = projectRepository.findById(dto.getProjectId());
        if (projectOptional.isEmpty())
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_FOUND).data(null).build();
        Project project = projectOptional.get();
        String projectName = project.getName();
        ProjectTutor tutor = projectTutorRepository.findByTutorIdAndProjectId(dto.getUserId(), dto.getProjectId());
        if (tutor == null)
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_FOUND).data(null).build();
        if (tutor.getAccepted() == null || !tutor.getAccepted())
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_APPROVED).data(null).build();
        SiptisUser user = tutor.getTutor();
        String tutorName = user.getFullName();
        Collection<ProjectStudent> students = project.getStudents();
        String key = "";
        for (ProjectStudent projectStudent : students) {
            UserInformation student = projectStudent.getStudent().getUserInformation();
            String studentName = student.getNames() + " " + student.getLastNames();

            Set<UserCareer> career = projectStudent.getStudent().getCareer();
            String careerName = career.iterator().next().getName();

            String directorName = siptisUserServiceCareerDirectorOperations.getCareerDirectorName(careerName);
            if (directorName == null)
                ServiceAnswer.builder().serviceMessage(ServiceMessage.NO_CURRENT_DIRECTOR).data(null).build();
            String filename = letterTool.generateTutorTribunalRequest(
                    tutorName, studentName, directorName, careerName, projectName, student.getCi());
            key = nube.uploadLetterToCloud(filename, projectName);
            Optional<Document> oDocument = documentRepository.findDocumentByPath(key);
            Document document;
            if (oDocument.isEmpty()) {
                document = new Document();
            } else {
                document = oDocument.get();
            }
            document.setPath(key);
            document.setType(DocumentType.LETTER.toString());
            document.setDescription("Carta de Conformidad y solicitud de asignación de tribunales, emitida por el tutor encargado del proyecto.");
            document.setDate(LocalDateTime.now());
            document.setSiptisUser(projectStudent.getStudent());
            documentRepository.save(document);
        }
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.DOCUMENT_GENERATED).data(key).build();
    }

    @Override
    public ServiceAnswer supervisorTribunalRequest(LetterGenerationRequestDTO dto) throws IOException {
        LetterTool letterTool = new LetterTool();
        Optional<Project> projectOptional = projectRepository.findById(dto.getProjectId());
        if (projectOptional.isEmpty())
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_FOUND).data(null).build();
        Project project = projectOptional.get();
        String projectName = project.getName();
        ProjectSupervisor supervisor = projectSupervisorRepository.findBySupervisorIdAndProjectId(dto.getUserId(), dto.getProjectId());
        if (supervisor == null)
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_FOUND).data(null).build();
        if (supervisor.getAccepted() == null || !supervisor.getAccepted())
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_APPROVED).data(null).build();
        SiptisUser user = supervisor.getSupervisor();
        String supervisorName = user.getFullName();
        Collection<ProjectStudent> students = project.getStudents();
        String key = "";
        for (ProjectStudent projectStudent : students) {
            UserInformation student = projectStudent.getStudent().getUserInformation();
            String studentName = student.getNames() + " " + student.getLastNames();
            Set<UserCareer> career = projectStudent.getStudent().getCareer();
            String careerName = career.iterator().next().getName();

            String directorName = siptisUserServiceCareerDirectorOperations.getCareerDirectorName(careerName);
            if (directorName == null)
                ServiceAnswer.builder().serviceMessage(ServiceMessage.NO_CURRENT_DIRECTOR).data(null).build();
            String filename = letterTool.generateSupervisorTribunalRequest(
                    supervisorName, studentName, directorName, careerName, projectName, student.getCi());
            key = nube.uploadLetterToCloud(filename, projectName);
            Optional<Document> oDocument = documentRepository.findDocumentByPath(key);
            Document document;
            if (oDocument.isEmpty()) {
                document = new Document();
            } else {
                document = oDocument.get();
            }
            document.setPath(key);
            document.setType(DocumentType.LETTER.toString());
            document.setDescription("Carta de Conformidad y solicitud de asignación de tribunales, emitida por el tutor encargado del proyecto.");
            document.setDate(LocalDateTime.now());
            document.setSiptisUser(projectStudent.getStudent());
            documentRepository.save(document);
        }
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.DOCUMENT_GENERATED).data(key).build();
    }

    @Override
    public ServiceAnswer studentTribunalRequest(LetterGenerationRequestDTO dto) throws IOException {
        LetterTool letterTool = new LetterTool();
        Optional<Project> projectOptional = projectRepository.findById(dto.getProjectId());
        if (projectOptional.isEmpty())
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_FOUND).data(null).build();
        Project project = projectOptional.get();
        String projectName = project.getName();
        ProjectStudent projectStudent = projectStudentRepository.findByStudentIdAndProjectId(dto.getUserId(), dto.getProjectId());
        if (projectStudent == null)
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_FOUND).data(null).build();
        UserInformation student = projectStudent.getStudent().getUserInformation();
        String studentName = student.getNames() + " " + student.getLastNames();
        String studentCi = student.getCi();
        String key = "";
        Set<UserCareer> career = projectStudent.getStudent().getCareer();
        String careerName = career.iterator().next().getName();
        String directorName = siptisUserServiceCareerDirectorOperations.getCareerDirectorName(careerName);
        if (directorName == null)
            ServiceAnswer.builder().serviceMessage(ServiceMessage.NO_CURRENT_DIRECTOR).data(null).build();
        String filename = letterTool.generateStudentTribunalRequest(studentName, directorName, careerName, projectName, studentCi);
        key = nube.uploadLetterToCloud(filename, projectName);
        Optional<Document> oDocument = documentRepository.findDocumentByPath(key);
        Document document;
        if (oDocument.isEmpty()) {
            document = new Document();
        } else {
            document = oDocument.get();
        }
        document.setPath(key);
        document.setType(DocumentType.LETTER.toString());
        document.setDescription("Carta de solicitud de asignación de tribunales emitida por estudiante que realizó el proyecto.");
        document.setDate(LocalDateTime.now());
        document.setSiptisUser(projectStudent.getStudent());
        documentRepository.save(document);

        return ServiceAnswer.builder().serviceMessage(ServiceMessage.DOCUMENT_GENERATED).data(key).build();
    }

    @Override
    public ServiceAnswer generateTribunalApproval(LetterGenerationRequestDTO dto) throws IOException {
        LetterTool letterTool = new LetterTool();
        Optional<Project> projectOptional = projectRepository.findById(dto.getProjectId());
        if (projectOptional.isEmpty())
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_FOUND).data(null).build();
        Project project = projectOptional.get();
        String projectName = project.getName();
        Collection<ProjectStudent> students = project.getStudents();
        String key = "";
        ProjectTribunal tribunal = projectTribunalRepository.findByProject_IdAndTribunal_Id(dto.getUserId(), dto.getProjectId());
        if (tribunal == null)
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_FOUND).data(null).build();
        if (tribunal.getAccepted() == null || !tribunal.getAccepted())
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_APPROVED).data(null).build();

        SiptisUser user = tribunal.getTribunal();
        String tribunalName = user.getFullName();

        for (ProjectStudent projectStudent : students) {
            Set<UserCareer> career = projectStudent.getStudent().getCareer();
            String careerName = career.iterator().next().getName();
            String directorName = siptisUserServiceCareerDirectorOperations.getCareerDirectorName(careerName);
            if (directorName == null)
                ServiceAnswer.builder().serviceMessage(ServiceMessage.NO_CURRENT_DIRECTOR).data(null).build();

            String studentName = projectStudent.getStudent().getFullName();
            String filename = letterTool.generateTribunalApproval(
                    studentName, directorName, careerName, projectName, tribunalName);
            key = nube.uploadLetterToCloud(filename, projectName);
            Document document;
            Optional<Document> oDocument = documentRepository.findDocumentByPath(key);
            if (oDocument.isEmpty()) {
                document = new Document();
            } else {
                document = oDocument.get();
            }
            document.setPath(key);
            document.setType(DocumentType.LETTER.toString());
            document.setDescription("Carta de aprobación de Tribunal encargado del proyecto.");
            document.setDate(LocalDateTime.now());
            document.setSiptisUser(projectStudent.getStudent());
            documentRepository.save(document);
        }

        return ServiceAnswer.builder().serviceMessage(ServiceMessage.DOCUMENT_GENERATED).data(key).build();
    }
}
