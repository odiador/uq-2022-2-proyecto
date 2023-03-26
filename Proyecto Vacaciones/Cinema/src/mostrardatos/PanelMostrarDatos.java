package mostrardatos;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import custom.Panel;
import custom.Text;

public class PanelMostrarDatos extends Panel {

	public PanelMostrarDatos() {
		Text bVerClientes = new Text("Ver Clientes");
		bVerClientes.setPreferredSize(300, 40);
		Text bVerCines = new Text("Ver Cines");
		bVerCines.setPreferredSize(300, 40);

		bVerCines.configAsButton(e -> VentanasDatos.mostrarVentanaDatosCines());
		bVerClientes.configAsButton(e -> VentanasDatos.mostrarVentanaDatosClientes());

		setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.insets = new Insets(0, 5, 5, 0);
		gbc.gridy = 0;
		add(bVerClientes, gbc);
		gbc.gridx = 1;
		add(bVerCines, gbc);
	}
}
