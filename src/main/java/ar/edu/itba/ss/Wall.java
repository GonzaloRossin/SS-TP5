package ar.edu.itba.ss;

import java.util.Locale;

public class Wall {
    private Vector2 startPoint, endPoint;
    int xyzRadius = 1, color = 100;

    public Wall(Vector2 startPoint, Vector2 endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public String toXYZ() {
        StringBuilder sb = new StringBuilder();
        sb.append( String.format(Locale.US,"%f %f %d %d\n", startPoint.getX(), startPoint.getY(), xyzRadius, color));
        sb.append( String.format(Locale.US,"%f %f %d %d\n", endPoint.getX(), endPoint.getY(), xyzRadius, color));
//        Vector2 lineVersor = endPoint.substract(startPoint).normalize();
//        Vector2 current = startPoint.clone();
//        for (int i = 0; i * 10 < wallXYZSize(); i++) {
//            sb.append( String.format(Locale.US,"%f %f %d %d\n", current.getX(), current.getY(), xyzRadius, color));
//            current = current.sum(lineVersor.scalarProduct(xyzRadius));
//        }
        return sb.toString();
    }

    public int wallXYZSize() {
//        double distance = startPoint.distanceTo(endPoint);
//        return (int)(distance / xyzRadius);
        return 2;
    }
}
