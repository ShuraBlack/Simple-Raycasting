package model;

public class Ray {

    protected Vector pos;
    protected Vector dir;

    public Ray(Vector pos, double angle) {
        this.pos = pos;
        this.dir = fromAngle(angle);
    }

    public Vector cast(Boundary wall) {
        double x1 = wall.a.x;
        double y1 = wall.a.y;
        double x2 = wall.b.x;
        double y2 = wall.b.y;

        double x3 = pos.x;
        double y3 = pos.y;
        double x4 = pos.x + dir.x;
        double y4 = pos.y + dir.y;

        double den = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
        if (den == 0.0) {
            return null;
        }

        double t = ((x1 - x3) * (y3 - y4) - (y1 - y3) * (x3 - x4)) / den;
        double u = -((x1 - x2) * (y1 - y3) - (y1 - y2) * (x1 - x3)) / den;

        if (t > 0 && t < 1 && u > 0) {
            Vector pt = new Vector();
            pt.x = x1 + t * (x2 - x1);
            pt.y = y1 + t * (y2 - y1);
            return pt;
        } else {
            return null;
        }
    }

    public Vector fromAngle(double angle) {
        return new Vector(Math.cos(angle), Math.sin(angle));
    }
}
