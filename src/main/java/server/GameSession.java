package server;

import dbal.databaseContext.QuestionDatabaseContext;
import dbal.repositories.QuestionRepository;
import javafx.print.PageLayout;
import models.*;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.Random;

public class GameSession {

    private ArrayList<Integer> questionsAsked = new ArrayList<Integer>();
    private QuestionRepository questionRepository = new QuestionRepository(new QuestionDatabaseContext());
    private IWebSocket iWebSocket = new WebSocket();
    private ArrayList<Player> players = new ArrayList<Player>();
    private ArrayList<Session>sessions = new ArrayList<>();

    GameSession(ArrayList<Session> sessions){
        this.sessions = sessions;
        PrepareRound();
    }

    public Question PrepareRandomQuestion(){
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
        SendMessageToPlayers(question);
        return new Round(question);
    }

    public void SendMessageToPlayers(Object object){
        for (Session session : sessions) {
            iWebSocket.sendTo(session.getId(), object);
        }
    }
    public boolean CheckAnswer(PlayerAnswer playerAnswer, String sessionId){
        iWebSocket.sendTo(sessionId, playerAnswer);
        return playerAnswer.getAnswer().isCorrect();
    }
}
