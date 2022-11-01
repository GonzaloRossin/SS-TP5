package ar.edu.itba.ss;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataAcumulator {
    Map<Double, List<Pair<Double, Integer>>> particleCountVsTime;
    Map<Double, List<Pair<Double, Double>>> QoverTime;

    public DataAcumulator(double[] wList) {
        particleCountVsTime = new HashMap<>();
        QoverTime = new HashMap<>();
        for (int i = 0; i < wList.length; i++) {
            particleCountVsTime.put(wList[i], new ArrayList<Pair<Double,Integer>>());
            QoverTime.put(wList[i], new ArrayList<Pair<Double,Double>>());
        }

    }

    public void addParticleCountStep(double time, int count, double w){
        particleCountVsTime.get(w).add(new Pair<>(time, count));
    }

    public void addQ(double w, double time, double Q){
        QoverTime.get(w).add(new Pair<>(time, Q));
    }

    public List<Pair<Double, Integer>> getCountList(double w){
        return particleCountVsTime.get(w);
    }
    public List<Pair<Double, Double>> getQList(double w){
        return QoverTime.get(w);
    }
}
