package agent;

import agent.actions.Action;
import agent.decision.AgentDecisionMaking;
import environment.Environment;
import view.View;

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
        state = environment.copy();
    }

    /**
     * The Agent generate an Action plan with his state
     */
    @Override
    public void generateActionPlan() {
        this.actionPlan = agentDecisionMaking.getActionPlan(state);
        if(actionPlan.size() == 0) {
            View.getInstance().getActionPlanView().setText("No action plan : environment equals to the perfect state");
            return;
        }
        View.getInstance().getActionPlanView().setText(actionPlanToString());
    }

    /**
     * The Agent execute the first Action of his Action plan
     * @param environment Environment
     */
    @Override
    public void executeActionPlan(Environment environment) {
        score += actionPlan.get(0).execute(environment);
        actionPlan.get(0).execute(state);
        actionPlan.remove(0);
        environment.updateView();
        View.getInstance().getScoreView().setText("Score : " + score);
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

    private String actionPlanToString() {
        StringBuilder stringBuilder = new StringBuilder("Action plan : ");
        for(Action action : actionPlan) {
            stringBuilder.append(action.getClass().getSimpleName()).append(", ");
        }
        stringBuilder.delete(stringBuilder.length()-2, stringBuilder.length());
        return stringBuilder.toString();
    }

}
