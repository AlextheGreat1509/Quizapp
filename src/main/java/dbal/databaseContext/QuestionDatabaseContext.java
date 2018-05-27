package dbal.databaseContext;


import dbal.context.IQuestionContext;
import models.Answer;
import models.Question;

import java.util.ArrayList;

public class QuestionDatabaseContext extends BaseDatabaseContext implements IQuestionContext {

    public Question GetQuestion(int id){
        try {
            getCon();
            // Create and execute an SQL statement that returns some data.
            String SQL = "SELECT * FROM Question WHERE Question.ID = ?; ";

            stmt = con.prepareStatement(SQL);
            stmt.setInt(1,id);
            rs = stmt.executeQuery();

            // Iterate through the data in the result set and display it.
            while (rs.next()) {
                System.out.println(rs.getString("ID") + " " + rs.getString("Question"));
            }
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
        return new Question(new ArrayList<Answer>(), 1, "test");
    }
}
