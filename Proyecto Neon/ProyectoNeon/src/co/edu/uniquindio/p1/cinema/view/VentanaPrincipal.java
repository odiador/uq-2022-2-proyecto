package co.edu.uniquindio.p1.cinema.view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import co.edu.uniquindio.p1.cinema.objetos.CLabel;
import co.edu.uniquindio.p1.cinema.services.ColorManagement;
import co.edu.uniquindio.p1.cinema.services.Herramientas;

public class VentanaPrincipal extends Template {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CLabel[] labels;
	private static String[] opciones = { "Facturar Cine", "Facturar Confitería", "Registrar Cliente", "Buscar cliente",
			"Dar tarjeta", "Recargar tarjeta", "Consolidado de Ventas", "Historial de compra", "Obtener Datos" };
	private static Herramientas.OPCIONES opcion = Herramientas.OPCIONES.CINE;

	private static VentanaPrincipal instance;

	public static VentanaPrincipal getInstance() {
		return instance;
	}

	public void conFigurarVentana() {
		setSize(834, 500);
		setTitle("Stellar Cinema");

	}

	public VentanaPrincipal(int width, int height) {
		super(width, height);
		instance = this;
		JPanel pane = new JPanel(new GridLayout(3, 3, 50, 50));
		pane.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
		pane.setBackground(Color.black);
		labels = new CLabel[9];
		int x = 0;
		for (int j = 0; j < 3; j++) {
			for (int i = 0; i < 3; i++) {
				labels[x] = new CLabel(opciones[x]);
				labels[x].setBorder(new LineBorder(ColorManagement.rgbColor));
				labels[x].setForeground(ColorManagement.rgbColor);
				labels[x].setFont(Herramientas.FUENTE_TITULO_DEFAULT);
				labels[x].setHorizontalAlignment(0);
				labels[x].setOpaque(true);
				labels[x].setBackground(Color.black);
				ToolKit.configureButtonHover(labels[x]);
				final int option = x;
				labels[x].setOnClicAction(() -> changeWindow(option));
				pane.add(labels[x]);
				x++;
			}
		}
		setCenter(pane);
	}

	private void changeWindow(int option) {
		if (opcion != Herramientas.OPCIONES.REGISTRAR && opcion != Herramientas.OPCIONES.CONSOLIDADO
				&& opcion != Herramientas.OPCIONES.VER) {
			VentanaTransformer facturarCine = new VentanaTransformer(opciones[option], opcion);
			facturarCine.setVisible(true);
			facturarCine.actualizarColores();
			setVisible(false);
		}
	}

	@Override
	public void updateStellarColors() {
		for (int i = 0; i < 9; i++) {
			labels[i].setBackground(Herramientas.black);
			labels[i].setBorder(new LineBorder(ColorManagement.rgbColor));
			labels[i].setForeground(ColorManagement.rgbColor);
		}
	}
}
