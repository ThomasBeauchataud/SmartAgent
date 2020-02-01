package agent;

import agent.decision.InformedAgentDecisionMaking;
import environment.Environment;

/**
 * @since 01.02.2020
 * @author Thomas Beauchataud
 * This Agent run in the Manor environment
 * All methods are implemented is the generic class : AbsractAgent
 */
public class Vacuum extends AbstractAgent {

    public Vacuum(Environment environment) {
        super(environment, new InformedAgentDecisionMaking());
    }

}
