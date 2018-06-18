package frontend;

import client.IUILogic;
import client.UILogic;
import client.authentication.AuthenticationController;
import client.authentication.IAuthenticationController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import models.Answer;
import models.GameResult;
import models.Question;
import models.RoundResult;

import java.util.ArrayList;
import java.util.Set;


public class QuestionFX extends Application implements IQuestionFX {
    private IUILogic logic;
    private Scene scene;
    private Scene sceneTest;
    private Question question;
    private Stage stage;
    private IAuthenticationController iAuthenticationController;

    public QuestionFX() {
        iAuthenticationController = new AuthenticationController();
        logic = UILogic.getInstance();
        logic.setUI(this);
    }


    void cleanup() {
        // stop animations reset model ect.
    }

    void startGame(Stage stage){
        TextField textUsername = new TextField("Username");
        PasswordField textPassword = new PasswordField();
        Button btnRegister = new Button("Register");
        btnRegister.setOnAction(event ->{
            if (iAuthenticationController.Register(textUsername.getText(), textPassword.getText())){
                logic.Connect(textUsername.getText(), textPassword.getText());
            }

        });
        Button btnLogin = new Button("Login");
        btnLogin.setOnAction(event -> {
            if (iAuthenticationController.Login(textUsername.getText(), textPassword.getText())){
                logic.Connect(textUsername.getText(), textPassword.getText());
            }
        });
        VBox vboxLogin = new VBox();
        vboxLogin.getChildren().addAll(textUsername, textPassword, btnRegister, btnLogin);
        StackPane root = new StackPane();
        root.getChildren().addAll(vboxLogin);
        scene = new Scene(root, 600, 250);
        stage.setTitle("Quiz App");
        stage.setScene(scene);
        stage.show();
    }

    void questionStage(Stage stage) {
        final Text questionField = new Text(question.getQuestion());
        ArrayList<Button> answerButtons = new ArrayList<Button>();
        for (final Answer answer : question.getAnswers()){
            Button button = new Button(answer.getAnswer());
            button.setTextAlignment(TextAlignment.CENTER);
            button.setOnAction(event -> {
                logic.ProcessAnswer(answer);
                goResultStage(stage, null);
            });
            answerButtons.add(button);
        }

        questionField.setTextAlignment(TextAlignment.CENTER);
        VBox vboxQuestionWithAnswers = new VBox();
        vboxQuestionWithAnswers.getChildren().addAll(questionField);
        for (Button button : answerButtons){
            vboxQuestionWithAnswers.getChildren().addAll(button);
        }
        vboxQuestionWithAnswers.setAlignment(Pos.TOP_CENTER);

        StackPane root = new StackPane();
        root.getChildren().addAll(vboxQuestionWithAnswers);
        scene = new Scene(root, 600, 250);
        stage.setTitle("Quiz App");
        stage.setScene(scene);
        stage.show();
    }

    void resultStage(Stage stage, RoundResult roundResult){
        StackPane root = new StackPane();
        if (roundResult == null){
            Text notFinishText = new Text("Not all players have finished answering");
            root.getChildren().addAll(notFinishText);
        } else {
            Text resultHeaderText = new Text("Results: ");
            resultHeaderText.setTextAlignment(TextAlignment.CENTER);
            Set<String> players = roundResult.getRoundresult().keySet();
            VBox resultVBox = new VBox();
            resultVBox.getChildren().addAll(resultHeaderText);
            for (String player : players) {
                Text playerText = new Text(player);
                Text resultText;
                if (roundResult.getRoundresult().get(player)) {
                    resultText = new Text(" answered correctly");
                }else {
                    resultText = new Text(" answered incorrectly");
                }
                HBox resultHBox = new HBox();
                resultHBox.getChildren().addAll(playerText, resultText);
                resultHBox.setAlignment(Pos.CENTER);
                resultVBox.getChildren().add(resultHBox);
            }
            resultVBox.setAlignment(Pos.CENTER);
            root.getChildren().addAll(resultVBox);
        }
        scene = new Scene(root, 600, 250);
        stage.setTitle("Quiz App");
        stage.setScene(scene);
        stage.show();
    }

    void gameResultStage(Stage stage, GameResult gameResult){
        StackPane root = new StackPane();
            Text resultHeaderText = new Text("Amount of questions correct: ");
            resultHeaderText.setTextAlignment(TextAlignment.CENTER);
            Set<String> players = gameResult.getResult().keySet();
            VBox resultVBox = new VBox();
            resultVBox.getChildren().addAll(resultHeaderText);
            for (String player : players) {
                Text playerText = new Text(player);
                Text resultText = new Text(" has answered " + gameResult.getResult().get(player).toString() + " questions correctly this game");
                HBox resultHBox = new HBox();
                resultHBox.getChildren().addAll(playerText, resultText);
                resultHBox.setAlignment(Pos.CENTER);
                resultVBox.getChildren().add(resultHBox);
            }
            resultVBox.setAlignment(Pos.CENTER);
            root.getChildren().addAll(resultVBox);
        scene = new Scene(root, 600, 250);
        stage.setTitle("Quiz App");
        stage.setScene(scene);
        stage.show();
    }

    void restart(Stage stage) {
        cleanup();
        questionStage(stage);
    }

    void goQuestionStage(Stage stage) {
        cleanup();
        questionStage(stage);
    }

    void goResultStage(Stage stage, RoundResult roundResult) {
        cleanup();
        resultStage(stage, roundResult);
    }

    void goGameResultStage(Stage stage, GameResult gameResult) {
        cleanup();
        gameResultStage(stage, gameResult);
    }

    @Override
    public void start(final Stage primaryStage){
        setStage(primaryStage);
        startGame(primaryStage);

    }

    void setStage(Stage stage){
        this.stage = stage;
    }

    public void updateQuestionUI(Question question){
        this.question = question;
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                goQuestionStage(stage);
            }
        });
    }

    public void updateResultUI(RoundResult roundResult){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                goResultStage(stage, roundResult);
            }
        });
    }

    @Override
    public void updateGameResultUI(GameResult gameResult) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                goGameResultStage(stage, gameResult);
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
