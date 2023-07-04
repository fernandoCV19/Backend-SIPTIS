package backend.siptis.service.userData.userPersonalInformation;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.userData.UserCareer;
import backend.siptis.model.entity.userData.UserInformation;
import backend.siptis.model.pjo.dto.EditStudentInformationDTO;
import backend.siptis.model.pjo.dto.EditTeacherInformationDTO;
import backend.siptis.model.pjo.dto.usersInformationDTO.StudentInformationDTO;
import backend.siptis.model.repository.userData.SiptisUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AdminEditInformationImpl implements AdminEditInformation{

    private final SiptisUserRepository userRepository;
    @Override
    public ServiceAnswer editStudentInformation(Long userID, EditStudentInformationDTO editDTO) {



        String message = "La información fue modificada exitosamente.";
        if(! userRepository.existsById(userID)){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_DOES_NOT_EXIST)
                    .data("No existe un usuario registrado con el id solicitado").build();
        }
        try{
            Optional<SiptisUser> user = userRepository.findById(userID);

            SiptisUser siptisUser = user.get();

            siptisUser.setEmail(editDTO.getEmail());
            UserInformation userInformation = siptisUser.getUserInformation();
            userInformation.setNames(editDTO.getNames());
            userInformation.setLastnames(editDTO.getLastnames());
            userInformation.setCelNumber(editDTO.getCelNumber());
            userInformation.setCi(editDTO.getCi());
            userInformation.setBirthDate(editDTO.getBirthDate());
            userInformation.setCodSIS(editDTO.getCodSIS());

            SiptisUser user1 = userRepository.save(siptisUser);
            StudentInformationDTO informationDTO = convertToStudentInformation(user1);
        }catch (Exception e){
            message = "Ocurrio un error al intentar modificar la cuenta seleccionada.";
        }
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(message).build();
    }

    @Override
    public ServiceAnswer editTeacherInformation(Long userID, EditTeacherInformationDTO editDTO) {
        if(! userRepository.existsById(userID)){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_DOES_NOT_EXIST)
                    .data("No existe un usuario registrado con el id solicitado").build();
        }
        Optional<SiptisUser> user = userRepository.findById(userID);

        SiptisUser siptisUser = user.get();

        siptisUser.setEmail(editDTO.getEmail());
        UserInformation userInformation = siptisUser.getUserInformation();
        userInformation.setNames(editDTO.getNames());
        userInformation.setLastnames(editDTO.getLastnames());
        userInformation.setCelNumber(editDTO.getCelNumber());
        userInformation.setCi(editDTO.getCi());
        userInformation.setBirthDate(editDTO.getBirthDate());
        userInformation.setCodSIS(editDTO.getCodSIS());

        SiptisUser user1 = userRepository.save(siptisUser);
        StudentInformationDTO informationDTO = convertToStudentInformation(user1);

        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(informationDTO).build();

    }


    private StudentInformationDTO convertToStudentInformation(SiptisUser user) {

        StudentInformationDTO student = new StudentInformationDTO();
        if (user != null) {

            student.setEmail(user.getEmail());
            UserInformation information = user.getUserInformation();
            if (information != null) {
                student.setNames(information.getNames());
                student.setLastnames(information.getLastnames());
                student.setCelNumber(information.getCelNumber());
                student.setCi(information.getCi());
                student.setBirthDate(information.getBirthDate());
                student.setCodSIS(information.getCodSIS());
                Set<UserCareer> career = user.getCareer();

                for (UserCareer userCareer : career) {
                    student.setCareer(userCareer.getName());
                    student.setCareerId(userCareer.getId());
                }
            }


        }
        return student;
    }

}
