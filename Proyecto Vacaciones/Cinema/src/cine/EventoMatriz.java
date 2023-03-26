package cine;

import config.Utilities;

public class EventoMatriz {

	public static final int PREFERENCIAL = 1;
	public static final int GENERAL = -1;

	public static final int MIXTA = 0;

	public static final int BASIC = 1;
	public static final int NORMAL = 0;
	public static final int GOLD = 2;

	public static final int BASICPTS = 3;
	public static final int GOLDPTS = 4;

	private String[] selectedChairs;

	public EventoMatriz(String[] selectedChairs) {
		setSelectedChairs(selectedChairs);
	}

	private void setSelectedChairs (String[] selectedChairs) {
		this.selectedChairs = selectedChairs;
	}

	public int getCantidad () {
		return getSelectedChairs().length;
	}

	public String[] getSelectedChairs () {
		return selectedChairs;
	}

	public String getSelectedChairsString () {
		return Utilities.toString(getSelectedChairs(), ", ");
	}

	public double getPrecio () {
		String sillas[] = getSelectedChairs();
		double precio = 0;
		for (String silla : sillas) precio += getTipoSilla(silla) == GENERAL ? 12000 : 16000;
		return precio;
	}

	public String getPrecioString () {
		return "$" + Utilities.format(getPrecio());
	}

	public double getPrecioBasic () {
		return getPrecio() * 0.95d;
	}

	public String getPrecioBasicString () {
		return "$" + Utilities.format(getPrecioBasic());
	}

	public int getPtsBasic () {
		return ((Double) (getPrecioBasic() / 100d)).intValue();
	}

	public double getPrecioGold () {
		return getPrecio() * 0.8d;
	}

	public String getPrecioGoldString () {
		return "$" + Utilities.format(getPrecioGold());
	}

	public int getPtsGold () {
		return ((Double) ((getPrecioGold() / 100d) * 2)).intValue();
	}

	public static int getTipoSilla (String silla) {
		return silla.charAt(0) < 'J' ? GENERAL : PREFERENCIAL;
	}

	public int getTipoSilla () {
		String sillas[] = getSelectedChairs();
		boolean esMixta = false;
		int tipo = getTipoSilla(sillas[0]);
		for (String silla : sillas) if (getTipoSilla(silla) != tipo) {
			esMixta = true;
			break;
		}
		return esMixta ? MIXTA : tipo;
	}

	public static double getValUnitario (String silla) {
		return getValUnitario(silla, NORMAL);
	}

	public static double getValUnitario (String silla, int tipo) {
		double precio = getTipoSilla(silla) == GENERAL ? 12000d : 16000d;
		if (tipo == BASIC) precio *= 0.95d;
		if (tipo == GOLD) precio *= 0.8d;
		return precio;
	}

	public String calcValUnitarioString (String silla) {
		return "$" + Utilities.format(getValUnitario(silla));
	}

	public String calcValUnitarioString () {
		return getTipoSilla() == MIXTA ? "Mixto" : calcValUnitarioString(getSelectedChairs()[0]);
	}

	public String getTipoSillaString () {
		return getTipoSilla() == MIXTA ? "Mixto" : (getTipoSilla() == GENERAL ? "General" : "Preferencial");
	}
}
