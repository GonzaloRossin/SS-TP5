package ar.edu.itba.ss;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;
import java.util.Map;
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
        for(Double time: timeSet){
            JSONObject step = new JSONObject();
            step.put("time", time);
            for(Double w : dataAcumulator.particleCountVsTime.keySet()){
                step.put(w.toString(), dataAcumulator.particleCountVsTime.get(w).get(time).getAverage());
            }
            prtNumberOverTime.add(step);
        }

    }

    public void createQArray(DataAcumulator dataAcumulator){
        Set<Double> timeSet = dataAcumulator.QoverTime.get(5.0).keySet();
        for(Double time: timeSet){
            JSONObject step = new JSONObject();
            step.put("time", time);
            for(Double w : dataAcumulator.QoverTime.keySet()){
                step.put(w.toString(), dataAcumulator.QoverTime.get(w).get(time).getAverage());
            }
            Qarray.add(step);
        }
    }

    public JSONArray getQarray() {
        return Qarray;
    }

    public JSONArray getPrtNumberOverTime() {
        return prtNumberOverTime;
    }
}
