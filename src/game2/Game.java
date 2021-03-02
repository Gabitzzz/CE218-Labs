package game2;

import utilities.JEasyFrame;

import java.util.ArrayList;
import java.util.List;

import static game2.Constants.DELAY;

public class Game {
    public static final int N_INITIAL_ASTEROIDS = 5;
    public List<GameObject> objects;
    Ship ship;
    Keys ctrl;

    public Game() {
        objects = new ArrayList<GameObject>();
        for (int i = 0; i < N_INITIAL_ASTEROIDS; i++) {
            objects.add(Asteroid.makeRandomAsteroid());

        }
        ctrl = new Keys();
        ship = new Ship(ctrl);
        objects.add(ship);
    }
    public static void main(String[] args) {
        Game game = new Game();
        View view = new View(game);
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
        List<GameObject> alive = new ArrayList<>();
        for (GameObject o : objects) {
            o.update();
            if (!o.dead) alive.add(o);
            if (ship.bullet != null) {
                alive.add(ship.bullet);
                ship.bullet = null;
            }
        }
        objects.clear();
        for (GameObject o:alive) objects.add(o);
    }



}

