package backend.siptis.service;

import java.util.Optional;

import backend.siptis.commons.MensajeServicio;
import backend.siptis.commons.RespuestaServicio;
import backend.siptis.model.entity.gestionProyecto.Presentacion;
import backend.siptis.model.entity.gestionProyecto.ProyectoGrado;
import backend.siptis.model.repository.PresentacionRepository;
import backend.siptis.model.repository.ProyectoDeGradoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PresentacionService {
    
    @Autowired
    private CloudManagementService nube;

    @Autowired
    private PresentacionRepository presentacionRepository;

    @Autowired
    private ProyectoDeGradoRepository proyectoGradoRepository;

    public RespuestaServicio generateNew (Long idProyecto){
        Optional <Presentacion> presentacionesNoEntregadas = presentacionRepository.findTopByProyectoGradoIdAndEntregado(idProyecto, false);
        if (presentacionesNoEntregadas.isPresent()){
            return RespuestaServicio.builder().mensajeServicio(MensajeServicio.PRESENTACION_PENDIENTE).data(null).build();
        }
        Presentacion presentacion = new Presentacion();
        Optional<ProyectoGrado> oproyectoGrado = proyectoGradoRepository.findById(idProyecto);
        if (oproyectoGrado.isEmpty()){
            return RespuestaServicio.builder().mensajeServicio(MensajeServicio.NOT_FOUND).data(null).build();
        }
        ProyectoGrado proyectoGrado = oproyectoGrado.get();
        presentacion.setProyectoGrado(proyectoGrado);
        presentacionRepository.save(presentacion);
        return RespuestaServicio.builder().mensajeServicio(MensajeServicio.PRESENTACION_CREADA).data(presentacion).build();
    }
    public RespuestaServicio delete (Long idPresentacion){
        Optional <Presentacion> presentacion = presentacionRepository.findById(idPresentacion);
        if (presentacion.isPresent()){
            presentacionRepository.deleteById(idPresentacion);
            return RespuestaServicio.builder().mensajeServicio(MensajeServicio.PRESENTACION_ELIMINADA).data(null).build();
        }
        return RespuestaServicio.builder().mensajeServicio(MensajeServicio.NOT_FOUND).data(null).build();
    }
    public RespuestaServicio entregarPresentacion(Long idPresentacion, String fase){
        Optional <Presentacion> opresentacion = presentacionRepository.findById(idPresentacion);
        if (opresentacion.isEmpty()){
            return RespuestaServicio.builder().mensajeServicio(MensajeServicio.NOT_FOUND).data(null).build();
        }
        Presentacion presentacion = opresentacion.get();
        if(presentacion.getEntregado()){
            return RespuestaServicio.builder().mensajeServicio(MensajeServicio.ERROR).data(null).build();
        }
        ProyectoGrado proyecto = presentacion.getProyectoGrado();
        presentacion.setFase(fase);
        if (presentacion.getDirProyecto() != null)
            proyecto.setDirProyecto(presentacion.getDirProyecto());
        if (presentacion.getDirLibroAzul() != null)
            proyecto.setDirLibroAzul(presentacion.getDirLibroAzul());
        proyecto.setFase(fase);
        presentacion.setEntregado(true);
        proyectoGradoRepository.saveAndFlush(proyecto);
        presentacionRepository.saveAndFlush(presentacion);
        return RespuestaServicio.builder().mensajeServicio(MensajeServicio.PRESENTACION_ENTREGADA).data(presentacion).build();
    }
    public RespuestaServicio subirLibroAzul(Long idPresentacion,MultipartFile libroAzul ){
        Optional <Presentacion> opresentacion = presentacionRepository.findById(idPresentacion);
        if (opresentacion.isEmpty()){
            return RespuestaServicio.builder().mensajeServicio(MensajeServicio.NOT_FOUND).data(null).build();
        }
        Presentacion presentacion = opresentacion.get();
        String key = nube.putObject(libroAzul,"Libro-Azul/");
        presentacion.setDirLibroAzul(key);
        presentacionRepository.saveAndFlush(presentacion);
        return RespuestaServicio.builder().mensajeServicio(MensajeServicio.OPERACION_DE_NUBE_COMPLETADA).data(presentacion).build();
    }
    public RespuestaServicio subirProyecto(Long idPresentacion,MultipartFile proyecto ){
        Optional <Presentacion> opresentacion = presentacionRepository.findById(idPresentacion);
        if (opresentacion.isEmpty()){
            return RespuestaServicio.builder().mensajeServicio(MensajeServicio.NOT_FOUND).data(null).build();
        }
        Presentacion presentacion = opresentacion.get();
        String key = nube.putObject(proyecto,"Trabajos-Grado/");
        presentacion.setDirProyecto(key);
        presentacionRepository.saveAndFlush(presentacion);
        return RespuestaServicio.builder().mensajeServicio(MensajeServicio.OPERACION_DE_NUBE_COMPLETADA).data(presentacion).build();
    }

    public RespuestaServicio quitarLibroAzul(Long idPresentacion){
        Optional <Presentacion> opresentacion = presentacionRepository.findById(idPresentacion);
        if (opresentacion.isEmpty()){
            return RespuestaServicio.builder().mensajeServicio(MensajeServicio.NOT_FOUND).data(null).build();
        }
        Presentacion presentacion = opresentacion.get();
        String key = presentacion.getDirLibroAzul();
        if(key == null){
            return RespuestaServicio.builder().mensajeServicio(MensajeServicio.NOT_FOUND).data(null).build();
        }
        nube.deleteObject(key);
        presentacion.setDirLibroAzul(null);
        presentacionRepository.saveAndFlush(presentacion);
        return RespuestaServicio.builder().mensajeServicio(MensajeServicio.OPERACION_DE_NUBE_COMPLETADA).data(presentacion).build();
    }

    public RespuestaServicio quitarTrabajoGrado(Long idPresentacion){
        Optional <Presentacion> opresentacion = presentacionRepository.findById(idPresentacion);
        if (opresentacion.isEmpty()){
            return RespuestaServicio.builder().mensajeServicio(MensajeServicio.NOT_FOUND).data(null).build();
        }
        Presentacion presentacion = opresentacion.get();
        String key = presentacion.getDirProyecto();
        if(key == null){
            return RespuestaServicio.builder().mensajeServicio(MensajeServicio.NOT_FOUND).data(null).build();
        }
        nube.deleteObject(key);
        presentacion.setDirProyecto(null);
        presentacionRepository.saveAndFlush(presentacion);
        return RespuestaServicio.builder().mensajeServicio(MensajeServicio.OPERACION_DE_NUBE_COMPLETADA).data(presentacion).build();
    }

}
