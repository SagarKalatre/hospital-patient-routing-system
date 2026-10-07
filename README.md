# Hospital Patient Triage & Routing System

## Project Overview

The Hospital Patient Triage & Routing System is a Core Java and JDBC based console application developed for Kiran Academy.

The system reads patient intake records from MySQL, validates the patient information, determines the patient's triage category, and routes the patient to either Critical Care or General Care.

The application uses JDBC, PreparedStatement, transactions, validation, duplicate checking, and MySQL.

## Technologies Used

* Java
* JDBC
* MySQL
* Maven
* MySQL Workbench
* PreparedStatement
* Transaction Management

## Project Structure

```text
src/main/java/com/kiranacademy/hospital/
│
├── HospitalRoutingApp.java
├── DBConnection.java
├── Patient.java
├── PatientDAO.java
├── PatientDAOIMP.java
├── PatientRoutingService.java
└── PatientValidator.java

sql/
├── 01_database.sql
└── 02_sample_data.sql
```

## Database

Database name:

```text
hospital_routing_db
```

Main tables:

```text
hospital_patient_intake
critical_care_patients
general_care_patients
```

## Routing Rules

A patient is routed to Critical Care when:

1. Condition status is Critical.
2. Admission type is Emergency and triage score is 7 or higher.
3. Condition status is Moderate and triage score is 8 or higher.

All other valid patients are routed to General Care.

Critical routing has precedence over the other conditions.

## Validation

The application validates:

* Patient name
* Age
* Gender
* Disease
* Admission type
* Condition status
* Triage score
* Doctor name
* Admission date
* Mobile number
* Transfer status

Invalid patients are not inserted into destination tables.

## Transaction Handling

The application processes patients using JDBC transactions.

For a valid patient:

```text
Read Patient
     ↓
Validate Patient
     ↓
Check Duplicate
     ↓
Determine Route
     ↓
Insert into Destination Table
     ↓
Update Source Patient Status
     ↓
Commit Transaction
```

If the destination insert or source update fails, the transaction is rolled back.

## Duplicate Handling

The application checks whether a patient has already been transferred to either:

```text
critical_care_patients
```

or

```text
general_care_patients
```

Duplicate patients are not inserted again.

## How to Run

### 1. Create the Database

Open MySQL Workbench and run:

```text
sql/01_database.sql
```

### 2. Insert Sample Data

Run:

```text
sql/02_sample_data.sql
```

### 3. Configure MySQL Password

Open:

```text
src/main/java/com/kiranacademy/hospital/DBConnection.java
```

Update the MySQL password:

```java
private static final String PASSWORD = "YOUR_MYSQL_PASSWORD";
```

Replace it with your MySQL password.

### 4. Compile the Project

Open the terminal in the project directory and run:

```bash
mvn clean compile
```

### 5. Run the Application

```bash
mvn exec:java -Dexec.mainClass=com.kiranacademy.hospital.HospitalRoutingApp
```

## Expected Result

The sample data contains 20 patients.

Expected routing:

```text
Total Patients : 20
Critical Care  : 10
General Care   : 10
```

After successful processing, the source patient records are marked as:

```text
PROCESSED
```

and the `processed_at` field is updated.

## Testing Performed

The project was tested for:

* Successful patient processing
* Critical Care routing
* General Care routing
* Invalid patient validation
* Duplicate patient detection
* Transaction rollback
* Source status update
* Successful transaction commit

## Author

Sagar Kalatre

## Project Type

Industrial Core Java + JDBC Capstone Project

## Company

Kiran Academy
