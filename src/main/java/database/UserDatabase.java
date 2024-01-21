package database;

import auth.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sha.SHA;

/**
 *
 * @author haris
 */
public class UserDatabase {
     /**
    * User Relates All queries
    */
    private static final String INSERT_NEW_USER = "INSERT INTO  public.\"user\"(username, email, type, hospital_name, password,database_name) VALUES (?,?,?, ?, ?, ?);";
    private static final String GET_LOGIN_USER = "SELECT  * FROM public.\"user\" where email=? and password=? and type=?";
    private static final String GET_LOGIN_USER_BY_NAME = "SELECT  * FROM public.\"user\" where email=? and username=? and type=?";
    private static final String UPDATE_ACCOUNT_PASSWORD = "UPDATE public.\"user\" SET password=? WHERE username=? and email=? and type=?";
    private static final String UPDATE_DATABASE_NAME = "UPDATE public.\"user\" SET database_name=? WHERE username=? and email=? and type=?";
    
     static UserDatabase singletone_database = null;
    public static UserDatabase getInstance() {
        if (singletone_database == null) {
            singletone_database = new UserDatabase();
            return singletone_database;
        }
        return singletone_database;
    }
    
     public Connection getConnection() {
        return DatabaseConfig.getSourceConnection();
    }
    public boolean insertNewUser(User user) {

        try {
            Connection conn = getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(INSERT_NEW_USER);

            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getUserType());
            preparedStatement.setString(4, user.getHospitalName());
            preparedStatement.setString(6, user.getDatabase_name());
            
            /**
             * This hash is generated using SHA-512 
             * public key i.e salt is email id of user
             * private key i.e password of user
             */
            String hash = SHA.getHash(user.getPassword() , user.getEmail());
            
            preparedStatement.setString(5, hash);
            preparedStatement.executeUpdate();
            return true;
            //conn.commit();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
    
    public User isValidUser(User user) {
        try {
            Connection conn = getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(GET_LOGIN_USER);
            preparedStatement.setString(1, user.getEmail());
            
            /**
             * This hash is generated using SHA-512 
             * public key i.e salt is email id of user
             * private key i.e password of user
             */
            String hash = SHA.getHash(user.getPassword() , user.getEmail());
            preparedStatement.setString(2, hash);
            preparedStatement.setString(3, user.getUserType());

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {

                User user_info = new User();
                user_info.setUserName(rs.getString("username"));
                user_info.setHospitalName(rs.getString("hospital_name"));
                user_info.setEmail(rs.getString("email"));
                user_info.setUserType(rs.getString("type"));
                user_info.setUserId(rs.getInt("user_id"));
                user_info.setDatabase_name(rs.getString("database_name"));

                System.out.println(user_info);
                return user_info;
                // user_info.setUserName(rs.getString("username"));

                // return patientdetails;
            }
        } catch (SQLException e) {

            System.out.println(e);

        }
        return null;

    }
    
     //using this for getting details of user who forgot the password
    public User isValidUserByName(User user) {
        System.out.println(user);
        //final String GET_USER = "SELECT  * FROM public.\"user\" where email="+"\'" + user.getEmail()+"\'" + " password= " + \123'; = \'" + username + "\'";
        try {
            Connection conn = getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(GET_LOGIN_USER_BY_NAME);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getUserName());
            preparedStatement.setString(3, user.getUserType());

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {

                User user_info = new User();
                user_info.setUserName(rs.getString("username"));
                user_info.setHospitalName(rs.getString("hospital_name"));
                user_info.setEmail(rs.getString("email"));
                user_info.setUserType(rs.getString("type"));
                user_info.setUserId(rs.getInt("user_id"));
                user_info.setDatabase_name(rs.getString("database_name"));

                return user_info;
                // user_info.setUserName(rs.getString("username"));

                // return patientdetails;
            }
        } catch (SQLException e) {

            System.out.println(e);

        }
        return null;

    }
    
    //using this for getting details of user who forgot the password
    public boolean updateUserAccountPassword(User user) {
        System.out.println(user);
        //final String GET_USER = "SELECT  * FROM public.\"user\" where email="+"\'" + user.getEmail()+"\'" + " password= " + \123'; = \'" + username + "\'";
       
        try {
            Connection conn = getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_ACCOUNT_PASSWORD);
            
            String hash = SHA.getHash(user.getPassword() , user.getEmail());
            
            preparedStatement.setString(1, hash);
            preparedStatement.setString(2, user.getUserName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getUserType());

            System.out.println(user);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {

            System.out.println(e);

        }
        return false;

    }
    public boolean updateUserDatabase(User user) {
        System.out.println(user);
        //final String GET_USER = "SELECT  * FROM public.\"user\" where email="+"\'" + user.getEmail()+"\'" + " password= " + \123'; = \'" + username + "\'";
       
        try {
            Connection conn = getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_DATABASE_NAME);
            
            
            preparedStatement.setString(1, user.getDatabase_name());
            preparedStatement.setString(2, user.getUserName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getUserType());

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {

            System.out.println(e);

        }
        return false;

    }
    
    
}
