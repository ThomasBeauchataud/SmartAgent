package agent;

import agent.decision.InformedAgentDecisionMaking;
import agent.decision.NoneInformedAgentDecisionMaking;
import environment.Environment;

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
                new NoneInformedAgentDecisionMaking(perfectState)
                //new InformedAgentDecisionMaking(perfectState);
        );
    }

}
