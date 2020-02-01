package agent.decision;

import agent.actions.Action;
import environment.Environment;
import environment.Manor;

import java.util.List;

/**
 * @since 01.02.2020
 * @author Thomas Beauchataud
 * This class contains all the logic of a none informed Agent
 */
public class NoneInformedAgentDecisionMaking extends AbstractAgentDecisionMaking {

    public NoneInformedAgentDecisionMaking(Environment perfectState) {
        super(perfectState);
    }

    /**
     * Generate a List of Action to execute when the perfect state is not reached
     * @param environment Environment
     * @return Action[]
     */
    @Override
    public List<Action> getRealActionPlan(Environment environment) {
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
        return !manor.equalsTo(perfectManor);
    }
}
