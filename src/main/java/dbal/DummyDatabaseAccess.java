package dbal;
import dbal.repositories.QuestionRepository;

public class DummyDatabaseAccess {
        public static void main(String[] args) {
            QuestionRepository test = new QuestionRepository();
            test.GetQuestion(1);
        }

}
