package agent.decision.informed;

import agent.actions.*;
import agent.decision.nodes.ManorNode;
import agent.decision.nodes.Node;
import environment.Environment;
import environment.Manor;

import java.util.List;

/**
 * @since 01.02.2020
 * @author Thomas Beauchataud
 * This class contains all the logic and the exploration algorithm of an informed Agent Vacuum in the Manor
 */
@SuppressWarnings("Duplicates")
public class InformedAgentDecisionMaking extends AbstractInformedAgentDecisionMaking {

    public InformedAgentDecisionMaking(Environment perfectState, List<Action> actions) {
        super(perfectState, actions);
    }

    /**
     * Return the first Node to expand
     * @param environment Environment
     * @return Node
     */
    @Override
    protected Node getFirstNode(Environment environment) {
        return new ManorNode(environment);
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
        for(int i = 0 ; i < manor.getRooms().length ; i++) {
            for(int k = 0 ; k < manor.getRooms().length ; k++) {
                if(!perfectManor.getRooms()[i][k].equalsTo(manor.getRooms()[i][k])) {
                    return true;
                }
            }
        }
        return false;
    }

}
