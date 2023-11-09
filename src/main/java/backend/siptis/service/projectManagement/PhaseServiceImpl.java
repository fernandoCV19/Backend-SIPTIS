package backend.siptis.service.projectManagement;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.projectManagement.Phase;
import backend.siptis.model.pjo.dto.PhaseDTO;
import backend.siptis.model.repository.projectManagement.PhaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PhaseServiceImpl implements PhaseService {

    private final PhaseRepository phaseRepository;

    @Override
    public ServiceAnswer createPhase(PhaseDTO phaseDTO) {
        ServiceAnswer serviceAnswer = new ServiceAnswer();
        Phase phase = new Phase();
        phase.setName(phaseDTO.getName());
        phase = phaseRepository.save(phase);
        return ServiceAnswer.builder()
                .serviceMessage(ServiceMessage.OK)
                .data(phase).build();
    }

    @Override
    public ServiceAnswer deletePhase(Long idPhase) {
        ServiceAnswer serviceAnswer = new ServiceAnswer();
        Phase phase = phaseRepository.findById(idPhase).orElse(null);
        if (phase == null) {
            return ServiceAnswer.builder()
                    .serviceMessage(ServiceMessage.NOT_FOUND)
                    .build();
        }
        phaseRepository.deleteById(idPhase);
        return ServiceAnswer.builder()
                .serviceMessage(ServiceMessage.OK)
                .build();
    }

    @Override
    public ServiceAnswer updatePhase(PhaseDTO phaseDTO, Long idPhase) {
        ServiceAnswer serviceAnswer = new ServiceAnswer();
        Phase phase = phaseRepository.findById(idPhase).orElse(null);
        if (phase == null) {
            return ServiceAnswer.builder()
                    .serviceMessage(ServiceMessage.NOT_FOUND)
                    .build();
        }
        phase.setName(phaseDTO.getName());
        phase = phaseRepository.save(phase);
        return ServiceAnswer.builder()
                .serviceMessage(ServiceMessage.OK)
                .data(phase)
                .build();
    }

    @Override
    public ServiceAnswer findAllPhases() {
        ServiceAnswer serviceAnswer = new ServiceAnswer();
        ServiceAnswer.builder()
                .serviceMessage(ServiceMessage.OK)
                .data(phaseRepository.findAll())
                .build();
        return serviceAnswer;
    }

    @Override
    public ServiceAnswer findPhaseById(Long idPhase) {
        ServiceAnswer serviceAnswer = new ServiceAnswer();
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
}
