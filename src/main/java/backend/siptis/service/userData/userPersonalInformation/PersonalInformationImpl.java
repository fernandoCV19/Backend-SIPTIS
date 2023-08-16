package backend.siptis.service.userData.userPersonalInformation;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.userData.UserInformation;
import backend.siptis.model.pjo.dto.AdminEditUserPersonalInformationDTO;
import backend.siptis.model.pjo.dto.UserEditPersonalInformationDTO;
import backend.siptis.model.pjo.dto.UserSelectedAreasDTO;
import backend.siptis.model.pjo.dto.notifications.PersonalInformationDTO;
import backend.siptis.service.userData.SiptisUserService;
import backend.siptis.service.userData.checkUserInformation.CheckUserInformation;
import backend.siptis.service.userData.checkUserInformation.SearchUserInformation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PersonalInformationImpl implements PersonalInformation{

    @Autowired
    private final CheckUserInformation checkUserInformation;
    @Autowired
    private final SearchUserInformation searchUserInformation;
    @Autowired
    private final SiptisUserService siptisUserService;

    @Override
    public ServiceAnswer getPersonalInformationById(Long id) {
        if(!checkUserInformation.existsById(id)){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_DOES_NOT_EXIST)
                    .data("No existe un usuario con el id solicitado").build();
        }

        SiptisUser user = searchUserInformation.findById(id);

        PersonalInformationDTO dto = convertToPersonalInformation(user);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(dto).build();

    }

    @Override
    public ServiceAnswer UserEditPersonalInformationById(Long id, UserEditPersonalInformationDTO dto) {
        String message = "La información de su cuenta fue modificada exitosamente.";
        if(! checkUserInformation.existsById(id)){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_DOES_NOT_EXIST)
                    .data("No existe un usuario registrado con el id solicitado").build();
        }
        try {
            SiptisUser siptisUser = searchUserInformation.findById(id);

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
    public ServiceAnswer UserRegisterAreas(Long id, UserSelectedAreasDTO dto) {


        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(dto).build();

    }

    @Override
    public ServiceAnswer AdminEditPersonalInformationById(Long id, AdminEditUserPersonalInformationDTO dto) {
        String message = "La información fue modificada exitosamente.";
        if(! checkUserInformation.existsById(id)){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_DOES_NOT_EXIST)
                    .data("No existe un usuario registrado con el id solicitado").build();
        }
        try {
            SiptisUser siptisUser = searchUserInformation.findById(id);

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

    @Override
    public ServiceAnswer getStudentCareerById(Long id) {
        return null;
    }

    @Override
    public ServiceAnswer getTeacherAreasById(Long id) {
        return null;
    }

    private PersonalInformationDTO convertToPersonalInformation(SiptisUser user){

        PersonalInformationDTO personalInfo = new PersonalInformationDTO();
        if(user != null){

            personalInfo.setEmail(user.getEmail());
            UserInformation information = user.getUserInformation();;
            if(information != null){
                personalInfo.setNames(information.getNames());
                personalInfo.setLastnames(information.getLastnames());
                personalInfo.setCelNumber(information.getCelNumber());
                personalInfo.setCi(information.getCi());
                personalInfo.setBirthDate(information.getBirthDate());
                personalInfo.setCodSIS(information.getCodSIS());
            }
        }
        return personalInfo;
    }


}
