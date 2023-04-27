package view;

import java.awt.Font;

import javax.swing.Icon;

public class TableText extends Text {
	public TableText() {
		this("", null, CustomLineBorder.NINGUNO, 0);
	}

	public TableText(String text) {
		this(text, null, CustomLineBorder.NINGUNO, 0);
	}

	public TableText(String cad, Font f) {
		this(cad, null, CustomLineBorder.NINGUNO, 0);
	}

	public TableText(String cad, int tipo) {
		this(cad, null, tipo, 0);
	}

	public TableText(String cad, Font f, int tipo) {
		this(cad, null, tipo, 0);
		getLabel().setFont(f);
	}

	public TableText(String cad, int tipo, int horizontalAlligment) {
		this(cad, null, tipo, horizontalAlligment);
	}

	public TableText(String cad, Icon image, int tipo) {
		this(cad, image, tipo, 0);
	}

	public TableText(String cad, Icon image, int tipo, int horizontalAlligment) {
		super(cad, image, horizontalAlligment);
		setOpaque(true);
		setBorder(new CustomLineBorder(Constantes.defaultLineColor, 1, tipo));
		setBackground(defaultColor);
		setPreferredSize(1, 30);
	}
}
