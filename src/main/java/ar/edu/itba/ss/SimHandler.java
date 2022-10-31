package ar.edu.itba.ss;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimHandler {
    private final List<Particle> particles = new ArrayList<>();
    private final List<Wall> walls = new ArrayList<>();

    private double step = 0.001, actualTime = 0;
    private double tf = 50;
    private int N = 200;

    private double A = 0.30, w = 7.5, D = 3, L = 70;

    public SimHandler() {
        generateWalls();
        generateParticles();
//        particles.add(new Particle(new Vector2(8.30, 30), new Vector2(0, 0), 1, 1));
//        particles.add(new Particle(new Vector2(15, 30), new Vector2(0, 0), 1, 1));
    }

    public void generateParticles() {
        Random r = new Random(0);
        for (int i = 0; i < N;) {
            double radius = (0.85 + r.nextDouble() * (1.15 - 0.85));

            Vector2 R = new Vector2(radius + r.nextDouble() * (20 - radius * 2), radius + r.nextDouble() * (70 - radius * 2));
            boolean ok = true;
            for (Particle p : particles) {
                if (R.distanceTo(p.getActualR()) < radius + p.getRadius()) {
                    ok = false;
                    break;
                }
            }
            if (!ok) {
                continue;
            }
            particles.add(new Particle(R, new Vector2(0,0), 1, radius));
            i++;
        }
    }

    public Vector2 generateNewLocation() {
        Random r = new Random(0);
        double radius = (0.85 + r.nextDouble() * (1.15 - 0.85));
        boolean ok = false;
        Vector2 R = null;
        while (!ok) {
            boolean overlapped = false;
            R = new Vector2(radius + r.nextDouble() * (20 - radius * 2), 40 + r.nextDouble() * (70 - 40 - radius * 2));
            for (Particle p : particles) {
                if (R.distanceTo(p.getActualR()) < radius + p.getRadius()) {
                    overlapped = true;
                    break;
                }
            }
            if (overlapped) {
                continue;
            }
            ok = true;
        }
        return R;
    }

    private void generateWalls() {
        walls.add(new Wall(new Vector2(0, L), new Vector2(0, 0), A, w));

        walls.add(new Wall(new Vector2(0, 0), new Vector2(20.0 / 2 - D / 2, 0), A, w));

        walls.add(new Wall(new Vector2(20.0 / 2 + D / 2, 0), new Vector2(20, 0), A, w));

        walls.add(new Wall(new Vector2(20, 0), new Vector2(20, L), A, w));

        walls.add(new Wall(new Vector2(20, L), new Vector2(0, L), A, w));
    }

    public void initParticlesPositions() {
        for(Particle p : particles) {
            p.applyEulerModified(particles, walls, step);
        }
        actualTime += step;
    }

    public void iterate(JsonPrinter jsonPrinter) {
        int count = 0;
        for(Particle p : particles) {
            p.applyBeeman(particles, walls, step);
            if (isOutOfContainer(p)){
                count++;
            }
            else if (isOutOfMap(p)) {
                respawnParticle(p);
            }
        }
        for(Wall w: walls) {
            w.oscillate(actualTime);
        }
        actualTime += step;
        jsonPrinter.addPrtNumberStep(count, actualTime, w);
    }

    private void respawnParticle(Particle p) {
        p.setActualR(generateNewLocation());
    }

    private boolean isOutOfMap(Particle p) {
        return p.getActualR().getY() < - L / 10;
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

    public boolean isOutOfContainer(Particle particle){
        return particle.getActualR().getY() <= 0;
    }

    public void setW(double w) {
        this.w = w;
    }
}
