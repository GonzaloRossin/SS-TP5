package ar.edu.itba.ss;

import java.util.HashMap;
import java.util.Map;

public class DataAcumulator {
    Map<Double, Map<Double,DataPackage<Double>>> particleCountVsTime;

    public DataAcumulator(double[] wList) {
        particleCountVsTime = new HashMap<>();
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


}
