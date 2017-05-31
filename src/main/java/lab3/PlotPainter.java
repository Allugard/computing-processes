package lab3;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by allugard on 19.05.17.
 */
public class PlotPainter {
    XYSeries processIdle = new XYSeries( "" );
    XYSeries averageTime = new XYSeries( "" );

    public void addIddle(double x, double y){
        processIdle.add(x, y);
    }

    public void addAverageTime(double x, double y) {
        averageTime.add(x, y);
    }

    public void draw() throws IOException {
        drawIdle();
        drawAverageTime();
    }

    private void drawAverageTime() throws IOException {
        XYSeriesCollection dataset = new XYSeriesCollection( );
        dataset.addSeries(averageTime);

        JFreeChart xylineChart = ChartFactory.createXYLineChart(
                "average time",
                "intensity",
                "average time",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        int width = 640;   /* Width of the image */
        int height = 480;  /* Height of the image */
        File XYChart = new File( "AverageTime.jpeg" );
        ChartUtilities.saveChartAsJPEG( XYChart, xylineChart, width, height);
    }

    private void drawIdle() throws IOException {
        XYSeriesCollection dataset = new XYSeriesCollection( );
        dataset.addSeries(processIdle);

        JFreeChart xylineChart = ChartFactory.createXYLineChart(
                "idle of processor",
                "intensity",
                "idle of processor",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        int width = 640;   /* Width of the image */
        int height = 480;  /* Height of the image */
        File XYChart = new File( "ProcessorIdleTime.jpeg" );
        ChartUtilities.saveChartAsJPEG( XYChart, xylineChart, width, height);
    }


    public void draw(TreeMap<Integer, Integer> timeNumberTask) throws IOException {
        XYSeries time = new XYSeries( "" );

        for (Map.Entry <Integer, Integer> entry: timeNumberTask.entrySet()) {
//            System.out.println(entry.getKey());
//            System.out.println(entry.getValue());
            time.add(entry.getKey(), entry.getValue());
        }

        XYSeriesCollection dataset = new XYSeriesCollection( );
        dataset.addSeries(time);

        JFreeChart xylineChart = ChartFactory.createXYLineChart(
                "number of task",
                "intensity",
                "idle of processor",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        int width = 640;   /* Width of the image */
        int height = 480;  /* Height of the image */
        File XYChart = new File( "NumberTask.jpeg" );
        ChartUtilities.saveChartAsJPEG( XYChart, xylineChart, width, height);

    }
}
