package com.example.signalbackend.global.utils.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.signalbackend.global.utils.s3.exception.FileIsEmptyException;
import com.example.signalbackend.global.utils.s3.exception.FileSaveFailedException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class S3Util {
    private final AmazonS3 amazonS3Client;

    @Value("${cloud.aws.s3.url}")
    private String s3BaseUrl;
    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    public String uploadImage(MultipartFile file) {
        String extension = verificationFile(file);
        String filePath;
        try{
            filePath = saveImage(file, extension);
        } catch (IOException e) {
            throw FileSaveFailedException.EXCEPTION;
        }

        return filePath;
    }

    public String getS3ObjectUrl(String path) {
        return s3BaseUrl + path;
    }

    public void delete(String objectName, String path) {
        amazonS3Client.deleteObject(bucketName, objectName + path);
    }

    private String verificationFile(MultipartFile file) {
        if(file.isEmpty()) throw FileIsEmptyException.EXCEPTION;
        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

        return extension;
    }

    private String saveImage(MultipartFile file, String extension) throws IOException {
        String filePath = UUID.randomUUID() + extension;
        amazonS3Client.putObject(new PutObjectRequest(bucketName, filePath, file.getInputStream(), null)
                .withCannedAcl(CannedAccessControlList.PublicRead));

        return s3BaseUrl + filePath;
    }

}
