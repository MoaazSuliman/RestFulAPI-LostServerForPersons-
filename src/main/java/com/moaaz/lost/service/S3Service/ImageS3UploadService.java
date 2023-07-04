package com.moaaz.lost.service.S3Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.util.Base64;
import java.util.UUID;

@Service
public class ImageS3UploadService {

    private final S3Client s3Client;

    @Autowired
    public ImageS3UploadService(S3Client s3Client) {
        this.s3Client = s3Client;
    }


    public String uploadImageToS3AndGetImageUrl(String base64Image) {
        // Decode the Base64 image
        byte[] imageBytes = Base64.getDecoder().decode(base64Image);

        // Generate a unique filename or key for the image
        String filename = UUID.randomUUID().toString() + ".png";

        // Upload the image to S3 bucket
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket("lostserver-images")
                .key(filename)
                .build();
        s3Client.putObject(request, RequestBody.fromBytes(imageBytes));

        // Return the URL of the uploaded image
        return "https://lostserver-images.s3.amazonaws.com/" + filename;

    }
}
