package dbal.context;

import models.Question;

public interface IQuestionContext extends IBaseContext {
    Question GetQuestion(int id);
}
