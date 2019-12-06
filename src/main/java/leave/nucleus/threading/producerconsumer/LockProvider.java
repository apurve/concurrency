package leave.nucleus.threading.producerconsumer;

import java.util.concurrent.locks.Lock;

public interface LockProvider {

    Lock getLock();

}