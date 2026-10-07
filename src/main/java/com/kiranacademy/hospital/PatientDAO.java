package com.kiranacademy.hospital;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface PatientDAO {
    public List<Patient>  getPendingPatients(Connection con) throws SQLException;

    public boolean alreadyTransferred(int patientId, Connection con) throws SQLException;

    public void insertCriticalPatient(Patient patient, Connection con) throws SQLException;

    public void insertGeneralPatient(Patient patient, Connection con) throws  SQLException;

    public void markProcessed(int patientId, Connection con) throws SQLException;
}
