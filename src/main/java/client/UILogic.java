package client;

import com.google.gson.Gson;
import frontend.IQuestionFX;
import frontend.QuestionFX;
import models.*;
import shared.EncapsulatingMessageGenerator;
import shared.messages.PlayerReadyMessage;

import java.util.ArrayList;
import java.util.Observer;
import java.util.Set;

public final class UILogic implements IUILogic, Observer{
    private IWebSocketClient client;
    private Gson gson = new Gson();
    private EncapsulatingMessageGenerator messageGenerator = new EncapsulatingMessageGenerator();
    boolean useServer = true;
    private Question question;
    PlayerFound playerFound = new PlayerFound();
    private IQuestionFX questionFX;

    private static final UILogic INSTANCE = new UILogic();

    private UILogic() {
        client = new WebSocketClient();
    }

    public void setUI(QuestionFX questionFX){
        this.questionFX = questionFX;
    }

    public void GetQuestion(){
        if (!useServer) {
            ArrayList<Answer> answers = new ArrayList<Answer>();
            answers.add(new Answer(1, "The Hague", false));
            answers.add(new Answer(2, "Rotterdam", false));
            answers.add(new Answer(3, "Amsterdam", true));
            answers.add(new Answer(4, "Maastricht", false));
            question = new Question(answers, 1, "What is the capital of the Netherlands?");
            questionFX.updateQuestionUI(question);
        } else {
            questionFX.updateQuestionUI(question);
        }
    }

    public void ProcessAnswer(Answer answer){
        PlayerAnswer playerAnswer = new PlayerAnswer(answer);
        client.SendMessage(messageGenerator.generateMessageString(playerAnswer));
        System.out.println(playerAnswer.getAnswer().getAnswer() + " " + playerAnswer.getAnswer().isCorrect());
    }

    public void SetQuestion(Question question) {
        this.question = question;
        questionFX.updateQuestionUI(question);
    }

    public void Connect(String username, String password){
        Player player = new Player(username,password);
        PlayerReadyMessage msg = new PlayerReadyMessage(player);
        client.SendMessage(messageGenerator.generateMessageString(msg));
    }

    public boolean PlayerFound(){
        playerFound.addObserver(this);
        return playerFound.isPlayerFound();
    }

    public void setRoundResult(RoundResult roundResult){
        questionFX.updateResultUI(roundResult);
    }

    public static UILogic getInstance(){
        return INSTANCE;
    }

    @Override
    public void update(java.util.Observable o, Object arg) {
        questionFX.updateQuestionUI(new Question(null,0,null));
    }
}
