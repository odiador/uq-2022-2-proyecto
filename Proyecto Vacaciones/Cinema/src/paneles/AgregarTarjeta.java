package paneles;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import custom.ClicListener;
import custom.ComboBox;
import custom.Evento;
import custom.NumberTextField;
import custom.Panel;
import custom.Text;
import objects.CustomException;
import tools.TablaClientes;

public class AgregarTarjeta extends Panel implements ClicListener {
	private Text lblCedu, lblTipo;
	private Text bAceptar;
	private NumberTextField tfCedu;
	private ComboBox comboTipo;

	public AgregarTarjeta() {
		setLblCedu(new Text("Escribe una cédula:"));
		setLblTipo(new Text("Elige un tipo de Tarjeta:"));
		setTfCedu(new NumberTextField(9999999999d, "Escribe una cédula"));
		setBotonAceptar(new Text("Aceptar"));
		setComboTipo(new ComboBox(new ArrayList<String>(Arrays.asList("Basic", "Gold"))));

		getLblCedu().setVerticalAlignment(SwingConstants.BOTTOM);
		getLblCedu().setHorizontalAlignment(SwingConstants.LEFT);
		getLblTipo().setVerticalAlignment(SwingConstants.BOTTOM);
		getLblTipo().setHorizontalAlignment(SwingConstants.LEFT);

		getBotonAceptar().setOpaque(true);
		getBotonAceptar().setCanBeSelectioned(true);
		getBotonAceptar().addClicListener(this);
		getBotonAceptar().setBackground(Text.brighter);

		getComboTipo().setOpaque(true);
		getComboTipo().setCanBeSelectioned(true);

		getTfCedu().setValue(0);

		getTfCedu().setPreferredSize(400, 40);
		getLblTipo().setPreferredSize(200, 40);
		getLblCedu().setPreferredSize(400, 40);
		getBotonAceptar().setPreferredSize(200, 40);
		getComboTipo().setPreferredSize(200, 40);

		Panel principal = new Panel();
		principal.setLayout(new GridBagLayout());
		setLayout(new BorderLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 0, 10, 0);
		principal.add(getLblCedu(), gbc);
		gbc.gridy = 1;
		principal.add(getTfCedu(), gbc);
		gbc.gridy = 2;
		principal.add(getLblTipo(), gbc);
		gbc.gridy = 3;
		principal.add(getComboTipo(), gbc);

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
			try {
				String tipo = getComboTipo().getText();
				if (tipo.equals("Basic")) TablaClientes.addBasic(getTfCedu().getNumber());
				if (tipo.equals("Gold")) TablaClientes.addGold(getTfCedu().getNumber());

			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, e1, "Error", JOptionPane.ERROR_MESSAGE);
				return;
			} catch (CustomException e1) {
				JOptionPane.showMessageDialog(null, e1.getCausa(), "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			JOptionPane.showMessageDialog(null, "La operación fue realizada con éxito", "Información",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public ComboBox getComboTipo () {
		return comboTipo;
	}

	public void setComboTipo (ComboBox comboTipo) {
		this.comboTipo = comboTipo;
	}

	public Text getLblTipo () {
		return lblTipo;
	}

	public void setLblTipo (Text lblTipo) {
		this.lblTipo = lblTipo;
	}

}
