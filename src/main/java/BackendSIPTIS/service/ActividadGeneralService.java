package BackendSIPTIS.service;

import BackendSIPTIS.model.entity.gestionProyecto.ActividadGeneral;
import BackendSIPTIS.model.pjo.dto.ActividadGeneralDTO;
import BackendSIPTIS.model.pjo.vo.ActividadGeneralVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.List;

public interface ActividadGeneralService {
    Optional<ActividadGeneral> findById(int id);
    ActividadGeneral persistCategoria(ActividadGeneralDTO actividadGeneralDTO);
    List<ActividadGeneralVO> findAllVO();
    Page<ActividadGeneralVO> findAllVO(Pageable pageable);
    void update(ActividadGeneral actividadGeneral);
    void delete(ActividadGeneral actividadGeneral);
    ActividadGeneralVO entityToVO(ActividadGeneral actividadGeneral);
}
