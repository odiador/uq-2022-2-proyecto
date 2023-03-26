package admin;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import config.Utilities;
import custom.CustomListener;
import custom.Panel;
import custom.Text;
import objects.Cinema;
import objects.CustomException;
import tools.TablaCinema;
import tools.TablaCompras;

public class PanelAdminCine extends Panel {

	Text bClearSala, bEditarCine, bAgregarCine, bCambiarCine, bVolver;
	private PanelDinamicoAdmin panelDinamicoAdmin;

	public PanelAdminCine(PanelDinamicoAdmin panelDinamicoAdmin) {
		setPanelDinamicoAdmin(panelDinamicoAdmin);

		bClearSala = new Text("Limpiar Sala");
		bEditarCine = new Text("Editar cine");
		bAgregarCine = new Text("Agregar cine");
		bCambiarCine = new Text("Cambiar cine seleccionado");
		bVolver = new Text("Volver");

		bClearSala.configAsButton(e -> limpiarSala());
		bEditarCine.configAsButton(e -> panelDinamicoAdmin.goToEditCine());
		bAgregarCine.configAsButton(e -> agregarCine());
		bCambiarCine.configAsButton(e -> panelDinamicoAdmin.goToChangeCine());
		bVolver.configAsButton(e -> panelDinamicoAdmin.goToPrincipal());

		bClearSala.setPreferredSize(300, 40);
		bEditarCine.setPreferredSize(300, 40);
		bAgregarCine.setPreferredSize(300, 40);
		bCambiarCine.setPreferredSize(300, 40);
		bVolver.setPreferredSize(300, 40);

		Panel panelCentral = new Panel();

		panelCentral.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 5, 5, 0);

		gbc.gridx = 0;
		gbc.gridy = 0;
		panelCentral.add(bClearSala, gbc);

		gbc.gridx = 1;
		panelCentral.add(bCambiarCine, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		panelCentral.add(bAgregarCine, gbc);

		gbc.gridx = 1;
		panelCentral.add(bEditarCine, gbc);

		add(panelCentral);
		add(bVolver, BorderLayout.SOUTH);
	}

	private void agregarCine () {
		if (bAgregarCine.getText().equals("Agregar cine")) {
			bAgregarCine.setText("Dale otra vez para confirmar");
			return;
		}
		try {
			Cinema[] cines = TablaCinema.getAllCinemas();
			int id = 0;
			for (Cinema cine : cines) if (cine.getId() == id) id++;
			TablaCinema.agregarCinema(new Cinema(id, "Nuevo Cinema"));

			JOptionPane.showMessageDialog(null,
					"<html><center><font color = #0000FF>El cine ha sido agregado exitosamente <br>(ID: " + id
							+ ")</font></center></html>");

		} catch (SQLException e1) {}

		bAgregarCine.setText("Agregar cine");
	}

	private void limpiarSala () {
		if (bClearSala.getText().equals("Limpiar Sala")) {
			bClearSala.setText("Dale otra vez para confirmar");
			return;
		}

		try {
			Cinema c = TablaCinema.getCinemaById(Utilities.getIdCine());
			c.clearCinema();
			TablaCinema.updateCine(c);
			TablaCompras.eliminarHistorialDe("Cinema");

			for (CustomListener l : panelDinamicoAdmin.getCustomListeners()) l.accionRealizada();

			JOptionPane.showMessageDialog(null, "<html><center>El Cine <font color = #0000FF>" + c.getName()
					+ "</font> ha sido limpiado con éxito</center></html>");
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null,
					"<html><center><font color = #FF0000>" + e1 + "</font></center></html>");
		} catch (CustomException e1) {
			JOptionPane.showMessageDialog(null,
					"<html><center><font color = #FF0000>" + e1.getCausa() + "</font></center></html>");
		}
		bClearSala.setText("Limpiar Sala");

	}

	public void setPanelDinamicoAdmin (PanelDinamicoAdmin panelDinamicoAdmin) {
		this.panelDinamicoAdmin = panelDinamicoAdmin;
	}
}
