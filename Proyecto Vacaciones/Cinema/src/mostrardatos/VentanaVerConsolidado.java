package mostrardatos;

import javax.swing.WindowConstants;

import custom.PanelConScroll;
import custom.Plantilla;
import objects.Compra;

public class VentanaVerConsolidado extends Plantilla {
	public static final int CONFITERIA = 0;
	public static final int CINE = 1;
	public static final int AMBOS = 2;

	public VentanaVerConsolidado(Compra[] compras, int tipo) {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		agregarBody(new PanelConScroll(new PanelVerConsolidado(compras, tipo)));
	}

	@Override
	public void configurarTam () {
		setSize(1100, 550);
	}

}