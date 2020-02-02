package agent.decision;

import agent.actions.Action;
import environment.Environment;

import java.util.List;

@SuppressWarnings("WeakerAccess")
public abstract class Node {

    protected List<Action> actions;
    protected Environment state;
    protected int score;

    public List<Action> getActions() {
        return actions;
    }

    public Environment getState() {
        return state;
    }

    public int getScore() {
        return score;
    }

    public abstract List<Node> expand(List<Action> actions);

}
