package leave.nucleus.threading.producerconsumer;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockProducer implements Producer {

    private ReentrantLock lock = null;
    private LinkedList<Integer> list = null;
    private Condition condition = null;

    public ReentrantLockProducer(LinkedList<Integer> list, ReentrantLock lock, Condition condition) {
        this.list = list;
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public LinkedList<Integer> getCollection() {
        return list;
    }

    @Override
    public Lock getLock() {
        return lock;
    }

    @Override
    public void produce() throws InterruptedException {
        int count = 0;
        while(true) {
            try{
                getLock().lock();
                while(getCollection().size() == LIMIT_OF_LIST) {
                    System.out.println("List full, gonna wait now!");
                    condition.await();
                }
                System.out.println("Adding number : " + count++);
                getCollection().add(count);
                condition.signal();
            } finally {
                getLock().unlock();
            }
            System.out.println("Queue size after adding :"+ getCollection().size());
        }
    }
}