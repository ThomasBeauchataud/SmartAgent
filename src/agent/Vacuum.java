package agent;

import agent.actions.*;
import agent.decision.informed.InformedAgentDecisionMaking;
import agent.decision.notinformed.NotInformedAgentDecisionMaking;
import environment.Environment;

import java.util.Arrays;
import java.util.List;

/**
 * @since 01.02.2020
 * @author Thomas Beauchataud
 * This Agent run in the Manor environment
 * All methods are implemented is the generic class : AbsractAgent
 */
public class Vacuum extends AbstractAgent {

    public Vacuum(Environment environment, Environment perfectState) {
        super(
                environment,
                new NotInformedAgentDecisionMaking(perfectState, getActions())
                //new InformedAgentDecisionMaking(perfectState, getActions())
        );
    }

    /**
     * Load the list of possible Actions for a Vacuum
     */
    private static List<Action> getActions() {
        return Arrays.asList(
                new MoveLeft(),
                new MoveRight(),
                new MoveTop(),
                new MoveBottom(),
                new Aspire(),
                new PickUp()
        );
    }

}
