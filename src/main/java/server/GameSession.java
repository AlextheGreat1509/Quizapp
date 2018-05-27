package server;

import dbal.repositories.QuestionRepository;
import models.Question;
import models.Round;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import java.util.Random;

public class GameSession {

    private QuestionRepository questionRepository = new QuestionRepository();

    public Question PrepareRandomQuestion(){
        int maxQuestions = questionRepository.GetAmountOfPossibleQuestionIDs();
        if (maxQuestions >= 1) {
            Random rand = new Random();
            int questionID = rand.nextInt(maxQuestions) + 1;
            return questionRepository.GetQuestion(questionID);
        }
        else {
            return null;
        }
    }
    public Round PrepareRound(){
        Question question = PrepareRandomQuestion();
        return new Round(question);
    }
}
