
package com.alex.threads;

/**
 *
 * @author Alex
 */
public class RunableSimple implements Runnable{

    @Override
    public void run() {
        long sum = 0;
        for (int i = 0; i < 1000; i++) {
            sum += i;
        }
        System.out.println(Thread.currentThread().getName() + ": " + sum);
    }
    
}
