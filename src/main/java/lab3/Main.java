package lab3;

import java.io.IOException;

/**
 * Created by allugard on 14.05.17.
 */
public class Main {
    public static void main(String[] args) throws IOException {
//        System.out.println((int)(Math.log(Math.pow(2,31))/Math.log(2)));
        double lambda = 0.1;
        double mu= 0.1;
        int n = 10000;
        Statistics.getInstance().setN(n);
        PlotPainter plotPainter = new PlotPainter();
        new QueueManagementSystem(lambda, mu, n).start();
        plotPainter.draw(Statistics.getInstance().getTimeNumberTask());
//        System.out.println(Statistics.getInstance());


        lambda = 0.01;

        for (int i = 0; i <15; lambda+= 0.05, i++) {
            Statistics.getInstance().setNull();
            new QueueManagementSystem(lambda, mu, n).start();
            plotPainter.addIddle(lambda, 1 - Statistics.getInstance().getExecTime()/ Statistics.getInstance().getCurTime());
            plotPainter.addAverageTime(lambda, Statistics.getInstance().getTimeInQueue()/n);
//            System.out.println(Statistics.getInstance().getExecTime()/ Statistics.getInstance().getCurTime());
//            System.out.println(Statistics.getInstance().getTimeInQueue()/n);
//            System.out.println("---------------");
        }

        plotPainter.draw();

    }
}
