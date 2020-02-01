package agent.decision;

import agent.actions.Action;
import environment.Environment;

import java.util.List;

/**
 * @since 01.02.2020
 * @author Thomas Beauchataud
 * This class contains all the logic of a none informed Agent
 */
public class NoneInformedAgentDecisionMaking implements AgentDecisionMaking {

    /**
     * Generate a List of Action to execute
     * @param environment Environment
     * @return Action[]
     */
    @Override
    public List<Action> getActionPlan(Environment environment) {
        return null;
    }

}
