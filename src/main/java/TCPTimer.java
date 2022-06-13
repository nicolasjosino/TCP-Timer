import java.util.ArrayList;

public class TCPTimer {
    private final double alpha;
    private final double beta;
    private final ArrayList<Double> sampleRTTs;
    private double estimatedRTT;
    private double devRTT;
    private double timeoutInterval;

    public TCPTimer(double alpha, double beta, double firstSample) {
        this.alpha = alpha;
        this.beta = beta;
        this.sampleRTTs = new ArrayList<>();
        this.sampleRTTs.add(firstSample);
        this.estimatedRTT = firstSample;
        this.devRTT = firstSample / 2;
    }

    public TCPTimer(double alpha, double beta, double estimatedRTT, double devRTT) {
        this.alpha = alpha;
        this.beta = beta;
        this.sampleRTTs = new ArrayList<>();
        this.estimatedRTT = estimatedRTT;
        this.devRTT = devRTT;
        this.timeoutInterval = estimatedRTT + 4 * devRTT;
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

    public double lastSample() {
        return sampleRTTs.get(sampleRTTs.size() - 1);
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
