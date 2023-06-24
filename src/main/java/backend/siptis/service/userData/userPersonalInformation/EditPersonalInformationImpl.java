package backend.siptis.service.userData.userPersonalInformation;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.userData.UserArea;
import backend.siptis.model.entity.userData.UserCareer;
import backend.siptis.model.entity.userData.UserInformation;
import backend.siptis.model.pjo.dto.AdminEditUserPersonalInformationDTO;
import backend.siptis.model.pjo.dto.UserEditPersonalInformationDTO;
import backend.siptis.model.pjo.dto.UserSelectedAreasDTO;
import backend.siptis.model.repository.userData.UserInformationRepository;
import backend.siptis.service.userData.GeneralInformation.GeneralInformationService;
import backend.siptis.service.userData.SiptisUserService;
import backend.siptis.service.userData.checkUserInformation.CheckUserInformation;
import backend.siptis.service.userData.checkUserInformation.SearchUserInformation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EditPersonalInformationImpl implements EditPersonalInformationService{

    @Autowired
    private final CheckUserInformation checkUserInformation;
    @Autowired
    private final SearchUserInformation searchUserInformation;
    @Autowired
    private final GeneralInformationService generalInformationService;
    @Autowired
    private final SiptisUserService siptisUserService;
    @Autowired
    private final UserInformationRepository userInformationRepository;

    @Override
    public ServiceAnswer EditLimitedPersonalInformationById(Long id, UserEditPersonalInformationDTO dto) {
        return null;
    }

    @Override
    public ServiceAnswer UpdateUserAreas(Long id, UserSelectedAreasDTO dto) {

        String message = "La información de su cuenta fue modificada exitosamente.";
        if(! checkUserInformation.existsById(id)){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_DOES_NOT_EXIST)
                    .data("No existe un usuario registrado con el id solicitado").build();
        }
        try {
            SiptisUser siptisUser = searchUserInformation.findById(id);
            userInformationRepository.deleteUserAreas(id);
            List<Long> ids = dto.getIds();
            for (Long areaId: ids) {
                ServiceAnswer response =
                        generalInformationService.getAreaById(areaId.intValue());
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
        return null;
    }
}
