package dbal.databaseContext;


import dbal.context.IPlayerContext;
import dbal.context.IQuestionContext;
import models.Answer;
import models.Player;
import models.Question;

import java.util.ArrayList;

public class PlayerDatabaseContext extends BaseDatabaseContext implements IPlayerContext {
    public boolean Register(Player player){
        try {
            getCon();
            // Create and execute an SQL statement that returns some data.
            String SQL = "INSERT INTO Player (Username, Password) VALUES (? , ?); ";

            stmt = con.prepareStatement(SQL);
            stmt.setString(1, player.getUsername());
            stmt.setString(2, player.getPassword());
            rs = stmt.executeQuery();
        }

        // Handle any errors that may have occurred.
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (rs != null) try { rs.close(); } catch(Exception e) {}
            if (stmt != null) try { stmt.close(); } catch(Exception e) {}
            if (con != null) try { con.close(); } catch(Exception e) {}
        }
        return true;
    }

    public boolean Login(Player player){
        try {
            getCon();
            // Create and execute an SQL statement that returns some data.
            String SQL = "SELECT COUNT(*) FROM Player WHERE Player.Username = ? AND Player.Password = ?; ";

            stmt = con.prepareStatement(SQL);
            stmt.setString(1, player.getUsername());
            stmt.setString(2, player.getPassword());
            rs = stmt.executeQuery();
        }

        // Handle any errors that may have occurred.
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (rs != null) try { rs.close(); } catch(Exception e) {}
            if (stmt != null) try { stmt.close(); } catch(Exception e) {}
            if (con != null) try { con.close(); } catch(Exception e) {}
        }
        return true;
    }
}
