package lab4;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by allugard on 19.05.17.
 */
public class PlotPainter {
    private XYSeries averageWaitingTime = new XYSeries(" ");
    public PlotPainter(){
    }

    public void addAverageWaitingTime(double x, double y){
        averageWaitingTime.add(x, y);
    }


    public void drawAverageWaitingTime() {
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(averageWaitingTime);
        JFreeChart chart = ChartFactory.createXYLineChart("Average Waiting Time", "size", "average time", dataset, PlotOrientation.VERTICAL, false, false, false);
        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        renderer.setSeriesPaint(0, Color.RED);

        plot.setRenderer(renderer);

        File imageFile = new File("AverageWaitingTimeLab4.png");
        int width = 640;
        int height = 480;
        try {
            ChartUtilities.saveChartAsPNG(imageFile, chart, width, height);
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

}
