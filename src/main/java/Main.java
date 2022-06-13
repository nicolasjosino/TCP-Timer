import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void getRTTs(TCPTimer t1) {
        System.out.println("SampleRTT: " + t1.lastSample() + "ms");
        System.out.println("EstimatedRTT: " + t1.getEstimatedRTT() + "ms");
        System.out.println("DevRTT: " + t1.getDevRTT() + " ms");
        System.out.println("TimeoutInterval: " + t1.getTimeoutInterval() + "ms");
    }

    public static void main(String[] args) {
        TCPTimer t1 = null;
        Scanner user = new Scanner(System.in);
        ArrayList<Double> samples = new ArrayList<>();

        try {
            System.out.print("Digite o valor de alpha: ");
            double alpha = user.nextDouble();
            System.out.print("Digite o valor de beta: ");
            double beta = user.nextDouble();

            System.out.print("Digitar valores iniciais dos RTT's? S ou N: ");
            String digitaRTT = user.next();

            if (digitaRTT.equalsIgnoreCase("S")) {
                System.out.print("Digite o valor inicial do EstimatedRTT: ");
                double estimatedRTT = user.nextDouble();
                System.out.print("Digite o valor inicial de DevRTT: ");
                double devRTT = user.nextDouble();
                t1 = new TCPTimer(alpha, beta, estimatedRTT, devRTT);
            }
            else if (digitaRTT.equalsIgnoreCase("N")) {
                System.out.println("Digite o valor da amostra inicial: ");
                double sample = user.nextDouble();
                t1 = new TCPTimer(alpha, beta, sample);
                getRTTs(t1);
                System.out.println();
            }
            else {
                System.out.println("Saindo...");
                System.exit(0);
            }

            System.out.print("Digite a quantidade de amostras a serem inseridas: ");
            int n = user.nextInt();
            if (n == 0) {
                System.out.println("Saindo...");
                System.exit(0);
            }

            System.out.println("Digite os SampleRTT's:");
            for (int i = 0; i < n; i++) {
                double sample = user.nextDouble();
                samples.add(sample);
            }

            for (Double sample : samples) {
                t1.addSample(sample);
                getRTTs(t1);
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um erro: " + e.getMessage());
        }
    }
}
