package environment;

import view.View;

import java.util.concurrent.TimeUnit;

/**
 * @since 01.02.2020
 * @author Thomas Beauchataud
 * This class represents the Manor (Environment of the Vaccum)
 */
@SuppressWarnings("InfiniteLoopStatement")
public class Manor implements Environment, Runnable {

    private Room[][] rooms;
    private Position vacuumPosition;

    public Manor() {
        loadRooms();
        loadVacuumPosition();
    }

    public Room[][] getRooms() {
        return rooms;
    }

    public Position getVacuumPosition() {
        return vacuumPosition;
    }

    /**
     * Update the view of the environment (Console, Windows)
     */
    @Override
    public void updateView() {
        View.getInstance().update(this);
    }

    /**
     * Return true if an Environment is equals fully equals to an other
     * @param environment Environment
     * @return boolean
     */
    @Override
    public boolean equalsTo(Environment environment) {
        Manor manor = (Manor)environment;
        Room[][] rooms = manor.getRooms();
        for(int i = 0 ; i < 5 ; i++) {
            for(int k = 0 ; k < 5 ; k++) {
                if(rooms[i][k].hasJewel() != this.rooms[i][k].hasJewel()
                || rooms[i][k].hasDust() != this.rooms[i][k].hasDust()) {
                    return false;
                }
            }
        }
        return manor.getVacuumPosition().getX() == this.getVacuumPosition().getX()
                && manor.getVacuumPosition().getY() == this.getVacuumPosition().getY();
    }

    /**
     * Copy the Environment (method implemented for the creation of Nodes)
     * @return Environment
     */
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

    /**
     * Run the life cycle of the Vacuum
     */
    @Override
    public void run() {
        try {
            while (true) {
                int sleepTime = (int)(Math.random()*4) + 3;
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

    /**
     * Create Rooms of the Manor
     */
    private void loadRooms() {
        rooms = new Room[5][5];
        for(int i = 0 ; i < 5 ; i++) {
            for(int k = 0 ; k < 5 ; k++) {
                rooms[i][k] = new Room();
            }
        }
    }

    /**
     * Initialize the Vacuum Position in the middle of the Manor
     */
    private void loadVacuumPosition() {
        vacuumPosition = new Position();
        vacuumPosition.setX(2);
        vacuumPosition.setY(2);
    }

}
