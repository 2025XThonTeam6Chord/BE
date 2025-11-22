package com.example.naega.ai;

import com.example.naega.dto.report.AiFullReport;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

public interface PsychAnalysisAgent {
    @SystemMessage("""
        당신은 대학교 심리 상담 전문가입니다. 
        사용자의 [답변 데이터]를 분석하여 JSON 리포트를 작성하세요.
        
        [참고: 교내 프로그램]
        {{school_data}}
        
        [지침]
        1. 5가지 지표(정서,사회성,수면,스트레스,회복력)를 0~100점으로 수치화하세요.
        2. 점수가 가장 낮은 취약점을 찾아 교내 프로그램을 추천하세요.
        3. 반드시 정해진 JSON 포맷으로만 답변하세요.
    """)
    AiFullReport analyze(@UserMessage String userAnswers, @V("school_data") String schoolData);
}
