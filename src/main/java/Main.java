import java.util.ArrayList;

public class Main {

    public void getRTTs(TCPTimer t1) {
        System.out.println("SampleRTT: " + t1.lastSample());
        System.out.println("EstimatedRTT: " + t1.getEstimatedRTT());
        System.out.println("DevRTT: " + t1.getDevRTT());
        System.out.println("TimeoutInterval: " + t1.getTimeoutInterval());
    }

    public static void main(String[] args) {
        ArrayList<Double> samples = new ArrayList<>();
        TCPTimer t1 = new TCPTimer(106.0);
        getRTTs(t1);
        System.out.println();
        samples.add(120.0);
        samples.add(140.0);
        samples.add(90.0);
        samples.add(115.0);

        for (Double sample : samples) {
            t1.addSample(sample);
            getRTTs(t1);
            System.out.println();
        }
    }
}
