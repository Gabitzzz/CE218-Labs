package game2;

import utilities.Vector2D;

import java.awt.*;
import java.sql.DataTruncation;

import static game2.Constants.*;

public abstract class GameObject {
    public Vector2D pos;
    public Vector2D vel;
    public double radius;
    public boolean dead;


    public GameObject(Vector2D pos, Vector2D vel, double radius
    ) {
        this.pos = pos;
        this.vel = vel;
        this.radius = radius;
        this.dead = false;
    }

    public void update() {
        pos.addScaled(vel, DT);
        pos.wrap(FRAME_WIDTH, FRAME_HEIGHT);}

    public abstract void draw(Graphics2D g);
}
