package leave.nucleus.threading.producerconsumer;

public interface Producer extends CollectionProvider, LockProvider {

    int LIMIT_OF_LIST = 10;

    default void produce() throws InterruptedException {
        int value = 0;
        while (true) {
            synchronized (getLock()) {
                System.out.println("Queue size before producing : " + getCollection().size());
                while (getCollection().size() == LIMIT_OF_LIST)
                    getLock().wait();
                getCollection().add(value++);
                getLock().notify();
            }
            Thread.sleep(200);
        }
    }

}