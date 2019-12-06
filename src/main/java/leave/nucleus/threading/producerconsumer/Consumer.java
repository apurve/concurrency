package leave.nucleus.threading.producerconsumer;

import java.util.Iterator;

public interface Consumer extends CollectionProvider, LockProvider {

    default void consume() throws InterruptedException {
        while (true) {
            synchronized (getLock()) {
                while (getCollection().size() == 0)
                    getLock().wait();
                System.out.println("Queue size before taking :" + getCollection().size());
                Iterator<Integer> integerIterator = getCollection().iterator();
                Integer value = null;
                while(integerIterator.hasNext()) {
                    value = integerIterator.next();
                    integerIterator.remove();
                }
                System.out.println("Took value :"+value);
                getLock().notify();
            }
            Thread.sleep(500);
        }
    }

}