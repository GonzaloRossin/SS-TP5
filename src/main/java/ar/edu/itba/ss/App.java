package ar.edu.itba.ss;

import java.io.PrintWriter;

import static ar.edu.itba.ss.Utils.openFile;
import static ar.edu.itba.ss.Utils.writeToFile;

public class App 
{
    public static void main( String[] args )
    {
        PrintWriter pw = openFile("output/withCIM.xyz");
        SimHandler sh = new SimHandler();

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
