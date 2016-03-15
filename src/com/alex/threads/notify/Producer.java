package com.alex.threads.notify;

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
