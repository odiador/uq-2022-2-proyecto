package admin;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import custom.Panel;
import custom.Text;

public class PanelAdmin extends Panel {

	public PanelAdmin(PanelDinamicoAdmin panelDinamicoAdmin) {
		Text bConfigCine = new Text("Funciones del cine");
		Text bConfigBase = new Text("Funciones de la base");

		bConfigCine.setPreferredSize(300, 40);
		bConfigBase.setPreferredSize(300, 40);

		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 5, 5, 0);

		gbc.gridx = 0;
		gbc.gridy = 0;
		add(bConfigCine, gbc);
		gbc.gridy = 1;
		add(bConfigBase, gbc);

		bConfigCine.configAsButton(e -> panelDinamicoAdmin.goToPrincipalCine());
		bConfigBase.configAsButton(e -> panelDinamicoAdmin.goToPrincipalBase());
	}
}
