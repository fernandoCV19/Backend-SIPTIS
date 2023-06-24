package backend.siptis.service.userData.userPersonalInformation;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.userData.UserArea;
import backend.siptis.model.entity.userData.UserCareer;
import backend.siptis.model.entity.userData.UserInformation;
import backend.siptis.model.pjo.dto.records.PersonalInformationDTO;
import backend.siptis.service.userData.SiptisUserService;
import backend.siptis.service.userData.checkUserInformation.CheckUserInformation;
import backend.siptis.service.userData.checkUserInformation.SearchUserInformation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class GetPersonalInformationImpl implements GetPersonalInformationService{

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
    public ServiceAnswer getStudentCareerById(Long id) {
        if(!checkUserInformation.existsById(id)){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_DOES_NOT_EXIST)
                    .data("No existe un usuario con el id solicitado").build();
        }
        SiptisUser user = searchUserInformation.findById(id);

        Set<UserCareer> career = user.getCareer();
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(career).build();

    }

    @Override
    public ServiceAnswer getTeacherAreasById(Long id) {
        if(!checkUserInformation.existsById(id)){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_DOES_NOT_EXIST)
                    .data("No existe un usuario con el id solicitado").build();
        }
        SiptisUser user = searchUserInformation.findById(id);

        Set<UserArea> areas = user.getAreas();
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(areas).build();

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
