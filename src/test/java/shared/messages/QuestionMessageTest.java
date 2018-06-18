package shared.messages;

import models.Answer;
import models.Question;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class QuestionMessageTest {
    @Test
    public void constructorTest(){
        ArrayList<Answer> answers = new ArrayList<>();
        answers.add(new Answer(1, "Test", true));
        Question question = new Question(answers, 1, "Test question");
        QuestionMessage questionMessage = new QuestionMessage(question);
        Assert.assertNotNull(questionMessage);
    }

    @Test
    public void valueTest(){
        ArrayList<Answer> answers = new ArrayList<>();
        answers.add(new Answer(1, "Test", true));
        Question question = new Question(answers, 1, "Test question");
        QuestionMessage questionMessage = new QuestionMessage(question);
        Assert.assertTrue(questionMessage.getQuestion() == question);
    }
}
