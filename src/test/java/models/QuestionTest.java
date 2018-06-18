package models;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class QuestionTest {
    @Test
    public void constructorTest(){
        ArrayList<Answer> answers = new ArrayList<>();
        answers.add(new Answer(1, "Test", true));
        Question question = new Question(answers, 1 , "Test question");
        Assert.assertNotNull(question);
    }

    @Test
    public void valueTest(){
        ArrayList<Answer> answers = new ArrayList<>();
        answers.add(new Answer(1, "Test", true));
        Question question = new Question(answers, 1 , "Test question");
        Assert.assertTrue(question.getAnswers() == answers && question.getQuestion().equals("Test question") && question.getId() == 1);
    }
}
