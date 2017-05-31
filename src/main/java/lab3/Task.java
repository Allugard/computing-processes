package lab3;

/**
 * Created by allugard on 14.05.17.
 */
public class Task {
    private double startedTime;
    private final double execTime;

    public Task(double startedTime, double execTime) {
        this.startedTime = startedTime;
        this.execTime = execTime;
    }

    public double getStartedTime() {
        return startedTime;
    }

    public void setStartedTime(double startedTime) {
        this.startedTime = startedTime;
    }


    public double getExecTime() {
        return execTime;
    }
}
