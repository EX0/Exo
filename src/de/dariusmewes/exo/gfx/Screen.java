package de.dariusmewes.exo.gfx;

public class Screen {

	private Tileset tileset;
	public int width;
	public int height;

	public int[] pixels;

	public Screen(int width, int height, Tileset tileset) {
		this.width = width;
		this.height = height;
		this.tileset = tileset;
		this.pixels = new int[width * height];
	}

	public void render(int xPos, int yPos, int tileID) {
		render(xPos, yPos, tileID, 0, 0);
	}

	public void render(int xPos, int yPos, int tileID, int xOff, int yOff) {
		xPos -= xOff;
		yPos -= yOff;

		int xTile = tileID % 4;
		int yTile = tileID / 4;

		for (int x = 0; x < Tileset.PIXEL_SIZE; x++) {
			if (xPos + x < 0 || xPos + x >= width)
				continue;

			for (int y = 0; y < Tileset.PIXEL_SIZE; y++) {
				if (yPos + y < 0 || yPos + y >= height)
					continue;

				int col = tileset.pixels[(xTile * Tileset.PIXEL_SIZE + x) + (yTile * Tileset.PIXEL_SIZE + y) * tileset.width];
				pixels[xPos + x + (yPos + y) * width] = col;
			}
		}
	}

}