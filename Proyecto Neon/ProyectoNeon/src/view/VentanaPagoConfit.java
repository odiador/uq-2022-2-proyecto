package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.time.LocalDateTime;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import model.Herramientas;
import objetos.CambioComboBox;
import objetos.Confiteria;
import objetos.CustomComboBox;
import objetos.CustomTextfield;
import objetos.ListadeConfiteria;

public class VentanaPagoConfit extends ParteSuperior implements CambioComboBox {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panelPagos, panelPrevio, previa[][];
	private JLabel lPrevia[][], lblLLenarTipo, lblLlenarMetodo1, lblLlenarMetodo2, lblLlenarTf1, lblLlenarTf2;
	private ListadeConfiteria listaConfiteria;
	private CustomTextfield tf, tf2;
	private double total;
	private CustomComboBox combo1, combo2, comboTipoPago, pagar, volver;
	private JScrollPane scroll;

	public static void main(String[] args) {
		ListadeConfiteria listaConfiteria = new ListadeConfiteria();
		listaConfiteria.agregarConfiteria("Pan", 500d, 3);
		listaConfiteria.agregarConfiteria("Arepa", 400d, 10);
		listaConfiteria.agregarConfiteria("Empanada", 600d, 1);
		new VentanaPagoConfit(listaConfiteria).setVisible(true);
	}

	public VentanaPagoConfit(ListadeConfiteria listaConfiteria) {

		setTotal(listaConfiteria.getPrecioTotal());
		setListaConfiteria(listaConfiteria);

		panelPagos = new JPanel();
		panelPagos.setBorder(new LineBorder(col));
		panelPrevio = new JPanel();
		panelPagos.setBackground(Herramientas.black);
		panelPrevio.setBackground(Herramientas.white);
		panelPrevio.setBorder(new EmptyBorder(1, 1, 1, 1));
		Rectangle auxBounds = panelSuperior.getBounds();
		auxBounds.y += auxBounds.height + 20;
		auxBounds.height = getHeight() - auxBounds.height - 31;

		panelPagos.setBounds(auxBounds);

		panelPrevio.setBounds(30, 0, auxBounds.width - 60, 199);
		panelPagos.setLayout(null);

		scroll = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		String[][] previaString = obtenerPrevia();

		panelPrevio.setLayout(new GridLayout(previaString.length, previaString[0].length, 1, 1));
		lPrevia = new JLabel[previaString.length][previaString[0].length];
		previa = new JPanel[previaString.length][previaString[0].length];
		for (int i = 0; i < previaString.length; i++) {
			for (int j = 0; j < previaString[0].length; j++) {
				previa[i][j] = new JPanel();
				previa[i][j].setBackground(Herramientas.black);
				previa[i][j].setLayout(new BorderLayout());

				lPrevia[i][j] = new JLabel(Herramientas.obtenerLabelCentrado(previaString[i][j], "center"));
				lPrevia[i][j].setFont(Herramientas.FUENTE_COOLVETICA);
				lPrevia[i][j].setToolTipText(previaString[i][j]);
				lPrevia[i][j].setForeground(Herramientas.white);
				lPrevia[i][j].setHorizontalAlignment(0);
				lPrevia[i][j].setVerticalAlignment(0);
				lPrevia[i][j].setPreferredSize(new Dimension(panelPrevio.getWidth() / previaString[0].length, 50));
				if (i == 0) {
					previa[i][j].setBackground(Herramientas.white);
					lPrevia[i][j].setForeground(Herramientas.black);
				}

				previa[i][j].add(lPrevia[i][j]);
				panelPrevio.add(previa[i][j]);
			}
		}
		scroll.getViewport().add(panelPrevio);
		scroll.setBounds(panelPrevio.getBounds());
		scroll.setBorder(new LineBorder(Herramientas.white));

		String opciones[] = { "Único", "Mixto" };
		comboTipoPago = new CustomComboBox(opciones);
		comboTipoPago.setFont(Herramientas.FUENTE_COOLVETICA);
		comboTipoPago.addCambioListener(this);

		lblLLenarTipo = new JLabel(Herramientas.obtenerLabelCentrado("Elige una opción de pago", "right"));
		lblLLenarTipo.setHorizontalAlignment(4);
		lblLLenarTipo.setFont(Herramientas.FUENTE_COOLVETICA);
		lblLLenarTipo.setForeground(Herramientas.white);

		tf = new CustomTextfield(Herramientas.setFormatodeNumero(0, getTotal()));
		tf.setText(Herramientas.formatoSinDecimal(getTotal()));
		tf.setFont(Herramientas.FUENTE_COOLVETICA.deriveFont(25f));
		tf.setEditable(false);
		tf.addKeyListener(this);

		tf2 = new CustomTextfield(Herramientas.setFormatodeNumero(0, getTotal()));
		tf2.setText("0");
		tf2.setDisabledTextColor(Herramientas.gray.darker());
		tf2.setVisible(false);
		tf2.setFont(Herramientas.FUENTE_COOLVETICA.deriveFont(25f));
		tf2.addKeyListener(this);

		EmptyBorder borde = new EmptyBorder(0, 0, 0, 10);

		lblLlenarMetodo1 = new JLabel(Herramientas.obtenerLabelCentrado("Elige un metodo de pago", "right"));
		lblLlenarMetodo1.setForeground(Herramientas.white);
		lblLlenarMetodo1.setBorder(borde);
		lblLlenarMetodo1.setFont(Herramientas.FUENTE_COOLVETICA.deriveFont(25f));
		lblLlenarMetodo1.setHorizontalAlignment(4);

		lblLlenarMetodo2 = new JLabel(Herramientas.obtenerLabelCentrado("Elige el metodo de pago 2", "right"));
		lblLlenarMetodo2.setForeground(Herramientas.white);
		lblLlenarMetodo2.setBorder(borde);
		lblLlenarMetodo2.setFont(Herramientas.FUENTE_COOLVETICA.deriveFont(25f));
		lblLlenarMetodo2.setHorizontalAlignment(4);
		lblLlenarMetodo2.setVisible(false);

		lblLlenarTf1 = new JLabel(Herramientas.obtenerLabelCentrado("Total a Pagar", "right"));
		lblLlenarTf1.setForeground(Herramientas.white);
		lblLlenarTf1.setBorder(borde);
		lblLlenarTf1.setFont(Herramientas.FUENTE_COOLVETICA.deriveFont(25f));
		lblLlenarTf1.setHorizontalAlignment(4);

		lblLlenarTf2 = new JLabel(Herramientas.obtenerLabelCentrado("Elige la cantidad 2", "right"));
		lblLlenarTf2.setBorder(borde);
		lblLlenarTf2.setForeground(Herramientas.white);
		lblLlenarTf2.setFont(Herramientas.FUENTE_COOLVETICA.deriveFont(25f));
		lblLlenarTf2.setHorizontalAlignment(4);
		lblLlenarTf2.setVisible(false);

		combo1 = new CustomComboBox(Herramientas.mediosDePagoConfiteria);
		combo1.setFont(Herramientas.FUENTE_COOLVETICA.deriveFont(25f));

		combo2 = new CustomComboBox(Herramientas.mediosDePagoConfiteria);
		combo2.setFont(Herramientas.FUENTE_COOLVETICA.deriveFont(25f));
		combo2.setVisible(false);

		pagar = new CustomComboBox("Pagar");
		pagar.setFont(Herramientas.FUENTE_COOLVETICA.deriveFont(25f));
		pagar.addCambioListener(this);

		volver = new CustomComboBox("Volver");
		volver.setFont(Herramientas.FUENTE_COOLVETICA.deriveFont(25f));
		volver.addCambioListener(this);

		lblLLenarTipo.setBounds(posXCentrada(480), 230, 300, 40);
		comboTipoPago.setBounds(posXCentrada(480) + 330, 230, 150, 40);

		lblLlenarTf1.setBounds(posXCentrada(630), 280, 300, 40);
		tf.setBounds(posXCentrada(630) + 330, 280, 300, 40);
		lblLlenarMetodo1.setBounds(posXCentrada(630), 330, 300, 40);
		combo1.setBounds(posXCentrada(630) + 330, 330, 300, 40);

		lblLlenarTf2.setBounds(posXCentrada(630), 380, 300, 40);
		tf2.setBounds(posXCentrada(630) + 330, 380, 300, 40);
		lblLlenarMetodo2.setBounds(posXCentrada(630), 430, 300, 40);
		combo2.setBounds(posXCentrada(630) + 330, 430, 300, 40);

		pagar.setBounds(posXCentrada(330), 390, 150, 40);
		volver.setBounds(posXCentrada(330) + 180, 390, 150, 40);
		setSize(getWidth(), panelPagos.getY() + pagar.getY() + pagar.getHeight() + 30);
		setLocationRelativeTo(null);

		panelPagos.add(tf);
		panelPagos.add(lblLlenarTf1);
		panelPagos.add(combo1);
		panelPagos.add(lblLlenarMetodo1);
		panelPagos.add(pagar);
		panelPagos.add(volver);

		panelPagos.add(tf2);
		panelPagos.add(lblLlenarTf2);
		panelPagos.add(combo2);
		panelPagos.add(lblLlenarMetodo2);
		panelPagos.add(comboTipoPago);
		panelPagos.add(scroll);
		panelPagos.add(lblLLenarTipo);
		contentPane.add(panelPagos);
	}

	public void conFigurarVentana() {
		setSize(1000, 575);
		cambiarTitulo("", Herramientas.FUENTE_TITULO_DEFAULT);
		setTitulo("Registrar Pago");

	}

	public String[][] obtenerPrevia() {
		String[][] prev = new String[listaConfiteria.getSize() + 1][3];
		prev[0][0] = "Tipo";
		prev[0][1] = "Cantidad";
		prev[0][2] = "Precio";
		for (int i = 0; i < listaConfiteria.getSize(); i++) {
			Confiteria obj = listaConfiteria.getElementodeCarrito(i);
			prev[i + 1][0] = obj.getTipoDeCompra();
			prev[i + 1][1] = obj.getCantidad() + "";
			prev[i + 1][2] = "$" + Herramientas.formatoSinDecimal(obj.getPrecio());
		}
		return prev;
	}

	public void keyPressed(KeyEvent e) {
		super.keyPressed(e);
		if (e.getSource() == tf.getTf()) {
			tf.teclaPresionada(e);
		}
		if (e.getSource() == tf2.getTf()) {
			tf2.teclaPresionada(e);
		}
	}

	public void keyReleased(KeyEvent e) {
		super.keyReleased(e);
		if (e.getSource() == tf.getTf() && isMixto())
			tf2.setText(Herramientas.formatoSinDecimal(getTotal() - Integer.parseInt(tf.getText())));
		if (e.getSource() == tf2.getTf() && isMixto())
			tf.setText(Herramientas.formatoSinDecimal(getTotal() - Integer.parseInt(tf2.getText())));

	}

	public void cambioDeColor() {
		pagar.actualizarColor(col);
		volver.actualizarColor(col);
		lblLLenarTipo.setForeground(col);
		comboTipoPago.actualizarColor(col);

		tf.actualizarColor(col);
		combo1.actualizarColor(col);
		lblLlenarMetodo1.setForeground(col);
		lblLlenarTf1.setForeground(col);

		tf2.actualizarColor(col);
		combo2.actualizarColor(col);
		lblLlenarMetodo2.setForeground(col);
		lblLlenarTf2.setForeground(col);

		scroll.setBorder(new LineBorder(col));
		panelPagos.setBorder(new EmptyBorder(2, 2, 2, 2));
		panelPrevio.setBackground(col);

		for (int i = 0; i < lPrevia.length; i++)
			for (int j = 0; j < lPrevia[0].length; j++) {
				if (i == 0) {
					previa[i][j].setBackground(col);
					continue;
				}
				lPrevia[i][j].setForeground(col);
			}
	}

	public void cambiarMaximizado(boolean esGrande) {
	}

	public void setListaConfiteria(ListadeConfiteria listaConfiteria) {
		this.listaConfiteria = listaConfiteria;
	}

	private boolean isMixto() {
		return comboTipoPago.getText().equals("Mixto");
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public static String pasarTimeString(int hh) {
		return Herramientas.pasarHoraString(hh);
	}

	public void AccionesCambioComboBox(CustomComboBox c) {
		if (c.equals(volver)) {
		}
		if (c.equals(pagar)) {
			LocalDateTime infoTime = LocalDateTime.now();
			String anio = pasarTimeString(infoTime.getYear());
			String mes = pasarTimeString(infoTime.getMonthValue());
			String dia = pasarTimeString(infoTime.getDayOfMonth());
			String hora = pasarTimeString(infoTime.getHour());
			String minuto = pasarTimeString(infoTime.getMinute());
			String segundo = pasarTimeString(infoTime.getSecond());
			int numC1 = Integer.parseInt(tf.getText());
			int numC2 = Integer.parseInt(tf2.getText());
			if (isMixto()) {
				if (combo1.getText().equals(combo2.getText())) {
					Herramientas.abrirPopUp("Advertencia", "Los tipos no pueden ser iguales");
					return;
				}
				if (numC1 == 0 || numC2 == 0) {
					Herramientas.abrirPopUp("Advertencia", "Ninguna cantidad puede ser 0");
					return;
				}
				System.out.println("Mixto, Confitería, " + listaConfiteria.getCarrito() + ",\n" + hora + ":" + minuto
						+ ":" + segundo + ", " + dia + "/" + mes + "/" + anio + ", " + combo1.getText() + ": $" + numC1
						+ ", " + combo2.getText() + ": $" + numC2);
			} else {
				System.out.println("Mixto, Confitería, " + listaConfiteria.getCarrito() + ",\n" + hora + ":" + minuto
						+ ":" + segundo + ", " + dia + "/" + mes + "/" + anio + ", " + combo1.getText() + ": $" + numC1);
			}
		}

		if (c.equals(comboTipoPago)) {
			int height = 575;
			if (comboTipoPago.getText().equals("Único")) {
				pagar.setLocation(pagar.getX(), 390);
				volver.setLocation(volver.getX(), 390);

				tf.setText(Herramientas.formatoSinDecimal(getTotal()));
				tf.setEditable(false);
				lblLlenarTf1.setText(Herramientas.obtenerLabelCentrado("Total a Pagar", "right"));
				lblLlenarMetodo1.setText(Herramientas.obtenerLabelCentrado("Elige un metodo de pago", "right"));
				lblLlenarTf2.setVisible(false);

				tf2.setText("0");
				lblLlenarMetodo2.setVisible(false);
				combo2.setVisible(false);
				tf2.setVisible(false);
				panelPagos.setSize(panelPagos.getWidth(), panelPagos.getHeight() - 100);
			} else {
				pagar.setLocation(pagar.getX(), 490);
				volver.setLocation(volver.getX(), 490);

				lblLlenarMetodo2.setVisible(true);
				lblLlenarTf2.setVisible(true);
				combo2.setVisible(true);
				tf2.setVisible(true);

				lblLlenarMetodo1.setText(Herramientas.obtenerLabelCentrado("Elige el metodo de pago 1", "right"));
				lblLlenarTf1.setText(Herramientas.obtenerLabelCentrado("Escribe la cantidad 1", "right"));
				tf.setEditable(true);
				height = 675;
				panelPagos.setSize(panelPagos.getWidth(), panelPagos.getHeight() + 100);
			}
			setSize(getWidth(), height);
			setLocationRelativeTo(null);
		}
	}
}