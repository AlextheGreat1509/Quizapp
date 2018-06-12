package frontend;

import client.IUILogic;
import client.UILogic;
import client.authentication.AuthenticationController;
import client.authentication.IAuthenticationController;
import javafx.application.Application;
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
import models.Question;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class QuestionFX extends Application implements IQuestionFX {
    private IUILogic logic = UILogic.getInstance();
    private Scene scene;
    private Scene sceneTest;
    private Question question;
    private Stage stage;
    private IAuthenticationController iAuthenticationController = new AuthenticationController();


    void cleanup() {
        // stop animations reset model ect.
    }

    void startGame(Stage stage){
        TextField textUsername = new TextField("Username");
        TextField textPassword = new TextField("Password");
        Button btnRegister = new Button("Register");
        btnRegister.setOnAction(event ->{
            iAuthenticationController.Register(textUsername.getText(), textPassword.getText());
        });
        Button btnLogin = new Button("Login");
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
                stage.setScene(sceneTest);
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
        StackPane rootTest = new StackPane();
        root.getChildren().addAll(vboxQuestionWithAnswers);
        Button btn = new Button("Proceed");
        rootTest.getChildren().addAll(btn);
        btn.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                restart(stage);
            }
        });
        scene = new Scene(root, 600, 250);
        sceneTest = new Scene(rootTest, 600, 250);
        stage.setTitle("Quiz App");
        stage.setScene(scene);
        stage.show();
    }

    void restart(Stage stage) {
        cleanup();
        startGame(stage);
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
        restart(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
