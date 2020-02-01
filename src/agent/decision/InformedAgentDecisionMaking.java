package agent.decision;

import agent.actions.*;
import environment.Environment;
import environment.Manor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @since 01.02.2020
 * @author Thomas Beauchataud
 * This class contains all the logic of an informed Agent
 */
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
        List<Node> nodes = new ArrayList<>();
        nodes.add(new Node(environment));
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

    /**
     * Return false if a List of Node has a solution and clear it before inserting the solution in it
     * @param nodes Node[]
     * @return boolean
     */
    private boolean hasNoSolution(List<Node> nodes) {
        Node validNode = null;
        for(Node node : nodes) {
            if(node.getState().equalsTo(perfectState)) {
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
