package confiteria;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import custom.NumberTextField;
import custom.Panel;
import custom.Text;
import custom.TextfieldListener;
import objects.Confiteria;

public class SeleccionCantidadConfi extends Panel {

	private Text lblCantidad;
	private NumberTextField tfCantidad;
	private Confiteria c;

	public SeleccionCantidadConfi(Confiteria c) {
		setConfiteria(c);
		setLblCantidad(
				new Text("<html><center>Escribe una cantidad de " + getConfiteria().getProducto() + "</center></html>"));
		setTfCantidad(new NumberTextField(9999));

		getLblCantidad().setPreferredSize(300, 50);
		getTfCantidad().setPreferredSize(300, 40);

		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(getLblCantidad(), gbc);
		gbc.weightx = 0;
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridy = 1;
		add(getTfCantidad(), gbc);
	}

	public void addTextFieldListener (TextfieldListener l) {
		getTfCantidad().addTextfieldListener(l);
	}

	public Confiteria getConfiteria () {
		return c;
	}

	public void setConfiteria (Confiteria c) {
		this.c = c;
	}

	public NumberTextField getTfCantidad () {
		return tfCantidad;
	}

	public void setTfCantidad (NumberTextField tfCantidad) {
		this.tfCantidad = tfCantidad;
	}

	public Text getLblCantidad () {
		return lblCantidad;
	}

	public void setLblCantidad (Text lblCantidad) {
		this.lblCantidad = lblCantidad;
	}

	public int getNumber () {
		return ((Long) getTfCantidad().getNumber()).intValue();
	}
}
