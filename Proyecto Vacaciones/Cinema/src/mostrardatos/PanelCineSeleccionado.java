package mostrardatos;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.time.format.DateTimeFormatter;

import javax.swing.border.LineBorder;

import cine.PanelGrid;
import config.Constantes;
import custom.Panel;
import custom.TableText;
import custom.Text;
import objects.Cinema;

public class PanelCineSeleccionado extends Panel {
	public PanelCineSeleccionado(Cinema c) {

		Text textNombre = new TableText("Nombre: " + c.getName());
		Text textId = new TableText("ID: " + c.getId());
		Text textAsientos = new TableText(
				"Cantidad de Asientos: " + c.getCantAsientos() + ", libres: " + c.getCantLibres());
		Text textFechayHora = new TableText(
				"Momento del Cine: " + c.getMomentoCine().format(DateTimeFormatter.ofPattern("dd/MM/yyyy, HH:mm")));

		PanelGrid grid = new PanelGrid(c.getMatrizBoolean());
		grid.setBorder(new LineBorder(Constantes.defaultLineColor));

		Panel contenedorSuperior = new Panel();
		contenedorSuperior.setLayout(new GridLayout(4, 1, 1, 1));
		contenedorSuperior.setBackground(Constantes.defaultLineColor);

		contenedorSuperior.add(textNombre);
		contenedorSuperior.add(textId);
		contenedorSuperior.add(textAsientos);
		contenedorSuperior.add(textFechayHora);
		add(contenedorSuperior, BorderLayout.NORTH);
		add(grid);
	}
}
