package client;

import models.Answer;
import models.Question;
import server.GameSession;

import java.util.ArrayList;

public class UILogic implements IUILogic{
    GameSession game = new GameSession();
    boolean useServer = true;

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
}
