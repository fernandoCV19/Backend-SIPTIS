package BackendSIPTIS.service.editoresYRevisores;

import BackendSIPTIS.commons.RespuestaServicio;
import BackendSIPTIS.model.repository.editoresYRevisores.TribunalProyectoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TribunalProyectoServiceImpl implements TribunalProyectoService{

    private final TribunalProyectoRepository tribunalProyectoRepository;

    @Override
    public RespuestaServicio obtenerTodosLosProyectosPorIdTribunal(Long id) {
        return null;
    }
}
