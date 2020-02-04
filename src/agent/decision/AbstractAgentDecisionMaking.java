package agent.decision;

import agent.actions.*;
import agent.decision.nodes.Node;
import environment.Environment;

import java.util.ArrayList;
import java.util.List;

/**
 * @since 01.02.2020
 * @author Thomas Beauchataud
 * This class contains generic methods for the logic and exploration algorithm of any kinf of Agents
 */
public abstract class AbstractAgentDecisionMaking implements AgentDecisionMaking {

    protected Environment perfectState;
    protected List<Action> possibleActions;

    public AbstractAgentDecisionMaking(Environment perfectState, List<Action> actions) {
        this.perfectState = perfectState;
        this.possibleActions = actions;
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
     * Return the first Node to expand
     * @param environment Environment
     * @return Node
     */
    protected abstract Node getFirstNode(Environment environment);

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

    /**
     * Return false if a List of Node has a solution and clear it before inserting the solution in it
     * @param nodes Node[]
     * @return boolean
     */
    protected boolean hasNoSolution(List<Node> nodes) {
        Node validNode = null;
        for(Node node : nodes) {
            if(!isNotPerfectState(node.getState())) {
                if(validNode == null) {
                    validNode = node;
                } else {
                    if(validNode.getScore() < node.getScore()) {
                        validNode = node;
                    }
                }
            }
        }
        if(validNode == null) {
            return true;
        } else {
            nodes.clear();
            nodes.add(validNode);
            return false;
        }
    }

}
