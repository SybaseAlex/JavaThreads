
package com.alex.threads.sync;

/**
 *
 * @author Alex
 */
public class ClassWithSyncMethods {
    
    synchronized public void m1() throws InterruptedException {
        wait(10000);
    }
    
    synchronized public void m2() throws InterruptedException {
        wait(10000);
    }
    
}
