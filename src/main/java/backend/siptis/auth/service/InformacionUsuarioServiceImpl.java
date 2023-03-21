package backend.siptis.auth.service;

import backend.siptis.auth.entity.Usuario;
import backend.siptis.auth.repository.InformacionUsuarioRepository;
import backend.siptis.auth.repository.UsuarioRepository;
import backend.siptis.model.entity.datosUsuario.InformacionUsuario;
import backend.siptis.model.pjo.dto.InformacionEstudianteDTO;
import backend.siptis.model.pjo.dto.RegistrarEstudianteDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InformacionUsuarioServiceImpl implements InformacionUsuarioService{

    @Autowired
    private final InformacionUsuarioRepository informacionUsuarioRepository;

    @Override
    public List<Usuario> findAll() {
        return null;
    }

    @Override
    public InformacionEstudianteDTO registrarEstudiante(
            RegistrarEstudianteDTO estudianteDTO, Usuario usuario) {

        InformacionUsuario informacionUsuario = new InformacionUsuario();
        informacionUsuario.setUsuario(usuario);
        informacionUsuario.setApellidos(estudianteDTO.getApellidos());
        informacionUsuario.setNombres(estudianteDTO.getNombres());
        informacionUsuario.setCi(estudianteDTO.getCi());
        informacionUsuario.setCodSIS(estudianteDTO.getCodSIS());
        informacionUsuario.setFechaNac(estudianteDTO.getFechaNac());
        informacionUsuario.setCelular(estudianteDTO.getCelular());

        informacionUsuarioRepository.save(informacionUsuario);

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
