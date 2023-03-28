package backend.siptis.service.gestionProyecto;

import backend.siptis.commons.RespuestaServicio;
import org.springframework.web.multipart.MultipartFile;

public interface PresentacionService {

    RespuestaServicio createPresentation (Long idProyecto, String fase);

    RespuestaServicio gradePresentation (Long idPresentacion);

    RespuestaServicio attachFile (Long idPresentacion, MultipartFile file, String context);

    RespuestaServicio removeFile (Long idPresentacion, String context);

    RespuestaServicio delete (Long idPresentacion);

    RespuestaServicio downloadFileFromCloud(String key);
}
