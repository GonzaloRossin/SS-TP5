package ar.edu.itba.ss;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JsonPrinter {
    JSONArray Qarray;
    JSONArray prtNumberOverTime;

    public JsonPrinter() {
        Qarray = new JSONArray();
        prtNumberOverTime = new JSONArray();
    }

    public void createParticleArray(DataAcumulator dataAcumulator){
        for(int i = 0;i<dataAcumulator.timeArray.size();i++){
            JSONObject step = new JSONObject();
            JSONObject qStep = new JSONObject();
            qStep.put("time", dataAcumulator.timeArray.get(i));
            step.put("time", dataAcumulator.timeArray.get(i));
            for(Double w: dataAcumulator.particleCountVsTime1.keySet()){
                step.put(w.toString(), dataAcumulator.getAverageList().get(w).get(i));
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
}
