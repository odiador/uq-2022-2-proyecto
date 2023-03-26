package confiteria;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;

import config.Constantes;
import custom.ClicListener;
import custom.Evento;
import custom.Panel;
import custom.Text;
import objects.ListaConfiteria;

public class CarritoConfi extends Panel implements ClicListener {

	private ListaConfiteria carrito;
	private Text bVolver;

	public CarritoConfi(ListaConfiteria carrito) {
		setCarrito(carrito);
		setbVolver(new Text("Volver", Constantes.defaultFont.deriveFont(25f)));
		getbVolver().setCanBeSelectioned(true);
		getbVolver().setBackground(Text.brighter);
		getbVolver().setOpaque(true);
		getbVolver().addClicListener(this);
		setLayout(new BorderLayout());
		add(getbVolver(), BorderLayout.SOUTH);
	}

	public ListaConfiteria getCarrito () {
		return carrito;
	}

	public void setCarrito (ListaConfiteria carrito) {
		this.carrito = carrito;
	}

	public Text getbVolver () {
		return bVolver;
	}

	public void setbVolver (Text bVolver) {
		this.bVolver = bVolver;
	}

	@Override
	public void botonPresionado (Evento e) {
		VentanaConfi frame = ((VentanaConfi) getRootPane().getParent());
		if (e.getSource() == getbVolver()) {
			removeAll();
			setLayout(new BorderLayout());
			setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
			add(new SelecionConfi(getCarrito()));
			frame.getInferior().setPreferredSize(300, 30);
			frame.getInferior().revalidate();
			revalidate();
			repaint();
		}
	}

}
