import model.Boundary;
import model.Particle;
import util.Constant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


/**
 * @since 27.05.2021
 * @author Ronny Klotz
 * Application procedure with drawing function
 */
public class Setup extends JPanel implements ActionListener {

    private final List<Boundary> walls;
    private final Particle particle;

    /**
     * Contructor for setup and basic configuration
     */
    public Setup() {

        this.setPreferredSize(new Dimension(Constant.WIDTH,Constant.HEIGHT));
        this.setBackground(new Color(0,0,0));
        this.setFocusable(true);
        this.addKeyListener(new CustomKeyListener());

        Timer timer = new Timer(Constant.DELAY, this);
        timer.start();

        walls = new ArrayList<>();
        createWalls();
        particle = new Particle();
    }

    /**
     * Function to get the graphic that allow you to draw objects
     * @param g Graphics of the super class
     */
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    /**
     * Actual draw function which will be used to draw the objects
     * @param g Graphics of the super class
     */
    public void draw (Graphics g) {
        for (Boundary wall : walls) {
            wall.show((Graphics2D) g);
        }
        PointerInfo info = MouseInfo.getPointerInfo();
        Point point = info.getLocation();
        particle.update(point.x, point.y);
        particle.look(walls, (Graphics2D) g);
    }

    /**
     * Function that will update the Application window in DELAY time (16ms = ~60FPS)
     * @param e ignored ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    public void createWalls() {
        walls.clear();
        for (int i = 0 ; i < 5 ; i++) {
            double x1 = ThreadLocalRandom.current().nextDouble(Constant.WIDTH);
            double y1 = ThreadLocalRandom.current().nextDouble(Constant.HEIGHT);
            double x2 = ThreadLocalRandom.current().nextDouble(Constant.WIDTH);
            double y2 = ThreadLocalRandom.current().nextDouble(Constant.HEIGHT);
            walls.add(new Boundary(x1,y1,x2,y2));
        }
    }

    private class CustomKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_UP) {
                particle.increaseBrightness();
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                particle.reduceBrightness();
            } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                createWalls();
            }
        }
    }
}
