package backend.siptis.service.projectManagement;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.projectManagement.Area;
import backend.siptis.model.entity.userData.UserArea;
import backend.siptis.model.pjo.dto.generalInformation.userArea.CreateAreaDTO;
import backend.siptis.model.repository.projectManagement.AreaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        area.setName(dto.getName());
        areaRepository.save(area);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.AREA_CREATED).data(area).build();
    }

    private ServiceAnswer validateCreateArea(String name){
        if(name == null || name.length()<2)
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.INVALID_AREA_NAME).build();
        if(areaRepository.existsAreaByName(name))
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.AREA_ALREADY_EXIST).data(null).build();
        return null;
    }

    private ServiceAnswer validateDeleteArea(Long id){
        if(!areaRepository.existsAreaById(id))
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.AREA_NOT_FOUND).data(null).build();
        Area area = areaRepository.findById(id.intValue()).get();
        if(area.getProjects().size() > 0)
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
