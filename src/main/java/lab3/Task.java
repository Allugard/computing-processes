package lab3;

/**
 * Created by allugard on 14.05.17.
 */
public class Task {
    private double startedTime;
    private double remainingExecutionTime;
    private final double execTime;
    private int length;

    public Task(double startedTime, double remainingExecutionTime, int length) {
        this.startedTime = startedTime;
        this.remainingExecutionTime = remainingExecutionTime;
        this.length = length;
        execTime = remainingExecutionTime;
    }

    public double getStartedTime() {
        return startedTime;
    }

    public void setStartedTime(double startedTime) {
        this.startedTime = startedTime;
    }

    public double getRemainingExecutionTime() {
        return remainingExecutionTime;
    }

    public void setRemainingExecutionTime(double remainingExecutionTime) {
        this.remainingExecutionTime = remainingExecutionTime;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public double getExecTime() {
        return execTime;
    }
}
