package lab3;

import java.util.HashMap;
import java.util.TreeMap;

/**
 * Created by allugard on 18.05.17.
 */
public class Statistics {
    private static Statistics statistics = new Statistics();
    private int n;
    private double timeInQueue;
    private double execTime;
    private double allTime;
    private double curTime;
    private TreeMap<Integer, Integer> timeNumberTask;

    private Statistics() {
        timeNumberTask = new TreeMap<>();
    }

    public static Statistics getInstance() {
        return statistics;
    }

    public TreeMap<Integer, Integer> getTimeNumberTask() {
        return timeNumberTask;
    }

    public void addTimeNumberTask(double time){
        if(timeNumberTask.containsKey((int)time)){
            int a = timeNumberTask.get((int)time);
            a++;
            timeNumberTask.put((int)time, a);
        }else {
            timeNumberTask.put((int)time, 1);
        }
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public double getTimeInQueue() {
        return timeInQueue;
    }

    public void setTimeInQueue() {
        timeInQueue = allTime - execTime;
    }

    public void addTimeInQueue(double timeInQueue) {
        this.timeInQueue += timeInQueue;
    }

    public double getExecTime() {
        return execTime;
    }

    public void setExecTime(double execTime) {
        this.execTime = execTime;
    }

    public void addExecTime(double execTime) {
        this.execTime += execTime;
    }

    public double getAllTime() {
        return allTime;
    }

    public void setAllTime(double allTime) {
        this.allTime = allTime;
    }

    public void addAllTime(double allTime) {
        this.allTime += allTime;
    }

    public double getCurTime() {
        return curTime;
    }

    public void setCurTime(double curTime) {
        this.curTime = curTime;
    }

    public void setNull(){
        timeInQueue = 0;
        execTime = 0;
        allTime = 0;
        curTime = 0;
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "timeInQueue=" + timeInQueue +
                ", execTime=" + execTime +
                ", allTime=" + allTime +
                ", curTime=" + curTime +
                '}';
    }
}
