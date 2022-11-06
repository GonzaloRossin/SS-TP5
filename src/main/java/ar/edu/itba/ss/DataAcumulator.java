package ar.edu.itba.ss;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataAcumulator {
    Map<Double, List<Integer>> particleCountVsTime1 = new HashMap<>();

    List<Integer> particleCounts = new ArrayList<>();
    List<Double> timeArray = new ArrayList<>();
    Map<Double, List<Double>> averageList = new HashMap<>();
    Map<Double, List<Double>> Qlist = new HashMap<>();

    double[] wlist;
    boolean timeArrayCompleted = false;

    public DataAcumulator() {
//        wlist = wList;
//        for (double w : wList) {
//            particleCountVsTime1.put(w, new ArrayList<Integer>());
//            Qlist.put(w, new ArrayList<Double>());
//            averageList.put(w, new ArrayList<Double>());
//        }
    }

    public void addParticleCountStep(double time, int count) {
//        particleCountVsTime1.get(w).add(count);

        particleCounts.add(count);
        timeArray.add(time);
    }

    public void setTimeArrayCompleted(boolean timeArrayCompleted) {
        this.timeArrayCompleted = timeArrayCompleted;
    }

    public double getAverage(int index, double w){
        int sum = 0;
        sum += particleCountVsTime1.get(w).get(index);
        return sum/3;
    }

    public void calculateAverageList(){
        for(int i = 0; i < timeArray.size() - 1000; i++) {
            for(Double w : wlist) {
                if (i % 1000 == 0) {
                    double averageT = getAverage(i, w);
                    double averageTplusW = getAverage(i + 1000, w);
                    List<Double> list = averageList.get(w);

                    list.add(averageT);
                    Qlist.get(w).add((averageTplusW - averageT) / timeArray.get(1000));
                }
            }
        }
    }

    public void calculateQlist(){
        for(int i = 0; i < timeArray.size() - 1; i++){
            for(Double w : wlist) {
                double particleCount = particleCountVsTime1.get(w).get(i);
                double particleCountPlus = particleCountVsTime1.get(w).get(i + 1);

                Qlist.get(w).add((particleCountPlus - particleCount) / (timeArray.get(i + 1) - timeArray.get(i)));
            }
        }
    }
    public Map<Double, List<Double>> getAverageList() {
        return averageList;
    }
    public Map<Double, List<Double>> getQlist() {
        return Qlist;
    }

    public Map<Double, List<Integer>> getParticleCountVsTime1() {
        return particleCountVsTime1;
    }

    public List<Double> getQ() {
        List<Double> Q = new ArrayList<>();
        for(int i = 0; i < timeArray.size() - 1; i++){
                double particleCount = particleCounts.get(i);
                double particleCountPlus = particleCounts.get(i + 1);

                Q.add((particleCountPlus - particleCount) / (timeArray.get(i + 1) - timeArray.get(i)));
        }
        return Q;
    }

    public List<Double> getTimeArray() {
        return timeArray;
    }
}
