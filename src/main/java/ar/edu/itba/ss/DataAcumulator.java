package ar.edu.itba.ss;

import java.util.HashMap;
import java.util.Map;

public class DataAcumulator {
    Map<Double, Map<Double,DataPackage<Double>>> particleCountVsTime;
    Map<Double, Map<Double,DataPackage<Double>>> QoverTime;

    public DataAcumulator(double[] wList) {
        particleCountVsTime = new HashMap<>();
        QoverTime = new HashMap<>();
        for (int i = 0; i < wList.length; i++) {
            particleCountVsTime.put(wList[i], new HashMap<Double, DataPackage<Double>>());
        }

    }

    public void addParticleCountStep(double time, int count, double w, int run){
        if(run == 0){
            DataPackage<Double> step = new DataPackage<>();
            step.addValue((double) count);
            particleCountVsTime.get(w).put(time, step);
        }
        else{
            particleCountVsTime.get(w).get(time).addValue((double) count);
        }
    }

    public void addQ(double w, int particleCount, double Q, int run){
        if(run == 0){
            DataPackage<Double> step = new DataPackage<>();
            step.addValue(Q);
            QoverTime.put((double) particleCount, new HashMap<Double, DataPackage<Double>>());
            QoverTime.get((double) particleCount).put(w, step);
        }else {
            QoverTime.get((double) particleCount).get(w).addValue(Q);
        }
    }

}
