package ar.edu.itba.ss;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JsonPrinter {
    private JSONArray Qarray;
    private JSONArray prtNumberOverTime;

    public JsonPrinter() {
        Qarray = new JSONArray();
        prtNumberOverTime = new JSONArray();
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
}
