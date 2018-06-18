package shared.messages;

import models.Answer;

public class PlayerAnswerMessage extends BaseMessage{
    private Answer answer;

    public PlayerAnswerMessage(Answer answer) {
        this.answer = answer;
    }

    public Answer getAnswer() {
        return answer;
    }
}
