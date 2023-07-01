package co.edu.uniquindio.p1.cinema.view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import co.edu.uniquindio.p1.cinema.model.Herramientas;
import co.edu.uniquindio.p1.cinema.threads.HiloColor;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import javax.swing.JSeparator;

/**
 * Genera la barra superior de la ventana de manera personalizada <br>
 * Con un método necesario, el cual es {@link #conFigurarVentana()}
 *
 * @see #conFigurarVentana
 *
 */
public abstract class ParteSuperior extends JFrame implements MouseListener, MouseMotionListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JPanel contentPane, panelSuperior;
	private JPanel panelCuadr, panelArrastre, panelCerrar, panelMinim;
	private JLabel stellarCinema, lblCerrar, lblMinim, lblCuadr, lblTitul;
	private JButton bNavegacion;

	public HiloColor hiloColor;
	private boolean estaMaxi = false, sePuedeMaxi = true, isPopUp = false, arrastrable = true;
	public Rectangle aux;
	private int posX, posY, posXInicial, posYInicial;
	private JSeparator separadorSup;
	private int sentidoColor = 0;
	public Color col = Herramientas.white;
	private static boolean yaSeCambiodeColor = false;

	private ArrayList<JFormattedTextField> tfArr;

	public ParteSuperior() {
		tfArr = new ArrayList<JFormattedTextField>();

		contentPane = new JPanel();
		conFigurarVentana();
		configurarPanelPrincipal(contentPane);
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);

		panelSuperior = new JPanel();
		panelSuperior.setBackground(Herramientas.black);
		panelSuperior.setLocation(1, 1);
		panelSuperior.setLayout(null);
		contentPane.add(panelSuperior);

		separadorSup = new JSeparator();
		separadorSup.setBackground(Herramientas.black);
		separadorSup.setForeground(Herramientas.white);
		separadorSup.setBounds(0, 30, getBounds().width, 1);
		panelSuperior.add(separadorSup);

		panelArrastre = new JPanel();
		panelArrastre.setBounds(150, 0, getBounds().width - 152, 30);
		panelArrastre.addMouseMotionListener(this);
		panelArrastre.addMouseListener(this);
		panelArrastre.setBackground(Herramientas.black);
		panelArrastre.setLayout(null);
		panelSuperior.add(panelArrastre);

		lblTitul.setForeground(Herramientas.white);
		lblTitul.setBounds(30, 0, 400, 30);
		lblTitul.addMouseListener(this);
		lblTitul.addMouseMotionListener(this);
		panelArrastre.add(lblTitul);

		panelMinim = new JPanel();
		panelMinim.setBounds(50, 0, 50, 30);
		panelMinim.setBackground(Herramientas.black);
		panelMinim.addMouseListener(this);
		panelMinim.setLayout(null);
		panelSuperior.add(panelMinim);

		lblMinim = new JLabel("0");
		lblMinim.setBounds(18, 7, 14, 19);
		lblMinim.setHorizontalAlignment(SwingConstants.CENTER);
		lblMinim.setFont(Herramientas.FUENTE_PIRANA);
		lblMinim.setForeground(Herramientas.white);
		panelMinim.add(lblMinim);

		panelCuadr = new JPanel();
		panelCuadr.setBounds(100, 0, 50, 30);
		panelCuadr.addMouseListener(this);
		panelCuadr.setBackground(Herramientas.black);
		panelSuperior.add(panelCuadr);
		panelCuadr.setLayout(null);

		lblCuadr = new JLabel("1");
		lblCuadr.setBounds(18, 7, 14, 19);
		lblCuadr.setHorizontalAlignment(SwingConstants.CENTER);
		lblCuadr.setFont(Herramientas.FUENTE_PIRANA);
		lblCuadr.setForeground(Herramientas.white);
		panelCuadr.add(lblCuadr);

		panelCerrar = new JPanel();
		panelCerrar.setBounds(0, 0, 50, 30);
		panelCerrar.setBackground(Herramientas.black);
		panelCerrar.addMouseListener(this);
		panelSuperior.add(panelCerrar);
		panelCerrar.setLayout(null);

		lblCerrar = new JLabel("X");
		lblCerrar.setBounds(18, 7, 14, 19);
		lblCerrar.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCerrar.setHorizontalAlignment(SwingConstants.CENTER);
		lblCerrar.setFont(Herramientas.FUENTE_PIRANA);
		lblCerrar.setForeground(Herramientas.white);
		panelCerrar.add(lblCerrar);

		stellarCinema = new JLabel("STELLAR CINEMA");
		int tam = (int) ((getBounds().width) * 50.0) / 834;
		if (tam > 40)
			tam = 40;
		stellarCinema.setFont(crearFuente("fonts/PAC-FONT.ttf", tam));
		stellarCinema.setBounds(1, 45, getBounds().width - 2, tam + 10);
		stellarCinema.setForeground(Herramientas.white);
		stellarCinema.setHorizontalAlignment(SwingConstants.CENTER);
		stellarCinema.addMouseListener(this);
		panelSuperior.add(stellarCinema);

		bNavegacion = new JButton("");
		bNavegacion.setBounds(getBounds().width, getBounds().height, 0, 0);
		bNavegacion.addKeyListener(this);
		bNavegacion.addMouseListener(this);
		bNavegacion.setFocusTraversalKeysEnabled(false);
		panelSuperior.add(bNavegacion);
		panelSuperior.setSize(getBounds().width - 2, stellarCinema.getBounds().y + stellarCinema.getBounds().height);

		UIManager.put("ToolTip.background", Herramientas.BLACK);
		UIManager.put("ToolTip.foreground", Herramientas.WHITE);
		UIManager.put("ToolTip.font", Herramientas.FUENTE_TITULO_DEFAULT);

	}

	public void setDefaultNavigation(boolean opt) {
		bNavegacion.setEnabled(opt);
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		if (e.getSource() == panelArrastre || e.getSource() == lblTitul) {
			posXInicial = getBounds().x;
			posYInicial = getBounds().y;
			if (e.getSource() == lblTitul)
				posX = e.getX() + panelArrastre.getX() + lblTitul.getX();
			if (e.getSource() == panelArrastre)
				posX = e.getX() + panelArrastre.getBounds().x;
			posY = e.getY();
		}
	}

	public void mouseReleased(MouseEvent e) {
		if (e.getSource() == panelCerrar) {
			if (Herramientas.estaEnRangoDe(panelCerrar)) {
				autoDestruccion();
			}
		}
		if (e.getSource() == panelMinim) {
			if (Herramientas.estaEnRangoDe(panelMinim)) {
				setState(ICONIFIED);
			}
		}
		if (e.getSource() == panelCuadr) {
			if (Herramientas.estaEnRangoDe(panelCuadr)) {
				cambiarEstado();
			}
		}
		if (e.getSource() == panelArrastre || e.getSource() == lblTitul) {
			if (Herramientas.estaEnRangoDe(panelArrastre)) {
				if (sePuedeMaxi) {
					if (e.getLocationOnScreen().y <= 0) {
						if (!estaMaxi) {
							cambiarEstado();
							aux.x = posXInicial;
							aux.y = posYInicial;
						}
					}
				}
			}
		}
		if (isPopUp) {
			if (e.getSource() != panelArrastre && e.getSource() != lblTitul) {
				setVisible(false);
			}
		}
	}

	public JLabel getLabelTitulo() {
		return lblTitul;
	}

	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == panelCerrar) {
			panelCerrar.setBackground(lblCerrar.getForeground());
			lblCerrar.setForeground(Herramientas.black);
		}
		if (e.getSource() == panelMinim) {
			panelMinim.setBackground(lblCuadr.getForeground());
			lblMinim.setForeground(Herramientas.black);
		}
		if (e.getSource() == panelCuadr) {
			panelCuadr.setBackground(lblCuadr.getForeground());
			lblCuadr.setForeground(Herramientas.black);
		}
		if (e.getSource() == stellarCinema) {
			stellarCinema.setText("stellar cinema");
			hiloColor = new HiloColor(this, stellarCinema);
			yaSeCambiodeColor = true;
			hiloColor.start();
		}
	}

	public void obtenerColLabels(JLabel jLabel) {
		try {
			switch (sentidoColor) {
			case 0:
				if (Herramientas.colorOriginal.getGreen() != 255) {
					Herramientas.colorOriginal = new Color(Herramientas.colorOriginal.getRed(),
							Herramientas.colorOriginal.getGreen() + 10, Herramientas.colorOriginal.getBlue());
				} else
					sentidoColor++;
				break;
			case 1:
				if (Herramientas.colorOriginal.getRed() != 175) {
					Herramientas.colorOriginal = new Color(Herramientas.colorOriginal.getRed() - 10,
							Herramientas.colorOriginal.getGreen(), Herramientas.colorOriginal.getBlue());
				} else
					sentidoColor++;
				break;
			case 2:
				if (Herramientas.colorOriginal.getBlue() != 255) {
					Herramientas.colorOriginal = new Color(Herramientas.colorOriginal.getRed(),
							Herramientas.colorOriginal.getGreen(), Herramientas.colorOriginal.getBlue() + 10);
				} else
					sentidoColor++;
				break;
			case 3:
				if (Herramientas.colorOriginal.getGreen() != 175) {
					Herramientas.colorOriginal = new Color(Herramientas.colorOriginal.getRed(),
							Herramientas.colorOriginal.getGreen() - 10, Herramientas.colorOriginal.getBlue());
				} else
					sentidoColor++;
				break;
			case 4:
				if (Herramientas.colorOriginal.getRed() != 255) {
					Herramientas.colorOriginal = new Color(Herramientas.colorOriginal.getRed() + 10,
							Herramientas.colorOriginal.getGreen(), Herramientas.colorOriginal.getBlue());
				} else
					sentidoColor++;
				break;
			case 5:
				if (Herramientas.colorOriginal.getBlue() != 175) {
					Herramientas.colorOriginal = new Color(Herramientas.colorOriginal.getRed(),
							Herramientas.colorOriginal.getGreen(), Herramientas.colorOriginal.getBlue() - 10);
				} else
					sentidoColor = 0;
				break;
			}
		} catch (Exception e) {
			Herramientas.colorOriginal = new Color(175, 255, 175);
		}
		jLabel.setForeground(Herramientas.colorOriginal);
		jLabel.update(jLabel.getGraphics());

	}

	public void mouseExited(MouseEvent e) {
		if (e.getSource() == panelCerrar) {
			lblCerrar.setForeground(panelCerrar.getBackground());
			panelCerrar.setBackground(Herramientas.black);

		}
		if (e.getSource() == panelMinim) {
			lblMinim.setForeground(panelMinim.getBackground());
			panelMinim.setBackground(Herramientas.black);
		}
		if (e.getSource() == panelCuadr) {
			lblCuadr.setForeground(panelCuadr.getBackground());
			panelCuadr.setBackground(Herramientas.black);

		}

		if (e.getSource() == stellarCinema) {
			stellarCinema.setText("STELLAR CINEMA");
			hiloColor.interrupt();
			cambiarColor();
			cambioDeColor();
		}
	}

	public void mouseDragged(MouseEvent e) {
		if (arrastrable) {
			if (e.getSource() == panelArrastre || e.getSource() == lblTitul) {
				int posX = e.getXOnScreen();
				int posY = e.getYOnScreen();
				if (!estaMaxi) {
					setLocation(posX - this.posX, posY - this.posY);
				}
				if (sePuedeMaxi) {
					if (estaMaxi) {
						if (posX <= (aux.width / 2)) {
							this.posX = posX;
						} else if (posX >= Herramientas.SCREEN_SIZE.width - (aux.width / 2)) {
							this.posX = posX - Herramientas.SCREEN_SIZE.width + aux.width;
						} else {
							this.posX = aux.width / 2;
						}
						this.posY = e.getY();
						cambiarEstado();
					}
				}
			}
		}
	}

	public void mouseMoved(MouseEvent e) {
	}

	public void cambiarEstado() {
		if (sePuedeMaxi) {
			int lim = 40;
			int tam = (int) ((getBounds().width) * 50.0) / 834;
			if (!estaMaxi) {
				lim = 80;
				lblCuadr.setText("2");
				aux = getBounds();
				setBounds(-1, -1, Herramientas.SCREEN_SIZE.width + 2, Herramientas.SCREEN_SIZE.height + 2);
				if (tam > lim)
					tam = lim;
				stellarCinema.setFont(crearFuente("fonts/PAC-FONT.ttf", tam));
				cambiarMaximizado(true);
			}
			if (estaMaxi) {
				lblCuadr.setText("1");
				setBounds(aux);
				if (tam > 40)
					tam = 40;
				stellarCinema.setFont(crearFuente("fonts/PAC-FONT.ttf", tam));
				cambiarMaximizado(false);
			}
			stellarCinema.setSize(getBounds().width - 2, tam + 10);
			panelSuperior.setSize(getBounds().width - 2,
					stellarCinema.getBounds().y + stellarCinema.getBounds().height);
			panelArrastre.setSize(panelSuperior.getWidth() - 150, 30);
			separadorSup.setSize(panelSuperior.getWidth(), 1);
			estaMaxi = !estaMaxi;
		}
	}

	/**
	 * Determina el título tanto de la ventana<br>
	 * como de la barra superior a la izquierda. <br>
	 *
	 * @param mensaje es el título de la ventana
	 * @param fuente  es la fuente del título
	 */
	public void cambiarTitulo(String mensaje, Font fuente) {
		lblTitul = new JLabel("");
		lblTitul.setFont(fuente);
		setTitle(mensaje);
		lblTitul.setText(mensaje);
	}

	public Dimension getTam() {
		return getSize();
	}

	/**
	 * Crea una fuente dependiendo de una ruta, si no la encuentra por default<br>
	 * la fuente se convierte en Arial
	 *
	 * @param ruta    La ruta de la fuente con extensión de fuente (.ttf, .otf, etc)
	 * @param tamanio El tamaño de la fuente
	 * @return La fuente generada
	 */
	public Font crearFuente(String ruta, int tamanio) {
		Font fuente = null;
		try {
			InputStream rutaLeer = new BufferedInputStream(new FileInputStream(ruta));
			Font fuenteBase = Font.createFont(Font.TRUETYPE_FONT, rutaLeer);
			fuente = fuenteBase.deriveFont(Font.PLAIN, tamanio);
		} catch (Exception e) {
			fuente = new Font("Arial", Font.PLAIN, tamanio);
			System.out.println("Error en la fuente de ruta " + e.getMessage());
		}
		return fuente;
	}

	public void configurarPanelPrincipal(JPanel contentPane) {
		setContentPane(contentPane);
		contentPane.setBackground(Herramientas.black);
		contentPane.setBorder(new LineBorder(Herramientas.white));
		contentPane.setLayout(null);
	}
	public void actualizarPanel() {
		setContentPane(contentPane);
		contentPane.setBorder(new LineBorder(col));
	}

	/**
	 * Configura la ventana con respecto a 2 métodos necesarios:<br>
	 * <li><b>{@code setSize}:</b> determina el tamaño original de la ventana
	 * <ul>
	 * Parámetros:
	 * <ul>
	 * <li>(int) width: es el ancho de la ventana
	 * <li>(int) height: es el alto de la ventana
	 * </ul>
	 * </ul>
	 * <li><b>{@code cambiarTitulo}:</b> determina el título tanto de la ventana<br>
	 * como de la barra superior a la izquierda. <br>
	 * <ul>
	 * Parámetros:
	 * <ul>
	 * <li>(String) titulo: es el título de la ventana
	 * <li>(Font) Es la fuente del título (se acepta generar la fuente con new
	 * Font()<br>
	 * o también usando el método crearFuente que obtiene la fuente de acuerdo a una
	 * ruta establecida y un tamaño
	 * </ul>
	 *
	 * @see #crearFuente
	 * @see #cambiarTitulo
	 */
	public abstract void conFigurarVentana();

	public abstract void cambioDeColor();

	public abstract void cambiarMaximizado(boolean esGrande);

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_HOME) {
			if (e.isControlDown() && e.isShiftDown()) {
				setLocationRelativeTo(null);
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_F4) {
			if (e.isAltDown()) {
				autoDestruccion();
			}
		}
		if (isPopUp) {
			setVisible(false);
		}
		for (int i = 0; i < tfArr.size(); i++) {
			if (e.getSource() == tfArr.get(i)) {
				if (e.getKeyChar() == 8) {
					if (tfArr.get(i).getText().length() == 1) {
						tfArr.get(i).setText("0");
					}
				}
			}

		}
	}

	public void keyReleased(KeyEvent e) {
	}

	public void setTitulo(String cadena) {
		setTitle("Stellar Cinema - " + cadena);
		lblTitul.setText("Stellar Cinema - " + cadena);
	}

	public void setPopUp(boolean opt) {
		isPopUp = opt;
	}

	public boolean getIsMaxed() {
		return estaMaxi;
	}

	public void setRedimensionable(boolean opcion) {
		sePuedeMaxi = opcion;
		if (!opcion) {
			panelMinim.setVisible(false);
			panelCuadr.setVisible(false);
			panelArrastre.setLocation(50, 0);
			panelArrastre.setSize(getBounds().width - 52, 30);
		} else {
			panelMinim.setVisible(true);
			panelCuadr.setVisible(true);
			panelArrastre.setLocation(150, 0);
			panelArrastre.setSize(getBounds().width - 152, 30);
		}
	}

	public void setAlwaysBig(boolean o) {
		if (!o) {
			setRedimensionable(true);
			setArrastrable(true);
		}
		setLocation(-1, -1);
		setSize(Herramientas.SCREEN_SIZE.width + 2, Herramientas.SCREEN_SIZE.height + 2);
		setRedimensionable(false);
		setArrastrable(false);
		separadorSup.setSize(getBounds().width, 1);
		stellarCinema.setLocation(posXCentrada(stellarCinema.getWidth()), stellarCinema.getY());
		int tam = (int) ((getBounds().width) * 50.0) / 834;
		if (tam > 80)
			tam = 80;
		stellarCinema.setFont(crearFuente("fonts/PAC-FONT.ttf", tam));
		stellarCinema.setBounds(1, 45, getBounds().width - 2, tam + 10);
		panelSuperior.setSize(getBounds().width - 2, stellarCinema.getBounds().y + stellarCinema.getBounds().height);

	}

	public void setArrastrable(boolean o) {
		arrastrable = o;
	}

	public void setCloseVisible(boolean opcion) {
		if (opcion) {
			panelCerrar.setVisible(true);
			if (!sePuedeMaxi) {
				panelArrastre.setLocation(50, 0);
				panelArrastre.setSize(getBounds().width - 52, 30);
			} else {
				panelArrastre.setLocation(150, 0);
				panelArrastre.setSize(getBounds().width - 152, 30);
			}
		} else {
			panelCerrar.setVisible(false);
			if (!sePuedeMaxi) {
				panelArrastre.setLocation(0, 0);
				panelArrastre.setSize(getBounds().width - 52, 30);
			} else {
				panelArrastre.setLocation(100, 0);
				panelArrastre.setSize(getBounds().width - 152, 30);
			}
		}

	}

	public Rectangle getTitleBounds() {
		return lblTitul.getBounds();
	}

	public int posXCentrada(int width) {
		return (int) ((getWidth() - width) / 2);
	}

	public int posYCentrada(int height) {
		return (int) ((getHeight() - panelSuperior.getHeight() - height) / 2) + panelSuperior.getHeight();
	}

	public void cambiarColor() {
		col = Herramientas.colorOriginal;
		contentPane.setBorder(new LineBorder(Herramientas.colorOriginal));
		lblTitul.setForeground(Herramientas.colorOriginal);
		panelCerrar.setBackground(Herramientas.black);
		lblCerrar.setForeground(Herramientas.colorOriginal);
		lblMinim.setForeground(Herramientas.colorOriginal);
		lblCuadr.setForeground(Herramientas.colorOriginal);
		separadorSup.setForeground(Herramientas.colorOriginal);
		stellarCinema.setForeground(Herramientas.colorOriginal);
		UIManager.put("ToolTip.foreground", Herramientas.colorOriginal);
	}

	public void autoDestruccion() {
		HiloColor hc = new HiloColor(this, stellarCinema);
		stellarCinema.setText("stellar cinema");
		hc.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		hc.interrupt();
		System.exit(0);
	}

	public void actualizarVentana() {
		for (int i = 0; i < contentPane.getComponentCount(); i++) {
			contentPane.getComponent(i).update(contentPane.getComponent(i).getGraphics());
		}
	}

	public void actualizarTextField(JFormattedTextField tf) {
		boolean seEncuentra = false;
		int p = -7;
		for (int i = 0; i < tfArr.size() && !seEncuentra; i++) {
			if (tfArr.get(i).equals(tf)) {
				seEncuentra = true;
				p = i;
			}
		}
		if (!seEncuentra) {
			tfArr.add(tf);
			return;
		}
		tfArr.set(p, tf);
	}

	public void setStellarVisible(boolean res) {
		panelSuperior.setSize(panelSuperior.getWidth(), separadorSup.getY() + separadorSup.getHeight());
		stellarCinema.setVisible(res);
	}

	public void settfFocus(JFormattedTextField tf) {
		tf.requestFocusInWindow();
		SwingUtilities.invokeLater(() -> tf.setCaretPosition(tf.getText().length()));
	}

	public final void actualizarColores() {
		if (yaSeCambiodeColor) {
			cambiarColor();
			cambioDeColor();
		}
	}

	public void interrumpirHiloColor() {
		if (hiloColor != null)
			if (!hiloColor.isInterrupted())
				hiloColor.interrupt();
	}
	public void setBorder(Border border) {
		contentPane.setBorder(border);
	}
}