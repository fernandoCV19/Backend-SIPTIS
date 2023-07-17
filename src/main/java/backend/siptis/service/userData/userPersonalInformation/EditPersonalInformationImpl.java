package backend.siptis.service.userData.userPersonalInformation;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.userData.UserArea;
import backend.siptis.model.entity.userData.UserInformation;
import backend.siptis.model.pjo.dto.AdminEditUserPersonalInformationDTO;
import backend.siptis.model.pjo.dto.UserEditPersonalInformationDTO;
import backend.siptis.model.pjo.dto.UserSelectedAreasDTO;
import backend.siptis.model.repository.userData.UserInformationRepository;
import backend.siptis.service.generalInformation.UserAreaService;
import backend.siptis.service.userData.GeneralInformation.GeneralInformationService;
import backend.siptis.service.userData.SiptisUserService;
import backend.siptis.service.userData.UserInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EditPersonalInformationImpl implements EditPersonalInformationService{

    @Autowired
    private final UserInformationService checkUserInformation;
    @Autowired
    private final GeneralInformationService generalInformationService;

    private final SiptisUserService siptisUserService;
    @Autowired
    private final UserInformationRepository userInformationRepository;

    private final UserAreaService userAreaService;

    @Override
    public ServiceAnswer EditLimitedPersonalInformationById(Long id, UserEditPersonalInformationDTO dto) {
        String message = "La información de su cuenta fue modificada exitosamente.";
        /*if(! checkUserInformation.existsById(id)){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_DOES_NOT_EXIST)
                    .data("No existe un usuario registrado con el id solicitado").build();
        }*/
        try {
            SiptisUser siptisUser = (SiptisUser) siptisUserService.findById(id.intValue()).getData();

            siptisUser.setEmail(dto.getEmail());
            UserInformation userInformation = siptisUser.getUserInformation();

            userInformation.setCelNumber(dto.getCelNumber());
            userInformation.setCi(dto.getCi());
            userInformation.setBirthDate(dto.getBirthDate());

            SiptisUser user1 = siptisUserService.save(siptisUser);
        }catch (Exception e){
            message = "Ocurrio un error al intentar modificar su informacion.";
        }

        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(message).build();

    }

    @Override
    public ServiceAnswer UpdateUserAreas(Long id, UserSelectedAreasDTO dto) {

        String message = "La información de su cuenta fue modificada exitosamente.";
        /*if(! checkUserInformation.existsById(id)){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_DOES_NOT_EXIST)
                    .data("No existe un usuario registrado con el id solicitado").build();
        }*/
        try {
            SiptisUser siptisUser = (SiptisUser) siptisUserService.findById(id.intValue()).getData();
            userInformationRepository.deleteUserAreas(id);
            List<Long> ids = dto.getIds();
            for (Long areaId: ids) {
                ServiceAnswer response =
                        userAreaService.getUserAreaById(areaId.intValue());
                siptisUser.addAreas((UserArea) response.getData());
            }

            SiptisUser user1 = siptisUserService.save(siptisUser);

        }catch (Exception e){
            System.out.println(e);
            message = "Ocurrio un error al intentar modificar su informacion.";
        }

        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(message).build();

    }

    @Override
    public ServiceAnswer DeleteUserAreas(Long id) {
        return null;
    }

    @Override
    public ServiceAnswer UpdateStudentCareer(Long id, Long careerId) {
        return null;
    }

    @Override
    public ServiceAnswer EditFullPersonalInformationById(Long id, AdminEditUserPersonalInformationDTO dto) {
        String message = "La información fue modificada exitosamente.";
        /*if(! checkUserInformation.existsById(id)){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_DOES_NOT_EXIST)
                    .data("No existe un usuario registrado con el id solicitado").build();
        }*/
        try {
            SiptisUser siptisUser = (SiptisUser) siptisUserService.findById(id.intValue()).getData();

            siptisUser.setEmail(dto.getEmail());
            UserInformation userInformation = siptisUser.getUserInformation();

            userInformation.setNames(dto.getNames());
            userInformation.setLastnames(dto.getLastnames());
            userInformation.setCelNumber(dto.getCelNumber());
            userInformation.setCi(dto.getCi());
            userInformation.setCodSIS(dto.getCodSIS());
            userInformation.setBirthDate(dto.getBirthDate());

            SiptisUser user1 = siptisUserService.save(siptisUser);
        }catch (Exception e){
            message = "Ocurrio un error al intentar modificar la informacion.";
        }

        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(message).build();

    }
}
