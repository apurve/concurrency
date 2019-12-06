package leave.nucleus.threading;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolsTest {

    ExecutorService executorService = null;

    @Before
    public void initializeThreadPool() {
        executorService = Executors.newFixedThreadPool(10);
    }

    @Test
    public void testExecutorService() {

        for(int i = 1; i<=10; i++) {
            executorService.submit(new SimpleJob(i));
        }

        executorService.shutdown();

        System.out.println("All tasks submitted!");

        try {
            executorService.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All tasks completed!");
    }

    @Test
    public void testCountDownLatch() {
        CountDownLatch countDownLatch = new CountDownLatch(20);

        for(int i = 1; i<=20; i++) {
            executorService.submit(new LatchBasedJob(countDownLatch, i));
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
