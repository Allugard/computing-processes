package lab4;

import java.util.Random;

/**
 * Created by allugard on 30.05.17.
 */
public class Main {
    public static void main(String[] args) {
        int [][] m = {
                {1, 0, 0, 0},
                {1, 1, 1, 0},
                {0, 1, 0, 0},
                {1, 0, 1, 0},
        };

        PlotPainter plotPainter = new PlotPainter();

        int startPosExperiment = 5;
        int finishedPosExperiment = 25;
        for (int i = startPosExperiment; i < finishedPosExperiment; i++) {
            Matrix matrix = new Matrix(new Random(), i);
            long start = System.nanoTime();
            while (matrix.length() != 0){
                int row = matrix.findMinRow();
                int column  = matrix.findMinColumn(row);

                matrix = matrix.deleteRowColumn(row, column);
                System.out.println(matrix);
            }
            long end = System.nanoTime();
            plotPainter.addAverageWaitingTime(i, (end - start)/(double)i);

            System.out.println((end - start)/(double)i);
        }

        plotPainter.drawAverageWaitingTime();

    }
}
