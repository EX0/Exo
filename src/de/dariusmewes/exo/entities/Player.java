package de.dariusmewes.exo.entities;

import de.dariusmewes.exo.Exo;
import de.dariusmewes.exo.InputHandler;
import de.dariusmewes.exo.gfx.Screen;
import de.dariusmewes.exo.gfx.Tileset;
import de.dariusmewes.exo.level.Level;
import de.dariusmewes.exo.level.Level.Direction;

public class Player extends Mob {

	private InputHandler input;
	private final static int SPEED = 3;

	public Player(Exo game, InputHandler input, Level level) {
		super(game, level, SPEED);
		this.input = input;
		x = level.width / 2 * Tileset.PIXEL_SIZE;
		y = level.height / 2 * Tileset.PIXEL_SIZE;
		level.spawn(this);
	}

	public void tick() {
		if (input.up.isPressed())
			move(Direction.NORTH);
		if (input.left.isPressed())
			move(Direction.WEST);
		if (input.down.isPressed())
			move(Direction.SOUTH);
		if (input.right.isPressed())
			move(Direction.EAST);
	}

	public void render(Screen screen) {
		screen.render(x - Tileset.PIXEL_SIZE / 2, y - Tileset.PIXEL_SIZE / 2, 4, game.camX, game.camY);
	}

	public boolean hasCollided(int xa, int ya) {
		return false;
	}

}