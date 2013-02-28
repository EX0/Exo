package de.dariusmewes.exo.entities;

import de.dariusmewes.exo.Exo;
import de.dariusmewes.exo.level.Level;
import de.dariusmewes.exo.level.Level.Direction;

public abstract class Mob extends Entity {

	protected int speed;
	protected int numSteps;
	protected Direction lastDir;

	public Mob(Exo game, Level level, int movingSpeed) {
		super(game, level);
		this.speed = movingSpeed;
	}

	public void move(Direction dir) {
		lastDir = dir;

		switch (dir) {
		case NORTH:
			y -= speed;
			break;
		case EAST:
			x += speed;
			break;
		case SOUTH:
			y += speed;
			break;
		case WEST:
			x -= speed;
			break;
		default:
			break;
		}
	}

	public abstract boolean hasCollided(int xa, int ya);

}