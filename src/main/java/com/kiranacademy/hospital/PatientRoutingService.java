package com.kiranacademy.hospital;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PatientRoutingService {
    private PatientDAO patientDAO;
    private PatientValidator patientValidator;

    public PatientRoutingService() {
        patientDAO = new PatientDAOIMP();
        patientValidator = new PatientValidator();
    }

    public void processPatients() throws SQLException {
        try (Connection con = DBConnection.getConnection()) {
            con.setAutoCommit(false);

            List<Patient> patients = patientDAO.getPendingPatients(con);

            System.out.println("---------------------------------------");
            System.out.println("Pending Patients : " + patients.size());
            System.out.println();

            int processed = 0;
            int critical = 0;
            int general = 0;
            int invalid = 0;
            int duplicate = 0;
            int failed = 0;

            for (Patient patient : patients) {
                System.out.println("Patient Id   : " + patient.getPatientId());
                System.out.println("Patient Name : " + patient.getPatientName());
                System.out.println("Admission Type = [" + patient.getAdmissionType() + "]");
                System.out.println("Transfer Status = [" + patient.getTransferStatus() + "]");
                try {
                    List<String> errors = patientValidator.validatePatient(patient);

                    if (!errors.isEmpty()) {
                        invalid++;

                        System.out.println("Status : INVALID");

                        for (String error : errors) {
                            System.out.println("Reason : " + error);
                        }

                        continue;
                    }
                    boolean alreadyTransferred = patientDAO.alreadyTransferred(patient.getPatientId(), con);

                    if (alreadyTransferred) {
                        duplicate++;

                        System.out.println("Status : DUPLICATE");
                        System.out.println("Reason : Patient already transferred");
                        continue;
                    }
                    if (isCritical(patient)) {
                        patientDAO.insertCriticalPatient(patient, con);

                        patientDAO.markProcessed(patient.getPatientId(), con);

                        con.commit();

                        critical++;
                        processed++;

                        System.out.println("Route  : CRITICAL CARE");
                        System.out.println("Status : PROCESSED");
                    } else {
                        patientDAO.insertGeneralPatient(patient, con);
                        patientDAO.markProcessed(patient.getPatientId(), con);
                        con.commit();

                        general++;
                        processed++;

                        System.out.println("Route  : GENERAL CARE");
                        System.out.println("Status : PROCESSED");
                    }
                } catch (SQLException e) {
                    con.rollback();
                    failed++;

                    System.out.println("Status : FAILED");
                    System.out.println("Reason : " + e.getMessage());
                    System.out.println("Transaction rolled back");
                }
            }
            con.setAutoCommit(true);

            System.out.println();
            System.out.println("========================================");
            System.out.println("          PROCESSING SUMMARY");
            System.out.println("========================================");
            System.out.println("Total Pending : " + patients.size());
            System.out.println("Processed     : " + processed);
            System.out.println("Critical Care : " + critical);
            System.out.println("General Care  : " + general);
            System.out.println("Invalid       : " + invalid);
            System.out.println("Duplicate     : " + duplicate);
            System.out.println("Failed        : " + failed);
            System.out.println("========================================");

        } catch (SQLException e) {
            System.out.println("Database connection error : " + e.getMessage());
        }
    }

    private boolean isCritical(Patient patient) {
        if (patient.getConditionStatus().equalsIgnoreCase("Critical")) {
            return true;
        }

        if (patient.getAdmissionType().equalsIgnoreCase("Emergency") && patient.getTriageScore() >= 7) {
            return true;
        }

        if (patient.getConditionStatus().equalsIgnoreCase("Moderate") && patient.getTriageScore() >= 8) {
            return true;
        }
        return false;
    }
}
