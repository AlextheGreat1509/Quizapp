package frontend;

import client.IUILogic;
import client.UILogic;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import models.Question;


public class QuizApp extends Application {
    private IUILogic logic = new UILogic();


    @Override
    public void start(Stage primaryStage) {

        Question question = logic.GetQuestion();
        Text questionField = new Text(question.getQuestion());
        questionField.setTextAlignment(TextAlignment.CENTER);
        Button btnAnswer1 = new Button(question.getAnswers().get(0).getAnswer());
        btnAnswer1.setTextAlignment(TextAlignment.CENTER);
        Button btnAnswer2 = new Button(question.getAnswers().get(1).getAnswer());
        btnAnswer2.setTextAlignment(TextAlignment.CENTER);
        Button btnAnswer3 = new Button(question.getAnswers().get(2).getAnswer());
        btnAnswer3.setTextAlignment(TextAlignment.CENTER);
        Button btnAnswer4 = new Button(question.getAnswers().get(3).getAnswer());
        btnAnswer4.setTextAlignment(TextAlignment.CENTER);
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
