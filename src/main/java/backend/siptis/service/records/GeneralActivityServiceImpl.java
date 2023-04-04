package backend.siptis.service.records;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.records.GeneralActivity;
import backend.siptis.model.pjo.dto.records.GeneralActivityDTO;
import backend.siptis.model.pjo.vo.GeneralActivityVO;
import backend.siptis.model.repository.records.GeneralActivityRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GeneralActivityServiceImpl implements GeneralActivityService {

    private final GeneralActivityRepository generalActivityRepository;
    @Autowired
    public GeneralActivityServiceImpl(GeneralActivityRepository generalActivityRepository) {
        this.generalActivityRepository = generalActivityRepository;
    }
    @Override
    public ServiceAnswer findById(long id) {
        Optional<GeneralActivity> generalActivityOptional = generalActivityRepository.findById(id);

        if(!generalActivityOptional.isEmpty()){
            return ServiceAnswer.builder()
                    .serviceMessage(ServiceMessage.OK)
                    .data(entityToVO(generalActivityOptional.get()))
                    .build();
        }
        return ServiceAnswer.builder()
                .serviceMessage(ServiceMessage.ID_DOES_NOT_EXIST)
                .build();
    }

    @Override
    public ServiceAnswer persistGeneralActivity(GeneralActivityDTO generalActivityDTO) {
        GeneralActivity generalActivity = new GeneralActivity();
        generalActivity.setActivityDescription(generalActivityDTO.getActivityDescription());
        generalActivity.setActivityDate(generalActivityDTO.getActivityDate());
        generalActivity.setActivityName(generalActivityDTO.getGeneralActivityName());
        generalActivity = generalActivityRepository.save(generalActivity);

        return ServiceAnswer.builder()
                .serviceMessage(ServiceMessage.ACTIVITY_CREATED)
                .data(entityToVO(generalActivity))
                .build();
    }

    @Override
    public List<GeneralActivityVO> findAllVO() {
        List<GeneralActivity> generalActivitiesList = generalActivityRepository.findAll();
        ArrayList<GeneralActivityVO> generalActivityArrayList = new ArrayList<>();
        for(GeneralActivity generalActivity: generalActivitiesList){
            generalActivityArrayList.add(entityToVO(generalActivity));
        }
        return generalActivityArrayList;
    }

    @Override
    public Page<GeneralActivityVO> findAllVO(Pageable pageable) {
        Page<GeneralActivity> generalActivities = generalActivityRepository.findAll(pageable);
        return generalActivities.map(this::entityToVO);
    }

    @Override
    public ServiceAnswer update(GeneralActivityDTO generalActivityDTO, long id) {
        Optional<GeneralActivity> optionalGeneralActivity = generalActivityRepository.findById(id);

        if(!optionalGeneralActivity.isEmpty()){
            GeneralActivity generalActivity = optionalGeneralActivity.get();
            generalActivity.setActivityName(generalActivityDTO.getGeneralActivityName());
            generalActivity.setActivityDescription(generalActivityDTO.getActivityDescription());
            generalActivity.setActivityDate(generalActivityDTO.getActivityDate());

            generalActivity = generalActivityRepository.findById(id).get();

            return ServiceAnswer.builder()
                    .serviceMessage(ServiceMessage.OK)
                    .data(generalActivity)
                    .build();
        }
        return ServiceAnswer.builder()
                .serviceMessage(ServiceMessage.ID_DOES_NOT_EXIST)
                .build();
    }

    @Override
    public ServiceAnswer delete(long id) {
        Optional<GeneralActivity> generalActivity = generalActivityRepository.findById(id);
        if(!generalActivity.isEmpty()) {
            generalActivityRepository.deleteById(id);
            generalActivity = generalActivityRepository.findById(id);
            return ServiceAnswer.builder()
                    .serviceMessage(ServiceMessage.OK)
                    .data(entityToVO(generalActivity.get()))
                    .build();
        }
        return ServiceAnswer.builder()
                .serviceMessage(ServiceMessage.ID_DOES_NOT_EXIST)
                .build();
    }

    @Override
    public GeneralActivityVO entityToVO(GeneralActivity generalActivity) {
        GeneralActivityVO generalActivityVO = new GeneralActivityVO();
        BeanUtils.copyProperties(generalActivity, generalActivityVO);
        return generalActivityVO;
    }
}
