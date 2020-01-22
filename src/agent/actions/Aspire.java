package agent.actions;

import environment.Environment;
import environment.Manor;
import environment.Room;

public class Aspire implements Action {

    @Override
    public int execute(Environment environment) {
        Manor manor = (Manor) environment;
        Room[][] rooms = ((Manor) environment).getRooms();
        int total = 0;
        if(rooms[manor.getVacuumPosition().getX()][manor.getVacuumPosition().getY()].hasDust()) {
            rooms[manor.getVacuumPosition().getX()][manor.getVacuumPosition().getY()].removeDust();
            total += 10;
        }
        if(rooms[manor.getVacuumPosition().getX()][manor.getVacuumPosition().getY()].hasJewel()) {
            rooms[manor.getVacuumPosition().getX()][manor.getVacuumPosition().getY()].removeJewel();
            total -= 20;
        }
        return total;
    }

}
