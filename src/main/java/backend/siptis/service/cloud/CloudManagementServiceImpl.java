package backend.siptis.service.cloud;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CloudManagementServiceImpl implements CloudManagementService {
    private final static String BUCKET = "siptiscloudbucket";

    @Autowired
    private AmazonS3 s3Client;

    @Override
    public String putObject(MultipartFile multipartFile, String carpeta) {
        String extension = StringUtils.getFilenameExtension(multipartFile.getOriginalFilename());
        String key = carpeta + String.format("%s.%s", UUID.randomUUID(), extension);

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(multipartFile.getContentType());

        objectMetadata.setContentLength(multipartFile.getSize());

        try {
            PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET, key, multipartFile.getInputStream(),
                    objectMetadata);
            s3Client.putObject(putObjectRequest);
            return key;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public String putObject(File file, String carpeta) {
        String key = carpeta + file.getName();
        PutObjectRequest request = new PutObjectRequest(BUCKET, key, file);
        s3Client.putObject(request);
        return key;
    }

    @Override
    public ByteArrayOutputStream getObject(String key) {
        try {
            S3Object s3Object = s3Client.getObject(BUCKET, key);
            // ObjectMetadata metadata = s3Object.getObjectMetadata();

            InputStream is = s3Object.getObjectContent();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            int len;
            byte[] buffer = new byte[4096];
            while ((len = is.read(buffer, 0, buffer.length)) != -1) {
                outputStream.write(buffer, 0, len);
            }

            return outputStream;
        } catch (IOException ioException) {
            System.err.println(ioException);
        }

        return null;
    }

    @Override
    public void deleteObject(String key) {
        s3Client.deleteObject(BUCKET, key);
    }

    @Override
    public String getObjectURL(String key) {
        return String.format("https://%s.s3.amazonaws.com/%s", BUCKET, key);
    }

    @Override
    public List<String> getObjectslistFromFolder(String folderKey) {

        ListObjectsRequest listObjectsRequest = new ListObjectsRequest()
                .withBucketName(BUCKET)
                .withPrefix(folderKey + "/");

        List<String> keys = new ArrayList<>();

        ObjectListing objects = s3Client.listObjects(listObjectsRequest);

        for (; ; ) {
            List<S3ObjectSummary> summaries = objects.getObjectSummaries();
            if (summaries.size() < 1) {
                break;
            }

            summaries.forEach(s -> keys.add(s.getKey()));
            objects = s3Client.listNextBatchOfObjects(objects);
        }
        return keys;
    }

    @Override
    public String uploadDocumentToCloud(String filename) {
        File document = new File(filename);
        String key = putObject(document, "Documentos/");
        return key;
    }

    @Override
    public String uploadLetterToCloud(String filename, String projectName) {
        File document = new File(filename);
        String key = putObject(document, "Cartas/");
        document.delete();
        return key;
    }
}