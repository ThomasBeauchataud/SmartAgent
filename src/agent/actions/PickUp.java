package agent.actions;

import environment.Environment;
import environment.Manor;
import environment.Room;

public class PickUp implements Action {

    /**
     * Execute an action of an Agent on his Environment and return the score modification for the agent
     * @param environment Environment
     * @return int
     */
    @Override
    public int execute(Environment environment) {
        Manor manor = (Manor) environment;
        Room[][] rooms = ((Manor) environment).getRooms();
        if(rooms[manor.getVacuumPosition().getX()][manor.getVacuumPosition().getY()].hasJewel()) {
            rooms[manor.getVacuumPosition().getX()][manor.getVacuumPosition().getY()].removeJewel();
            return 10;
        }
        return 0;
    }

}
