package game2;

import utilities.Vector2D;

import java.awt.*;


import static game2.Constants.DT;

public class Bullet extends GameObject {
    private double lifetime;
    public static final int RADIUS = 2;
    public static final int BULLET_LIFE = 2; // second

    public Bullet(Vector2D pos, Vector2D vel) {
        super(pos, vel, 0);
        this.lifetime = BULLET_LIFE;
    }

    @Override
    public void update() {
        super.update();
        lifetime -= DT;
        if (lifetime <= 0) dead = true;
    }

    @Override
    public void draw(Graphics2D g)
    {g.setColor(Color.WHITE);
        g.fillOval((int) pos.x-RADIUS, (int) pos.y-RADIUS, 2*RADIUS, 2*RADIUS);


    }
}
