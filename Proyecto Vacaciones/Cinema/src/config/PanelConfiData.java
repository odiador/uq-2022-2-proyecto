package config;

import custom.Panel;
import custom.Text;

public class PanelConfiData {
	private int status;
	private Panel[] paneles;
	private Text[] texts;
	private Text[] imgs;

	public PanelConfiData(int status) {
		this.status = status;
	}

	public int getStatus () {
		return status;
	}

	public void setStatus (int status) {
		this.status = status;
	}

	public Panel[] getPaneles () {
		return paneles;
	}

	public void setPaneles (Panel[] paneles) {
		this.paneles = paneles;
	}

	public Text[] getTexts () {
		return texts;
	}

	public void setTexts (Text[] texts) {
		this.texts = texts;
	}

	public Text[] getImgs () {
		return imgs;
	}

	public void setImgs (Text[] imgs) {
		this.imgs = imgs;
	}

	public String[] getStrings () {
		return Constantes.strings;
	}
}