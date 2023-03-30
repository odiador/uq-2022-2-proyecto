package confiteria;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import config.Constantes;
import custom.Evento;
import custom.PagoListener;
import custom.Panel;
import custom.Plantilla;
import custom.Text;
import objects.Cliente;
import objects.ListaConfiteria;

public class VentanaConfi extends Plantilla implements PagoListener {
	private Text bPagar, bVaciar, bCancelar;
	private Panel inferior;
	private SelecionConfi panelConfi;

	private ListaConfiteria carrito = new ListaConfiteria();
	private Cliente cliente;
	private PanelPrevioConfi panelPrevio;

	public VentanaConfi(Cliente c) {
		setCliente(c);
		panelPrevio = new PanelPrevioConfi();
		panelPrevio.setInfo(carrito);
		panelPrevio.setPreferredSize(300, 300);

		Font f = Constantes.defaultFont.deriveFont(25f);
		setbCancelar(new Text("Cancelar", f));
		setbPagar(new Text("Pagar", f));
		setbVaciar(new Text("Vaciar Carrito", f));
		SelecionConfi selecionConfi = new SelecionConfi(getCarrito());
		selecionConfi.addCustomListener(() -> panelPrevio.setInfo(carrito));
		setPaneConfi(selecionConfi);

		getPanelConfi().setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		getbCancelar().configAsButton(this);
		getbPagar().configAsButton(this);
		getbVaciar().configAsButton(this);

		setInferior(new Panel());
		getInferior().setLayout(new GridLayout(1, 3));
		getInferior().setPreferredSize(1, 40);

		getInferior().add(getbPagar());
		getInferior().add(getbVaciar());
		getInferior().add(getbCancelar());

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		agregarBody(getPanelConfi(), BorderLayout.CENTER);
		agregarBody(getInferior(), BorderLayout.SOUTH);
		agregarBody(panelPrevio, BorderLayout.EAST);
	}

	@Override
	public void botonPresionado(Evento e) {
		super.botonPresionado(e);
		if (e.getSource() == getbPagar()) {
			getPanelConfi().removeAll();
			getPanelConfi().setLayout(new BorderLayout());
			MedioPagoConfi comp = new MedioPagoConfi(getCliente(), getCarrito());
			comp.addPagoListener(this);
			getPanelConfi().add(comp);
			getPanelConfi().setBorder(null);
			getPanelConfi().revalidate();
			getPanelConfi().repaint();
			getInferior().setPreferredSize(0, 0);
			getInferior().revalidate();
		}
		if (e.getSource() == getbVaciar()) {
			carrito.clear();
			panelPrevio.setInfo(carrito);
		}
		if (e.getSource() == getbCancelar())
			dispose();

	}

	public static void main(String[] args) {
		VentanaConfi p = new VentanaConfi(new Cliente("Juan Antonio de las Nieves", 1098343206));
		p.setVisible(true);
	}

	@Override
	public void pagoRealizado(String infoExtra) {
		JOptionPane.showMessageDialog(null, "Pago realizado con éxito", "Info", JOptionPane.INFORMATION_MESSAGE);
		botonPresionado(new Evento(getbCancelar()));
	}

	@Override
	public void pagoNoRealizado(SQLException e) {
		JOptionPane.showMessageDialog(null, "Error: " + e, "Error", JOptionPane.ERROR_MESSAGE);
	}

	@Override
	public void volver() {
		getPanelConfi().revalidate();
		getPanelConfi().repaint();
		getInferior().setPreferredSize(1, 40);
		getInferior().revalidate();
	}

	public Text getbPagar() {
		return bPagar;
	}

	public void setbPagar(Text bPagar) {
		this.bPagar = bPagar;
	}

	public Text getbCancelar() {
		return bCancelar;
	}

	public void setbCancelar(Text bCancelar) {
		this.bCancelar = bCancelar;
	}

	public Panel getInferior() {
		return inferior;
	}

	public void setInferior(Panel inferior) {
		this.inferior = inferior;
	}

	public SelecionConfi getPanelConfi() {
		return panelConfi;
	}

	public void setPaneConfi(SelecionConfi panelConfi) {
		this.panelConfi = panelConfi;
	}

	@Override
	public void configurarTam() {
		setSize(1100, 550);

	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente c) {
		this.cliente = c;
	}

	public Text getbVaciar() {
		return bVaciar;
	}

	public void setbVaciar(Text bCarrito) {
		this.bVaciar = bCarrito;
	}

	public ListaConfiteria getCarrito() {
		return carrito;
	}

	public void setCarrito(ListaConfiteria carrito) {
		this.carrito = carrito;
	}
}