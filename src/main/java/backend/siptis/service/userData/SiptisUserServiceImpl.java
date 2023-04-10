package backend.siptis.service.userData;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.entity.userData.UserCareer;
import backend.siptis.model.entity.userData.UserInformation;
import backend.siptis.model.pjo.dto.EditStudentInformationDTO;
import backend.siptis.model.pjo.dto.StudentInformationDTO;
import backend.siptis.model.pjo.dto.UserGeneralInformationDTO;
import backend.siptis.model.repository.general.RoleRepository;
import backend.siptis.model.repository.userData.SiptisUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SiptisUserServiceImpl implements SiptisUserService {

    private final SiptisUserRepository usuarioCommonRepository;

    @Autowired
    private final UserInformationService userInformationService;

    @Override
    public ServiceAnswer findAll() {
        List<SiptisUser> userDBList = usuarioCommonRepository.findAll();
        List<UserGeneralInformationDTO> userList = new ArrayList<>();
        for (SiptisUser user: userDBList) {
            UserGeneralInformationDTO userDTO = convertToDTO(user);

            userList.add(userDTO);
            
        }
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(userList).build();
    }

    @Override
    public ServiceAnswer obtenerProyectosSupervisorParaMenuPrincipalPorIdUsuario(Integer id) {
        Optional<SiptisUser> usuarioOptional = usuarioCommonRepository.findById(id);
        if(usuarioOptional.isEmpty()){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_FOUND).data(null).build();
        }

        return null;
    }

    @Override
    public ServiceAnswer editStudentInformation(Long id,EditStudentInformationDTO editDTO) {
        Optional<SiptisUser> user = usuarioCommonRepository.findById(id);

        SiptisUser siptisUser = user.get();

        siptisUser.setEmail(editDTO.getEmail());
        UserInformation userInformation = siptisUser.getUserInformation();
        userInformation.setNames(editDTO.getNames());
        userInformation.setLastnames(editDTO.getLastnames());
        userInformation.setCelNumber(editDTO.getCelNumber());
        userInformation.setCi(editDTO.getCi());
        userInformation.setBirthDate(editDTO.getBirthDate());
        userInformation.setCodSIS(editDTO.getCodSIS());


        SiptisUser user1 = usuarioCommonRepository.save(siptisUser);

        StudentInformationDTO informationDTO = convertToStudentInformation(user1);

        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(informationDTO).build();
    }

    private UserGeneralInformationDTO convertToDTO(SiptisUser user){
        UserGeneralInformationDTO userDTO = new UserGeneralInformationDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }

    private StudentInformationDTO convertToStudentInformation(SiptisUser user){

        StudentInformationDTO student = new StudentInformationDTO();
        if(user != null){

            student.setEmail(user.getEmail());
            UserInformation information = user.getUserInformation();
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
