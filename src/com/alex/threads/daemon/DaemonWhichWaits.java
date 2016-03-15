
package com.alex.threads.daemon;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 *
 * @author Alex
 */
public class DaemonWhichWaits extends Thread {

    private BlockingQueue<String> queue = new ArrayBlockingQueue<String>(100);
    private boolean runDaemon = true;
    public DaemonWhichWaits(BlockingQueue<String> queue) {       
        super("Daemon demo thread");
        this.queue = queue;
        //setDaemon(true);
        start();
    }

    public void run() {
        while (runDaemon) {
            try {
                Thread threads[];
                int count = GroupThreadsForDaemon.GROUP.activeCount();
                threads = new Thread[count + 10];
                count = GroupThreadsForDaemon.GROUP.enumerate(threads);
                
                for (int i=0; i<count; i++) {
                    System.out.println(threads[i].getName()+", " + i);
                    threads[i].join();
                    showQueue();
                }               
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    private void showQueue() {
        while (!queue.isEmpty()) {
            System.out.println("poll:" + queue.poll());
        }
    }
}