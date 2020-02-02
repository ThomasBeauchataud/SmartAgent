package agent.decision;

import agent.actions.*;
import environment.Environment;
import environment.Manor;

import java.util.Arrays;
import java.util.List;

/**
 * @since 01.02.2020
 * @author Thomas Beauchataud
 * This class contains all the logic and the exploration algorithm of an informed Agent
 */
@SuppressWarnings("Duplicates")
public class InformedAgentDecisionMaking extends AbstractAgentDecisionMaking {

    public InformedAgentDecisionMaking(Environment perfectState) {
        super(perfectState);
    }

    /**
     * Generate a List of Action to execute when the perfect state is not reached
     * @param environment Environment
     * @return Action[]
     */
    @Override
    public List<Action> getRealActionPlan(Environment environment) {
        //TODO Implements
        return null;
    }

    /**
     * Return true if the environment equals the perfect state of the Agent
     * @param environment Environment
     * @return boolean
     */
    @Override
    protected boolean isNotPerfectState(Environment environment) {
        Manor manor = (Manor)environment;
        Manor perfectManor = (Manor)this.perfectState;
        for(int i = 0 ; i < manor.getRooms().length ; i++) {
            for(int k = 0 ; k < manor.getRooms().length ; k++) {
                if(!perfectManor.getRooms()[i][k].equalsTo(manor.getRooms()[i][k])) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Load the list of possible Actions for a Node
     */
    @Override
    protected void loadActions() {
        this.possibleActions = Arrays.asList(
                new MoveLeft(),
                new MoveRight(),
                new MoveTop(),
                new MoveBottom(),
                new Aspire(),
                new PickUp()
        );
    }
}
