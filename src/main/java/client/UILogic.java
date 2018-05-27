package client;

import models.Answer;
import models.Question;

import java.util.ArrayList;

public class UILogic implements IUILogic{
    public Question GetQuestion(){
        ArrayList<Answer> answers = new ArrayList<Answer>();
        answers.add(new Answer("The Hague", false));
        answers.add(new Answer("Rotterdam", false));
        answers.add(new Answer("Amsterdam", true));
        answers.add(new Answer("Maastricht", false));
        return new Question(answers, 1, "What is the capital of the Netherlands?");
    }
}
