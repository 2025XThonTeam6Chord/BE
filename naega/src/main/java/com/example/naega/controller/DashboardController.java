package com.example.naega.controller;

import com.example.naega.dto.dashboard.AverageScoreRes;
import com.example.naega.dto.dashboard.CounselingRequestListRes;
import com.example.naega.dto.dashboard.FilteredGroupScoreRes;
import com.example.naega.dto.dashboard.TotalSummaryRes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
@Tag(name="대시보드", description = "관리자 대시보드에 사용되는 API 모음")
public class DashboardController {
    @Operation(
            summary = "전체 정신건강 지표 요약",
            description = "관리자가 확인할 수 있는 전체 학생 평균 점수, 상담 신청 건수, 이번 주 응답률, 고위험군 학생 수를 조회합니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공했습니다."),
            @ApiResponse(responseCode = "400", description = "잘못된 요청입니다."),
            @ApiResponse(responseCode = "401", description = "관리자가 아닙니다.")
    })
    @GetMapping("/total-summary")
    public ResponseEntity<TotalSummaryRes> getDashboardTotalSummary() {
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "주차별 전체 학생들의 평균 심리 점수 추이 조회",
            description = "주차별로 전체 학생들의 평균 심리 점수 추이를 조회합니다. (100점 만점)"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공했습니다."),
            @ApiResponse(responseCode = "400", description = "잘못된 요청입니다."),
            @ApiResponse(responseCode = "401", description = "관리자가 아닙니다.")
    })
    @GetMapping("/average-score")
    public ResponseEntity<AverageScoreRes> getDashboardAverageScore() {
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "집단별 점수 조회",
            description = "아무것도 보내지 않는 경우 or 0은 단과대별, 1은 학과별, 2는 학년별 결과를 조회합니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공했습니다."),
            @ApiResponse(responseCode = "400", description = "잘못된 요청입니다."),
            @ApiResponse(responseCode = "401", description = "관리자가 아닙니다.")
    })
    @GetMapping("/filtered-score")
    public ResponseEntity<FilteredGroupScoreRes> getDashboardGroupScoreByFilter(
            @RequestParam(name = "filter") Integer filter
    ) {
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "상담 신청 목록 조회",
            description = "상담 신청자들의 목록을 조회합니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공했습니다."),
            @ApiResponse(responseCode = "400", description = "잘못된 요청입니다."),
            @ApiResponse(responseCode = "401", description = "관리자가 아닙니다.")
    })
    @GetMapping("/reserve-list")
    public ResponseEntity<CounselingRequestListRes> getDashboardCounselingReserveList() {
        return ResponseEntity.ok().build();
    }
}
