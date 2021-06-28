package model;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

import static util.Constant.*;

public class Particle {

    private final Vector pos;
    private final List<Ray> rays;

    private float brightness = 0.3f;

    public Particle() {
        pos = new Vector(WIDTH/2, HEIGHT/2);
        rays = new ArrayList<>();
        for (int i = 0 ; i < 360; i+=1) {
            rays.add(new Ray(pos, Math.toRadians(i)));
        }
    }

    public void update(double x, double y) {
        pos.set(x,y);
    }


    public void look(List<Boundary> walls, Graphics2D g) {
        Ellipse2D ellipse = new Ellipse2D.Double();
        ellipse.setFrame(pos.x, pos.y, 4,4);
        g.fill(ellipse);
        for (Ray ray : rays) {
            Vector closest = null;
            double record = Double.MAX_VALUE;
            for (Boundary wall : walls) {
                Vector pt = ray.cast(wall);
                if (pt != null) {
                    double d = dist(pos, pt);
                    if (d < record) {
                        record = d;
                        closest = pt;
                    }
                }
            }
            if (closest != null) {
                Line2D line = new Line2D.Double();
                Color color = new Color(1,1,1,brightness);
                g.setPaint(color);
                line.setLine(pos.x, pos.y, closest.x, closest.y);
                g.draw(line);
            }
        }
    }

    public void increaseBrightness () {
        if (brightness < 1.0f) {
            brightness = brightness + 0.1f;
        }
    }

    public void reduceBrightness () {
        if (brightness > 0.1f) {
            brightness = brightness - 0.1f;
        }
    }

    public double dist(Vector a, Vector b) {
        return Math.sqrt((b.y - a.y) * (b.y - a.y) + (b.x - a.x) * (b.x - a.x));
    }
}
