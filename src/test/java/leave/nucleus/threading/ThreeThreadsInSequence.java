package leave.nucleus.threading;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreeThreadsInSequence {

    private Object lock = new Object();
    private int value = 1;

    @Test
    public void testThreeThreadsInSequence() throws InterruptedException {

        Thread t1 = new Thread(() -> {
            try {
                runOne();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        Thread t2 = new Thread(() -> {
            try {
                runTwo();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t2.start();

        Thread t3 = new Thread(() -> {
            try {
                runThree();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t3.start();

        t3.join();
    }

    @Test
    public void testThreeThreadsInSequenceWithExecutorFramework() throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        executorService.submit(() -> {
            try {
                runOne();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executorService.submit(() -> {
            try {
                runTwo();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executorService.submit(() -> {
            try {
                runThree();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executorService.awaitTermination(5, TimeUnit.SECONDS);
        executorService.shutdown();
    }

    private void runThree() throws InterruptedException {
        while (true) {
            synchronized (lock) {
                if(value == 3) {
                    System.out.println("runThree says "+value);
                    value = 1;
                    lock.notifyAll();
                    goToSleep();
                } else {
                    lock.wait();
                }
            }
        }
    }

    public void runOne() throws InterruptedException {
        while (true) {
            synchronized (lock) {
                if(value == 1) {
                    System.out.println("runOne says "+value);
                    value = 2;
                    lock.notifyAll();
                    goToSleep();
                } else {
                    lock.wait();
                }
            }
        }
    }

    public void runTwo() throws InterruptedException {
        while (true) {
            synchronized (lock) {
                if(value == 2) {
                    System.out.println("runTwo says "+value);
                    value = 3;
                    lock.notifyAll();
                    goToSleep();
                } else {
                    lock.wait();
                }
            }
        }
    }

    private void goToSleep() throws InterruptedException {
        Thread.sleep(300);
    }

}