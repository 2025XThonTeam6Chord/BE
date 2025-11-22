package com.example.naega.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/questions")
public class QuestionsController {
    @Operation(
            summary = "질문에 대한 답변 제출",
            description = "사용자가 질문에 대한 답변을 제출합니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공적으로 답변이 제출되었습니다."),
            @ApiResponse(responseCode = "400", description = "잘못된 요청입니다.")
    })
    @PostMapping
    public ResponseEntity<Void> answer(@RequestParam(name = "answer") String answer) {
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "질문 조회",
            description = "등록된 질문을 조회합니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "질문 조회 성공"),
            @ApiResponse(responseCode = "404", description = "질문을 찾을 수 없습니다.")
    })
    @GetMapping
    public ResponseEntity<String> getQuestion() {
        return ResponseEntity.ok().build();
    }
}
