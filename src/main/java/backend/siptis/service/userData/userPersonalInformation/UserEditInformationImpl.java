package backend.siptis.service.userData.userPersonalInformation;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.userData.UserCareer;
import backend.siptis.model.entity.userData.UserInformation;
import backend.siptis.model.pjo.dto.StudentEditPersonalInfoDTO;
import backend.siptis.model.pjo.dto.StudentInformationDTO;
import backend.siptis.model.pjo.dto.TeacherEditPersonalInfoDTO;
import backend.siptis.model.repository.userData.SiptisUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserEditInformationImpl implements UserEditInformation{

    private final SiptisUserRepository usuarioCommonRepository;

    @Override
    public ServiceAnswer teacherEditPersonalInfo(Long id, TeacherEditPersonalInfoDTO dto) {
        Optional<SiptisUser> user = usuarioCommonRepository.findById(id);
        SiptisUser siptisUser = user.get();

        siptisUser.setEmail(dto.getEmail());
        UserInformation userInformation = siptisUser.getUserInformation();

        userInformation.setCelNumber(dto.getCelNumber());
        userInformation.setCi(dto.getCi());
        userInformation.setBirthDate(dto.getBirthDate());

        SiptisUser user1 = usuarioCommonRepository.save(siptisUser);

        StudentInformationDTO informationDTO = convertToStudentInformation(user1);

        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(informationDTO).build();

    }

    private StudentInformationDTO convertToStudentInformation(SiptisUser user) {

        StudentInformationDTO student = new StudentInformationDTO();
        if (user != null) {

            student.setEmail(user.getEmail());
            UserInformation information = user.getUserInformation();
            ;
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
