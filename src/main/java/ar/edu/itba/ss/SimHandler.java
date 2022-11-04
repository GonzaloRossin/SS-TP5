package ar.edu.itba.ss;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static java.lang.Math.round;

public class SimHandler {
    private final List<Particle> particles = new ArrayList<>();
    private final List<Wall> walls = new ArrayList<>();

    private double step = 0.001, actualTime = 0;
    private double tf = 400;
    private int N = 200, particleCount = 0;

    private double A = 0.15, w = 10, D = 3, L = 70, offset = 0.8;

    private CIM cim;

    public SimHandler() {
        generateWalls();
        generateParticles();
//        particles.add(new Particle(new Vector2(10 + 1.5, L/2 + 1.5), new Vector2(0, 0), 1, 1));
//        particles.add(new Particle(new Vector2(10 + offset, L/2 + 1.5), new Vector2(0, 0), 1, 1));
//        particles.add(new Particle(new Vector2(10 - offset, L/2 + 1.5), new Vector2(0, 0), 1, 1));
//        particles.add(new Particle(new Vector2(10 + 1.5, L/2 + offset), new Vector2(0, 0), 1, 1));
//        particles.add(new Particle(new Vector2(10 + offset, L/2 + offset), new Vector2(0, 0), 1, 1));
//        particles.add(new Particle(new Vector2(10 - offset, L/2 + offset), new Vector2(0, 0), 1, 1));
    }

    public void generateParticles() {
        Random r = new Random(0);
        for (int i = 0; i < N;) {
            double radius = 0.85 + i * (1.15 - 0.85) / N;
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

    public Vector2 generateNewLocation(double radius) {
        Random r = new Random(1);
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
        cim = new CIM(particles);

        for(Particle p : particles) {
            List<Particle> neighbours = cim.calculateNeighbours(p);
            p.applyEulerModified(neighbours, walls, step);
        }
        actualTime += step;
    }

    public void iterate(DataAcumulator dataAcumulator, int run) {
        for(Particle p : particles) {

            List<Particle> neighbours = cim.calculateNeighbours(p);

            p.applyBeeman(neighbours, walls, step);
            if (isOutOfContainer(p)){
                particleCount++;
                p.setOutOfSilo(true);
            }
            if (isOutOfMap(p)) {
                respawnParticle(p);
            }

            cim.updateParticle(p);
        }
        for(Wall w: walls) {
            w.oscillate(actualTime);
        }
        actualTime += step;
        dataAcumulator.addParticleCountStep(actualTime, particleCount, w, run);
        if(particleCount % 10 == 0){
            dataAcumulator.addQ( w, particleCount, particleCount / actualTime, run);
        }
    }

    private void respawnParticle(Particle p) {
        p.setActualR(generateNewLocation(p.getRadius()));
        p.setActualV(new Vector2(0,0));
        p.setOutOfSilo(false);
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

    public double getW() {
        return w;
    }

    public boolean isOutOfContainer(Particle particle){
        return particle.getActualR().getY() <= -particle.getRadius() && !particle.isOutOfSilo();
    }

    public void setW(double w) {
        this.w = w;
    }
}
