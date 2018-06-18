package client;

import com.google.gson.Gson;
import frontend.IQuestionFX;
import frontend.QuestionFX;
import models.*;
import shared.EncapsulatingMessageGenerator;
import shared.IEncapsulatingMessageGenerator;
import shared.messages.PlayerAnswerMessage;
import shared.messages.PlayerReadyMessage;

import java.util.ArrayList;
import java.util.Observer;

public final class UILogic implements IUILogic, Observer{
    private IWebSocketClient client;
    private IEncapsulatingMessageGenerator messageGenerator;
    private IQuestionFX questionFX;

    private static final UILogic INSTANCE = new UILogic();

    private UILogic() {
        client = new WebSocketClient();
        messageGenerator = new EncapsulatingMessageGenerator();
    }

    public void setUI(QuestionFX questionFX){
        this.questionFX = questionFX;
    }

    public void processAnswer(Answer answer){
        client.SendMessage(messageGenerator.generateMessageString(new PlayerAnswerMessage(answer)));
    }

    public void setQuestion(Question question) {
        questionFX.updateQuestionUI(question);
    }

    public void connect(String username, String password){
        Player player = new Player(username,password);
        client.SendMessage(messageGenerator.generateMessageString(new PlayerReadyMessage(player)));
    }

    public void setRoundResult(RoundResult roundResult){
        questionFX.updateResultUI(roundResult);
    }

    public void setGameResult(GameResult gameResult){
        questionFX.updateGameResultUI(gameResult);
    }

    public static UILogic getInstance(){
        return INSTANCE;
    }

    @Override
    public void update(java.util.Observable o, Object arg) {
        questionFX.updateQuestionUI(new Question(null,0,null));
    }
}
