package backend.siptis.service.userData;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.repository.userData.UserCareerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCareerServiceImpl implements UserCareerService {

    private final UserCareerRepository userCareerRepository;

    @Override
    public ServiceAnswer getCareerByName(String name) {
        if (!userCareerRepository.existsByName(name)) {
            return createResponse(ServiceMessage.NOT_FOUND, null);
        }
        return createResponse(ServiceMessage.OK, userCareerRepository.findUserCareerByName(name));
    }

    private ServiceAnswer createResponse(ServiceMessage serviceMessage, Object data) {
        return ServiceAnswer.builder().serviceMessage(
                serviceMessage).data(data
        ).build();
    }
}
