package dbal.databaseContext;

import java.sql.*;

public abstract class BaseDatabaseContext {
    private String connectionUrl = "jdbc:sqlserver://localhost:1433;" +
            "databaseName=Quiz;user=Quiz;password=Quiz123";

    // Declare the JDBC objects.
    Connection con = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    // Establish the connection.
    public Connection getCon() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);
        }catch(Exception e){
            e.printStackTrace();
        }
        return con;
    }
}
