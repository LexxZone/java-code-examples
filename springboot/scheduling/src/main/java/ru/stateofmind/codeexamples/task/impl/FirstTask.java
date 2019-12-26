package ru.stateofmind.codeexamples.task.impl;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.stateofmind.codeexamples.task.Task;

import java.util.logging.Logger;

@Component   //  annotation enables spring to check events in
public class FirstTask implements Task {

    private Logger logger = Logger.getLogger(this.getClass().getSimpleName());
    private static String taskName = "FirstTask";

    public FirstTask(String taskName) {
        FirstTask.taskName = taskName;
    }

    public FirstTask() {
    }

    /**
     * @Scheduled - tells to spring start it each #fixedRate milliseconds.
     *
     * You can make few methods in this class like this one.
     * All of these tasks will work the same way.
     */
    @Scheduled(fixedRate = 3000)
    @Override
    public void start() {
        logger.info(String.join(" ","Task", taskName, "has started."));
        // do something
    }
}
