package model;

public class Vector {

    protected double x;
    protected double y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector() {
        this.x = 0;
        this.y = 0;
    }

    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void normalize() {
        double m = mag();
        if (m != 0 && m != 1) {
            div(m);
        }
    }

    public double mag() {
        return Math.sqrt(x*x + y*y);
    }

    public void div(double n) {
        x /= n;
        y /= n;
    }

    @Override
    public String toString() {
        return "Vector{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
