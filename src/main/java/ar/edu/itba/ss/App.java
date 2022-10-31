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
        PrintWriter pw = openFile("output/withCIM.xyz");
        int[] wList = {5, 10, 15, 20, 30, 50};
        for(int i = 0; i < wList.length;i++){
            List<Integer> particleCount = new ArrayList<>();
            SimHandler sh = new SimHandler();

            writeToFile(pw, sh.printSystem());
            JsonPrinter jp = new JsonPrinter();
            sh.setW(wList[i]);
            double outerStep = 0.1, lastTime = sh.getActualTime();
            sh.initParticlesPositions();
            while(sh.getActualTime() < sh.getTf()) {
                sh.iterate(jp);
                
                if (sh.getActualTime() - lastTime > outerStep ) {
                    lastTime = sh.getActualTime();
                    writeToFile(pw, sh.printSystem());
                    System.out.println(sh.getActualTime());
                }
            }
            PrintWriter pw2 = openFile("plots/QvsTime.json");
            PrintWriter pw3 = openFile("plots/ParticlesvsTime.json");
            writeToFile(pw2, jp.getQarray().toJSONString());
            writeToFile(pw3, jp.getPrtNumberOverTime().toJSONString());
        }
    }
}
