package com.example.naega.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name = "report")
public class Report {
    @Id
    @Column(name = "report_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users users;

    @Column(name = "content", columnDefinition = "LONGTEXT")
    private String content;

    @Column(name = "report_date")
    private LocalDateTime reportDate;

    @Column(name = "emotion_score")
    private Integer emotionScore;

    @Column(name = "stress_score")
    private Integer stressScore;

    @Column(name = "resilience_score")
    private Integer resilienceScore;

    @Column(name = "sociality_score")
    private Integer socialityScore;

    @Column(name = "sleep_score")
    private Integer sleepScore;

    @Column(name = "average_score")
    private Integer averageScore;

    @Column(name = "total_answer_count")
    private Long totalAnswerCount;

}
