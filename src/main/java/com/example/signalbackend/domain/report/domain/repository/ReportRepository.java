package com.example.signalbackend.domain.report.domain.repository;

import com.example.signalbackend.domain.report.domain.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReportRepository extends JpaRepository<Report, UUID> {
}
