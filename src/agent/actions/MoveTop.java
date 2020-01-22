package agent.actions;

import environment.Environment;
import environment.Manor;

public class MoveTop implements Action {

    @Override
    public int execute(Environment environment) {
        Manor manor = (Manor) environment;
        if(manor.getVacuumPosition().getY() != 0) {
            manor.getVacuumPosition().setY(manor.getVacuumPosition().getY() - 1);
        }
        return -1;
    }

}
