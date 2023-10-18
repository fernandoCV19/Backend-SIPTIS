package backend.siptis.service.projectManagement;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.projectManagement.SubArea;
import backend.siptis.model.pjo.dto.generalInformation.userArea.CreateAreaDTO;
import backend.siptis.model.repository.projectManagement.SubAreaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubAreaServiceImpl implements SubAreaService{
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
        area.setName(dto.getName());
        subAreaRepository.save(area);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK)
                .data("El sub area fue creada exitosamente.").build();
    }

    private ServiceAnswer validateCreateArea(String name){
        if(subAreaRepository.existsSubAreaByName(name))
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ERROR)
                    .data("El sub area ya se encuentra registrada.").build();
        return null;
    }

    private ServiceAnswer validateDeleteArea(Long id){

        if(!subAreaRepository.existsSubAreaById(id)){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ERROR)
                    .data("No pudimos encontrar el sub area solicitada.").build();
        }
        SubArea area = subAreaRepository.findById(id.intValue()).get();
        if(area.getProjects().size() > 0){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ERROR)
                    .data("No se puede eliminar el sub area, existen proyectos asignados.").build();
        }
        return null;

    }

    @Override
    public ServiceAnswer deleteSubArea(Long id) {
        ServiceAnswer answer = validateDeleteArea(id);
        if (answer != null){
            return answer;
        }
        subAreaRepository.deleteById(id.intValue());

        return ServiceAnswer.builder()
                .serviceMessage(ServiceMessage.AREA_DELETED)
                .data("El sub area fue eliminada correctamente.").build();
    }
}
