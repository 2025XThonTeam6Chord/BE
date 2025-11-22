package com.example.naega.service.user;

import com.example.naega.ai.PsychAnalysisAgent;
import com.example.naega.dto.report.AiFullReport;
import com.example.naega.entity.Report;
import com.example.naega.entity.Users;
import com.example.naega.repository.ReportRepository;
import com.example.naega.repository.UserRepository;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.service.AiServices;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tools.jackson.databind.ObjectMapper;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;
    private final UserRepository userRepository;
    private final ChatLanguageModel chatModel; // 설정파일에서 주입됨
    private final ObjectMapper objectMapper;   // JSON 변환기

    private PsychAnalysisAgent aiAgent;
    private String schoolDataCache;

    @PostConstruct
    public void init() {
        try {
            // 1. 텍스트 파일 로딩
            var resource = new ClassPathResource("school_data.txt");
            this.schoolDataCache = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);

            // 2. AI 에이전트 생성
            this.aiAgent = AiServices.builder(PsychAnalysisAgent.class)
                    .chatLanguageModel(chatModel)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("초기화 실패", e);
        }
    }

    @Transactional
    public Report getOrGenerateReport(Long userId) {
        LocalDateTime startOfDay = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        LocalDateTime endOfDay = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);

        // 1. 오늘자 리포트 DB 조회
        return reportRepository.findByUsersIdAndReportDateBetween(userId, startOfDay, endOfDay)
                .orElseGet(() -> generateNewReport(userId));
    }

    private Report generateNewReport(Long userId) {
        // TODO: 실제 답변 데이터를 가져오는 로직 필요 (임시 데이터 사용)
        String mockUserAnswers = "사용자 답변 데이터...";
        long answerCount = 15L; // 실제 답변 개수 조회 필요

        // 1. AI 분석 수행
        AiFullReport aiResult = aiAgent.analyze(mockUserAnswers, schoolDataCache);
        AiFullReport.PsychScores s = aiResult.scores();

        try {
            String contentJson = objectMapper.writeValueAsString(aiResult);
            Users user = userRepository.findById(userId).orElseThrow();

            // 3. 엔티티 생성 (점수는 컬럼에, 나머지는 content에)
            Report newReport = Report.builder()
                    .users(user)
                    .reportDate(LocalDateTime.now())
                    .totalAnswerCount(answerCount)
                    // [점수 컬럼 매핑]
                    .emotionScore(s.emotion())
                    .stressScore(s.stress())
                    .resilienceScore(s.resilience())
                    .socialityScore(s.sociality())
                    .sleepScore(s.sleep())
                    .averageScore((s.emotion() + s.stress() + s.resilience() + s.sociality() + s.sleep()) / 5)
                    // [JSON 컨텐츠 매핑]
                    .content(contentJson)
                    .build();

            return reportRepository.save(newReport);

        } catch (Exception e) {
            throw new RuntimeException("리포트 생성 중 오류 발생", e);
        }
    }

    public AiFullReport parseContent(String jsonString) {
        try {
            return objectMapper.readValue(jsonString, AiFullReport.class);
        } catch (Exception e) {
            throw new RuntimeException("JSON 파싱 오류", e);
        }
    }
}
