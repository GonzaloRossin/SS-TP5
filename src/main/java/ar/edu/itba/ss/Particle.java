package ar.edu.itba.ss;

import java.util.List;
import java.util.Locale;

public class Particle {
    private Vector2 actualR, lastR = new Vector2(0,0), actualV, actualForce;
    private final double mass, radius;
    private final double g = 980;
    private final int color = 0;

    public Particle(Vector2 actualR, Vector2 actualV, double mass, double radius) {
        this.actualR = actualR;
        this.actualV = actualV;
        this.mass = mass;
        this.radius = radius;
    }

    public Vector2 calculateForceSum() {
        Vector2 newForce = new Vector2(0,0);

        return newForce.sum(calculateGravity());
    }

    private Vector2 calculateGravity() {
        Vector2 gravityVersor = new Vector2(0, -1);
        return gravityVersor.scalarProduct(g);
    }

    public void applyEulerModified(/* List<Planet> planets,*/ double step) {
        Vector2 lastForce = actualForce;
        actualForce = calculateForceSum();

        Vector2 a = actualForce.scalarProduct(1/mass);

        Vector2 lastV = actualV;
        Vector2 term = actualForce.scalarProduct(step / mass);
        actualV = actualV.sum(term);

        lastR = actualR;

        Vector2 term1 = actualV.scalarProduct(step);
        Vector2 term2 = actualForce.scalarProduct(step * step / (2 * mass));
        actualR = actualR.sum(term1).sum(term2);
    }

    public Double applyBeeman(/* List<Planet> planets,*/ double step) {
        Vector2 lastForce = actualForce;
        actualForce = calculateForceSum();

        Vector2 actualA = actualForce.scalarProduct(1.0/mass);
        Vector2 lastA = lastForce.scalarProduct(1.0/mass);

        Vector2 rTerm1 = actualV.scalarProduct(step);
        Vector2 rTerm2 = actualA.scalarProduct((2.0/3.0) * step * step);
        Vector2 rTerm3 = lastA.scalarProduct((1.0/6.0) * step * step);

        // Stores in actualR the position r(t + step)
        actualR = actualR.sum(rTerm1).sum(rTerm2).substract(rTerm3);

        // Calculates predicted velocity
        Vector2 predictV = actualV.sum(actualA.scalarProduct((3.0/2.0) * step)).substract(lastA.scalarProduct((1.0/2.0) * step));

        // Calculate force in t + step
        Vector2 nextForce = calculateForceSum();
        Vector2 nextA = nextForce.scalarProduct(1.0/mass);

        // Calculate corrected velocity
        Vector2 term1 = nextA.scalarProduct((1.0/3.0) * step);
        Vector2 term2 = actualA.scalarProduct((5.0/6.0) * step);
        Vector2 term3 = lastA.scalarProduct((1.0/6.0) * step);

        actualV = actualV.sum(term1).sum(term2).substract(term3);
        return actualR.getX();
    }

    public String toXYZ() {
        return String.format(Locale.US,"%f %f %f %d\n", actualR.getX(), actualR.getY(), radius, color);
    }
}
