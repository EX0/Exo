package de.dariusmewes.exo.entities;

import de.dariusmewes.exo.Exo;
import de.dariusmewes.exo.gfx.Screen;
import de.dariusmewes.exo.level.Level;

public abstract class Entity {

	protected Exo game;
	protected Level level;
	public int x,y;

	public Entity(Exo game, Level level) {
		this.game = game;
		this.level = level;
	}

	public abstract void tick();

	public abstract void render(Screen screen);

}