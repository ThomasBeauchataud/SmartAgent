package agent;

import environment.Environment;

import java.util.concurrent.TimeUnit;

public class AgentThread implements Runnable {

    private Environment environment;
    private Agent agent;

    public AgentThread(Environment environment, Agent agent) {
        this.environment = environment;
        this.agent = agent;
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (agent.hasRealState(environment) && agent.hasActionPlan()) {
                    agent.executeActionPlan(environment);
                    TimeUnit.SECONDS.sleep(1);
                } else {
                    TimeUnit.SECONDS.sleep(1);
                    agent.detect(environment);
                    agent.generateActionPlan();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
