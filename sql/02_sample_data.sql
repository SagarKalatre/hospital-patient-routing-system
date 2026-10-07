USE hospital_routing_db;

INSERT INTO hospital_patient_intake
(patient_name, age, gender, disease, admission_type, condition_status, triage_score, doctor_name, admission_date, mobile, transfer_status)
VALUES
('Rahul Patil', 35, 'Male', 'Fever', 'Regular', 'Stable', 4, 'Dr. Mehta', '2026-09-25', '9876543210', 'PENDING'),
('Priya Shah', 42, 'Female', 'Heart Attack', 'Emergency', 'Critical', 10, 'Dr. Sharma', '2026-09-25', '9876543211', 'PENDING'),
('Amit Jadhav', 58, 'Male', 'Stroke', 'Emergency', 'Critical', 9, 'Dr. Kulkarni', '2026-09-26', '9876543212', 'PENDING'),
('Sneha More', 29, 'Female', 'Severe Asthma', 'Emergency', 'Critical', 8, 'Dr. Patil', '2026-09-26', '9876543213', 'PENDING'),
('Raj Kumar', 45, 'Male', 'Diabetes', 'Regular', 'Stable', 5, 'Dr. Joshi', '2026-09-27', '9876543214', 'PENDING'),
('Neha Patil', 31, 'Female', 'Migraine', 'Regular', 'Moderate', 6, 'Dr. Mehta', '2026-09-27', '9876543215', 'PENDING'),
('Vikas Kulkarni', 67, 'Male', 'Chest Pain', 'Emergency', 'Stable', 9, 'Dr. Sharma', '2026-09-28', '9876543216', 'PENDING'),
('Pooja Deshmukh', 26, 'Female', 'Fever', 'Regular', 'Stable', 3, 'Dr. Patil', '2026-09-28', '9876543217', 'PENDING'),
('Rohan Shinde', 51, 'Male', 'Pneumonia', 'Emergency', 'Moderate', 8, 'Dr. Kulkarni', '2026-09-29', '9876543218', 'PENDING'),
('Kavita Joshi', 38, 'Female', 'Thyroid', 'Regular', 'Stable', 4, 'Dr. Joshi', '2026-09-29', '9876543219', 'PENDING'),
('Suresh Pawar', 72, 'Male', 'Cardiac Arrest', 'Emergency', 'Critical', 10, 'Dr. Sharma', '2026-09-30', '9876543220', 'PENDING'),
('Anjali More', 33, 'Female', 'Back Pain', 'Regular', 'Stable', 4, 'Dr. Mehta', '2026-09-30', '9876543221', 'PENDING'),
('Nitin Kale', 61, 'Male', 'Stroke', 'Emergency', 'Moderate', 8, 'Dr. Kulkarni', '2026-10-01', '9876543222', 'PENDING'),
('Swati Pawar', 27, 'Female', 'Cold', 'Regular', 'Stable', 2, 'Dr. Patil', '2026-10-01', '9876543223', 'PENDING'),
('Mahesh Shinde', 49, 'Male', 'Severe Infection', 'Emergency', 'Critical', 9, 'Dr. Sharma', '2026-10-02', '9876543224', 'PENDING'),
('Aarti Kulkarni', 24, 'Female', 'Headache', 'Regular', 'Stable', 3, 'Dr. Joshi', '2026-10-02', '9876543225', 'PENDING'),
('Deepak Joshi', 55, 'Male', 'Breathing Problem', 'Emergency', 'Moderate', 8, 'Dr. Patil', '2026-10-03', '9876543226', 'PENDING'),
('Meena Patil', 40, 'Female', 'Gastritis', 'Regular', 'Stable', 4, 'Dr. Mehta', '2026-10-03', '9876543227', 'PENDING'),
('Akash More', 63, 'Male', 'Heart Disease', 'Emergency', 'Critical', 10, 'Dr. Sharma', '2026-10-04', '9876543228', 'PENDING'),
('Riya Deshmukh', 30, 'Female', 'Allergy', 'Regular', 'Stable', 3, 'Dr. Patil', '2026-10-04', '9876543229', 'PENDING');