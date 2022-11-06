package ar.edu.itba.ss;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

public class JsonPrinter {
    JSONArray Qarray;
    JSONArray prtNumberOverTime;

    public JsonPrinter() {
        Qarray = new JSONArray();
        prtNumberOverTime = new JSONArray();
    }

    public void createParticleArray(DataAcumulator dataAcumulator){
        for(int i = 0; i < dataAcumulator.timeArray.size() - 1; i++){
            JSONObject step = new JSONObject();
            JSONObject qStep = new JSONObject();
            qStep.put("time", dataAcumulator.timeArray.get(i));
            step.put("time", dataAcumulator.timeArray.get(i));
            for (Double w : dataAcumulator.averageList.keySet()) {
                step.put(w.toString(), dataAcumulator.getParticleCountVsTime1().get(w).get(i));
                qStep.put(w.toString(), dataAcumulator.getQlist().get(w).get(i));
            }
            prtNumberOverTime.add(step);
            Qarray.add(qStep);
        }
    }

    public JSONArray getPrtNumberOverTime() {
        return prtNumberOverTime;
    }

    public JSONArray getQarray() {
        return Qarray;
    }

    public void addQOverTime(Double w, List<Double> time, List<Double> q) {
        for(int i = 0; i < q.size() - 1; i++){
            JSONObject qStep = new JSONObject();
            qStep.put("time", time.get(i));
            qStep.put(w.toString(), q.get(i));
            Qarray.add(qStep);
        }
    }
}
