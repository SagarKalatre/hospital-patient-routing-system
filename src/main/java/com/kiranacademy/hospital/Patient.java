package com.kiranacademy.hospital;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Data 
@NoArgsConstructor 
@AllArgsConstructor

public class Patient {
    @Id 
    private int patientId;
    private String patientName;
    private int age;
    private String gender;
    private String disease;
    private String admissionType;
    private String conditionStatus;
    private int triageScore;
    private String doctorName;
    private LocalDate admissionDate;
    private String mobile;
    private String transferStatus;
    private LocalDateTime processedAt;
    private LocalDateTime createdAt;
}
