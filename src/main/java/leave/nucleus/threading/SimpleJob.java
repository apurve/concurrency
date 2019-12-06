package leave.nucleus.threading;

public class SimpleJob implements Runnable, Job {

    private int id;

    public SimpleJob(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        doWork(id);
    }

}