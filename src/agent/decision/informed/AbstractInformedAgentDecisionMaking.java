package agent.decision.informed;

import agent.actions.Action;
import agent.decision.AbstractAgentDecisionMaking;
import agent.decision.nodes.Node;
import environment.Environment;

import java.util.ArrayList;
import java.util.List;

/**
 * @since 01.02.2020
 * @author Thomas Beauchataud
 * This class contains the generic logic and exploration algorithm of an informed Agent
 */
@SuppressWarnings({"Duplicates","WeakerAccess"})
public abstract class AbstractInformedAgentDecisionMaking extends AbstractAgentDecisionMaking {

    public AbstractInformedAgentDecisionMaking(Environment perfectState) {
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
            List<Node> recentNodes = nodes.get(0).expand(this.possibleActions);
            nodes.remove(0);
            nodes.addAll(recentNodes);
            filterDuplicates(nodes);
            orderNodes(nodes);
        }
        return nodes.get(0).getActions();
    }

    /**
     * Order a list of Nodes by their score
     * @param nodes Node[]
     */
    private void orderNodes(List<Node> nodes) {
        for (int i = 0; i < nodes.size(); i++) {
            for(int k = 0 ; k < nodes.size() - i - 1; k++) {
                if(nodes.get(k).getScore() < nodes.get(k + 1).getScore()) {
                    Node temp = nodes.get(k);
                    nodes.set(k, nodes.get(k + 1));
                    nodes.set(k + 1, temp);
                }
            }
        }
    }

    /**
     * Remove duplicates Node
     * We keep the Node with the best score
     * @param nodes Node
     */
    private void filterDuplicates(List<Node> nodes) {
        try {
            for (int i = 0; i < nodes.size(); i++) {
                for (int k = i + 1; k < nodes.size(); k++) {
                    if (nodes.get(i).getState().equalsTo(nodes.get(k).getState())) {
                        if (nodes.get(i).getScore() <= nodes.get(k).getScore()) {
                            nodes.remove(i);
                            if(i != 0) {
                                i--;
                            }
                            k--;
                        } else {
                            nodes.remove(k);
                            if(i != 0) {
                                i--;
                            }
                            k--;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
