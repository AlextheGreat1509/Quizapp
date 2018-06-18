package client.messagehandlers;

import client.IUILogic;
import shared.messages.QuestionMessage;

public class QuestionMessageHandler extends BaseMessageHandler {

    public QuestionMessageHandler(IUILogic logic) {
        super(logic);
    }

    public void HandleQuestion(QuestionMessage questionMessage){
        logic.SetQuestion(questionMessage.getQuestion());
    }
}
