import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Double> samples = new ArrayList<>();
        TCPTimer t1 = new TCPTimer();
        samples.add(106.0);
        samples.add(120.0);
        samples.add(140.0);
        samples.add(90.0);
        samples.add(115.0);

        for (Double sample : samples) {
            t1.addSample(sample);
            System.out.println("sampleRTT: " + sample);
            System.out.println("EstimatedRTT: " + t1.getEstimatedRTT());
            System.out.println("DevRTT: " + t1.getDevRTT());
            System.out.println("TimeoutInterval: " + t1.getTimeoutInterval());
            System.out.println();
        }
    }
}
