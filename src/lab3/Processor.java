package lab3;

import static java.lang.Double.POSITIVE_INFINITY;

/**
 * Created by allugard on 14.05.17.
 */
public class Processor {
    private Task task;
    private double quantumTime;


    public void setTask(Task task, double quantumTime) {
        this.task = task;
        if(quantumTime < task.getExecutionTime()) {
            this.quantumTime = quantumTime;
        }else {
            this.quantumTime = task.getExecutionTime();
        }
    }

    public void nullTask(){
        task=null;
    }
    public boolean isEmpty(){
        if (task==null){
            return true;
        }
        return false;
    }
    public Task getTask(){
        return task;
    }
    public double getQuantumTime(){
        if(task==null){
            return POSITIVE_INFINITY;
        }
        return quantumTime;
    }
}
