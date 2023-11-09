package com.example.signalbackend.domain.attachment.service;

import com.example.signalbackend.domain.attachment.presentation.dto.response.UploadImageResponse;
import com.example.signalbackend.global.utils.s3.S3Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class AttachmentService {
    private final S3Util s3Util;

    @Transactional
    public UploadImageResponse uploadImage(MultipartFile image) {
        return new UploadImageResponse(s3Util.uploadImage(image));
    }
}
