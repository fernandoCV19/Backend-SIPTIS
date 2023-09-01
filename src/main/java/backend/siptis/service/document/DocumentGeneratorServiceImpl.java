package backend.siptis.service.document;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.DocumentType;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.editorsAndReviewers.ProjectStudent;
import backend.siptis.model.entity.editorsAndReviewers.ProjectTeacher;
import backend.siptis.model.entity.editorsAndReviewers.ProjectTribunal;
import backend.siptis.model.entity.projectManagement.Phase;
import backend.siptis.model.entity.projectManagement.Project;
import backend.siptis.model.entity.userData.Document;
import backend.siptis.model.entity.userData.UserCareer;
import backend.siptis.model.entity.userData.UserInformation;
import backend.siptis.model.pjo.dto.document.ReportDocumentDTO;
import backend.siptis.model.pjo.dto.document.DocumentaryRecordDto;
import backend.siptis.model.repository.projectManagement.PhaseRepository;
import backend.siptis.model.repository.projectManagement.ProjectRepository;
import backend.siptis.model.repository.userData.DocumentRepository;
import backend.siptis.model.repository.userData.SiptisUserRepository;
import backend.siptis.model.repository.userData.UserInformationRepository;
import backend.siptis.service.cloud.CloudManagementService;
import backend.siptis.service.document.generationTools.DocumentaryRecordTool;
import backend.siptis.service.document.generationTools.LetterTool;
import backend.siptis.service.document.generationTools.ReportTool;
import backend.siptis.service.document.generationTools.SolvencyTool;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
@RequiredArgsConstructor
public class DocumentGeneratorServiceImpl implements DocumentGeneratorService {
    @Autowired
    private CloudManagementService nube;

    @Autowired
    private SiptisUserRepository siptisUserRepository;

    @Autowired
    private UserInformationRepository userInformationRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private PhaseRepository phaseRepository;

    @Override
    public ServiceAnswer getAllDocumentsFromUser (long idUser){
        Optional<SiptisUser> oUser = siptisUserRepository.findOneById(idUser);
        if (oUser.isEmpty()){return ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_FOUND).data(null).build();}
        SiptisUser user = oUser.get();
        List<Document> documents =  user.getDocuments().stream().toList();
        if (documents.isEmpty()){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NO_DOCUMENTS).data(null).build();
        }
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(documents).build();
    }

    @Override
    public ServiceAnswer deleteDocument (long idDocument){
        Optional<Document> oDocument = documentRepository.findById(idDocument);
        if (oDocument.isEmpty()){return ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_FOUND).data(null).build();}
        Document document = oDocument.get();
        String path = document.getPath();
        nube.deleteObject(path);
        documentRepository.delete(document);
        documentRepository.flush();
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.DOCUMENT_DELETED).data(document).build();
    }

    @Override
    public ServiceAnswer generateReport (ReportDocumentDTO reportDocumentDTO){
        Long idProject = reportDocumentDTO.getProjectId();
        Long userId = reportDocumentDTO.getUserId();
        Optional <Project> optionalProject = projectRepository.findById(idProject);
        if (optionalProject .isEmpty()){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ERROR).data(null).build();
        }
        Project project = optionalProject.get();
        //List<String> aaaa = userInformationRepository.getTeachersNames(idProject);
        List<String> tutors = userInformationRepository.getTutorsNames(idProject);
        String teacherCompleteName = userInformationRepository.getTeachersNames(idProject).get(0);
        //String tutorCompleteName = userInformationRepository.getTutorsNames(idProject).get(0);
        String title = project.getName();
        int reportNumber = (project.getReportIndex() + 1);
        Optional<SiptisUser> oUser = siptisUserRepository.findOneById(userId);

        if (oUser.isEmpty()){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_FOUND).data(null).build();
        }
        SiptisUser user = oUser.get();
        String postulant = user.getUserInformation().getNames()+ ' '+user.getUserInformation().getLastnames();
        ReportTool reportTool = new ReportTool();
        String filename = reportTool.generate(postulant,Integer.toString(reportNumber), title,tutors,teacherCompleteName,reportDocumentDTO.getDescription()) ;
        String key = nube.uploadDocumentToCloud(filename);

        backend.siptis.model.entity.userData.Document document = new backend.siptis.model.entity.userData.Document();
        document.setPath(key);
        document.setType(DocumentType.REPORT.toString());
        document.setDescription(reportDocumentDTO.getShortDescription());
        document.setSiptisUser(user);
        documentRepository.save(document);

        project.setReportIndex(reportNumber);
        projectRepository.save(project);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.DOCUMENT_GENERATED).data(key).build();
    }
    @Override
    public ServiceAnswer generateSolvency(long idUser){
        Optional<SiptisUser> oUser = siptisUserRepository.findOneById(idUser);

        if (oUser.isEmpty()){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_FOUND).data(null).build();
        }
        SiptisUser user = oUser.get();
        UserInformation info = user.getUserInformation();
        String name = info.getNames() + " " + info.getLastnames();
        String ci = info.getCi();
        UserCareer career = null;
        String careerName = "";
        for (UserCareer userCareer : user.getCareer()) {
            career = userCareer;
        }
        if(career != null)
            careerName = career.getName();

        Path location = null;
        try {
            location = blueprintRetrieve("CertificadoSolvencia.pdf");
        }catch(IOException e){
            System.err.println(e.getMessage());
        }

        SolvencyTool solvencyTool = new SolvencyTool(location);
        String filename = solvencyTool.generate(name, ci, careerName);

        String key = nube.uploadDocumentToCloud(filename);
        backend.siptis.model.entity.userData.Document document = new backend.siptis.model.entity.userData.Document();
        document.setPath(key);
        document.setType(DocumentType.FORM.toString());
        document.setDescription("Formulario de Solvencia");
        document.setSiptisUser(user);
        documentRepository.save(document);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.DOCUMENT_GENERATED).data(key).build();
    }
    @Override
    public ServiceAnswer generateDocumentaryRecord(DocumentaryRecordDto documentaryRecordDto){
        Long idProject = documentaryRecordDto.getProjectId();
        Long userId = documentaryRecordDto.getUserId();
        Optional <Project> optionalProject = projectRepository.findById(idProject);
        if (optionalProject .isEmpty()){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ERROR).data(null).build();
        }
        Project project = optionalProject.get();
        String modality = project.getModality().getName();
        List<String> tutors = userInformationRepository.getTutorsNames(idProject);
        String title = project.getName();

        Optional<SiptisUser> oUser = siptisUserRepository.findOneById(userId);

        if (oUser.isEmpty()){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_FOUND).data(null).build();
        }
        SiptisUser user = oUser.get();
        UserInformation info = user.getUserInformation();
        String authorLastNames =  info.getLastnames() ;
        String authorNames =   info.getNames();
        UserCareer career = null;
        String careerName = "";
        for (UserCareer userCareer : user.getCareer()) {
            career = userCareer;
        }
        if(career != null)
            careerName = career.getName();

        Path location = null;
        try {
            location = blueprintRetrieve("FichaDocumental.pdf");
        }catch(IOException e){
            System.err.println(e.getMessage());
        }

        DocumentaryRecordTool documentaryRecordTool = new DocumentaryRecordTool(location);

        String filename = documentaryRecordTool.generate(documentaryRecordDto, modality ,careerName, authorNames, authorLastNames, tutors, title);
        String key = nube.uploadDocumentToCloud(filename);
        backend.siptis.model.entity.userData.Document document = new backend.siptis.model.entity.userData.Document();
        document.setPath(key);
        document.setType(DocumentType.RECORD.toString());
        document.setDescription("Ficha documental");
        document.setSiptisUser(user);
        documentRepository.save(document);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.DOCUMENT_GENERATED).data(key).build();
    }

    private Path blueprintRetrieve(String blueprintName) throws IOException {
        ByteArrayOutputStream document= nube.getObject("Plantillas/"+blueprintName);
        Path tempDir = Files.createTempDirectory("directory");
        Path tempFilePath = tempDir.resolve(blueprintName);
        byte[] bytes = document.toByteArray();
        Files.write(tempFilePath, bytes);

        return tempFilePath;
    }

    @Override
    public ServiceAnswer tribunalRequest(long id) throws IOException {
        LetterTool letterTool = new LetterTool();

        if(!projectRepository.existsById(id))
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_FOUND).data(null).build();
        Project project = projectRepository.findById(52L).get();
        String projectName = project.getName();
        Collection<ProjectStudent> students = project.getStudents();
        // Collection<ProjectTutor> tutors = project.getTutors();
        Collection<ProjectTeacher> teachers = project.getTeachers();
        UserInformation teacher = teachers.iterator().next().getTeacher().getUserInformation();
        String teacherName = teacher.getLastnames() + " " + teacher.getNames();

        ArrayList<String> response = new ArrayList<>();
        String key = "";

        for (ProjectStudent projectStudent: students) {
            UserInformation student = projectStudent.getStudent().getUserInformation();
            String studentName = student.getLastnames() + " " + student.getNames();
            Set<UserCareer> career = projectStudent.getStudent().getCareer();
            String careerName = career.iterator().next().getName();

            String filename = letterTool.generateTribunalRequest(
                    studentName, "Calancha Navia Boris Marcelo", careerName, projectName, teacherName);
            response.add(filename);

            key = nube.uploadLetterToCloud(filename);

            backend.siptis.model.entity.userData.Document document = new backend.siptis.model.entity.userData.Document();
            document.setPath(key);
            document.setPhase(phaseRepository.findById(1l).get());
            document.setType(DocumentType.LETTER.toString());
            document.setDescription("Solicitud de tribunales");
            document.setSiptisUser(projectStudent.getStudent());
            documentRepository.save(document);

        }

        return ServiceAnswer.builder().serviceMessage(ServiceMessage.DOCUMENT_GENERATED).data(key).build();
    }

    @Override
    public ServiceAnswer generateTribunalApproval(Long id) throws IOException {
        LetterTool letterTool = new LetterTool();

        if(!projectRepository.existsById(id))
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_FOUND).data(null).build();
        Project project = projectRepository.findById(52L).get();
        String projectName = project.getName();
        Collection<ProjectStudent> students = project.getStudents();
        Collection<ProjectTribunal> tribunals = project.getTribunals();
        // Collection<ProjectTutor> tutors = project.getTutors();
        ProjectStudent projectStudent = students.iterator().next();
        UserInformation student = projectStudent.getStudent().getUserInformation();
        String studentName = student.getLastnames() + " " + student.getNames();
        Set<UserCareer> career = projectStudent.getStudent().getCareer();
        String careerName = career.iterator().next().getName();

        ArrayList<String> response = new ArrayList<>();
        String key = "";

        for (ProjectTribunal tribunal: tribunals) {

            UserInformation tribunalInfo = tribunal.getTribunal().getUserInformation();
            String tribunalName = tribunalInfo.getLastnames() + " " + tribunalInfo.getNames();
            String filename = letterTool.generateTribunalApproval(
                    studentName, "Calancha Navia Boris Marcelo", careerName, projectName, tribunalName);
            response.add(filename);

            key = nube.uploadLetterToCloud(filename);

            backend.siptis.model.entity.userData.Document document = new backend.siptis.model.entity.userData.Document();
            document.setPath(key);
            document.setPhase(phaseRepository.findById(1l).get());
            document.setType(DocumentType.LETTER.toString());
            document.setDescription("Solicitud de tribunales");
            document.setSiptisUser(projectStudent.getStudent());
            documentRepository.save(document);

        }

        return ServiceAnswer.builder().serviceMessage(ServiceMessage.DOCUMENT_GENERATED).data(key).build();
    }


}