package mostrardatos;

import java.awt.GridLayout;

import config.Utilities;
import custom.Panel;
import custom.Plantilla;
import custom.Text;
import objects.Cinema;

public class PanelVerCines extends Panel {

	public PanelVerCines(Cinema[] c) {
		if (c == null) return;
		Cinema[] cines = new Cinema[c.length];
		for (int i = 0; i < c.length; i++) cines[i] = c[i];

		int cantj = Utilities.conseguirNumeroTabla(c.length);
		int canti = c.length / cantj;
		
		setLayout(new GridLayout(canti, cantj, 5, 5));
		for (int i = 0; i < cines.length; i++) {
			Cinema cine = cines[i];
			Text t = new Text("<html><center>" + cine.getName() + "<center><html>");
			t.configAsButton(e -> new VentanaCineSeleccionado(cine).setVisible(true));
			add(t);
		}
		
	}

	public static final class VentanaCineSeleccionado extends Plantilla {

		public VentanaCineSeleccionado(Cinema cine) {
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			agregarBody(new PanelCineSeleccionado(cine));
		}

		@Override
		public void configurarTam () {
			setSize(1000, 600);
		}

	}

}
