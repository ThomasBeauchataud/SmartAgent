package agent.decision;

import agent.actions.Action;
import environment.Environment;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("WeakerAccess")
public class Node {

    private List<Action> actions;
    private Environment state;
    private int score;

    public Node(Environment state) {
        this.state = state;
    }

    public Node(Environment state, List<Action> actions, Action action, int score) {
        this.state = state;
        this.score = score;
        this.actions = actions;
        this.score += action.execute(state);
        actions.add(action);
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

    public List<Node> expand(List<Action> actions) {
        List<Node> nodes = new ArrayList<>();
        for(Action action : actions) {
            if(!action.equals(this.actions.get(this.actions.size()-1))) {
                nodes.add(new Node(this.state, this.actions, action, this.score));
            }
        }
        return nodes;
    }

}
