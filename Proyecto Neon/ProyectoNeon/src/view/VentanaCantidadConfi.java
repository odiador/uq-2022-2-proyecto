package view;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.LineBorder;

import model.Herramientas;
import objetos.Confiteria;
import objetos.ListadeConfiteria;

public class VentanaCantidadConfi extends ParteSuperior {
	private String tipo;
	private JPanel aceptar, volver, vistaPrevia[][], panImagen;
	private double valorUnitario;
	private JSeparator sepa;
	private VentanaConfi ventanaConfi;
	private JFormattedTextField tfCantidad;
	private JLabel lblCantidad, lAceptar, lVolver, lVistaPrevia[][], lImagen;
	private ListadeConfiteria listaConfiteria;
	private Confiteria confiteria;
	private String mensajes[][] = { { "Vista Previa", "" }, { "Tipo", "" }, { "Valor Unitario", "" },
			{ "Cantidad", "" }, { "Precio", "" }, { "Precio Total", "" } };

	public static void main(String[] args) {
		new VentanaCantidadConfi(null, "Sándwich", 1000, new ListadeConfiteria()).setVisible(true);
	}

	public void conFigurarVentana() {
		setSize(1170, 700);
		cambiarTitulo("Cantidad", Herramientas.FUENTE_TITULO_DEFAULT);
		setTitulo("Cantidad");
	}

	public VentanaCantidadConfi(VentanaConfi ventanaConfi, String tipo, double valorUnitario,
			ListadeConfiteria listaConfiteria) {
		setDefaultNavigation(false);
		this.ventanaConfi = ventanaConfi;
		this.tipo = tipo;
		this.listaConfiteria = listaConfiteria;
		this.valorUnitario = valorUnitario;
		lblCantidad = new JLabel();
		lblCantidad.setFont(Herramientas.FUENTE_COOLVETICA.deriveFont(30f));
		String msg = "Ingresa la cantidad de " + tipo + ":";
		lblCantidad.setText(Herramientas.obtenerLabelCentrado(msg, "left"));
		lblCantidad.setVerticalAlignment(3);
		lblCantidad.setBounds(100, 100, 500, 100);
		lblCantidad.setForeground(Herramientas.white);
		contentPane.add(lblCantidad);

		tfCantidad = new JFormattedTextField(Herramientas.setFormatodeNumero(0d, 9999d));
		tfCantidad.setText("0");
		tfCantidad.setBounds(100, 220, 500, 40);
		tfCantidad.setFocusTraversalKeysEnabled(false);
		tfCantidad.setFont(Herramientas.FUENTE_COOLVETICA.deriveFont(30f));
		tfCantidad.setBorder(null);
		tfCantidad.addKeyListener(this);
		tfCantidad.setForeground(Herramientas.white);
		tfCantidad.setBackground(Herramientas.black);

		sepa = new JSeparator();
		sepa.setBackground(Herramientas.black);
		sepa.setForeground(Herramientas.white);
		sepa.setBounds(tfCantidad.getX(), tfCantidad.getY() + tfCantidad.getHeight(), tfCantidad.getWidth(), 1);

		contentPane.add(tfCantidad);
		contentPane.add(sepa);

		aceptar = new JPanel();
		aceptar.setBounds((getWidth() / 2) - 220, 600, 200, 50);
		aceptar.setLayout(new BorderLayout(0, 0));
		aceptar.setCursor(Herramientas.HAND_CURSOR);
		aceptar.addMouseListener(this);
		aceptar.setBackground(Herramientas.BLACK);
		aceptar.setBorder(new LineBorder(col));

		lAceptar = new JLabel("Aceptar");
		lAceptar.setForeground(Herramientas.WHITE);
		lAceptar.setHorizontalAlignment(0);
		lAceptar.setFont(Herramientas.FUENTE_TITULO_DEFAULT.deriveFont(30f));

		contentPane.add(aceptar);
		aceptar.add(lAceptar, "Center");

		volver = new JPanel();
		volver.setBounds((getWidth() / 2) + 20, 600, 200, 50);
		volver.setLayout(new BorderLayout(0, 0));
		volver.addMouseListener(this);
		volver.setCursor(Herramientas.HAND_CURSOR);
		volver.setBackground(Herramientas.BLACK);
		volver.setBorder(new LineBorder(col));

		lVolver = new JLabel("Volver");
		lVolver.setForeground(Herramientas.WHITE);
		lVolver.setHorizontalAlignment(0);
		lVolver.setFont(Herramientas.FUENTE_TITULO_DEFAULT.deriveFont(30f));

		contentPane.add(volver);
		volver.add(lVolver, "Center");

		vistaPrevia = new JPanel[6][2];
		lVistaPrevia = new JLabel[vistaPrevia.length][vistaPrevia[0].length];
		vistaPrevia[0][0] = new JPanel();
		vistaPrevia[0][0].setLayout(new BorderLayout(0, 0));
		vistaPrevia[0][0].setBackground(col);
		vistaPrevia[0][0].setBounds(150, 300, 400, 45);
		lVistaPrevia[0][0] = new JLabel();
		lVistaPrevia[0][0].setText(mensajes[0][0]);
		lVistaPrevia[0][0].setFont(Herramientas.FUENTE_COOLVETICA.deriveFont(36f));
		lVistaPrevia[0][0].setHorizontalAlignment(0);
		lVistaPrevia[0][0].setForeground(Herramientas.black);
		Herramientas.cambiarTamLabel(vistaPrevia[0][0].getBounds(), lVistaPrevia[0][0]);

		vistaPrevia[0][0].add(lVistaPrevia[0][0], "Center");
		contentPane.add(vistaPrevia[0][0]);

		for (int i = 1; i < vistaPrevia.length; i++) {
			for (int j = 0; j < vistaPrevia[0].length; j++) {
				vistaPrevia[i][j] = new JPanel();
				vistaPrevia[i][j].setLayout(new BorderLayout(0, 0));
				vistaPrevia[i][j].setBackground(Herramientas.black);
				vistaPrevia[i][j].setBorder(new LineBorder(col));
				vistaPrevia[i][j].setBounds(vistaPrevia[0][0].getX() + (vistaPrevia[0][0].getWidth() * j / 2),
						vistaPrevia[0][0].getY() + (i * vistaPrevia[0][0].getHeight()),
						vistaPrevia[0][0].getWidth() / 2, vistaPrevia[0][0].getHeight());
				lVistaPrevia[i][j] = new JLabel();
				lVistaPrevia[i][j].setText(mensajes[i][j]);
				lVistaPrevia[i][j].setHorizontalAlignment(0);
				lVistaPrevia[i][j].setFont(lVistaPrevia[0][0].getFont());
				lVistaPrevia[i][j].setForeground(col);
				Herramientas.cambiarTamLabel(vistaPrevia[i][j].getBounds(), lVistaPrevia[i][j]);

				vistaPrevia[i][j].add(lVistaPrevia[i][j], "Center");
				contentPane.add(vistaPrevia[i][j]);
			}
		}
		panImagen = new JPanel();
		panImagen.setLayout(new BorderLayout(0, 0));
		panImagen.setBorder(new LineBorder(col));
		lImagen = new JLabel();

		panImagen
				.setBounds(650,
						vistaPrevia[vistaPrevia.length - 1][0].getY()
								+ vistaPrevia[vistaPrevia.length - 1][0].getHeight() - vistaPrevia[0][0].getWidth(),
						vistaPrevia[0][0].getWidth(), vistaPrevia[0][0].getWidth());
		lImagen.setIcon(Herramientas.escalarImagen(Herramientas.getImage(tipo), panImagen.getWidth(),
				panImagen.getHeight(), Herramientas.SCALE_SMOOTH));
		panImagen.add(lImagen, "Center");
		contentPane.add(panImagen);
		actualizarConfiteria();
	}

	public void keyPressed(KeyEvent e) {
		super.keyPressed(e);
		actualizarTextField(tfCantidad);
		if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_SPACE) {
			accionesAceptar();
		}
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			ventanaConfi.actualizarColores();
			ventanaConfi.setVisible(true);
			setVisible(false);
		}
	}

	public void keyReleased(KeyEvent e) {
		super.keyReleased(e);
		actualizarConfiteria();
	}

	public void mouseExited(MouseEvent e) {
		super.mouseExited(e);
		if (e.getSource() == aceptar) {
			aceptar.setBackground(Herramientas.black);
			lAceptar.setForeground(col);
		}
		if (e.getSource() == volver) {
			volver.setBackground(Herramientas.black);
			lVolver.setForeground(col);
		}

	}

	public void mouseEntered(MouseEvent e) {
		super.mouseEntered(e);
		if (e.getSource() == aceptar) {
			aceptar.setBackground(col);
			lAceptar.setForeground(Herramientas.black);
		}
		if (e.getSource() == volver) {
			volver.setBackground(col);
			lVolver.setForeground(Herramientas.black);
		}
	}

	public void mouseReleased(MouseEvent e) {
		super.mouseReleased(e);
		if (e.getSource() == aceptar) {
			if (Herramientas.estaEnRangoDe(aceptar)) {
				accionesAceptar();
			}
		}
		if (e.getSource() == volver) {
			if (Herramientas.estaEnRangoDe(volver)) {
				ventanaConfi.actualizarColores();
				ventanaConfi.setVisible(true);
				setVisible(false);
			}
		}
	}

	public void cambioDeColor() {
		sepa.setForeground(col);
		tfCantidad.setForeground(col);
		lblCantidad.setForeground(col);
		vistaPrevia[0][0].setBackground(col);
		LineBorder linea = new LineBorder(col);
		aceptar.setBackground(Herramientas.black);
		aceptar.setBorder(linea);
		lAceptar.setForeground(col);
		panImagen.setBorder(new LineBorder(col));
		volver.setBackground(Herramientas.black);
		volver.setBorder(linea);
		lVolver.setForeground(col);

		for (int i = 1; i < vistaPrevia.length; i++) {
			for (int j = 0; j < vistaPrevia[0].length; j++) {
				if (vistaPrevia[i][j] != null) {
					lVistaPrevia[i][j].setForeground(col);
					vistaPrevia[i][j].setBorder(linea);
				}
			}
		}
	}

	public void actualizarConfiteria() {
		confiteria = new Confiteria(tipo, valorUnitario,
				Integer.parseInt(Herramientas.conseguirTextoFormatoSinDecimal(tfCantidad.getText())));
		lVistaPrevia[1][1].setText(confiteria.getTipoDeCompra());
		lVistaPrevia[2][1].setText("$" + Herramientas.formatoSinDecimal(valorUnitario));
		lVistaPrevia[3][1].setText(String.valueOf(confiteria.getCantidad()));
		lVistaPrevia[4][1].setText("$" + Herramientas.formatoSinDecimal(confiteria.getPrecio()));
		double precioTotal = listaConfiteria.getPrecioTotal() + confiteria.getPrecio();
		lVistaPrevia[5][1].setText("$" + Herramientas.formatoSinDecimal(precioTotal));
		for (int i = 1; i < lVistaPrevia.length; i++) {
			Herramientas.cambiarTamLabel(vistaPrevia[i][1].getBounds(), lVistaPrevia[i][1]);
			for (int j = 0; j < lVistaPrevia[0].length; j++) {
				lVistaPrevia[i][j].setToolTipText(lVistaPrevia[i][j].getText());
			}
		}
	}

	public void accionesAceptar() {
		int cant = Integer.parseInt(Herramientas.conseguirTextoFormatoSinDecimal(tfCantidad.getText()));
		if (cant == 0) {
			Herramientas.abrirPopUp("Advertencia", "La cantidad no puede ser 0");
			return;
		}
		listaConfiteria.agregarConfiteria(tipo, valorUnitario, cant);
		ventanaConfi.getTotalPrevio()
				.setText("Total: $" + Herramientas.formatoSinDecimal(listaConfiteria.getPrecioTotal()));
		ventanaConfi.actualizarColores();
		ventanaConfi.setVisible(true);
		setVisible(false);
	}

	@Override
	public void cambiarMaximizado(boolean esGrande) {
	}
}