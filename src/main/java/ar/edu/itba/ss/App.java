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
//        simulation();
//        doorSimulation();
        varyW();
    }
    public static void doorSimulation(){
        double[] doorSizes = {3, 4, 5, 6};
        double[] wList = {5};

        DataAcumulator dataAcumulator = new DataAcumulator();
        JsonPrinter jp = new JsonPrinter();
        for(double doorSize: doorSizes) {

            for (int i = 0; i < 3; i++) {
                SimHandler sh = new SimHandler(5, doorSize, 100);

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

    public static void varyW() {
        double[] wList = {5, 10, 15, 20, 30, 50};
//        double[] wList = { 30 };
//        DataAcumulator dataAcumulator = new DataAcumulator(wList);
        JsonPrinter jp = new JsonPrinter();
        for (int j = 0; j < 3; j++) {
            for (double v : wList) {
                DataAcumulator dAccum = new DataAcumulator();
                SimHandler sh = new SimHandler(v,0.15, 50);
                double outerStep = 0.05, lastTime = sh.getActualTime();
                sh.initParticlesPositions();
                while (sh.getActualTime() < sh.getTf()) {
                    if (sh.iterate()) {
                        dAccum.addParticleCountStep(sh.getActualTime(), sh.getParticleCount());
                    }
                    if (sh.getActualTime() - lastTime > outerStep) {
                        lastTime = sh.getActualTime();
                    }
                }
                // Calculo de caudal, ya tengo toda la informacion de la tirada
                System.out.print("" + v + " ");

                // Aca al jp
                jp.addQOverTime(v, dAccum.getTimeArray(), dAccum.getQ());
                jp.addParticleCountOverTime(v, dAccum.getTimeArray(), dAccum.getParticleCounts());
            }
            // Calculo del error de Q para cierto w

            PrintWriter pw3 = openFile("plots/ParticlesvsTime" + j + ".json");
            PrintWriter pw2 = openFile("plots/Qlist" + j + ".json");

//        jp.createParticleArray(dAccum);
            writeToFile(pw3, jp.getPrtNumberOverTime().toJSONString());
            writeToFile(pw2, jp.getQarray().toJSONString());

            System.out.println("Finished j = " + j);
        }
//        dataAcumulator.calculateAverageList();
//        dAccum.calculateQlist();

    }
    public static void simulation() {
        PrintWriter pw = openFile("output/system.xyz");
        SimHandler sh = new SimHandler(30, 0.15, 50);

        writeToFile(pw, sh.printSystem());

        double outerStep = 0.1, lastTime = sh.getActualTime();
        sh.initParticlesPositions();
        while(sh.getActualTime() < sh.getTf()) {
            sh.iterate();

            if (sh.getActualTime() - lastTime > outerStep ) {
                lastTime = sh.getActualTime();
                writeToFile(pw, sh.printSystem());
                System.out.println(sh.getActualTime());
            }
        }
    }
}
