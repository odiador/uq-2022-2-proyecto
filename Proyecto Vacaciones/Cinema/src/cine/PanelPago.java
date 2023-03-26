package cine;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import config.Utilities;
import custom.ClicListener;
import custom.ComboBox;
import custom.ComboListener;
import custom.Componente;
import custom.Evento;
import custom.EventoCombo;
import custom.EventoTf;
import custom.NumberTextField;
import custom.PagoListener;
import custom.Panel;
import custom.Text;
import custom.TextfieldListener;
import objects.Cliente;
import objects.Compra;
import objects.CustomException;
import tools.TablaCinema;
import tools.TablaClientes;
import tools.TablaCompras;

public class PanelPago extends Panel implements ClicListener, ComboListener, TextfieldListener {

	private ComboBox comboTipoPago;
	private ComboBox comboMedioPago1, comboMedioPago2;
	private Text lblComboMedio1, lblComboMedio2, lblCantidad1, lblCantidad2;
	private NumberTextField tfCantidad1, tfCantidad2;
	private Text bAceptar, bVolver, lblElige;
	private Text bMas1, bMas2, bMenos1, bMenos2;
	private Text lblInfoTarj1, lblInfoTarj2;
	private EventoMatriz infoSillas;
	private Cliente cliente;
	public static final int UNICO = 0;
	public static final int MIXTO = 1;
	public static final String[] tipos = new String[] { "Único", "Mixto" };
	private List<PagoListener> lista = new ArrayList<PagoListener>();
	private String mensajeExtra = "";

	public PanelPago(Cliente cliente, EventoMatriz infoSillas) {

		setCliente(cliente);
		setInfoSillas(infoSillas);

		setComboTipoPago(new ComboBox(tipos));

		setLblCantidad1(new Text("La cantidad es:"));
		setLblCantidad2(new Text("Escribe la cantidad 2:"));
		setLblComboMedio1(new Text("Elige un medio de pago:"));
		setLblComboMedio2(new Text("Elige el medio de pago 2:"));
		lblInfoTarj1 = new Text();
		lblInfoTarj2 = new Text();
		setbMas1(new Text("+"));
		setbMas2(new Text("+"));
		setbMenos1(new Text("-"));
		setbMenos2(new Text("-"));
		setLblElige(new Text("Elige un tipo de pago:"));

		ArrayList<String> listaCombo = new ArrayList<String>(Arrays.asList("Efectivo", "PSE", "Tarjeta de Crédito"));
		if (cliente.gettBasic().getExisteTarjeta()) {
			listaCombo.add("Tarjeta Basic");
			listaCombo.add("Puntos Basic");
		}
		if (cliente.gettGold().getExisteTarjeta()) {
			listaCombo.add("Tarjeta Gold");
			listaCombo.add("Puntos Gold");
		}

		setComboMedioPago1(new ComboBox(listaCombo));
		setComboMedioPago2(new ComboBox(listaCombo));
		setTfCantidad1(new NumberTextField(getInfoSillas().getCantidad()));
		setTfCantidad2(new NumberTextField(getInfoSillas().getCantidad()));

		getTfCantidad1().setValue(getInfoSillas().getCantidad());
		getTfCantidad2().setValue(0);

		getTfCantidad1().setEditable(false);
		getTfCantidad2().setEditable(false);
		getTfCantidad1().addTextfieldListener(this);
		getTfCantidad2().addTextfieldListener(this);
		getComboTipoPago().addComboListener(this);

		getComboMedioPago1().addComboListener(this);
		getComboMedioPago2().addComboListener(this);

		getLblElige().setHorizontalAlignment(SwingConstants.LEFT);
		getLblCantidad1().setHorizontalAlignment(SwingConstants.RIGHT);
		getLblCantidad2().setHorizontalAlignment(SwingConstants.RIGHT);
		getLblComboMedio1().setHorizontalAlignment(SwingConstants.RIGHT);
		getLblComboMedio2().setHorizontalAlignment(SwingConstants.RIGHT);
		getLblCantidad1().setTextInsets(0, 0, 0, 5);
		getLblCantidad2().setTextInsets(0, 0, 0, 5);
		getLblComboMedio1().setTextInsets(0, 0, 0, 5);
		getLblComboMedio2().setTextInsets(0, 0, 0, 5);

		getbMas1().configAsButton(this);
		getbMas2().configAsButton(this);
		getbMenos1().configAsButton(this);
		getbMenos2().configAsButton(this);
		getComboTipoPago().configAsButton(this, Componente.defaultColor);

		getComboTipoPago().setPreferredSize(300, 40);
		getTfCantidad1().setPreferredSize(300, 40);
		getComboMedioPago1().setPreferredSize(300, 40);

		getComboMedioPago2().setPreferredSize(300, 0);
		getTfCantidad2().setPreferredSize(300, 0);
		getLblCantidad2().setPreferredSize(300, 0);
		getLblComboMedio2().setPreferredSize(300, 0);

		getbMas1().setPreferredSize(0, 0);
		getbMenos1().setPreferredSize(0, 0);
		getbMas2().setPreferredSize(0, 0);
		getbMenos2().setPreferredSize(0, 0);

		lblInfoTarj1.setHorizontalAlignment(SwingConstants.LEFT);
		lblInfoTarj2.setHorizontalAlignment(SwingConstants.LEFT);

		setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0, 0, 10, 0);

		gbc.gridwidth = 4;
		add(lblElige, gbc);

		gbc.gridy = 1;
		add(getComboTipoPago(), gbc);
		gbc.gridwidth = 1;
		gbc.gridy = 2;
		add(getLblCantidad1(), gbc);
		gbc.gridx = 1;
		add(getTfCantidad1(), gbc);
		gbc.gridx = 2;
		gbc.insets = new Insets(0, 5, 0, 0);
		gbc.fill = GridBagConstraints.NONE;
		add(getbMas1(), gbc);
		gbc.gridx = 3;
		add(getbMenos1(), gbc);
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0, 0, 0, 0);
		add(getLblComboMedio1(), gbc);
		gbc.gridx = 1;
		add(getComboMedioPago1(), gbc);
		gbc.gridx = 2;
		gbc.gridwidth = 3;
		add(lblInfoTarj1, gbc);
		gbc.gridwidth = 1;
		gbc.insets = new Insets(0, 0, 10, 0);
		gbc.gridx = 0;
		gbc.gridy = 4;
		add(getLblCantidad2(), gbc);
		gbc.gridx = 1;
		add(getTfCantidad2(), gbc);
		gbc.insets = new Insets(0, 5, 0, 0);
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridx = 2;
		add(getbMas2(), gbc);
		gbc.gridx = 3;
		add(getbMenos2(), gbc);
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0, 0, 0, 0);
		add(getLblComboMedio2(), gbc);
		gbc.gridx = 1;
		add(getComboMedioPago2(), gbc);
		gbc.gridx = 2;
		gbc.gridwidth = 3;
		add(lblInfoTarj2, gbc);
	}

	@Override
	public void botonPresionado (Evento e) {
		if (e.getSource() == getbMas1()) {
			if (getTfCantidad1().getIntNumber() == getInfoSillas().getCantidad()) return;
			getTfCantidad1().setValue(getTfCantidad1().getIntNumber() + 1);
		}
		if (e.getSource() == getbMas2()) {
			if (getTfCantidad2().getIntNumber() == getInfoSillas().getCantidad()) return;
			getTfCantidad2().setValue(getTfCantidad2().getIntNumber() + 1);
		}
		if (e.getSource() == getbMenos1()) {
			if (getTfCantidad1().getIntNumber() == 0) return;
			getTfCantidad1().setValue(getTfCantidad1().getIntNumber() - 1);
		}
		if (e.getSource() == getbMenos2()) {
			if (getTfCantidad2().getIntNumber() == 0) return;
			getTfCantidad2().setValue(getTfCantidad2().getIntNumber() - 1);
		}
	}

	@Override
	public void textfieldUpdate (EventoTf e) {
		if (comboTipoPago.getText().equals("Mixto")) {
			if (e.getSource() == getTfCantidad1()) {
				getTfCantidad2().setUpdateActivated(false);
				getTfCantidad2()
						.setValue(getInfoSillas().getCantidad() - ((Long) getTfCantidad1().getNumber()).intValue());
				getTfCantidad2().setUpdateActivated(true);
			} else if (e.getSource() == getTfCantidad2()) {
				getTfCantidad1().setUpdateActivated(false);
				getTfCantidad1()
						.setValue(getInfoSillas().getCantidad() - ((Long) getTfCantidad2().getNumber()).intValue());
				getTfCantidad1().setUpdateActivated(true);
			}
		}
	}

	public double getPrecio (int from, int until, int medioPagoCode) {
		double precio = 0;
		String sillas[] = getInfoSillas().getSelectedChairs();
		for (int i = from; i < until; i++) {
			precio += EventoMatriz.getValUnitario(sillas[i], medioPagoCode);
		}
		return precio;
	}

	public static int getCodigoMedioPago (String medioPago) {
		int code = EventoMatriz.NORMAL;
		if (medioPago.equals("Tarjeta Basic")) {
			code = EventoMatriz.BASIC;
		} else if (medioPago.equals("Puntos Basic")) {
			code = EventoMatriz.BASICPTS;
		} else if (medioPago.equals("Tarjeta Gold")) {
			code = EventoMatriz.GOLD;
		} else if (medioPago.equals("Puntos Gold")) {
			code = EventoMatriz.GOLDPTS;
		}
		return code;
	}

	public void pagar (LocalDateTime time, int from, int until, String medioPago, boolean esFinal)
			throws SQLException, CustomException {
		int code = getCodigoMedioPago(medioPago);
		double dinero = -1;
		double precio = -1;
		int puntos = -1;
		switch (code) {
			case EventoMatriz.BASIC:
				dinero = TablaClientes.getCliente(getCliente().getCC()).gettBasic().getDinero();
				precio = getPrecio(from, until, code);
				if (dinero < precio) throw new CustomException(CustomException.NO_HAY_DINERO,
						"<html>No hay suficiente dinero en la tarjeta basic para la compra <br>(Saldo: <font color=#FF0000>"
								+ Utilities.format(dinero) + "</font>, valor: " + Utilities.format(precio)
								+ ")</html>");
				break;
			case EventoMatriz.GOLD:
				dinero = TablaClientes.getCliente(getCliente().getCC()).gettGold().getDinero();
				precio = getPrecio(from, until, code);
				if (dinero < precio) throw new CustomException(CustomException.NO_HAY_DINERO,
						"<html>No hay suficiente dinero en la tarjeta gold para la compra <br>(Saldo: <font color=#FF0000>"
								+ Utilities.format(dinero) + "</font>, valor: " + Utilities.format(precio)
								+ ")</html>");
				break;
			case EventoMatriz.BASICPTS:
				puntos = TablaClientes.getCliente(getCliente().getCC()).gettBasic().getPuntos();
				int cantidadQueCubreBasic = puntos / 150;
				int cantidadNecesitadaBasic = until - from;
				if (cantidadQueCubreBasic < cantidadNecesitadaBasic) {
					throw new CustomException(CustomException.NO_HAY_PUNTOS,
							"<html>No hay suficientes puntos en la tarjeta basic para la compra <br>(Tienes: <font color=#FF0000>"
									+ puntos + " y se necesitan: " + (cantidadNecesitadaBasic * 150));
				}
				break;
			case EventoMatriz.GOLDPTS:
				puntos = TablaClientes.getCliente(getCliente().getCC()).gettGold().getPuntos();
				int cantidadQueCubreGold = puntos / 150;
				int cantidadNecesitadaGold = until - from;
				if (cantidadQueCubreGold < cantidadNecesitadaGold) {
					throw new CustomException(CustomException.NO_HAY_PUNTOS,
							"<html>No hay suficientes puntos en la tarjeta gold para la compra <br>(Tienes: <font color=#FF0000>"
									+ puntos + " y se necesitan: " + (cantidadNecesitadaGold * 150));
				}
				break;
		}

		String sillas[] = getInfoSillas().getSelectedChairs();

		for (int i = from; i < until; i++) {
			double cadaPrecio = EventoMatriz.getValUnitario(sillas[i], code);

			switch (code) {
				case EventoMatriz.BASIC -> TablaClientes.removeDineroBasic(getCliente().getCC(), cadaPrecio);
				case EventoMatriz.GOLD -> TablaClientes.removeDineroGold(getCliente().getCC(), cadaPrecio);
				case EventoMatriz.BASICPTS -> TablaClientes.removePuntosBasic(getCliente().getCC(), 150);
				case EventoMatriz.GOLDPTS -> TablaClientes.removePuntosGold(getCliente().getCC(), 150);
			}

			Compra c = new Compra(time, "Cinema", TablaCinema.getCinemaById(Utilities.getIdCine()).toString(),
					getCliente(), medioPago, sillas[i], cadaPrecio, 1);
			try {
				TablaCompras.agregarCompra(c);
			} catch (SQLException e1) {
				if (esFinal) for (PagoListener l : getLista()) l.pagoNoRealizado(e1);
				return;
			}
		}
		int ptsAAgregarBasic = (int) (precio / 100);
		boolean esBasicOGold = false;
		if (code == EventoMatriz.BASIC) {
			TablaClientes.addPuntosBasic(getCliente().getCC(), ptsAAgregarBasic);
			esBasicOGold = true;
		}
		if (code == EventoMatriz.GOLD) {
			TablaClientes.addPuntosGold(getCliente().getCC(), ptsAAgregarBasic * 2);
			esBasicOGold = true;
		}
		if (esBasicOGold) {
			if (!mensajeExtra.isEmpty()) mensajeExtra += "; ";
			mensajeExtra += "Saldo Anterior " + (code == EventoMatriz.BASIC ? "Basic" : "Gold") + ": $"
					+ Utilities.format(dinero) + ", precio: $" + Utilities.format(precio) + ", saldo nuevo: $"
					+ Utilities.format((dinero - precio));
		} else if (code != EventoMatriz.NORMAL) {
			if (!mensajeExtra.isEmpty()) mensajeExtra += "; ";
			int valor = (until - from) * 150;
			mensajeExtra += "Puntos Anteriores: " + puntos + ", valor: " + valor + ", nuevos: " + (puntos - valor);
		}
		if (esFinal) {
			for (PagoListener l : getLista()) l.pagoRealizado(mensajeExtra);
			mensajeExtra = "";
		}
	}

	public void changeText (int code, Text text) {
		switch (code) {
			case EventoMatriz.BASIC ->
				text.setText(" Saldo: $" + Utilities.format(getCliente().gettBasic().getDinero()));
			case EventoMatriz.GOLD -> text.setText(" Saldo: $" + Utilities.format(getCliente().gettGold().getDinero()));
			case EventoMatriz.BASICPTS -> text.setText(" Puntos: " + getCliente().gettBasic().getPuntos());
			case EventoMatriz.GOLDPTS -> text.setText(" Puntos: " + getCliente().gettGold().getPuntos());
			default -> text.setText(" ");
		}
	}

	@Override
	public void comboUpdate (EventoCombo e) {
		int comboCode = EventoMatriz.NORMAL;
		if (e.getText().equals("Tarjeta Basic")) comboCode = EventoMatriz.BASIC;
		if (e.getText().equals("Tarjeta Gold")) comboCode = EventoMatriz.GOLD;
		if (e.getText().equals("Puntos Basic")) comboCode = EventoMatriz.BASICPTS;
		if (e.getText().equals("Puntos Gold")) comboCode = EventoMatriz.GOLDPTS;

		if (e.getSource() == getComboMedioPago1()) {
			changeText(comboCode, lblInfoTarj1);
		}
		if (e.getSource() == getComboMedioPago2()) {
			changeText(comboCode, lblInfoTarj2);
		}
		if (e.getSource() == getComboTipoPago()) {
			if (getInfoSillas().getCantidad() < 2) {
				JOptionPane.showMessageDialog(null, "Para que pueda ser mixto, la cantidad debe de ser mayor que 1");
				getComboTipoPago().setText(0);
				return;
			}
			if (e.getText().equals("Mixto")) {
				getLblComboMedio1().setText("Elige el medio de pago 1:");
				getLblCantidad1().setText("Escribe la cantidad 1:");
				getTfCantidad1().setEditable(true);
				getTfCantidad2().setEditable(true);

				getTfCantidad2().setPreferredSize(300, 40);
				getComboMedioPago2().setPreferredSize(300, 40);
				getLblComboMedio2().setPreferredSize(300, 40);

				getbMas1().setPreferredSize(30, 30);
				getbMas2().setPreferredSize(30, 30);
				getbMenos1().setPreferredSize(30, 30);
				getbMenos2().setPreferredSize(30, 30);

				getTfCantidad2().revalidate();
				getComboMedioPago2().revalidate();
				getLblComboMedio2().revalidate();

				getbMas1().revalidate();
				getbMas2().revalidate();
				getbMenos1().revalidate();
				getbMenos2().revalidate();
			}
			if (e.getText().equals("Único")) {
				getLblComboMedio1().setText("Elige un medio de pago:");
				getLblCantidad1().setText("La cantidad es:");
				getTfCantidad1().setText(getInfoSillas().getCantidad() + "");
				getTfCantidad1().setEditable(false);
				getTfCantidad2().setEditable(false);

				getTfCantidad2().setPreferredSize(300, 0);
				getComboMedioPago2().setPreferredSize(300, 0);
				getLblComboMedio2().setPreferredSize(300, 0);
				getbMas1().setPreferredSize(0, 0);
				getbMas2().setPreferredSize(0, 0);
				getbMenos1().setPreferredSize(0, 0);
				getbMenos2().setPreferredSize(0, 0);

				getTfCantidad2().revalidate();
				getComboMedioPago2().revalidate();
				getLblComboMedio2().revalidate();
				getbMas1().revalidate();
				getbMas2().revalidate();
				getbMenos1().revalidate();
				getbMenos2().revalidate();
			}
		}
	}

	public Cliente getCliente () {
		return cliente;
	}

	public void setCliente (Cliente cliente) {
		this.cliente = cliente;
	}

	public EventoMatriz getInfoSillas () {
		return infoSillas;
	}

	public void setInfoSillas (EventoMatriz infoSillas) {
		this.infoSillas = infoSillas;
	}

	public Text getLblElige () {
		return lblElige;
	}

	public void setLblElige (Text lblElige) {
		this.lblElige = lblElige;
	}

	public Text getbVolver () {
		return bVolver;
	}

	public void setbVolver (Text bVolver) {
		this.bVolver = bVolver;
	}

	public Text getbAceptar () {
		return bAceptar;
	}

	public void setbAceptar (Text bAceptar) {
		this.bAceptar = bAceptar;
	}

	public ComboBox getComboTipoPago () {
		return comboTipoPago;
	}

	public void setComboTipoPago (ComboBox comboMedioPago) {
		this.comboTipoPago = comboMedioPago;
	}

	public List<PagoListener> getLista () {
		return lista;
	}

	public void setLista (List<PagoListener> lista) {
		this.lista = lista;
	}

	public void addPagoListener (PagoListener l) {
		getLista().add(l);
	}

	public void clearPagoListeners () {
		getLista().clear();
	}

	public ComboBox getComboMedioPago1 () {
		return comboMedioPago1;
	}

	public void setComboMedioPago1 (ComboBox comboMedioPago1) {
		this.comboMedioPago1 = comboMedioPago1;
	}

	public ComboBox getComboMedioPago2 () {
		return comboMedioPago2;
	}

	public void setComboMedioPago2 (ComboBox comboMedioPago2) {
		this.comboMedioPago2 = comboMedioPago2;
	}

	public NumberTextField getTfCantidad1 () {
		return tfCantidad1;
	}

	public void setTfCantidad1 (NumberTextField tfCantidad1) {
		this.tfCantidad1 = tfCantidad1;
	}

	public NumberTextField getTfCantidad2 () {
		return tfCantidad2;
	}

	public void setTfCantidad2 (NumberTextField tfCantidad2) {
		this.tfCantidad2 = tfCantidad2;
	}

	public Text getLblComboMedio1 () {
		return lblComboMedio1;
	}

	public void setLblComboMedio1 (Text lblComboMedio1) {
		this.lblComboMedio1 = lblComboMedio1;
	}

	public Text getLblCantidad1 () {
		return lblCantidad1;
	}

	public void setLblCantidad1 (Text lblCantidad1) {
		this.lblCantidad1 = lblCantidad1;
	}

	public Text getLblCantidad2 () {
		return lblCantidad2;
	}

	public void setLblCantidad2 (Text lblCantidad2) {
		this.lblCantidad2 = lblCantidad2;
	}

	public Text getLblComboMedio2 () {
		return lblComboMedio2;
	}

	public void setLblComboMedio2 (Text lblComboMedio2) {
		this.lblComboMedio2 = lblComboMedio2;
	}

	public Text getbMas1 () {
		return bMas1;
	}

	public void setbMas1 (Text bMas1) {
		this.bMas1 = bMas1;
	}

	public Text getbMas2 () {
		return bMas2;
	}

	public void setbMas2 (Text bMas2) {
		this.bMas2 = bMas2;
	}

	public Text getbMenos1 () {
		return bMenos1;
	}

	public void setbMenos1 (Text bMenos1) {
		this.bMenos1 = bMenos1;
	}

	public Text getbMenos2 () {
		return bMenos2;
	}

	public void setbMenos2 (Text bMenos2) {
		this.bMenos2 = bMenos2;
	}
}
