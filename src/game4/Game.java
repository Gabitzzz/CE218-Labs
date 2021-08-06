package game4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import utilities.JEasyFrame;
import utilities.Lib;

public class Game {
	public static final int N_INITIAL_ASTEROIDS = 4;

	public List<GameObject> objects;
	public View view;
	public Ship ship;

	public Game() {
		objects = new LinkedList<GameObject>();
		for (int i = 0; i < N_INITIAL_ASTEROIDS; i++) {
			objects.add(new Asteroid());
		}
		Keys keys = new Keys();
		ship = new Ship(keys);
		objects.add(ship);
		view = new View(objects);
		JEasyFrame jf = new JEasyFrame(view, "Asteroid Game - Lab 4");
		jf.setResizable(false);
		jf.addKeyListener(keys);
	}

	public static void main(String[] args) {
		gameLoop();
	}

	public static void gameLoop() {
		Game game = new Game();
		final long DT_MS = Math.round(1000 / Constants.FPS);
		System.out.println("DT_MS=" + DT_MS);
		while (true) {
			long time0 = System.currentTimeMillis();
			game.update();
			game.view.repaint();
			long timeToSleep = DT_MS + time0 - System.currentTimeMillis();
			if (timeToSleep < 0)
				System.out.println("Warning: timeToSleep negative");
			else
				Lib.sleep(timeToSleep);
		}
	}

	private void update() {
		for (GameObject object : objects) {
			for (GameObject other : objects) {
				if (object != other)
					object.collisionHandling(other);
			}
		}
		List<GameObject> alive = new ArrayList<GameObject>();
		for (GameObject object : objects) {
			if (!object.dead) {
				object.update();
				alive.add(object);
			}
		}
		if (ship.bullet != null) {
			alive.add(ship.bullet);
			ship.bullet = null;
		}
		synchronized (Game.class) {
			objects.clear();
			objects.addAll(alive);
		}
	}

}
