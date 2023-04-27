package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.border.LineBorder;

import model.Herramientas;
import objetos.ListadeConfiteria;
import threads.HiloCargando;
import threads.HiloColor;

public class VentanaCarrito extends ParteSuperior {
	private ListadeConfiteria listaConfiteria;
	private JLabel[] lImagenes, lValUnitarios, lTipos, lSuperiores, lCantidades, lPrecios;
	private JLabel lHeaderTotal, lTotal, lVolver;
	private JSeparator separadores[], separadoresVerticales[];
	private JPanel panelCarrito, panelArriba, panelTotal, volver, aceptar;
	private static LineBorder linea = new LineBorder(Herramientas.white);
	private static final String textoSuperior[] = { "Imagen", "Tipo", "Valor Unitario", "Cantidad", "Precio" };
	private JScrollPane scroll;
	private String cadPrecio;
	private VentanaConfi ventanaConfi;

	public static void main(String[] args) {
		ListadeConfiteria x = new ListadeConfiteria();
		x.agregarConfiteria("Sándwich", 1000d, 3);
		x.agregarConfiteria("Gaseosa grande", 10000d, 2);
		x.agregarConfiteria("Crispeta grande", 2000d, 2);
		x.agregarConfiteria("Crispeta mediana", 2000d, 2);
		x.agregarConfiteria("Crispeta pequeña", 2000d, 2);
		new VentanaCarrito(null, x).setVisible(true);
	}

	public VentanaCarrito(VentanaConfi ventanaConfiteria, ListadeConfiteria listaConfiteria) {
		setFrame(ventanaConfiteria);
		setListaConfiteria(listaConfiteria);
		setAlwaysBig(true);
		setStellarVisible(false);
		Rectangle auxBounds = panelSuperior.getBounds();
		auxBounds.x += 40;
		auxBounds.width -= 80;
		auxBounds.y = auxBounds.height + 20;
		auxBounds.height = 40;

		panelArriba = new JPanel();
		panelArriba.setBackground(Herramientas.white);
		panelArriba.setBounds(auxBounds);
		panelArriba.setLayout(new GridLayout(1, 4, 1, 1));

		auxBounds.y += panelArriba.getHeight();
		auxBounds.height = getHeight() - panelSuperior.getHeight() - panelArriba.getHeight() - 200;

		panelCarrito = new JPanel();
		panelCarrito.setBackground(Herramientas.black);
		panelCarrito.setBorder(linea);
		panelCarrito.setBounds(auxBounds);
		panelCarrito.setLayout(null);
		lSuperiores = new JLabel[textoSuperior.length];
		separadoresVerticales = new JSeparator[textoSuperior.length - 1];

		int cantidadCar = listaConfiteria.getCarrito().size();
		int ancho = (panelArriba.getWidth() / textoSuperior.length) - 1;
		lImagenes = new JLabel[cantidadCar];
		lValUnitarios = new JLabel[cantidadCar];
		lTipos = new JLabel[cantidadCar];
		lCantidades = new JLabel[cantidadCar];
		lPrecios = new JLabel[cantidadCar];
		separadores = new JSeparator[cantidadCar];

		for (int i = 0; i < cantidadCar; i++) {
			String tipo = listaConfiteria.getCarrito().get(i).getTipoDeCompra();
			String cantidad = Herramientas.formatoSinDecimal(listaConfiteria.getCarrito().get(i).getCantidad());
			String valorUnitario = Herramientas.formatoSinDecimal(listaConfiteria.getCarrito().get(i).getValUnitario());
			String precio = Herramientas.formatoSinDecimal(listaConfiteria.getCarrito().get(i).getPrecio());

			lImagenes[i] = new JLabel();
			lImagenes[i].setHorizontalAlignment(0);

			lTipos[i] = new JLabel(Herramientas.obtenerLabelCentrado(tipo, "center"));
			lTipos[i].setFont(Herramientas.FUENTE_COOLVETICA.deriveFont(30f));
			lTipos[i].setForeground(Herramientas.white);
			lTipos[i].setHorizontalAlignment(0);

			lValUnitarios[i] = new JLabel(Herramientas.obtenerLabelCentrado("$" + valorUnitario, "center"));
			lValUnitarios[i].setForeground(Herramientas.white);
			lValUnitarios[i].setFont(Herramientas.FUENTE_COOLVETICA.deriveFont(30f));
			lValUnitarios[i].setHorizontalAlignment(0);

			lCantidades[i] = new JLabel(Herramientas.obtenerLabelCentrado(cantidad, "center"));
			lCantidades[i].setForeground(Herramientas.white);
			lCantidades[i].setFont(Herramientas.FUENTE_COOLVETICA.deriveFont(30f));
			lCantidades[i].setHorizontalAlignment(0);

			lPrecios[i] = new JLabel(Herramientas.obtenerLabelCentrado("$" + precio, "center"));
			lPrecios[i].setForeground(Herramientas.white);
			lPrecios[i].setFont(Herramientas.FUENTE_COOLVETICA.deriveFont(30f));
			lPrecios[i].setHorizontalAlignment(0);

			separadores[i] = new JSeparator();
			separadores[i].setBackground(Herramientas.black);
			separadores[i].setForeground(Herramientas.white);
			panelCarrito.add(lImagenes[i]);
			panelCarrito.add(lPrecios[i]);
			panelCarrito.add(lTipos[i]);
			panelCarrito.add(lValUnitarios[i]);
			panelCarrito.add(lCantidades[i]);
			panelCarrito.add(separadores[i]);

			int posicionX = 1;
			int altura = 199;
			lImagenes[i].setBounds(posicionX, 1 + 200 * i, ancho, altura);
			lTipos[i].setBounds(posicionX += ancho + 1, 1 + 201 * i, ancho, altura);
			lValUnitarios[i].setBounds(posicionX += ancho + 1, 1 + 201 * i, ancho, altura);
			lCantidades[i].setBounds(posicionX += ancho + 1, 1 + 201 * i, ancho, altura);
			lPrecios[i].setBounds(posicionX += ancho + 1, 1 + 201 * i, ancho, altura);
			separadores[i].setBounds(0, 1 + 200 * (i + 1), getWidth(), 1);

			lImagenes[i].setIcon(Herramientas.escalarImagen(Herramientas.getImage(tipo), lImagenes[i].getHeight(),
					lImagenes[i].getHeight(), Herramientas.SCALE_SMOOTH));
		}
		for (int i = 0; i < textoSuperior.length; i++) {
			lSuperiores[i] = new JLabel(Herramientas.obtenerLabelCentrado(textoSuperior[i], "center"));
			lSuperiores[i].setForeground(Herramientas.black);
			lSuperiores[i].setFont(Herramientas.FUENTE_COOLVETICA);
			lSuperiores[i].setHorizontalAlignment(0);
			panelArriba.add(lSuperiores[i]);

			if (i == textoSuperior.length - 1)
				continue;
			separadoresVerticales[i] = new JSeparator(Herramientas.ORIENTACION_VERTICAL);
			separadoresVerticales[i].setBackground(Herramientas.black);
			separadoresVerticales[i].setForeground(Herramientas.white);
			panelCarrito.add(separadoresVerticales[i]);

			separadoresVerticales[i].setBounds(panelArriba.getWidth() * (i + 1) / textoSuperior.length, 0, 1,
					lImagenes[cantidadCar - 1].getY() + lImagenes[cantidadCar - 1].getHeight() + 1);
		}
		panelCarrito.setPreferredSize(new Dimension(panelCarrito.getWidth(), separadoresVerticales[0].getHeight()));

		scroll = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setBorder(linea);
		scroll.getViewport().add(panelCarrito);
		auxBounds = panelCarrito.getBounds();
		auxBounds.width += 17;
		scroll.setBounds(auxBounds);

		panelTotal = new JPanel();
		panelTotal.setLayout(new BorderLayout());
		panelTotal.setBackground(Herramientas.white);

		auxBounds.y += auxBounds.height;
		auxBounds.width = ancho * 4 + 5;
		auxBounds.height = 50;
		panelTotal.setBounds(auxBounds);

		lHeaderTotal = new JLabel("Total");
		lHeaderTotal.setFont(Herramientas.FUENTE_COOLVETICA.deriveFont(30f));
		lHeaderTotal.setHorizontalAlignment(0);
		lHeaderTotal.setForeground(Herramientas.black);
		panelTotal.add(lHeaderTotal, "Center");

		String total = Herramientas.formatoSinDecimal(listaConfiteria.getPrecioTotal());
		aceptar = new JPanel();
		aceptar.setBorder(linea);
		aceptar.setCursor(Herramientas.HAND_CURSOR);
		aceptar.setBackground(Herramientas.black);
		aceptar.addMouseListener(this);

		auxBounds.x += auxBounds.width;
		auxBounds.width = ancho;
		aceptar.setBounds(auxBounds);

		lTotal = new JLabel(Herramientas.obtenerLabelCentrado("$" + total, "center"));
		lTotal.setFont(Herramientas.FUENTE_COOLVETICA.deriveFont(30f));
		lTotal.setHorizontalAlignment(0);
		lTotal.setForeground(Herramientas.white);

		aceptar.add(lTotal);

		volver = new JPanel();
		volver.setBackground(Herramientas.black);
		volver.setBorder(linea);
		volver.setCursor(Herramientas.HAND_CURSOR);
		volver.addMouseListener(this);
		auxBounds.y += auxBounds.height;
		volver.setBounds(auxBounds);

		lVolver = new JLabel(Herramientas.obtenerLabelCentrado("Volver", "center"));
		lVolver.setFont(Herramientas.FUENTE_COOLVETICA.deriveFont(30f));
		lVolver.setHorizontalAlignment(0);
		lVolver.setForeground(Herramientas.white);

		volver.add(lVolver);

		contentPane.add(aceptar);
		contentPane.add(panelTotal);
		contentPane.add(panelArriba);
		contentPane.add(scroll);
		contentPane.add(volver);
		actualizarColores();
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_SPACE) {
			accionesPagar();

		}
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			ventanaConfi.actualizarColores();
			ventanaConfi.setVisible(true);
			setVisible(false);
		}
	}

	public void mouseEntered(MouseEvent e) {
		super.mouseEntered(e);
		if (e.getSource() == getLabelTitulo()) {
			hiloColor = new HiloColor(this, getLabelTitulo());
			hiloColor.start();
		}
		if (e.getSource() == aceptar) {
			aceptar.setBackground(col);
			lTotal.setForeground(Herramientas.black);
			cadPrecio = lTotal.getText();
			lTotal.setText("Pagar");
		}
		if (e.getSource() == volver) {
			volver.setBackground(col);
			lVolver.setForeground(Herramientas.black);
		}
	}

	public void mouseReleased(MouseEvent e) {
		super.mouseReleased(e);
		if (e.getSource() == volver) {
			if (Herramientas.estaEnRangoDe(volver)) {
				ventanaConfi.actualizarColores();
				ventanaConfi.setVisible(true);
				setVisible(false);
			}
		}
		if (e.getSource() == aceptar) {
			if (Herramientas.estaEnRangoDe(aceptar)) {
				accionesPagar();
			}
		}
	}

	public void mouseExited(MouseEvent e) {
		super.mouseExited(e);
		if (e.getSource() == getLabelTitulo()) {
			hiloColor.interrupt();
			cambiarColor();
			cambioDeColor();
		}
		if (e.getSource() == aceptar) {
			aceptar.setBackground(Herramientas.black);
			lTotal.setForeground(col);
			lTotal.setText(cadPrecio);
		}
		if (e.getSource() == volver) {
			volver.setBackground(Herramientas.black);
			lVolver.setForeground(col);
		}
	}

	public void conFigurarVentana() {
		setSize(1000, 300);
		cambiarTitulo("", Herramientas.FUENTE_TITULO_DEFAULT);
		setTitulo("Carrito");

	}

	public void cambioDeColor() {
		linea = new LineBorder(col);
		panelCarrito.setBorder(linea);
		panelArriba.setBackground(col);
		scroll.setBorder(linea);
		panelTotal.setBackground(col);
		lTotal.setForeground(col);
		aceptar.setBorder(linea);
		volver.setBorder(linea);
		lVolver.setForeground(col);
		for (int i = 0; i < separadoresVerticales.length; i++) {
			separadoresVerticales[i].setForeground(col);
		}
		for (int i = 0; i < listaConfiteria.getCarrito().size(); i++) {
			lTipos[i].setForeground(col);
			lValUnitarios[i].setForeground(col);
			lPrecios[i].setForeground(col);
			lCantidades[i].setForeground(col);
			separadores[i].setForeground(col);

		}
	}

	@Override
	public void cambiarMaximizado(boolean esGrande) {
	}

	@Override
	public void autoDestruccion() {
		System.exit(0);
	}

	public void accionesPagar() {
		HiloCargando hilo = new HiloCargando(aceptar, lTotal, "Cargando");
		interrumpirHiloColor();
		hilo.start();

		VentanaPagoConfit vPagos = new VentanaPagoConfit(listaConfiteria);
		vPagos.setVisible(true);

		hilo.interrupt();
		setVisible(false);
	}

	private void setFrame(VentanaConfi frame) {
		this.ventanaConfi = frame;
	}

	private void setListaConfiteria(ListadeConfiteria listaConfiteria) {
		this.listaConfiteria = listaConfiteria;
	}

}