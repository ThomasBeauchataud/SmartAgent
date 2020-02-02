package agent.decision.notinformed;

import agent.actions.Action;
import agent.decision.AbstractAgentDecisionMaking;
import agent.decision.nodes.Node;
import environment.Environment;

import java.util.ArrayList;
import java.util.List;

/**
 * @since 01.02.2020
 * @author Thomas Beauchataud
 * This class contains the generic logic and exploration algorithm of an not informed Agent
 */
@SuppressWarnings("WeakerAccess")
public abstract class AbstractNotInformedAgentDecisionMaking extends AbstractAgentDecisionMaking {

    public AbstractNotInformedAgentDecisionMaking(Environment perfectState) {
        super(perfectState);
    }

    /**
     * Generate a List of Action to execute when the perfect state is not reached
     * @param environment Environment
     * @return Action[]
     */
    @Override
    public List<Action> getRealActionPlan(Environment environment) {
        List<Node> nodes = new ArrayList<>();
        nodes.add(getFirstNode(environment));
        while(this.hasNoSolution(nodes)) {
            List<Node> recentNodes = new ArrayList<>();
            for(Node node : nodes) {
                recentNodes.addAll(node.expand(this.possibleActions));
            }
            nodes.clear();
            nodes.addAll(recentNodes);
        }
        return nodes.get(0).getActions();
    }

}
