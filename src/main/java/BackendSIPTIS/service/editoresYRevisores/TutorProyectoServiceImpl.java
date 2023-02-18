package BackendSIPTIS.service.editoresYRevisores;

import BackendSIPTIS.commons.RespuestaServicio;
import BackendSIPTIS.model.repository.editoresYRevisores.TutorProyectoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TutorProyectoServiceImpl implements TutorProyectoService{

    private final TutorProyectoRepository tutorProyectoRepository;

    @Override
    public RespuestaServicio obtenerTodosLosProyectosPorIdTutor(Long id) {
        return null;
    }
}