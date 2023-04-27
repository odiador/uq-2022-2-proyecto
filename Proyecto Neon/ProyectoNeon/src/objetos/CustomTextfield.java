package objetos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JSeparator;

import model.Herramientas;

public class CustomTextfield extends JComponent {
	private JFormattedTextField tf;
	private JSeparator separador;
	private Color color = Herramientas.white;

	public CustomTextfield(AbstractFormatter numberFormatter) {
		setLayout(null);
		setTf(new JFormattedTextField(numberFormatter));
		setSeparador(new JSeparator());
		getTf().setBorder(null);
		getTf().setForeground(Herramientas.white);
		getTf().setBackground(Herramientas.black);
		getSeparador().setForeground(Herramientas.white);
		getSeparador().setBackground(Herramientas.black);

		add(getTf());
		add(getSeparador());
	}
	public void addKeyListener(KeyListener l) {
		getTf().addKeyListener(l);
	}


	public void setEditable(boolean b) {
		getTf().setEditable(b);
	}

	public void setEnabled(boolean b) {
		getTf().setEnabled(b);
		if (b) {
			getSeparador().setForeground(color);
		} else {
			getSeparador().setForeground(getTf().getDisabledTextColor());
		}
	}

	public void setDisabledTextColor(Color c) {
		getTf().setDisabledTextColor(c);

	}

	public void setFont(Font f) {
		getTf().setFont(f);
	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		getTf().setBounds(0, 0, width, height - 1);
		getSeparador().setBounds(0, height - 1, width, 1);
	}

	public void setForeground(Color c) {
		getTf().setForeground(c);
		getSeparador().setForeground(c);
	}

	public void setBounds(Rectangle b) {
		super.setBounds(b);
		getTf().setBounds(0, 0, b.width, b.height - 1);
		getSeparador().setBounds(0, b.height - 1, b.width, 1);
	}

	public void setText(String text) {
		getTf().setText(text);
	}

	public String getText() {
		String total = "";
		for (String parcial : getTf().getText().replace('.', '-').split("-"))
			total += parcial;
		return total;
	}

	public JSeparator getSeparador() {
		return separador;
	}

	public void setSeparador(JSeparator separador) {
		this.separador = separador;
	}

	public JFormattedTextField getTf() {
		return tf;
	}

	public void setTf(JFormattedTextField tf) {
		this.tf = tf;
	}

	public void teclaPresionada(KeyEvent e) {
		if (e.getKeyChar() == 8) {
			if (getTf().getText().length() == 1) {
				getTf().setText("0");
			}
		}
	}

	public void actualizarColor(Color c) {
		getSeparador().setForeground(c);
		getTf().setForeground(c);
	}
}
