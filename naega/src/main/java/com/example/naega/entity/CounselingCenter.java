package com.example.naega.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "counseling_center")
@NoArgsConstructor
public class CounselingCenter {
    @Id
    @Column(name = "counseling_center_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @Column(name = "center_name")
    private String centerName;

    @Column(name = "center_ph")
    private String centerPh;

    @Column(name = "center_address")
    private String centerAddress;
}
