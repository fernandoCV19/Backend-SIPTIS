package BackendSIPTIS.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.util.IOUtils;

@Service
public class CloudManagementService {
    private final static String BUCKET = "siptiscloudbucket";

    @Autowired
    private AmazonS3 s3Client;

    public String putObject(MultipartFile multipartFile, String carpeta) {
        String extension = StringUtils.getFilenameExtension(multipartFile.getOriginalFilename());
        String key = carpeta + String.format("%s.%s", UUID.randomUUID(), extension);

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType("multipartFile.getContentType()");

        try {
            PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET, key, multipartFile.getInputStream(),
                    objectMetadata);
            s3Client.putObject(putObjectRequest);
            return key;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public String getObject(String key) {
        S3Object s3Object = s3Client.getObject(BUCKET, key);
        // ObjectMetadata metadata = s3Object.getObjectMetadata();

        S3ObjectInputStream inputStream = s3Object.getObjectContent();

        try {
            byte[] bytes = IOUtils.toByteArray(inputStream);
            return bytes.toString();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void deleteObject(String key) {
        s3Client.deleteObject(BUCKET, key);
    }

    public String getObjectURL(String key) {
        return String.format("https://%s.s3.amazonaws.com/%s", BUCKET, key);
    }

    public List<String> getObjectslistFromFolder(String folderKey) {

        ListObjectsRequest listObjectsRequest = new ListObjectsRequest()
                .withBucketName(BUCKET)
                .withPrefix(folderKey + "/");

        List<String> keys = new ArrayList<>();

        ObjectListing objects = s3Client.listObjects(listObjectsRequest);

        for (;;) {
            List<S3ObjectSummary> summaries = objects.getObjectSummaries();
            if (summaries.size() < 1) {
                break;
            }

            summaries.forEach(s -> keys.add(s.getKey()));
            objects = s3Client.listNextBatchOfObjects(objects);
        }

        return keys;
    }
}
