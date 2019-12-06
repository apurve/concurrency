package leave.nucleus.threading;

import org.junit.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ThreeThreadsIncreasingSequence {

    Object lock = new Object();

    private CyclicBarrier cyclicBarrier = new CyclicBarrier(1);

    int value = 1;

    @Test
    public void testThreeThreadsIncreasingSequence() throws BrokenBarrierException, InterruptedException {
        new Thread(() -> {
            try {
                runOne();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                runTwo();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                runThree();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        cyclicBarrier.await();
    }

    private void runOne() throws InterruptedException {
        while(true) {
            synchronized (lock) {
                if(value % 3 == 1) {
                    System.out.println("One says "+value);
                    value++;
                    lock.notifyAll();
                    goToSleep();
                } else {
                    lock.wait();
                }
            }
        }
    }

    private void runTwo() throws InterruptedException {
        while(true) {
            synchronized (lock) {
                if(value % 3 == 2) {
                    System.out.println("Two says "+value);
                    value++;
                    lock.notifyAll();
                    goToSleep();
                } else {
                    lock.wait();
                }
            }
        }
    }

    private void runThree() throws InterruptedException {
        while(true) {
            synchronized (lock) {
                if(value % 3 == 0) {
                    System.out.println("Three says "+value);
                    value++;
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