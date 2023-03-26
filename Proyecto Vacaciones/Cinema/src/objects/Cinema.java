package objects;

import java.time.LocalDateTime;

import javax.swing.JOptionPane;

public class Cinema implements Comparable<Cinema> {
	private int id = -1;
	private int width = -1;
	private int height = -1;
	private String ocuppied;
	private LocalDateTime l;
	private String name;

	public static void main (String[] args) {
		LocalDateTime k = LocalDateTime.of(2023, 3, 31, 23, 30);
		LocalDateTime l = k.plusMonths(1).minusMinutes(10);
		LocalDateTime m = LocalDateTime.of(2023, 5, 30, 23, 20);
		System.out.println("Cine: " + k);
		System.out.println("Mes: " + l);
		System.out.println("Entrada: " + m);
		int num = l.compareTo(m);
		System.out.println(num);
		if (num < 0) JOptionPane.showMessageDialog(null,
				"<html><center><font color=#0000FF> La compra está fuera del rango de tiempo, <br>"
						+ "recuerda la compra se debe de hacer entre <font color=#FF0000>0 </font>y <font "
						+ "color=#FF0000>30</font> días de la función, <br>máximo 10 minutos antes."
						+ "</font></center></html>");

	}

	public static Cinema ninguno () {
		return new Cinema();
	}

	public boolean getExiste () {
		boolean b = true;
		if (getId() < 0) b = false;
		if (getName() == null) b = false;
		if (getWidth() < 0) b = false;
		if (getHeight() < 0) b = false;
		if (getMomentoCine() == null) b = false;
		if (getOcuppied() == null) b = false;

		return b;
	}

	public Cinema() {}

	public Cinema(int id, String name) {
		this(id, name, 16, 13);
	}

	public Cinema(int id, String name, int width, int height) {
		this(id, name, width, height, LocalDateTime.now());
	}

	public Cinema(int id, String name, int width, int height, LocalDateTime l) {
		this(id, name, width, height, "", l);
	}

	public Cinema(int id, String name, int width, int height, String ocuppied, LocalDateTime l) {
		setId(id);
		setName(name);
		setWidth(width);
		setHeight(height);
		setOcuppied(ocuppied);
		setMomentoCine(l.withSecond(0).withNano(0));
	}

	public String[] getOccupiedArr () {
		return getOcuppied().split(":");
	}

	public boolean[][] getMatrizBoolean () {
		if (getHeight() < 0 || getWidth() < 0) return new boolean[13][16];

		boolean res[][] = new boolean[getHeight()][getWidth()];
		for (int i = 0; i < res.length; i++) for (int j = 0; j < res[0].length; j++) {
			String silla = (char) ('A' + i) + "" + (j + 1);
			res[i][j] = getisOccupied(silla);
		}
		return res;
	}

	public boolean getisOccupied (String silla) {
		boolean ret = false;
		for (String ocupada : getOccupiedArr()) if (ocupada.equals(silla)) {
			ret = true;
			break;
		}
		return ret;
	}

	public int getId () {
		return id;
	}

	public void setId (int id) {
		this.id = id;
	}

	public int getWidth () {
		return width;
	}

	public void setWidth (int width) {
		this.width = width;
	}

	public int getHeight () {
		return height;
	}

	public void setHeight (int height) {
		this.height = height;
	}

	public String getOcuppied () {
		return ocuppied;
	}

	public void addOcupied (int posI, int posJ) {
		addOcupied((char) ('A' + posI) + "" + (posJ + 1));
	}

	public void addOcupied (String silla) {
		if (getOcuppied().isEmpty()) setOcuppied(silla);
		else setOcuppied(getOcuppied() + ":" + silla);
	}

	public void clearCinema () {
		setOcuppied("");
	}

	public int getCantLibres () {
		String[] occupiedArr = getOccupiedArr();
		int cant = getCantAsientos();
		if (!occupiedArr[0].isEmpty()) cant -= occupiedArr.length;
		return cant;
	}

	public void setOcuppied (String ocuppied) {
		this.ocuppied = ocuppied;
	}

	public LocalDateTime getMomentoCine () {

		return l;
	}

	public void setMomentoCine (LocalDateTime l) {
		this.l = l;
	}

	public static Cinema parse (String cinemaString) {
		if (cinemaString.equals("?")) return Cinema.ninguno();
		StringBuilder sb = new StringBuilder(cinemaString);
		sb.deleteCharAt(sb.length() - 1);
		sb.deleteCharAt(0);
		String arrInfo[] = sb.toString().split(";");

		int id = Integer.parseInt(arrInfo[0]);
		String name = arrInfo[1];
		int width = Integer.parseInt(arrInfo[2]);
		int height = Integer.parseInt(arrInfo[3]);
		String occupied = arrInfo[4];
		LocalDateTime momento = LocalDateTime.parse(arrInfo[5]);
		return new Cinema(id, name, width, height, occupied, momento);
	}

	@Override
	public String toString () {
		return getId() < 0 ? "?"
				: "<" + getId() + ";" + getName() + ";" + getHeight() + ";" + getWidth() + ";" + getOcuppied() + ";"
						+ getMomentoCine().toString() + ">";
	}

	public String getName () {
		return name;
	}

	public void setName (String name) {
		this.name = name;
	}

	public int getCantAsientos () {
		return getWidth() * getHeight();
	}

	@Override
	public int compareTo (Cinema o) {
		return o.getId() - getId();
	}
}
