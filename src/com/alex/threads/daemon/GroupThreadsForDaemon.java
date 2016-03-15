package com.alex.threads.daemon;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 *
 * @author Alex
 */
public class GroupThreadsForDaemon implements Runnable {

    private BlockingQueue<String> queue = new ArrayBlockingQueue<String>(100);
    private int size = 0;
    public final static ThreadGroup GROUP = new ThreadGroup("Daemon demo");

    public GroupThreadsForDaemon(BlockingQueue<String> queue, int size) {
        this.queue = queue;
        this.size = size;
        new Thread(GROUP, this, "Thread main of group").start();
    }

    @Override
    public void run() {
        for (int i = 0; i < size; i++) {
            System.out.println("Thread " + i + " started");
            new ThreadForDaemon(queue, GROUP, "Thread " + i);
        }
    }

}
