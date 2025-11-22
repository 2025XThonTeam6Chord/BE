package com.example.naega.dto.dashboard;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Builder
@Schema(description = "평균 심리 점수 추이 DTO")
public record AverageScoreRes(
        @ArraySchema(
                schema = @Schema(description = "주간 평균 심리 점수 리스트"),
                arraySchema = @Schema(implementation = AverageScore[].class)
        )
        List<AverageScore> averageScores
) {
    @Builder
    @Schema(description = "심리 점수 DTO")
    public record AverageScore(
            @Schema(description = "X축(날짜)", example = "2025/08/17~2025/08/24")
            String dateX,

            @Schema(description = "Y축(점수)", example = "28")
            String scoreY
    ){}
}
