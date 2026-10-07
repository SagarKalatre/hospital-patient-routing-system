package com.kiranacademy.hospital;

import java.sql.SQLException;

public class HospitalRoutingApp {
    public static void main(String[] args) throws SQLException {
        PatientRoutingService service = new PatientRoutingService();

        service.processPatients();
    }
}
