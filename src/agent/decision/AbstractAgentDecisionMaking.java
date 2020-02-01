package agent.decision;

import agent.actions.Action;
import environment.Environment;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("WeakerAccess")
public abstract class AbstractAgentDecisionMaking implements AgentDecisionMaking {

    protected Environment perfectState;

    public AbstractAgentDecisionMaking(Environment perfectState) {
        this.perfectState = perfectState;
    }

    /**
     * Generate a List of Action to execute
     * @param environment Environment
     * @return Action[]
     */
    @Override
    public List<Action> getActionPlan(Environment environment) {
        if(isNotPerfectState(environment)) {
            return getRealActionPlan(environment);
        }
        return new ArrayList<>();
    }

    /**
     * Generate a List of Action to execute when the perfect state is not reached
     * @param environment Environment
     * @return Action[]
     */
    protected abstract List<Action> getRealActionPlan(Environment environment);

    /**
     * Return true if the environment equals the perfect state of the Agent
     * @param environment Environment
     * @return boolean
     */
    protected abstract boolean isNotPerfectState(Environment environment);

}
