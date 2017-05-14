package lab3;

/**
 * Created by allugard on 14.05.17.
 */
public class QueueManagementSystem {


    private double lambda;
    private double mu;
    private int n;
    private static Generator g;
    private Processor processor;
    private Queue queue;
    public QueueManagementSystem(double lambda, double mu, int n) {
        this.lambda = lambda;
        this.mu = mu;
        this.n = n;
        g = new Generator(lambda,mu);
        processor = new Processor();
        queue = new Queue();
    }


    public void start() {
        double curTime = 0.0;
        int currentNumberTask = 0;
        double nextTaskTime =curTime + g.getNextTime();
        Task task = new Task(curTime, g.getExecutionTime(), g.lengthTask());
        queue.addTask(task);
        processor.setTask(task, 1/g.getMu());
        while (currentNumberTask < n){
            if(nextTaskTime < curTime + processor.getQuantumTime()){
                curTime = nextTaskTime;
                nextTaskTime = g.getNextTime();
                task = new Task(curTime, g.getExecutionTime(), g.lengthTask());
                queue.addTask(task);
            }else {
                //TODO
            }
        }
    }
}
