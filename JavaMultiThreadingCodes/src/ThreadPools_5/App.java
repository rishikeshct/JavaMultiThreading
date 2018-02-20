package ThreadPools_5;

/**
 * ThreadPool ("number of workers in a factory")
 * <br><br>
 * Codes with minor comments are from
 * <a href="http://www.caveofprogramming.com/youtube/">
 * <em>http://www.caveofprogramming.com/youtube/</em>
 * </a>
 * <br>
 * also freely available at
 * <a href="https://www.udemy.com/java-multithreading/?couponCode=FREE">
 *     <em>https://www.udemy.com/java-multithreading/?couponCode=FREE</em>
 * </a>
 *
 * @author Z.B. Celik <celik.berkay@gmail.com>
 */
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Processor implements Runnable {

    private int id;

    public Processor(int id) {
        this.id = id;
    }

    public void run() {
        System.out.println("Starting: " + id);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ignored) {
        }
        System.out.println("Completed: " + id);
    }
}

public class App {

    public static void main(String[] args) {
        /**
         * Created 2 threads, and assign tasks (Processor(i).run) to the threads
         */
		 
		 /*
		 
			

down vote
Thread Pools are useful only in a Server-client kind of situation where the number/occurrence of client requests cannot be determined/predicted.

In this scenario, creating a new Thread each time a client request is made has two dis-advantages:

1) Run time latency for thread creation: Creation of a thread requires some time, thus the actual job does not start as soon as the request comes in. The client may notice a slight delay.

This criteria is crucial in interactive systems, where the client expects an immediate action.

2) Uncontrolled use of System Resources: Threads consume system resources (memory etc.), thus the system may run out of resources in case there is an unprecedented flow of client requests.

Thread pools address the above concerns by:
1) Creating specified number of threads on server start-up instead of creating them during the run-time.
2) Limiting the number of threads that are running at any given time.

Note: The above is applicable for Thread Pools of Fixed Sizes.
		 
		 
		 */
        ExecutorService executor = Executors.newFixedThreadPool(2);//2 Threads
        for (int i = 0; i < 2; i++) { // call the (Processor(i).run) 2 times with 2 threads
            executor.submit(new Processor(i));
        }
        executor.shutdown();
        System.out.println("All tasks submitted.");
        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException ignored) {
        }
        System.out.println("All tasks completed.");
    }
}
