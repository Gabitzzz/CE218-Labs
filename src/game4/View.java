package game4;

//import game4ActiveRender.Constants;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public class View extends JComponent {
    // background colour
    public static final Color BG_COLOR = Color.black;
    private Iterable<GameObject> gameObjects;
    private int iteration = 0;
    private long t0 = System.currentTimeMillis();

    public View(Iterable<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }

    @Override
    public void paintComponent(Graphics g0) {
        // System.out.println("paintComponent");
        Graphics2D g = (Graphics2D) g0;
        // paint the background
        g.setColor(BG_COLOR);
        g.fillRect(0, 0, getWidth(), getHeight());
        synchronized (Game.class) {
            for (GameObject object : gameObjects)
                object.draw(g);
        }
        iteration++;
        if (System.currentTimeMillis() - t0 > 1000) {
            System.out.println("FPS= " + iteration);
            iteration = 0;
            t0 = System.currentTimeMillis();
        }

    }

    @Override
    public Dimension getPreferredSize() {
        return Constants.FRAME_SIZE;
    }
}