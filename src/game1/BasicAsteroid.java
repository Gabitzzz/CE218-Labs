package game1;

import utilities.Vector2D;

import static game1.Constants.DT;
import static game1.Constants.FRAME_HEIGHT;
import static game1.Constants.FRAME_WIDTH;
import java.awt.Color;
import java.awt.Graphics2D;

public class BasicAsteroid {
    public static final int RADIUS = 10;
    public static final double MAX_SPEED = 100;

    private Vector2D pos;
    private Vector2D vel;

    public BasicAsteroid(double x, double y, double vx, double vy) {
        this.pos = new Vector2D(x,y);
        this.vel = new Vector2D(vx, vy);
    }

    public void update(){
        pos.addScaled(vel, DT);
        pos.wrap(FRAME_WIDTH, FRAME_HEIGHT);
    }

    public void draw(Graphics2D g){
        g.setColor(Color.RED);
        g.fillOval((int) pos.x-RADIUS, (int) pos.y-RADIUS, 2*RADIUS, 2*RADIUS);
    }

    public static BasicAsteroid makeRandomAsteroid(){
        double x = Math.random() * FRAME_WIDTH;
        double y = Math.random() + FRAME_HEIGHT;
        double vx = Math.random() * MAX_SPEED;
        if (Math.random()<0.5) vx *= -1;
        double vy = Math.random() * MAX_SPEED;
        if (Math.random()<0.5) vy *= -1;
        // there are better ways to generate numbers in the range
        //      -MAX_SPEED .. MAX_SPEED
        return new BasicAsteroid(x,y,vx,vy);

    }
}
