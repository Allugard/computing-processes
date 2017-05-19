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
        double nextTaskTime = curTime + g.getNextTime();
        Task task = new Task(curTime, g.getExecutionTime(), g.lengthTask());
        queue.addTask(task);
        processor.setTask(task, 1/g.getMu());
        while (currentNumberTask < n){
            if(nextTaskTime < curTime + processor.getQuantumTime()){
                processor.setQuantumTime(processor.getQuantumTime() - nextTaskTime + curTime);
                curTime = nextTaskTime;
                nextTaskTime = curTime + g.getNextTime();
                task = new Task(curTime, g.getExecutionTime(), g.lengthTask());
                queue.addTask(task);
                if(processor.isEmpty()){
                    processor.setTask(task, ((double) queue.getIndex(task)));
                }
                currentNumberTask++;
            }else {
                curTime = curTime+processor.getQuantumTime();
                Task processorTask = processor.getTask();
                processorTask.setRemainingExecutionTime(processorTask.getRemainingExecutionTime() - processorTask.getRemainingExecutionTime());
                if (processorTask.getRemainingExecutionTime() == 0.){
                    Statistics.getInstance().addAllTime(curTime - processorTask.getStartedTime());
                    Statistics.getInstance().addExecTime(processorTask.getExecTime());
                    Statistics.getInstance().addTimeInQueue(curTime - processorTask.getStartedTime() - processorTask.getExecTime());
                    queue.remove(processorTask);
                }else {
                    queue.next(processorTask);
                }
                if(!queue.isEmpty()){
                    task = queue.getTask();
                    processor.setTask(task, ((double) queue.getIndex(task)));
                }else {
                    processor.nullTask();
                }
            }
        }
//        Statistics.getInstance().setTimeInQueue();
        Statistics.getInstance().setCurTime(curTime);

    }
}
