package agent.actions;

import environment.Environment;
import environment.Manor;
import environment.Room;

/**
 * @since 01.02.2020
 * @author Thomas Beauchataud
 * This class represents the Aspire Action
 * When this action is executed :
 *      if there is jewel in the room, the jewel is removed and return a score modification of +10
 *      in any case, the Action has a score modification of -1
 */
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
        int total = -1;
        if(rooms[manor.getVacuumPosition().getX()][manor.getVacuumPosition().getY()].hasJewel()) {
            rooms[manor.getVacuumPosition().getX()][manor.getVacuumPosition().getY()].removeJewel();
            total += 10;
        }
        return total;
    }

}
