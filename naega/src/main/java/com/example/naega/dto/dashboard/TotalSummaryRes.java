package com.example.naega.dto.dashboard;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "전체 정신 건강 요약 DTO")
public record TotalSummaryRes(
        @Schema(description = "전체 학생 평균 점수", example = "6.2")
        Double averageScore,

        @Schema(description = "지난 주 대비 증감", example = "+0.3")
        String averageScoreChanged,

        @Schema(description = "고위험군 학생 수", example = "23")
        Integer highRiskNum,

        @Schema(description = "고위험군 학생 수 지난 주 대비 증감", example = "+2")
        String highRiskNumChanged,

        @Schema(description = "이번 주 응답률", example = "35.6")
        Double responseNum,

        @Schema(description = "이번 주 응답률 지난 주 대비 증감", example = "-3.5%")
        String responseNumChanged,

        @Schema(description = "상담 신청 건수", example = "12")
        Integer counselingReserveCount,

        @Schema(description = "상담 신청 건수 지난 주 대비 증감", example = "0")
        String counselingReserveCountChanged
) {
}
