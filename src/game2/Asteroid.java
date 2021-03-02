package game2;

import utilities.Vector2D;

import static game2.Constants.DT;
import static game2.Constants.FRAME_HEIGHT;
import static game2.Constants.FRAME_WIDTH;
import java.awt.Color;
import java.awt.Graphics2D;

public class Asteroid extends GameObject{
    public static final int RADIUS = 10;
    public static final double MAX_SPEED = 100;

    public Asteroid(double x, double y, double vx, double vy) {
        super(new Vector2D(x,y),
                new Vector2D(vx, vy), 0);
    }

    public void draw(Graphics2D g){
        g.setColor(Color.RED);
        g.fillOval((int) pos.x-RADIUS, (int) pos.y-RADIUS, 2*RADIUS, 2*RADIUS);
    }

    public static Asteroid makeRandomAsteroid(){
        double x = Math.random() * FRAME_WIDTH;
        double y = Math.random() + FRAME_HEIGHT;
        double vx = Math.random() * MAX_SPEED;
        if (Math.random()<0.5) vx *= -1;
        double vy = Math.random() * MAX_SPEED;
        if (Math.random()<0.5) vy *= -1;
        // there are better ways to generate numbers in the range
        //      -MAX_SPEED .. MAX_SPEED
        return new Asteroid(x,y,vx,vy);

    }
}
