package backend.siptis.service.notifications;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.entity.notifications.GeneralActivity;
import backend.siptis.model.pjo.dto.notifications.GeneralActivityDTO;
import backend.siptis.model.pjo.vo.GeneralActivityVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GeneralActivityService {
    ServiceAnswer findById(long id);

    ServiceAnswer persistGeneralActivity(GeneralActivityDTO generalActivityDTO);

    List<GeneralActivityVO> findAllVO();

    Page<GeneralActivityVO> findAllVO(Pageable pageable);

    Page<GeneralActivity> findAll(Pageable pageable);

    ServiceAnswer update(GeneralActivityDTO generalActivityDTO, long id);

    ServiceAnswer delete(long id);

    GeneralActivityVO entityToVO(GeneralActivity generalActivity);
}
