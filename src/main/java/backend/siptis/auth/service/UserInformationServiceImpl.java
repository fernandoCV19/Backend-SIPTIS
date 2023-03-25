package backend.siptis.auth.service;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.auth.repository.InformacionUsuarioRepository;
import backend.siptis.model.entity.datosUsuario.UserInformation;
import backend.siptis.model.pjo.dto.InformacionEstudianteDTO;
import backend.siptis.model.pjo.dto.RegistrarEstudianteDTO;
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
    public List<SiptisUser> findAll() {
        return null;
    }

    @Override
    public InformacionEstudianteDTO registerStudent(
            RegistrarEstudianteDTO estudianteDTO, SiptisUser siptisUser) {

        UserInformation userInformation = new UserInformation();
        userInformation.setSiptisUser(siptisUser);
        userInformation.setLastnames(estudianteDTO.getApellidos());
        userInformation.setNames(estudianteDTO.getNombres());
        userInformation.setCi(estudianteDTO.getCi());
        userInformation.setCodSIS(estudianteDTO.getCodSIS());
        //userInformation.setFechaNac(estudianteDTO.getFechaNac());
        userInformation.setCelNumber(estudianteDTO.getCelular());

        informacionUsuarioRepository.save(userInformation);

        InformacionEstudianteDTO informacionEstudianteDTO = new InformacionEstudianteDTO();

        informacionEstudianteDTO.setApellidos(estudianteDTO.getApellidos());
        informacionEstudianteDTO.setNombres(estudianteDTO.getNombres());
        informacionEstudianteDTO.setCi(estudianteDTO.getCi());
        informacionEstudianteDTO.setCodSIS(estudianteDTO.getCodSIS());
        informacionEstudianteDTO.setFechaNac(estudianteDTO.getFechaNac());
        informacionEstudianteDTO.setCelular(estudianteDTO.getCelular());

        return informacionEstudianteDTO;
    }
}
