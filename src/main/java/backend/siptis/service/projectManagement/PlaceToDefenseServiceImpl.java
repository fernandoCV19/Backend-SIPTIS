package backend.siptis.service.projectManagement;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.projectManagement.Defense;
import backend.siptis.model.entity.projectManagement.PlaceToDefense;
import backend.siptis.model.pjo.vo.projectManagement.PlaceToDefenseVO;
import backend.siptis.model.repository.projectManagement.PlaceToDefenseRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PlaceToDefenseServiceImpl implements PlaceToDefenseService{

    private final PlaceToDefenseRepository placeToDefenseRepository;

    @Override
    public ServiceAnswer getReservedDates() {
        List<PlaceToDefense> query = placeToDefenseRepository.findAll();
        HashMap<String, List<Date>> reservedDates = new HashMap<>();
        for(PlaceToDefense place: query){
            if(place.getDefenses().size() > 0) {
                reservedDates.put(place.getName() + ":" + place.getLocation(), place.getDefenses().stream().map(Defense::getDate).toList());
            }
        }
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(reservedDates).build();
    }

    @Override
    public ServiceAnswer getAvailablePlaces() {
        List<PlaceToDefense> query = placeToDefenseRepository.findAll();
        List<PlaceToDefenseVO> res = query.stream().map((PlaceToDefenseVO::new)).toList();
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(res).build();
    }
}
