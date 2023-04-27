package view;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import model.Herramientas;

public class PopUp extends ParteSuperior {

	private JPanel aceptar;
	private JLabel label, lAceptar;

	public PopUp(String titulo, String info) {
		setTitulo(titulo);
		setAlwaysOnTop(true);
		setRedimensionable(false);

		label = new JLabel(Herramientas.obtenerLabelCentrado(info, "center"));
		label.setHorizontalAlignment(0);
		label.setFont(crearFuente("fonts/coolvetica rg.otf", (int) ((getSize().width / 10.0))));
		label.setSize(398, 200);
		label.setForeground(Herramientas.white);
		label.setLocation(1, getTitleBounds().y + getTitleBounds().height + 1);
		contentPane.add(label);

		aceptar = new JPanel();
		aceptar.setSize(150, 40);
		aceptar.setLocation(posXCentrada(aceptar.getWidth()), label.getY() + label.getHeight() + 1);
		aceptar.addMouseListener(this);
		aceptar.setBorder(new LineBorder(Herramientas.WHITE));
		aceptar.setBackground(Herramientas.black);
		aceptar.setLayout(new BorderLayout(0, 0));
		contentPane.add(aceptar);

		lAceptar = new JLabel("Aceptar");
		lAceptar.setHorizontalAlignment(0);
		lAceptar.setForeground(Herramientas.WHITE);
		lAceptar.setFont(crearFuente("fonts/coolvetica rg.otf", 20));
		lAceptar.setCursor(Herramientas.HAND_CURSOR);
		aceptar.add(lAceptar, "Center");
	}

	@Override
	public void conFigurarVentana() {
		setSize(400, 300);
		cambiarTitulo(null, crearFuente("fonts/Bubble Bobble.otf", 20));
	}

	@Override
	public void cambiarMaximizado(boolean esGrande) {
	}

	@Override
	public void cambioDeColor() {
		label.setForeground(col);
		contentPane.setBorder(new LineBorder(col));
		aceptar.setBorder(new LineBorder(col));
		aceptar.setBackground(Herramientas.black);
		lAceptar.setForeground(col);
	}

	public void mouseEntered(MouseEvent e) {
		super.mouseEntered(e);
		if (e.getSource() == aceptar) {
			aceptar.setBorder(null);
			aceptar.setBackground(col);
			lAceptar.setForeground(Herramientas.black);
		}
	}

	public void mouseExited(MouseEvent e) {
		super.mouseExited(e);
		if (e.getSource() == aceptar) {
			aceptar.setBorder(new LineBorder(col));
			aceptar.setBackground(Herramientas.black);
			lAceptar.setForeground(col);
		}
	}
}
