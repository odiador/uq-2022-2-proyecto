package paneles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import config.Constantes;
import custom.ClicListener;
import custom.Evento;
import custom.Panel;
import custom.Text;
import mostrardatos.VentanaVerTarjeta;
import objects.Cliente;
import paneles.VistaPedirCedu.OPCIONES;
import variablesVista.VarVistaBuscar;

public class VistaBusqueda extends Panel implements ClicListener {
	public VarVistaBuscar data = new VarVistaBuscar();

	public VistaBusqueda(Cliente cliente) {
		data.setCliente(cliente);
		data.setLblNombre(new Text("Nombre: " + cliente.getName(), Constantes.defaultFont.deriveFont(30f)));
		data.setLblCedu(new Text("CC: " + cliente.getStringCC(), Constantes.defaultFont.deriveFont(30f)));
		data.setLblFrec(new Text("Frecuencia: ", Constantes.defaultFont.deriveFont(30f)));
		data.setBotonVolver(new Text("Volver"));

		if (cliente.gettBasic().getExisteTarjeta()) {
			data.setBotonBasic(new Text("Ver tarjeta Basic", Constantes.defaultFont.deriveFont(30f)));
			data.getBotonBasic().addClicListener(this);
			data.getBotonBasic().setOpaque(true);
		} else
			data.setBotonBasic(new Text("No tiene tarjeta basic", Constantes.defaultFont.deriveFont(30f)));

		if (cliente.gettGold().getExisteTarjeta()) {
			data.setBotonGold(new Text("Ver tarjeta Gold", Constantes.defaultFont.deriveFont(30f)));
			data.getBotonGold().addClicListener(this);
			data.getBotonGold().setOpaque(true);
		} else
			data.setBotonGold(new Text("No tiene tarjeta gold", Constantes.defaultFont.deriveFont(30f)));

		data.getBotonVolver().setOpaque(true);
		data.getBotonVolver().addClicListener(this);
		data.getBotonVolver().setCanBeSelectioned(true);
		data.getBotonBasic().setCanBeSelectioned(true);
		data.getBotonGold().setCanBeSelectioned(true);
		data.getBotonVolver().setBackground(new Color(75, 75, 75));
		data.getBotonBasic().setBackground(new Color(75, 75, 75));
		data.getBotonGold().setBackground(new Color(75, 75, 75));

		data.getLblNombre().setPreferredSize(400, 40);
		data.getLblCedu().setPreferredSize(400, 40);
		data.getLblFrec().setPreferredSize(400, 40);
		data.getBotonBasic().setPreferredSize(300, 40);
		data.getBotonGold().setPreferredSize(300, 40);
		data.getBotonVolver().setPreferredSize(200, 40);

		setLayout(new BorderLayout());

		Panel p = new Panel();
		p.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridx = 0;
		gbc.gridy = 0;
		p.add(data.getLblNombre(), gbc);
		gbc.gridy = 1;
		p.add(data.getLblCedu(), gbc);
		gbc.gridy = 2;
		p.add(data.getLblFrec(), gbc);
		gbc.gridy = 3;
		p.add(data.getBotonBasic(), gbc);
		gbc.gridy = 4;
		p.add(data.getBotonGold(), gbc);
		gbc.gridy = 5;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.LAST_LINE_END;
		add(p, BorderLayout.CENTER);
		add(data.getBotonVolver(), BorderLayout.SOUTH);
	}

	public void botonPresionado(Evento e) {
		if (e.getSource() == data.getBotonBasic()) {
			VentanaVerTarjeta ventanaVerTarjeta = new VentanaVerTarjeta(true, data.getCliente().gettBasic());
			ventanaVerTarjeta.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			ventanaVerTarjeta.setVisible(true);
		}
		if (e.getSource() == data.getBotonGold()) {
			VentanaVerTarjeta ventanaVerTarjeta = new VentanaVerTarjeta(false, data.getCliente().gettGold());
			ventanaVerTarjeta.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			ventanaVerTarjeta.setVisible(true);
		}
		if (e.getSource() == data.getBotonVolver()) {
			removeAll();
			setLayout(new BorderLayout());
			add(new VistaPedirCedu(OPCIONES.BUSCAR));
			revalidate();
			repaint();
		}
	}
}
