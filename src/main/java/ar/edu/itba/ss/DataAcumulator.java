package ar.edu.itba.ss;

import java.util.ArrayList;
import java.util.List;

public class DataAcumulator {
    List<List<Integer>> particleCountList = new ArrayList<>();



    public DataAcumulator(List<List<Integer>> particleCountList) {
        this.particleCountList = particleCountList;
    }

    public List<List<Integer>> getParticleCountList() {
        return particleCountList;
    }
}
