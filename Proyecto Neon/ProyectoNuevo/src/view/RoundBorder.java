package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.border.AbstractBorder;

public class RoundBorder extends AbstractBorder {

	private static final long serialVersionUID = 1L;

	private Color borderColor;
	private int thickness;
	private int radius;
	private Insets insets;

	public RoundBorder(Color color, int thickness, int radius) {
		this.borderColor = color;
		this.thickness = thickness;
		this.radius = radius;
		this.insets = new Insets(this.thickness + 1, this.thickness + 1, this.thickness + 2, this.thickness);
	}

	public RoundBorder(Color color, int thickness, int radius, Insets insets) {
		this.borderColor = color;
		this.thickness = thickness;
		this.radius = radius;
		this.insets = insets;
	}

	@Override
	public Insets getBorderInsets(Component c) {
		return insets;
	}

	@Override
	public Insets getBorderInsets(Component c, Insets insets) {
		return getBorderInsets(c);
	}

	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		int r = radius;
		int w = width - 1;
		int h = height - 1;
		Area round = new Area(new RoundRectangle2D.Float(x, y, w, h, r, r));
		Rectangle2D rect = new Rectangle2D.Float(x + r, y, w - 2 * r, h);
		round.add(new Area(rect));
		g2.setPaint(borderColor);
		g2.fill(round);
		g2.dispose();
	}

}