package client;

import models.Answer;
import models.Question;

public interface IUILogic {
    Question GetQuestion();
    Answer ProcessAnswer(Answer answer);
}
