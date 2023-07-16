package backend.siptis.service.generalInformation;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.repository.general.StadisticsRepository;
import backend.siptis.model.repository.general.UserAreaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAreaServiceImpl implements UserAreaService{


    private final UserAreaRepository userAreaRepository;

    @Override
    public ServiceAnswer getAllUserAreas() {

        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK)
                .data(userAreaRepository.findAll()).build();

    }

    @Override
    public ServiceAnswer getUserAreaById(int id) {
        return null;
    }
}
