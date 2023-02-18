package BackendSIPTIS.service.editoresYRevisores;

import BackendSIPTIS.commons.RespuestaServicio;
import BackendSIPTIS.model.repository.editoresYRevisores.SupervisorProyectoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SupervisorProyectoServiceImpl implements SupervisorProyectoService{

    private final SupervisorProyectoRepository supervisorProyectoRepository;

    @Override
    public RespuestaServicio obtenerTodosLosProyectosPorIdSupervisor(Long id) {
        return null;
    }
}
