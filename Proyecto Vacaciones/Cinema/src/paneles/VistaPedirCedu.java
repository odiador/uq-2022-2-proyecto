package paneles;

import static tools.TablaClientes.getCliente;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import confiteria.VentanaConfi;
import custom.ClicListener;
import custom.Evento;
import custom.NumberTextField;
import custom.Panel;
import custom.Text;
import objects.Cliente;
import objects.CustomException;
import tools.TablaClientes;

public class VistaPedirCedu extends Panel implements ClicListener {

	public VistaPedirCedu(OPCIONES opcion) {
		setOpcion(opcion);
		setLabelCedu(new Text("Escribe una cédula"));
		setTfCedu(new NumberTextField(9999999999d, "Escribe una cédula"));
		setBotonAceptar(new Text("Aceptar"));

		getLabelCedu().setVerticalAlignment(SwingConstants.BOTTOM);
		getLabelCedu().setHorizontalAlignment(SwingConstants.LEFT);

		getBotonAceptar().setOpaque(true);
		getBotonAceptar().setCanBeSelectioned(true);
		getBotonAceptar().addClicListener(this);
		getBotonAceptar().setBackground(Text.brighter);

		getTfCedu().setValue(0);

		getTfCedu().setPreferredSize(400, 40);
		getLabelCedu().setPreferredSize(400, 40);
		getBotonAceptar().setPreferredSize(200, 40);

		Panel principal = new Panel();
		principal.setLayout(new GridBagLayout());
		setLayout(new BorderLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 10, 10, 10);
		principal.add(getLabelCedu(), gbc);
		gbc.gridy = 1;
		principal.add(getTfCedu(), gbc);

		add(principal, BorderLayout.CENTER);
		add(getBotonAceptar(), BorderLayout.SOUTH);
	}

	public Cliente buscarCliente (long cc) {
		try {
			return getCliente(cc);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void botonPresionado (Evento e) {
		switch (getOpcion()) {
			case BUSCAR -> {
				Cliente c;
				try {
					c = TablaClientes.getCliente(getTfCedu().getNumber());
					if (!c.existe()) {
						JOptionPane.showMessageDialog(null, "Error: el cliente no fue encontrado", "Error",
								JOptionPane.ERROR_MESSAGE);
						break;
					}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Error: " + e1, "Error", JOptionPane.ERROR_MESSAGE);
					break;
				}
				removeAll();
				setLayout(new BorderLayout());
				add(new VistaBusqueda(c));
				revalidate();
				repaint();
			}
			case CONFITERIA -> {
				try {
					Cliente c = TablaClientes.getCliente(getTfCedu().getNumber());
					new VentanaConfi(c).setVisible(true);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Error: " + ex, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			case ELIMINAR -> {
				String name;
				try {
					name = TablaClientes.eliminarCliente(getTfCedu().getNumber());
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Error: " + e1, "Error", JOptionPane.ERROR_MESSAGE);
					break;
				} catch (CustomException e1) {
					JOptionPane.showMessageDialog(null, "Error: no se encontró el cliente", "Error",
							JOptionPane.ERROR_MESSAGE);
					break;
				}
				JOptionPane.showMessageDialog(null, "Cliente con el nombre " + name + " eliminado");
			}
			case HISTORIAL -> {
				JOptionPane.showMessageDialog(null, "Aún no se ha implementado");
			}
			default -> {}
		}
	}

	public OPCIONES getOpcion () {
		return opcion;
	}

	public void setOpcion (OPCIONES opcion) {
		this.opcion = opcion;
	}

	public NumberTextField getTfCedu () {
		return tfCedu;
	}

	public void setTfCedu (NumberTextField tfCedu) {
		this.tfCedu = tfCedu;
	}

	public Text getBotonAceptar () {
		return botonAceptar;
	}

	public void setBotonAceptar (Text botonAceptar) {
		this.botonAceptar = botonAceptar;
	}

	public Text getLabelCedu () {
		return labelCedu;
	}

	public void setLabelCedu (Text labelCedu) {
		this.labelCedu = labelCedu;
	}

	private Text labelCedu, botonAceptar;
	private NumberTextField tfCedu;
	private OPCIONES opcion;

	public static enum OPCIONES {
		CINE, CONFITERIA, REGISTAR, ELIMINAR, BUSCAR, TARJETA, RECARGAR, HISTORIAL, CONSOLIDADO, VER
	}
}
