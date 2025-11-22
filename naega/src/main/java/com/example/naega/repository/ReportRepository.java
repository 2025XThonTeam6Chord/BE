package com.example.naega.repository;

import com.example.naega.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReportRepository extends JpaRepository<Report, Long> {
    Optional<Report> findByUsersIdAndReportDateBetween(Long userId, LocalDateTime start, LocalDateTime end);

    // 특정 유저의 특정 기간 리포트를 날짜 오름차순으로 조회 (그래프용)
    List<Report> findAllByUsersIdAndReportDateBetweenOrderByReportDateAsc(
            Long userId, LocalDateTime start, LocalDateTime end
    );
}
