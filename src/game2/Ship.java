package game2;

import utilities.Vector2D;

import java.awt.*;
import java.awt.geom.AffineTransform;

import static game2.Constants.*;

public class Ship extends GameObject {
    public static final int RADIUS = 8;

    public static final double STEER_RATE = 2 * Math.PI;

    public static final double MAG_ACC = 200;

    public static final double DRAG = 0.01;

    public static final double DRAWING_SCALE = 1.5;

    public static final int MUZZLE_VELOCITY = 100;

    public static final Color COLOR = Color.cyan;

    public Vector2D dir;

    public boolean thrusting;

    public Bullet bullet;


    public int XP[] = { -6, 0, 6, 0 }, YP[] = { 8, 4, 8, -8 };
    public int XPTHRUST[] = { -5, 0, 5, 0 }, YPTHRUST[] = { 7, 3, 7, -7 };

    private Controller ctrl;

    public Ship(Controller ctrl) {
        super(new Vector2D(FRAME_WIDTH / 2, FRAME_HEIGHT / 2), new Vector2D(0, -1), 0);
        this.ctrl = ctrl;
        dir = new Vector2D(0,-1);
        thrusting = false;
        bullet = null;
    }

    private void mkBullet(){
        Vector2D bulletPos = new Vector2D(pos);
        bulletPos.addScaled(dir, radius);
        Vector2D bulletVel = new Vector2D(vel);
        bulletVel.addScaled(dir, MUZZLE_VELOCITY);
        bullet = new Bullet(bulletPos, bulletVel);

    }

    @Override
    public void update() {
        Action action = ctrl.action();
        if (action.shoot) {
            mkBullet();
            System.out.println("made bullet");
            action.shoot = false;
        }
        thrusting = action.thrust != 0;
        dir.rotate(action.turn * STEER_RATE * DT);
        vel = new Vector2D(dir).mult(vel.mag());
        vel.addScaled(dir, MAG_ACC * DT * action.thrust);
        vel.addScaled(vel, -DRAG);
        super.update();
    }

    @Override
    public void draw(Graphics2D g) {
        AffineTransform at = g.getTransform();
        g.translate(pos.x, pos.y);
        double rot = dir.angle() + Math.PI / 2;
        g.rotate(rot);
        g.scale(DRAWING_SCALE, DRAWING_SCALE);
        g.setColor(COLOR);
        g.fillPolygon(XP, YP, XP.length);
        if (thrusting) {
            g.setColor(Color.red);
            g.fillPolygon(XPTHRUST, YPTHRUST, XPTHRUST.length);
        }
        g.setTransform(at);
    }

}
