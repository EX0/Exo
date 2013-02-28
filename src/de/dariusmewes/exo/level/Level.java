package de.dariusmewes.exo.level;

import java.util.ArrayList;
import java.util.List;

import de.dariusmewes.exo.Exo;
import de.dariusmewes.exo.gfx.Screen;
import de.dariusmewes.exo.gfx.Tileset;
import de.dariusmewes.exo.entities.Entity;

public class Level {

	private Exo game;
	public List<Entity> entities = new ArrayList<Entity>();
	public int width;
	public int height;
	private byte[] world;

	public Level(Exo game, int width, int height) {
		this.game = game;
		this.width = width;
		this.height = height;
		this.world = new byte[width * height];
		generate();
	}

	public void generate() {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				int index = x + y * width;
				if (y < height / 2)
					world[index] = Tile.AIR.getID();
				else
					world[index] = Tile.DUST.getID();
			}
		}
	}

	public void spawn(Entity entity) {
		this.entities.add(entity);
	}

	public void tick() {
		for (Entity ent : entities)
			ent.tick();
	}

	public void render(Screen screen) {
		// map
		if (game.camX < 0)
			game.camX = 0;
		if (game.camX > (width * Tileset.PIXEL_SIZE - screen.width))
			game.camX = (width * Tileset.PIXEL_SIZE - screen.width);
		if (game.camY < 0)
			game.camY = 0;
		if (game.camY > (height * Tileset.PIXEL_SIZE - screen.height))
			game.camY = (height * Tileset.PIXEL_SIZE - screen.height);

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				screen.render(x * Tileset.PIXEL_SIZE, y * Tileset.PIXEL_SIZE, getTile(x, y).getID(), game.camX, game.camY);
			}
		}

		// entities
		for (Entity ent : entities)
			ent.render(screen);
	}

	public void renderBG(Screen screen) {
		
	}

	public Tile getTile(int x, int y) {
		if (0 > x || x >= width || 0 > y || y >= height)
			return Tile.VOID;

		return Tile.tileTypes[world[x + y * width]];
	}

	public enum Direction {
		NORTH, SOUTH, WEST, EAST;
	}

}