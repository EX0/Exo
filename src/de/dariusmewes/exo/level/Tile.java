package de.dariusmewes.exo.level;

public class Tile {

	public static Tile[] tileTypes = new Tile[256];
	public static final Tile VOID = new Tile(3, true);
	public static final Tile AIR = new Tile(0, false);
	public static final Tile STONE = new Tile(1, true);
	public static final Tile DUST = new Tile(2, true);

	private int id;
	private boolean solid;

	public Tile(int id, boolean solid) {
		this.id = id;
		this.solid = solid;
		tileTypes[id] = this;
	}

	public byte getID() {
		return (byte) id;
	}

	public boolean isSolid() {
		return solid;
	}

	public void tick() {

	}

}