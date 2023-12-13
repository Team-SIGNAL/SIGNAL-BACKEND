package com.example.signalbackend.domain.report.presentation;

import com.example.signalbackend.domain.report.presentation.dto.CreateReportRequest;
import com.example.signalbackend.domain.report.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/report")
@RestController
public class ReportController {

    private final ReportService reportService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createReport(@RequestBody CreateReportRequest request) {
        reportService.createReport(request);
    }
}
