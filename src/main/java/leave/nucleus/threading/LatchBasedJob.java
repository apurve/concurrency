package leave.nucleus.threading;

import java.util.concurrent.CountDownLatch;

public class LatchBasedJob implements Runnable, Job {

    private CountDownLatch latch;
    private Integer id;

    public LatchBasedJob(CountDownLatch latch, int id) {
        this.latch = latch;
        this.id = id;
    }

    @Override
    public void run() {
        doWork(id);
        System.out.println(latch.getCount());
        latch.countDown();
    }

}