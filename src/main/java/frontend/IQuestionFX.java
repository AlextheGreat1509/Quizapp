package frontend;

import models.GameResult;
import models.Question;
import models.RoundResult;

public interface IQuestionFX {
    /**
     * @param question
     * Changes the UI to show the question
     */
    void updateQuestionUI(Question question);

    /**
     * @param roundResult
     * Changes the UI to show the round result
     */
    void updateResultUI(RoundResult roundResult);

    /**
     * @param gameResult
     * Changes the UI to show the game result
     */
    void updateGameResultUI(GameResult gameResult);
}
