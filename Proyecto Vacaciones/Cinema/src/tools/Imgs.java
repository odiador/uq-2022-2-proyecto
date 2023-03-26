package tools;

import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import custom.ImagenRedonda;

public class Imgs {

	public static final int SCALE_FAST = Image.SCALE_FAST;
	public static final int SCALE_AREA_AVERAGING = Image.SCALE_AREA_AVERAGING;
	public static final int SCALE_SMOOTH = Image.SCALE_SMOOTH;
	public static final int SCALE_DEFAULT = Image.SCALE_DEFAULT;
	public static final int SCALE_REPLICATE = Image.SCALE_REPLICATE;

	public static final ImageIcon IMAGEN_DEFAULT = new ImageIcon("images/Stellar Cinema.png");

	public static ImageIcon escalarImagen (ImageIcon imagen, int width, int height, int tipoDeEscala) {
		return new ImageIcon(imagen.getImage().getScaledInstance(width, height, tipoDeEscala));

	}

	public static ImageIcon getImage (String cad) {
		ImageIcon img = new ImageIcon("images/" + cad + ".png");
		if (img.getImageLoadStatus() != 8) {
			img = IMAGEN_DEFAULT;
		}
		return img;
	}

	public static Icon generarImagenRedonda (Icon img, int width, int height, int radius) {
		return new ImagenRedonda(img, width, height, radius);
	}
}
