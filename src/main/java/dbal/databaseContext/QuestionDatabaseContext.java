package dbal.databaseContext;


import dbal.context.IQuestionContext;
import javassist.bytecode.stackmap.TypeData;
import models.Answer;
import models.Question;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QuestionDatabaseContext extends BaseDatabaseContext implements IQuestionContext {
    private static final Logger LOGGER = Logger.getLogger( TypeData.ClassName.class.getName() );

    public Question GetQuestion(int id){
        Question question = null;
        ArrayList<Answer> answers = new ArrayList<Answer>();
        int questionID = 0;
        String tempQuestion = "";
        try {
            getCon();
            // Create and execute an SQL statement that returns some data.
            String SQL = "SELECT Question.ID, Question.Question, Answer.ID AS AnswerID, Answer.Answer, Answer.Correct FROM Question INNER JOIN Answer ON Question.ID = Answer.QuestionID WHERE Question.ID = ?; ";

            stmt = con.prepareStatement(SQL);
            stmt.setInt(1,id);
            rs = stmt.executeQuery();

            // Iterate through the data in the result set and display it.
            while (rs.next()) {
                answers.add(new Answer(rs.getInt("AnswerID"), rs.getString("Answer"), rs.getBoolean("Correct")));
                questionID = rs.getInt("ID");
                tempQuestion = rs.getString("Question");
            }

            question = new Question(answers,questionID,tempQuestion);
        }

        // Handle any errors that may have occurred.
        catch (Exception e) {
            LOGGER.log( Level.SEVERE, e.toString(), e );
        }
        finally {
            if (rs != null) try { rs.close(); } catch(Exception e) {}
            if (stmt != null) try { stmt.close(); } catch(Exception e) {}
            if (con != null) try { con.close(); } catch(Exception e) {}
        }
        return question;
    }

    public int GetAmountOfPossibleQuestionIDs(){
        int amount = 0;
        try {
            getCon();
            // Create and execute an SQL statement that returns some data.
            String SQL = "Select Max(ID) AS ID From Question; ";

            stmt = con.prepareStatement(SQL);
            rs = stmt.executeQuery();

            // Iterate through the data in the result set and display it.
            while (rs.next()) {
                amount = rs.getInt("ID");
            }
            closeConnection();
        }

        // Handle any errors that may have occurred.
        catch (Exception e) {
            LOGGER.log( Level.SEVERE, e.toString(), e );
        }
        return amount;
    }
}
