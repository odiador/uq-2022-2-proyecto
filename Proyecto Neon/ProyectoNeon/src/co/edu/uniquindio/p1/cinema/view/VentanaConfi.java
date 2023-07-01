package co.edu.uniquindio.p1.cinema.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import co.edu.uniquindio.p1.cinema.model.Herramientas;
import co.edu.uniquindio.p1.cinema.objetos.ListadeConfiteria;
import co.edu.uniquindio.p1.cinema.threads.HiloCargando;

public class VentanaConfi extends ParteSuperior {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel paneles[][], panelConfiteria, vistaPrevia, botonesClutch[], panelClutch;
	public String titulos[][] = { { "Crispetas", "Bebidas", "Comidas Rápidas", "Comidas Rápidas", "Combos" },
			{ "Crispeta pequeña", "Gaseosa pequeña", "Hamburguesa", "Empanada", "Combo 1" },
			{ "Crispeta mediana", "Gaseosa mediana", "Perro", "Helado", "Combo 2" },
			{ "Crispeta grande", "Gaseosa grande", "Nachos", "Pizza", "Combo 3" },
			{ "Crispeta familiar", "Té", "Sándwich", "Chocolatina Jet", "Combo 4" } };
	public double precios[][] = { { 0, 0, 0, 0, 0 }, { 8000, 6000, 11500, 6000, 14000 },
			{ 11000, 8000, 11000, 7000, 25000 }, { 13000, 10000, 10500, 11500, 33500 },
			{ 20000, 7000, 5000, 1500, 49900 } };
	private JLabel labels[][], vistaPreviaL, imagenPrevia, precioPrevio, totalPrevio, tipoPrevio, clutchLabels[];
	private int pI = 0, pJ = 0;
	private ImageIcon imagenes[][];
	private ListadeConfiteria listaConfiteria;
	private VentanaPrincipal ventanaPrincipal;

	public static void main(String[] args) {
		new VentanaConfi(null).setVisible(true);
	}

	public VentanaConfi(VentanaPrincipal ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
		listaConfiteria = new ListadeConfiteria();
		setAlwaysBig(true);
		panelConfiteria = new JPanel();
		panelConfiteria.setSize(getBounds().width - 400, getBounds().height - panelSuperior.getHeight() - 100);
		panelConfiteria.setLocation(30, panelSuperior.getHeight() + 50);
		panelConfiteria.setBackground(Herramientas.BLACK);
		panelConfiteria.setLayout(new GridLayout(5, 5, 10, 10));

		contentPane.add(panelConfiteria);
		paneles = new JPanel[5][5];
		labels = new JLabel[5][5];
		LineBorder linea = new LineBorder(col), lineax10 = new LineBorder(col, 10);
		imagenes = new ImageIcon[5][5];

		vistaPrevia = new JPanel();
		vistaPrevia.setBounds(panelConfiteria.getX() + panelConfiteria.getWidth() + 20, panelConfiteria.getY(),
				getWidth() - panelConfiteria.getX() - panelConfiteria.getWidth() - 40, panelConfiteria.getHeight());
		vistaPrevia.setLayout(null);
		vistaPrevia.setBackground(Herramientas.BLACK);
		contentPane.add(vistaPrevia);

		vistaPreviaL = new JLabel(Herramientas.obtenerLabelCentrado("Vista Previa", "center"));
		vistaPreviaL.setBorder(linea);
		vistaPreviaL.setForeground(Herramientas.WHITE);
		vistaPreviaL.setBounds(0, 0, vistaPrevia.getWidth(), 50);
		vistaPreviaL.setFont(Herramientas.FUENTE_TITULO_DEFAULT.deriveFont(40f));
		vistaPreviaL.setHorizontalAlignment(0);
		vistaPrevia.add(vistaPreviaL);

		tipoPrevio = new JLabel(Herramientas.obtenerLabelCentrado("Crispeta pequeña", "center"));
		tipoPrevio.setText("Crispeta pequeña");
		tipoPrevio.setText(titulos[pI + 1][pJ]);
		tipoPrevio.setBorder(linea);
		tipoPrevio.setForeground(Herramientas.WHITE);
		tipoPrevio.setBounds(0, vistaPreviaL.getHeight(), vistaPrevia.getWidth(), 50);
		tipoPrevio.setFont(Herramientas.FUENTE_TITULO_DEFAULT.deriveFont(40f));
		tipoPrevio.setHorizontalAlignment(0);
		vistaPrevia.add(tipoPrevio);

		imagenPrevia = new JLabel();
		imagenPrevia.setBounds(0, tipoPrevio.getHeight() + tipoPrevio.getY(), vistaPrevia.getWidth(),
				vistaPrevia.getWidth());
		imagenPrevia.setHorizontalAlignment(0);
		imagenPrevia.setBorder(linea);
		vistaPrevia.add(imagenPrevia);

		precioPrevio = new JLabel();
		precioPrevio.setText("Precio: $" + Herramientas.formatoSinDecimal(precios[1][0]));
		precioPrevio.setForeground(Herramientas.WHITE);
		precioPrevio.setHorizontalAlignment(0);
		precioPrevio.setFont(Herramientas.FUENTE_TITULO_DEFAULT.deriveFont(35f));
		precioPrevio.setBounds(0, imagenPrevia.getHeight() + imagenPrevia.getY(), vistaPrevia.getWidth(), 45);
		precioPrevio.setBorder(linea);
		vistaPrevia.add(precioPrevio);

		totalPrevio = new JLabel();
		getTotalPrevio().setText("Total: $" + Herramientas.formatoSinDecimal(listaConfiteria.getPrecioTotal()));
		getTotalPrevio().setForeground(Herramientas.WHITE);
		getTotalPrevio().setHorizontalAlignment(0);
		getTotalPrevio().setFont(Herramientas.FUENTE_TITULO_DEFAULT.deriveFont(35f));
		getTotalPrevio().setBounds(0, precioPrevio.getHeight() + precioPrevio.getY(), vistaPrevia.getWidth(), 45);
		getTotalPrevio().setBorder(linea);
		vistaPrevia.add(getTotalPrevio());
		vistaPrevia.setSize(vistaPrevia.getSize().width, getTotalPrevio().getHeight() + getTotalPrevio().getY());

		panelClutch = new JPanel();
		panelClutch.setBackground(Herramientas.BLACK);
		panelClutch.setBounds(vistaPrevia.getX() + 40, vistaPrevia.getY() + vistaPrevia.getHeight() + 5,
				vistaPrevia.getWidth() - 80, panelConfiteria.getHeight() - vistaPrevia.getHeight() - 10);
		contentPane.add(panelClutch);
		panelClutch.setLayout(new GridLayout(3, 1, 5, 5));
		botonesClutch = new JPanel[3];
		clutchLabels = new JLabel[3];
		String textos[] = { "Ver Carrito", "Aceptar", "Volver" };

		for (int i = 0; i < botonesClutch.length; i++) {
			clutchLabels[i] = new JLabel(textos[i]);
			clutchLabels[i].setFont(Herramientas.FUENTE_TITULO_DEFAULT);
			clutchLabels[i].setHorizontalAlignment(0);
			clutchLabels[i].setForeground(col);
			botonesClutch[i] = new JPanel();
			botonesClutch[i].setLayout(new BorderLayout(0, 0));
			botonesClutch[i].setCursor(Herramientas.HAND_CURSOR);
			botonesClutch[i].setBackground(Herramientas.BLACK);
			botonesClutch[i].addMouseListener(this);

			botonesClutch[i].setBorder(linea);
			botonesClutch[i].add(clutchLabels[i], "Center");
			panelClutch.add(botonesClutch[i]);
		}
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {

				imagenes[i][j] = Herramientas.escalarImagen(Herramientas.getImage(titulos[i][j]),
						imagenPrevia.getWidth() - 20, imagenPrevia.getHeight() - 20, Herramientas.SCALE_SMOOTH);

				paneles[i][j] = new JPanel();
				paneles[i][j].setBackground(Herramientas.black);
				paneles[i][j].setLayout(new BorderLayout(0, 0));
				panelConfiteria.add(paneles[i][j]);

				labels[i][j] = new JLabel(Herramientas.obtenerLabelCentrado(titulos[i][j], "center"));
				labels[i][j].setHorizontalAlignment(0);
				labels[i][j].setForeground(col);
				labels[i][j].setFont(Herramientas.FUENTE_COOLVETICA.deriveFont(38f));
				if (i == 0) {
					paneles[i][j].setBorder(lineax10);
				} else {
					paneles[i][j].setBorder(linea);
					paneles[i][j].setCursor(Herramientas.HAND_CURSOR);
					paneles[i][j].addMouseListener(this);

				}
				paneles[i][j].add(labels[i][j], "Center");
			}
		}
		pintarLibres(true);

		imagenPrevia.setIcon(imagenes[1][0]);
		actualizarColores();
	}

	public void conFigurarVentana() {
		setSize(1000, 300);
		cambiarTitulo("", Herramientas.FUENTE_TITULO_DEFAULT);
		setTitulo("Ventana Contifería");
	}

	public void cambioDeColor() {
		LineBorder linea = new LineBorder(col), lineax10 = new LineBorder(col, 10);
		tipoPrevio.setBorder(linea);
		tipoPrevio.setForeground(col);
		precioPrevio.setForeground(col);
		precioPrevio.setBorder(linea);
		imagenPrevia.setBorder(linea);
		vistaPreviaL.setForeground(col);
		vistaPreviaL.setBorder(linea);
		totalPrevio.setBorder(linea);
		totalPrevio.setForeground(col);
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				labels[i][j].setForeground(col);
				if (i == 0) {
					paneles[i][j].setBorder(lineax10);
				} else {
					paneles[i][j].setBorder(linea);
				}
			}
		}
		for (int i = 0; i < 3; i++) {
			botonesClutch[i].setBackground(Herramientas.BLACK);
			botonesClutch[i].setBorder(linea);
			clutchLabels[i].setForeground(col);
		}
		pintarLibres(true);
	}

	public void cambiarMaximizado(boolean esGrande) {
	}

	public void keyPressed(KeyEvent e) {
		super.keyPressed(e);
		if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_SPACE) {
			pasaraCantidad();
		}
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			ventanaPrincipal.actualizarColores();
			ventanaPrincipal.setVisible(true);
			setVisible(false);
		}
		if (e.getKeyCode() == KeyEvent.VK_INSERT) {
			pasaraCarrito();
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			pintarLibres(false);
			pJ++;
			if (pJ >= 5)
				pJ = 0;
			pintarLibres(true);
			actualizarPrevia();
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			pintarLibres(false);
			pJ--;
			if (pJ < 0)
				pJ = 4;
			pintarLibres(true);
			actualizarPrevia();
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			pintarLibres(false);
			pI--;
			if (pI < 0)
				pI = 3;
			pintarLibres(true);
			actualizarPrevia();
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			pintarLibres(false);
			pI++;
			if (pI >= 4)
				pI = 0;
			pintarLibres(true);
			actualizarPrevia();
		}
		if (e.getKeyCode() == KeyEvent.VK_TAB) {
			pintarLibres(false);
			if (!e.isShiftDown()) {
				pJ++;
				if (pJ >= 5) {
					pJ = 0;
					pI++;
				}
				if (pI >= 4)
					pI = 0;
			} else {
				pJ--;
				if (pJ < 0) {
					pJ = 4;
					pI--;
				}
				if (pI < 0)
					pI = 3;
			}
			pintarLibres(true);
			actualizarPrevia();
		}
	}

	public void mouseReleased(MouseEvent e) {
		super.mouseReleased(e);
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (e.getSource() == paneles[i][j]) {
					Rectangle bounds = paneles[i][j].getBounds();
					bounds.x += panelConfiteria.getX();
					bounds.y += panelConfiteria.getY();
					if (Herramientas.estaEnRangoDe(paneles[i][j])) {
						pasaraCantidad();
					}
				}
			}
		}
		if (e.getSource() == botonesClutch[0]) {
			Rectangle bounds = botonesClutch[0].getBounds();
			bounds.x += panelClutch.getX();
			bounds.y += panelClutch.getY();
			if (Herramientas.estaEnRangoDe(botonesClutch[0])) {
				pasaraCarrito();
			}

		}
		if (e.getSource() == botonesClutch[1]) {
			Rectangle bounds = botonesClutch[1].getBounds();
			bounds.x += panelClutch.getX();
			bounds.y += panelClutch.getY();
			if (Herramientas.estaEnRangoDe(botonesClutch[1])) {
				pasaraCantidad();
			}
		}
		if (e.getSource() == botonesClutch[2]) {
			Rectangle bounds = botonesClutch[2].getBounds();
			bounds.x += panelClutch.getX();
			bounds.y += panelClutch.getY();
			if (Herramientas.estaEnRangoDe(botonesClutch[2])) {
				ventanaPrincipal.actualizarColores();
				ventanaPrincipal.setVisible(true);
				setVisible(false);
			}
		}
	}

	public void pintarLibres(boolean estaLibre) {
		if (estaLibre) {
			paneles[pI + 1][pJ].setBackground(col);
			labels[pI + 1][pJ].setForeground(Herramientas.black);
			paneles[pI + 1][pJ].setBorder(null);
		} else {
			labels[pI + 1][pJ].setForeground(col);
			paneles[pI + 1][pJ].setBackground(Herramientas.black);
			paneles[pI + 1][pJ].setBorder(new LineBorder(col));
		}
	}

	public void autoDestruccion() {
		System.exit(0);
	}

	public void mouseExited(MouseEvent e) {
		super.mouseExited(e);
		LineBorder linea = new LineBorder(col);
		for (int i = 0; i < 3; i++) {
			if (e.getSource() == botonesClutch[i]) {
				botonesClutch[i].setBackground(Herramientas.BLACK);
				botonesClutch[i].setBorder(linea);
				clutchLabels[i].setForeground(col);
			}
		}

	}

	public void mouseEntered(MouseEvent e) {
		super.mouseEntered(e);
		for (int i = 1; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (e.getSource() == paneles[i][j]) {
					pintarLibres(false);
					pI = i - 1;
					pJ = j;
					pintarLibres(true);
					actualizarPrevia();
				}
			}
		}
		for (int i = 0; i < 3; i++) {
			if (e.getSource() == botonesClutch[i]) {
				botonesClutch[i].setBackground(col);
				botonesClutch[i].setBorder(null);
				clutchLabels[i].setForeground(Herramientas.BLACK);
			}
		}
	}

	public void actualizarPrevia() {
		imagenPrevia.setIcon(imagenes[pI + 1][pJ]);
		precioPrevio.setText("Precio: $" + Herramientas.formatoSinDecimal(precios[pI + 1][pJ]));
		tipoPrevio.setText(titulos[pI + 1][pJ]);
	}

	public void pasaraCantidad() {
		String tipo = titulos[pI + 1][pJ];
		double valorUnitario = precios[pI + 1][pJ];
		VentanaCantidadConfi miCantidadConfi = new VentanaCantidadConfi(this, tipo, valorUnitario, listaConfiteria);
		miCantidadConfi.actualizarColores();
		miCantidadConfi.setVisible(true);
		setVisible(false);
	}

	public void pasaraCarrito() {
		HiloCargando hilo = new HiloCargando(botonesClutch[0], clutchLabels[0], "Cargando");
		interrumpirHiloColor();
		hilo.start();
		if (!listaConfiteria.estaVacia()) {
			VentanaCarrito vCarri = new VentanaCarrito(this, listaConfiteria);
			vCarri.actualizarColores();
			vCarri.setVisible(true);
			setVisible(false);
		} else {
			Herramientas.abrirPopUp("Advertencia", "El Carrito está vacío");
		}
		hilo.interrupt();
	}

	/**
	 * @return the totalPrevio
	 */
	public JLabel getTotalPrevio() {
		return totalPrevio;
	}
}