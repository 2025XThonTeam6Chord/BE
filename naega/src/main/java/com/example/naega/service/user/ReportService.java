package com.example.naega.service.user;

import com.example.naega.ai.PsychAnalysisAgent;
import com.example.naega.dto.report.AiFullReport;
import com.example.naega.dto.report.EmotionTrendRes;
import com.example.naega.dto.report.ReportAnalysisDto;
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
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

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
    public ReportAnalysisDto getOrGenerateReport(Long userId) {
        // 1. 기준 시간 설정 (이번 주 월요일 00:00 ~ 이번 주 일요일 23:59:59)
        LocalDateTime now = LocalDateTime.now();

        // 이번 주 월요일 찾기 (오늘이 월요일이면 오늘, 아니면 지난 월요일)
        LocalDateTime startOfWeek = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
                .with(LocalTime.MIN); // 00:00:00

        // 이번 주 일요일 찾기 (오늘이 일요일이면 오늘, 아니면 다가올 일요일)
        LocalDateTime endOfWeek = now.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY))
                .with(LocalTime.MAX); // 23:59:59.999

        // 2. "이번 주" 범위 내에 해당 유저의 리포트가 있는지 조회
        // (만약 수요일에 만들었으면, 목~일요일에 들어와도 이걸 반환함)
        Optional<Report> weeklyReportOpt = reportRepository.findByUsersIdAndReportDateBetween(
                userId, startOfWeek, endOfWeek
        );

        if (weeklyReportOpt.isPresent()) {
            // A. 이번 주에 이미 만든 게 있다 -> 그거 반환 (재생성 X)
            Report existingReport = weeklyReportOpt.get();
            AiFullReport parsedReport = parseContent(existingReport.getContent());
            return ReportAnalysisDto.from(existingReport, parsedReport);
        }

        // 3. 이번 주 데이터가 없다 (월요일이 지났거나, 이번 주 첫 접속) -> 새로 생성
        // 1) 일단 생성 및 저장 (Entity 리턴됨)
        Report newReport = generateNewReport(userId);

        // 2) 방금 저장한 JSON을 다시 객체로 변환 (이게 있어야 null이 안 됨)
        AiFullReport newAiData = parseContent(newReport.getContent());

        // 3) Entity와 AI 데이터를 합쳐서 DTO 반환
        return ReportAnalysisDto.from(newReport, newAiData);
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

    @Transactional(readOnly = true)
    public EmotionTrendRes getEmotionTrend(Long userId) {
        // 1. 조회 기간 설정 (최근 8주)
        LocalDateTime endDate = LocalDateTime.now();
        LocalDateTime startDate = endDate.minusWeeks(8);

        // 2. DB에서 과거 데이터 조회 (이미 날짜순 정렬되어 있음)
        List<Report> reports = reportRepository.findAllByUsersIdAndReportDateBetweenOrderByReportDateAsc(
                userId, startDate, endDate
        );

        // 3. 데이터 변환 (1주 1리포트이므로 단순 매핑)
        List<String> dates = new ArrayList<>();
        List<Integer> scores = new ArrayList<>();

        for (Report report : reports) {
            // 날짜 -> "11월 3주차" 변환
            dates.add(convertToWeekString(report.getReportDate()));
            // 점수 -> 종합 평균 점수 (또는 emotionScore 등 원하는 점수)
            scores.add(report.getAverageScore());
        }

        // 4. 트렌드 분석 멘트 가져오기
        // (리스트의 마지막 요소가 가장 최신 리포트임)
        String trendMessage = "데이터가 더 쌓이면 AI가 분석해드릴게요!";

        if (!reports.isEmpty()) {
            Report latestReport = reports.get(reports.size() - 1); // 리스트의 마지막 = 최신
            try {
                // JSON 파싱해서 멘트 추출
                AiFullReport fullReport = parseContent(latestReport.getContent());
                if (fullReport.trendAnalysis() != null) {
                    trendMessage = fullReport.trendAnalysis().message();
                }
            } catch (Exception e) {
                // 파싱 에러 시 기본 메시지 유지 (로그 정도만 남김)
                System.err.println("트렌드 멘트 파싱 실패: " + e.getMessage());
            }
        }

        return new EmotionTrendRes(dates, scores, trendMessage);
    }

    // [Helper] 날짜 변환 메서드
    private String convertToWeekString(LocalDateTime date) {
        WeekFields weekFields = WeekFields.of(Locale.KOREA);
        int month = date.getMonthValue();
        int weekOfMonth = date.get(weekFields.weekOfMonth());
        return month + "월 " + weekOfMonth + "주차";
    }

    public AiFullReport parseContent(String jsonString) {
        try {
            return objectMapper.readValue(jsonString, AiFullReport.class);
        } catch (Exception e) {
            throw new RuntimeException("JSON 파싱 오류", e);
        }
    }
}
