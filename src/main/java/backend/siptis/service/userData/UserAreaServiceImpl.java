package backend.siptis.service.userData;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.userData.UserArea;
import backend.siptis.model.pjo.dto.generalInformation.userArea.CreateAreaDTO;
import backend.siptis.model.repository.userData.UserAreaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserAreaServiceImpl implements UserAreaService {


    private final UserAreaRepository userAreaRepository;

    @Override
    public ServiceAnswer getAllUserAreas() {
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK)
                .data(userAreaRepository.findAll()).build();
    }

    @Override
    public ServiceAnswer createUserArea(CreateAreaDTO dto) {
        ServiceAnswer answer = validateCreateUserArea(dto.getName());
        if (answer != null) {
            return answer;
        }
        UserArea userArea = new UserArea();
        userArea.setName(dto.getName());
        userAreaRepository.save(userArea);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.AREA_CREATED).data(userArea).build();
    }

    private ServiceAnswer validateCreateUserArea(String name) {
        if (name == null || name.length() < 2)
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.INVALID_AREA_NAME).build();
        if (userAreaRepository.existsUserAreaByName(name))
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.AREA_ALREADY_EXIST).build();
        return null;
    }

    private ServiceAnswer validateDeleteUserArea(Long id) {
        Optional<UserArea> areaOptional = userAreaRepository.findById(id.intValue());
        if (areaOptional.isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.AREA_NOT_FOUND).build();
        }
        UserArea area = areaOptional.get();
        if (!area.getSiptisUsers().isEmpty()) {
            return ServiceAnswer.builder()
                    .serviceMessage(ServiceMessage.CANNOT_DELETE_AREA).build();
        }
        return null;

    }

    @Override
    public ServiceAnswer deleteUserArea(Long id) {
        ServiceAnswer answer = validateDeleteUserArea(id);
        if (answer != null)
            return answer;
        userAreaRepository.deleteById(id.intValue());
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.AREA_DELETED).build();
    }

    @Override
    public UserArea getUserAreaById(int id) {
        Optional<UserArea> area = userAreaRepository.findById(id);
        if (area.isEmpty())
            return null;
        return area.get();
    }

    @Override
    public boolean userAreaExistById(int id) {
        return userAreaRepository.existsById(id);
    }
}
