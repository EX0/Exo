package de.dariusmewes.exo;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import de.dariusmewes.exo.entities.Player;
import de.dariusmewes.exo.gfx.Screen;
import de.dariusmewes.exo.gfx.Tileset;
import de.dariusmewes.exo.level.Level;

public class Exo extends Canvas implements Runnable {
	public static final long serialVersionUID = 1L;

	public static final String TITLE = "Exo";
	public static final int WIDTH = 350 * 3;
	public static final int HEIGHT = WIDTH / 16 * 9;
	public static final int SCALE = 1;
	public static final Dimension SIZE = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);

	public static boolean running = false;
	public boolean isApplet = false;

	public int camX = 0, camY = 0;

	public JFrame frame;
	private Thread thread;

	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	private Screen screen;
	private InputHandler input;
	private Level level;
	public Player player;

	public void init() {
		screen = new Screen(WIDTH, HEIGHT, new Tileset("/sprite_sheet.png"));
		input = new InputHandler(this);
		level = new Level(this, 64, 64);
		player = new Player(this, input, level);
	}

	public synchronized void start() {
		running = true;
		thread = new Thread(this, TITLE + "_main");
		thread.start();
	}

	public synchronized void stop() {
		running = false;

		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		init();
		long lastTime = System.nanoTime();
		long lastShowed = System.currentTimeMillis();
		long now;
		double nsPerTick = 1000000000.0 / 60.0;
		double unprocessed = 0;
		int ticks = 0;
		int frames = 0;
		boolean render;

		while (running) {
			now = System.nanoTime();
			unprocessed += (now - lastTime) / nsPerTick;
			lastTime = now;
			// change
			render = false;

			while (unprocessed >= 1) {
				tick();
				ticks++;
				unprocessed--;
				render = true;
			}

			if (render) {
				render();
				frames++;
			}

			if (!isApplet && System.currentTimeMillis() - lastShowed > 1000) {
				frame.setTitle("Exo - " + ticks + " t, " + frames + " FPS");
				ticks = 0;
				frames = 0;
				lastShowed = System.currentTimeMillis();
			}
		}
	}

	public void tick() {
		level.tick();
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		camX = player.x - (screen.width / 2);
		camY = player.y - (screen.height / 2);

		// render
		level.render(screen);
		// end render

		for (int i = 0; i < pixels.length; i++)
			pixels[i] = screen.pixels[i];

		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();
	}
}