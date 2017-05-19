package lab3;

/**
 * Created by allugard on 14.05.17.
 */
public class Main {
    public static void main(String[] args) {
//        System.out.println((int)(Math.log(Math.pow(2,31))/Math.log(2)));
        double lambda = 0.09;
        double mu= 0.1;
        int n = 100000;
        Statistics.getInstance().setN(n);
        new QueueManagementSystem(lambda, mu, n).start();
        System.out.println(Statistics.getInstance());

//        Generator g = new Generator(1,0.1);
//        for (int i = 0; i <1000000; i++) {
//            System.out.println(g.getRemainingExecutionTime());
//        }
    }
}
