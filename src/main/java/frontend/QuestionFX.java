package frontend;

import client.IUILogic;
import client.IWebSocketClient;
import client.UILogic;
import client.WebSocketClient;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import models.Answer;
import models.Question;

import java.util.ArrayList;


public class QuestionFX extends Application {
    private IUILogic logic = new UILogic(new WebSocketClient());
    Scene scene;
    Scene sceneTest;
    Question question;


    void cleanup() {
        // stop animations reset model ect.
    }

    void startGame(Stage stage) {
        question = logic.GetQuestion();
        final Text questionField = new Text(question.getQuestion());
        ArrayList<Button> answerButtons = new ArrayList<Button>();
        for (final Answer answer : question.getAnswers()){
            Button button = new Button(answer.getAnswer());
            button.setTextAlignment(TextAlignment.CENTER);
            button.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                    logic.ProcessAnswer(answer);
                    stage.setScene(sceneTest);
                }
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
    public void start(final Stage primaryStage) {

        startGame(primaryStage);

    }

    public static void main(String[] args) {
        launch(args);
    }
}
