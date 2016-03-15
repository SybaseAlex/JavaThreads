package com.alex.threads.notify;

/**
 *
 * @author Alex
 */
public class Consumer implements Runnable {

    private BlockingQueueSync queue;

    public Consumer(BlockingQueueSync queue) {
        this.queue = queue;
        new Thread(this, "Consumer").start();
    }

    public void run() {
        while (true) {
            String value = queue.get();
            if("value:99".equals(value)) {
                break;
            }
        }
    }
}
