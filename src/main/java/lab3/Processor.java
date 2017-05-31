package lab3;

import static java.lang.Double.POSITIVE_INFINITY;

/**
 * Created by allugard on 14.05.17.
 */
public class Processor {
    private Task task;


    public void setTask(Task task) {
        this.task = task;
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

    public double getTime(){
        if(task==null){
            return POSITIVE_INFINITY;
        }
        return task.getExecTime();
    }

}
