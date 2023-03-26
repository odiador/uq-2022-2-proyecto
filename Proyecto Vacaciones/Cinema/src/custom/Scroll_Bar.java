package custom;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.plaf.basic.BasicScrollBarUI;

import config.Constantes;

public class Scroll_Bar extends BasicScrollBarUI {

	@Override
	protected JButton createDecreaseButton (int orientation) {
		return ninguno();
	}

	@Override
	protected JButton createIncreaseButton (int orientation) {
		return ninguno();
	}

	private JButton ninguno () {
		return new JButton() {
			@Override
			public Dimension getPreferredSize () {
				return new Dimension();
			}
		};
	}

	@Override
	protected void paintTrack (Graphics g, JComponent c, Rectangle trackBounds) {
		g.setColor(Constantes.defaultBgColor);
		g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
	}

	protected void paintThumb (Graphics g, JComponent c, Rectangle r) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Color color = null;
		JScrollBar sb = (JScrollBar) c;
		if (!sb.isEnabled() || r.width > r.height) {
			return;
		} else if (isDragging) {
			color = Constantes.defaultBgColor.brighter().brighter().brighter();
		} else if (isThumbRollover()) {
			color = Constantes.defaultBgColor.brighter().brighter();
		} else {
			color = Constantes.defaultBgColor.brighter();
		}
		g2.setPaint(color);

		RoundRectangle2D superior = new RoundRectangle2D.Float(r.x + 1, r.y + 1, r.width - 2, r.width - 2, r.width,
				r.width);
		RoundRectangle2D inferior = new RoundRectangle2D.Float(r.x + 1, r.y + 1 + r.height - r.width, r.width - 2,
				r.width - 2, r.width, r.width);
		Rectangle2D rectangulo = new Rectangle2D.Float(r.x + 1, r.y + 1 + r.width / 2, r.width - 2, r.height - r.width);

		g2.fill(superior);
		g2.fill(rectangulo);
		g2.fill(inferior);
		g2.dispose();
	}

	@Override
	protected void paintDecreaseHighlight (Graphics g) {}

	@Override
	protected void paintIncreaseHighlight (Graphics g) {}

	protected void setThumbBounds (int x, int y, int width, int height) {
		super.setThumbBounds(x, y, width, height);
		scrollbar.repaint();
	}
}
