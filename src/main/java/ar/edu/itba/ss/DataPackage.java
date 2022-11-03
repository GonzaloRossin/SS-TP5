package ar.edu.itba.ss;

import java.util.ArrayList;
import java.util.List;

public class DataPackage<U>{
    private final List<U> runList;

    public DataPackage() {
        runList = new ArrayList<>();
    }

    public void addValue(U value){
        runList.add(value);
    }

    public double getAverage(){
        double sum = 0;
        for(U value: runList){
            sum += (Double) value;
        }
        return sum/ runList.size();
    }

    public double getMin(){
        double min =(Double) runList.get(0);
        for(int i=1;i< runList.size();i++){
            double currentVal = (Double) runList.get(i);
            if(currentVal < min)
                min = currentVal;
        }
        return min;
    }

    public double getMax(){
        double max = 0.0;
        for(U value: runList){
            double currentVal = (Double) value;
            if(currentVal > max)
                max =  currentVal;
        }
        return max;
    }
}
