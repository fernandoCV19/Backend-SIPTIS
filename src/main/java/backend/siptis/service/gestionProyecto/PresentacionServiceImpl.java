package backend.siptis.service.gestionProyecto;

import backend.siptis.commons.MensajeServicio;
import backend.siptis.commons.RespuestaServicio;
import backend.siptis.model.entity.gestionProyecto.Presentacion;
import backend.siptis.model.entity.gestionProyecto.ProyectoGrado;
import backend.siptis.model.repository.gestionProyecto.PresentacionRepository;
import backend.siptis.model.repository.gestionProyecto.ProyectoDeGradoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PresentacionServiceImpl implements PresentacionService {

    @Autowired
    private CloudManagementService nube;

    @Autowired
    private PresentacionRepository presentacionRepository;

    @Autowired
    private ProyectoDeGradoRepository proyectoGradoRepository;

    @Override
    public RespuestaServicio createPresentation (Long idProyecto, String fase){
        Optional <Presentacion> presentacionesNoEntregadas = presentacionRepository.findTopByProyectoGradoIdAndRevisado(idProyecto, false);
        if (presentacionesNoEntregadas.isPresent()){
            return RespuestaServicio.builder().mensajeServicio(MensajeServicio.PRESENTACION_PENDIENTE).data(null).build();
        }
        Optional<ProyectoGrado> oproyectoGrado = proyectoGradoRepository.findById(idProyecto);
        if (oproyectoGrado.isEmpty()){
            return RespuestaServicio.builder().mensajeServicio(MensajeServicio.NOT_FOUND).data(null).build();
        }
        ProyectoGrado proyectoGrado = oproyectoGrado.get();
        Presentacion presentacion = new Presentacion();

        presentacion.setFase(fase);
        presentacion.setProyectoGrado(proyectoGrado);
        presentacionRepository.save(presentacion);
        return RespuestaServicio.builder().mensajeServicio(MensajeServicio.PRESENTACION_CREADA).data(presentacion).build();

    }

    @Override
    public RespuestaServicio gradePresentation (Long idPresentacion){
        Optional <Presentacion> opresentacion = presentacionRepository.findById(idPresentacion);
        if (opresentacion.isEmpty()){
            return RespuestaServicio.builder().mensajeServicio(MensajeServicio.NOT_FOUND).data(null).build();
        }
        Presentacion presentacion = opresentacion.get();
        presentacion.setRevisado(true);
        ProyectoGrado proyecto = presentacion.getProyectoGrado();
        String fase = presentacion.getFase();
        if (presentacion.getDirProyecto() != null)
            proyecto.setDirProyecto(presentacion.getDirProyecto());
        if (presentacion.getDirLibroAzul() != null)
            proyecto.setDirLibroAzul(presentacion.getDirLibroAzul());
        proyecto.setFase(fase);
        proyectoGradoRepository.saveAndFlush(proyecto);
        presentacionRepository.saveAndFlush(presentacion);
        return RespuestaServicio.builder().mensajeServicio(MensajeServicio.PRESENTACION_REVISADA).data(presentacion).build();
    }

    @Override
    public RespuestaServicio attachFile (Long idPresentacion, MultipartFile file, String path){
        path = correctFileContext(path);
        if (path.equals("Unknown")){
            return RespuestaServicio.builder().mensajeServicio(MensajeServicio.ERROR).data(null).build();
        }
        Optional <Presentacion> opresentacion = presentacionRepository.findById(idPresentacion);
        if (opresentacion.isEmpty()){
            return RespuestaServicio.builder().mensajeServicio(MensajeServicio.NOT_FOUND).data(null).build();
        }
        Presentacion presentacion = opresentacion.get();
        String key = nube.putObject(file,path);
        if (path.equals("Libro-Azul/")){presentacion.setDirLibroAzul(key);}
        if (path.equals("Trabajos-Grado/")){presentacion.setDirProyecto(key);}

        presentacionRepository.saveAndFlush(presentacion);
        return RespuestaServicio.builder().mensajeServicio(MensajeServicio.OPERACION_DE_NUBE_COMPLETADA).data(key).build();
    }

    @Override
    public RespuestaServicio removeFile (Long idPresentacion, String path){
        path = correctFileContext(path);
        if (path.equals("Unknown")){
            return RespuestaServicio.builder().mensajeServicio(MensajeServicio.ERROR).data(null).build();
        }
        Optional <Presentacion> opresentacion = presentacionRepository.findById(idPresentacion);
        if (opresentacion.isEmpty()){
            return RespuestaServicio.builder().mensajeServicio(MensajeServicio.NOT_FOUND).data(null).build();
        }
        Presentacion presentacion = opresentacion.get();
        String key = "";
        if (path.equals("Libro-Azul/")){
            key = presentacion.getDirLibroAzul();
            if(key == null){
                return RespuestaServicio.builder().mensajeServicio(MensajeServicio.NO_FILE_ATTACHED).data(null).build();
            }
            presentacion.setDirLibroAzul(null);
        }
        if (path.equals("Trabajos-Grado/")){
            key = presentacion.getDirProyecto();
            if(key == null){
                return RespuestaServicio.builder().mensajeServicio(MensajeServicio.NO_FILE_ATTACHED).data(null).build();
            }
            presentacion.setDirProyecto(null);
        }
        nube.deleteObject(key);
        presentacionRepository.saveAndFlush(presentacion);
        return RespuestaServicio.builder().mensajeServicio(MensajeServicio.OPERACION_DE_NUBE_COMPLETADA).data(presentacion).build();
    }

    @Override
    public RespuestaServicio delete (Long idPresentacion){
        Optional <Presentacion> presentacion = presentacionRepository.findById(idPresentacion);
        if (presentacion.isPresent()){
            String blue = presentacion.get().getDirLibroAzul();
            String project = presentacion.get().getDirProyecto();
            if (blue!= null)
                nube.deleteObject(blue);
            if (project!=null)
                nube.deleteObject(project);
            presentacionRepository.deleteById(idPresentacion);
            return RespuestaServicio.builder().mensajeServicio(MensajeServicio.PRESENTACION_ELIMINADA).data(null).build();
        }
        return RespuestaServicio.builder().mensajeServicio(MensajeServicio.NOT_FOUND).data(null).build();
    }

    @Override
    public RespuestaServicio downloadFileFromCloud(String key){
        String response= nube.getObject(key);
        if (response == null){
            return RespuestaServicio.builder().mensajeServicio(MensajeServicio.NOT_FOUND).data(null).build();
        }
        return RespuestaServicio.builder().mensajeServicio(MensajeServicio.OK).data(response).build();

    }

    private String correctFileContext (String context){
        if (context.equals("libro-azul")){return "Libro-Azul/";}
        if (context.equals("trabajo-grado")){return "Trabajos-Grado/";}
        return "Unknown";
    }
}
