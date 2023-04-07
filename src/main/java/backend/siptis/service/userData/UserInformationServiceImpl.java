package backend.siptis.service.userData;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.userData.UserCareer;
import backend.siptis.model.repository.userData.UserInformationRepository;
import backend.siptis.model.entity.userData.UserInformation;
import backend.siptis.model.pjo.dto.StudentInformationDTO;
import backend.siptis.model.pjo.dto.StudentRegisterDTO;
import backend.siptis.model.repository.general.UserCareerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserInformationServiceImpl implements UserInformationService {

    @Autowired
    private final UserInformationRepository userInformationRepository;

    @Autowired
    private final UserCareerRepository userCareerRepository;

    @Override
    public ServiceAnswer findAll() {
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_FOUND).data(null).build();
        //return null;
    }

    public boolean existByCodSIS(String codSIS){
        return userInformationRepository.existsByCodSIS(codSIS);
    }

    public boolean existByCi(String ci){
        return userInformationRepository.existsByCi(ci);
    }

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

        userInformationRepository.save(userInformation);

        StudentInformationDTO studentInformationDTO = new StudentInformationDTO();

        studentInformationDTO.setLastnames(estudianteDTO.getLastnames());
        studentInformationDTO.setNames(estudianteDTO.getNames());
        studentInformationDTO.setCi(estudianteDTO.getCi());
        studentInformationDTO.setCodSIS(estudianteDTO.getCodSIS());
        studentInformationDTO.setFechaNac(estudianteDTO.getBirthDate());
        studentInformationDTO.setCelNumber(estudianteDTO.getCelNumber());

        //return studentInformationDTO;
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(studentInformationDTO).build();
    }
}
