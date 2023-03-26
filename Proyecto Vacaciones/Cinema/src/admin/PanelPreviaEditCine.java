package admin;

import java.awt.BorderLayout;

import cine.PanelGrid;
import custom.Panel;
import custom.Text;

public class PanelPreviaEditCine extends Panel {
	public PanelPreviaEditCine(int width, int height, PanelDinamicoAdmin panelDinamicoAdmin) {
		int cantidad = 1;
		if (height * width >= 3) cantidad = 3;
		PanelGrid panelGrid = new PanelGrid(cantidad, new boolean[height][width]);
		Text bVolver = new Text("Volver");

		bVolver.configAsButton(e -> panelDinamicoAdmin.volverDePreviaEditCine());
		bVolver.setPreferredSize(300, 40);

		setLayout(new BorderLayout(0, 5));
		add(panelGrid);
		add(bVolver, BorderLayout.SOUTH);
	}
}
