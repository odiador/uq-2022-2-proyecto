package confiteria;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

import custom.ClicListener;
import custom.CustomListener;
import custom.Evento;
import custom.EventoTf;
import custom.Panel;
import custom.Text;
import custom.TextfieldListener;
import objects.Confiteria;
import objects.ListaConfiteria;

public class PanelCantidadConfi extends Panel implements TextfieldListener, ClicListener {
	private SeleccionPreviaConfi panelPrevia;
	private SeleccionCantidadConfi panelCantidad;
	private Confiteria confi;
	private Text bAceptar, bVolver;
	private ListaConfiteria carrito;

	public PanelCantidadConfi() {
		
	}

	public PanelCantidadConfi(String tipo, double valUnitario, ListaConfiteria carrito) {
		setCarrito(carrito);
		setConfi(new Confiteria(tipo, valUnitario));
		setPanelCantidad(new SeleccionCantidadConfi(getConfi()));
		setPanelPrevia(new SeleccionPreviaConfi(getConfi(), 0));
		setbAceptar(new Text("Aceptar"));
		setbVolver(new Text("Volver"));

		getbAceptar().setOpaque(true);
		getbVolver().setOpaque(true);
		getbAceptar().setCanBeSelectioned(true);
		getbVolver().setCanBeSelectioned(true);
		getbAceptar().setBackground(getbAceptar().getBackground().brighter());
		getbVolver().setBackground(getbVolver().getBackground().brighter());
		getbAceptar().addClicListener(this);
		getbVolver().addClicListener(this);

		getbAceptar().setPreferredSize(300, 40);
		getbVolver().setPreferredSize(300, 40);

		getPanelCantidad().addTextFieldListener(this);

		Panel contenedor = new Panel();
		contenedor.setLayout(new BorderLayout(0, 10));

		contenedor.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));

		contenedor.add(getPanelCantidad(), BorderLayout.NORTH);
		contenedor.add(getPanelPrevia());

		Panel contenedor2 = new Panel();
		contenedor2.setLayout(new GridLayout(1, 2));
		contenedor2.add(getbAceptar());
		contenedor2.add(getbVolver());

		add(contenedor);
		add(contenedor2, BorderLayout.SOUTH);

	}

	@Override
	public void textfieldUpdate (EventoTf e) {
		getPanelPrevia().actualizar(e.getIntNumber());
	}

	@Override
	public void botonPresionado (Evento e) {
		if (e.getSource() == getbAceptar()) {
			int cantidad = getPanelCantidad().getNumber();
			if (cantidad == 0) {
				JOptionPane.showMessageDialog(null, "La cantidad no puede ser 0", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			getConfi().setCantidad(cantidad);
			getCarrito().addItemCarrito(getConfi());
			for (CustomListener l : getCustomListeners())
				l.accionRealizada();
		}
		removeAll();
		setLayout(new BorderLayout());
		SelecionConfi selecionConfi = new SelecionConfi(getCarrito());
		selecionConfi.addCustomListener(() -> {
			for (CustomListener l : getCustomListeners())
				l.accionRealizada();
		});
		add(selecionConfi);
		revalidate();
		repaint();
	}

	public ListaConfiteria getCarrito () {
		return carrito;
	}

	public void setCarrito (ListaConfiteria carrito) {
		this.carrito = carrito;
	}

	public Confiteria getConfi () {
		return confi;
	}

	public void setConfi (Confiteria confi) {
		this.confi = confi;
	}

	public SeleccionCantidadConfi getPanelCantidad () {
		return panelCantidad;
	}

	public void setPanelCantidad (SeleccionCantidadConfi panelCantidad) {
		this.panelCantidad = panelCantidad;
	}

	public Text getbAceptar () {
		return bAceptar;
	}

	public void setbAceptar (Text bAceptar) {
		this.bAceptar = bAceptar;
	}

	public Text getbVolver () {
		return bVolver;
	}

	public void setbVolver (Text bVolver) {
		this.bVolver = bVolver;
	}

	public SeleccionPreviaConfi getPanelPrevia () {
		return panelPrevia;
	}

	public void setPanelPrevia (SeleccionPreviaConfi panelPrevia) {
		this.panelPrevia = panelPrevia;
	}

}
