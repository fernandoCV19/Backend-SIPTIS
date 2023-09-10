package backend.siptis.service.projectManagement;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.projectManagement.Defense;
import backend.siptis.model.entity.projectManagement.PlaceToDefense;
import backend.siptis.model.pjo.vo.projectManagement.PlaceReservedByMonthVO;
import backend.siptis.model.repository.projectManagement.DefenseRepository;
import backend.siptis.model.repository.projectManagement.PlaceToDefenseRepository;
import backend.siptis.model.repository.userData.SiptisUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class DefenseServiceImpl implements DefenseService{

    private DefenseRepository defenseRepository;
    private PlaceToDefenseRepository placeToDefenseRepository;
    private SiptisUserRepository siptisUserRepository;

    @Override
    public ServiceAnswer getPlaceReservationsAndDirectorByMonth(Long placeId, Integer month, Long directorId) {
        Optional<PlaceToDefense> placeToDefenseOptional = placeToDefenseRepository.findById(placeId);
        if(placeToDefenseOptional.isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_PLACE_DOES_NOT_EXIST).data("Invalidad place id").build();
        }
        if(siptisUserRepository.findById(directorId).isEmpty()){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.USER_ID_DOES_NOT_EXIST).data("The is user does not exist").build();
        }
        if(month < 1 || month > 12){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.MONTH_NUMBER_NOT_VALID).data("Month must be a number between 1 and 12").build();
        }

        PlaceToDefense placeToDefense = placeToDefenseOptional.get();
        LocalDateTime now = LocalDateTime.now();

        List<Defense> defenses = placeToDefense
                .getDefenses()
                .stream()
                .filter(defense -> defense.getDate().getMonthValue() == month && defense.getDate().getYear() == now.getYear() && defense.getDirector().getId().equals(directorId))
                .toList();

        PlaceReservedByMonthVO res = new PlaceReservedByMonthVO(defenses, month);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(res).build();
    }
}
