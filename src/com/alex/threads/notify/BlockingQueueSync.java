package com.alex.threads.notify;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 *
 * @author Alex
 */
public class BlockingQueueSync {

    BlockingQueue<String> queue = new ArrayBlockingQueue<String>(100);
    
    public BlockingQueueSync(BlockingQueue<String> queue){
       this.queue = queue; 
    }

    synchronized String get() {
//        if (queue.isEmpty()) {
//            try {
//                wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        String value = queue.poll();
        System.out.println("Got: " + value);
        //notify();
        return value;
    }

    synchronized void put(int n) {
//        if (!queue.isEmpty()) {
//            try {
//                wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        String value = "value:" + n;
        System.out.println("Put: " + value);
        queue.add(value);
//        notify();
    }
}
