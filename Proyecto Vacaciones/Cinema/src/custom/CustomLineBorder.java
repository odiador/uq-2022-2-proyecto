package custom;

import javax.swing.border.AbstractBorder;
import java.awt.*;

public class CustomLineBorder extends AbstractBorder {

	private Color color;
	private int thickness;
	private int tipo;
	public static final int NINGUNO = -1;
	public static final int NORTE = 0;
	public static final int ESTE = 1;
	public static final int OESTE = 2;
	public static final int SUR = 3;
	public static final int SURESTE = 4;
	public static final int NORESTE = 5;
	public static final int NOROESTE = 6;
	public static final int SUROESTE = 7;
	public static final int NORTE_Y_SUR = 8;
	public static final int ESTE_Y_OESTE = 9;
	public static final int NORTE_ESTE_Y_OESTE = 10;
	public static final int SUR_ESTE_Y_OESTE = 11;
	public static final int NORTE_SUR_Y_ESTE = 12;
	public static final int NORTE_SUR_Y_OESTE = 13;
	public static final int TODOS = 14;

	public CustomLineBorder(Color color, int thickness, int tipo) {
		this.color = color;
		this.thickness = thickness;
		this.tipo = tipo;
	}

	public void paintBorder (Component c, Graphics g, int x, int y, int width, int height) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setColor(color);
		g2d.setStroke(new BasicStroke(thickness));
		boolean tieneOeste = tipo == OESTE || tipo == NOROESTE || tipo == SUROESTE || tipo == ESTE_Y_OESTE
				|| tipo == NORTE_ESTE_Y_OESTE || tipo == NORTE_SUR_Y_OESTE || tipo == SUR_ESTE_Y_OESTE || tipo == TODOS;
		boolean tieneEste = tipo == ESTE || tipo == NORESTE || tipo == SURESTE || tipo == ESTE_Y_OESTE
				|| tipo == NORTE_ESTE_Y_OESTE || tipo == NORTE_SUR_Y_ESTE || tipo == SUR_ESTE_Y_OESTE || tipo == TODOS;

		boolean tieneNorte = tipo == NORTE || tipo == NORESTE || tipo == NOROESTE || tipo == NORTE_Y_SUR
				|| tipo == NORTE_ESTE_Y_OESTE || tipo == NORTE_SUR_Y_ESTE || tipo == NORTE_SUR_Y_OESTE || tipo == TODOS;
		boolean tieneSur = tipo == SUR || tipo == SURESTE || tipo == SUROESTE || tipo == NORTE_Y_SUR
				|| tipo == SUR_ESTE_Y_OESTE || tipo == NORTE_SUR_Y_ESTE || tipo == NORTE_SUR_Y_OESTE || tipo == TODOS;

		if (tieneOeste) g2d.drawLine(x, y, x, y + height - 1);

		if (tieneEste) g2d.drawLine(x + width - 1, y, x + width - 1, y + height - 1);

		if (tieneNorte) g2d.drawLine(x, y, x + width - 1, y);

		if (tieneSur) g2d.drawLine(x, y + height - 1, x + width - 1, y + height - 1);

		g2d.dispose();
	}

	public Insets getBorderInsets (Component c) {
		return new Insets(0, 0, 0, thickness);
	}

	public Insets getBorderInsets (Component c, Insets insets) {
		insets.left = thickness;
		insets.top = insets.right = insets.bottom = 0;
		return insets;
	}

}
