package ar.edu.itba.ss;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;
import java.util.Map;

public class JsonPrinter {
    JSONArray Qarray;
    JSONArray prtNumberOverTime;

    public JsonPrinter() {
        Qarray = new JSONArray();
        prtNumberOverTime = new JSONArray();
    }

    public void createParticleArray(double w,List<Pair<Double,Integer>> particleList){
        for(Pair<Double, Integer> pair : particleList){
            addPrtNumberStep(pair.getSecondValue(), pair.getFirstValue(), w);
        }
    }

    public void createQArray(double w,List<Pair<Double,Double>> QList){
        for(Pair<Double, Double> pair : QList){
            addQstep(pair.getSecondValue(), pair.getFirstValue(), w);
        }
    }

    public void addQstep(double Q, double time, double w){
        JSONObject qStep = new JSONObject();
        qStep.put("Q", Q);
        qStep.put("time", time);
        qStep.put("w", w);
        Qarray.add(qStep);
    }

    public void addPrtNumberStep(int particleCount, double time, double w){
        JSONObject step = new JSONObject();
        step.put("particles", particleCount);
        step.put("time", time);
        step.put("w", w);
        prtNumberOverTime.add(step);
        addQstep( particleCount/time, time, w);
    }

    public JSONArray getQarray() {
        return Qarray;
    }

    public JSONArray getPrtNumberOverTime() {
        return prtNumberOverTime;
    }
}
