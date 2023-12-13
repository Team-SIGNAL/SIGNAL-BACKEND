package com.example.signalbackend.domain.report.service;

import com.example.signalbackend.domain.report.domain.Report;
import com.example.signalbackend.domain.report.domain.repository.ReportRepository;
import com.example.signalbackend.domain.report.presentation.dto.CreateReportRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ReportService {

    private final ReportRepository reportRepository;

    @Transactional
    public void createReport(CreateReportRequest request) {
        reportRepository.save(Report.builder()
                .image(request.getImage())
                .content(request.getContent())
                .build());
    }
}
