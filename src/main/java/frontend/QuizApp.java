package frontend;

import client.IUILogic;
import client.UILogic;
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

import javax.xml.transform.Source;
import java.beans.EventHandler;
import java.util.Date;


public class QuizApp extends Application {
    private IUILogic logic = new UILogic();


    @Override
    public void start(Stage primaryStage) {

        final Question question = logic.GetQuestion();
        Text questionField = new Text(question.getQuestion());
        questionField.setTextAlignment(TextAlignment.CENTER);
        final Button btnAnswer1 = new Button(question.getAnswers().get(0).getAnswer());
        btnAnswer1.setTextAlignment(TextAlignment.CENTER);
        btnAnswer1.setOnAction(new javafx.event.EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                logic.ProcessAnswer(question.getAnswers().get(0));
            }
        });
        final Button btnAnswer2 = new Button(question.getAnswers().get(1).getAnswer());
        btnAnswer2.setTextAlignment(TextAlignment.CENTER);
        btnAnswer2.setOnAction(new javafx.event.EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                logic.ProcessAnswer(question.getAnswers().get(1));
            }
        });
        final Button btnAnswer3 = new Button(question.getAnswers().get(2).getAnswer());
        btnAnswer3.setTextAlignment(TextAlignment.CENTER);
        btnAnswer3.setOnAction(new javafx.event.EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                logic.ProcessAnswer(question.getAnswers().get(2));
            }
        });
        final Button btnAnswer4 = new Button(question.getAnswers().get(3).getAnswer());
        btnAnswer4.setTextAlignment(TextAlignment.CENTER);
        btnAnswer4.setOnAction(new javafx.event.EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                logic.ProcessAnswer(question.getAnswers().get(3));
            }
        });
        VBox vboxQuestionWithAnswers = new VBox();
        vboxQuestionWithAnswers.getChildren().addAll(questionField,btnAnswer1,btnAnswer2,btnAnswer3,btnAnswer4);
        vboxQuestionWithAnswers.setAlignment(Pos.TOP_CENTER);

        StackPane root = new StackPane();
        root.getChildren().addAll(vboxQuestionWithAnswers);
        Scene scene = new Scene(root, 600, 250);

        primaryStage.setTitle("Quiz App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
