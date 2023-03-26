package cine;

import static config.Utilities.getIdCine;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import custom.CustomListener;
import custom.Evento;
import custom.PagoListener;
import custom.Panel;
import custom.Plantilla;
import custom.Text;
import objects.Cinema;
import objects.Cliente;
import objects.CustomException;
import objects.TarjetaUQ;
import paneles.ComprarBoleta;
import tools.TablaCinema;

public class VentanaSala extends Plantilla implements KeyListener, GridListener, PagoListener {

	public static void main (String[] args) {
		VentanaSala v = new VentanaSala(new Cliente("Carlitos", 10000, new TarjetaUQ(), TarjetaUQ.ninguna()), 5,
				new ComprarBoleta());
		v.setVisible(true);
	}

	private Cliente cliente;
	private Panel panelSuperior, panelInferior;
	private PanelDerecha panelDerecha;
	private boolean[][] matrizBooleana;
	private int cantidad;
	public static final Color COL_NO = Text.defaultColor.brighter();
	public static final Color COL_SI = COL_NO.brighter();
	private Text bCancelar, bSiguiente;
	private PanelDinamico panelDinamico;
	private boolean hayLibre;
	private ComprarBoleta comprarBoleta;

	/**
	 * @param idCine
	 * @param cliente
	 * @param cantidad
	 * @param comprarBoleta
	 */
	public VentanaSala(Cliente cliente, int cantidad, ComprarBoleta comprarBoleta) {
		setComprarBoleta(comprarBoleta);
		try {
			matrizBooleana = TablaCinema.getMatrizBooleanById(getIdCine());
		} catch (SQLException | CustomException e) {
			matrizBooleana = new boolean[13][16];
		}
		setCliente(cliente);
		setCantidad(cantidad);
		setDefaultNavigation(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		hayLibre = true;
		if (getCantLibres() < getCantidad()) {
			hayLibre = false;
			EventQueue.invokeLater(new Runnable() {
				public void run () {
					dispose();
					JOptionPane.showMessageDialog(null, "No hay suficientes puestos libres");
				}
			});
		}
		if (!hayLibre) return;

		configurarPanelInferior();
		configurarPanelSuperior();

		setPanelDinamico(new PanelDinamico(cantidad, getOcupiedArr()));
		setPanelDerecha(new PanelDerecha());

		getPanelDinamico().addGridListener(this);
		agregarBody(getPanelDinamico());
		agregarBody(getPanelDerecha(), BorderLayout.EAST);

	}

	/**
	 * 
	 */
	private void configurarPanelSuperior () {
		panelSuperior = new Panel();
		Text text;
		try {
			text = new Text(TablaCinema.getNameById(getIdCine()));
		} catch (SQLException | CustomException e) {
			text = new Text("Cinema");
		}
		panelSuperior.add(text);

		panelSuperior.setPreferredSize(100, 100);
		agregarBody(panelSuperior, BorderLayout.NORTH);
	}

	private void configurarPanelInferior () {
		panelInferior = new Panel();
		bCancelar = new Text("Cerrar");
		bSiguiente = new Text("Siguiente");
		panelInferior.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		panelInferior.setLayout(new BoxLayout(panelInferior, BoxLayout.X_AXIS));
		bSiguiente.configAsButton(this);
		bCancelar.configAsButton(this);
		panelInferior.add(bCancelar);
		panelInferior.add(bSiguiente);

		panelInferior.setPreferredSize(100, 60);
		agregarBody(panelInferior, BorderLayout.SOUTH);
	}

	private boolean haceParteMatriz (int code) {
		return movDerecha(code) || movIzquierda(code) || movArriba(code) || movAbajo(code) || code == KeyEvent.VK_TAB;
	}

	public boolean movEnter (int code) {
		return code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE;
	}

	public boolean movDerecha (int code) {
		return code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D;
	}

	public boolean movIzquierda (int code) {
		return code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A;
	}

	public boolean movArriba (int code) {
		return code == KeyEvent.VK_UP || code == KeyEvent.VK_W;
	}

	public boolean movAbajo (int code) {
		return code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S;
	}

	public void keyPressed (KeyEvent e) {
		int c = e.getKeyCode();
		if (haceParteMatriz(c)) {
			getPanelDinamico().keyPressed(e);
		}
		if (movEnter(c)) siguiente();
	}

	private int getCantLibres () {
		boolean[][] ocupadas = getOcupiedArr();
		int cant = 0;
		for (int i = 0; i < ocupadas.length; i++)
			for (int j = 0; j < ocupadas[0].length; j++) if (!ocupadas[i][j]) cant++;
		return cant;
	}

	public boolean[][] getOcupiedArr () {
		boolean[][] ret = new boolean[matrizBooleana.length][matrizBooleana[0].length];
		for (int i = 0; i < ret.length; i++) for (int j = 0; j < ret[0].length; j++) ret[i][j] = matrizBooleana[i][j];
		return ret;
	}

	public void configurarTam () {
		setSize(1200, 700);
	}

	public Cliente getCliente () {
		return cliente;
	}

	public void setCliente (Cliente cliente) {
		this.cliente = cliente;
	}

	public int getCantidad () {
		return cantidad;
	}

	public void setCantidad (int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public void movimientoMatriz (EventoMatriz e) {
		getPanelDerecha().actualizarVistaPrevia(e);
	}

	public void botonPresionado (Evento e) {
		super.botonPresionado(e);
		if (e.getSource() == bSiguiente) siguiente();

		if (e.getSource() == bCancelar) devolver();
	}

	private void siguiente () {
		int code = getPanelDinamico().getCode();
		if (code == PanelDinamico.MAIN) {
			irAMedio();
		} else if (code == PanelDinamico.MEDIO) {
			try {
				pagar();
			} catch (SQLException e) {
				pagoNoRealizado(e);
			} catch (CustomException e) {
				JOptionPane.showMessageDialog(null, e.getCausa(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void devolver () {
		int code = getPanelDinamico().getCode();
		if (code == PanelDinamico.MAIN) dispose();
		else if (code == PanelDinamico.MEDIO) volverAMain();
		else;
	}

	private void pagar () throws SQLException, CustomException {
		if (getPanelDinamico().isMixto()) {
			LocalDateTime time = LocalDateTime.now();
			int cantidad1 = getPanelDinamico().getCantidad1();
			String medioPago1 = getPanelDinamico().getMedioPago1();
			int cantidadTotal = getPanelDinamico().getCantidadTotal();
			String medioPago2 = getPanelDinamico().getMedioPago2();

			if (medioPago1.equals(medioPago2)) throw new CustomException(CustomException.MISMO_MEDIO_PAGO,
					"Los medios de pago no pueden ser los mismos");

			if (cantidad1 == 0 || cantidad1 == cantidadTotal) throw new CustomException(CustomException.CANTIDAD_ES_0,
					"Ninguna cantidad puede ser 0, en ese caso usa el tipo de pago único");

			getPanelDinamico().pagar(time, 0, cantidad1, medioPago1, false);
			
			getPanelDinamico().pagar(time, cantidad1, cantidadTotal, medioPago2);
		} else {
			getPanelDinamico().pagar(LocalDateTime.now(), 0, getPanelDinamico().getCantidadTotal(),
					getPanelDinamico().getMedioPago1());
		}
	}

	private void volverAMain () {
		getPanelDerecha().setPreferredSize(300, 1);
		getPanelDerecha().revalidate();
		getPanelDinamico().volverAMain();
		bCancelar.setText("Cerrar");
	}

	private void irAMedio () {
		getPanelDerecha().setPreferredSize(0, 1);
		getPanelDerecha().revalidate();
		getPanelDinamico().irAMedio(getCliente(), this);
		bCancelar.setText("Volver");
	}

	public PanelDinamico getPanelDinamico () {
		return panelDinamico;
	}

	public void setPanelDinamico (PanelDinamico panelDinamico) {
		this.panelDinamico = panelDinamico;
	}

	public void setVisible (boolean b) {
		if (hayLibre) movimientoMatriz(new EventoMatriz(getPanelDinamico().getSelectedChairs()));
		super.setVisible(b);
	}

	public PanelDerecha getPanelDerecha () {
		return panelDerecha;
	}

	public void setPanelDerecha (PanelDerecha panelDerecha) {
		this.panelDerecha = panelDerecha;
	}

	@Override
	public void pagoRealizado (String infoExtra) {
		try {
			Cinema cine = TablaCinema.getCinemaById(getIdCine());

			String sillas[] = getPanelDinamico().getSelectedChairs();

			for (String cadaSilla : sillas) cine.addOcupied(cadaSilla);

			TablaCinema.changeOccupiedChairs(getIdCine(), cine.getOcuppied());

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error: " + e, "Error", JOptionPane.ERROR_MESSAGE);
			return;
		} catch (CustomException e) {
			JOptionPane.showMessageDialog(null, "Error: " + e.getCausa(), "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		String infoAImprimir = "";
		if (!infoExtra.isEmpty()) {
			infoAImprimir += "<font color = #0000FF>(";
			String infoCiclo[] = infoExtra.split(";");
			for (String cadaInfo : infoCiclo) {
				infoAImprimir += "<br>" + cadaInfo;
			}
			infoAImprimir += ")</font>";
		}

		for (CustomListener l : comprarBoleta.getCustomListeners()) l.accionRealizada();
		JOptionPane.showMessageDialog(null,
				"<html><center>Pago realizado con éxito" + infoAImprimir + "</center></html>", "Info",
				JOptionPane.INFORMATION_MESSAGE);

		dispose();
	}

	@Override
	public void pagoNoRealizado (SQLException e) {
		JOptionPane.showMessageDialog(null, "Error: " + e, "Error", JOptionPane.ERROR_MESSAGE);
		dispose();
	}

	@Override
	public void volver () {
		dispose();
	}

	public ComprarBoleta getComprarBoleta () {
		return comprarBoleta;
	}

	public void setComprarBoleta (ComprarBoleta comprarBoleta) {
		this.comprarBoleta = comprarBoleta;
	}
}
