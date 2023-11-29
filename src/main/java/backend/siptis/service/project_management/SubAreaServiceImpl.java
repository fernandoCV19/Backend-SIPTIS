package backend.siptis.service.project_management;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.project_management.SubArea;
import backend.siptis.model.pjo.dto.general_information.user_area.CreateAreaDTO;
import backend.siptis.model.repository.project_management.SubAreaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class SubAreaServiceImpl implements SubAreaService {
    private final SubAreaRepository subAreaRepository;

    @Override
    public ServiceAnswer getAllSubAreas() {
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK)
                .data(subAreaRepository.findAll()).build();
    }

    @Override
    public ServiceAnswer createSubArea(CreateAreaDTO dto) {
        ServiceAnswer answer = validateCreateArea(dto.getName());
        if (answer != null)
            return answer;
        SubArea area = new SubArea();
        area.setName(dto.getName().toUpperCase());
        subAreaRepository.save(area);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.SUB_AREA_CREATED)
                .data(area).build();
    }

    private ServiceAnswer validateCreateArea(String name) {
        if (name == null || name.length() < 2)
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.INVALID_SUB_AREA_NAME).build();
        if (subAreaRepository.existsSubAreaByName(name.toUpperCase()    ))
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.SUB_AREA_ALREADY_EXIST).build();
        return null;
    }

    private ServiceAnswer validateDeleteArea(Long id) {
        Optional<SubArea> areaOptional = subAreaRepository.findById(id);
        if (areaOptional.isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.SUB_AREA_NOT_FOUND).build();
        }
        SubArea area = areaOptional.get();
        if (!area.getProjects().isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.CANNOT_DELETE_SUB_AREA).build();
        }
        return null;

    }

    @Override
    public ServiceAnswer deleteSubArea(Long id) {
        ServiceAnswer answer = validateDeleteArea(id);
        if (answer != null)
            return answer;
        subAreaRepository.deleteById(id.intValue());

        return ServiceAnswer.builder().serviceMessage(ServiceMessage.SUB_AREA_DELETED).build();
    }
}
