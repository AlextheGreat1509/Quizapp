package client;

import frontend.QuestionFX;
import models.Answer;
import models.GameResult;
import models.Question;
import models.RoundResult;

public interface IUILogic {
    void processAnswer(Answer answer);
    void connect(String username, String password);
    void setQuestion(Question question);
    void setUI(QuestionFX questionFX);
    void setRoundResult(RoundResult roundResult);
    void setGameResult(GameResult gameResult);
}
