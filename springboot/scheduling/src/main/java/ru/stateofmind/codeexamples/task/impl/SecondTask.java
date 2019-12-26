package ru.stateofmind.codeexamples.task.impl;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.stateofmind.codeexamples.task.Task;

import java.util.logging.Logger;

@Component
public class SecondTask implements Task {

    private Logger logger = Logger.getLogger(this.getClass().getSimpleName());
    private static String taskName = "SecondTask";

    public SecondTask(String taskName) {
        SecondTask.taskName = taskName;
    }

    public SecondTask() {
    }

    /**
     * @Scheduled takes cron format as parameter.
     * Will run method each 5 seconds.
     */
    @Scheduled(cron = "*/5 * * * * *")
    @Override
    public void start() {
        logger.info(String.join(" ","Task", taskName, "has started."));
        // do something
    }
}
