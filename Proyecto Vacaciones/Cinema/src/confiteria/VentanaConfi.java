package confiteria;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
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
	private Text bPagar, bCarrito, bCancelar, tTitulo;
	private Panel inferior;
	private SelecionConfi panelConfi;
	private ListaConfiteria carrito = new ListaConfiteria();
	private Cliente cliente;

	public VentanaConfi(Cliente c) {
		setCliente(c);

		Font f = Constantes.defaultFont.deriveFont(25f);
		settTitulo(new Text("Nombre: " + c.getName(), f));
		setbCancelar(new Text("Cancelar", f));
		setbPagar(new Text("Pagar", f));
		setbCarrito(new Text("Ver Carrito", f));

		setPaneConfi(new SelecionConfi(getCarrito()));

		getPanelConfi().setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		gettTitulo().setOpaque(true);
		getbCancelar().configAsButton(this);
		getbPagar().configAsButton(this);
		getbCarrito().configAsButton(this);

		gettTitulo().setVerticalAlignment(SwingConstants.CENTER);
		gettTitulo().setPreferredSize(300, 100);

		setInferior(new Panel());
		getInferior().setLayout(new GridLayout(1, 3));
		getInferior().setPreferredSize(1, 40);

		getInferior().add(getbPagar());
		getInferior().add(getbCarrito());
		getInferior().add(getbCancelar());

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		agregarBody(gettTitulo(), BorderLayout.NORTH);
		agregarBody(getPanelConfi(), BorderLayout.CENTER);
		agregarBody(getInferior(), BorderLayout.SOUTH);
	}

	@Override
	public void botonPresionado (Evento e) {
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
		if (e.getSource() == getbCarrito()) {
			getPanelConfi().removeAll();
			getPanelConfi().setLayout(new BorderLayout());
			getPanelConfi().add(new CarritoConfi(getCarrito()));
			getPanelConfi().setBorder(null);
			getPanelConfi().revalidate();
			getPanelConfi().repaint();
			getInferior().setPreferredSize(0, 0);
			getInferior().revalidate();
		}
		if (e.getSource() == getbCancelar()) dispose();

	}

	public static void main (String[] args) {
		VentanaConfi p = new VentanaConfi(new Cliente("Juan Antonio de las Nieves", 1098343206));
		p.setVisible(true);
	}

	@Override
	public void pagoRealizado (String infoExtra) {
		JOptionPane.showMessageDialog(null, "Pago realizado con éxito", "Info", JOptionPane.INFORMATION_MESSAGE);
		botonPresionado(new Evento(getbCancelar()));
	}

	@Override
	public void pagoNoRealizado (SQLException e) {
		JOptionPane.showMessageDialog(null, "Error: " + e, "Error", JOptionPane.ERROR_MESSAGE);
	}

	@Override
	public void volver () {
		getPanelConfi().revalidate();
		getPanelConfi().repaint();
		getInferior().setPreferredSize(1, 40);
		getInferior().revalidate();
	}

	public Text getbPagar () {
		return bPagar;
	}

	public void setbPagar (Text bPagar) {
		this.bPagar = bPagar;
	}

	public Text getbCancelar () {
		return bCancelar;
	}

	public void setbCancelar (Text bCancelar) {
		this.bCancelar = bCancelar;
	}

	public Text gettTitulo () {
		return tTitulo;
	}

	public void settTitulo (Text tTitulo) {
		this.tTitulo = tTitulo;
	}

	public Panel getInferior () {
		return inferior;
	}

	public void setInferior (Panel inferior) {
		this.inferior = inferior;
	}

	public SelecionConfi getPanelConfi () {
		return panelConfi;
	}

	public void setPaneConfi (SelecionConfi panelConfi) {
		this.panelConfi = panelConfi;
	}

	@Override
	public void configurarTam () {
		setSize(800, 500);

	}

	public Cliente getCliente () {
		return cliente;
	}

	public void setCliente (Cliente c) {
		this.cliente = c;
	}

	public Text getbCarrito () {
		return bCarrito;
	}

	public void setbCarrito (Text bCarrito) {
		this.bCarrito = bCarrito;
	}

	public ListaConfiteria getCarrito () {
		return carrito;
	}

	public void setCarrito (ListaConfiteria carrito) {
		this.carrito = carrito;
	}
}