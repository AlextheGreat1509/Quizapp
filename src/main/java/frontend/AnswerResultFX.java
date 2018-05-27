package frontend;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import models.Question;

public class AnswerResultFX{
    private final StackPane root ; // or any other kind of pane, or  Group...

    public AnswerResultFX(Question question, final Stage primaryStage) {
        Text questionField = new Text(question.getQuestion());
        questionField.setTextAlignment(TextAlignment.CENTER);
        Button btnNextQuestion = new Button("Next Question");
        btnNextQuestion.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            }
        });
        root = new StackPane();
        root.getChildren().addAll(questionField,btnNextQuestion);

    }

    public Pane getRootPane() {
        return root ;
    }
}
