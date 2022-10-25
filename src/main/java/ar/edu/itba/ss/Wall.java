package ar.edu.itba.ss;

import java.util.Locale;

public class Wall {
    private Vector2 startPoint, endPoint;
    private double xyzRadius = 0.25;
    private int color = 100;

    public Wall(Vector2 startPoint, Vector2 endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public String toXYZ() {
        StringBuilder sb = new StringBuilder();
//        sb.append( String.format(Locale.US,"%f %f %d %d\n", startPoint.getX(), startPoint.getY(), xyzRadius, color));
//        sb.append( String.format(Locale.US,"%f %f %d %d\n", endPoint.getX(), endPoint.getY(), xyzRadius, color));
        Vector2 lineVersor = endPoint.substract(startPoint).normalize();
        Vector2 current = startPoint.clone();
        for (int i = 0; i < wallXYZSize(); i++) {
            sb.append( String.format(Locale.US,"%f %f %f %d\n", current.getX(), current.getY(), xyzRadius, color));
            current = current.sum(lineVersor.scalarProduct(xyzRadius * 2));
        }
        return sb.toString();
    }

    public int wallXYZSize() {
        double distance = startPoint.distanceTo(endPoint);
        return (int)(distance / (xyzRadius * 2)) + 1;
//        return 2;
    }

    public double distanceToPoint(Particle particle) {
        double term1 = (endPoint.getX() - startPoint.getX()) * (startPoint.getY() - particle.getActualR().getY());
        double term2 = (startPoint.getX() - particle.getActualR().getX()) * (endPoint.getY() - startPoint.getY());
        double numerator = Math.abs(term1 - term2);

        double denominator = endPoint.distanceTo(startPoint);
        return numerator / denominator;
    }

    public Vector2 getNormalVersor() {
        return startPoint.substract(endPoint).normalize().getOrthogonal();
    }
}
