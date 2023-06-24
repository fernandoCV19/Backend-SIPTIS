package backend.siptis.service.userData.registerUser;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.userData.UserCareer;
import backend.siptis.model.entity.userData.UserInformation;
import backend.siptis.model.pjo.dto.StudentInformationDTO;
import backend.siptis.model.pjo.dto.StudentRegisterDTO;
import backend.siptis.model.pjo.dto.TeacherRegisterDTO;
import backend.siptis.model.repository.general.UserCareerRepository;
import backend.siptis.model.repository.userData.UserInformationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegisterUserInformationImpl implements RegisterUserInformation{

    @Autowired
    private final UserInformationRepository userInformationRepository;

    @Autowired
    private final UserCareerRepository userCareerRepository;


    @Override
    public ServiceAnswer registerStudent(
            StudentRegisterDTO estudianteDTO, SiptisUser siptisUser) {

        UserInformation userInformation = new UserInformation();
        userInformation.setSiptisUser(siptisUser);
        userInformation.setLastnames(estudianteDTO.getLastnames());
        userInformation.setNames(estudianteDTO.getNames());
        userInformation.setCi(estudianteDTO.getCi());
        userInformation.setCodSIS(estudianteDTO.getCodSIS());
        userInformation.setBirthDate(estudianteDTO.getBirthDate());
        userInformation.setCelNumber(estudianteDTO.getCelNumber());

        UserCareer userCareer = userCareerRepository.findById(estudianteDTO.getCareer())
                .orElseThrow(() -> new UsernameNotFoundException(
                        "La carrera solicitada no existe"
                ));

        siptisUser.addCareer(userCareer);

        UserInformation information =  userInformationRepository.save(userInformation);

        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK)
                .data(information).build();
    }

    @Override
    public ServiceAnswer registerTeacherInformation(TeacherRegisterDTO teacherDTO, SiptisUser siptisUser) {

        UserInformation userInformation = new UserInformation();
        userInformation.setSiptisUser(siptisUser);
        userInformation.setLastnames(teacherDTO.getLastnames());
        userInformation.setNames(teacherDTO.getNames());
        userInformation.setCi(teacherDTO.getCi());
        userInformation.setCodSIS(teacherDTO.getCodSIS());
        userInformation.setBirthDate(teacherDTO.getBirthDate());
        userInformation.setCelNumber(teacherDTO.getCelNumber());
        UserInformation information = userInformationRepository.save(userInformation);
        //return studentInformationDTO;
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK)
                .data(information).build();

    }

}
