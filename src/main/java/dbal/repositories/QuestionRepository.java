package dbal.repositories;

import dbal.context.IQuestionContext;
import dbal.databaseContext.QuestionDatabaseContext;
import models.Question;

public class QuestionRepository {
    private IQuestionContext questionContext = new QuestionDatabaseContext();

    public Question GetQuestion(int id){
        return questionContext.GetQuestion(id);
    }

    public int GetAmountOfPossibleQuestionIDs(){
        return questionContext.GetAmountOfPossibleQuestionIDs();
    }

}
