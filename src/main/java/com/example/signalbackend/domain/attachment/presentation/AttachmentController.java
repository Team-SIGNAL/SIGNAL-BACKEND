package com.example.signalbackend.domain.attachment.presentation;

import com.example.signalbackend.domain.attachment.presentation.dto.response.UploadImageResponse;
import com.example.signalbackend.domain.attachment.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RequestMapping("/attachment")
@RestController
public class AttachmentController {
    private final AttachmentService attachmentService;

    @PostMapping
    public UploadImageResponse uploadHospitalImage(MultipartFile image) {
        return attachmentService.uploadImage(image);
    }
}
