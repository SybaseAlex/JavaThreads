package com.alex.threads.daemon;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alex
 */
public class DemonDemo extends Thread {

    private BlockingQueue<String> queue = new ArrayBlockingQueue<String>(100);
    public DemonDemo(BlockingQueue<String> queue) {       
        super("Daemon demo thread");
        this.queue = queue;
        setDaemon(true);
        start();
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                System.out.println("daemon: " + queue);
                while(!queue.isEmpty()) {
                    System.out.println("poll:"+queue.poll());
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
