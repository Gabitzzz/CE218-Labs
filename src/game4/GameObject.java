package game4;

import java.awt.Graphics2D;

import utilities.Vector2D;

public abstract class GameObject {
    public Vector2D position, velocity;
    public boolean dead;
    public double radius;

    public GameObject(Vector2D p, Vector2D v, double radius) {
        dead = false;
        this.position = p;
        this.velocity = v;
        this.radius = radius;
    }

    public GameObject(double x, double y, double vx, double vy, double radius) {
        dead = false;
        this.position = new Vector2D(x, y);
        this.velocity = new Vector2D(vx, vy);
        this.radius = radius;
    }

    public void hit() {
        dead = true;
    }

    public void update() {
        position.addScaled(velocity, Constants.DT);
        position.wrap(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
    }

    public boolean overlap(GameObject other) {
        // naive bounding circle overlap
        // Warning: ignores wrap-around world geometry!
        return position.dist(other.position) <= radius + other.radius;
    }

    public void collisionHandling(GameObject other) {
        if (this.getClass() != other.getClass() && this.overlap(other)) {
            this.hit();
            other.hit();
        }
    }

    public abstract void draw(Graphics2D g);

}
