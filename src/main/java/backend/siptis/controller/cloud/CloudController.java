package backend.siptis.controller.cloud;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.service.cloud.FileManagerService;
import backend.siptis.utils.constant.controller_constans.ControllerConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;

@Tag(name = ControllerConstants.Cloud.TAG_NAME, description = ControllerConstants.Cloud.TAG_DESCRIPTION)
@RestController
@RequestMapping(ControllerConstants.Cloud.BASE_PATH)
@CrossOrigin
@RequiredArgsConstructor
public class CloudController {

    private final FileManagerService fileDownloaderService;

    @Operation(summary = "Download file from cloud")
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
            case "xlsx", "xls" ->
                    MediaType.valueOf("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            default -> MediaType.APPLICATION_OCTET_STREAM;
        };
    }
}
