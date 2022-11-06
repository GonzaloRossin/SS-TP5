package ar.edu.itba.ss;

import java.io.PrintWriter;

import static ar.edu.itba.ss.Utils.openFile;
import static ar.edu.itba.ss.Utils.writeToFile;

public class XYZmain {

    public static void main(String[] args){

        PrintWriter pw = openFile("output/system50.xyz");
        SimHandler sh = new SimHandler(50,0.15);

        writeToFile(pw, sh.printSystem());
        double outerStep = 0.05, lastTime = sh.getActualTime();
        sh.initParticlesPositions();
        while(sh.getActualTime() < sh.getTf()) {
            sh.iterate();

            if (sh.getActualTime() - lastTime > outerStep ) {
                lastTime = sh.getActualTime();
                writeToFile(pw, sh.printSystem());
            }
        }
    }
}
