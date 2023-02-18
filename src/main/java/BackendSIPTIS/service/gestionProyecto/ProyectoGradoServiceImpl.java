package BackendSIPTIS.service.gestionProyecto;

import BackendSIPTIS.model.repository.gestionProyecto.ProyectoGradoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProyectoGradoServiceImpl implements ProyectoGradoService{

    private final ProyectoGradoRepository proyectoGradoRepository;
}
