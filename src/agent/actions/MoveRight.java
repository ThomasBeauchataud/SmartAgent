package agent.actions;

import environment.Environment;
import environment.Manor;

public class MoveRight implements Action {

    @Override
    public int execute(Environment environment) {
        Manor manor = (Manor) environment;
        if(manor.getVacuumPosition().getX() != 4) {
            manor.getVacuumPosition().setY(manor.getVacuumPosition().getX() + 1);
        }
        return -1;
    }

}
