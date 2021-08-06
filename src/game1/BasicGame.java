package game1;

import utils.JEasyFrame;


import java.util.ArrayList;
import java.util.List;

import static game1.Constants.DELAY;

public class BasicGame {
    public static final int N_INITIAL_ASTEROIDS = 5;
    public List<BasicAsteroid> asteroids;
    BasicShip ship;
    BasicKeys ctrl;

    public BasicGame() {
        asteroids = new ArrayList<BasicAsteroid>();
        for (int i = 0; i < N_INITIAL_ASTEROIDS; i++) {
            asteroids.add(BasicAsteroid.makeRandomAsteroid());

        }
        ctrl = new BasicKeys();
        ship = new BasicShip(ctrl);
    }
    public static void main(String[] args) {
        BasicGame game = new BasicGame();
        BasicView view = new BasicView(game);
        new JEasyFrame(view, "BasicGame").addKeyListener(game.ctrl);
        while (true)
        {
            game.update();
            view.repaint();
            try { Thread.sleep(DELAY); }
            catch (Exception e) {}


        }
    }

    public void update() {
        for (BasicAsteroid a : asteroids) {
            a.update();
        }
        ship.update();

    }
}

