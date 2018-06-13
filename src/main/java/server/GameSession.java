package server;

import dbal.databaseContext.QuestionDatabaseContext;
import dbal.repositories.QuestionRepository;
import javafx.print.PageLayout;
import models.*;
import shared.EncapsulatingMessageGenerator;
import shared.messages.EncapsulatingMessage;

import javax.websocket.Session;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class GameSession {

    private ArrayList<Integer> questionsAsked = new ArrayList<Integer>();
    private QuestionRepository questionRepository;
    private ArrayList<Player> players = new ArrayList<Player>();
    private ArrayList<Session>sessions = new ArrayList<>();
    private ArrayList<String>roundSessions = new ArrayList<>();
    private RoundResult roundResult = new RoundResult();
    private IMessageSender iMessageSender;

    GameSession(ArrayList<Session> sessions){
        this.sessions = sessions;
        questionRepository = new QuestionRepository(new QuestionDatabaseContext());
        iMessageSender = new MessageSender();
        iMessageSender.setSessions(sessions);
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
        for (Session session : sessions){
            roundSessions.add(session.getId());
        }
        SendMessageToPlayers(question);

        return new Round(question);
    }

    public void SendMessageToPlayers(Object object){
        for (Session session : sessions) {
                iMessageSender.sendTo(session.getId(), object);
        }
    }
    public boolean CheckAnswer(PlayerAnswer playerAnswer, String sessionId){
        if (!roundSessions.isEmpty()){
            int i = 0;
            for (String roundsessionId : roundSessions) {
                if (roundsessionId == sessionId) {
                    //TODO add roundresult.addresult here
                    roundSessions.remove(i);
                }
                i++;
            }
        }
        iMessageSender.sendTo(sessionId, playerAnswer.getAnswer().isCorrect());
        return playerAnswer.getAnswer().isCorrect();
    }
}
