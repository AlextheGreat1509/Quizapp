package shared.messages;

import models.Answer;
import models.Player;

public class PlayerAnswerMessage {
    private Answer answer;

    public PlayerAnswerMessage(Answer answer) {
        this.answer = answer;
    }

    public Answer getAnswer() {
        return answer;
    }
}
