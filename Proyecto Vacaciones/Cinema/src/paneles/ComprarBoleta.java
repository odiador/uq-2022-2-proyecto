package paneles;

import static config.Utilities.getIdCine;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import cine.VentanaSala;
import custom.ClicListener;
import custom.Evento;
import custom.NumberTextField;
import custom.Panel;
import custom.Text;
import objects.Cinema;
import objects.Cliente;
import objects.CustomException;
import tools.TablaCinema;
import tools.TablaClientes;

public class ComprarBoleta extends Panel implements ClicListener {
	private Text lblCedu, lblTipo;
	private Text bAceptar;
	private NumberTextField tfCedu, tfCantidad;
	private Text lblSillasDisponibles, lblAsientos, lblName;

	public ComprarBoleta() {
		Cinema c = Cinema.ninguno();
		try {
			c = TablaCinema.getCinemaById(getIdCine());
		} catch (SQLException | CustomException e) {}
		setLblName(new Text("Cine: "));
		setLblCedu(new Text("Escribe una cédula:"));
		setLblTipo(new Text("Escribe una cantidad de sillas:"));
		setTfCedu(new NumberTextField(9999999999d, "Escribe una cédula"));
		setBotonAceptar(new Text("Aceptar"));
		setLblSillasDisponibles(new Text("Sillas disponibles: "));
		setLblAsientos(new Text("Cantidad de asientos: "));

		if (c.getExiste()) {
			getLblName().addText(c.getName());
			getLblSillasDisponibles().addText(c.getCantLibres() + "");
			getLblAsientos().addText(c.getCantAsientos() + "");
			setTfCantidad(new NumberTextField(c.getCantAsientos(), "Escribe una cantidad"));
		} else {
			getLblName().addText("Offline");
			getLblSillasDisponibles().addText("208");
			getLblAsientos().addText("208");
			setTfCantidad(new NumberTextField(208, "Escribe una cantidad"));
		}

		getLblCedu().setVerticalAlignment(SwingConstants.BOTTOM);
		getLblCedu().setHorizontalAlignment(SwingConstants.LEFT);
		getLblTipo().setVerticalAlignment(SwingConstants.BOTTOM);
		getLblTipo().setHorizontalAlignment(SwingConstants.LEFT);

		getBotonAceptar().configAsButton(this);

		getTfCedu().setValue(0);
		getTfCantidad().setValue(0);

		getTfCedu().setPreferredSize(400, 40);
		getLblTipo().setPreferredSize(200, 40);
		getLblName().setPreferredSize(400, 40);
		getLblCedu().setPreferredSize(400, 40);
		getBotonAceptar().setPreferredSize(200, 40);
		getTfCantidad().setPreferredSize(200, 40);
		getLblSillasDisponibles().setPreferredSize(400, 40);
		getLblAsientos().setPreferredSize(400, 40);

		Panel principal = new Panel();
		principal.setLayout(new GridBagLayout());
		setLayout(new BorderLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 0, 10, 0);
		principal.add(getLblName(), gbc);
		gbc.gridy = 1;
		principal.add(getLblCedu(), gbc);
		gbc.gridy = 2;
		principal.add(getTfCedu(), gbc);
		gbc.gridy = 3;
		principal.add(getLblTipo(), gbc);
		gbc.gridy = 4;
		principal.add(getTfCantidad(), gbc);
		gbc.gridy = 5;
		principal.add(getLblSillasDisponibles(), gbc);
		gbc.gridy = 6;
		principal.add(getLblAsientos(), gbc);

		add(principal, BorderLayout.CENTER);
		add(getBotonAceptar(), BorderLayout.SOUTH);
	}

	public Text getLblCedu () {
		return lblCedu;
	}

	public void setLblCedu (Text labelCedu) {
		this.lblCedu = labelCedu;
	}

	public NumberTextField getTfCedu () {
		return tfCedu;
	}

	public void setTfCedu (NumberTextField tfCedu) {
		this.tfCedu = tfCedu;
	}

	public Text getBotonAceptar () {
		return bAceptar;
	}

	public void setBotonAceptar (Text botonAceptar) {
		this.bAceptar = botonAceptar;
	}

	@Override
	public void botonPresionado (Evento e) {
		if (e.getSource() == getBotonAceptar()) {
			long cc = getTfCedu().getNumber();
			int cantidad = ((Long) getTfCantidad().getNumber()).intValue();
			if (manejoDeErrores(cc, cantidad)) return;
			new VentanaSala(getCliente(), cantidad, this).setVisible(true);
		}
	}

	private Cliente cliente;

	public boolean manejoDeErrores (long cc, int cantidad) {
		boolean res = false;
		try {
			setCliente(TablaClientes.getCliente(cc));
		} catch (SQLException e1) {
			if (e1.getErrorCode() == 0) {
				setCliente(new Cliente(getTfCedu().getText(), getTfCedu().getNumber()));
				JOptionPane.showMessageDialog(null, "Database bypass", "Info", JOptionPane.INFORMATION_MESSAGE);

			} else {
				res = true;
				JOptionPane.showMessageDialog(null, "Error: " + e1, "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		if (!res && cliente == null) {
			res = true;
			JOptionPane.showMessageDialog(null, "Error: El cliente no fue encontrado", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		if (!res && cantidad == 0) {
			res = true;
			JOptionPane.showMessageDialog(null, "Error: La cantidad tiene que ser mayor que 0", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		return res;
	}

	public NumberTextField getTfCantidad () {
		return tfCantidad;
	}

	public void setTfCantidad (NumberTextField comboTipo) {
		this.tfCantidad = comboTipo;
	}

	public Text getLblTipo () {
		return lblTipo;
	}

	public void setLblTipo (Text lblTipo) {
		this.lblTipo = lblTipo;
	}

	public Cliente getCliente () {
		return cliente;
	}

	public void setCliente (Cliente cliente) {
		this.cliente = cliente;
	}

	public Text getLblSillasDisponibles () {
		return lblSillasDisponibles;
	}

	public void setLblSillasDisponibles (Text lblSillasDisponibles) {
		this.lblSillasDisponibles = lblSillasDisponibles;
	}

	public Text getLblAsientos () {
		return lblAsientos;
	}

	public void setLblAsientos (Text lblAsientos) {
		this.lblAsientos = lblAsientos;
	}

	public Text getLblName () {
		return lblName;
	}

	public void setLblName (Text lblName) {
		this.lblName = lblName;
	}

}
