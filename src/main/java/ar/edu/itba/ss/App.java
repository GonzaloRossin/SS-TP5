package ar.edu.itba.ss;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static ar.edu.itba.ss.Utils.openFile;
import static ar.edu.itba.ss.Utils.writeToFile;

public class App 
{
    public static void main( String[] args )
    {
        double[] wList = {5, 10, 15, 20, 30, 50};
        DataAcumulator dataAcumulator = new DataAcumulator(wList);
        JsonPrinter jp = new JsonPrinter();
        for (double v : wList) {
            for (int j = 0; j < 2; j++) {
                SimHandler sh = new SimHandler();
                sh.setW(v);
                double outerStep = 0.05, lastTime = sh.getActualTime();
                sh.initParticlesPositions();
                while (sh.getActualTime() < sh.getTf()) {
                    sh.iterate(dataAcumulator, j);

                    if (sh.getActualTime() - lastTime > outerStep) {
                        lastTime = sh.getActualTime();
                    }
                }
            }

        }
        PrintWriter pw2 = openFile("plots/QvsTime.json");
        PrintWriter pw3 = openFile("plots/ParticlesvsTime.json");
        jp.createParticleArray(dataAcumulator);
        jp.createQArray(dataAcumulator);
        writeToFile(pw2, jp.getQarray().toJSONString());
        writeToFile(pw3, jp.getPrtNumberOverTime().toJSONString());
    }
}
