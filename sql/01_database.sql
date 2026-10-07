CREATE DATABASE IF NOT EXISTS hospital_routing_db;

USE hospital_routing_db;

CREATE TABLE hospital_patient_intake (
    patient_id INT PRIMARY KEY AUTO_INCREMENT,
    patient_name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    gender VARCHAR(10) NOT NULL,
    disease VARCHAR(120) NOT NULL,
    admission_type VARCHAR(20) NOT NULL,
    condition_status VARCHAR(20) NOT NULL,
    triage_score INT NOT NULL,
    doctor_name VARCHAR(100) NOT NULL,
    admission_date DATE NOT NULL,
    mobile VARCHAR(15) NOT NULL,
    transfer_status VARCHAR(20) DEFAULT 'PENDING',
    processed_at DATETIME NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE critical_care_patients (
    critical_id INT PRIMARY KEY AUTO_INCREMENT,
    source_patient_id INT NOT NULL UNIQUE,
    patient_name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    disease VARCHAR(120) NOT NULL,
    admission_type VARCHAR(20) NOT NULL,
    condition_status VARCHAR(20) NOT NULL,
    triage_score INT NOT NULL,
    doctor_name VARCHAR(100) NOT NULL,
    routed_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE general_care_patients (
    general_id INT PRIMARY KEY AUTO_INCREMENT,
    source_patient_id INT NOT NULL UNIQUE,
    patient_name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    disease VARCHAR(120) NOT NULL,
    admission_type VARCHAR(20) NOT NULL,
    condition_status VARCHAR(20) NOT NULL,
    triage_score INT NOT NULL,
    doctor_name VARCHAR(100) NOT NULL,
    routed_at DATETIME DEFAULT CURRENT_TIMESTAMP
);