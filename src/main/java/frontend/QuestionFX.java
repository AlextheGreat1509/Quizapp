package frontend;

import client.IUILogic;
import client.UILogic;
import client.authentication.AuthenticationController;
import client.authentication.IAuthenticationController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import models.Answer;
import models.PlayerFound;
import models.Question;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


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
        TextField textPassword = new TextField("Password");
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
                resultStage(stage);
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

    void resultStage(Stage stage){
        StackPane root = new StackPane();
        Button btn = new Button("Proceed");
        root.getChildren().addAll(btn);
        btn.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                restart(stage);
            }
        });
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

    @Override
    public void start(final Stage primaryStage){
        //logic.Connect();
        //try {
        //    TimeUnit.SECONDS.sleep(1);
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //}
        //question = logic.GetQuestion();
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

    public static void main(String[] args) {
        launch(args);
    }
}
