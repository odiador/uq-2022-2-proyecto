package cine;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.SwingConstants;

import custom.ComboBox;
import custom.Panel;
import custom.Text;
import objects.Cliente;

public class MedioPagoSala extends Panel {

	private ComboBox comboMedioPago;
	private Text lblElige;
	private EventoMatriz evento;
	private Cliente cliente;

	public MedioPagoSala(Cliente cliente, EventoMatriz evento) {
		setEvento(evento);
		setCliente(cliente);
		ArrayList<String> listaCombo = new ArrayList<String>(Arrays.asList("Efectivo", "PSE", "Tarjeta de Crédito"));
		if (cliente.gettBasic().getExisteTarjeta()) listaCombo.add("Tarjeta Basic");
		if (cliente.gettGold().getExisteTarjeta()) listaCombo.add("Tarjeta Gold");

		setComboMedioPago(new ComboBox(listaCombo));

		setLblElige(new Text("Elige un método de pago:"));

		getLblElige().setHorizontalAlignment(SwingConstants.LEFT);

		getComboMedioPago().setCanBeSelectioned(true);

		getComboMedioPago().setOpaque(true);

		Panel contenedorCentral;

		contenedorCentral = new Panel();

		contenedorCentral.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0, 0, 20, 0);
		contenedorCentral.add(getLblElige(), gbc);
		gbc.gridy = 1;
		contenedorCentral.add(getComboMedioPago(), gbc);

		getComboMedioPago().setPreferredSize(300, 40);
		add(contenedorCentral);
	}

	public Cliente getCliente () {
		return cliente;
	}

	public void setCliente (Cliente cliente) {
		this.cliente = cliente;
	}

	public Text getLblElige () {
		return lblElige;
	}

	public void setLblElige (Text lblElige) {
		this.lblElige = lblElige;
	}

	public ComboBox getComboMedioPago () {
		return comboMedioPago;
	}

	public void setComboMedioPago (ComboBox comboMedioPago) {
		this.comboMedioPago = comboMedioPago;
	}

	public EventoMatriz getEvento () {
		return evento;
	}

	public void setEvento (EventoMatriz evento) {
		this.evento = evento;
	}

}
