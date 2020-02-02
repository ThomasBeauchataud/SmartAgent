package environment;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.io.File;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("InfiniteLoopStatement")
public class Manor implements Environment, Runnable {

    private Room[][] rooms;
    private Position vacuumPosition;
    private GridPane root;

    public Manor() {
        loadRooms();
        loadVacuumPosition();
    }

    public Manor(Stage primaryStage) {
        loadRoot(primaryStage);
        loadRooms();
        loadVacuumPosition();
        updateView();
    }

    @Override
    public void updateView() {
        GridPane.setConstraints(root.getChildren().get(1), vacuumPosition.getX() * 150, vacuumPosition.getY() * 150);
        //Temporary loop to print view on the console
        System.out.println();
        for(int i = 0 ; i < 5 ; i++) {
            for(int k = 0 ; k < 5 ; k++) {
                if(vacuumPosition.getX() == i && vacuumPosition.getY() == k) {
                    System.out.print("V");
                } else {
                    Room room = rooms[i][k];
                    if(room.hasDust()) {
                        System.out.print("D");
                    } else {
                        if(room.hasJewel()) {
                            System.out.print("J");
                        } else {
                            System.out.print("R");
                        }
                    }
                }
            }
            System.out.println();
        }
    }

    @Override
    public boolean equalsTo(Environment environment) {
        Room[][] rooms = ((Manor)environment).getRooms();
        for(int i = 0 ; i < 5 ; i++) {
            for(int k = 0 ; k < 5 ; k++) {
                if(rooms[i][k].hasJewel() != this.rooms[i][k].hasJewel() || rooms[i][k].hasDust() != this.rooms[i][k].hasDust()) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public Environment copy() {
        Manor manor = new Manor();
        manor.getVacuumPosition().setY(this.vacuumPosition.getY());
        manor.getVacuumPosition().setX(this.vacuumPosition.getX());
        for(int i = 0 ; i < 5 ; i++) {
            for(int k = 0 ; k < 5 ; k++) {
                manor.getRooms()[i][k].setDust(this.rooms[i][k].hasDust());
                manor.getRooms()[i][k].setJewel(this.rooms[i][k].hasJewel());
            }
        }
        return manor;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int sleepTime = (int)(Math.random()*5) + 3;
                TimeUnit.SECONDS.sleep(sleepTime);
                int x = (int)(Math.random()*5);
                if(x < 1) {
                    x = 1;
                }
                int y = (int)(Math.random()*5);
                if(y < 1) {
                    y = 1;
                }
                if((int)(Math.random()*2) == 1) {
                    rooms[x-1][y-1].putDust();
                } else {
                    rooms[x-1][y-1].putJewel();
                }
                updateView();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
