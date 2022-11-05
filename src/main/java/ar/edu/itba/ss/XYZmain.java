package ar.edu.itba.ss;

import java.io.PrintWriter;

import static ar.edu.itba.ss.Utils.openFile;
import static ar.edu.itba.ss.Utils.writeToFile;

public class XYZmain {

    public static void main(String[] args){

        double[] wList = {20};
        PrintWriter pw = openFile("output/system.xyz");
        SimHandler sh = new SimHandler();

        DataAcumulator dataAcumulator = new DataAcumulator(wList);
        writeToFile(pw, sh.printSystem());
        sh.setW(wList[0]);
        double outerStep = 0.05, lastTime = sh.getActualTime();
        sh.initParticlesPositions();
        while(sh.getActualTime() < sh.getTf()) {
            sh.iterate(dataAcumulator,0);

            if (sh.getActualTime() - lastTime > outerStep ) {
                lastTime = sh.getActualTime();
                writeToFile(pw, sh.printSystem());
            }
        }
    }
}
