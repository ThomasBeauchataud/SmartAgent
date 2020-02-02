package agent.decision;

import agent.actions.*;
import environment.Environment;
import environment.Manor;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("WeakerAccess")
public class ManorNode extends Node {

    public ManorNode(Environment state) {
        this.state = state.copy();
        this.actions = new ArrayList<>();
    }

    public ManorNode(Environment state, List<Action> actions, Action action, int score) {
        this.state = state.copy();
        this.score = score;
        this.actions = new ArrayList<>();
        this.actions.addAll(actions);
        this.score += action.execute(this.state);
        this.actions.add(action);
    }

    @Override
    public List<Node> expand(List<Action> actions) {
        List<Node> nodes = new ArrayList<>();
        for(Action action : this.filterPossibleActions(actions)) {
            nodes.add(new ManorNode(this.state, this.actions, action, this.score));
        }
        return nodes;
    }

    private List<Action> filterPossibleActions(List<Action> actions) {
        Manor manor = (Manor)state;
        List<Action> finalActions = new ArrayList<>();
        for(Action action : actions) {
            if(!(action.getClass() == MoveTop.class
                    && (manor.getVacuumPosition().getY() == 0
                        || (this.actions.size() > 0 && this.actions.get(0).getClass() == MoveBottom.class)))
            && !(action.getClass() == MoveBottom.class
                    && (manor.getVacuumPosition().getY() == 4
                        || (this.actions.size() > 0 && this.actions.get(0).getClass() == MoveTop.class)))
            && !(action.getClass() == MoveLeft.class
                    && (manor.getVacuumPosition().getX() == 0
                        || (this.actions.size() > 0 && this.actions.get(0).getClass() == MoveRight.class)))
            && !(action.getClass() == MoveRight.class
                    && (manor.getVacuumPosition().getY() == 4
                        || (this.actions.size() > 0 && this.actions.get(0).getClass() == MoveLeft.class)))) {
                finalActions.add(action);
            }
        }
        return finalActions;
    }

}
