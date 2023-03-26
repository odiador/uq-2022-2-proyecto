package admin;

import static config.Utilities.setIdCine;
import static javax.swing.JOptionPane.showMessageDialog;
import static tools.TablaCinema.eliminarCinema;
import static tools.TablaClientes.eliminarCliente;
import static tools.TablaClientes.getAllClients;
import static tools.TablaCompras.eliminarCompraByID;
import static tools.TablaCompras.getAllCompras;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;

import custom.CustomListener;
import custom.Panel;
import custom.Text;
import objects.Cinema;
import objects.Cliente;
import objects.Compra;
import objects.CustomException;
import tools.TablaCinema;

public class PanelAdminBase extends Panel {
	public PanelAdminBase(PanelDinamicoAdmin panelDinamicoAdmin) {
		Text bEliminarClientes = new Text("Eliminar clientes");
		Text bEliminarCompras = new Text("Eliminar compras");
		Text bEliminarSalas = new Text("Eliminar salas");
		Text bEliminarTodo = new Text("Eliminar todo");
		Text bVolver = new Text("Volver");

		bEliminarClientes.setPreferredSize(300, 40);
		bEliminarCompras.setPreferredSize(300, 40);
		bEliminarSalas.setPreferredSize(300, 40);
		bEliminarTodo.setPreferredSize(300, 40);
		bVolver.setPreferredSize(300, 40);

		Panel centralPanel = new Panel();

		centralPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 5, 5, 0);

		gbc.gridx = 0;
		gbc.gridy = 0;
		centralPanel.add(bEliminarClientes, gbc);

		gbc.gridx = 1;
		centralPanel.add(bEliminarCompras, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		centralPanel.add(bEliminarSalas, gbc);
		gbc.gridx = 1;
		centralPanel.add(bEliminarTodo, gbc);

		add(centralPanel);
		add(bVolver, BorderLayout.SOUTH);

		bEliminarClientes.configAsButton(e -> {
			if (bEliminarClientes.getText().equals("Eliminar clientes")) {
				bEliminarClientes.setText("Dale otra vez para confirmar");
			} else {
				eliminarClientes();
				bEliminarClientes.setText("Eliminar clientes");
			}
		});
		bEliminarCompras.configAsButton(e -> {
			if (bEliminarCompras.getText().equals("Eliminar compras")) {
				bEliminarCompras.setText("Dale otra vez para confirmar");
			} else {
				eliminarCompras();
				bEliminarCompras.setText("Eliminar compras");
			}
		});
		bEliminarSalas.configAsButton(e -> {
			if (bEliminarSalas.getText().equals("Eliminar salas")) {
				bEliminarSalas.setText("Dale otra vez para confirmar");
			} else {

				if (eliminarSalas()) for (CustomListener l : panelDinamicoAdmin.getCustomListeners())
					l.accionRealizada();

				bEliminarSalas.setText("Eliminar salas");
			}
		});
		bEliminarTodo.configAsButton(e -> {
			if (bEliminarTodo.getText().equals("Eliminar todo")) {
				bEliminarTodo.setText("Dale otras 3 veces para confirmar");
			} else if (bEliminarTodo.getText().equals("Dale otras 3 veces para confirmar")) {
				bEliminarTodo.setText("Dale otras 2 veces para confirmar");
			} else if (bEliminarTodo.getText().equals("Dale otras 2 veces para confirmar")) {
				bEliminarTodo.setText("Dale otra vez para confirmar");
			} else {
				if (eliminarSalas()) for (CustomListener l : panelDinamicoAdmin.getCustomListeners())
					l.accionRealizada();
				eliminarCompras();
				eliminarClientes();
				bEliminarTodo.setText("Eliminar todo");
			}
		});
		bVolver.configAsButton(e -> {
			panelDinamicoAdmin.goToPrincipal();
		});
	}

	public static void eliminarCompras () {
		try {
			Compra[] compras = getAllCompras();
			for (Compra c : compras) eliminarCompraByID(c.getId());
			showMessageDialog(null, "Todas las compras han sido eliminadas");
		} catch (SQLException e) {
			showMessageDialog(null, e);
		} catch (CustomException e) {
			showMessageDialog(null, e.getCausa());
		}
	}

	public static boolean eliminarSalas () {
		boolean ret = true;
		try {
			Cinema[] cines = TablaCinema.getAllCinemas();
			for (Cinema c : cines) if (c.getId() != 0) eliminarCinema(c.getId());
			showMessageDialog(null, "Todas las salas (excepto la de id 0) han sido eliminadas");
			setIdCine(0);
		} catch (SQLException e) {
			ret = false;
			showMessageDialog(null, e);
		} catch (CustomException e) {
			ret = false;
			showMessageDialog(null, e.getCausa());
		}
		return ret;
	}

	public static void eliminarClientes () {
		try {
			Cliente[] clientes = getAllClients();
			for (Cliente c : clientes) eliminarCliente(c.getCC());
			showMessageDialog(null, "Todos los clientes han sido eliminados");
		} catch (SQLException e) {
			showMessageDialog(null, e);
		} catch (CustomException e) {
			showMessageDialog(null, e.getCausa());
		}
	}
}