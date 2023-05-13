package backend.siptis.service.cloud;

import backend.siptis.commons.ServiceAnswer;

public interface FileManagerService {
    ServiceAnswer downloadFileFromCloud(String key);
}
