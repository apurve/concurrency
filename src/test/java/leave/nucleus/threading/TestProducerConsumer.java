package leave.nucleus.threading;

import leave.nucleus.threading.producerconsumer.*;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TestProducerConsumer {

    private LinkedList<Integer> list = null;
    private ReentrantLock lock = null;
    private CyclicBarrier barrier = null;

    @Before
    public void setup() {
        this.list = new LinkedList<Integer>();
        this.lock = new ReentrantLock();
        this.barrier = new CyclicBarrier(2);
    }

    @Test
    public void testDefaultProducerConsumer() throws InterruptedException, BrokenBarrierException {
        Producer producer = new DefaultProducerImpl(list, lock);
        Consumer consumer = new DefaultConsumerImpl(list, lock);
        Thread producerThread = new Thread(() -> {
            try {
                producer.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        producerThread.start();

        Thread consumerThread = new Thread(() -> {
            try {
                consumer.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        consumerThread.start();
        barrier.await();
    }

    @Test
    public void testReentrantProducerConsumer() throws InterruptedException, BrokenBarrierException {
        Condition condition = lock.newCondition();
        Producer producer = new ReentrantLockProducer(list, lock, condition);
        Consumer consumer = new ReentrantLockConsumer(list, lock, condition);
        Thread producerThread = new Thread(() -> {
            try {
                producer.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        producerThread.start();

        Thread consumerThread = new Thread(() -> {
            try {
                consumer.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        consumerThread.start();
        barrier.await();
    }

}