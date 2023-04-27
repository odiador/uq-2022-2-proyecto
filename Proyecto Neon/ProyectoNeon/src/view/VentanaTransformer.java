package view;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.LineBorder;

import model.Herramientas;
import model.data;
import threads.HiloCargando;

public class VentanaTransformer extends ParteSuperior {

	private JPanel panelTfCedu, panelAcept, panelVolver, panelTfCantidad;
	private JLabel lblCedu, lblAcept, lblVolver, lblCantidad;
	private JFormattedTextField tfCedu, tfCantidad;
	private JSeparator separadorCedu, separadorCantidad;
	private boolean estaVac = false;
	private Herramientas.OPCIONES opcion;
	private data data;
	private VentanaPrincipal ventanaPrincipal;

	public void conFigurarVentana() {
		setSize(600, 400);
		cambiarTitulo("", Herramientas.FUENTE_TITULO_DEFAULT);
	}

	public VentanaTransformer(VentanaPrincipal ventanaPrincipal, String cadTipo, Herramientas.OPCIONES opcion) {
		this.ventanaPrincipal = ventanaPrincipal;
		data = new data();
		this.opcion = opcion;
		setTitulo(cadTipo);
		setDefaultNavigation(false);

		panelTfCedu = new JPanel();
		panelTfCedu.setBounds(100, 200, 400, 30);
		panelTfCedu.setLayout(new BorderLayout(0, 0));
		contentPane.add(panelTfCedu);

		panelAcept = new JPanel();
		panelAcept.setBounds(100, 300, 170, 50);
		panelAcept.setLayout(new BorderLayout(0, 0));
		panelAcept.setBorder(new LineBorder(Herramientas.white));
		panelAcept.setBackground(Herramientas.black);
		panelAcept.addMouseListener(this);
		panelAcept.setCursor(Herramientas.HAND_CURSOR);
		contentPane.add(panelAcept);

		panelVolver = new JPanel();
		panelVolver.setBounds(300, 300, 170, 50);
		panelVolver.setLayout(new BorderLayout(0, 0));
		panelVolver.setBorder(new LineBorder(Herramientas.white));
		panelVolver.setBackground(Herramientas.black);
		panelVolver.addMouseListener(this);
		panelVolver.setCursor(Herramientas.HAND_CURSOR);
		contentPane.add(panelVolver);

		lblCedu = new JLabel();
		lblCedu.setFont(Herramientas.FUENTE_COOLVETICA);
		lblCedu.setText("Ingresa la cédula de la persona: ");
		lblCedu.setBounds(100, 160, 400, 30);
		lblCedu.addMouseListener(this);
		lblCedu.setForeground(Herramientas.white);
		contentPane.add(lblCedu);

		separadorCedu = new JSeparator();
		separadorCedu.setBackground(Herramientas.gray);
		separadorCedu.setForeground(Herramientas.gray);
		separadorCedu.setBounds(100, 231, 400, 2);
		contentPane.add(separadorCedu);

		tfCedu = new JFormattedTextField(Herramientas.setFormatodeNumero(0d, 9999999999d));
		tfCedu.setText("");
		tfCedu.addMouseListener(this);
		tfCedu.addKeyListener(this);
		tfCedu.setBackground(Herramientas.black);
		tfCedu.setBorder(null);
		tfCedu.setCursor(Herramientas.TEXT_CURSOR);
		tfCedu.setFont(Herramientas.FUENTE_COOLVETICA);
		tfCedu.setFocusTraversalKeysEnabled(false);
		tfCedu.requestFocusInWindow();
		cambiarColtfCedu();

		panelTfCedu.add(tfCedu, BorderLayout.CENTER);

		lblAcept = new JLabel("Aceptar");
		lblAcept.setFont(Herramientas.FUENTE_COOLVETICA);
		lblAcept.setForeground(Herramientas.white);
		lblAcept.setHorizontalAlignment(0);
		panelAcept.add(lblAcept, BorderLayout.CENTER);

		lblVolver = new JLabel("Volver");
		lblVolver.setFont(Herramientas.FUENTE_COOLVETICA);
		lblVolver.setForeground(Herramientas.white);
		lblVolver.setHorizontalAlignment(0);
		panelVolver.add(lblVolver, BorderLayout.CENTER);

		if (opcion == Herramientas.OPCIONES.CINE || opcion == Herramientas.OPCIONES.RECARGAR) {
			if (opcion == Herramientas.OPCIONES.CINE) {
				lblCantidad = new JLabel("Escribe la cantidad de sillas:");
				tfCantidad = new JFormattedTextField(Herramientas.setFormatodeNumero(0, 99));
			}
			if (opcion == Herramientas.OPCIONES.RECARGAR) {
				lblCantidad = new JLabel("Escribe la cantidad a recargar:");
				tfCantidad = new JFormattedTextField(Herramientas.setFormatodeNumero(0, 999999999999d));
			}
			lblCantidad.setFont(Herramientas.FUENTE_COOLVETICA);
			lblCantidad.setForeground(Herramientas.white);
			lblCantidad.setBounds(100, 210, 400, 30);
			contentPane.add(lblCantidad);

			panelTfCantidad = new JPanel();
			panelTfCantidad.setBounds(100, 240, 400, 30);
			panelTfCantidad.setLayout(new BorderLayout(0, 0));

			tfCantidad.setFont(Herramientas.FUENTE_COOLVETICA);
			tfCantidad.setBackground(Herramientas.black);
			tfCantidad.setBorder(null);
			tfCantidad.addMouseListener(this);
			tfCantidad.addKeyListener(this);
			tfCantidad.setCursor(Herramientas.TEXT_CURSOR);
			tfCantidad.setFocusTraversalKeysEnabled(false);

			separadorCantidad = new JSeparator();
			separadorCantidad.setBounds(100, 270, 400, 2);
			contentPane.add(separadorCantidad);

			panelTfCantidad.add(tfCantidad, "Center");
			contentPane.add(panelTfCantidad);

			cambiarColtfCantidad();
			panelTfCedu.setLocation(panelTfCedu.getLocation().x, panelTfCedu.getLocation().y - 40);
			lblCedu.setLocation(lblCedu.getLocation().x, lblCedu.getLocation().y - 30);
			separadorCedu.setLocation(separadorCedu.getLocation().x, separadorCedu.getLocation().y - 40);
		}
	}

	public void cambiarMaximizado(boolean esGrande) {

	}

	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);
		if (e.getSource() == tfCedu) {
			estaVac = false;
			cambiarColtfCedu();
			if (opcion == Herramientas.OPCIONES.CINE)
				cambiarColtfCantidad();
		}
		if (e.getSource() == tfCantidad) {
			estaVac = true;
			cambiarColtfCedu();
			cambiarColtfCantidad();
		}
	}

	public void mouseReleased(MouseEvent e) {
		super.mouseReleased(e);
		if (e.getSource() == panelAcept) {
			if (Herramientas.estaEnRangoDe(panelAcept)) {
				accionesAceptar();
			}
		}
		if (e.getSource() == panelVolver) {
			if (Herramientas.estaEnRangoDe(panelVolver)) {
				ventanaPrincipal.actualizarColores();
				ventanaPrincipal.setVisible(true);
				setVisible(false);
			}

		}
	}

	public void keyTyped(KeyEvent e) {
		super.keyTyped(e);
		if (e.getKeyChar() == 9) {
			return;
		}
		if (e.getSource() == tfCedu) {
			tfCedu.setForeground(lblCedu.getForeground());
			separadorCedu.setBackground(lblCedu.getForeground());
			separadorCedu.setForeground(lblCedu.getForeground());

		}
		if (e.getSource() == tfCantidad) {
			tfCantidad.setForeground(lblCedu.getForeground());
			separadorCantidad.setBackground(lblCedu.getForeground());
			separadorCantidad.setForeground(lblCedu.getForeground());
		}
	}

	public void cambioDeColor() {
		LineBorder linea = new LineBorder(col);
		lblCedu.setForeground(col);
		panelAcept.setBorder(linea);
		lblAcept.setForeground(col);
		panelVolver.setBorder(linea);
		lblVolver.setForeground(col);
		cambiarColtfCedu();
		if (lblCantidad != null) {
			lblCantidad.setForeground(col);
			cambiarColtfCantidad();
		}

	}

	public void mouseEntered(MouseEvent e) {
		super.mouseEntered(e);
		if (e.getSource() == panelAcept) {
			panelAcept.setBorder(null);
			panelAcept.setBackground(lblCedu.getForeground());
			lblAcept.setForeground(Herramientas.black);
		}
		if (e.getSource() == panelVolver) {
			panelVolver.setBorder(null);
			panelVolver.setBackground(lblCedu.getForeground());
			lblVolver.setForeground(Herramientas.black);
		}
	}

	public void mouseExited(MouseEvent e) {
		super.mouseExited(e);
		if (e.getSource() == panelAcept) {
			panelAcept.setBorder(new LineBorder(lblCedu.getForeground()));
			panelAcept.setBackground(Herramientas.black);
			lblAcept.setForeground(lblCedu.getForeground());
		}
		if (e.getSource() == panelVolver) {
			panelVolver.setBorder(new LineBorder(lblCedu.getForeground()));
			panelVolver.setBackground(Herramientas.black);
			lblVolver.setForeground(lblCedu.getForeground());
		}
	}

	public void accionesAceptar() {
		panelAcept.setBorder(null);
		panelAcept.setBackground(lblCedu.getForeground());
		lblAcept.setForeground(Herramientas.black);
		HiloCargando miHiloCargando = new HiloCargando(panelAcept, lblAcept, "Cargando");
		interrumpirHiloColor();
		miHiloCargando.start();

		String strCedu = tfCedu.getText();
		ArrayList<Object> cliente = data.buscarCliente(strCedu);
		String strCantidad;
		if (strCedu.equals("")) {
			estaVac = false;
			cambiarColtfCedu();
			settfFocus(tfCedu);
			Herramientas.abrirPopUp("Advertencia", "Recuerda escribir la cédula");
			opcion = Herramientas.OPCIONES.ninguna;
		}
		if (cliente.size() == 0) {
			estaVac = false;
			cambiarColtfCedu();
			if (tfCantidad != null)
				cambiarColtfCantidad();
			settfFocus(tfCedu);
			Herramientas.abrirPopUp("Información", "No se encontró la persona");
			opcion = Herramientas.OPCIONES.ninguna;
		}
		int cantidad = 0;
		if (tfCantidad != null) {
			strCantidad = tfCantidad.getText();
			if (strCantidad.equals("")) {
				estaVac = true;
				cambiarColtfCedu();
				cambiarColtfCantidad();
				settfFocus(tfCantidad);
				Herramientas.abrirPopUp("Advertencia", "Recuerda escribir una cantidad");
				opcion = Herramientas.OPCIONES.ninguna;
			} else {
				cantidad = Integer.parseInt(tfCantidad.getText());
			}
		}
		switch (opcion) {
		case CINE:
			if (cantidad < 1 || cantidad > 16) {
				estaVac = true;
				cambiarColtfCedu();
				cambiarColtfCantidad();
				settfFocus(tfCantidad);
				Herramientas.abrirPopUp("Advertencia", "La cantidad tiene que ser entre 1 y 16");
				break;
			}
			if (!buscarEspacio(cantidad)) {
				Herramientas.abrirPopUp("Advertencia", "No hay sillas disponibles");
				break;
			}
			VentanaCine vCine = new VentanaCine(cliente, cantidad, ventanaPrincipal);
			vCine.actualizarColores();
			vCine.setVisible(true);
			setVisible(false);
			break;
		case CONFI:
			VentanaConfi vConfi = new VentanaConfi(ventanaPrincipal);
			vConfi.actualizarColores();
			vConfi.setVisible(true);
			setVisible(false);
			break;
		case BUSCAR:
			break;
		case DAR:
			break;
		case HISTORIAL:
			break;
		case RECARGAR:
//			--;
			break;
		default:
			break;
		}
		miHiloCargando.interrupt();
		panelAcept.setBorder(new LineBorder(lblCedu.getForeground()));
		panelAcept.setBackground(Herramientas.black);
		lblAcept.setForeground(lblCedu.getForeground());
	}

	public void cambiarColtfCantidad() {
		if (!estaVac) {
			tfCantidad.setForeground(Herramientas.gray);
			separadorCantidad.setBackground(Herramientas.gray);
			separadorCantidad.setForeground(Herramientas.gray);
		} else {
			tfCantidad.setForeground(lblCedu.getForeground());
			separadorCantidad.setBackground(lblCedu.getForeground());
			separadorCantidad.setForeground(lblCedu.getForeground());
		}
	}

	public void cambiarColtfCedu() {
		if (estaVac) {
			tfCedu.setForeground(Herramientas.gray);
			separadorCedu.setBackground(Herramientas.gray);
			separadorCedu.setForeground(Herramientas.gray);
		} else {
			tfCedu.setForeground(lblCedu.getForeground());
			separadorCedu.setBackground(lblCedu.getForeground());
			separadorCedu.setForeground(lblCedu.getForeground());
		}
	}

	public void keyPressed(KeyEvent e) {
		actualizarTextField(tfCedu);
		super.keyPressed(e);
		if (tfCantidad != null) {
			actualizarTextField(tfCantidad);
			if (e.getKeyCode() == KeyEvent.VK_TAB) {
				if (!estaVac) {
					settfFocus(tfCantidad);
				} else {
					settfFocus(tfCedu);
				}
				estaVac = !estaVac;
				cambiarColtfCedu();
				cambiarColtfCantidad();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_END) {
			if (e.isShiftDown() && e.isControlDown()) {
				tfCedu.setText("1092851416");
				if (tfCantidad != null)
					tfCantidad.setText("3");
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_SPACE) {
			accionesAceptar();
		}
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			ventanaPrincipal.actualizarColores();
			ventanaPrincipal.setVisible(true);
			setVisible(false);
		}
	}

	public boolean estaLibre(int pI, int pJ, int cant) {
		boolean res = true;
		for (int i = 0; i < cant; i++) {
			if (data.cine[pI][pJ + i])
				res = false;
		}
		return res;
	}

	public boolean buscarEspacio(int cant) {
		boolean hayEspacio = false;
		for (int i = 0; i < 13 && !hayEspacio; i++) {
			for (int j = 0; j < 16 - cant + 1 && !hayEspacio; j++) {
				if (estaLibre(i, j, cant))
					hayEspacio = true;

			}
		}
		return hayEspacio;
	}
}