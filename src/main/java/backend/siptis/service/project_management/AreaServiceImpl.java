package backend.siptis.service.project_management;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.project_management.Area;
import backend.siptis.model.pjo.dto.general_information.user_area.CreateAreaDTO;
import backend.siptis.model.repository.project_management.AreaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AreaServiceImpl implements AreaService {

    private final AreaRepository areaRepository;

    @Override
    public ServiceAnswer getAllAreas() {
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK)
                .data(areaRepository.findAll()).build();
    }

    @Override
    public ServiceAnswer createArea(CreateAreaDTO dto) {
        ServiceAnswer answer = validateCreateArea(dto.getName());
        if (answer != null)
            return answer;
        Area area = new Area();
        area.setName(dto.getName().toUpperCase());
        areaRepository.save(area);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.AREA_CREATED).data(area).build();
    }

    private ServiceAnswer validateCreateArea(String name) {
        if (name == null || name.length() < 2)
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.INVALID_AREA_NAME).build();
        if (areaRepository.existsAreaByName(name.toUpperCase()))
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.AREA_ALREADY_EXIST).data(null).build();
        return null;
    }

    private ServiceAnswer validateDeleteArea(Long id) {
        Optional<Area> areaOptional = areaRepository.findById(id.intValue());
        if (areaOptional.isEmpty())
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.AREA_NOT_FOUND).data(null).build();
        Area area = areaOptional.get();
        if (!area.getProjects().isEmpty())
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.CANNOT_DELETE_AREA).data(null).build();
        return null;
    }

    @Override
    public ServiceAnswer deleteArea(Long id) {
        ServiceAnswer answer = validateDeleteArea(id);
        if (answer != null)
            return answer;
        areaRepository.deleteById(id.intValue());
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.AREA_DELETED).data(null).build();
    }

}
