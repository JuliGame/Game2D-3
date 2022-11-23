package com.craivet.gfx;

import java.awt.image.BufferedImage;

import com.craivet.utils.Utils;

public class Assets {

	public static SpriteSheet player = new SpriteSheet(Utils.loadImage("textures/player.png"));

	/**
	 * Obtiene las subimagenes del sprite sheet especificado.
	 *
	 * @param ss     el sprite sheet que contiene las imagenes agrupadas.
	 * @param width  el ancho de la subimagen.
	 * @param height el alto de la subimagen.
	 * @return una matriz con las subimagenes del sprite sheet.
	 *
	 * <p>TODO Incluir funcion para anchos y altos de subimagenes diferentes (por ejemplo, si el parametro es true uso switch)
	 */
	public static BufferedImage[] getImages(SpriteSheet ss, int width, int height) {
		int col = ss.getWidth() / width;
		int row = ss.getHeight() / height;
		BufferedImage[] images = new BufferedImage[col * row];
		int i = 0;
		for (int y = 0; y < row; y++)
			for (int x = 0; x < col; x++)
				images[i++] = ss.crop(x * width, y * height, width, height);
		return images;
	}

	private Assets() {
	}

}
