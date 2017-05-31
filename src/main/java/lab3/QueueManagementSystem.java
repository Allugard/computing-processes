package lab3;

/**
 * Created by allugard on 14.05.17.
 */
public class QueueManagementSystem {

    private int n;
    private static Generator g;
    private Processor processor;
    private Queue queue;
    public QueueManagementSystem(double lambda, double mu, int n) {
        this.n = n;
        g = new Generator(lambda,mu);
        processor = new Processor();
        queue = new Queue();
    }


    public void start() {
        double time = 0.0;
        Task task = new Task(time, g.getExecutionTime());
        processor.setTask(task);
        double generationTime = g.getNextTime();

        int numTask=0;
        while (numTask < n || !queue.isEmpty() || processor.isEmpty()){
//            System.out.println(time);
//            System.out.println(generationTime);
//            System.out.println(time + processor.getTime());
//            System.out.println(processor.getTime());
//            System.out.println("---------------------------------------------------------------------");
            if(generationTime < time + processor.getTime()){
                time = generationTime;
//                statistic.incTasks();
                queue.addTask(new Task(time, g.getExecutionTime()));
                generationTime = time + g.getNextTime();
                if(processor.isEmpty()){
                    task= queue.getTask();
                    processor.setTask(task);
                }
                numTask++;
                if(numTask == n ){
                    generationTime = Double.POSITIVE_INFINITY;
                }
            }else {
                time += processor.getTime();
//                statistic.incEndedTasks();
//                processor.getTask().close(time);
                Statistics.getInstance().addAllTime(time - processor.getTime());
                Statistics.getInstance().addExecTime(processor.getTime());
                Statistics.getInstance().addTimeInQueue(time - processor.getTask().getStartedTime() - processor.getTime());
                double a = time - processor.getTime();
                a = time - processor.getTask().getStartedTime();
//                System.out.println(a);
                Statistics.getInstance().addTimeNumberTask(a);
//                System.out.println("time" + time);
//                System.out.println("started" + processor.getTask().getStartedTime());
//                System.out.println("exec" + processor.getTask().getExecTime());
//                System.out.println("-----------------------------------------------------------");
                processor.nullTask();
                if(!queue.isEmpty()){
                    task=queue.getTask();
                    processor.setTask(task);
                }
            }
        }
        Statistics.getInstance().setCurTime(time);

    }
}
