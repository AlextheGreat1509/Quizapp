package client;

import frontend.QuestionFX;
import models.Answer;
import models.GameResult;
import models.Question;
import models.RoundResult;

public interface IUILogic {

    /**
     * @param answer
     * Processes the answer given by the UI and sends it to the Server
     */
    void processAnswer(Answer answer);

    /**
     * @param username
     * @param password
     * connects to the server by sending a player object to the server
     */
    void connect(String username, String password);

    /**
     * @param question
     * Sets the question and changes the UI scene to the question scene
     */
    void setQuestion(Question question);

    /**
     * @param questionFX
     * sets the UI to be used by the UILogic
     */
    void setUI(QuestionFX questionFX);

    /**
     * @param roundResult
     * Sets the result of the round played and changes the UI scene to the question scene
     */
    void setRoundResult(RoundResult roundResult);

    /**
     * @param gameResult
     * Sets the result of the game played and changes the UI scene to the question scene
     */
    void setGameResult(GameResult gameResult);
}
