package frontend;

import models.Question;
import models.RoundResult;

public interface IQuestionFX {
    void updateQuestionUI(Question question);
    void updateResultUI(RoundResult roundResult);
}
