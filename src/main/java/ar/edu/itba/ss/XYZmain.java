package ar.edu.itba.ss;

import java.io.PrintWriter;

import static ar.edu.itba.ss.Utils.openFile;
import static ar.edu.itba.ss.Utils.writeToFile;

public class XYZmain {

    public static void main(String[] args){
        double[] wList = {5, 10, 15, 20, 30, 50};
        for(double w : wList){
            PrintWriter pw = openFile("output/system"+w+".xyz");
            SimHandler sh = new SimHandler(w,0.15, 200, 3);

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
}
