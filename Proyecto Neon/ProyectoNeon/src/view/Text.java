package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.Icon;
import javax.swing.JLabel;

public class Text extends Componente {

	private JLabel label;
	private String text;
	private Icon icon;

	public Text() {
		this("", null, 0);
	}

	public Text(String cad) {
		this(cad, null, 0);
	}

	public Text(String cad, Font f) {
		this(cad, null, 0);
		getLabel().setFont(f);
	}

	public Text(Icon image) {
		this(null, image);
	}

	public Text(String cad, int horizontalAlligment) {
		this(cad, null, horizontalAlligment);
	}

	public Text(String cad, Icon image) {
		this(cad, image, 0);
	}

	public Text(String cad, Icon image, int horizontalAlligment) {
		setLabel(new JLabel(cad, image, horizontalAlligment));
		setIcon(image);
		setText(cad);
		setLayout(new BorderLayout());
		getLabel().setFont(Constantes.defaultFont);
		getLabel().setVerticalAlignment(0);
		setForeground(Constantes.defaultTextColor);
		add(getLabel(), BorderLayout.CENTER);
	}

	public void setVerticalAlignment(int alignment) {
		getLabel().setVerticalAlignment(alignment);
	}

	public void setHorizontalAlignment(int alignment) {
		getLabel().setHorizontalAlignment(alignment);
	}

	public void setText(String text) {
		this.text = text;
		getLabel().setText(text);
	}

	public void addText(String text) {
		setText(getText() + text);
	}

	public String getText() {
		return text;
	}

	public void setForeground(Color fg) {
		if (getLabel() != null) {
			getLabel().setForeground(fg);
			return;
		}
		super.setForeground(fg);
	}

	public void setFont(Font f) {
		if (getLabel() != null) {
			getLabel().setFont(f);
			return;
		}
		super.setFont(f);
	}

	public JLabel getLabel() {
		return label;
	}

	public void setLabel(JLabel label) {
		this.label = label;
	}

	public Icon getIcon() {
		return icon;
	}

	public void setIcon(Icon icon) {
		this.icon = icon;
	}
}
