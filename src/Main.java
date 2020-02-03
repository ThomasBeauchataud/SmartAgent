import agent.Agent;
import agent.Vacuum;
import environment.Environment;
import environment.Manor;
import javafx.application.Application;
import javafx.stage.Stage;
import view.View;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        View.setInstance(primaryStage);
        Environment environment = new Manor();
        environment.updateView();
        Agent agent = new Vacuum(environment, new Manor());
        new Thread((Runnable)environment).start();
        new Thread((Runnable)agent).start();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
