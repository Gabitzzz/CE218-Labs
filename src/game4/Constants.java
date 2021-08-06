package game4;

import java.awt.Dimension;

import utilities.Vector2D;

public class Constants {

    // frame dimensions
    public static final int FRAME_HEIGHT = 480;
    public static final int FRAME_WIDTH = 640;
    public static final Dimension FRAME_SIZE = new Dimension(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);

    public static final Vector2D CENTER = new Vector2D(Constants.FRAME_WIDTH / 2, Constants.FRAME_HEIGHT / 2);
    public static final Vector2D UPWARDS = new Vector2D(0, -1);

    // constants relating to frame rate
    public static final int FPS = 40;
    public static final double DT = 1.0 / FPS;
}
