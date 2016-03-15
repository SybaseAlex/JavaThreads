package com.alex.threads.executorservice;

import java.util.concurrent.Callable;

/**
 *
 * @author Alex
 */
public class TaskWithResult implements Callable<String> {

    private int id;

    public TaskWithResult(int id) {
        this.id = id;
    }

    public String call() {
        for(int i = 0; i < 5; i++) {
            System.out.println("thread #" + id + " i:" + i);
        }
        return "result of TaskWithResult " + id;
    }
}
