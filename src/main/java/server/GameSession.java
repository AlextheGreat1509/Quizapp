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
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GameSession {

    private ArrayList<Integer> questionsAsked = new ArrayList<Integer>();
    private QuestionRepository questionRepository;
    private ArrayList<Player> players = new ArrayList<Player>();
    private ArrayList<Session> sessions = new ArrayList<>();
    private ArrayList<String> roundSessions = new ArrayList<>();
    private RoundResult roundResult = new RoundResult();
    private final IMessageSender iMessageSender;
    private Map<String, Player> playerSessions = new HashMap<>();
    private int maxPlayers;

    GameSession(int maxPlayers){
        this.maxPlayers = maxPlayers;
        questionRepository = new QuestionRepository(new QuestionDatabaseContext());
        iMessageSender = new MessageSender();
    }

    public void addSession(Session session){
        sessions.add(session);
        if (sessions.size() >= maxPlayers){
            iMessageSender.setSessions(sessions);
        }
    }

    public void addPlayerSession(Player player, String sessionId){
        playerSessions.put(sessionId, player);
        if (playerSessions.size() >= maxPlayers){
            PrepareRound();
        }
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
        roundSessions.clear();
        SendMessageToPlayers(question);

        return new Round(question);
    }

    public void SendMessageToPlayers(Object object){
        for (Session session : sessions) {
                iMessageSender.sendTo(session.getId(), object);
        }
    }
    public void CheckAnswer(PlayerAnswer playerAnswer, String sessionId){
        if (roundSessions.size() != sessions.size()){
            String currentSessionId = null;
            for (String roundsessionId : roundSessions) {
                if (!roundsessionId.equals(sessionId)) {
                    currentSessionId = sessionId;
                }
            }
            if (currentSessionId != null) {
                roundResult.addResultToRound(getPlayerFromSessionId(sessionId).getUsername(), playerAnswer.getAnswer().isCorrect());
                roundSessions.add(sessionId);
            }
            if (roundSessions.isEmpty()){
                roundResult.addResultToRound(getPlayerFromSessionId(sessionId).getUsername(),playerAnswer.getAnswer().isCorrect());
                roundSessions.add(sessionId);
            }
            if (roundSessions.size() == sessions.size()){
                SendMessageToPlayers(roundResult);
            }
        } else {
            SendMessageToPlayers(roundResult);
        }
    }

    public Player getPlayerFromSessionId(String sessionId){
        return playerSessions.get(sessionId);
    }
}
