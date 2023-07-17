package backend.siptis.service.generalInformation;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.userData.UserCareer;
import backend.siptis.model.repository.general.UserCareerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserCareerServiceImpl implements UserCareerService{

    private final UserCareerRepository userCareerRepository;
    @Override
    public ServiceAnswer getAllCareers() {

        List<UserCareer> careers = userCareerRepository.findAll();
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK)
                .data(careers).build();
    }

    @Override
    public ServiceAnswer getCareerById(int id) {
        Optional<UserCareer> career = userCareerRepository.findById(id);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK)
                .data(career.get()).build();
    }
}
