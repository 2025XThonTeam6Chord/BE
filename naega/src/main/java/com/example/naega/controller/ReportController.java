package com.example.naega.controller;

import com.example.naega.common.GlobalHeaderInterceptor;
import com.example.naega.dto.report.*;
import com.example.naega.entity.Report;
import com.example.naega.service.report.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user-reports/analysis")
@RequiredArgsConstructor
@Tag(name = "심리 분석 리포트", description = "사용자의 심리 분석 결과를 조회하는 API 모음")
public class ReportController {

    private final ReportService reportService;

    @Operation(
            summary = "요약 리포트 조회",
            description = "사용자의 종합 심리 분석 요약 정보를 조회합니다. (예: 위험도, 요약 텍스트 등)"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "요약 리포트 조회 성공"),
    })
    @GetMapping("/summary")
    public ResponseEntity<ReportResultSummaryRes> summary() {
        Long userId = Long.parseLong(GlobalHeaderInterceptor.localThread.get());
        ReportAnalysisDto dto = reportService.getOrGenerateReport(userId);
        AiFullReport.Summary s = dto.getAiData().analysisSummary();

        return ResponseEntity.ok(new ReportResultSummaryRes(
                s.summary(),                // AI가 쓴 요약
                s.dangerRate(),             // AI가 계산한 위험도
                dto.getTotalAnswerCount()   // DB에 저장된 응답 개수
        ));
    }

    @Operation(
            summary = "심리 균형 분석 조회",
            description = "정서, 사회성, 수면, 스트레스, 회복력 등 5가지 심리 핵심 지표의 균형 분석을 조회합니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "심리 균형 분석 조회 성공"),
    })
    @GetMapping("/mental-balance")
    public ResponseEntity<MentalBalanceRes> analysisMentalBalance() {
        Long userId = Long.parseLong(GlobalHeaderInterceptor.localThread.get());
        ReportAnalysisDto dto = reportService.getOrGenerateReport(userId);
        AiFullReport.PsychScores s = dto.getAiData().scores();

        return ResponseEntity.ok(new MentalBalanceRes(
                s.emotion(), s.sociality(), s.sleep(), s.stress(), s.resilience()
        ));
    }

    @Operation(
            summary = "상세 분석 조회",
            description = "가장 낮은 지표, 가장 높은 지표, 종합 분석 메시지 등 상세 분석 정보를 조회합니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "상세 분석 조회 성공"),
    })
    @GetMapping("/detail")
    public ResponseEntity<DetailAnalysisRes> analysisDetail() {
        Long userId = Long.parseLong(GlobalHeaderInterceptor.localThread.get());
        ReportAnalysisDto dto = reportService.getOrGenerateReport(userId);
        AiFullReport.DetailComment d = dto.getAiData().detailAnalysis();

        return ResponseEntity.ok(new DetailAnalysisRes(
                d.lowestMessage(),
                d.highestMessage(),
                d.overallMessage()
        ));
    }

    @Operation(
            summary = "맞춤 케어 추천 조회",
            description = "사용자의 상태에 기반한 맞춤 케어 제안 항목을 리스트로 제공합니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "맞춤 케어 추천 조회 성공"),
    })
    @GetMapping("/recommendations")
    public ResponseEntity<CareRecommendationRes> getRecommendations() {
        Long userId = Long.parseLong(GlobalHeaderInterceptor.localThread.get());
        ReportAnalysisDto dto = reportService.getOrGenerateReport(userId);

        // AI 결과 리스트 -> 응답 DTO 리스트 변환
        List<CareRecommendationRes.CareRecommendationItem> items = dto.getAiData().recommendations().stream()
                .map(rec -> new CareRecommendationRes.CareRecommendationItem(
                        rec.title(),
                        rec.message()
                ))
                .toList();

        return ResponseEntity.ok(new CareRecommendationRes(items));
    }

    @Operation(
            summary = "주차별 변화 그래프",
            description = "주차 심리 균형 분석에서 낸 점수 5개의 평균값을 조회"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "감정 변화 조회 성공"),
    })
    @GetMapping("/emotion-trend")
    public ResponseEntity<EmotionTrendRes> getEmotionTrend() {
        Long userId = Long.parseLong(GlobalHeaderInterceptor.localThread.get());
        EmotionTrendRes response = reportService.getEmotionTrend(userId);
        return ResponseEntity.ok(response);
    }
}
