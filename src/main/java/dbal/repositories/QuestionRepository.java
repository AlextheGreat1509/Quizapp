package dbal.repositories;

import dbal.context.IQuestionContext;
import models.Question;

public class QuestionRepository extends BaseRepository{
    private final IQuestionContext questionContext;

    public QuestionRepository(IQuestionContext questionContext) {
        this.questionContext = questionContext;
    }

    public Question GetQuestion(int id){
        return questionContext.GetQuestion(id);
    }

    public int GetAmountOfPossibleQuestionIDs(){
        return questionContext.GetAmountOfPossibleQuestionIDs();
    }

}
