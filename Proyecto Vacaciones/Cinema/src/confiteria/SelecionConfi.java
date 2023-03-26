package confiteria;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.GridLayout;

import javax.swing.ImageIcon;

import config.Constantes;
import config.PanelConfiData;
import custom.ClicListener;
import custom.Evento;
import custom.Panel;
import custom.Text;
import objects.ListaConfiteria;
import tools.Imgs;

public class SelecionConfi extends Panel implements ClicListener {

	private static ImageIcon imagenes[];

	private PanelConfiData data = new PanelConfiData(0);

	private ListaConfiteria carrito;

	public SelecionConfi(ListaConfiteria carrito) {
		this.carrito = carrito;
		int grid = 2;
		setLayout(new GridLayout(grid, getStrings().length / grid, 5, 5));

		if (getImagenes() == null) setImagenes(new ImageIcon[getStrings().length]);

		setTexts(new Text[getStrings().length]);
		setImgs(new Text[getStrings().length]);
		setPaneles(new Panel[getStrings().length]);

		for (int i = 0; i < getStrings().length; i++) {
			if (getImagenes()[i] == null) {
				getImagenes()[i] = new ImageIcon("images/" + getStrings()[i] + ".png");
				getImagenes()[i] = (getImagenes()[i].getImageLoadStatus() == 8) ? getImagenes()[i]
						: new ImageIcon("images/Stellar Cinema.png");
			}
			getTexts()[i] = new Text(getStrings()[i]);
			getImgs()[i] = new Text(Imgs.generarImagenRedonda(getImagenes()[i], Constantes.minImgConfiSize,
					Constantes.minImgConfiSize, Constantes.minImgConfiRnd));
			getPaneles()[i] = new Panel();

			getTexts()[i].setPreferredSize(300, 30);

			getPaneles()[i].setLayout(new BorderLayout());

			getPaneles()[i].setCanBeSelectioned(true);
			getPaneles()[i].addClicListener(this);
			getPaneles()[i].setCursor(new Cursor(Cursor.HAND_CURSOR));

			getPaneles()[i].add(getTexts()[i], BorderLayout.NORTH);
			getPaneles()[i].add(getImgs()[i], BorderLayout.CENTER);
			getPaneles()[i].setBackground(getBackground().brighter());

			add(getPaneles()[i]);
		}
	}

	@Override
	public void botonPresionado (Evento e) {
		Panel s = (Panel) e.getSource();
		String tipo = ((Text) s.getComponents()[0]).getText();
		removeAll();
		setLayout(new BorderLayout());
		setStatus(1);
		add(new Pruebas(tipo, 5000, carrito));
		revalidate();
		repaint();
	}

	public Panel[] getPaneles () {
		return data.getPaneles();
	}

	public void setPaneles (Panel paneles[]) {
		data.setPaneles(paneles);
	}

	public Text[] getTexts () {
		return data.getTexts();
	}

	public void setTexts (Text texts[]) {
		data.setTexts(texts);
	}

	public Text[] getImgs () {
		return data.getImgs();
	}

	public void setImgs (Text imgs[]) {
		data.setImgs(imgs);
	}

	public String[] getStrings () {
		return data.getStrings();
	}

	public static ImageIcon[] getImagenes () {
		return imagenes;
	}

	public static void setImagenes (ImageIcon imagenes[]) {
		SelecionConfi.imagenes = imagenes;
	}

	/**
	 * 0 for the start panel 1 for the quantity panel 2 for the car panel
	 * 
	 * @return el status
	 */
	public int getStatus () {
		return data.getStatus();
	}

	public void setStatus (int status) {
		data.setStatus(status);
	}
}
