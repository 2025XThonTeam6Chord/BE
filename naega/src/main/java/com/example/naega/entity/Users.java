package com.example.naega.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "users")
@NoArgsConstructor
public class Users {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_number")
    private Long studentNumber;

    @Column(name = "student_major")
    private String studentMajor;

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "student_univ")
    private String studentUniv;

    @Column(name = "student_year")
    private Integer studentYear;

    @Enumerated(EnumType.STRING)
    @Column(name = "student_risk")
    private Risk studentRisk;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRole userRole;

    @OneToMany(mappedBy = "users")
    private List<Answers> answers = new ArrayList<>();
}
