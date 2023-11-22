package backend.siptis.controller.cloud;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.service.cloud.FileManagerService;
import backend.siptis.utils.constant.controllerConstans.ControllerConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;


@RestController
@RequestMapping(ControllerConstants.Cloud.BASE_PATH)
@CrossOrigin
@RequiredArgsConstructor
public class CloudController {

    private final FileManagerService fileDownloaderService;

    @GetMapping("/download-file/{folder}/{file}")
    ResponseEntity<byte[]> downloadFile(@PathVariable String folder, @PathVariable String file) {
        String key = folder + "/" + file;
        ServiceAnswer respuestaServicio = fileDownloaderService.downloadFileFromCloud(key);
        ByteArrayOutputStream downloadInputStream = (ByteArrayOutputStream) respuestaServicio.getData();
        return ResponseEntity.ok()
                .contentType(contentType(key))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + key + "\"")
                .body(downloadInputStream.toByteArray());
    }

    private MediaType contentType(String filename) {
        String[] fileArrSplit = filename.split("\\.");
        String fileExtension = fileArrSplit[fileArrSplit.length - 1];
        return switch (fileExtension) {
            case "txt" -> MediaType.TEXT_PLAIN;
            case "png" -> MediaType.IMAGE_PNG;
            case "jpg" -> MediaType.IMAGE_JPEG;
            case "pdf" -> MediaType.APPLICATION_PDF;
            default -> MediaType.APPLICATION_OCTET_STREAM;
        };
    }
}
