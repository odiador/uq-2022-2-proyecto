package mostrardatos;

import java.awt.GridLayout;

import javax.swing.SwingConstants;

import config.Constantes;
import config.Utilities;
import custom.PanelConImagen;
import custom.Plantilla;
import custom.Text;
import objects.TarjetaUQ;

public class VentanaVerTarjeta extends Plantilla {

	public VentanaVerTarjeta(boolean esBasic, TarjetaUQ tarjeta) {
		PanelConImagen panel = new PanelConImagen("Tarjeta gold");
		if (esBasic)
			panel = new PanelConImagen("Tarjeta basic");
		Text lblDinero = new Text("Dinero: $" + Utilities.format(tarjeta.getDinero()));
		Text lblPuntos = new Text("Puntos: " + tarjeta.getPuntos());
		Text lblFecha = new Text("Fecha Compra: " + Utilities.format(tarjeta.getFecha()));

		lblDinero.setFont(Constantes.defaultFont.deriveFont(50f));
		lblFecha.setFont(Constantes.defaultFont.deriveFont(50f));
		lblPuntos.setFont(Constantes.defaultFont.deriveFont(50f));

		lblDinero.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFecha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPuntos.setHorizontalAlignment(SwingConstants.RIGHT);

		lblDinero.setTextInsets(0, 0, 0, 100);
		lblFecha.setTextInsets(0, 0, 0, 100);
		lblPuntos.setTextInsets(0, 0, 0, 100);

		panel.setLayout(new GridLayout(3, 1));
		panel.add(lblDinero);
		panel.add(lblPuntos);
		panel.add(lblFecha);
		agregarBody(panel);
	}

	@Override
	public void configurarTam() {
		setSize(830, 450);
	}

}
