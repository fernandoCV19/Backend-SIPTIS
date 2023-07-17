package backend.siptis.service.generalInformation;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.projectManagement.Area;
import backend.siptis.model.repository.projectManagement.AreaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AreasServiceImpl implements AreasService{

    private final AreaRepository areaRepository;

    @Override
    public ServiceAnswer getAllAreas() {
        List<Area> areas = areaRepository.findAll();

        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK)
                .data(areas).build();
    }

    @Override
    public ServiceAnswer getAreaById(int id) {
        Optional<Area> area = areaRepository.findById(id);

        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK)
                .data(area).build();
    }

    @Override
    public ServiceAnswer getAllProjectAreas() {
        return null;
    }
}
