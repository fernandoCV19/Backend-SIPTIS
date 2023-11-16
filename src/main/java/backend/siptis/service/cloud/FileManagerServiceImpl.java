package backend.siptis.service.cloud;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
@RequiredArgsConstructor
public class FileManagerServiceImpl implements FileManagerService {

    private final CloudManagementService nube;

    @Override
    public ServiceAnswer downloadFileFromCloud(String key) {
        ByteArrayOutputStream response = nube.getObject(key);
        if (response == null) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_FOUND).data(null).build();
        }
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(response).build();

    }


}
