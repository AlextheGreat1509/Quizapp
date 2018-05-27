package frontend;

import client.IUILogic;
import client.UILogic;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import models.Answer;
import models.Question;

import javax.xml.transform.Source;
import java.beans.EventHandler;
import java.util.ArrayList;
import java.util.Date;


public class QuestionFX extends Application {
    private IUILogic logic = new UILogic();
    Scene scene;
    Scene sceneTest;

    @Override
    public void start(final Stage primaryStage) {
        final Question question = logic.GetQuestion();
        Text questionField = new Text(question.getQuestion());
        ArrayList<Button> answerButtons = new ArrayList<Button>();
        for (final Answer answer : question.getAnswers()){
            Button button = new Button(answer.getAnswer());
            button.setTextAlignment(TextAlignment.CENTER);
            button.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                    logic.ProcessAnswer(answer);
                    primaryStage.setScene(sceneTest);
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
        rootTest.getChildren().addAll(questionField,btn);
        btn.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                primaryStage.setScene(scene);
            }
        });
        scene = new Scene(root, 600, 250);
        sceneTest = new Scene(rootTest, 600, 250);
        primaryStage.setTitle("Quiz App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
