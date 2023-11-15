package backend.siptis.service.defenseManagement;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.defenseManagement.Defense;
import backend.siptis.model.entity.defenseManagement.PlaceToDefense;
import backend.siptis.model.entity.projectManagement.Project;
import backend.siptis.model.pjo.dto.projectManagement.DefenseDTO;
import backend.siptis.model.pjo.vo.projectManagement.PlaceReservedByMonthVO;
import backend.siptis.model.repository.auth.SiptisUserRepository;
import backend.siptis.model.repository.defenseManagement.DefenseRepository;
import backend.siptis.model.repository.defenseManagement.PlaceToDefenseRepository;
import backend.siptis.model.repository.projectManagement.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefenseServiceImpl implements DefenseService {

    private final DefenseRepository defenseRepository;
    private final PlaceToDefenseRepository placeToDefenseRepository;
    private final SiptisUserRepository siptisUserRepository;
    private final ProjectRepository projectRepository;

    @Override
    public ServiceAnswer getDefenseByMonth(Integer month) {
        if (month < 1 || month > 12) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.MONTH_NUMBER_NOT_VALID).data("Month must be a number between 1 and 12").build();
        }

        List<Defense> defenses = defenseRepository.findAll();
        LocalDateTime now = LocalDateTime.now();

        defenses = defenses
                .stream()
                .filter(defense -> defense.getDate().getMonthValue() == month && defense.getDate().getYear() == now.getYear())
                .sorted((o1, o2) -> {
                    if (o1.getStartTime().equals(o2.getEndTime()) || o1.getStartTime().isAfter(o2.getEndTime())) {
                        return 1;
                    }
                    return -1;
                })
                .toList();

        PlaceReservedByMonthVO res = new PlaceReservedByMonthVO(defenses, month);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(res).build();
    }

    @Override
    public ServiceAnswer registerDefense(DefenseDTO newDefense) {
        if (newDefense.getDate() == null) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.INVALID_DATE).data("The date is mandatory").build();
        }

        if (newDefense.getStartTime() == null || newDefense.getEndTime() == null) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.INVALID_HOUR).data("Start time and end time is mandatory").build();
        }

        LocalTime startTime = newDefense.getStartTime();
        LocalTime endTime = newDefense.getEndTime();
        LocalDate date = newDefense.getDate();
        LocalDate now = LocalDate.now();
        Optional<Project> project = projectRepository.findById(newDefense.getProjectId());
        Optional<PlaceToDefense> place = placeToDefenseRepository.findById(newDefense.getPlaceId());

        if (startTime.isAfter(endTime)) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.INVALID_HOUR).data("The start hour must be before the end hour").build();
        }
        if (project.isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.PROJECT_ID_DOES_NOT_EXIST).data("").build();
        }
        if (place.isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_PLACE_DOES_NOT_EXIST).data("").build();
        }
        if (date.isBefore(now)) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.INVALID_DATE).data("The date must be after the current day").build();
        }

        if (project.get().getDefense() != null) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.DEFENSE_ERROR).data("The project has a defense").build();
        }
        List<Defense> defenses = defenseRepository.findAll()
                .stream()
                .filter(defense -> defense.getDate().getYear() == date.getYear() && defense.getDate().getMonth() == date.getMonth() && defense.getDate().getDayOfMonth() == date.getDayOfMonth())
                .toList();

        List<Defense> blocks = new ArrayList<>();
        for (Defense defense : defenses) {
            if (endTime.isAfter(defense.getStartTime()) && startTime.isBefore(defense.getEndTime())) {
                blocks.add(defense);
            }
        }

        if (!blocks.isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.INVALID_DATE).data("This date is already registered").build();
        }

        Defense defense = new Defense(place.get(), project.get(), date, startTime, endTime, newDefense.getSubstituteName());
        defenseRepository.save(defense);

        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data("The defense has been registered").build();
    }

    @Override
    public ServiceAnswer removeDefense(Long projectId) {
        Optional<Project> optionalProject = projectRepository.findById(projectId);
        if (optionalProject.isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.PROJECT_ID_DOES_NOT_EXIST).data("").build();
        }
        Project project = optionalProject.get();
        if (project.getDefense() == null) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.DEFENSE_ERROR).data("Project does not have a defense").build();
        }
        if (project.getTotalDefensePoints() != null) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.DEFENSE_ERROR).data("Project has been defended").build();
        }
        defenseRepository.deleteById(project.getDefense().getId());
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data("Defense has been deleted").build();
    }

}


