package com.example.naega.dto.report;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(description = "주차별 감정 변화 그래프 응답 DTO")
public record EmotionTrendRes(

        @Schema(description = "날짜 리스트 (예: 8월 1주차, 8월 2주차")
        List<String> dates,

        @Schema(description = "각 날짜별 감정 점수", example = "[48, 52, 56, 60, 63, 68]")
        List<Integer> scores,

        @Schema(description = "감정 변화 트렌드 분석 문구")
        String trendMessage
) {
}
