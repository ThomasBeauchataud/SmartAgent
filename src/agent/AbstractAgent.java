package agent;

import agent.actions.Action;
import agent.decision.AgentDecisionMaking;
import environment.Environment;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @since 01.02.2020
 * @author Thomas Beauchataud
 * This abstract class is a generic Agent class
 */
@SuppressWarnings({"WeakerAccess","InfiniteLoopStatement"})
public abstract class AbstractAgent implements Agent, Runnable {

    protected Environment environment;
    protected Environment state;
    protected List<Action> actionPlan;
    protected AgentDecisionMaking agentDecisionMaking;
    protected int score;

    public AbstractAgent(Environment environment, AgentDecisionMaking agentDecisionMaking) {
        this.environment = environment;
        this.actionPlan = new ArrayList<>();
        this.agentDecisionMaking = agentDecisionMaking;
        this.score = 0;
    }

    /**
     * The Agent detect the environment and update his state
     * @param environment Environment
     */
    @Override
    public void detect(Environment environment) {
        state = environment;
    }

    /**
     * The Agent generate an Action plan with his state
     */
    @Override
    public void generateActionPlan() {
        this.actionPlan = agentDecisionMaking.getActionPlan(state);
        if(actionPlan == null) {
            System.out.println("No action plan for the agent");
            return;
        }
        System.out.print("New action plan for the agent : ");
        for(Action action : actionPlan) {
            System.out.print(action.getClass().getSimpleName() + " / ");
            System.out.println();
        }
    }

    /**
     * The Agent execute the first Action of his Action plan
     * @param environment Environment
     */
    @Override
    public void executeActionPlan(Environment environment) {
        score += actionPlan.get(0).execute(environment);
        actionPlan.remove(0);
        state = environment;
        environment.updateView();
        System.out.println("New score for the agent : " + score);
    }

    /**
     * Return true if the state of the Agent is the real environment
     * @param environment Environment
     * @return boolean
     */
    @Override
    public boolean hasRealState(Environment environment) {
        if(state == null) {
            return false;
        }
        return state.equalsTo(environment);
    }

    /**
     * Return true if the Agent has an Action plan
     * @return boolean
     */
    @Override
    public boolean hasActionPlan() {
        return actionPlan.size() > 0;
    }

    /**
     * Execute the life cycle of the Agent
     */
    @Override
    public void run() {
        try {
            while (true) {
                if (hasRealState(environment) && hasActionPlan()) {
                    executeActionPlan(environment);
                    TimeUnit.SECONDS.sleep(1);
                } else {
                    TimeUnit.SECONDS.sleep(1);
                    detect(environment);
                    generateActionPlan();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
