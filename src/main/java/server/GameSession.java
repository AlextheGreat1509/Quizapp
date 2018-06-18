package server;

import dbal.databaseContext.QuestionDatabaseContext;
import dbal.repositories.QuestionRepository;
import models.*;
import shared.messages.GameResultMessage;
import shared.messages.QuestionMessage;
import shared.messages.RoundResultMessage;

import javax.websocket.Session;
import java.util.*;

public class GameSession implements IGameSession {

    private ArrayList<Integer> questionsAsked = new ArrayList<Integer>();
    private QuestionRepository questionRepository;
    private ArrayList<Session> sessions = new ArrayList<>();
    private ArrayList<String> roundSessions = new ArrayList<>();
    private ArrayList<Round> allRounds = new ArrayList<>();
    private final IMessageSender iMessageSender;
    private Map<String, Player> playerSessions = new HashMap<>();
    private int maxPlayers;
    private Round round;

    GameSession(int maxPlayers){
        this.maxPlayers = maxPlayers;
        questionRepository = new QuestionRepository(new QuestionDatabaseContext());
        iMessageSender = new MessageSender();
    }

    public boolean addSession(Session session){
        sessions.add(session);
        if (sessions.size() >= maxPlayers){
            iMessageSender.setSessions(sessions);
        }
        return true;
    }

    public boolean addPlayerSession(Player player, String sessionId){
        playerSessions.put(sessionId, player);
        if (playerSessions.size() >= maxPlayers){
            PrepareFirstRound();
        }
        return true;
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

    private void PrepareFirstRound(){
        prepareRound();
        roundSessions.clear();
        SendMessageToPlayers(new QuestionMessage(round.getQuestion()));
    }

    private void SendMessageToPlayers(Object object){
        for (Session session : sessions) {
                iMessageSender.sendTo(session.getId(), object);
        }
    }
    public void CheckAnswer(Answer answer, String sessionId){
        if (roundSessions.size() != sessions.size()){
            String currentSessionId = null;
            for (String roundsessionId : roundSessions) {
                if (!roundsessionId.equals(sessionId)) {
                    currentSessionId = sessionId;
                }
            }
            if (currentSessionId != null) {
                round.getResult().addResultToRound(getPlayerFromSessionId(sessionId).getUsername(), answer.isCorrect());
                roundSessions.add(sessionId);
            }
            if (roundSessions.isEmpty()){
                round.getResult().addResultToRound(getPlayerFromSessionId(sessionId).getUsername(), answer.isCorrect());
                roundSessions.add(sessionId);
            }
            if (roundSessions.size() == sessions.size()){
                SendMessageToPlayers(new RoundResultMessage(round.getResult()));
                prepareNextRound();
            }
        } else {
            SendMessageToPlayers(new RoundResultMessage(round.getResult()));
        }
    }

    private void prepareNextRound(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
        allRounds.add(round);
        if (checkGameEnd(2)){
            SendMessageToPlayers(new GameResultMessage(calculateEndResult()));
        } else {
            prepareRound();
            roundSessions.clear();
            SendMessageToPlayers(new QuestionMessage(round.getQuestion()));
        }
    }

    private void prepareRound(){
        Question question = PrepareRandomQuestion();
        round = new Round(question);
        round.setResult(new RoundResult());
    }

    private boolean checkGameEnd(int maxRounds){
        return allRounds.size() >= maxRounds;
    }

    private Map<String, Integer> calculateEndResult(){
        Map<String, Integer> gameresult = new HashMap<>();
        for (Round round: allRounds){
            Set<Map.Entry<String, Boolean>> results = round.getResult().getRoundresult().entrySet();
            for (Map.Entry<String, Boolean> playerresult : results){
                String player = playerresult.getKey();
                boolean correct = playerresult.getValue();
                if (correct) {
                    if (gameresult.containsKey(player)) {
                        int amountCorrect = 1 + gameresult.get(player);
                        gameresult.replace(player, amountCorrect);
                    } else {
                        gameresult.put(player, 1);
                    }
                }
                if (!gameresult.containsKey(player)){
                    gameresult.put(player, 0);
                }
            }
        }
        return gameresult;
    }
    private Player getPlayerFromSessionId(String sessionId){
        return playerSessions.get(sessionId);
    }
}
