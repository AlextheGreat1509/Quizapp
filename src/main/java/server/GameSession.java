package server;

import dbal.databaseContext.QuestionDatabaseContext;
import dbal.repositories.QuestionRepository;
import models.Answer;
import models.Question;
import models.Round;

import java.util.ArrayList;
import java.util.Random;

public class GameSession {

    private QuestionRepository questionRepository = new QuestionRepository(new QuestionDatabaseContext());

    public Question PrepareRandomQuestion(){
        ArrayList<Integer> questionsAsked = new ArrayList<Integer>();
        questionsAsked.add(0);
        int questionID = 0;
        int maxQuestions = questionRepository.GetAmountOfPossibleQuestionIDs();
        if (maxQuestions >= 1) {
            Random rand = new Random();
            while (questionsAsked.contains(questionID)) {
                questionID = rand.nextInt(maxQuestions) + 1;
            }
            questionsAsked.add(questionID);
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

    public boolean CheckAnswer(Answer answer){
        return answer.isCorrect();
    }
}
