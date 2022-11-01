package ar.edu.itba.ss;

import java.io.PrintWriter;

import static ar.edu.itba.ss.Utils.openFile;
import static ar.edu.itba.ss.Utils.writeToFile;

public class App 
{
    public static void main( String[] args )
    {
        double[] wList = {5, 10, 15, 20, 30, 50};
        for(int i = 0; i < wList.length;i++){
            PrintWriter pw = openFile("output/system.xyz");
            SimHandler sh = new SimHandler();

            DataAcumulator dataAcumulator = new DataAcumulator(wList);
            writeToFile(pw, sh.printSystem());
            JsonPrinter jp = new JsonPrinter();
            sh.setW(wList[i]);
            double outerStep = 0.05, lastTime = sh.getActualTime();
            sh.initParticlesPositions();
            while(sh.getActualTime() < sh.getTf()) {
                sh.iterate(dataAcumulator);

                if (sh.getActualTime() - lastTime > outerStep ) {
                    lastTime = sh.getActualTime();
                    writeToFile(pw, sh.printSystem());
                }
            }
            PrintWriter pw2 = openFile("plots/QvsTime"+ sh.getW() +".json");
            PrintWriter pw3 = openFile("plots/ParticlesvsTime"+sh.getW()+".json");
            jp.createParticleArray(sh.getW(), dataAcumulator.getCountList(sh.getW()));
            jp.createQArray(sh.getW(), dataAcumulator.getQList(sh.getW()));
            writeToFile(pw2, jp.getQarray().toJSONString());
            writeToFile(pw3, jp.getPrtNumberOverTime().toJSONString());
        }
    }
}
