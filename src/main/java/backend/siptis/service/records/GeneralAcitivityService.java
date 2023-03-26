package backend.siptis.service.records;

import backend.siptis.model.entity.records.GeneralActivity;
import backend.siptis.model.pjo.dto.records.GeneralActivityDTO;
import backend.siptis.model.pjo.vo.GeneralActivityVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.List;

public interface GeneralAcitivityService {
    Optional<GeneralActivity> findById(long id);
    GeneralActivityVO persistGeneralActivity(GeneralActivityDTO generalActivityDTO);
    List<GeneralActivityVO> findAllVO();
    Page<GeneralActivityVO> findAllVO(Pageable pageable);
    void update(GeneralActivityDTO generalActivityDTO, long id);
    void delete(long id);
    GeneralActivityVO entityToVO(GeneralActivity generalActivity);
}