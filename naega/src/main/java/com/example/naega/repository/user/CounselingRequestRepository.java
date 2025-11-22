package com.example.naega.repository.user;

import com.example.naega.entity.CounselingRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CounselingRequestRepository extends JpaRepository<CounselingRequest, Long> {

    @Query("""
        SELECT cr.users.studentName AS name,
               cr.users.studentNumber AS studentNumber,
               cr.users.studentMajor AS studentMajor,
               cr.users.studentUniv AS studentUniv,
               r.averageScore AS averageScore,
               u.studentRisk AS studentRisk
        FROM CounselingRequest cr
        JOIN cr.users u
        LEFT JOIN Report r ON r.users = u AND r.reportDate = cr.reserveDate
        ORDER BY cr.reserveDate DESC
    """)
    List<CounselingUserProjection> findCounselingRequestListWithUserDetailsAndReportScore();
}
