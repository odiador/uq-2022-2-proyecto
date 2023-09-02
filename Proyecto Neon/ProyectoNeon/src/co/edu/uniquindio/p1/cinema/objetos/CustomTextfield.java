package co.edu.uniquindio.p1.cinema.objetos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;

import co.edu.uniquindio.p1.cinema.services.Herramientas;

import javax.swing.JPanel;
import javax.swing.JSeparator;

public class CustomTextfield extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFormattedTextField tf;
	private JSeparator separador;
	private Color color = Herramientas.white;

	public CustomTextfield(AbstractFormatter numberFormatter) {
		setLayout(new BorderLayout());
		tf = new JFormattedTextField(numberFormatter);
		separador = new JSeparator();
		getTf().setBorder(null);
		getTf().setForeground(Herramientas.white);
		getTf().setBackground(Herramientas.black);
		separador.setForeground(Herramientas.black);
		separador.setBackground(Herramientas.white);

		add(getTf());
		add(separador, BorderLayout.SOUTH);
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
			separador.setForeground(color);
			return;
		}
		separador.setForeground(getTf().getDisabledTextColor());

	}

	public void setDisabledTextColor(Color c) {
		getTf().setDisabledTextColor(c);
	}

	public void setFont(Font f) {
		if (tf == null)
			super.setFont(f);
		else
			getTf().setFont(f);
	}

	public void setForeground(Color c) {
		if (tf == null) {
			super.setForeground(c);
			return;
		}
		getTf().setForeground(c);
		separador.setForeground(c);
	}

	public void setText(String text) {
		getTf().setText(text);
	}

	public String getText() {
		String total = "";
		String[] split = getTf().getText().replace('.', '-').split("-");
		for (String parcial : split)
			total += parcial;
		return total;
	}

	public void teclaPresionada(KeyEvent e) {
		if (e.getKeyChar() == 8) {
			if (getTf().getText().length() == 1) {
				getTf().setText("0");
			}
		}
	}

	public void actualizarColor(Color c) {
		separador.setBackground(c);
		getTf().setForeground(c);
	}

	/**
	 * @return the tf
	 */
	public JFormattedTextField getTf() {
		return tf;
	}

	/**
	 * @param tf the tf to set
	 */
	public void setTf(JFormattedTextField tf) {
		this.tf = tf;
	}
}
