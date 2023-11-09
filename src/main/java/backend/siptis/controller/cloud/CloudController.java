package backend.siptis.controller.cloud;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.service.cloud.FileManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.ByteArrayOutputStream;


@RestController
@RequestMapping("/cloud")
@CrossOrigin
public class    CloudController {

    @Autowired
    private FileManagerService fileDownloaderService;

    @GetMapping("/download-file/{folder}/{file}")
    ResponseEntity<?> downloadFile(@PathVariable String folder, @PathVariable String file){
        String key = folder+"/"+file;
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
