package custom;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import tools.Imgs;

public class PanelConImagen extends Panel {

	public ImageIcon image;

	public PanelConImagen(String ruta) {
		image = Imgs.getImage(ruta);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (image != null) {
			g.drawImage(image.getImage(), 0, 0, getWidth(), getHeight(), null);
		}
	}

}
