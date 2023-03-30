package confiteria;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

import config.Constantes;
import config.Utilities;
import custom.CustomLineBorder;
import custom.Panel;
import custom.TableText;
import custom.Text;
import objects.Confiteria;
import objects.ListaConfiteria;

public class PanelPrevioConfi extends Panel {

	private GridBagConstraints constraints;
	private Panel panelRestoCentral;
	private TableText textCarrito;
	private Panel panelCentral;
	private TableText lblTotal;

	public PanelPrevioConfi() {
		setBorder(new LineBorder(Constantes.defaultLineColor));
		textCarrito = new TableText("Carrito", CustomLineBorder.SUR);
		panelRestoCentral = new Panel();
		constraints = new GridBagConstraints();
		panelCentral = new Panel();
		lblTotal = new TableText("Total: $0.00", CustomLineBorder.NORTE);

		panelCentral.setLayout(new GridBagLayout());

		add(panelCentral);
		add(lblTotal, BorderLayout.SOUTH);
		init();
	}

	public void init() {
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weighty = 0;
		constraints.weightx = 1;
		constraints.fill = GridBagConstraints.BOTH;

		panelCentral.add(textCarrito, constraints);
		constraints.gridy++;
		constraints.weighty = 1;
		panelCentral.add(panelRestoCentral, constraints);
	}

	public void setInfo(ListaConfiteria lista) {
		lblTotal.setText("Total: $" + Utilities.format(lista.getTotalCarrito()));
		panelCentral.removeAll();
		init();
		for (int i = 0; i < lista.getSize(); i++)
			addInfo(lista.get(i));
		panelCentral.revalidate();
	}

	private void addInfo(Confiteria c) {
		Text name = new TableText(c.getProducto());

		name.configAsButton(e -> JOptionPane.showMessageDialog(null, c));

		constraints.weighty = 0;
		panelCentral.add(name, constraints);

		constraints.gridy++;
		constraints.weighty = 1;
		panelCentral.add(panelRestoCentral, constraints);

		constraints.gridx = 0;
		panelCentral.revalidate();
	}
}
