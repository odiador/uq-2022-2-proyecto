package cine;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.border.LineBorder;

import config.Constantes;
import custom.Panel;
import custom.Text;

public class PanelDerecha extends Panel {

	private Text prevSillas, prevCantidad, prevPrecio, prevTipo, prevVUnitario, prevPrecioBasic, prevPuntosBasic,
			prevPrecioGold, prevPuntosGold;

	public PanelDerecha() {
		String vistaPrevia = "Vista Previa";
		Text tVistaPrevia = new Text(vistaPrevia);

		Text tSillas = new Text();
		Text tCantidad = new Text();
		Text tTipo = new Text();
		Text tPrecio = new Text();

		Text tPrecioBasic = new Text();
		Text tPuntosBasic = new Text();

		Text tPrecioGold = new Text();
		Text tPuntosGold = new Text();
		Text tVUnitario = new Text();

		prevSillas = new Text();
		prevCantidad = new Text();
		prevPrecio = new Text();
		prevTipo = new Text();
		prevVUnitario = new Text();
		prevPrecioBasic = new Text();
		prevPuntosBasic = new Text();
		prevPrecioGold = new Text();
		prevPuntosGold = new Text();

		String sillas = "Sillas";
		String cantidad = "Cantidad";
		String precio = "Precio sin dcto";
		String tipo = "Tipo de Silla";
		String vUnitario = "Valor unitario";
		String precioBasic = "Precio (Basic)";
		String puntosBasic = "Puntos (Basic)";
		String precioGold = "Precio (Gold)";
		String puntosGold = "Puntos (Gold)";

		configurarTextTabla(tVistaPrevia, vistaPrevia);
		configurarTextTabla(tCantidad, cantidad);
		configurarTextTabla(tSillas, sillas);
		configurarTextTabla(tPrecio, precio);
		configurarTextTabla(tTipo, tipo);
		configurarTextTabla(tVUnitario, vUnitario);
		configurarTextTabla(tPrecioBasic, precioBasic);
		configurarTextTabla(tPuntosBasic, puntosBasic);
		configurarTextTabla(tPrecioGold, precioGold);
		configurarTextTabla(tPuntosGold, puntosGold);

		configurarTextTabla(prevCantidad);
		configurarTextTabla(prevSillas);
		configurarTextTabla(prevPrecio);
		configurarTextTabla(prevTipo);
		configurarTextTabla(prevVUnitario);
		configurarTextTabla(prevPrecioBasic);
		configurarTextTabla(prevPuntosBasic);
		configurarTextTabla(prevPrecioGold);
		configurarTextTabla(prevPuntosGold);

		tVistaPrevia.setBackground(VentanaSala.COL_SI);

		setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = 0;
		gbc.gridwidth = 2;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.BOTH;
		add(tVistaPrevia, gbc);
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		add(tCantidad, gbc);
		gbc.gridx = 1;
		add(prevCantidad, gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		add(tSillas, gbc);
		gbc.gridx = 1;
		add(prevSillas, gbc);
		gbc.gridx = 0;
		gbc.gridy = 3;
		add(tTipo, gbc);
		gbc.gridx = 1;
		add(prevTipo, gbc);
		gbc.gridx = 0;
		gbc.gridy = 4;
		add(tVUnitario, gbc);
		gbc.gridx = 1;
		add(prevVUnitario, gbc);
		gbc.gridx = 0;
		gbc.gridy = 5;
		add(tPrecio, gbc);
		gbc.gridx = 1;
		add(prevPrecio, gbc);
		gbc.gridx = 0;
		gbc.gridy = 6;
		add(tPrecioBasic, gbc);
		gbc.gridx = 1;
		add(prevPrecioBasic, gbc);
		gbc.gridx = 0;
		gbc.gridy = 7;
		add(tPuntosBasic, gbc);
		gbc.gridx = 1;
		add(prevPuntosBasic, gbc);
		gbc.gridx = 0;
		gbc.gridy = 8;
		add(tPrecioGold, gbc);
		gbc.gridx = 1;
		add(prevPrecioGold, gbc);
		gbc.gridx = 0;
		gbc.gridy = 9;
		add(tPuntosGold, gbc);
		gbc.gridx = 1;
		add(prevPuntosGold, gbc);
		setPreferredSize(300, 1);
	}

	private void configurarTextTabla (Text t) {
		configurarTextTabla(t, "");
	}

	private void configurarTextTabla (Text t, String text) {
		t.setBorder(new LineBorder(Constantes.defaultBgColor));
		t.setOpaque(true);
		t.setBackground(VentanaSala.COL_NO);
		t.setPreferredSize(140, 30);
		t.setText(text);
		t.setToolTipText(text);
	}

	private void actualizarTexto (Text t, String text) {
		t.setText(text);
		t.setToolTipText(text);
	}

	public void actualizarVistaPrevia (EventoMatriz e) {
		actualizarTexto(prevSillas, e.getSelectedChairsString());
		actualizarTexto(prevCantidad, e.getCantidad() + "");
		actualizarTexto(prevPrecio, e.getPrecioString());
		actualizarTexto(prevTipo, e.getTipoSillaString());
		actualizarTexto(prevVUnitario, e.calcValUnitarioString());
		actualizarTexto(prevPrecioBasic, e.getPrecioBasicString());
		actualizarTexto(prevPuntosBasic, e.getPtsBasic() + "");
		actualizarTexto(prevPrecioGold, e.getPrecioGoldString());
		actualizarTexto(prevPuntosGold, e.getPtsGold() + "");
	}

}
