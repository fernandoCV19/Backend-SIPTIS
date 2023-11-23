package backend.siptis.service.notifications.general_activity_services;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.notifications.GeneralActivity;
import backend.siptis.model.pjo.dto.notifications.GeneralActivityDTO;
import backend.siptis.model.pjo.vo.GeneralActivityVO;
import backend.siptis.model.repository.notifications.GeneralActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class GeneralActivityServiceModifyOperations {

    private final GeneralActivityRepository generalActivityRepository;

    public ServiceAnswer persistGeneralActivity(GeneralActivityDTO generalActivityDTO) {
        GeneralActivity generalActivity = new GeneralActivity();
        generalActivity.setActivityDescription(generalActivityDTO.getActivityDescription());
        generalActivity.setActivityDate(generalActivityDTO.getActivityDate());
        generalActivity.setActivityName(generalActivityDTO.getActivityName());
        generalActivity = generalActivityRepository.save(generalActivity);

        return ServiceAnswer.builder()
                .serviceMessage(ServiceMessage.OK)
                .data(entityToVO(generalActivity))
                .build();
    }

    public ServiceAnswer update(GeneralActivityDTO generalActivityDTO, long id) {
        Optional<GeneralActivity> optionalGeneralActivity = generalActivityRepository.findById(id);

        if (!optionalGeneralActivity.isEmpty()) {
            GeneralActivity generalActivity = optionalGeneralActivity.get();
            generalActivity.setActivityName(generalActivityDTO.getActivityName());
            generalActivity.setActivityDescription(generalActivityDTO.getActivityDescription());
            generalActivity.setActivityDate(generalActivityDTO.getActivityDate());
            generalActivityRepository.save(generalActivity);
            Optional<GeneralActivity> activityFound = generalActivityRepository.findById(id);
            if(activityFound.isEmpty()){
                return ServiceAnswer.builder()
                        .serviceMessage(ServiceMessage.NOT_FOUND)
                        .build();
            }

            generalActivity = activityFound.get();

            return ServiceAnswer.builder()
                    .serviceMessage(ServiceMessage.OK)
                    .data(entityToVO(generalActivity))
                    .build();
        }
        return ServiceAnswer.builder()
                .serviceMessage(ServiceMessage.NOT_FOUND)
                .build();
    }

    public ServiceAnswer delete(long id) {
        Optional<GeneralActivity> generalActivity = generalActivityRepository.findById(id);
        if (!generalActivity.isEmpty()) {
            generalActivityRepository.deleteById(id);

            return ServiceAnswer.builder()
                    .serviceMessage(ServiceMessage.OK)
                    .data(null)
                    .build();
        }
        return ServiceAnswer.builder()
                .serviceMessage(ServiceMessage.NOT_FOUND)
                .build();
    }

    public GeneralActivityVO entityToVO(GeneralActivity generalActivity) {
        GeneralActivityVO generalActivityVO = new GeneralActivityVO();
        BeanUtils.copyProperties(generalActivity, generalActivityVO);
        return generalActivityVO;
    }
}
