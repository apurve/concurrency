package leave.nucleus.threading.producerconsumer;

import leave.nucleus.threading.producerconsumer.Producer;

import java.util.LinkedList;
import java.util.concurrent.locks.Lock;

public class DefaultProducerImpl implements Producer {

    private LinkedList<Integer> list = null;
    private Lock lock = null;

    public DefaultProducerImpl(LinkedList<Integer> list, Lock lock) {
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