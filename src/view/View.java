package view;

import environment.Manor;
import environment.Position;
import environment.Room;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;

public class View {

    private static View view;
    
    private GridPane manorView;
    private Text actionPlanView;
    private Text scoreView;

    public View(Stage primaryStage) {
        try {
            BorderPane pane = new BorderPane();
            manorView = new GridPane();
            pane.setCenter(manorView);
            actionPlanView = new Text();
            actionPlanView.setFont(new Font(20));
            scoreView = new Text();
            scoreView.setFont(new Font(20));
            pane.setTop(actionPlanView);
            pane.setBottom(scoreView);
            for (int i = 0; i < 5 ; i++) {
                ColumnConstraints column = new ColumnConstraints(150);
                RowConstraints row = new RowConstraints(150);
                manorView.getColumnConstraints().add(column);
                manorView.getRowConstraints().add(row);
            }
            manorView.setGridLinesVisible(true);
            manorView.getChildren().add(new ImageView(new Image(
                    new File(System.getProperty("user.dir") + "/resources/vacuum.png").toURI().toString())));
            primaryStage.setTitle("Smart Agent");
            Scene scene = new Scene(pane, 750, 805);
            primaryStage.setScene(scene);
            primaryStage.show();
            for(int i = 0 ; i < 5 ; i++) {
                for (int k = 0; k < 5; k++) {
                    manorView.getChildren().add(new ImageView(new Image(
                            new File(System.getProperty("user.dir") + "/resources/dust.png").toURI().toString()
                    )));
                    GridPane.setConstraints(manorView.getChildren().get(manorView.getChildren().size() - 1), k, i);
                    manorView.getChildren().add(new ImageView(new Image(
                            new File(System.getProperty("user.dir") + "/resources/jewel.png").toURI().toString()
                    )));
                    GridPane.setConstraints(manorView.getChildren().get(manorView.getChildren().size() - 1), k, i);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error while trying to load the scene");
            System.exit(1);
        }
    }

    public GridPane getManorView() {
        return manorView;
    }

    public Text getActionPlanView() {
        return actionPlanView;
    }

    public Text getScoreView() {
        return scoreView;
    }

    public void update(Manor manor) {
        Position vacuumPosition = manor.getVacuumPosition();
        Room[][] rooms = manor.getRooms();
        GridPane root = View.getInstance().getManorView();
        GridPane.setConstraints(root.getChildren().get(1), vacuumPosition.getY(), vacuumPosition.getX());
        for(int i = 0 ; i < rooms.length ; i++) {
            for(int k = 0 ; k < rooms.length ; k++) {
                root.getChildren().get(i * 10 + k * 2 + 2).setVisible(rooms[i][k].hasDust());
                root.getChildren().get(i * 10 + k * 2 + 3).setVisible(rooms[i][k].hasJewel());
            }
        }
    }

    public static void setInstance(Stage primaryStage) {
        view = new View(primaryStage);
    }

    public static View getInstance() {
        return view;
    }

}
