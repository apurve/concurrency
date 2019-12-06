package leave.nucleus.threading;

public interface Job {

    default void doWork(int jodId) {
        System.out.println("Starting: "+jodId);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Completing: "+jodId);
    }
}
