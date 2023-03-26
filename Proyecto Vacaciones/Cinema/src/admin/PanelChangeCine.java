package admin;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import config.Utilities;
import custom.ClicListener;
import custom.CustomListener;
import custom.Evento;
import custom.NumberTextField;
import custom.Panel;
import custom.Text;
import objects.Cinema;
import objects.CustomException;
import tools.TablaCinema;

public class PanelChangeCine extends Panel implements ClicListener {
	private PanelDinamicoAdmin panelDinamicoAdmin;
	private NumberTextField tf;
	private Text bAceptar, bVolver;

	public PanelChangeCine(PanelDinamicoAdmin panelDinamicoAdmin) {
		setPanelDinamicoAdmin(panelDinamicoAdmin);

		Text lblEscribeId = new Text("Escribe el ID de la sala a seleccionar", SwingConstants.LEFT);
		tf = new NumberTextField(9999999999d);
		bAceptar = new Text("Aceptar");
		bVolver = new Text("Volver");

		bAceptar.configAsButton(this);
		bVolver.configAsButton(this);

		tf.setValue(Utilities.getIdCine());

		tf.setPreferredSize(300, 40);
		bAceptar.setPreferredSize(300, 40);
		bVolver.setPreferredSize(300, 40);
		lblEscribeId.setPreferredSize(300, 40);

		Panel panelCentral = new Panel();
		panelCentral.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelCentral.add(lblEscribeId, gbc);
		gbc.gridy = 1;
		panelCentral.add(tf, gbc);

		Panel panelInferior = new Panel();
		panelInferior.setLayout(new GridLayout(1, 2));
		panelInferior.add(bVolver);
		panelInferior.add(bAceptar);

		add(panelCentral);
		add(panelInferior, BorderLayout.SOUTH);
	}

	public PanelDinamicoAdmin getPanelDinamicoAdmin () {
		return panelDinamicoAdmin;
	}

	public void setPanelDinamicoAdmin (PanelDinamicoAdmin panelDinamicoAdmin) {
		this.panelDinamicoAdmin = panelDinamicoAdmin;
	}

	@Override
	public void botonPresionado (Evento e) {
		if (e.getSource() == bAceptar) {
			try {
				int cine = tf.getIntNumber();
				Cinema c = TablaCinema.getCinemaById(cine);
				Utilities.setIdCine(cine);

				for (CustomListener l : getPanelDinamicoAdmin().getCustomListeners()) l.accionRealizada();
				JOptionPane.showMessageDialog(null,
						"<html><center><font color = #0000FF>El cine ha sido cambiado,<br> ahora es: " + c.getName()
								+ "</font></center></html>");

			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null,
						"<html><center><font color = #FF0000>" + e1 + "</font></center></html>");
			} catch (CustomException e1) {
				JOptionPane.showMessageDialog(null,
						"<html><center><font color = #FF0000>" + e1.getCausa() + "</font></center></html>");
			}
		}
		if (e.getSource() == bVolver) {
			getPanelDinamicoAdmin().goToPrincipalCine();
		}
	}
}
