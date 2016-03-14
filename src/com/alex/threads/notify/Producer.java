package com.alex.threads.notify;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 *
 * @author Alex
 */
public class Producer implements Runnable {

    BlockingQueueSync queue;

    public Producer(BlockingQueueSync queue) {
        this.queue = queue;
        new Thread(this, "Producer").start();
    }

    public void run() {
        int i = 0;

        while (true) {
            queue.put(i++);
            if (i == 100) {
                break;
            }
        }
    }
}
