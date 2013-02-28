package de.dariusmewes.exo.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tileset {

	public static final int PIXEL_SIZE = 64;
	public int width;
	public int height;
	public int[] pixels;

	public Tileset(String path) {
		BufferedImage image = null;

		try {
			image = ImageIO.read(this.getClass().getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (image == null)
			return;

		this.width = image.getWidth();
		this.height = image.getHeight();

		pixels = image.getRGB(0, 0, width, height, null, 0, width);
	}

}
