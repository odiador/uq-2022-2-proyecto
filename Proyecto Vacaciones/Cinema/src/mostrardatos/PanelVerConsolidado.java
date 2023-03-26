package mostrardatos;

import static mostrardatos.VentanaVerConsolidado.*;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import javax.swing.JOptionPane;

import config.Constantes;
import config.Utilities;
import custom.CustomLineBorder;
import custom.Panel;
import custom.Plantilla;
import custom.TableText;
import custom.Text;
import objects.Cinema;
import objects.Cliente;
import objects.Compra;
import paneles.VistaBusqueda;

public class PanelVerConsolidado extends Panel {
	public static Compra[] eliminarComprasPorTipo (Compra[] compras, int tipo) {
		String comprasString = "";
		for (int i = 0; i < compras.length; i++) {
			if (tipo == CONFITERIA) {
				if (compras[i].getTipo().equals("Confitería")) comprasString += compras[i] + "Ç";
			} else if (tipo == CINE) {
				if (compras[i].getTipo().equals("Cinema")) comprasString += compras[i] + "Ç";
			} else if (tipo == AMBOS) comprasString += compras[i] + "Ç";
		}
		String[] comprasSplit = comprasString.split("Ç");
		Compra[] result = new Compra[comprasSplit.length];

		for (int i = 0; i < comprasSplit.length; i++) result[i] = Compra.parse(comprasSplit[i]);

		return result;
	}

	public PanelVerConsolidado(Compra[] c, int tipo) {
		if (c == null) return;
		Compra[] compras = new Compra[c.length];
		for (int i = 0; i < c.length; i++) compras[i] = c[i];

		compras = eliminarComprasPorTipo(compras, tipo);

		Arrays.sort(compras, Compra::compararPorCantidadMenorMayor);
		Arrays.sort(compras, Compra::compararPorHora);
		Arrays.sort(compras, Compra::compararPorTipo);

		String tipos = "";
		if (tipo == AMBOS) tipos += "Tipo-";
		tipos += "Nombre-Producto-Fecha-Hora-Medio de Pago-";
		if (tipo != CONFITERIA) tipos += "ID del Cine-";
		tipos += "Valor unitario-Cantidad-Total";
		String arrHeader[] = tipos.split("-");
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
		for (int i = 0; i < compras.length; i++) {
			int b = CustomLineBorder.SUR_ESTE_Y_OESTE;
			if (i == compras.length - 1) {
				gbc.weighty = 1;
				gbc.anchor = GridBagConstraints.PAGE_START;
			}

			gbc.gridy = i + 1;
			gbc.gridx = 0;
			Cliente cliente = compras[i].getCliente();
			if (tipo == AMBOS) {
				add(new TableText(compras[i].getTipo(), f, b), gbc);
				gbc.gridx++;
				b = CustomLineBorder.SURESTE;
			}

			TableText OpaqueTextNombre = new TableText(cliente.getName(), f, b);
			OpaqueTextNombre.configAsButton(e -> new MostrarCliente(cliente).setVisible(true));

			add(OpaqueTextNombre, gbc);
			gbc.gridx++;
			b = CustomLineBorder.SURESTE;
			add(new TableText(compras[i].getElemento(), f, b), gbc);
			
			gbc.gridx++;
			add(new TableText(compras[i].getMomento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), f, b), gbc);
			
			gbc.gridx++;
			add(new TableText(compras[i].getMomento().format(DateTimeFormatter.ofPattern("HH:mm")), f, b), gbc);
			
			gbc.gridx++;
			add(new TableText(compras[i].getMedioPago(), f, b), gbc);
			if (tipo != CONFITERIA) {
				TableText opaqueTextInfoCine = new TableText("-", f, b);
				opaqueTextInfoCine.setOpaque(true);

				if (compras[i].getTipo().equals("Cinema")) {
					Cinema cinema = Cinema.parse(compras[i].getInfoExtra());
					opaqueTextInfoCine = new TableText(cinema.getId() + "", f, b);

					opaqueTextInfoCine.configAsButton(e -> JOptionPane.showMessageDialog(null, cinema));
				}
				gbc.gridx++;
				add(opaqueTextInfoCine, gbc);
			}
			gbc.gridx++;
			add(new TableText("$" + Utilities.format(compras[i].getvUnitario()), f, b), gbc);
			gbc.gridx++;
			add(new TableText(compras[i].getCantidad() + "", f, b), gbc);
			gbc.gridx++;
			add(new TableText("$" + Utilities.format(compras[i].getPrecio()), f, b), gbc);
		}
	}

	private static final class MostrarCliente extends Plantilla {
		public MostrarCliente(Cliente c) {
			VistaBusqueda c2 = new VistaBusqueda(c);
			setDefaultCloseOperation(Plantilla.DISPOSE_ON_CLOSE);
			c2.data.getBotonVolver().setPreferredSize(0, 0);
			agregarBody(c2);
		}

		@Override
		public void configurarTam () {
			setSize(400, 300);
		}
	}
}
