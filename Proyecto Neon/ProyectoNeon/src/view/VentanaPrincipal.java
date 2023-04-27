package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Rectangle;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import model.Herramientas;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class VentanaPrincipal extends ParteSuperior {
	private JPanel[] botones;
	private JLabel[] labels;
	private static int defaultButton = 0;
	private Rectangle[] bigBoundsB, smallBoundsB;
	private static String[] opciones = { "Facturar Cine", "Facturar Confitería", "Registrar Cliente", "Buscar cliente",
			"Dar tarjeta", "Recargar tarjeta", "Consolidado de Ventas", "Historial de compra", "Obtener Datos" };
	private static Herramientas.OPCIONES opcion = Herramientas.OPCIONES.CINE;

	public void conFigurarVentana() {
		setSize(834, 500);
		cambiarTitulo("Stellar Cinema", Herramientas.FUENTE_TITULO_DEFAULT);

	}

	public VentanaPrincipal() {
		botones = new JPanel[9];
		labels = new JLabel[9];
		smallBoundsB = new Rectangle[9];
		bigBoundsB = new Rectangle[9];
		int x = 0;
		LineBorder linea = new LineBorder(Herramientas.white);
		for (int j = 0; j < 3; j++) {
			for (int i = 0; i < 3; i++) {
				botones[x] = new JPanel();
				labels[x] = new JLabel(opciones[x]);
				botones[x].setLayout(new BorderLayout(0, 0));
				botones[x].setBorder(linea);
				botones[x].setSize(200, 50);
				botones[x].setLocation(i * 230 + ((getSize().width - 660) / 2),
						j * 80 + (((getSize().height - 31 - 210) / 2) + 31));
				botones[x].setBackground(Herramientas.black);
				botones[x].setCursor(Herramientas.HAND_CURSOR);
				botones[x].addMouseListener(this);

				labels[x].setHorizontalAlignment(SwingConstants.CENTER);
				labels[x].setFont(Herramientas.FUENTE_TITULO_DEFAULT);
				labels[x].setForeground(Herramientas.white);
				botones[x].add(labels[x]);
				contentPane.add(botones[x], BorderLayout.CENTER);
				x++;
			}
		}
		botones[defaultButton].setBackground(Herramientas.white);
		botones[defaultButton].setBorder(null);
		labels[defaultButton].setForeground(Herramientas.black);
	}

	public void cambiarMaximizado(boolean esGrande) {
		for (int i = 0; i < 9; i++) {
			double proporcionX = (Herramientas.SCREEN_SIZE.width + 0.0) / (aux.width + 0.0);
			double proporcionY = (Herramientas.SCREEN_SIZE.height + 0.0) / (aux.height + 0.0);
			if (esGrande) {
				if (bigBoundsB[i] == null) {
					bigBoundsB[i] = new Rectangle((int) (proporcionX * botones[i].getLocation().x),
							(int) (proporcionY * botones[i].getLocation().y),
							(int) (proporcionX * botones[i].getSize().width),
							(int) (proporcionY * botones[i].getSize().height));
				}
				botones[i].setBounds(bigBoundsB[i]);
				labels[i].setFont(labels[i].getFont().deriveFont(labels[i].getFont().getStyle(), 35));
			} else {
				proporcionX = 1 / proporcionX;
				proporcionY = 1 / proporcionY;
				if (smallBoundsB[i] == null) {
					smallBoundsB[i] = new Rectangle((int) (proporcionX * botones[i].getLocation().x),
							(int) (proporcionY * botones[i].getLocation().y),
							(int) (proporcionX * botones[i].getSize().width),
							(int) (proporcionY * botones[i].getSize().height));
				}
				botones[i].setBounds(smallBoundsB[i]);
				labels[i].setFont(labels[i].getFont().deriveFont(labels[i].getFont().getStyle(), 20));
			}
		}
	}

	public void mouseEntered(MouseEvent e) {
		super.mouseEntered(e);
		for (int i = 0; i < 9; i++) {
			if (e.getSource() == botones[i]) {
				if (i == defaultButton) {
					continue;
				}
				pintarBotonDef(false);
				defaultButton = i;
				pintarBotonDef(true);
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		super.keyReleased(e);
	}

	public void keyPressed(KeyEvent e) {
		super.keyPressed(e);
		if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_SPACE) {
			accionarBoton();
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			pintarBotonDef(false);
			if (defaultButton % 3 != 2) {
				defaultButton++;
			} else {
				defaultButton -= 2;
			}
			pintarBotonDef(true);
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			pintarBotonDef(false);
			if (defaultButton % 3 != 0) {
				defaultButton--;
			} else {
				defaultButton += 2;
			}
			pintarBotonDef(true);
		}
		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			pintarBotonDef(false);
			if (defaultButton > 2) {
				defaultButton -= 3;
			} else {
				defaultButton += 6;
			}
			pintarBotonDef(true);
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
			pintarBotonDef(false);
			if (defaultButton <= 5) {
				defaultButton += 3;
			} else {
				defaultButton -= 6;
			}
			pintarBotonDef(true);
		}
		if (e.getKeyCode() == KeyEvent.VK_TAB) {
			pintarBotonDef(false);
			if (!e.isShiftDown()) {
				defaultButton++;
				if (defaultButton == 9)
					defaultButton = 0;
			} else {
				defaultButton--;
				if (defaultButton == -1)
					defaultButton = 8;
			}
			pintarBotonDef(true);
		}
	}

	public void pintarBotonDef(boolean isSelected) {
		if (isSelected) {
			opcion = Herramientas.OPCIONES.values()[defaultButton];
			botones[defaultButton].setBorder(null);
			botones[defaultButton].setBackground(col);
			labels[defaultButton].setForeground(Herramientas.black);
		} else {
			botones[defaultButton].setBackground(Herramientas.black);
			botones[defaultButton].setBorder(new LineBorder(col));
			labels[defaultButton].setForeground(col);
		}
	}

	public void mouseReleased(MouseEvent e) {
		super.mouseReleased(e);
		for (int i = 0; i < 9; i++) {
			if (e.getSource() == botones[i]) {
				if (Herramientas.estaEnRangoDe(botones[i])) {
					accionarBoton();
				}
			}
		}
	}

	public void accionarBoton() {
		if (opcion != Herramientas.OPCIONES.REGISTRAR && opcion != Herramientas.OPCIONES.CONSOLIDADO
				&& opcion != Herramientas.OPCIONES.VER) {
			VentanaTransformer facturarCine = new VentanaTransformer(this, opciones[defaultButton], opcion);
			facturarCine.setVisible(true);
			facturarCine.actualizarColores();
			setVisible(false);
		}
	}

	public void cambioDeColor() {
		LineBorder linea = new LineBorder(col);
		for (int i = 0; i < 9; i++) {
			if (i != defaultButton) {
				botones[i].setBackground(Herramientas.black);
				botones[i].setBorder(linea);
				labels[i].setForeground(col);
			} else {
				botones[i].setBackground(col);
				botones[i].setBorder(null);
				labels[i].setForeground(Herramientas.black);
			}
		}
	}
}
