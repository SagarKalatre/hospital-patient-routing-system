package com.kiranacademy.hospital;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PatientValidator {

    public List<String> validatePatient(Patient patient) {

        List<String> errors = new ArrayList<>();

        if (patient.getPatientName() == null ||
                patient.getPatientName().trim().isEmpty()) {

            errors.add("Patient name is blank");

        } else if (patient.getPatientName().trim().length() < 3) {

            errors.add("Patient name must contain at least 3 characters");
        }

        if (patient.getAge() < 0 ||
                patient.getAge() > 120) {

            errors.add("Invalid age");
        }

        if (patient.getGender() == null ||
                !(patient.getGender().trim().equalsIgnoreCase("Male") ||
                  patient.getGender().trim().equalsIgnoreCase("Female") ||
                  patient.getGender().trim().equalsIgnoreCase("Other"))) {

            errors.add("Invalid gender");
        }

        if (patient.getDisease() == null ||
                patient.getDisease().trim().isEmpty()) {

            errors.add("Disease is blank");
        }

        if (patient.getAdmissionType() == null ||
                !(patient.getAdmissionType().trim().equalsIgnoreCase("Emergency") ||
                  patient.getAdmissionType().trim().equalsIgnoreCase("Regular"))) {

            errors.add("Invalid admission type");
        }

        if (patient.getConditionStatus() == null ||
                !(patient.getConditionStatus().trim().equalsIgnoreCase("Critical") ||
                  patient.getConditionStatus().trim().equalsIgnoreCase("Moderate") ||
                  patient.getConditionStatus().trim().equalsIgnoreCase("Stable"))) {

            errors.add("Invalid condition status");
        }

        if (patient.getTriageScore() < 1 ||
                patient.getTriageScore() > 10) {

            errors.add("Invalid triage score");
        }

        if (patient.getDoctorName() == null ||
                patient.getDoctorName().trim().isEmpty()) {

            errors.add("Doctor name is blank");
        }

        if (patient.getAdmissionDate() == null) {

            errors.add("Admission date is missing");

        } else if (patient.getAdmissionDate().isAfter(LocalDate.now())) {

            errors.add("Admission date cannot be a future date");
        }

        if (patient.getMobile() == null ||
                !patient.getMobile().trim().matches("\\d{10}")) {

            errors.add("Invalid mobile number");
        }

        if (patient.getTransferStatus() == null ||
                !patient.getTransferStatus().trim().equalsIgnoreCase("PENDING")) {

            errors.add("Transfer status must be PENDING");
        }

        return errors;
    }
}