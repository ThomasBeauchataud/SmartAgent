package agent.decision;

import agent.actions.Action;
import environment.Environment;

import java.util.List;

/**
 * @since 01.02.2020
 * @author Thomas Beauchataud
 * This class contains all the logic and exploration algorithms of an Agent
 */
public interface AgentDecisionMaking {

    /**
     * Generate a List of Action to execute
     * @param environment Environment
     * @return Action[]
     */
    List<Action> getActionPlan(Environment environment);

}
