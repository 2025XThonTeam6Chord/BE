package com.example.naega.dto.report;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(description = "2주간 감정 변화 응답 DTO")
public record EmotionTrendRes(

        @Schema(description = "날짜 리스트 (예: 1일, 4일, 7일 등)")
        List<String> dates,

        @Schema(description = "각 날짜별 감정 점수", example = "[48, 52, 56, 60, 63, 68]")
        List<Integer> scores,

        @Schema(description = "감정 변화 트렌드 분석 문구")
        String trendMessage
) {
}
