package agent;

import agent.actions.Action;
import environment.Environment;

import java.util.List;

public interface AgentDecisionMakingInterface {

    List<Action> getActionPlan(Environment environment);

}
