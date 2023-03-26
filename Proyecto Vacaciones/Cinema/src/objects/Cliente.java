package objects;

public class Cliente {
	private long CC = -1;
	private String name;
	private int frecuency;
	private TarjetaUQ tBasic;
	private TarjetaUQ tGold;

	public Cliente(String name, long CC) {
		this(name, CC, 0, TarjetaUQ.ninguna(), TarjetaUQ.ninguna());
	}

	public Cliente() {
		this(null, -1, -1, TarjetaUQ.ninguna(), TarjetaUQ.ninguna());
	}

	public Cliente(String name, long CC, TarjetaUQ tBasic, TarjetaUQ tGold) {
		this(name, CC, 0, tBasic, tGold);
	}

	public Cliente(String name, long CC, int frecuency, TarjetaUQ tBasic, TarjetaUQ tGold) {
		setCC(CC);
		setName(name);
		setFrecuency(frecuency);
		settBasic(tBasic);
		settGold(tGold);
	}

	public String getStringCC () {
		StringBuilder c = new StringBuilder(getCC() + "");
		while (c.length() < 10) {
			c = new StringBuilder("0" + c.toString());
		}
		return c.toString();
	}

	public long getCC () {
		return CC;
	}

	public void setCC (long cc) {
		this.CC = cc;
	}

	public String getName () {
		return name;
	}

	public void setName (String name) {
		this.name = name;
	}

	public int getFrecuency () {
		return frecuency;
	}

	public void setFrecuency (int frecuency) {
		this.frecuency = frecuency;
	}

	public TarjetaUQ gettBasic () {
		return tBasic;
	}

	public void settBasic (TarjetaUQ tBasic) {
		this.tBasic = tBasic;
	}

	public TarjetaUQ gettGold () {
		return tGold;
	}

	public void settGold (TarjetaUQ tGold) {
		this.tGold = tGold;
	}

	public static Cliente parse (String c) {
		if (c.equals("n/a")) {
			return new Cliente();
		}
		StringBuilder sb = new StringBuilder(c);
		sb.deleteCharAt(0);
		sb.deleteCharAt(sb.length() - 1);
		String info[] = sb.toString().split(",");
		String name = info[0];
		long cc = Long.parseLong(info[1]);
		int frec = Integer.parseInt(info[2]);
		TarjetaUQ tBasic = TarjetaUQ.parse(info[3]);
		TarjetaUQ tGold = TarjetaUQ.parse(info[4]);
		return new Cliente(name, cc, frec, tBasic, tGold);
	}

	public static Cliente ninguno () {
		return new Cliente();
	}

	public boolean existe () {
		return !toString().equals("n/a");
	}

	public String toString () {
		return getCC() == -1 || getName() == null ? "n/a"
				: "{" + getName() + "," + getCC() + "," + getFrecuency() + "," + gettBasic() + "," + gettGold() + "}";
	}

}
