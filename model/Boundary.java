package model;

import java.awt.*;
import java.awt.geom.Line2D;

public class Boundary {

    protected Vector a;
    protected Vector b;

    public Boundary(double x1, double y1, double x2, double y2) {
        a = new Vector(x1,y1);
        b = new Vector(x2,y2);
    }

    public void show(Graphics2D g) {
        Line2D line = new Line2D.Double();
        g.setColor(Color.WHITE);
        line.setLine(a.x,a.y,b.x,b.y);
        g.draw(line);
    }
}
