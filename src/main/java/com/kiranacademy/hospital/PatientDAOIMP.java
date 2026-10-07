package com.kiranacademy.hospital;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientDAOIMP implements PatientDAO {
    @Override
    public List<Patient> getPendingPatients(Connection con) throws SQLException {
        List<Patient> patients = new ArrayList<>();

        String sql = "select * from hospital_patient_intake " +
                "where transfer_status = 'pending' " +
                "order by patient_id";

        try (PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Patient patient = new Patient();

                patient.setPatientId(rs.getInt("patient_id"));
                patient.setPatientName(rs.getString("patient_name"));
                patient.setAge(rs.getInt("age"));
                patient.setGender(rs.getString("gender"));
                patient.setDisease(rs.getString("disease"));
                patient.setAdmissionType(rs.getString("admission_type"));
                patient.setConditionStatus(rs.getString("condition_status"));
                patient.setTriageScore(rs.getInt("triage_score"));
                patient.setDoctorName(rs.getString("doctor_name"));

                if (rs.getDate("admission_date") != null) {
                    patient.setAdmissionDate(rs.getDate("admission_date").toLocalDate());
                }

                patient.setMobile(rs.getString("mobile"));
                patient.setTransferStatus(rs.getString("transfer_status"));

                if (rs.getTimestamp("processed_at") != null) {
                    patient.setProcessedAt(rs.getTimestamp("processed_at").toLocalDateTime());
                }

                if (rs.getTimestamp("created_at") != null) {
                    patient.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                }

                patients.add(patient);
            }
        }
        return patients;
    }

    @Override
    public boolean alreadyTransferred(int patientId, Connection con) throws SQLException {

        String criticalSql = "SELECT COUNT(*) FROM critical_care_patients " +
                "WHERE source_patient_id = ?";

        String generalSql = "SELECT COUNT(*) FROM general_care_patients " +
                "WHERE source_patient_id = ?";

        try (PreparedStatement ps1 = con.prepareStatement(criticalSql);
                PreparedStatement ps2 = con.prepareStatement(generalSql)) {

            ps1.setInt(1, patientId);
            ps2.setInt(1, patientId);

            try (ResultSet rs1 = ps1.executeQuery();
                    ResultSet rs2 = ps2.executeQuery()) {

                int criticalCount = 0;
                int generalCount = 0;

                if (rs1.next()) {
                    criticalCount = rs1.getInt(1);
                }

                if (rs2.next()) {
                    generalCount = rs2.getInt(1);
                }

                return criticalCount > 0 || generalCount > 0;
            }
        }
    }

    @Override
    public void insertCriticalPatient(
            Patient patient,
            Connection con) throws SQLException {

        String sql = "INSERT INTO critical_care_patients " +
                "(source_patient_id, patient_name, age, disease, " +
                "admission_type, condition_status, triage_score, doctor_name) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, patient.getPatientId());
            ps.setString(2, patient.getPatientName());
            ps.setInt(3, patient.getAge());
            ps.setString(4, patient.getDisease());
            ps.setString(5, patient.getAdmissionType());
            ps.setString(6, patient.getConditionStatus());
            ps.setInt(7, patient.getTriageScore());
            ps.setString(8, patient.getDoctorName());

            ps.executeUpdate();
        }
    }

    @Override
    public void insertGeneralPatient(Patient patient, Connection con) throws SQLException {
        String sql = "INSERT INTO general_care_patients " +
                "(source_patient_id, patient_name, age, disease, " +
                "admission_type, condition_status, triage_score, doctor_name) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, patient.getPatientId());
            ps.setString(2, patient.getPatientName());
            ps.setInt(3, patient.getAge());
            ps.setString(4, patient.getDisease());
            ps.setString(5, patient.getAdmissionType());
            ps.setString(6, patient.getConditionStatus());
            ps.setInt(7, patient.getTriageScore());
            ps.setString(8, patient.getDoctorName());

            ps.executeUpdate();
        }
    }

    @Override
    public void markProcessed(int patientId, Connection con) throws SQLException {
        String sql = "update hospital_patient_intake " +
                "set transfer_status = 'processed', " +
                "processed_at = now() " +
                "where patient_id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, patientId);

            ps.executeUpdate();
        }
    }
}
