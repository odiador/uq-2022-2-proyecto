package mostrardatos;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;

import custom.Panel;
import custom.Text;
import objects.Compra;
import tools.TablaCompras;

public class PanelDinamicoCons extends Panel {
	public PanelDinamicoCons() {
		Text bVerComprasCine = new Text("Ver compras del cine");
		Text bVerComprasConfiteria = new Text("Ver compras confitería");
		Text bVerCompras = new Text("Ver compras");

		bVerComprasCine.setPreferredSize(300, 40);
		bVerComprasConfiteria.setPreferredSize(300, 40);
		bVerCompras.setPreferredSize(300, 40);

		bVerComprasCine.configAsButton(e -> goToVerCompras(VentanaVerConsolidado.CINE));
		bVerComprasConfiteria.configAsButton(e -> goToVerCompras(VentanaVerConsolidado.CONFITERIA));
		bVerCompras.configAsButton(e -> goToVerCompras(VentanaVerConsolidado.AMBOS));

		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 5, 5, 0);
		gbc.fill = GridBagConstraints.BOTH;

		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(bVerCompras, gbc);

		gbc.gridwidth = 1;
		gbc.gridy = 1;
		add(bVerComprasCine, gbc);

		gbc.gridx = 1;
		add(bVerComprasConfiteria, gbc);
	}

	public void goToVerCompras (int id) {
		Compra[] compras = null;
		try {
			compras = TablaCompras.getAllCompras();
		} catch (SQLException e) {}
		new VentanaVerConsolidado(compras, id).setVisible(true);
	}
}
