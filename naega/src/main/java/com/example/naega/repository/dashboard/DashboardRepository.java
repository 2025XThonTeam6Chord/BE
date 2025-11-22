package com.example.naega.repository.dashboard;

import com.example.naega.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DashboardRepository extends JpaRepository<Report, Long> {
}
