package mostrardatos;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;

import javax.swing.SwingConstants;

import custom.NumberTextField;
import custom.Panel;
import custom.Text;
import tools.TablaClientes;

public class PanelPedirCedulaHist extends Panel {
	public PanelPedirCedulaHist(PanelDinamicoHist panelDinamicoHist) {
		Text lblEscribe = new Text("Escribe una cédula", SwingConstants.LEFT);
		NumberTextField tfCedu = new NumberTextField(9999999999d);
		Text bAceptar = new Text("Aceptar");
		tfCedu.setValue(0);
		lblEscribe.setPreferredSize(400, 40);
		tfCedu.setPreferredSize(400, 40);
		bAceptar.setPreferredSize(400, 40);

		Panel centralPanel = new Panel();
		centralPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(0, 10, 10, 10);

		gbc.gridx = 0;
		gbc.gridy = 0;
		centralPanel.add(lblEscribe, gbc);

		gbc.gridy = 1;
		centralPanel.add(tfCedu, gbc);

		add(centralPanel);
		add(bAceptar, BorderLayout.SOUTH);

		bAceptar.configAsButton(e -> {
			try {
				panelDinamicoHist.goToHistorialCompraConfiteria(TablaClientes.getCliente(tfCedu.getNumber()),
						VentanaVerConsolidado.AMBOS);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});
	}
}
