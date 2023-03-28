package backend.siptis.service;

import backend.siptis.commons.ServiceMessage;
import backend.siptis.commons.ServiceAnswer;
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

    public ServiceAnswer obtenerProyectos(){
        List<ProyectoGrado> proyectos = proyectoDeGradoRepository.findAll();
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(proyectos).build();
    }
    public ServiceAnswer obtenerPresentaciones (Long idProyecto){
        Optional<ProyectoGrado> oProyectoGrado = proyectoDeGradoRepository.findById(idProyecto);
        if(oProyectoGrado.isEmpty()){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_FOUND).data(null).build();
        }
        ProyectoGrado  proyecto = oProyectoGrado.get();
        List<Presentacion> presentaciones = proyecto.getPresentaciones().stream().toList();
        if (presentaciones.size()==0){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.SIN_PRESENTACIONES).data(null).build();
        }
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(presentaciones).build();
    }

}
