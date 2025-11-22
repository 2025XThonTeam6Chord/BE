package com.example.naega.dto.report;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(description = "맞춤 케어 제안 응답 DTO")
public record CareRecommendationRes(

        @Schema(description = "추천 항목 리스트")
        List<CareRecommendationItem> recommendations

) {
    @Schema(description = "맞춤 케어 제안 항목 DTO")
    public record CareRecommendationItem(

            @Schema(description = "제안 제목", example = "수면 개선이 필요해요")
            String title,

            @Schema(description = "세부 메시지", example = "수면 점수가 38점으로 낮게 나타났습니다. 규칙적인 수면 패턴을 만들어보세요.")
            String message

    ) {}
}