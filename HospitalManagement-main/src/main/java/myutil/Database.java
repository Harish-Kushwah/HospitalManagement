package myutil;

import java.sql.*;
import java.util.ArrayList;

import myutil.PatientDetails;
/*
In Database Singletone Design pattern used for reducing the instance of databases and their connection

*/
public class Database {

    private final String url = "jdbc:mysql://localhost/guru";
    private final String user = "root";
    private final String password = "";

    private static final String SELECT_ALL_QUERY = "select * from pdetail";
    private static final String UPDATE_USERS_SQL = "update pdetail set username = ? where id = ?;";
    private static final String INSERT_RECORD_SQL = "INSERT INTO `pdetail`( `pno`,`date`, `name`, `mno`, `gen`, `age`, `wht`, `bp`, `pls`, `pdis`) VALUES (?,?,?,?,?,?,?,?,?,?);";
    private static final String GET_TOTAL_NO_OF_ROWS = "SELECT COUNT(NAME) FROM pdetail";
    private static final String GET_TOTAL_MONTH_PATIENT = "select * from pdetail where MONTH(date) = MONTH(now()) and YEAR(date) = YEAR(now());";
    private static final String GET_TOTAL_TODAY_PATIENT = "SELECT * FROM `pdetail` WHERE date = CURRENT_DATE+\" 00:00:00\"; ";

    Connection connection = null;
    private static final String GET_MAX_INDEX = "SELECT MAX(pno) FROM pdetail;";
    static Database singletone_database = null;

    private static final String INSERT_MEDECINE_INFO = "INSERT INTO `medi` (`pno`, `pname`, `medicin`, `mqty`, `mtime`, `ba`, `qty`) VALUES (?,?,?,?,?,?,?);";

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

                patientDetailsList.add(patientdetails);
                i++;
            }
            return patientDetailsList;

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
            System.out.println(preparedStatement);
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

    public PatientDetails getRecord(String username) {
        final String GET_PAITENT_HAVING_USERNAME = "select * from pdetail where name = \'" + username + "\'";
        try {
            Connection connection = connect();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_PAITENT_HAVING_USERNAME);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {

                PatientDetails patientdetails = new PatientDetails();
                //patientdetails.setPid(Integer.parseInt(rs.getString("pno")));
                //patientdetails.setName(rs.getString("name"));
                //patientdetails.setMobileNo(rs.getString("mno"));
                //patientdetails.setGender(rs.getString("gen"));
                //patientdetails.setAge(Integer.parseInt(rs.getString("age")));
                //patientdetails.setPulse(Integer.parseInt(rs.getString("pls")));
                //patientdetails.setSymptoms(rs.getString("pdis"));
                //patientdetails.setWeight(Float.parseFloat(rs.getString("wht")));
                // patientdetails.setSugar(Integer.parseInt(rs.getString("sugar")));
                //  System.out.println(rs.getString("pdis"));
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
            preparedStatement.setString(3, patientdetails.getName());
            preparedStatement.setString(4, patientdetails.getMobileNo());
            preparedStatement.setString(5, patientdetails.getGender());
            preparedStatement.setInt(6, patientdetails.getAge());
            preparedStatement.setInt(7, patientdetails.getWeight());
            preparedStatement.setString(8, patientdetails.getBloodPressure());
            preparedStatement.setString(9, patientdetails.getPulse());
//            preparedStatement.setString(9, patientdetails.getSugar());
            preparedStatement.setString(10, patientdetails.getSymptoms());

            preparedStatement.executeUpdate();
            conn.commit();
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
            preparedStatement.setString(3, medicineDetails.getMedicineName());
            preparedStatement.setString(4, medicineDetails.getMedicineQuantity());
            preparedStatement.setString(5, medicineDetails.getMedicineTime());
            preparedStatement.setString(6, medicineDetails.getMedicineMealTime());
            preparedStatement.setString(7, medicineDetails.getTotalQuantity());

            preparedStatement.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public ArrayList<String> getLikeMedicine(String str) {

        ArrayList<String> medi = new ArrayList<String>();

        try {
            Connection conn = connect();
            StringBuffer GET_LIKE_MEDICINE = new StringBuffer("SELECT * FROM `medilist` WHERE medicine LIKE ");
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

}
