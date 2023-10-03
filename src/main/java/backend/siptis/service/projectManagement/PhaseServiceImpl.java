package backend.siptis.service.projectManagement;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.projectManagement.Phase;
import backend.siptis.model.entity.projectManagement.Project;
import backend.siptis.model.pjo.dto.PhaseDTO;
import backend.siptis.model.pjo.vo.projectManagement.PhaseVO;
import backend.siptis.model.repository.projectManagement.PhaseRepository;
import backend.siptis.service.userData.SiptisUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PhaseServiceImpl implements PhaseService{

    private final PhaseRepository phaseRepository;
    private final SiptisUserService siptisUserService;
    private final ProjectService projectService;
    @Override
    public ServiceAnswer createPhase(PhaseDTO phaseDTO) {
        ServiceAnswer serviceAnswer = new ServiceAnswer();
        Phase phase = new Phase();
        phase.setName(phaseDTO.getName());
        phase = phaseRepository.save(phase);
        return serviceAnswer.builder()
                .serviceMessage(ServiceMessage.OK)
                .data(phase).build();
    }

    @Override
    public ServiceAnswer deletePhase(Long idPhase) {
        ServiceAnswer serviceAnswer = new ServiceAnswer();
        Phase phase = phaseRepository.findById(idPhase).orElse(null);
        if (phase == null){
            return serviceAnswer.builder()
                    .serviceMessage(ServiceMessage.NOT_FOUND)
                    .build();
        }
        phaseRepository.deleteById(idPhase);
        return serviceAnswer.builder()
                .serviceMessage(ServiceMessage.OK)
                .build();
    }
    @Override
    public ServiceAnswer updatePhase(PhaseDTO phaseDTO, Long idPhase) {
        ServiceAnswer serviceAnswer = new ServiceAnswer();
        Phase phase = phaseRepository.findById(idPhase).orElse(null);
        if (phase == null){
            return serviceAnswer.builder()
                    .serviceMessage(ServiceMessage.NOT_FOUND)
                    .build();
        }
        phase.setName(phaseDTO.getName());
        phase = phaseRepository.save(phase);
        return serviceAnswer.builder()
                .serviceMessage(ServiceMessage.OK)
                .data(phase)
                .build();
    }

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
        ServiceAnswer serviceAnswer = new ServiceAnswer();
        Phase phase = phaseRepository.findById(idPhase).orElse(null);
        if (phase == null){
            return serviceAnswer.builder()
                    .serviceMessage(ServiceMessage.NOT_FOUND)
                    .build();
        }
        serviceAnswer.builder()
                .serviceMessage(ServiceMessage.OK)
                .data(phase)
                .build();
        return null;
    }

    @Override
    public ServiceAnswer getPhasesByUserId(Long id){
        Long projectId = siptisUserService.getProjectById(id);
        ServiceAnswer projectAnswer = projectService.getProjectById(projectId);
        if(projectAnswer.getServiceMessage() == ServiceMessage.PROJECT_ID_DOES_NOT_EXIST){
            return ServiceAnswer.builder()
                    .serviceMessage(ServiceMessage.NOT_FOUND)
                    .build();
        }

        Long modalityId = ((Project)projectAnswer.getData()).getModality().getId();
        List<Phase> list = phaseRepository.findAllByModalityId(modalityId);
        return ServiceAnswer.builder()
                .serviceMessage(ServiceMessage.OK)
                .data(list)
                .build();
    }
    @Override
    public ServiceAnswer findPhaseByModalityId(Long idModality){
        List<Phase> list = phaseRepository.findAllByModalityId(idModality);

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
