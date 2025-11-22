package com.example.naega.repository;

import com.example.naega.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ReportRepository extends JpaRepository<Report, Long> {
    Optional<Report> findByUsersIdAndReportDateBetween(Long userId, LocalDateTime start, LocalDateTime end);
}
