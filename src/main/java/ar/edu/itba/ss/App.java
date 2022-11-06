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
            for (int j = 0; j < 3; j++) {
                SimHandler sh = new SimHandler(v,0.15, 600);
                double outerStep = 0.05, lastTime = sh.getActualTime();
                sh.initParticlesPositions();
                while (sh.getActualTime() < sh.getTf()) {
                    sh.iterate();
                    dataAcumulator.addParticleCountStep(sh.getActualTime(),sh.getParticleCount(),v,j);
                    if (sh.getActualTime() - lastTime > outerStep) {
                        lastTime = sh.getActualTime();
                    }
                }
                dataAcumulator.setTimeArrayCompleted(true);
            }

        }
        dataAcumulator.calculateAverageList();
        PrintWriter pw3 = openFile("plots/ParticlesvsTime2.json");
        PrintWriter pw2 = openFile("plots/Qlist.json");
        jp.createParticleArray(dataAcumulator);
        writeToFile(pw3, jp.getPrtNumberOverTime().toJSONString());
        writeToFile(pw2, jp.getQarray().toJSONString());
    }
    public static void doorSimulation(){
        double[] doorSizes = {0.15, 4, 5, 6};
        double[] wList = {5};
        DataAcumulator dataAcumulator = new DataAcumulator(wList);
        JsonPrinter jp = new JsonPrinter();
        for(double doorSize: doorSizes){
            for (int i = 0; i < 3; i++){
                SimHandler sh = new SimHandler(5,doorSize, 600);

                double outerStep = 0.05, lastTime = sh.getActualTime();
                sh.initParticlesPositions();
                while (sh.getActualTime() < sh.getTf()) {
                    sh.iterate();

                    if (sh.getActualTime() - lastTime > outerStep) {
                        lastTime = sh.getActualTime();
                    }
                }
                dataAcumulator.setTimeArrayCompleted(true);
            }
        }
    }
}
