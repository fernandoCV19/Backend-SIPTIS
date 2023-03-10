package BackendSIPTIS.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import BackendSIPTIS.service.BorradorUploadService;

@RestController
@RequestMapping
public class BorradorUploadController {
    @Autowired
    private BorradorUploadService s3Service;

    @GetMapping("/get")
    String prueba(){

        return "hola";
            
    }

    @PostMapping("/upload")
    Map<String, String> upload(@RequestParam MultipartFile file){
        System.out.println("aqui");
        String key = s3Service.putObject(file);
        Map <String, String> result = new HashMap<>();
        result.put("key", key);

        result.put("url", s3Service.getObjectURL(key));

        return result;

    }

    @GetMapping(value = "/get-object", params = "key")
    ResponseEntity <String> getObject(@RequestParam String key){
        String content = s3Service.getObject(key);

        return (ResponseEntity<String>) ResponseEntity
                .ok()
                .body(content);
            
    }


}
