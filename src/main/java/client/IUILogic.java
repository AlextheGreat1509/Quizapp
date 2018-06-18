package client;

import frontend.QuestionFX;
import models.Answer;
import models.GameResult;
import models.Question;
import models.RoundResult;

public interface IUILogic {
    void GetQuestion();
    void ProcessAnswer(Answer answer);
    void Connect(String username, String password);
    void SetQuestion(Question question);
    void setUI(QuestionFX questionFX);
    void setRoundResult(RoundResult roundResult);
    void setGameResult(GameResult gameResult);
}
