import java.util.ArrayList;

public class TCPTimer {
    private final double alpha;
    private final double beta;
    private final ArrayList<Double> sampleRTTs;
    private double estimatedRTT;
    private double devRTT;
    private double timeoutInterval;

    public TCPTimer() {
        alpha = 0.125;
        beta = 0.25;
        sampleRTTs = new ArrayList<>();
        estimatedRTT = 0.125;
        devRTT = 0.25;
        timeoutInterval = estimatedRTT + 4 * devRTT;
    }

    public TCPTimer(double alpha, double beta, ArrayList<Double> sampleRTTs) {
        this.alpha = alpha;
        this.beta = beta;
        this.sampleRTTs = sampleRTTs;
    }

    public TCPTimer(double alpha, double beta, ArrayList<Double> sampleRTTs, double estimatedRTT,
                    double devRTT) {
        this.alpha = alpha;
        this.beta = beta;
        this.sampleRTTs = sampleRTTs;
        this.estimatedRTT = estimatedRTT;
        this.devRTT = devRTT;
        timeoutInterval = estimatedRTT + 4 * devRTT;
    }

    public double getAlpha() {
        return alpha;
    }

    public double getBeta() {
        return beta;
    }

    public ArrayList<Double> getSampleRTTs() {
        return sampleRTTs;
    }

    public double getEstimatedRTT() {
        return estimatedRTT;
    }

    public double getDevRTT() {
        return devRTT;
    }

    public double getTimeoutInterval() {
        return timeoutInterval;
    }

    public void addSample(double sample) {
        sampleRTTs.add(sample);
        calcRTTs(sample);
    }

    private void calcRTTs(double sample) {
        estimatedRTT = (1 - alpha) * estimatedRTT + alpha * sample;
        devRTT = (1 - beta) * devRTT + beta * Math.abs(sample - estimatedRTT);
        timeoutInterval = estimatedRTT + 4 * devRTT;
    }

}
