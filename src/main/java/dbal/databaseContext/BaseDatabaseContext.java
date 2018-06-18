package dbal.databaseContext;

import javassist.bytecode.stackmap.TypeData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class BaseDatabaseContext {
    private static final Logger LOGGER = Logger.getLogger( TypeData.ClassName.class.getName() );

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
            LOGGER.log( Level.SEVERE, e.toString(), e );
        }
        return con;
    }

    public void closeConnection(){
        if (rs != null) try { rs.close(); } catch(Exception e) {}
        if (stmt != null) try { stmt.close(); } catch(Exception e) {}
        if (con != null) try { con.close(); } catch(Exception e) {}
    }
}
