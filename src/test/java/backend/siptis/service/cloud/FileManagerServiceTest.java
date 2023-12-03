package backend.siptis.service.cloud;

import backend.siptis.commons.ServiceMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class FileManagerServiceTest {
    private final FileManagerService fileManagerService;

    @Autowired
    FileManagerServiceTest(FileManagerService fileManagerService) {
        this.fileManagerService = fileManagerService;
    }

    @Test
    @DisplayName("Test for download file from cloud")
    void givenKeyWhenDownloadFileFromCloudThenServiceMessageOK() {
        assertEquals(ServiceMessage.OK, fileManagerService.downloadFileFromCloud("Cartas/test.pdf").getServiceMessage());
    }

    @Test
    @DisplayName("Test for download file from cloud")
    void givenKeyWhenDownloadFileFromCloudThenServiceMessageNotFound() {
        assertEquals(ServiceMessage.NOT_FOUND, fileManagerService.downloadFileFromCloud("test.pdf").getServiceMessage());
    }
}
