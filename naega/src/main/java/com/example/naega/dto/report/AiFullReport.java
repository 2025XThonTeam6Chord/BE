package com.example.naega.dto.report;

import java.util.List;

public record AiFullReport(
        PsychScores scores,
        Summary analysisSummary,
        DetailComment detailAnalysis,
        List<Recommendation> recommendations,
        TrendComment trendAnalysis
) {
    // 1. 점수 (MentalBalanceRes)
    public record PsychScores(
            int emotion,    // 정서
            int sociality,  // 사회성
            int sleep,      // 수면
            int stress,     // 스트레스
            int resilience  // 회복력
    ) {}

    // 2. 요약 (ReportResultSummaryRes)
    public record Summary(
            String summaryDescription, // 마음 날씨 ("구름 낀 해...") -> summary 매핑
            int dangerScore            // 위험도 (0~100) -> dangerRate 매핑
    ) {}

    // 3. 상세 (DetailAnalysisRes)
    public record DetailComment(
            String lowestMetricMessage,  // 가장 낮은 지표 멘트 -> lowestMessage 매핑
            String highestMetricMessage, // 가장 높은 지표 멘트 -> highestMessage 매핑
            String overallMessage        // 종합 분석 -> overallMessage 매핑
    ) {}

    // 4. 추천 (CareRecommendationRes)
    public record Recommendation(
            String title,    // 제안 제목 ("수면 개선 필요")
            String message   // 세부 메시지 ("점수가 낮아요...")
    ) {}

    // 5. 트렌드 (EmotionTrendRes)
    public record TrendComment(
            String message // 트렌드 분석 문구 ("지난주보다 좋아졌어요")
    ) {}
}
