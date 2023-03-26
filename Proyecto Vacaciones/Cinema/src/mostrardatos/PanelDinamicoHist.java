package mostrardatos;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import custom.Panel;
import custom.PanelConScroll;
import objects.Cliente;
import objects.Compra;
import tools.TablaCompras;

public class PanelDinamicoHist extends Panel {
	public PanelDinamicoHist() {
		goToPedirCedu();
	}

	public void goToPedirCedu () {
		removeAll();
		add(new PanelPedirCedulaHist(this));
		revalidate();
	}

	public void goToHistorialCompraConfiteria (Cliente c, int tipo) {
		goToPedirCedu();

		Compra[] compras = getComprasByTipo(c, tipo);
		new VentanaVerConsolidado(compras, tipo).setVisible(true);
	}

	public void goToHistorialCompraCinema () {
		removeAll();
		add(new PanelConScroll(new PanelPedirCedulaHist(this)));
		revalidate();
	}

	public static Compra[] getComprasByTipo (int tipo) {
		Compra[] comprasFiltradas = null;
		try {
			Compra[] compras = TablaCompras.getAllCompras();
			String compraString = "";
			for (Compra compra : compras) {
				if (tipo == VentanaVerConsolidado.CONFITERIA && compra.getTipo().equals("Confitería")) {
					compraString += compra.toString() + "Ç";
				}
				if (tipo == VentanaVerConsolidado.CINE && compra.getTipo().equals("Cinema")) {
					compraString += compra.toString() + "Ç";
				} else if (tipo == VentanaVerConsolidado.AMBOS) {
					compraString += compra.toString() + "Ç";
				}
			}
			String comprasFiltradasString[] = compraString.split("Ç");
			comprasFiltradas = new Compra[comprasFiltradasString.length];
			for (int i = 0; i < comprasFiltradasString.length; i++)
				comprasFiltradas[i] = Compra.parse(comprasFiltradasString[i]);

		} catch (SQLException e) {}
		return comprasFiltradas;

	}

	public static Compra[] getComprasByTipo (Cliente c, int tipo) {
		Compra[] comprasFiltroTipo = getComprasByTipo(tipo);
		if (comprasFiltroTipo == null) return null;
		String compraString = "";
		for (Compra compra : comprasFiltroTipo) {
			if (compra.getCC() == c.getCC()) {
				compraString += compra.toString() + "Ç";
			}
		}
		String comprasFiltradasString[] = compraString.split("Ç");
		Compra[] comprasFiltradas = new Compra[comprasFiltradasString.length];
		for (int i = 0; i < comprasFiltradasString.length; i++)
			comprasFiltradas[i] = Compra.parse(comprasFiltradasString[i]);
		return comprasFiltradas;
	}

	/**
	 * 
	 * @param c
	 * @param tipo
	 * @return las compras separadas por hora de un tipo determinado
	 * @deprecated no separa bien las compras
	 */
	@Deprecated
	public static String getComprasMismaHora (Cliente c, int tipo) {
		Compra[] comprasFiltroTipo = getComprasByTipo(c, tipo);
		if (comprasFiltroTipo == null) return null;
		String compraString = "";
		ArrayList<LocalDateTime> tiempos = new ArrayList<LocalDateTime>();
		for (int i = 0; i < comprasFiltroTipo.length; i++) {

			if (!haceParteDe(tiempos, comprasFiltroTipo[i].getMomento())) {
				compraString += "[";
				for (Compra compraHora : getComprasMismaHora(c, tipo, comprasFiltroTipo[i].getMomento()))
					compraString += compraHora.getElemento() + ", ";

				compraString += "]";
				tiempos.add(comprasFiltroTipo[i].getMomento());
			}

		}
		return compraString;
	}

	public static boolean haceParteDe (ArrayList<LocalDateTime> tiempos, LocalDateTime time) {
		for (LocalDateTime cadaTiempo : tiempos) if (cadaTiempo.isEqual(time)) return true;
		return false;

	}

	public static Compra[] getComprasMismaHora (Cliente c, int tipo, LocalDateTime momento) {
		Compra[] comprasFiltroTipo = getComprasByTipo(c, tipo);
		if (comprasFiltroTipo == null) return null;
		String compraString = "";
		for (Compra compra : comprasFiltroTipo) {
			if (compra.getMomento().isEqual(momento)) {
				compraString += compra.toString() + "Ç";
			}
		}

		String comprasFiltradasString[] = compraString.split("Ç");
		Compra[] comprasFiltradas = new Compra[comprasFiltradasString.length];
		for (int i = 0; i < comprasFiltradasString.length; i++)
			comprasFiltradas[i] = Compra.parse(comprasFiltradasString[i]);
		return comprasFiltradas;
	}
}
