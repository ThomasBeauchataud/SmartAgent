package agent.actions;

import environment.Environment;

/**
 * @since 01.02.2020
 * @author Thomas Beauchataud
 * This class represents an Action that an Agent can execute on his Environment
 */
public interface Action {

    /**
     * Execute an action of an Agent on his Environment and return the score modification for the agent
     * @param environment Environment
     * @return int
     */
    int execute(Environment environment);

}
