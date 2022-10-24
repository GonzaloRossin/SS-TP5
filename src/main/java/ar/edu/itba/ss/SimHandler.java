package ar.edu.itba.ss;

import java.util.ArrayList;
import java.util.List;

public class SimHandler {
    private final List<Particle> particles = new ArrayList<>();

    private double step = 0.001, actualTime = 0;
    private double tf = 100;

    public SimHandler() {
        particles.add(new Particle(new Vector2(35, 10), new Vector2(0, 0), 1, 1));
    }

    public void initParticlesPositions() {
        for(Particle p : particles) {
            p.applyEulerModified(step);
        }
        actualTime += step;
    }

    public void iterate() {
        for(Particle p : particles) {
            p.applyBeeman(step);
        }
        actualTime += step;
    }

    public String printParticles() {
        StringBuilder sb = new StringBuilder();
        for(Particle p : particles) {
            sb.append(p.toXYZ());
        }
        return sb.toString();
    }

    public String printWalls() {
        return "";
    }

    public String printSystem() {
        String.format("%d\n\n%s%s", particles.size() /* + walls.size()*/, printParticles(), printWalls());
        return printParticles() + printWalls();
    }

    public double getActualTime() {
        return actualTime;
    }

    public double getTf() {
        return tf;
    }
}
