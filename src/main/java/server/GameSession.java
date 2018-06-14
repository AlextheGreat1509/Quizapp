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
    private ArrayList<Session> sessions;
    private ArrayList<String> roundSessions = new ArrayList<>();
    private RoundResult roundResult = new RoundResult();
    private final IMessageSender iMessageSender;
    private Map<String, Player> playerSessions = new HashMap<>();

    GameSession(ArrayList<Session> gameSessions){
        sessions = new ArrayList<>(gameSessions);
        questionRepository = new QuestionRepository(new QuestionDatabaseContext());
        iMessageSender = new MessageSender();
        iMessageSender.setSessions(sessions);
        PrepareRound();
    }

    public void addPlayerSession(Player player, String sessionId){
        playerSessions.put(sessionId, player);
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
            roundSessions.add(sessionId);
            for (String roundsessionId : roundSessions) {
                if (!roundsessionId.equals(sessionId)) {
                    roundResult.addResultToRound(getPlayerFromSessionId(sessionId),playerAnswer.getAnswer().isCorrect());
                }
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
