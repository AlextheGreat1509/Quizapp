package shared.messages;

import models.Question;

public class QuestionMessage extends BaseMessage{
    private Question question;


    public QuestionMessage(Question question) {
        this.question = question;
    }

    public Question getQuestion() {
        return question;
    }
}
