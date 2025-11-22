package com.example.naega.dto.report;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "상세 분석 결과 DTO")
public record DetailAnalysisRes(
        @Schema(description = "가장 낮은 지표 상세 문구", example = "수면 영역이 38점으로 가장 낮게 측정되었습니다. 이 부분에 대한 관심이 필요합니다.")
        String lowestMessage,

        @Schema(description = "가장 높은 지표 상세 문구", example = "스트레스 영역이 72점으로 가장 높게 나타났습니다. 이 강점을 활용해보세요.")
        String highestMessage,

        @Schema(description = "종합 분석 문구", example = "전반적으로 약간의 불균형이 감지되었습니다...")
        String overallMessage
) {
}
