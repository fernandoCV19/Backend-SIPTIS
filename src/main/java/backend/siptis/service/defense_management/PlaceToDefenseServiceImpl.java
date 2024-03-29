package backend.siptis.service.defense_management;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.defense_management.PlaceToDefense;
import backend.siptis.model.pjo.vo.project_management.PlaceToDefenseVO;
import backend.siptis.model.pjo.vo.project_management.PlaceWithReservedDateVO;
import backend.siptis.model.repository.defense_management.PlaceToDefenseRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PlaceToDefenseServiceImpl implements PlaceToDefenseService {

    private final PlaceToDefenseRepository placeToDefenseRepository;

    @Override
    public ServiceAnswer getReservedDates() {
        List<PlaceToDefense> query = placeToDefenseRepository.findAll();
        List<PlaceWithReservedDateVO> reservedDates = query.stream().filter(placeToDefense -> !placeToDefense.getDefenses().isEmpty()).map(PlaceWithReservedDateVO::new).toList();
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(reservedDates).build();
    }

    @Override
    public ServiceAnswer getAvailablePlaces() {
        List<PlaceToDefense> query = placeToDefenseRepository.findAll();
        List<PlaceToDefenseVO> res = query.stream().map((PlaceToDefenseVO::new)).toList();
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(res).build();
    }
}
