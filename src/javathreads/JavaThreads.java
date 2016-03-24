package javathreads;

import com.alex.threads.daemon.DaemonWhichWaits;
import com.alex.threads.daemon.DemonDemo;
import com.alex.threads.daemon.GroupThreadsForDaemon;
import com.alex.threads.RunableSimple;
import com.alex.threads.executorservice.TaskWithResult;
import com.alex.threads.notify.BlockingQueueSync;
import com.alex.threads.notify.Consumer;
import com.alex.threads.notify.Producer;
import com.alex.threads.sync.ClassWithSyncMethods;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

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

        // run1SimpleThread();
        //runAsync3SimpleThreads();
        // runWithPriority3SimpleThreads();
        // runWithDaemon();
        //runWithDaemonWhichWaits();
        //exampleNotify();
        //executorService();
        callTwoMethodsOfOneClass();
        System.out.println("main thread finished");
    }

    /**
     * run one simple thread
     */
    static void run1SimpleThread() {
        new Thread(new RunableSimple()).start();
    }

    /**
     * run async 3 threads which will increment variable and will show message
     */
    static void runAsync3SimpleThreads() {
        Thread t[] = new Thread[3];
        for (int i = 0; i < t.length; i++) {
            t[i] = new Thread(new RunableSimple(), "Thread " + i);
        }
        // start threads
        for (int i = 0; i < t.length; i++) {
            t[i].start();

            System.out.println(t[i].getName() + " started");
        }
    }

    /**
     * run async 3 threads with priority which will increment variable and will
     * show message
     */
    static void runWithPriority3SimpleThreads() {
        Thread t[] = new Thread[3];
        for (int i = 0; i < t.length; i++) {
            t[i] = new Thread(new RunableSimple(), "Thread " + i);
            t[i].setPriority(Thread.MIN_PRIORITY
                    + (Thread.MAX_PRIORITY - Thread.MIN_PRIORITY) / t.length * i);
        }
        // Запуск потоков
        for (int i = t.length - 1; i >= 0; i--) {
            t[i].start();

            System.out.println(t[i].getName() + " started");
        }
    }

    /**
     * run async group of threads and one daemon thread which will work with
     * collection ArrayBlockingQueue. Each thread will async put message into
     * the collection and daemon will get from the collection until the threads
     * are folded messages.
     */
    static void runWithDaemon() {
        new GroupThreadsForDaemon(queue, 3);
        new DemonDemo(queue);
    }

    /**
     * run async group of threads and one daemon thread which will work with
     * collection ArrayBlockingQueue. Each thread will async put message into
     * the collection and daemon will get from the collection until the threads
     * are folded messages, but the daemon can not finish the job because it's
     * daemon and it's shutdown after all user threads finished. If you want to
     * finish the job of daemon you have to comment string setDaemon(true);
     */
    static void runWithDaemonWhichWaits() {
        new GroupThreadsForDaemon(queue, 3);
        new DaemonWhichWaits(queue);
    }

    /**
     * example of sync work between producer and consumer
     */
    static void exampleNotify() {
        BlockingQueue<String> q = new ArrayBlockingQueue<String>(100);
        BlockingQueueSync blockingQueueSync = new BlockingQueueSync(q);
        new Producer(blockingQueueSync);
        new Consumer(blockingQueueSync);
    }

    /**
     * example of work with ExecutorService(work with pool of threads). all
     * threads in pool work async
     */
    static void executorService() {
        ExecutorService exec = Executors.newCachedThreadPool();
        List<Future<String>> results = new ArrayList<Future<String>>();

        for (int i = 0; i < 10; i++) {
            results.add(exec.submit(new TaskWithResult(i)));
        }

        for (Future<String> fs : results) {
            try {
                //calling get() blocks until completion of thread pool
                System.out.println(fs.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            } catch (ExecutionException e) {
                e.printStackTrace();
            } finally {
                exec.shutdown();
            }
        }
    }
    
    /**
     * run two methods of one class in two different threads
     */
    static void callTwoMethodsOfOneClass() {
        final ClassWithSyncMethods obj = new ClassWithSyncMethods();
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    System.out.println("start of m1: " + LocalDateTime.now());
                    obj.m1();
                    System.out.println("end of m1: " + LocalDateTime.now());
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        
        }).run();
        
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    System.out.println("start of m2: " + LocalDateTime.now());
                    obj.m2();
                    System.out.println("end of m2: " + LocalDateTime.now());
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        
        }).run();
    }
    
    static void m2(final String param) {
    }
}
