package agent.decision.nodes;

import agent.actions.*;
import environment.Environment;
import environment.Manor;

import java.util.ArrayList;
import java.util.List;

/**
 * @since 01.02.2020
 * @author Thomas Beauchataud
 * This class represents a Node of the Manor
 */
@SuppressWarnings("WeakerAccess")
public class ManorNode extends Node {

    public ManorNode(Environment state) {
        super(new ArrayList<>(), state.copy(), 0);
    }

    public ManorNode(Environment state, List<Action> actions, Action action, int score) {
        super(new ArrayList<>(), state.copy(), score);
        this.actions.addAll(actions);
        this.score += action.execute(this.state);
        this.actions.add(action);
    }

    /**
     * Expand a Node with possible actions and return new generated Nodes
     * The Action list is not filtered,
     *      they contain all Action that the Agent can execute careless of his environment
     *      the method filterPossibleActions() filterer impossible Action of the Agent cause of the environment
     * @param actions Action
     * @return Action[]
     */
    @Override
    public List<Node> expand(List<Action> actions) {
        List<Node> nodes = new ArrayList<>();
        for(Action action : this.filterPossibleActions(actions)) {
            nodes.add(new ManorNode(this.state, this.actions, action, this.score));
        }
        return nodes;
    }

    /**
     * Filter Action that an Agent can't execute cause of his environment
     * @param actions Action[]
     * @return Action[]
     */
    private List<Action> filterPossibleActions(List<Action> actions) {
        Manor manor = (Manor)state;
        List<Action> finalActions = new ArrayList<>();
        for(Action action : actions) {
            if(!(action.getClass() == MoveTop.class
                    && (manor.getVacuumPosition().getX() == 0
                        || (this.actions.size() > 0 && this.actions.get(0).getClass() == MoveBottom.class)))
            && !(action.getClass() == MoveBottom.class
                    && (manor.getVacuumPosition().getX() == 4
                        || (this.actions.size() > 0 && this.actions.get(0).getClass() == MoveTop.class)))
            && !(action.getClass() == MoveLeft.class
                    && (manor.getVacuumPosition().getY() == 0
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
