package lab3;

import java.util.Random;

/**
 * Created by allugard on 14.05.17.
 */
public class Generator {
    private final double lambda;
    private final double mu;
    private Random r;

    public Generator(double lambda, double mu) {
        this.lambda = lambda;
        this.mu = mu;
        r = new Random();
    }

    public double getNextTime(){
        double rand=r.nextDouble();
        return (-1/lambda)*Math.log(rand);
    }

    public double getExecutionTime(){
//        double rand=r.nextDouble();
        return 1+r.nextInt(10);
    }

    public double getMu() {
        return mu;
    }

        public int lengthTask(){
        return r.nextInt((int)Math.pow(2, 32));
    }

}
