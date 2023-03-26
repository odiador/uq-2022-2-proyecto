package confiteria;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import config.Constantes;
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
import objects.Confiteria;
import objects.ListaConfiteria;
import tools.TablaCompras;

public class MedioPagoConfi extends Panel implements ClicListener, ComboListener, TextfieldListener {

	private ComboBox comboTipoPago;
	private ComboBox comboMedioPago1, comboMedioPago2;
	private Text lblComboMedio1, lblComboMedio2, lblCantidad1, lblCantidad2;
	private NumberTextField tfCantidad1, tfCantidad2;
	private Text bAceptar, bVolver, lblElige;
	private Text bMas1, bMas2, bMenos1, bMenos2;
	private ListaConfiteria carrito;
	private Cliente cliente;
	public static final int UNICO = 0;
	public static final int MIXTO = 1;
	public static final String[] tipos = new String[] { "Único", "Mixto" };
	private List<PagoListener> lista = new ArrayList<PagoListener>();

	public MedioPagoConfi(Cliente cliente, ListaConfiteria carrito) {
		Font f = Constantes.defaultFont.deriveFont(25f);

		setCliente(cliente);
		setCarrito(carrito);

		setComboTipoPago(new ComboBox(tipos));

		lblCantidad1 = new Text("La cantidad es:");
		lblCantidad2 = new Text("Escribe la cantidad 2:");
		lblComboMedio1 = new Text("Elige un medio de pago:");
		lblComboMedio2 = new Text("Elige el medio de pago 2:");
		bMas1 = new Text("+");
		bMas2 = new Text("+");
		bMenos1 = new Text("-");
		bMenos2 = new Text("-");
		setLblElige(new Text("Elige un tipo de pago:"));
		setbAceptar(new Text("Aceptar", f));
		setbVolver(new Text("Volver", f));

		ArrayList<String> listaCombo = new ArrayList<String>(Arrays.asList("Efectivo", "PSE", "Tarjeta de Crédito"));
		if (cliente.gettBasic().getExisteTarjeta()) listaCombo.add("Tarjeta Basic");
		if (cliente.gettGold().getExisteTarjeta()) listaCombo.add("Tarjeta Gold");

		comboMedioPago1 = new ComboBox(listaCombo);
		comboMedioPago2 = new ComboBox(listaCombo);
		tfCantidad1 = new NumberTextField(carrito.getSize());
		tfCantidad2 = new NumberTextField(carrito.getSize());

		tfCantidad1.setText(carrito.getSize() + "");
		tfCantidad2.setText("0");

		tfCantidad1.setEditable(false);
		tfCantidad2.setEditable(false);
		tfCantidad1.addTextfieldListener(this);
		tfCantidad2.addTextfieldListener(this);
		getComboTipoPago().addComboListener(this);

		getLblElige().setHorizontalAlignment(SwingConstants.LEFT);
		lblCantidad1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCantidad2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblComboMedio1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblComboMedio2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCantidad1.setTextInsets(0, 0, 0, 5);
		lblCantidad2.setTextInsets(0, 0, 0, 5);
		lblComboMedio1.setTextInsets(0, 0, 0, 5);
		lblComboMedio2.setTextInsets(0, 0, 0, 5);

		bMas1.configAsButton(this);
		bMas2.configAsButton(this);
		bMenos1.configAsButton(this);
		bMenos2.configAsButton(this);
		getComboTipoPago().configAsButton(this, Componente.defaultColor);
		getbAceptar().configAsButton(this);
		getbVolver().configAsButton(this);

		getbAceptar().setPreferredSize(300, 40);
		getbVolver().setPreferredSize(300, 40);

		getComboTipoPago().setPreferredSize(300, 40);

		tfCantidad1.setPreferredSize(300, 40);
		tfCantidad2.setPreferredSize(300, 0);
		comboMedioPago1.setPreferredSize(300, 40);
		comboMedioPago2.setPreferredSize(300, 0);
		lblCantidad2.setPreferredSize(300, 0);
		lblComboMedio2.setPreferredSize(300, 0);
		bMas1.setPreferredSize(0, 0);
		bMenos1.setPreferredSize(0, 0);
		bMas2.setPreferredSize(0, 0);
		bMenos2.setPreferredSize(0, 0);

		Panel contenedorCentral = new Panel();

		contenedorCentral.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0, 0, 10, 0);
		gbc.gridwidth = 4;
		contenedorCentral.add(lblElige, gbc);

		gbc.gridy = 1;
		contenedorCentral.add(getComboTipoPago(), gbc);
		gbc.gridwidth = 1;
		gbc.gridy = 2;
		contenedorCentral.add(lblCantidad1, gbc);
		gbc.gridx = 1;
		contenedorCentral.add(tfCantidad1, gbc);
		gbc.gridx = 2;
		gbc.insets = new Insets(0, 5, 0, 0);
		gbc.fill = GridBagConstraints.NONE;
		contenedorCentral.add(bMas1, gbc);
		gbc.gridx = 3;
		contenedorCentral.add(bMenos1, gbc);
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0, 0, 10, 0);
		contenedorCentral.add(lblComboMedio1, gbc);
		gbc.gridx = 1;
		contenedorCentral.add(comboMedioPago1, gbc);

		gbc.gridx = 0;
		gbc.gridy = 4;
		contenedorCentral.add(lblCantidad2, gbc);
		gbc.gridx = 1;
		contenedorCentral.add(tfCantidad2, gbc);
		gbc.insets = new Insets(0, 5, 0, 0);
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridx = 2;
		contenedorCentral.add(bMas2, gbc);
		gbc.gridx = 3;
		contenedorCentral.add(bMenos2, gbc);
		gbc.gridx = 0;
		gbc.gridy = 5;
		contenedorCentral.add(lblComboMedio2, gbc);
		gbc.gridx = 1;
		contenedorCentral.add(comboMedioPago2, gbc);

		getComboTipoPago().setPreferredSize(300, 40);

		Panel contenedorInf = new Panel();
		contenedorInf.setLayout(new GridLayout(1, 2));
		contenedorInf.add(getbAceptar());
		contenedorInf.add(getbVolver());

		add(contenedorCentral);
		add(contenedorInf, BorderLayout.SOUTH);
	}

	@Override
	public void botonPresionado (Evento e) {
		if (e.getSource() == bMas1) {
			if (tfCantidad1.getIntNumber() == getCarrito().getSize()) return;
			tfCantidad1.setValue(tfCantidad1.getIntNumber() + 1);
		}
		if (e.getSource() == bMas2) {
			if (tfCantidad2.getIntNumber() == getCarrito().getSize()) return;
			tfCantidad2.setValue(tfCantidad2.getIntNumber() + 1);
		}
		if (e.getSource() == bMenos1) {
			if (tfCantidad1.getIntNumber() == 0) return;
			tfCantidad1.setValue(tfCantidad1.getIntNumber() - 1);
		}
		if (e.getSource() == bMenos2) {
			if (tfCantidad2.getIntNumber() == 0) return;
			tfCantidad2.setValue(tfCantidad2.getIntNumber() - 1);
		}
		if (e.getSource() == getbAceptar()) {
			LocalDateTime time = LocalDateTime.now();
			if (comboTipoPago.getText().equals("Único")) {
				pagar(time, 0, getCarrito().getSize(), comboMedioPago1.getText());
			} else {
				if (comboMedioPago1.getText().equals(comboMedioPago2.getText())) {
					JOptionPane.showMessageDialog(null, "Los medios dde pagos no pueden ser iguales");
					return;
				}
				if (tfCantidad1.getNumber() == 0 || tfCantidad2.getNumber() == 0) {
					JOptionPane.showMessageDialog(null, "Ninguna cantidad puede ser 0");
					return;
				}
				int mitad = tfCantidad1.getIntNumber();
				pagar(time, 0, mitad, comboMedioPago1.getText());
				pagar(time, mitad, getCarrito().getSize(), comboMedioPago2.getText());
			}

		}
		if (e.getSource() == getbVolver()) {
			removeAll();
			setLayout(new BorderLayout());
			setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
			add(new SelecionConfi(getCarrito()));
			revalidate();
			repaint();
			for (PagoListener l : getLista()) l.volver();
		}
	}

	@Override
	public void textfieldUpdate (EventoTf e) {
		if (comboTipoPago.getText().equals("Mixto")) {
			if (e.getSource() == tfCantidad1) {
				tfCantidad2.setUpdateActivated(false);
				tfCantidad2.setValue(carrito.getSize() - ((Long) tfCantidad1.getNumber()).intValue());
				tfCantidad2.setUpdateActivated(true);
			} else if (e.getSource() == tfCantidad2) {
				tfCantidad1.setUpdateActivated(false);
				tfCantidad1.setValue(carrito.getSize() - ((Long) tfCantidad2.getNumber()).intValue());
				tfCantidad1.setUpdateActivated(true);
			}
		}
	}

	private void pagar (LocalDateTime time, int from, int until, String medioPago) {
		for (int i = from; i < until; i++) {
			Confiteria confi = getCarrito().get(i);
			Compra c = new Compra(time, "Confitería", "", getCliente(), medioPago, confi.getProducto(),
					confi.getValUnitario(), confi.getCantidad());
			try {
				TablaCompras.agregarCompra(c);
			} catch (SQLException e1) {
				for (PagoListener l : getLista()) l.pagoNoRealizado(e1);
				return;
			}
		}
		for (PagoListener l : getLista()) l.pagoRealizado("");

	}

	@Override
	public void comboUpdate (EventoCombo e) {
		if (getCarrito().getSize() < 2) {
			JOptionPane.showMessageDialog(null, "Para que pueda ser mixto, la cantidad debe de ser mayor que 1");
			getComboTipoPago().setText(0);
			return;
		}
		if (e.getText().equals("Mixto")) {
			lblComboMedio1.setText("Elige el medio de pago 1:");
			lblCantidad1.setText("Escribe la cantidad 1:");
			tfCantidad1.setEditable(true);
			tfCantidad2.setEditable(true);

			tfCantidad2.setPreferredSize(300, 40);
			comboMedioPago2.setPreferredSize(300, 40);
			bMas1.setPreferredSize(30, 30);
			bMas2.setPreferredSize(30, 30);
			bMenos1.setPreferredSize(30, 30);
			bMenos2.setPreferredSize(30, 30);
			tfCantidad2.revalidate();
			comboMedioPago2.revalidate();
			bMas1.revalidate();
			bMas2.revalidate();
			bMenos1.revalidate();
			bMenos2.revalidate();
		}
		if (e.getText().equals("Único")) {
			lblComboMedio1.setText("Elige un medio de pago:");
			lblCantidad1.setText("La cantidad es:");
			tfCantidad1.setText(carrito.getSize() + "");
			tfCantidad1.setEditable(false);
			tfCantidad2.setEditable(false);

			tfCantidad2.setPreferredSize(300, 0);
			comboMedioPago2.setPreferredSize(300, 0);
			bMas1.setPreferredSize(0, 0);
			bMas2.setPreferredSize(0, 0);
			bMenos1.setPreferredSize(0, 0);
			bMenos2.setPreferredSize(0, 0);

			tfCantidad2.revalidate();
			comboMedioPago2.revalidate();
			bMas1.revalidate();
			bMas2.revalidate();
			bMenos1.revalidate();
			bMenos2.revalidate();
		}
	}

	public Cliente getCliente () {
		return cliente;
	}

	public void setCliente (Cliente cliente) {
		this.cliente = cliente;
	}

	public ListaConfiteria getCarrito () {
		return carrito;
	}

	public void setCarrito (ListaConfiteria carrito) {
		this.carrito = carrito;
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
}
