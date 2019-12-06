package leave.nucleus.threading.producerconsumer;

import java.util.LinkedList;
import java.util.concurrent.locks.Lock;

public class DefaultConsumerImpl implements Consumer {

    private LinkedList<Integer> list = null;
    private Lock lock = null;

    public DefaultConsumerImpl(LinkedList<Integer> list, Lock lock) {
        this.list = list;
        this.lock = lock;
    }

    @Override
    public LinkedList<Integer> getCollection() {
        return list;
    }

    @Override
    public Lock getLock() {
        return lock;
    }

}