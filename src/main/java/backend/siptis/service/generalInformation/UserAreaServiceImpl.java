package backend.siptis.service.generalInformation;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.userData.UserArea;
import backend.siptis.model.pjo.dto.generalInformation.userArea.CreateUserAreaDTO;
import backend.siptis.model.repository.general.StadisticsRepository;
import backend.siptis.model.repository.general.UserAreaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public ServiceAnswer createUserArea(CreateUserAreaDTO dto) {
        ServiceAnswer answer = validateCreateUserArea(dto.getName());
        if (answer != null){
            return answer;
        }
        UserArea userArea = new UserArea();
        userArea.setName(dto.getName());
        userAreaRepository.save(userArea);
        return ServiceAnswer.builder()
                .serviceMessage(ServiceMessage.OK)
                .data("El area fue creada exitosamente.").build();
    }

    private ServiceAnswer validateCreateUserArea(String name){
        if(name == null || name.length()<2){
            return ServiceAnswer.builder()
                    .serviceMessage(ServiceMessage.ERROR)
                    .data("Ingrese un nombre valido.").build();
        }

        if(userAreaRepository.existsUserAreaByName(name)){
            return ServiceAnswer.builder()
                    .serviceMessage(ServiceMessage.ERROR)
                    .data("El area ya se encuentra registrada.").build();
        }
        return null;
    }

    private ServiceAnswer validateDeleteUserArea(Long id){

        if(!userAreaRepository.existsUserAreaById(id)){
            return ServiceAnswer.builder()
                    .serviceMessage(ServiceMessage.ERROR)
                    .data("No pudimos encontrar el area solicitada.").build();
        }
        UserArea area = userAreaRepository.findById(id.intValue()).get();
        if(area.getSiptisUsers().size() > 0){
            return ServiceAnswer.builder()
                    .serviceMessage(ServiceMessage.ERROR)
                    .data("No se puede eliminar el area, existen usuarios asignados.").build();
        }
        return null;

    }

    @Override
    public ServiceAnswer deleteUserArea(Long id) {
        ServiceAnswer answer = validateDeleteUserArea(id);
        if (answer != null){
            return answer;
        }
        userAreaRepository.deleteById(id.intValue());

        return ServiceAnswer.builder()
                .serviceMessage(ServiceMessage.USER_AREA_DELETED)
                .data("El area fue eliminada correctamente.").build();
    }

    @Override
    public UserArea getUserAreaById(int id) {
        Optional<UserArea> area = userAreaRepository.findById(id);
        return area.get();

    }

    @Override
    public boolean userAreaExistById(int id) {
        return userAreaRepository.existsById(id);
    }
}
