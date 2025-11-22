package com.example.naega.dto.dashboard;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "집단별 점수 DTO")
public record FilteredGroupScoreRes(
        @ArraySchema(
                schema = @Schema(description = "집단별 점수 리스트"),
                arraySchema = @Schema(implementation = FilteredGroup[].class)
        )
        List<FilteredGroup> filteredGroups
) {
    @Schema(description = "집단 DTO")
    public record FilteredGroup(
            @Schema(description = "X축(집단)", example = "공과대학/컴퓨터공학과/1학년 등")
            String groupX,
            @Schema(description = "Y축(점수)", example = "58")
            String scoreY
    ){}
}
