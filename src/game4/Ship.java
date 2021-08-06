package game4;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import utilities.Vector2D;

public class Ship extends GameObject {
    static final double STEER_RATE = 2*Math.PI;
    static final double DRAG = 0.01;
    static final double MAG_ACC = 200;
    static final Color COLOR = Color.cyan;
    static final int[] XP = { -2, 0, 2, 0 };
    static final int[] YP = { 2, -2, 2, 0 };
    static final int[] XPTHRUST = { -2, 0, 2, 0 };
    static final int[] YPTHRUST = { 2, 3, 2, 0 };
    static final double SCALE = 5;

    // direction in which the nose of the ship is pointing
    // this will be the direction in which thrust is applied
    // it is a unit-vector representing the angle by which the ship has rotated
    public Vector2D direction;
    private Controller ctrl;
    public boolean thrusting = false;
    public Bullet bullet = null;

    public Ship(Controller ctrl) {
        super(new Vector2D(), new Vector2D(), 10);
        this.direction = new Vector2D(Constants.UPWARDS);
        this.ctrl = ctrl;
        reset();
    }

    @Override
    public String toString() {
        return "Ship [position=" + position
                + ", velocity=" + velocity + "]";
    }

    public void reset() {
        position.set(Constants.CENTER);
        velocity.set(new Vector2D(0,0));
        direction.set(Constants.UPWARDS);
    }

    @Override
    public void update() {
        super.update();
        Action action = ctrl.action();
        direction.rotate(action.turn * STEER_RATE * Constants.DT);
        velocity.addScaled(direction, action.thrust * MAG_ACC * Constants.DT);
        velocity.mult(1.0 - DRAG);
        thrusting = (ctrl.action().thrust > 0);
        if (action.shoot) {
            mkBullet();
            action.shoot = false;
        }
    }

    private void mkBullet() {
        bullet = new Bullet(new Vector2D(position), new Vector2D(velocity));
        // make it clear the ship
        bullet.position.addScaled(direction, (radius + bullet.radius) * 1.1);
        bullet.velocity.addScaled(direction, Bullet.INITIAL_SPEED);
    }

    @Override
    public void draw(Graphics2D g) {
        AffineTransform at = g.getTransform();
        g.translate(position.x, position.y);
        double rot = direction.angle() + Math.PI / 2;
        g.rotate(rot);
        g.scale(SCALE, SCALE);
        g.setColor(COLOR);
        g.fillPolygon(XP, YP, XP.length);
        if (thrusting) {
            g.setColor(Color.red);
            g.fillPolygon(XPTHRUST, YPTHRUST, XPTHRUST.length);
        }
        g.setTransform(at);
    }
}
