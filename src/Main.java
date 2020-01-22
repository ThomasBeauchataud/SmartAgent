import agent.Agent;
import agent.AgentThread;
import agent.Vacuum;
import environment.Environment;
import environment.Manor;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Environment environment = new Manor(primaryStage);
        Agent agent = new Vacuum();
        new Thread(new AgentThread(environment, agent)).start();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
