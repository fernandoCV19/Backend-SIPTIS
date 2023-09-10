package backend.siptis.service.projectManagement;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.entity.projectManagement.Defense;
import backend.siptis.model.entity.projectManagement.PlaceToDefense;
import backend.siptis.model.repository.projectManagement.DefenseRepository;
import backend.siptis.model.repository.projectManagement.PlaceToDefenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class DefenseServiceImpl implements DefenseService{

    private DefenseRepository defenseRepository;
    private PlaceToDefenseRepository placeToDefenseRepository;

    @Override
    public ServiceAnswer getPlaceByMonth(Long placeId, Integer month) {
        Optional<PlaceToDefense> placeToDefenseOptional = placeToDefenseRepository.findById(placeId);
        if(placeToDefenseOptional.isEmpty()){
            return null;
        }
        PlaceToDefense placeToDefense = placeToDefenseOptional.get();
        Date now = new Date();

        List<Defense> defenses = placeToDefense.getDefenses().stream().filter(defense -> defense.getDate().getMonth() == month && defense.getDate().getYear() == now.getYear()).toList();



        return null;
    }
}
