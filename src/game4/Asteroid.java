package game4;

import java.awt.Color;
import java.awt.Graphics2D;

import utilities.Vector2D;

public class Asteroid extends GameObject {
    // constant related to max velocity of asteroid
    public static final double VMAX = 100;
    public final static int RADIUS = 10;

    public Asteroid(Vector2D p, Vector2D v) {
        super(p, v, RADIUS);
    }

    public Asteroid(double x, double y, double vx, double vy) {
        super(x, y, vx, vy, RADIUS);
    }

    public Asteroid() {
        this(Constants.FRAME_WIDTH * Math.random(), Constants.FRAME_HEIGHT * Math.random(),
                2 * VMAX * (Math.random() - 0.5), 2 * VMAX * (Math.random() - 0.5));
    }

    @Override
    public void draw(Graphics2D g) {
        int x = (int) position.x;
        int y = (int) position.y;
        g.setColor(Color.red);
        g.fillOval(x - RADIUS, y - RADIUS, 2 * RADIUS, 2 * RADIUS);
    }
}
