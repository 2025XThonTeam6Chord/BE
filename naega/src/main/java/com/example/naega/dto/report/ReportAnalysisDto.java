package com.example.naega.dto.report;

import com.example.naega.entity.Report;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReportAnalysisDto {
    // DB 엔티티에서 가져온 정보
    private Long reportId;
    private Integer totalAnswerCount; // Report 엔티티에 있는 필드

    // AI가 분석한 정보 (JSON 파싱됨)
    private AiFullReport aiData;

    // 생성 메서드
    public static ReportAnalysisDto from(Report report, AiFullReport aiData) {
        return ReportAnalysisDto.builder()
                .reportId(report.getId())
                .totalAnswerCount(report.getTotalAnswerCount().intValue()) // Long -> Integer 변환
                .aiData(aiData)
                .build();
    }
}
