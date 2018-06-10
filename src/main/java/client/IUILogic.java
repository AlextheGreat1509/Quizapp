package client;

import models.Answer;
import models.Question;

public interface IUILogic {
    Question GetQuestion();
    void ProcessAnswer(Answer answer);
    void Connect();
    void SetQuestion(Question question);
}
