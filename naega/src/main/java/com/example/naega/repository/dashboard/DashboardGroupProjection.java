package com.example.naega.repository.dashboard;

public interface DashboardGroupProjection {
    Integer getStudentYear();

    String getStudentMajor();

    String getStudentUniv();

    Double getAvgScore();

    Long getCount();
}