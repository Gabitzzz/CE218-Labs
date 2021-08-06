package game4;

import java.awt.Color;
import java.awt.Graphics2D;

import utilities.Vector2D;

public class Bullet extends GameObject {
    public static final double INITIAL_SPEED = 200;
    public static final int TIME_TO_LIVE = 30;
    public static final int RADIUS = 2;
    private int ttl;

    public Bullet(Vector2D p, Vector2D v) {
        super(p,v,RADIUS);
        this.ttl = TIME_TO_LIVE;
    }

    public Bullet(double x, double y, double vx, double vy) {
        this(new Vector2D(x,y), new Vector2D(vx,vy));
    }

    @Override
    public void update() {
        super.update();
        ttl--;
        if (ttl<0) dead = true;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.red);
        g.fillOval((int) (position.x-RADIUS), (int) (position.y-RADIUS), RADIUS * 2, RADIUS * 2);
    }

}
