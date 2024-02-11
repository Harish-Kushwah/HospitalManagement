package database;

/**
 *
 * @author haris
 */
import auth.User;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConfig {

    /**
     * Parent Database url for creating all the database
     */
    private static final String URL = "jdbc:postgresql://localhost:5432/";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Harish";

    /**
     * System database which stores all the user information give the template
     * for all the remaining database
     */
    private static final String SOURCE_URL = "jdbc:postgresql://localhost:5432/guru";
    private static final String SOURCE_USER = "postgres";
    private static final String SOURCE_PASSWORD = "Harish";

    /**
     * Child database which is created for each user
     */
    private static final String DESTINATION_URL = "jdbc:postgresql://localhost:5432/";
    private static final String DESTINATION_USER = "postgres";
    private static final String DESTINATION_PASSWORD = "Harish";

    private static Connection SOURCE_CONNECTION = null;
    private static Connection DESTINATION_CONNECTION = null;

    static String to_database;

    public static Connection getSourceConnection() {
        try {
            if (SOURCE_CONNECTION == null) {
                SOURCE_CONNECTION = DriverManager.getConnection(SOURCE_URL, SOURCE_USER, SOURCE_PASSWORD);
                return SOURCE_CONNECTION;
            }
            return SOURCE_CONNECTION;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static Connection getDestinationConnection() {
            return DESTINATION_CONNECTION;
    }

    public static boolean createDatabase() {
        Connection ref_connection = null;
        Statement statement = null;

        try {

            // Open a connection
            ref_connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = ref_connection.createStatement();

            // Check if the database already exists
            if (!databaseExists(statement, DatabaseConfig.to_database)) {
                // Create a new database
                String createDbQuery = "CREATE DATABASE " + DatabaseConfig.to_database;
                statement.executeUpdate(createDbQuery);
                System.out.println("Database '" + DatabaseConfig.to_database + "' created successfully.");

                return true;

            } else {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the statement and connection in a finally block to ensure proper cleanup
            try {
                if (statement != null) {
                    statement.close();
                }
                if (ref_connection != null) {
                    ref_connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean copyTables(String db_name) {
        Connection sourceConnection = null;
        Connection destinationConnection = null;
        Statement destinationStatement = null;

        try {

            // Open connections to source and destination databases
            sourceConnection = DriverManager.getConnection(SOURCE_URL, SOURCE_USER, SOURCE_PASSWORD);
            DatabaseConfig.SOURCE_CONNECTION = sourceConnection;

            String local_url  = DESTINATION_URL + db_name;
            System.out.println(DESTINATION_URL);
//            destinationConnection = getDestinationConnection();
            destinationConnection = DriverManager.getConnection(local_url, DESTINATION_USER, DESTINATION_PASSWORD);

            // Create a statement for the destination database
            destinationStatement = destinationConnection.createStatement();

            // Get the metadata of tables from the source database
            DatabaseMetaData metaData = sourceConnection.getMetaData();
            ResultSet tables = metaData.getTables(null, null, "%", new String[]{"TABLE"});

            // Iterate through the tables and create them in the destination database
            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                
                if (tableName.contentEquals("user")) {
                    /**
                     * do not create the user table for all the tables
                     */
//                    String createTableQuery ="CREATE TABLE public.user(user_id bigint NOT NULL,username text, email text NOT NULL, type text,hospital_name text,password text NOT NULL);";
//                    destinationStatement.executeUpdate(createTableQuery);
                } else {
                    String createTableQuery = getCreateTableQuery(metaData, tableName);
                    destinationStatement.executeUpdate(createTableQuery);
                    System.out.println("Table '" + tableName + "' created in the destination database.");
                }

            }
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            // Close connections and statement in a finally block to ensure proper cleanup
//            try {
//                if (destinationStatement != null) {
//                    destinationStatement.close();
//                }
//                if (destinationConnection != null) {
//                    destinationConnection.close();
//                }
                
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
        }
    }

    private static String getCreateTableQuery(DatabaseMetaData metaData, String tableName) throws SQLException {
        ResultSet resultSet = metaData.getColumns(null, null, tableName, null);
        StringBuilder createTableQuery = new StringBuilder("CREATE TABLE " + tableName + " (");
        while (resultSet.next()) {
            String columnName = resultSet.getString("COLUMN_NAME");
            String dataType = resultSet.getString("TYPE_NAME");
            createTableQuery.append(columnName).append(" ").append(dataType).append(", ");
        }
        createTableQuery.setLength(createTableQuery.length() - 2); // Remove the trailing comma and space
        createTableQuery.append(")");
        return createTableQuery.toString();
    }

    // Check if a database with the given name already exists
    private static boolean databaseExists(Statement statement, String dbName) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT 1 FROM pg_database WHERE datname = '" + dbName + "'");
        return resultSet.next();
    }

    public static boolean initNewDatabase(User user) {

       String db_name = user.getDatabase_name();
       DatabaseConfig.to_database = "db_" + String.valueOf(user.getUserId());
        if (db_name == null) {      
            if (createDatabase()) {
                return copyTables(DatabaseConfig.to_database);

            } else {
                System.err.println("Database is not created");
            }
        } else {
            return true;

        }
        return false;

    }
    public static boolean LoadDatabase(User user)
    {
        DatabaseConfig.to_database = user.getDatabase_name();
        String destination_url = DESTINATION_URL +  user.getDatabase_name();
        try {
            DESTINATION_CONNECTION = DriverManager.getConnection(destination_url, DESTINATION_USER, DESTINATION_PASSWORD);
        } catch (SQLException ex) {
        }

        return true;
    }

    public static void closeDestinationConnection() {
        try {
            DatabaseConfig.DESTINATION_CONNECTION.close();
        } catch (SQLException ex) {
        }
    }
}
