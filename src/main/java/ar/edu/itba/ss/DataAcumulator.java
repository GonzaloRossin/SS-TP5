package ar.edu.itba.ss;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataAcumulator {
    Map<Double, List<Integer>> particleCountVsTime1 = new HashMap<>();
    Map<Double, List<Integer>> particleCountVsTime2 = new HashMap<>();
    Map<Double, List<Integer>> particleCountVsTime3 = new HashMap<>();
    Map<Double, List<Double>> averageList = new HashMap<>();
    Map<Double, List<Double>> Qlist = new HashMap<>();
    List<Double> timeArray = new ArrayList<>();
    double[] wlist;
    boolean timeArrayCompleted = false;

    public DataAcumulator(double[] wList) {
        wlist = wList;
        for (double w : wList) {
            particleCountVsTime1.put(w, new ArrayList<Integer>());
            particleCountVsTime2.put(w, new ArrayList<Integer>());
            particleCountVsTime3.put(w, new ArrayList<Integer>());
            Qlist.put(w, new ArrayList<Double>());
            averageList.put(w, new ArrayList<Double>());
        }

    }

    public void addParticleCountStep(double time, int count, double w, int run){
        if(run == 0){
            particleCountVsTime1.get(w).add(count);
            if(!timeArrayCompleted)
                timeArray.add(time);
        }else if(run == 1){
            particleCountVsTime2.get(w).add(count);
        }else{
            particleCountVsTime3.get(w).add(count);
        }
    }

    public void setTimeArrayCompleted(boolean timeArrayCompleted) {
        this.timeArrayCompleted = timeArrayCompleted;
    }

    public double getAverage(int index, double w){
        int sum=0;
        sum +=particleCountVsTime1.get(w).get(index);
        sum +=particleCountVsTime2.get(w).get(index);
        sum +=particleCountVsTime3.get(w).get(index);
        return sum/3;
    }
    public void calculateAverageList(){
        for(int i = 0; i < timeArray.size();i++){
            for(Double w : wlist){
                double average = getAverage(i, w);
                averageList.get(w).add(average);
                Qlist.get(w).add(average/timeArray.get(i));
            }
        }
    }
    public Map<Double, List<Double>> getAverageList() {
        return averageList;
    }
    public Map<Double, List<Double>> getQlist() {
        return Qlist;
    }
}
