package client;

import com.google.gson.Gson;
import frontend.IQuestionFX;
import frontend.QuestionFX;
import models.Answer;
import models.PlayerAnswer;
import models.Question;
import server.GameSession;
import shared.EncapsulatingMessageGenerator;
import shared.messages.PlayerReadyMessage;

import java.util.ArrayList;
import java.util.Set;

public final class UILogic implements IUILogic{
    private IWebSocketClient client = new WebSocketClient();
    private Gson gson = new Gson();
    EncapsulatingMessageGenerator messageGenerator = new EncapsulatingMessageGenerator();
    boolean useServer = true;
    Question question;

    private static final UILogic INSTANCE = new UILogic();

    public Question GetQuestion(){
        if (!useServer) {
            ArrayList<Answer> answers = new ArrayList<Answer>();
            answers.add(new Answer(1, "The Hague", false));
            answers.add(new Answer(2, "Rotterdam", false));
            answers.add(new Answer(3, "Amsterdam", true));
            answers.add(new Answer(4, "Maastricht", false));
            return new Question(answers, 1, "What is the capital of the Netherlands?");
        } else {
            return question;
        }
    }

    public void ProcessAnswer(Answer answer){
        PlayerAnswer playerAnswer = new PlayerAnswer(answer);
        client.SendMessage(messageGenerator.generateMessageString(playerAnswer));
        System.out.println(playerAnswer.getAnswer().getAnswer() + " " + playerAnswer.getAnswer().isCorrect());
    }

    public void SetQuestion(Question question) {
        this.question = question;
    }

    public void Connect(){
        PlayerReadyMessage msg = new PlayerReadyMessage();
        client.SendMessage(messageGenerator.generateMessageString(msg));
    }

    public static UILogic getInstance(){
        return INSTANCE;
    }
}
