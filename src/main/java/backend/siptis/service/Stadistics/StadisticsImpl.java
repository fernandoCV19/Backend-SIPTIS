package backend.siptis.service.Stadistics;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.repository.general.StadisticsRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StadisticsImpl implements StadisticsService {

    @Autowired
    private final StadisticsRepository stadisticsRepository;

    @Override
    public ServiceAnswer StudentsByCareerStadistics() {

        return ServiceAnswer.builder().serviceMessage(
                ServiceMessage.OK).data(stadisticsRepository.getNumberOfStudentsInCareer()
        ).build();
    }

    @Override
    public ServiceAnswer UserByAreaStadistics() {
        return ServiceAnswer.builder().serviceMessage(
                ServiceMessage.OK).data(stadisticsRepository.getNumberOfUserInArea()
        ).build();
    }

    @Override
    public ServiceAnswer ProyectsByAreaAndCareer(int idCareer) {
        return ServiceAnswer.builder().serviceMessage(
                        ServiceMessage.OK)
                .data(stadisticsRepository.getNumberOfProyectsByAreaAndCareer(idCareer)
                ).build();
    }

    @Override
    public ServiceAnswer getStudentsByYear() {
        return ServiceAnswer.builder().serviceMessage(
                        ServiceMessage.OK)
                .data(stadisticsRepository.getNumberOfStudentsByYear()
                ).build();
    }

    @Override
    public ServiceAnswer getStudentsByYearAndCareer(String career) {
        return ServiceAnswer.builder().serviceMessage(
                        ServiceMessage.OK)
                .data(stadisticsRepository.getNumberOfStudentsByYearAndCareer(career)
                ).build();
    }

    private ServiceAnswer createResponse(ServiceMessage serviceMessage, String errorMessage) {
        return ServiceAnswer.builder().serviceMessage(
                serviceMessage).data(errorMessage
        ).build();
    }
}
