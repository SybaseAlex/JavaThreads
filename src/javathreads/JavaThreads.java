
package javathreads;

import com.alex.threads.daemon.DaemonWhichWaits;
import com.alex.threads.daemon.DemonDemo;
import com.alex.threads.daemon.GroupThreadsForDaemon;
import com.alex.threads.RunableSimple;
import com.alex.threads.daemon.ThreadForDaemon;
import com.alex.threads.notify.BlockingQueueSync;
import com.alex.threads.notify.Consumer;
import com.alex.threads.notify.Producer;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 *
 * @author Alex
 */
public class JavaThreads {
    public static BlockingQueue<String> queue = new ArrayBlockingQueue<String>(100);
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      exampleNotify();
       System.out.println("main thread after call user thread");
    }
    
    static void run1SimpleThread() {
        new Thread(new RunableSimple()).start(); 
    }
    
    static void runAsync3SimpleThreads() {
        Thread t[] = new Thread[3];
        for (int i = 0; i < t.length; i++) {
            t[i] = new Thread(new RunableSimple(), "Thread " + i);
        }
        // Запуск потоков
        for (int i = 0; i < t.length; i++) {
            t[i].start();

            System.out.println(t[i].getName() + " started");
        }
    }
    
    static void runWithPriority3SimpleThreads() {
        Thread t[] = new Thread[3];
        for (int i = 0; i < t.length; i++) {
            t[i] = new Thread(new RunableSimple(), "Thread " + i);
            t[i].setPriority(Thread.MIN_PRIORITY+
                (Thread.MAX_PRIORITY-Thread.MIN_PRIORITY)/t.length*i);
        }
        // Запуск потоков
        for (int i = t.length - 1; i >= 0; i--) {
            t[i].start();

            System.out.println(t[i].getName() + " started");
        }
    }
    
    static void runWithDaemon() {
        new GroupThreadsForDaemon(queue, 3);
        new DemonDemo(queue);    
    }
    
    static void runWithDaemonWhichWaits() {
        new GroupThreadsForDaemon(queue, 3);
        new DaemonWhichWaits(queue);    
    }
    
    static void exampleNotify() {
        BlockingQueue<String> q = new ArrayBlockingQueue<String>(100);
        BlockingQueueSync blockingQueueSync = new BlockingQueueSync(q);
        new Producer(blockingQueueSync);
        new Consumer(blockingQueueSync);     
    }
    
}
