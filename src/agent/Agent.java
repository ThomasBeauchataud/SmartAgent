package agent;

import environment.Environment;

public interface Agent {

    void detect (Environment environment);

    void generateActionPlan();

    void executeActionPlan(Environment environment);

    boolean hasRealState(Environment environment);

    boolean hasActionPlan();

}
