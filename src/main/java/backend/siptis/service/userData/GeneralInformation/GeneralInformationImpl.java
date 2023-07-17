package backend.siptis.service.userData.GeneralInformation;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.projectManagement.Area;
import backend.siptis.model.entity.projectManagement.Project;
import backend.siptis.model.entity.userData.UserArea;
import backend.siptis.model.entity.userData.UserCareer;
import backend.siptis.model.pjo.dto.PotentialTribunalDTO;
import backend.siptis.model.pjo.dto.ProjectDTO;
import backend.siptis.model.pjo.dto.ProjectInfoDTO;
import backend.siptis.model.pjo.dto.UserSelectedAreasDTO;
import backend.siptis.model.pjo.dto.stadisticsDTO.UserTribunalDTO;
import backend.siptis.model.repository.general.UserAreaRepository;
import backend.siptis.model.repository.general.UserCareerRepository;
import backend.siptis.model.repository.projectManagement.AreaRepository;
import backend.siptis.model.repository.projectManagement.ProjectRepository;
import backend.siptis.model.repository.userData.SiptisUserRepository;
import backend.siptis.model.repository.userData.UserInformationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class GeneralInformationImpl implements GeneralInformationService{

    @Autowired
    private final UserAreaRepository areaRepository;
    @Autowired
    private final AreaRepository projectAreaRepository;
    @Autowired
    private final UserCareerRepository careerRepository;
    @Autowired
    private final UserInformationRepository userInformationRepository;
    @Autowired
    private final SiptisUserRepository siptisUserRepository;
    @Autowired
    private final ProjectRepository projectRepository;




    @Override
    public ServiceAnswer getAllPotentialTribunals() {
        List<UserTribunalDTO> tribunalDTOS = new ArrayList<>();
        List<PotentialTribunalDTO> tribunals = userInformationRepository.getTeachersInformation();
        for (PotentialTribunalDTO user : tribunals) {
            Optional<SiptisUser> siptisUser = siptisUserRepository.findById(user.getId());
            Set<UserArea> userAreas = siptisUser.get().getAreas();
            UserTribunalDTO dto = new UserTribunalDTO();
            dto.setId(user.getId());
            dto.setNames(user.getNames());
            dto.setLastnames(user.getLastnames());
            dto.setAreas(userAreas);
            tribunalDTOS.add(dto);
        }
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK)
                .data(tribunalDTOS ).build();

    }

    public ServiceAnswer getProjects() {
        List<ProjectDTO> response = new ArrayList<>();
        List<ProjectInfoDTO> projects = projectRepository.getProjectsList();

        for (ProjectInfoDTO info : projects) {
            Optional<Project> pj = projectRepository.findById(info.getId());
            Set<Area> projectAreas = pj.get().getAreas();
            ProjectDTO projectDTO = new ProjectDTO();
            projectDTO.setName(info.getName());
            projectDTO.setModality(info.getModality());
            projectDTO.setPerfil(info.getPerfil());
            projectDTO.setAreas(projectAreas);
            projectDTO.setModalityId(info.getModalityId());

            response.add(projectDTO);
        }

        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK)
                .data(response ).build();
    }

    @Override
    public ServiceAnswer getProjectsByAreas(UserSelectedAreasDTO dto) {
        List<Long> selectedAreas = dto.getIds();
        if (selectedAreas.isEmpty()){
            return getProjects();
        }
        List<ProjectDTO> response = new ArrayList<>();
        List<ProjectInfoDTO> projects = projectRepository.getProjectsList();

        for (ProjectInfoDTO info : projects) {
            boolean isValid = false;
            Optional<Project> pj = projectRepository.findById(info.getId());
            Set<Area> projectAreas = pj.get().getAreas();

            for (Area area: projectAreas) {
                if(selectedAreas.contains(area.getId())){
                    isValid = true;
                    break;
                }
            }
            if(isValid){
                ProjectDTO projectDTO = new ProjectDTO();
                projectDTO.setName(info.getName());
                projectDTO.setModality(info.getModality());
                projectDTO.setPerfil(info.getPerfil());
                projectDTO.setAreas(projectAreas);
                projectDTO.setModalityId(info.getModalityId());

                response.add(projectDTO);
            }

        }

        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK)
                .data(response ).build();
    }

    @Override
    public ServiceAnswer getPotentialTribunalsByAreas(UserSelectedAreasDTO areasDto) {
        List<UserTribunalDTO> tribunalDTOS = new ArrayList<>();
        List<Long> selectedAreas = areasDto.getIds();

        if (selectedAreas.isEmpty()){
            return getAllPotentialTribunals();
        }

        List<PotentialTribunalDTO> tribunals = userInformationRepository.getTeachersInformation();
        for (PotentialTribunalDTO user : tribunals) {
            boolean isValid = false;
            Optional<SiptisUser> siptisUser = siptisUserRepository.findById(user.getId());
            Set<UserArea> userAreas = siptisUser.get().getAreas();

            for (UserArea area: userAreas) {
                if(selectedAreas.contains(area.getId())){
                    isValid = true;
                    break;
                }
            }

            if(isValid){
                UserTribunalDTO dto = new UserTribunalDTO();
                dto.setId(user.getId());
                dto.setNames(user.getNames());
                dto.setLastnames(user.getLastnames());
                dto.setAreas(userAreas);
                tribunalDTOS.add(dto);
            }
        }
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK)
                .data(tribunalDTOS ).build();

    }
}
