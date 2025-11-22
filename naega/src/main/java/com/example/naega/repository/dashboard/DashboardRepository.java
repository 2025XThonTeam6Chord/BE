package com.example.naega.repository.dashboard;

import com.example.naega.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface DashboardRepository extends JpaRepository<Report, Long> {
    List<Report> findAllByReportDateBetween(LocalDateTime weekStart, LocalDateTime weekEnd);

    // 1. 학과별 (Major) 통계
    @Query("""
        SELECT u.studentMajor as studentMajor, 
               AVG(r.stressScore) as avgScore, 
               COUNT(DISTINCT u) as count 
        FROM Report r 
        JOIN r.users u 
        GROUP BY u.studentMajor
    """)
    List<DashboardGroupProjection> findByMajorWithStressAvg();

    // 2. 학년별 (Year) 통계
    @Query("""
        SELECT u.studentYear as studentYear, 
               AVG(r.stressScore) as avgScore, 
               COUNT(DISTINCT u) as count 
        FROM Report r 
        JOIN r.users u 
        GROUP BY u.studentYear
    """)
    List<DashboardGroupProjection> findByYearWithStressAvg();

    // 3. 단과대별 (Univ) 통계
    @Query("""
        SELECT u.studentUniv as studentUniv, 
               AVG(r.stressScore) as avgScore, 
               COUNT(DISTINCT u) as count 
        FROM Report r 
        JOIN r.users u 
        GROUP BY u.studentUniv
    """)
    List<DashboardGroupProjection> findByUnivWithStressAvg();
}
