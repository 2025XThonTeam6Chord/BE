package com.example.naega.repository.user;

import com.example.naega.entity.Risk;

public interface CounselingUserProjection {
    String getName();
    Long getStudentNumber();
    String getStudentMajor();
    String getStudentUniv();

    Risk getStudentRisk();
    Integer getAverageScore();
}
