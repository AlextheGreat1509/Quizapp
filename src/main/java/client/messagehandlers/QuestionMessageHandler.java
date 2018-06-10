package client.messagehandlers;

import client.IUILogic;
import models.Question;

public class QuestionMessageHandler extends BaseMessageHandler {

    public QuestionMessageHandler(IUILogic logic) {
        super(logic);
    }

    public void HandleQuestion(Question question){
        logic.SetQuestion(question);
    }
}
