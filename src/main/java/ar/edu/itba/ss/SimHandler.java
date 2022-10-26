package ar.edu.itba.ss;

import java.util.ArrayList;
import java.util.List;

public class SimHandler {
    private final List<Particle> particles = new ArrayList<>();
    private final List<Wall> walls = new ArrayList<>();

    private double step = 0.001, actualTime = 0;
    private double tf = 10;

    public SimHandler() {
        generateWalls();
        particles.add(new Particle(new Vector2(5, 3), new Vector2(100, 0), 1, 1));
        particles.add(new Particle(new Vector2(15, 3), new Vector2(-100, 0), 1, 1));
    }

    private void generateWalls() {
        walls.add(new Wall(new Vector2(0, 70), new Vector2(0, 0)));

        walls.add(new Wall(new Vector2(0, 0), new Vector2(20, 0)));

        walls.add(new Wall(new Vector2(20, 0), new Vector2(20, 70)));

        walls.add(new Wall(new Vector2(20, 70), new Vector2(0, 70)));
    }

    public void initParticlesPositions() {
        for(Particle p : particles) {
            p.applyEulerModified(particles, walls, step);
        }
        actualTime += step;
    }

    public void iterate() {
        for(Particle p : particles) {
            p.applyBeeman(particles, walls, step);
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
        StringBuilder sb = new StringBuilder();
        for(Wall w : walls) {
            sb.append(w.toXYZ());
        }
        return sb.toString();
    }

    public int wallsSize() {
        int accum = 0;
        for (Wall wall : walls) {
            accum += wall.wallXYZSize();
        }
        return accum;
    }

    public String printSystem() {
        return String.format("%d\n\n%s%s", particles.size() + wallsSize(), printParticles(), printWalls());
    }

    public double getActualTime() {
        return actualTime;
    }

    public double getTf() {
        return tf;
    }
}
