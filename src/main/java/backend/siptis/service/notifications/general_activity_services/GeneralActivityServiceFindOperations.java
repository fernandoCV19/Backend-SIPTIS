package backend.siptis.service.notifications.general_activity_services;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.notifications.GeneralActivity;
import backend.siptis.model.pjo.vo.GeneralActivityVO;
import backend.siptis.model.repository.notifications.GeneralActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class GeneralActivityServiceFindOperations {

    private final GeneralActivityRepository generalActivityRepository;

    public ServiceAnswer findById(long id) {
        Optional<GeneralActivity> generalActivityOptional = generalActivityRepository.findById(id);

        if (!generalActivityOptional.isEmpty()) {
            return ServiceAnswer.builder()
                    .serviceMessage(ServiceMessage.OK)
                    .data(entityToVO(generalActivityOptional.get()))
                    .build();
        }
        return ServiceAnswer.builder()
                .serviceMessage(ServiceMessage.NOT_FOUND)
                .build();
    }

    public List<GeneralActivityVO> findAllVO() {
        List<GeneralActivity> generalActivitiesList = generalActivityRepository.findAll();
        ArrayList<GeneralActivityVO> generalActivityArrayList = new ArrayList<>();
        for (GeneralActivity generalActivity : generalActivitiesList) {
            generalActivityArrayList.add(entityToVO(generalActivity));
        }
        return generalActivityArrayList;
    }

    public Page<GeneralActivity> findAll(Pageable pageable) {
        LocalDateTime now = LocalDateTime.now();
        Date actual = new Date(now.getYear() - 1900, now.getMonthValue() - 1, now.getDayOfMonth() - 1);
        return generalActivityRepository.findAllAfterADate(actual, pageable);
    }

    public Page<GeneralActivityVO> findAllVO(Pageable pageable) {
        Page<GeneralActivity> pageFound = generalActivityRepository.findAll(pageable);
        List<GeneralActivity> generalList = generalActivityRepository.findAll();

        List<GeneralActivity> activitiesRes = new ArrayList<>();
        for (GeneralActivity activity : generalList) {
            if (isAfter(activity.getActivityDate())) {
                activitiesRes.add(activity);
            }
        }
        return new PageImpl(entityToVO(activitiesRes), pageable, pageFound.getTotalElements());
    }

    private boolean isAfter(Date date) {
        int mesActual = LocalDateTime.now().getMonthValue();
        int diaActual = LocalDateTime.now().getDayOfMonth();
        int anioActual = LocalDateTime.now().getYear();
        Date actual = new Date(anioActual - 1900, mesActual - 1, diaActual - 1);
        return date.after(actual);
    }

    private List<GeneralActivityVO> entityToVO(List<GeneralActivity> list) {
        List<GeneralActivityVO> generalActivityVOList = new ArrayList<>();
        for (GeneralActivity generalActivity : list) {
            GeneralActivityVO generalActivityVO = new GeneralActivityVO();
            BeanUtils.copyProperties(generalActivity, generalActivityVO);
            generalActivityVOList.add(generalActivityVO);
        }
        return generalActivityVOList;
    }

    public GeneralActivityVO entityToVO(GeneralActivity generalActivity) {
        GeneralActivityVO generalActivityVO = new GeneralActivityVO();
        BeanUtils.copyProperties(generalActivity, generalActivityVO);
        return generalActivityVO;
    }
}
