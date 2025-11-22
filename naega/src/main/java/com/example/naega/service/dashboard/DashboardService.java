package com.example.naega.service.dashboard;


import com.example.naega.dto.dashboard.AverageScoreRes;
import com.example.naega.entity.Report;
import com.example.naega.repository.dashboard.DashboardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DashboardService {
    private final DashboardRepository dashboardRepository;

    public AverageScoreRes getDashboardGraph() {
        List<AverageScoreRes.AverageScore> scoreList = new ArrayList<>();

        LocalDateTime semesterStart = LocalDate.of(2025, 9, 1).atStartOfDay();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        for (int i = 1; i <= 15; i++) {
            LocalDateTime startOfWeek = semesterStart.plusWeeks(i - 1);
            LocalDateTime endOfWeek = startOfWeek.plusDays(6).with(LocalTime.MAX);

            List<Report> weekReports = dashboardRepository.findAllByReportDateBetween(startOfWeek, endOfWeek);

            double totalAvg = 0.0;

            if (!weekReports.isEmpty()) {

                double stressAvg = weekReports.stream()
                        .mapToInt(report -> report.getStressScore() == null ? 0 : report.getStressScore())
                        .average().orElse(0.0);

                double emotionAvg = weekReports.stream()
                        .mapToInt(report -> report.getEmotionScore() == null ? 0 : report.getEmotionScore())
                        .average().orElse(0.0);

                totalAvg = (stressAvg + emotionAvg) / 2.0;
            }

            String dateRange = startOfWeek.format(formatter) + "~" + endOfWeek.format(formatter);

            scoreList.add(AverageScoreRes.AverageScore.builder()
                    .dateX(dateRange)
                    .scoreY(String.format("%.1f", totalAvg))
                    .build());
        }

        return AverageScoreRes.builder()
                .averageScores(scoreList)
                .build();
    }
}
