package com.example.naega.dto.answer;

import com.example.naega.entity.ResponseType;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "Question 반환 DTO")
public record UserAnswerRes(
        @Schema(description = "question 내용")
        String content,
        @Schema(description = "question type", example = "(매우 그렇다 ~ 전혀 아니다) -> RATING_5 , (예 / 아니오) -> YES_NO , (단답형 텍스트) -> SHORT_TEXT")
        ResponseType responseType,
        @Schema(description = "qestion ID")
        Long questionId,

        @JsonInclude(JsonInclude.Include.NON_NULL)
        String question1,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String question2,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String question3,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String question4,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String question5,

        Long count
) {
}
