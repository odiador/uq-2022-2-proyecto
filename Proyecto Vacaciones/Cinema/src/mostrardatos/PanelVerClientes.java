package mostrardatos;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JOptionPane;

import config.Constantes;
import custom.CustomLineBorder;
import custom.Panel;
import custom.TableText;
import custom.Text;
import objects.Cliente;
import objects.TarjetaUQ;

public class PanelVerClientes extends Panel {

	public PanelVerClientes(Cliente[] c) {
		if (c == null)
			return;
		Cliente[] clientes = new Cliente[c.length];
		for (int i = 0; i < c.length; i++)
			clientes[i] = c[i];

		String arrHeader[] = "Nombre-CC-Frecuencia-Tarjeta Basic-Tarjeta Gold".split("-");
		Font f = Constantes.defaultFont.deriveFont(20f);

		setBackground(defaultColor);
		setLayout(new BorderLayout(0, 10));
		setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;
		gbc.gridy = 0;

		for (int i = 0, borde = CustomLineBorder.TODOS; i < arrHeader.length; i++) {
			TableText header = new TableText(arrHeader[i], borde);
			header.setBackground(Text.brighter);
			gbc.gridx = i;
			add(header, gbc);
			borde = CustomLineBorder.NORTE_SUR_Y_ESTE;
		}
		gbc.gridy++;
		for (int i = 0; i < clientes.length; i++) {
			int b = CustomLineBorder.SUR_ESTE_Y_OESTE;
			if (i == clientes.length - 1) {
				gbc.weighty = 1;
				gbc.anchor = GridBagConstraints.PAGE_START;
			}

			gbc.gridy = i + 1;
			gbc.gridx = 0;
			add(new TableText(clientes[i].getName(), f, b), gbc);
			gbc.gridx++;
			add(new TableText(clientes[i].getCC() + "", f, b), gbc);
			gbc.gridx++;
			add(new TableText(clientes[i].getFrecuency() + "", f, b), gbc);

			TarjetaUQ tBasic = clientes[i].gettBasic();
			TarjetaUQ tGold = clientes[i].gettGold();

			gbc.gridx++;
			if (tBasic.getExisteTarjeta()) {
				TableText bTarjeta = new TableText("Ver tarjeta Basic", f, b);
				bTarjeta.configAsButton(e -> {
					VentanaVerTarjeta ventanaVerTarjeta = new VentanaVerTarjeta(true, tBasic);
					ventanaVerTarjeta.setDefaultCloseOperation(VentanaVerTarjeta.DISPOSE_ON_CLOSE);
					ventanaVerTarjeta.setVisible(true);
				});
				add(bTarjeta, gbc);
			} else
				add(new TableText("-", f, b), gbc);

			gbc.gridx++;
			if (tGold.getExisteTarjeta()) {
				TableText bTarjeta = new TableText("Ver tarjeta Gold", f, b);
				bTarjeta.configAsButton(e -> {
					VentanaVerTarjeta ventanaVerTarjeta = new VentanaVerTarjeta(false, tGold);
					ventanaVerTarjeta.setDefaultCloseOperation(VentanaVerTarjeta.DISPOSE_ON_CLOSE);
					ventanaVerTarjeta.setVisible(true);
				});
				add(bTarjeta, gbc);
			} else
				add(new TableText("-", f, b), gbc);

			b = CustomLineBorder.SURESTE;
		}
	}

}
