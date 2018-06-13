package client;

import frontend.QuestionFX;
import models.Answer;
import models.Question;

public interface IUILogic {
    void GetQuestion();
    void ProcessAnswer(Answer answer);
    void Connect();
    void SetQuestion(Question question);
    boolean PlayerFound();
    void setUI(QuestionFX questionFX);
}
