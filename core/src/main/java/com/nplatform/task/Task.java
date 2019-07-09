package com.nplatform.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Task {

    @Scheduled(cron = "0/3 * * * * ?")
    public void start() {
        System. out.println( "task execing ,start to run ...");
    }
}
