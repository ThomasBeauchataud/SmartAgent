package agent;

import agent.actions.Action;
import agent.decision.AgentDecisionMaking;
import agent.decision.AgentDecisionMakingInterface;
import environment.Environment;

import java.util.ArrayList;
import java.util.List;

public class Vacuum extends AbstractAgent {

    private Environment state;
    private List<Action> actionPlan;
    private AgentDecisionMakingInterface agentDecisionMaking;
    private int score;

    public Vacuum(Environment environment) {
        super(environment);
        actionPlan = new ArrayList<>();
        agentDecisionMaking = new AgentDecisionMaking();
        score = 0;
    }

    @Override
    public void detect(Environment environment) {
        state = environment;
    }

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

    @Override
    public void executeActionPlan(Environment environment) {
        score += actionPlan.get(0).execute(environment);
        actionPlan.remove(0);
        state = environment;
        environment.updateView();
        System.out.println("New score for the agent : " + score);
    }

    @Override
    public boolean hasRealState(Environment environment) {
        if(state == null) {
            return false;
        }
        return state.equalsTo(environment);
    }

    @Override
    public boolean hasActionPlan() {
        return actionPlan.size() > 0;
    }

}
