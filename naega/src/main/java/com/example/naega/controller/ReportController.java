package com.example.naega.controller;

import com.example.naega.dto.report.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-reports/analysis")
@Tag(name = "심리 분석 리포트", description = "사용자의 심리 분석 결과를 조회하는 API 모음")
public class ReportController {

    @Operation(
            summary = "요약 리포트 조회",
            description = "사용자의 종합 심리 분석 요약 정보를 조회합니다. (예: 위험도, 요약 텍스트 등)"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "요약 리포트 조회 성공"),
    })
    @GetMapping("/summary")
    public ResponseEntity<ReportResultSummaryRes> summary() {
        return ResponseEntity.ok().build();
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
        return ResponseEntity.ok().build();
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
        return ResponseEntity.ok().build();
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
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "2주간 감정 변화 조회",
            description = "최근 14일간 사용자의 감정 점수 변화와 트렌드 분석 메시지를 조회합니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "감정 변화 조회 성공"),
    })
    @GetMapping("/emotion-trend")
    public ResponseEntity<EmotionTrendRes> getEmotionTrend() {
        return ResponseEntity.ok().build();
    }
}
