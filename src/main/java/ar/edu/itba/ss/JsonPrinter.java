package ar.edu.itba.ss;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class JsonPrinter {
    JSONArray Qarray;
    JSONArray prtNumberOverTime;

    public JsonPrinter() {
        Qarray = new JSONArray();
        prtNumberOverTime = new JSONArray();
    }

    public void createParticleArray(DataAcumulator dataAcumulator){
        Set<Double> timeSet = dataAcumulator.particleCountVsTime.get(5.0).keySet();
        List<Double> sortedTimes =  new ArrayList<>(timeSet);
        Collections.sort(sortedTimes);
        for(Double time: sortedTimes){
            JSONObject step = new JSONObject();
            step.put("time", time);
            for(Double w : dataAcumulator.particleCountVsTime.keySet()){
                step.put(w.toString(), dataAcumulator.particleCountVsTime.get(w).get(time).getAverage());
            }
            prtNumberOverTime.add(step);
        }

    }

    public JSONArray getPrtNumberOverTime() {
        return prtNumberOverTime;
    }
}
