package agent;

import environment.Environment;

/**
 * @since 01.02.2020
 * @author Thomas Beauchataud
 * This class represents an Agent
 */
public interface Agent {

    /**
     * The Agent detect the environment and update his state
     * @param environment Environment
     */
    void detect (Environment environment);

    /**
     * The Agent generate an Action plan with his state
     */
    void generateActionPlan();

    /**
     * The Agent execute the first Action of his Action plan
     * @param environment Environment
     */
    void executeActionPlan(Environment environment);

    /**
     * Return true if the state of the Agent is the real environment
     * @param environment Environment
     * @return boolean
     */
    boolean hasRealState(Environment environment);

    /**
     * Return true if the Agent has an Action plan
     * @return boolean
     */
    boolean hasActionPlan();

}
