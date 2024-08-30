package com.alfrendosilalahi.spring.aws.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
public class ImageService {

    @Value("${spring.cloud.aws.bucket.name}")
    private String bucketName;

    private final AmazonS3 s3Client;

    public ImageService(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }

    public boolean uploadFile(MultipartFile image) {
        String dir = "test/";
        try {
            s3Client.putObject(bucketName, dir + image.getOriginalFilename(), image.getInputStream(), new ObjectMetadata());
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public byte[] getImage(String imageName) throws IOException {
        try (
                S3Object s3Object = s3Client.getObject(bucketName, imageName);
                InputStream inputStream = s3Object.getObjectContent()
        ) {
            return IOUtils.toByteArray(inputStream);
        }
    }

}
