package backend.siptis.service.userData.userPersonalInformation;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.userData.UserCareer;
import backend.siptis.model.entity.userData.UserInformation;
import backend.siptis.model.pjo.dto.StudentInformationDTO;
import backend.siptis.service.userData.checkUserInformation.CheckUserInformation;
import backend.siptis.service.userData.checkUserInformation.SearchUserInformation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PersonalInformationImpl implements PersonalInformation{

    @Autowired
    private final CheckUserInformation checkUserInformation;
    @Autowired
    private final SearchUserInformation searchUserInformation;

    @Override
    public ServiceAnswer getPersonalInformationById(Long id) {
        if(!checkUserInformation.existsById(id)){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_DOES_NOT_EXIST)
                    .data("No existe un usuario con el id solicitado").build();
        }

        SiptisUser user = searchUserInformation.findById(id);

        StudentInformationDTO dto = convertToPersonalInformation(user);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(dto).build();

    }

    private StudentInformationDTO convertToPersonalInformation(SiptisUser user){

        StudentInformationDTO student = new StudentInformationDTO();
        if(user != null){

            student.setEmail(user.getEmail());
            UserInformation information = user.getUserInformation();;
            if(information != null){
                student.setNames(information.getNames());
                student.setLastnames(information.getLastnames());
                student.setCelNumber(information.getCelNumber());
                student.setCi(information.getCi());
                student.setBirthDate(information.getBirthDate());
                student.setCodSIS(information.getCodSIS());
                Set<UserCareer> career = user.getCareer();

                for (UserCareer userCareer: career) {
                    student.setCareer(userCareer.getName());
                    student.setCareerId(userCareer.getId());
                }
            }


        }


        return student;
    }


}
