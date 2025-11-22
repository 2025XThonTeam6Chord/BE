package com.example.naega.dto.report;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "신고 요약 응답 DTO")
public record ReportResultSummaryRes(
        @Schema(description = "심리검사 내용 요약(자연어)", example = "오늘의 마음은 구름 낀 해와 같아요. 약간의 스트레스가 있지만,,")
        String summary,

        @Schema(description = "위험도 지수 (0~100)", example = "75")
        Integer dangerRate,

        @Schema(description = "총 응답 개수", example = "12")
        Integer answerCount
) {
}