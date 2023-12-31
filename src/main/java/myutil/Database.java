package myutil;

import email.EmailInformation;
import java.sql.*;
import java.util.ArrayList;

import myutil.PatientDetails;

/*
  In Database Singleton Design pattern used for reducing the instance of databases and their connection

 */
public class Database {

    private final String url = "jdbc:postgresql://localhost:5432/guru";
    private final String user = "postgres";
    private final String password = "root";

    private static final String SELECT_ALL_QUERY = "select * from pdetail";
    private static final String UPDATE_USERS_SQL = "update pdetail set username = ? where id = ?;";
    private static final String INSERT_RECORD_SQL = "INSERT INTO pdetail (pno, date, name, mno, gen, age, wht, bp, pls, pdis,email) VALUES (?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String GET_TOTAL_NO_OF_ROWS = "SELECT COUNT(NAME) FROM pdetail";
    private static final String GET_TOTAL_MONTH_PATIENT = "SELECT * FROM pdetail WHERE EXTRACT(MONTH FROM date::date) = EXTRACT(MONTH FROM CURRENT_DATE) AND EXTRACT(YEAR FROM date::date) = EXTRACT(YEAR FROM CURRENT_DATE);";
    private static final String GET_TOTAL_TODAY_PATIENT = "SELECT * FROM pdetail WHERE date::timestamp >= DATE_TRUNC('day', CURRENT_DATE);";
    private static final String GET_MEDI_PEDI = "SELECT* FROM pdetail,medi;";
    private static final String GET_MAX_INDEX = "SELECT MAX(pno) FROM pdetail;";
    private static final String INSERT_MEDECINE_INFO = "INSERT INTO medi (pno, pname, medicin, mqty, mtime, ba, qty) VALUES (?, ?, ?, ?, ?, ?, ?);";
    private static final String FIND_PATIENT_BY_PNO = "select * from pdetail where pno = ?";
    private static final String FIND_MEDICINE_BY_PNO = "select * from medi where pno = ?";
    private static final String DELETE_MEDICINE_BY_PNO = "delete  from medi where pno =?";
    private static final String UPDATE_PATIENT_DATE = "update pdetail set date = ? where pno = ?;";
    private static final String UPDATE_PATIENT_MOBILE_NO = "update pdetail set mno = ? where pno = ?;";
    private static final String DELETE_BOOKMARK_BY_NAME = "DELETE FROM bookmark WHERE bname=?";
    private static final String INSERT_BOOKMARK = "INSERT INTO bookmark (bname, medicin, mqty, mtime, ba, qty) VALUES (?,?,?,?,?,?);";

    private static final String UPDATE_PATIENT_FEES = "UPDATE pdetail SET  fees_paid =? WHERE pno = ?";

    private static final String INSERT_MEDICINE = "INSERT INTO medilist (medicine) VALUES (?);";
    
    private static final String INSERT_REPORT = "INSERT INTO patient_reports (patient_no, reports , report_date) VALUES (?,?,?);";
    private static final String DELETE_TEST_REPORT_BY_PNO  = "delete  from patient_reports where patient_no =?";
    private static final String GET_ALL_TEST_REPORTS ="SELECT *FROM patient_reports where patient_no=?";
    
    private static final String INSERT_REPORT_NAME = "INSERT INTO reports (report_name)VALUES (?);";
    private static final String INSERT_DOCTOR_NAME = "INSERT INTO doctor_names (doc_name) VALUES (?);";
    private static final String GET_ALL_DOCTOR_NAMES = "SELECT * FROM doctor_names";
    private static final String GET_DOCTOR_ID = "SELECT * FROM doctor_names where doc_name = ?";

    private static final String INSERT_INTO_EMAIL = "INSERT INTO email (email_from, email_to, subject, body, template) VALUES (?,?,?,?,?)";

    private static final String INSERT_INTO_EMAIL_TEMPLATE = "INSERT INTO email_template (template, subject, body, attach_file) VALUES (?, ?, ?, ?)";

    private static final String DELETE_EMPLATE_TEMPLATE_BY_NAME = "delete  from email_template where template =?";
    static Database singletone_database = null;
    Connection connection = null;

    //creates the database connection
    public Connection connect() {
        try {
            if (connection == null) {
                connection = DriverManager.getConnection(url, user, password);
                return connection;
            }
            return connection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    //give the instance of database 
    public static Database getInstance() {
        if (singletone_database == null) {
            singletone_database = new Database();
            return singletone_database;
        }
        return singletone_database;
    }

    //return the total number of patients data presnt in database
    public String getTotalNumberOfUser() {
        try {
            Connection conn = connect();
            PreparedStatement preparedStatement = conn.prepareStatement(GET_TOTAL_NO_OF_ROWS);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            return Integer.toString(rs.getInt(1));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    //return total monthly patinets
    public String getTotalMonthlyPatient() {
        try {
            Connection conn = connect();
            PreparedStatement preparedStatement = conn.prepareStatement(GET_TOTAL_MONTH_PATIENT);
            ResultSet rs = preparedStatement.executeQuery();
            int i = 0;
            while (rs.next()) {
                i++;
            }
            return Integer.toString(i);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //return total today patient
    public String getTotalTodayPatient() {
        try {
            Connection conn = connect();
            PreparedStatement preparedStatement = conn.prepareStatement(GET_TOTAL_TODAY_PATIENT);
            ResultSet rs = preparedStatement.executeQuery();
            int i = 0;
            while (rs.next()) {
                i++;
            }
            return Integer.toString(i);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<PatientDetails> getMonthlyPatient() {

        ArrayList<PatientDetails> patientDetailsList = new ArrayList<PatientDetails>();

        try {
            Connection conn = connect();
            PreparedStatement preparedStatement = conn.prepareStatement(GET_TOTAL_MONTH_PATIENT);
            ResultSet rs = preparedStatement.executeQuery();
            int i = 0;
            while (rs.next()) {
                PatientDetails patientdetails = new PatientDetails();
                patientdetails.setPid(rs.getInt("pno"));
                patientdetails.setName(rs.getString("name"));

                patientdetails.setGender(rs.getString("gen"));

                Date date = rs.getDate("date");
                patientdetails.setDate(date);

                String fees = rs.getString("fees_paid");
                if (fees != null) {
                    patientdetails.setFees(Float.parseFloat(fees));
                } else {
                    float f = 0.0f;
                    patientdetails.setFees(f);
                }

                patientDetailsList.add(patientdetails);
                i++;
            }
            return patientDetailsList;

        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public ArrayList<PatientDetails> getTodayPatient() {

        ArrayList<PatientDetails> patientDetailsList = new ArrayList<PatientDetails>();

        try {
            Connection conn = connect();
            PreparedStatement preparedStatement = conn.prepareStatement(GET_TOTAL_TODAY_PATIENT);
            ResultSet rs = preparedStatement.executeQuery();
            int i = 0;
            while (rs.next()) {
                PatientDetails patientdetails = new PatientDetails();
                patientdetails.setPid(rs.getInt("pno"));
                patientdetails.setName(rs.getString("name"));

                patientdetails.setGender(rs.getString("gen"));

                Date date = rs.getDate("date");
                patientdetails.setDate(date);

                String fees = rs.getString("fees_paid");
                if (fees != null) {
                    patientdetails.setFees(Float.parseFloat(fees));
                } else {
                    float f = 0.0f;
                    patientdetails.setFees(f);
                }

                patientDetailsList.add(patientdetails);
                i++;
            }
            return patientDetailsList;

        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public PatientDetails getPatientDetails(int patient_report_number) {
        try {
            // Step 1: Establishing a Connection
            Connection conn = connect();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = conn.prepareStatement(FIND_PATIENT_BY_PNO);
            preparedStatement.setInt(1, patient_report_number);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();
            // Step 4: Process the ResultSet object.
            PatientDetails patientdetails = null;
            while (rs.next()) {

                patientdetails = new PatientDetails();
                patientdetails.setPid(rs.getInt("pno"));
                patientdetails.setName(rs.getString("name"));
                patientdetails.setGender(rs.getString("gen"));
                patientdetails.setAge(rs.getString("age"));
                patientdetails.setPulse(rs.getString("pls"));
                patientdetails.setSymptoms(rs.getString("pdis"));
                patientdetails.setWeight(rs.getString("wht"));
                patientdetails.setMobileNo(rs.getString("mno"));
                patientdetails.setEmail(rs.getString("email"));

                String fees = rs.getString("fees_paid");
                if (fees != null) {
                    patientdetails.setFees(Float.parseFloat(fees));
                } else {
                    float f = 0.0f;
                    patientdetails.setFees(f);
                }
                Date date = rs.getDate("date");
                patientdetails.setDate(date);

            }
            return patientdetails;

        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;

    }

    public ArrayList<PatientDetails> getAllUsers() {

        ArrayList<PatientDetails> patientDetailsList = new ArrayList<PatientDetails>();

        try {
            // Step 1: Establishing a Connection
            Connection conn = connect();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = conn.prepareStatement(SELECT_ALL_QUERY);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();
            // Step 4: Process the ResultSet object.
            int i = 0;
            while (rs.next()) {

                PatientDetails patientdetails = new PatientDetails();
                patientdetails.setPid(rs.getInt("pno"));
                patientdetails.setName(rs.getString("name"));
                patientdetails.setGender(rs.getString("gen"));
                patientdetails.setAge(rs.getString("age"));
                patientdetails.setPulse(rs.getString("pls"));
                patientdetails.setSymptoms(rs.getString("pdis"));
                patientdetails.setWeight(rs.getString("wht"));
                patientdetails.setSugar(rs.getString("sugar"));

                Date date = rs.getDate("date");
                patientdetails.setDate(date);

                patientDetailsList.add(patientdetails);
                i++;
            }
            return patientDetailsList;

        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public void updateRecord() {

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_USERS_SQL);
            preparedStatement.setString(1, "Ram");
            preparedStatement.setInt(2, 3);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updatePatientDate(PatientDetails patientdetails) {

        try {
            Connection conn = connect();
            PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_PATIENT_DATE);

            preparedStatement.setDate(1, new Date(patientdetails.getDate().getTime()));
            preparedStatement.setInt(2, patientdetails.getPid());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);

        }
    }

    public void updatePatientMobileNo(PatientDetails patientdetails) {

        try {
            Connection conn = connect();
            PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_PATIENT_MOBILE_NO);

            preparedStatement.setString(1, patientdetails.getMobileNo());
            preparedStatement.setInt(2, patientdetails.getPid());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);

        }
    }

    public boolean updatePatientFees(int pno, float fees) {

        try {
            Connection conn = connect();
            PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_PATIENT_FEES);

            preparedStatement.setFloat(1, fees);
            preparedStatement.setInt(2, pno);

            int a = preparedStatement.executeUpdate();
            if (a > 0) {
                return true;
            }
            return false;

        } catch (SQLException e) {

            System.out.println(e);
            return false;

        }
    }

    public PatientDetails getRecord(String username) {
        final String GET_PAITENT_HAVING_USERNAME = "select * from pdetail where name = \'" + username + "\'";
        try {
            Connection connection = connect();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_PAITENT_HAVING_USERNAME);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {

                PatientDetails patientdetails = new PatientDetails();
                return patientdetails;

            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    //This is requird for maintaing the patient number as id 
    public int getMaxIndex() {
        try {
            Connection conn = connect();
            PreparedStatement preparedStatement = conn.prepareStatement(GET_MAX_INDEX);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            int i = rs.getInt(1);
            return i;

        } catch (SQLException e) {
            System.out.println(e);
        }
        return -1;
    }

    public int insertRecord(PatientDetails patientdetails) {
        int id = -1;
        try {
            Connection conn = connect();
            PreparedStatement preparedStatement = conn.prepareStatement(INSERT_RECORD_SQL);
            id = getMaxIndex() + 1;
            preparedStatement.setInt(1, id);

            preparedStatement.setDate(2, new Date(patientdetails.getDate().getTime()));
            preparedStatement.setString(3, patientdetails.getName().toUpperCase());
            preparedStatement.setString(4, patientdetails.getMobileNo());
            preparedStatement.setString(5, patientdetails.getGender());
            preparedStatement.setInt(6, patientdetails.getAge());
            preparedStatement.setInt(7, patientdetails.getWeight());
            preparedStatement.setString(8, patientdetails.getBloodPressure());
            preparedStatement.setString(9, patientdetails.getPulse());

            preparedStatement.setString(10, patientdetails.getSymptoms());
            preparedStatement.setString(11, patientdetails.getEmail());

            preparedStatement.executeUpdate();
            //conn.commit();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return id;
    }

    public void insertRecordInMedicine(MedicineDetails medicineDetails) {
        try {
            Connection conn = connect();
            PreparedStatement preparedStatement = conn.prepareStatement(INSERT_MEDECINE_INFO);
            PatientDetails patientDetails = medicineDetails.getPatientDetails();
            preparedStatement.setInt(1, patientDetails.getPid());

            preparedStatement.setString(2, patientDetails.getName());
            preparedStatement.setString(3, medicineDetails.getMedicineName().toUpperCase());
            preparedStatement.setString(4, medicineDetails.getMedicineQuantity());
            preparedStatement.setString(5, medicineDetails.getMedicineTime());
            preparedStatement.setInt(6, medicineDetails.getMedicineMealTime());
            preparedStatement.setInt(7, Integer.parseInt(medicineDetails.getTotalQuantity()));

            preparedStatement.executeUpdate();
            //conn.commit();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void insertMedicine(String medicine_name) {
        try {
            Connection conn = connect();
            PreparedStatement preparedStatement = conn.prepareStatement(INSERT_MEDICINE);
            preparedStatement.setString(1, medicine_name.toUpperCase());

            preparedStatement.executeUpdate();
            //conn.commit();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //inserting test reports 
    public void insertTestReport(int pno, String report_name, long date_in_time) {
        try {
            Connection conn = connect();
            PreparedStatement preparedStatement = conn.prepareStatement(INSERT_REPORT);
            preparedStatement.setInt(1, pno);
            preparedStatement.setString(2, report_name.toUpperCase());
            preparedStatement.setDate(3, new Date(date_in_time));

            preparedStatement.executeUpdate();
            //conn.commit();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void insertNewTestReportName(String report_name) {
        try {
            Connection conn = connect();
            PreparedStatement preparedStatement = conn.prepareStatement(INSERT_REPORT_NAME);
            preparedStatement.setString(1, report_name.toUpperCase());

            preparedStatement.executeUpdate();
            //conn.commit();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void removeALlTestReport(int patient_number) {
        try {
            Connection conn = connect();
            PreparedStatement preparedStatement = conn.prepareStatement(DELETE_TEST_REPORT_BY_PNO);
            preparedStatement.setInt(1, patient_number);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public ArrayList<String> getLikeMedicine(String str) {

        ArrayList<String> medi = new ArrayList<String>();

        try {
            Connection conn = connect();
            StringBuffer GET_LIKE_MEDICINE = new StringBuffer("SELECT * FROM medilist WHERE medicine LIKE ");
            GET_LIKE_MEDICINE.append("\'");
            GET_LIKE_MEDICINE.append("%");
            GET_LIKE_MEDICINE.append(new StringBuffer(str.toUpperCase()));
            GET_LIKE_MEDICINE.append("%';");

            PreparedStatement preparedStatement = conn.prepareStatement(new String(GET_LIKE_MEDICINE));
            ResultSet rs = preparedStatement.executeQuery();
            int i = 0;
            while (rs.next()) {
                medi.add(rs.getString("medicine"));
                i++;
            }
            return medi;

        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public ArrayList<String> getLikePatient(String str) {

        ArrayList<String> patient_details = new ArrayList<>();

        try {
            Connection conn = connect();
            StringBuffer GET_LIKE_PATIENT = new StringBuffer("SELECT * FROM pdetail WHERE name LIKE ");
            GET_LIKE_PATIENT.append("\'");
            GET_LIKE_PATIENT.append("%");
            GET_LIKE_PATIENT.append(new StringBuffer(str.toUpperCase()));
            GET_LIKE_PATIENT.append("%';");

            PreparedStatement preparedStatement = conn.prepareStatement(new String(GET_LIKE_PATIENT));
            ResultSet rs = preparedStatement.executeQuery();
            int i = 0;
            while (rs.next()) {
                String info = rs.getString("pno") + "/" + rs.getString("name") + "/" + (rs.getString("date").split(" "))[0];

                patient_details.add(info);
                i++;
            }
            return patient_details;

        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public ArrayList<String> getLikePatient(int patient_number) {

        ArrayList<String> patient_details = new ArrayList<>();

        try {
            Connection conn = connect();
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM pdetail WHERE pno=?");
            preparedStatement.setInt(1, patient_number);
            ResultSet rs = preparedStatement.executeQuery();
            int i = 0;
            while (rs.next()) {
                String info = rs.getString("pno") + "/" + rs.getString("name") + "/" + (rs.getString("date").split(" "))[0];

                patient_details.add(info);
                i++;
            }
            return patient_details;

        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public ArrayList<String> getLikePatientByMobileNo(String mobile_number) {

        ArrayList<String> patient_details = new ArrayList<>();

        try {
            Connection conn = connect();
            StringBuffer GET_LIKE_PATIENT = new StringBuffer("SELECT * FROM pdetail WHERE mno LIKE ");
            GET_LIKE_PATIENT.append("\'");
            GET_LIKE_PATIENT.append("%");
            GET_LIKE_PATIENT.append(mobile_number);
            GET_LIKE_PATIENT.append("%';");

            PreparedStatement preparedStatement = conn.prepareStatement(new String(GET_LIKE_PATIENT));
            ResultSet rs = preparedStatement.executeQuery();
            int i = 0;
            while (rs.next()) {
                String info = rs.getString("pno") + "/" + rs.getString("name") + "/" + (rs.getString("date").split(" "))[0];

                patient_details.add(info);
                i++;
            }
            return patient_details;

        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public ArrayList<String> getLikePatientByGender(String gender) {

        ArrayList<String> patient_details = new ArrayList<>();

        try {
            Connection conn = connect();
            StringBuffer GET_LIKE_PATIENT = new StringBuffer("SELECT * FROM pdetail WHERE gen LIKE ");
            GET_LIKE_PATIENT.append("\'");
            GET_LIKE_PATIENT.append("%");
            GET_LIKE_PATIENT.append(gender);
            GET_LIKE_PATIENT.append("%';");

            PreparedStatement preparedStatement = conn.prepareStatement(new String(GET_LIKE_PATIENT));
            ResultSet rs = preparedStatement.executeQuery();
            int i = 0;
            while (rs.next()) {
                String info = rs.getString("pno") + "/" + rs.getString("name") + "/" + (rs.getString("date").split(" "))[0];

                patient_details.add(info);
                i++;
            }
            return patient_details;

        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public ArrayList<String> getLikePatientByDate(long date_in_time_format) {

        ArrayList<String> patient_details = new ArrayList<>();

        try {
            Connection conn = connect();
            Date date = new Date(date_in_time_format);
            StringBuffer GET_LIKE_PATIENT = new StringBuffer("SELECT * FROM pdetail WHERE date LIKE ");
            GET_LIKE_PATIENT.append("\'");
            GET_LIKE_PATIENT.append("%");
            GET_LIKE_PATIENT.append(date.toString());
            GET_LIKE_PATIENT.append("%';");

            PreparedStatement preparedStatement = conn.prepareStatement(new String(GET_LIKE_PATIENT));
            ResultSet rs = preparedStatement.executeQuery();
            int i = 0;
            while (rs.next()) {
                String info = rs.getString("pno") + "/" + rs.getString("name") + "/" + (rs.getString("date").split(" "))[0];

                patient_details.add(info);
                i++;
            }
            return patient_details;

        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public ArrayList<String> getAllMedicine() {

        ArrayList<String> medi = new ArrayList<String>();

        try {
            Connection conn = connect();
            StringBuffer GET_LIKE_MEDICINE = new StringBuffer("SELECT * FROM medilist");
            PreparedStatement preparedStatement = conn.prepareStatement(new String(GET_LIKE_MEDICINE));
            ResultSet rs = preparedStatement.executeQuery();
            int i = 0;
            while (rs.next()) {
                medi.add(rs.getString("medicine"));
                i++;
            }
            return medi;

        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public ArrayList<String> getLikeReport(String str) {

        ArrayList<String> report = new ArrayList<String>();

        try {
            Connection conn = connect();
            StringBuffer GET_LIKE_REPORT = new StringBuffer("SELECT * FROM reports WHERE report_name LIKE ");
            GET_LIKE_REPORT.append("\'");
            GET_LIKE_REPORT.append("%");
            GET_LIKE_REPORT.append(new StringBuffer(str.toUpperCase()));
            GET_LIKE_REPORT.append("%';");

            PreparedStatement preparedStatement = conn.prepareStatement(new String(GET_LIKE_REPORT));
            ResultSet rs = preparedStatement.executeQuery();
            int i = 0;
            while (rs.next()) {
                report.add(rs.getString("report_name"));
                i++;
            }
            return report;

        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public void getMediPedi() {
        try {
            Connection conn = connect();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = conn.prepareStatement(GET_MEDI_PEDI);

            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();
            // Step 4: Process the ResultSet object.
            int i = 0;
            while (rs.next()) {

                PatientDetails patientdetails = new PatientDetails();
                patientdetails.setPid(rs.getInt("pno"));
                patientdetails.setName(rs.getString("name"));
                patientdetails.setGender(rs.getString("gen"));
                patientdetails.setAge(rs.getString("age"));
                patientdetails.setPulse(rs.getString("pls"));
                patientdetails.setSymptoms(rs.getString("pdis"));
                patientdetails.setWeight(rs.getString("wht"));

                Date date = rs.getDate("date");
                patientdetails.setDate(date);

                i++;

            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public ArrayList<MedicineDetails> getMedicineList(int patient_number) {

        ArrayList<MedicineDetails> medicineDetailsList = new ArrayList<MedicineDetails>();

        try {
            Connection conn = connect();
            PreparedStatement preparedStatement = conn.prepareStatement(FIND_MEDICINE_BY_PNO);
            preparedStatement.setInt(1, patient_number);

            ResultSet rs = preparedStatement.executeQuery();
            int i = 0;
            while (rs.next()) {
                MedicineDetails medicineDetails = new MedicineDetails();
                medicineDetails.setMedicineName(rs.getString("medicin"));
                medicineDetails.setMedicineQuantity(rs.getString("mqty"));
                String before = rs.getString("ba");
                if (before.equalsIgnoreCase("1")) {
                    medicineDetails.setMedicineMealTime(true);
                } else {
                    medicineDetails.setMedicineMealTime(false);
                }
                String doses_time = rs.getString("mtime");
                boolean morning_status = false;
                boolean afternoon_status = false;
                boolean evening_status = false;
                if (doses_time.charAt(0) == '1') {
                    morning_status = true;
                }
                if (doses_time.charAt(3) == '1') {
                    afternoon_status = true;
                }
                if (doses_time.charAt(6) == '1') {
                    evening_status = true;
                }

                medicineDetails.setPatientDetails(getPatientDetails(patient_number));
                medicineDetails.setMedicineTime(morning_status, afternoon_status, evening_status);
                medicineDetails.setTotalQuantity(rs.getString("qty"));
                medicineDetailsList.add(medicineDetails);

                i++;
            }
            return medicineDetailsList;

        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public void removeAllMedicinesOf(int patient_number) {

        try {
            Connection conn = connect();
            PreparedStatement preparedStatement = conn.prepareStatement(DELETE_MEDICINE_BY_PNO);
            preparedStatement.setInt(1, patient_number);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public ArrayList<String> getBookmark() {

        ArrayList<String> medi = new ArrayList<String>();

        try {
            Connection conn = connect();
            StringBuffer GET_LIKE_MEDICINE = new StringBuffer("SELECT DISTINCT bname FROM bookmark;");

            PreparedStatement preparedStatement = conn.prepareStatement(new String(GET_LIKE_MEDICINE));
            ResultSet rs = preparedStatement.executeQuery();
            int i = 0;
            while (rs.next()) {
                medi.add(rs.getString("bname").toUpperCase());
                i++;
            }
            return medi;

        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public ReportInfomartion getAllTestReports(int patient_number) {

        ReportInfomartion test_report = new ReportInfomartion();

        try {
            Connection conn = connect();

            PreparedStatement preparedStatement = conn.prepareStatement(GET_ALL_TEST_REPORTS);
            preparedStatement.setInt(1, patient_number);

            ResultSet rs = preparedStatement.executeQuery();
            //rs.getDate()
            test_report.setPatientNumber(patient_number);
            int i = 0;
            while (rs.next()) {

                test_report.setReportName(rs.getString("reports"));
                test_report.setDate(rs.getDate("report_date"));
                i++;
            }
            if (i == 0) {
                return null;
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return test_report;
    }

    public ArrayList<String> getLikeBookmarkMedicine(String str) {

        ArrayList<String> medi = new ArrayList<String>();

        try {
            Connection conn = connect();
            StringBuffer GET_LIKE_MEDICINE = new StringBuffer("SELECT * FROM bookmark WHERE bname =");
            GET_LIKE_MEDICINE.append("\'");
            GET_LIKE_MEDICINE.append(new StringBuffer(str.toUpperCase()));
            GET_LIKE_MEDICINE.append("\';");

            PreparedStatement preparedStatement = conn.prepareStatement(new String(GET_LIKE_MEDICINE));
            ResultSet rs = preparedStatement.executeQuery();
            int i = 0;
            while (rs.next()) {
                medi.add(rs.getString("medicin"));
                i++;
            }
            return medi;

        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public ArrayList<String> getLikeBookmark(String str) {

        ArrayList<String> medi = new ArrayList<String>();

        try {
            Connection conn = connect();
            StringBuffer GET_LIKE_BOOKMARK = new StringBuffer("SELECT  DISTINCT  bname FROM bookmark WHERE bname LIKE ");

            GET_LIKE_BOOKMARK.append("\'");
            GET_LIKE_BOOKMARK.append("%");
            GET_LIKE_BOOKMARK.append(new StringBuffer(str.toUpperCase()));
            GET_LIKE_BOOKMARK.append("%';");
            PreparedStatement preparedStatement = conn.prepareStatement(new String(GET_LIKE_BOOKMARK));
            ResultSet rs = preparedStatement.executeQuery();
            int i = 0;
            while (rs.next()) {
                medi.add(rs.getString("bname").toUpperCase());
                i++;
            }
            return medi;

        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public void removeBookmark(String name) {

        try {
            Connection conn = connect();
            PreparedStatement preparedStatement = conn.prepareStatement(DELETE_BOOKMARK_BY_NAME);
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public void addBookmark(String bookmark_name, MedicineDetails medicineDetails) {
        try {
            Connection conn = connect();
            PreparedStatement preparedStatement = conn.prepareStatement(INSERT_BOOKMARK);

            preparedStatement.setString(1, bookmark_name.toUpperCase());
            preparedStatement.setString(2, medicineDetails.getMedicineName());
            preparedStatement.setString(3, medicineDetails.getMedicineQuantity());
            preparedStatement.setString(4, medicineDetails.getMedicineTime());
            preparedStatement.setInt(5, medicineDetails.getMedicineMealTime());
            
            preparedStatement.setInt(6, Integer.parseInt(medicineDetails.getTotalQuantity()));

            preparedStatement.executeUpdate();
            //conn.commit();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

//    doctor related 
    public void insertDoctorName(String doctor_name) {
        try {
            Connection conn = connect();
            PreparedStatement preparedStatement = conn.prepareStatement(INSERT_DOCTOR_NAME);
            preparedStatement.setString(1, doctor_name.toUpperCase());
            
            preparedStatement.executeUpdate();
            //conn.commit();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public ArrayList<String> getAllDoctorNames(String str) {

        ArrayList<String> medi = new ArrayList<String>();

        try {
            Connection conn = connect();
            StringBuffer GET_LIKE_DOCTOR = new StringBuffer("SELECT  DISTINCT  doc_name FROM doctor_names WHERE doc_name LIKE ");

            GET_LIKE_DOCTOR.append("\'");
            GET_LIKE_DOCTOR.append("%");
            GET_LIKE_DOCTOR.append(new StringBuffer(str.toUpperCase()));
            GET_LIKE_DOCTOR.append("%';");
            PreparedStatement preparedStatement = conn.prepareStatement(new String(GET_LIKE_DOCTOR));
            ResultSet rs = preparedStatement.executeQuery();
            int i = 0;
            while (rs.next()) {
                medi.add(rs.getString("doc_name").toUpperCase());
                i++;
            }
            return medi;

        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public int getDoctorID(String doctor_name) {
        try {
            Connection conn = connect();
            PreparedStatement preparedStatement = conn.prepareStatement(GET_DOCTOR_ID);
            preparedStatement.setString(1, doctor_name.toUpperCase());
            ResultSet rs = preparedStatement.executeQuery();
            if(rs==null)
            {
                            System.out.println("rs next");
            }
           // System.out.println(rs.getString("doc_name"));
           rs.next();
            System.out.println(rs.getString("doc_name"));
            return Integer.parseInt(rs.getString("doc_id"));
            //return 1;
            
        } catch (SQLException e) {
            System.out.println(e);
        }
        return -1;
    }

    //Email Related operations
    public void insertEmail(EmailInformation emailInformation) {

        try {
            Connection conn = connect();
            PreparedStatement preparedStatement = conn.prepareStatement(INSERT_INTO_EMAIL);

            preparedStatement.setString(1, emailInformation.getSendFrom());

            preparedStatement.setString(2, emailInformation.getSendTo());
            preparedStatement.setString(3, emailInformation.getSubject());
            preparedStatement.setString(4, emailInformation.getBody());
            preparedStatement.setString(5, emailInformation.getTemplate());

            preparedStatement.executeUpdate();
            //conn.commit();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void insertEmailTemplate(EmailInformation emailInformation) {

        try {
            Connection conn = connect();
            PreparedStatement preparedStatement = conn.prepareStatement(INSERT_INTO_EMAIL_TEMPLATE);

            preparedStatement.setString(1, emailInformation.getTemplate().toUpperCase());
            preparedStatement.setString(2, emailInformation.getSubject());
            preparedStatement.setString(3, emailInformation.getBody());
            preparedStatement.setString(4, emailInformation.getAttachFilePath());

            preparedStatement.executeUpdate();
            //conn.commit();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public ArrayList<String> getLikeTemplate(String template_name) {

        ArrayList<String> template = new ArrayList<String>();

        try {
            Connection conn = connect();
            StringBuffer GET_LIKE_EMAIL = new StringBuffer("SELECT  DISTINCT  subject ,template FROM email_template WHERE template LIKE ");

            GET_LIKE_EMAIL.append("\'");
            GET_LIKE_EMAIL.append("%");
            GET_LIKE_EMAIL.append(new StringBuffer(template_name.toUpperCase()));
            GET_LIKE_EMAIL.append("%';");
            PreparedStatement preparedStatement = conn.prepareStatement(new String(GET_LIKE_EMAIL));
            ResultSet rs = preparedStatement.executeQuery();
            int i = 0;
            while (rs.next()) {
                //String str =rs.getString("template").toUpperCase()+" "+rs.getString("subject").toUpperCase();
             ///  System.out.println(rs.getString("template").toUpperCase());
                template.add(rs.getString("template").toUpperCase());
              // template.add(str);
                i++;
            }
            return template;

        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public EmailInformation getEmail(String template_name) {

        try {
            Connection conn = connect();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM email_template where template = ?");
            preparedStatement.setString(1, template_name);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();
            // Step 4: Process the ResultSet object.

            while (rs.next()) {

                EmailInformation emailInformation = new EmailInformation();
                emailInformation.setTemplate(rs.getString("template"));
                emailInformation.setSubject(rs.getString("subject"));
                emailInformation.setBody(rs.getString("body"));
                emailInformation.setAttachFilePath(rs.getString("attach_file"));
                return emailInformation;
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    public boolean removeTemplate(String template_name) {
        try {
            
            Connection conn = connect();
            PreparedStatement preparedStatement = conn.prepareStatement(DELETE_EMPLATE_TEMPLATE_BY_NAME);
            preparedStatement.setString(1, template_name);

            preparedStatement.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println(e);
             return false;
        }
    }

    public String getEmail(int patient_number) {
        return getPatientDetails(patient_number).getEmail();
    }

}
