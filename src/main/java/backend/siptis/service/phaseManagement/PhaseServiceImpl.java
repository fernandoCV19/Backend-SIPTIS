package backend.siptis.service.phaseManagement;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.phaseManagement.Phase;
import backend.siptis.model.entity.projectManagement.Project;
import backend.siptis.model.pjo.vo.projectManagement.PhaseVO;
import backend.siptis.model.repository.phaseManagement.PhaseRepository;
import backend.siptis.service.auth.siptisUserServices.SiptisUserServiceGeneralUserOperations;
import backend.siptis.service.projectManagement.project.ProjectServiceGetProjectInfo;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PhaseServiceImpl implements PhaseService {

    private final PhaseRepository phaseRepository;
    private final ProjectServiceGetProjectInfo projectServiceGetProjectInfo;
    private final SiptisUserServiceGeneralUserOperations siptisUserServiceGeneralUserOperations;

    @Override
    public ServiceAnswer findAllPhases() {
        List<Phase> list = phaseRepository.findAll();

        return ServiceAnswer.builder()
                .serviceMessage(ServiceMessage.OK)
                .data(list.stream().map(this::entityToVO))
                .build();
    }

    @Override
    public ServiceAnswer findPhaseByUserId(Long idPhase) {
        Phase phase = phaseRepository.findById(idPhase).orElse(null);
        if (phase == null) {
            return ServiceAnswer.builder()
                    .serviceMessage(ServiceMessage.NOT_FOUND)
                    .build();
        }
        ServiceAnswer.builder()
                .serviceMessage(ServiceMessage.OK)
                .data(phase)
                .build();
        return null;
    }

    @Override
    public ServiceAnswer getPhasesByUserId(Long id) {
        Long projectId = siptisUserServiceGeneralUserOperations.getProjectById(id);
        ServiceAnswer projectAnswer = projectServiceGetProjectInfo.getProjectById(projectId);
        if (projectAnswer.getServiceMessage() == ServiceMessage.PROJECT_ID_DOES_NOT_EXIST) {
            return ServiceAnswer.builder()
                    .serviceMessage(ServiceMessage.NOT_FOUND)
                    .build();
        }

        Long modalityId = ((Project) projectAnswer.getData()).getModality().getId();
        List<Phase> list = phaseRepository.findByModality_IdOrderByNumberPhaseAsc(modalityId);
        return ServiceAnswer.builder()
                .serviceMessage(ServiceMessage.OK)
                .data(list)
                .build();
    }

    @Override
    public ServiceAnswer findPhaseByModalityId(Long idModality) {
        List<Phase> list = phaseRepository.findByModality_IdOrderByNumberPhaseAsc(idModality);

        return ServiceAnswer.builder()
                .serviceMessage(ServiceMessage.OK)
                .data(list.stream().map(this::entityToVO))
                .build();
    }

    @Override
    public PhaseVO entityToVO(Phase phase) {
        PhaseVO phaseVO = new PhaseVO();
        BeanUtils.copyProperties(phase, phaseVO);
        System.out.print(phaseVO);
        return phaseVO;
    }
}
