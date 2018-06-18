package dbal.databaseContext;


import dbal.context.IPlayerContext;
import javassist.bytecode.stackmap.TypeData;
import models.Player;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PlayerDatabaseContext extends BaseDatabaseContext implements IPlayerContext {
    private static final Logger LOGGER = Logger.getLogger( TypeData.ClassName.class.getName() );
    public boolean Register(Player player){
        try {
            getCon();
            // Create and execute an SQL statement that returns some data.
            String SQL = "INSERT INTO Player (Username, Password) VALUES (? , ?); ";

            stmt = con.prepareStatement(SQL);
            stmt.setString(1, player.getUsername());
            stmt.setString(2, player.getPassword());
            stmt.executeQuery();
            closeConnection();
        }

        // Handle any errors that may have occurred.
        catch (Exception e) {
            LOGGER.log( Level.SEVERE, e.toString(), e );
        }
        return true;
    }

    public boolean Login(Player player){
        boolean exists = false;
        try {
            getCon();
            // Create and execute an SQL statement that returns some data.
            String SQL = "SELECT Username FROM Player WHERE Player.Username = ? AND Player.Password = ?; ";

            stmt = con.prepareStatement(SQL);
            stmt.setString(1, player.getUsername());
            stmt.setString(2, player.getPassword());
            rs = stmt.executeQuery();

            exists = rs.next();
            closeConnection();
            return exists;
        }

        // Handle any errors that may have occurred.
        catch (Exception e) {
            LOGGER.log( Level.SEVERE, e.toString(), e );
        }
        return exists;
    }
}
