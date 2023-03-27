package backend.siptis.service.datosUsuario;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.MensajeServicio;
import backend.siptis.commons.RespuestaServicio;
import backend.siptis.model.repository.datosUsuario.InformacionUsuarioRepository;
import backend.siptis.model.entity.datosUsuario.UserInformation;
import backend.siptis.model.pjo.dto.StudentInformationDTO;
import backend.siptis.model.pjo.dto.StudentRegisterDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserInformationServiceImpl implements UserInformationService {

    @Autowired
    private final InformacionUsuarioRepository informacionUsuarioRepository;

    @Override
    public RespuestaServicio findAll() {
        return RespuestaServicio.builder().mensajeServicio(MensajeServicio.NOT_FOUND).data(null).build();
        //return null;
    }

    @Override
    public RespuestaServicio registerStudent(
            StudentRegisterDTO estudianteDTO, SiptisUser siptisUser) {

        UserInformation userInformation = new UserInformation();
        userInformation.setSiptisUser(siptisUser);
        userInformation.setLastnames(estudianteDTO.getLastnames());
        userInformation.setNames(estudianteDTO.getNames());
        userInformation.setCi(estudianteDTO.getCi());
        userInformation.setCodSIS(estudianteDTO.getCodSIS());
        userInformation.setBirthDate(estudianteDTO.getBirthDate());
        userInformation.setCelNumber(estudianteDTO.getCelNumber());

        informacionUsuarioRepository.save(userInformation);

        StudentInformationDTO studentInformationDTO = new StudentInformationDTO();

        studentInformationDTO.setLastnames(estudianteDTO.getLastnames());
        studentInformationDTO.setNames(estudianteDTO.getNames());
        studentInformationDTO.setCi(estudianteDTO.getCi());
        studentInformationDTO.setCodSIS(estudianteDTO.getCodSIS());
        studentInformationDTO.setFechaNac(estudianteDTO.getBirthDate());
        studentInformationDTO.setCelNumber(estudianteDTO.getCelNumber());

        //return studentInformationDTO;
        return RespuestaServicio.builder().mensajeServicio(MensajeServicio.OK).data(studentInformationDTO).build();
    }
}
