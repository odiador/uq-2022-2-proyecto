package vista;

import custom.Text;

public class HeaderData {
	private Text textoHeader;
	private Text textoImg;

	public HeaderData() {}

	public Text getTextoHeader () {
		return textoHeader;
	}

	public void setTextoHeader (Text textoHeader) {
		this.textoHeader = textoHeader;
	}

	public Text getTextoImg () {
		return textoImg;
	}

	public void setTextoImg (Text textoImg) {
		this.textoImg = textoImg;
	}
}