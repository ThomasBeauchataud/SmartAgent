package agent;

import environment.Environment;

import java.util.concurrent.TimeUnit;

@SuppressWarnings({"WeakerAccess","InfiniteLoopStatement"})
public abstract class AbstractAgent implements Agent, Runnable {

    private Environment environment;

    public AbstractAgent(Environment environment) {
        this.environment = environment;
    }

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
