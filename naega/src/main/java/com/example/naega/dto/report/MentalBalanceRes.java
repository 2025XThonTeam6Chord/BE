package com.example.naega.dto.report;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "심리 균형 분석 결과 DTO")
public record MentalBalanceRes(
        @Schema(description = "정서 점수", example = "65")
        Integer emotion,

        @Schema(description = "사회성 점수", example = "45")
        Integer sociality,

        @Schema(description = "수면 점수", example = "38")
        Integer sleep,

        @Schema(description = "스트레스 점수", example = "72")
        Integer stress,

        @Schema(description = "회복력 점수", example = "55")
        Integer resilience
) {
}
