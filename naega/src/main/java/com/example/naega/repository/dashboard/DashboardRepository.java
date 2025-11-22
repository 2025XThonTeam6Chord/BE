package com.example.naega.repository.dashboard;

import com.example.naega.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface DashboardRepository extends JpaRepository<Report, Long> {
    List<Report> findAllByReportDateBetween(LocalDateTime weekStart, LocalDateTime weekEnd);
}
