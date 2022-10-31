package ar.edu.itba.ss;

import java.io.PrintWriter;

import static ar.edu.itba.ss.Utils.openFile;
import static ar.edu.itba.ss.Utils.writeToFile;

public class App 
{
    public static void main( String[] args )
    {
        PrintWriter pw = openFile("output/system.xyz");
        SimHandler sh = new SimHandler();

        writeToFile(pw, sh.printSystem());
        JsonPrinter jp = new JsonPrinter();
        //double w = 0.25;
        double outerStep = 0.05, lastTime = sh.getActualTime();
        sh.initParticlesPositions();
        while(sh.getActualTime() < sh.getTf()) {
            sh.iterate(jp);

            if (sh.getActualTime() - lastTime > outerStep ) {
                lastTime = sh.getActualTime();
                writeToFile(pw, sh.printSystem());
            }
        }
        PrintWriter pw2 = openFile("plots/QvsTime.json");
        PrintWriter pw3 = openFile("plots/ParticlesvsTime.json");
        writeToFile(pw2, jp.getQarray().toJSONString());
        writeToFile(pw3, jp.getPrtNumberOverTime().toJSONString());
    }
}
