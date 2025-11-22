package com.example.naega.dto.dashboard;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "상담 신청자 리스트 DTO")
public record CounselingRequestListRes(
        @ArraySchema(
                schema = @Schema(description = "상담 신청자 리스트"),
                arraySchema = @Schema(implementation = CounselingUser[].class)
        )
        List<CounselingUser> counselingUsers
) {
    @Schema(description = "상담 신청자 DTO")
    public record CounselingUser(
            @Schema(description = "이름", example = "하지은")
            String name,
            @Schema(description = "학번", example = "2021111937")
            String userKey,
            @Schema(description = "단과대", example = "공과대학")
            String univ,
            @Schema(description = "과", example = "컴퓨터공학과")
            String major,
            String risk
    ) {}
}
