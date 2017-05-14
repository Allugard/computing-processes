package lab3;

/**
 * Created by allugard on 14.05.17.
 */
public class Task {
    private double startedTime;
    private double executionTime;
    private int length;

    public Task(double startedTime, double executionTime, int length) {
        this.startedTime = startedTime;
        this.executionTime = executionTime;
        this.length = length;
    }

    public double getStartedTime() {
        return startedTime;
    }

    public void setStartedTime(double startedTime) {
        this.startedTime = startedTime;
    }

    public double getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(double executionTime) {
        this.executionTime = executionTime;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
