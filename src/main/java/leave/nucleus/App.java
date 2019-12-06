package leave.nucleus;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Hello world!
 *
 */
public class App {

    private int count = 0;
    Object lock = new Object();
    public static void main( String[] args )
    {
        App app = new App();
        app.doWork();

    }

    private void doWork() {
        Thread t1 = new Thread(() -> { for( int i = 0; i<10000; i++) incrementCount(); });
        Thread t2 = new Thread(() -> { for( int i = 0; i<10000; i++) incrementCount(); });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.print(count);
    }

    private void incrementCount() {
        synchronized (lock) {
            count++;
        }
    }
}