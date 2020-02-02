package environment;

/**
 * @since 01.02.2020
 * @author Thomas Beauchataud
 * This class represents an Environment in which an Agent is evolving
 */
public interface Environment {

    /**
     * Update the view of the environment (Console, Windows)
     */
    void updateView();

    /**
     * Return true if an Environment is equals fully equals to an other
     * @param environment Environment
     * @return boolean
     */
    boolean equalsTo(Environment environment);

    /**
     * Copy the Environment (method implemented for the creation of Nodes)
     * @return Environment
     */
    Environment copy();

}
