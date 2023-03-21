package backend.siptis.service;

import backend.siptis.commons.MensajeServicio;
import backend.siptis.commons.RespuestaServicio;
import backend.siptis.model.entity.gestionProyecto.Presentacion;
import backend.siptis.model.entity.gestionProyecto.ProyectoGrado;
import backend.siptis.model.repository.ProyectoDeGradoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProyectoGradoService {
    @Autowired
    private ProyectoDeGradoRepository proyectoDeGradoRepository;

    public RespuestaServicio obtenerProyectos(){
        List<ProyectoGrado> proyectos = proyectoDeGradoRepository.findAll();
        return RespuestaServicio.builder().mensajeServicio(MensajeServicio.OK).data(proyectos).build();
    }
    public RespuestaServicio obtenerPresentaciones (Long idProyecto){
        Optional<ProyectoGrado> oProyectoGrado = proyectoDeGradoRepository.findById(idProyecto);
        if(oProyectoGrado.isEmpty()){
            return RespuestaServicio.builder().mensajeServicio(MensajeServicio.NOT_FOUND).data(null).build();
        }
        ProyectoGrado  proyecto = oProyectoGrado.get();
        List<Presentacion> presentaciones = proyecto.getPresentaciones().stream().toList();
        if (presentaciones.size()==0){
            return RespuestaServicio.builder().mensajeServicio(MensajeServicio.SIN_PRESENTACIONES).data(null).build();
        }
        return RespuestaServicio.builder().mensajeServicio(MensajeServicio.OK).data(presentaciones).build();
    }

}
