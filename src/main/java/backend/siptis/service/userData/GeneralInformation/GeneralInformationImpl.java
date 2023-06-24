package backend.siptis.service.userData.GeneralInformation;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.userData.UserArea;
import backend.siptis.model.entity.userData.UserCareer;
import backend.siptis.model.repository.general.UserAreaRepository;
import backend.siptis.model.repository.general.UserCareerRepository;
import backend.siptis.model.repository.userData.SiptisUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GeneralInformationImpl implements GeneralInformationService{

    @Autowired
    private final UserAreaRepository areaRepository;
    @Autowired
    private final UserCareerRepository careerRepository;

    @Override
    public ServiceAnswer getAllCareers() {
        List<UserCareer> careers = careerRepository.findAll();
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(careers).build();

    }

    @Override
    public ServiceAnswer getAllUserAreas() {
        List<UserArea> areas = areaRepository.findAll();
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(areas).build();

    }

    @Override
    public ServiceAnswer getAreaById(int id) {
        Optional<UserArea> area = areaRepository.findById(id);
        UserArea response = area.get();
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(response).build();

    }

    @Override
    public ServiceAnswer getCareerById(int id) {
        Optional<UserCareer> career = careerRepository.findById(id);
        UserCareer response = career.get();
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(response).build();

    }
}
