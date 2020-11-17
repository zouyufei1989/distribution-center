package com.money.TestTask;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestTask {

    @Scheduled(fixedRate = 1000)
    public void task1() {
        System.out.println(1);
    }

}
