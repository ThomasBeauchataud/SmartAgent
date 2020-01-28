package environment;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.io.File;

public class Manor implements Environment, Runnable {

    private Room[][] rooms;
    private Position vacuumPosition;
    private GridPane root;

    public Manor(Stage primaryStage) {
        loadRoot(primaryStage);
        loadRooms();
        loadVacuumPosition();
        updateView();
    }

    @Override
    public void updateView() {
        GridPane.setConstraints(root.getChildren().get(1), vacuumPosition.getX() * 150, vacuumPosition.getY() * 150);
    }

    @Override
    public boolean equalsTo(Environment environment) {
        return false;
    }

    @Override
    public void run() {
        //TODO Implements environment update
    }

    public Room[][] getRooms() {
        return rooms;
    }

    public Position getVacuumPosition() {
        return vacuumPosition;
    }

    private void loadRoot(Stage primaryStage) {
        try {
            root = new GridPane();
            for (int i = 0; i < 5 ; i++) {
                ColumnConstraints column = new ColumnConstraints(150);
                RowConstraints row = new RowConstraints(150);
                root.getColumnConstraints().add(column);
                root.getRowConstraints().add(row);
            }
            root.setGridLinesVisible(true);
            root.getChildren().add(new ImageView(new Image(
                    new File(System.getProperty("user.dir") + "/resources/vacuum.png").toURI().toString())));
            primaryStage.setTitle("Smart Agent");
            Scene scene = new Scene(root, 750, 750);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error while trying to load the scene");
            System.exit(1);
        }
    }

    private void loadRooms() {
        rooms = new Room[5][5];
        for(int i = 0 ; i < 5 ; i++) {
            for(int k = 0 ; k < 5 ; k++) {
                rooms[i][k] = new Room();
            }
        }
    }

    private void loadVacuumPosition() {
        vacuumPosition = new Position();
        vacuumPosition.setX(2);
        vacuumPosition.setY(2);
    }

}
