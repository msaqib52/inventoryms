package yhc.inventory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class DatabaseConnection {
    
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String DATABASEURL = "jdbc:mysql://localhost/yinventory";
    private static boolean databaseConnection = false;
    private static Connection connection;
    private static Statement statement;
    
    public DatabaseConnection(String user, String password) throws SQLException, ClassNotFoundException {
        Class.forName(DRIVER);
        connection = DriverManager.getConnection(DATABASEURL, user, password);
        statement  = connection.createStatement();
        setDatabaseConnection(true);
    }
    
    public static Connection getDatabaseConnection() {
        return connection;
    }
    
    public static Statement getStatement() {
        return statement;
    }
    
    public static boolean isDatabaseConnected() {
        return databaseConnection;
    }
    
    public static void setDatabaseConnection(boolean b) {
        databaseConnection = b;
    }
    
}
