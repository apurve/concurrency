package leave.nucleus.threading;

import org.junit.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class NonBlockingThreeThreadsInSequence {

    private AtomicInteger value = new AtomicInteger(1);
    private CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

    @Test
    public void testThreeThreadsInSequence() throws InterruptedException, BrokenBarrierException {

        new Thread(() -> runOne()).start();
        new Thread(() -> runTwo()).start();
        new Thread(() -> runThree()).start();

        cyclicBarrier.await();

    }

    private void runOne() {
        while(true) {
            int currentValue = value.get();
            if(currentValue == 1) {
                System.out.println("T1 :" + currentValue);
                value.compareAndSet(currentValue, 2);
            }
            sleepForReadability();
        }
    }

    private void runTwo() {
        while(true) {
            int currentValue = value.get();
            if(currentValue == 2) {
                System.out.println("T2 :" + currentValue);
                value.compareAndSet(currentValue, 3);
            }
            sleepForReadability();
        }
    }

    private void runThree() {
        while(true) {
            int currentValue = value.get();
            if(currentValue == 3) {
                System.out.println("T3 :" + currentValue);
                value.compareAndSet(currentValue, 1);
            }
            sleepForReadability();
        }
    }

    private void sleepForReadability() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}