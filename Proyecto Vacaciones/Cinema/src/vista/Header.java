package vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;

import custom.ImagenRedonda;
import custom.Panel;
import custom.Text;
import tools.Imgs;

public class Header extends Panel {

	public Header(String text, ImageIcon imagenHead) {
		setLayout(new GridBagLayout());
		setBackground(new Color(40, 40, 40));
		int anchoImg = 100;
		int altoImg = 100;
		int redondeo = 50;

		setTextoHeader(new Text(text));
		setTextoImg(new Text(new ImagenRedonda(Imgs.escalarImagen(imagenHead, anchoImg, altoImg, Imgs.SCALE_SMOOTH),
				anchoImg, altoImg, redondeo)));
		getTextoImg().setPreferredSize(new Dimension(anchoImg, altoImg));
		add(getTextoImg(), crearGridBag(0, 0, 0, 0, GridBagConstraints.NORTH, 0, 0));
		add(getTextoHeader(), crearGridBag(0, 1, 0, 0, GridBagConstraints.NORTH, 0, 20));
	}

	public Text getTextoHeader () {
		return data.getTextoHeader();
	}

	public void setTextoHeader (Text textoHeader) {
		this.data.setTextoHeader(textoHeader);
	}

	public Text getTextoImg () {
		return data.getTextoImg();
	}

	public void setTextoImg (Text textoImg) {
		this.data.setTextoImg(textoImg);
	}

	private HeaderData data = new HeaderData();
}
