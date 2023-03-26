package cine;

import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.time.LocalDateTime;

import custom.PagoListener;
import custom.Panel;
import objects.Cliente;
import objects.CustomException;

public class PanelDinamico extends Panel {
	public static final int MAIN = 0;
	public static final int MEDIO = 1;
	public static final int PAGO = 2;
	private int code;
	private PanelGrid panelMatriz;
	private PanelPago panelPago;

	public PanelDinamico(int cantidad, boolean[][] arr) {
		setCode(MAIN);
		setPanelMatriz(new PanelGrid(cantidad, arr));
		removeAll();
		add(getPanelMatriz());
		revalidate();
	}

	public String[] getSelectedChairs () {
		return getPanelMatriz().getSelectedChairs();
	}

	public int getCantidadTotal () {
		return getPanelMedio().getInfoSillas().getCantidad();
	}

	public void volverAMain () {
		setCode(MAIN);
		removeAll();
		add(getPanelMatriz());
		revalidate();
	}

	public void irAMedio (Cliente c, PagoListener l) {
		setCode(MEDIO);
		setPanelMedio(new PanelPago(c, panelMatriz.getEventoMatriz()));
		clearPagoListeners();
		addPagoListener(l);
		removeAll();
		add(getPanelMedio());
		revalidate();
	}

	public void addPagoListener (PagoListener l) {
		if (getPanelMedio() == null) return;
		getPanelMedio().addPagoListener(l);
	}

	public void clearPagoListeners () {
		if (getPanelMedio() == null) return;
		getPanelMedio().clearPagoListeners();
	}

	public void keyPressed (KeyEvent e) {
		if (getPanelMatriz() != null) getPanelMatriz().keyPressed(e);
	}

	public EventoMatriz getEventoMatriz () {
		if (getPanelMatriz() != null) {
			return getPanelMatriz().getEventoMatriz();
		}
		return null;
	}

	public void addGridListener (GridListener l) {
		if (getPanelMatriz() != null) getPanelMatriz().addGridListener(l);
	}

	public void pagar (LocalDateTime hora, int min, int max, String medioPago, boolean esFinal)
			throws SQLException, CustomException {
		getPanelMedio().pagar(hora, min, max, medioPago, esFinal);
	}

	public void pagar (LocalDateTime hora, int min, int max, String medioPago) throws SQLException, CustomException {
		getPanelMedio().pagar(hora, min, max, medioPago, true);
	}

	public boolean isMixto () {
		return getTipoPago().equals("Mixto");
	}

	public String getTipoPago () {
		return getPanelMedio().getComboTipoPago().getText();
	}

	public String getMedioPago1 () {
		return getPanelMedio().getComboMedioPago1().getText();
	}

	public String getMedioPago2 () {
		return getPanelMedio().getComboMedioPago2().getText();
	}

	public int getCantidad1 () {
		return getPanelMedio().getTfCantidad1().getIntNumber();
	}

	public int getCantidad2 () {
		return getPanelMedio().getTfCantidad2().getIntNumber();
	}

	public int getCode () {
		return code;
	}

	public void setCode (int code) {
		this.code = code;
	}

	public PanelGrid getPanelMatriz () {
		return panelMatriz;
	}

	public void setPanelMatriz (PanelGrid panelMatriz) {
		this.panelMatriz = panelMatriz;
	}

	public PanelPago getPanelMedio () {
		return panelPago;
	}

	public void setPanelMedio (PanelPago panelMedio) {
		this.panelPago = panelMedio;
	}

}
