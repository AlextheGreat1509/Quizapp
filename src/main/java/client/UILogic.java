package client;

import com.google.gson.Gson;
import models.Answer;
import models.Question;
import server.GameSession;
import shared.EncapsulatingMessageGenerator;

import java.util.ArrayList;

public class UILogic implements IUILogic{
    private final IWebSocketClient client;
    private Gson gson = new Gson();
    EncapsulatingMessageGenerator messageGenerator = new EncapsulatingMessageGenerator();

    GameSession game = new GameSession();
    boolean useServer = true;

    public UILogic(IWebSocketClient client) {
        this.client = client;
    }

    public Question GetQuestion(){
        if (!useServer) {
            ArrayList<Answer> answers = new ArrayList<Answer>();
            answers.add(new Answer(1, "The Hague", false));
            answers.add(new Answer(2, "Rotterdam", false));
            answers.add(new Answer(3, "Amsterdam", true));
            answers.add(new Answer(4, "Maastricht", false));
            return new Question(answers, 1, "What is the capital of the Netherlands?");
        } else {

            return game.PrepareRandomQuestion();
        }
    }

    public void ProcessAnswer(Answer answer){
        client.SendMessage(messageGenerator.generateMessageString(answer));
        System.out.println(answer.getAnswer() + " " + answer.isCorrect());
    }
}
