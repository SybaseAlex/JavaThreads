
package com.alex.threads.daemon;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 *
 * @author Alex
 */
public class ThreadForDaemon implements Runnable {
    private BlockingQueue<String> queue = new ArrayBlockingQueue<String>(100);
    public ThreadForDaemon(BlockingQueue<String> queue, ThreadGroup group, String name) {
        this.queue = queue;
        new Thread(group, this, name).start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println(Thread.currentThread().getName() + "put i: " + i);
                queue.add(Thread.currentThread().getName() + " i: " + i);
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
               ex.printStackTrace();
            }            
        }
    }
    
}
