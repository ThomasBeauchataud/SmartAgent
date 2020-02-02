package agent.actions;

import environment.Environment;
import environment.Manor;

/**
 * @since 01.02.2020
 * @author Thomas Beauchataud
 * This class represents the Aspire Action
 * When this action is executed : the Agent go top in the Manor and return a score modification of -1
 */
public class MoveTop implements Action {

    /**
     * Execute an action of an Agent on his Environment and return the score modification for the agent
     * @param environment Environment
     * @return int
     */
    @Override
    public int execute(Environment environment) {
        Manor manor = (Manor) environment;
        if(manor.getVacuumPosition().getY() != 0) {
            manor.getVacuumPosition().setY(manor.getVacuumPosition().getY() - 1);
        }
        return -1;
    }

}
