package agent.decision.nodes;

import agent.actions.Action;
import environment.Environment;

import java.util.List;

/**
 * @since 01.02.2020
 * @author Thomas Beauchataud
 * This abstract class is a generic Node class for exploration algorithms
 */
@SuppressWarnings("WeakerAccess")
public abstract class Node {

    protected List<Action> actions;
    protected Environment state;
    protected int score;

    public Node(List<Action> actions, Environment state, int score) {
        this.actions = actions;
        this.state = state;
        this.score = score;
    }

    public List<Action> getActions() {
        return actions;
    }

    public Environment getState() {
        return state;
    }

    public int getScore() {
        return score;
    }

    /**
     * Expand a Node with possible actions and return new generated Nodes
     * The Action list in parameter is not filtered,
     *      it contains all Action that the Agent can execute careless of his environment
     * @param actions Action
     * @return Action[]
     */
    public abstract List<Node> expand(List<Action> actions);

}
