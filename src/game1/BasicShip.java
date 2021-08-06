package game1;

import utilities.Vector2D;

import java.awt.*;

import static game1.Constants.*;

public class BasicShip {
    public static final int RADIUS = 8;

    public static final double STEER_RATE = 2 * Math.PI;

    public static final double MAG_ACC = 200;

    public static final double DRAG = 0.01;

    public static final Color COLOR = Color.cyan;

    public Vector2D pos, vel;
    public Vector2D dir;

    private BasicController ctrl;

    public BasicShip(BasicController ctrl){
        this.ctrl = ctrl;
        pos = new Vector2D(FRAME_WIDTH/2, FRAME_HEIGHT/2);
                vel = new Vector2D();
                        dir = new Vector2D(0,-1);
    }

    public void update() {
        Action action = ctrl.action();
        dir.rotate(action.turn*STEER_RATE*DT);
        vel = new Vector2D(dir).mult(vel.mag());
        vel.addScaled(dir, MAG_ACC*DT*action.thrust);
        vel.addScaled(vel, -DRAG);
        pos.addScaled(vel, DT);
        pos.wrap(FRAME_WIDTH, FRAME_HEIGHT);
    }

    public void draw(Graphics2D g) {
        g.setColor(COLOR);
        g.fillOval((int)(pos.x-RADIUS), (int)(pos.y-RADIUS), 2*RADIUS, 2*RADIUS);
        Vector2D endLine = new Vector2D(pos);
        endLine.addScaled(dir, 3*RADIUS);
        g.drawLine((int)pos.x, (int)pos.y, (int)endLine.x, (int)endLine.y);
    }

}
