package BackendSIPTIS.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import BackendSIPTIS.commons.MensajeServicio;
import BackendSIPTIS.commons.RespuestaServicio;
import BackendSIPTIS.model.entity.gestionProyecto.Presentacion;
import BackendSIPTIS.model.entity.gestionProyecto.ProyectoGrado;
import BackendSIPTIS.model.repository.PresentacionRepository;
import BackendSIPTIS.model.repository.ProyectoDeGradoRepository;

@Service
public class PresentacionService {
    
    @Autowired
    private CloudManagementService nube;

    @Autowired
    private PresentacionRepository presentacionRepository;

    @Autowired
    private ProyectoDeGradoRepository proyectoGradoRepository;

    public RespuestaServicio createPresentacion(MultipartFile libroAzul, MultipartFile proyecto, String fasePresentacion, Long id_proyecto){
        Presentacion presentacion = new Presentacion();
        Optional<ProyectoGrado> oproyectoGrado = proyectoGradoRepository.findById(id_proyecto);
        if (!oproyectoGrado.isPresent()){
            return RespuestaServicio.builder().mensajeServicio(MensajeServicio.NOT_FOUND).data(null).build();
        }
        ProyectoGrado proyectoGrado = oproyectoGrado.get();
        presentacion.setProyectoGrado(proyectoGrado);
        presentacion.setFase(fasePresentacion);
        if (!libroAzul.isEmpty()){
            String dirLibroAzul = nube.putObject(libroAzul, "Libro-Azul/");
            presentacion.setDirLibroAzul(dirLibroAzul);
        }
        if (!proyecto.isEmpty()){
            String dirProyectoGrado = nube.putObject(libroAzul, "Trabajos-Grado/");
            presentacion.setDirProyecto(dirProyectoGrado);
        }
        presentacionRepository.save(presentacion);
        return RespuestaServicio.builder().mensajeServicio(MensajeServicio.PRESENTACION_CREADA).data(presentacion).build();
    }
}
