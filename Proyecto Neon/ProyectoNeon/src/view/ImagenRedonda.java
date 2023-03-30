package view;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class ImagenRedonda implements Icon {

	private BufferedImage image;
	private int width;
	private int height;

	public ImagenRedonda(Icon icon, int width, int height, int radius) {
		this.width = width;
		this.height = height;

		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.drawImage(((ImageIcon) icon).getImage(), 0, 0, width, height, null);
		g2.dispose();

		image = makeRoundedCorner(image, radius);
	}

	@Override
	public int getIconWidth () {
		return width;
	}

	@Override
	public int getIconHeight () {
		return height;
	}

	@Override
	public void paintIcon (Component c, Graphics g, int x, int y) {
		g.drawImage(image, x, y, null);
	}

	public static BufferedImage makeRoundedCorner (BufferedImage image, int cornerRadius) {
		int w = image.getWidth();
		int h = image.getHeight();
		BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2 = output.createGraphics();

		g2.setComposite(AlphaComposite.Src);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Color.WHITE);
		g2.fill(new RoundRectangle2D.Float(0, 0, w, h, cornerRadius, cornerRadius));
		g2.setComposite(AlphaComposite.SrcAtop);
		g2.drawImage(image, 0, 0, null);

		g2.dispose();

		return output;
	}

}
