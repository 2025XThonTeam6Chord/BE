package com.example.naega.dto.answer;

public record UserAnswerReq(
        String answer,
        Long userId,
        Long questionId
) {
}
