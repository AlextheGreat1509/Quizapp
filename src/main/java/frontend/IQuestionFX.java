package frontend;

import models.GameResult;
import models.Question;
import models.RoundResult;

public interface IQuestionFX {
    void updateQuestionUI(Question question);
    void updateResultUI(RoundResult roundResult);
    void updateGameResultUI(GameResult gameResult);
}
