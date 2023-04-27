package objetos;

public class Cliente {

	public static final int ERROR = Integer.MAX_VALUE;
	public static final int PERFECTO = 0;

	private String nombre;
	private String id;
	private TarjetaUQ tarjBasic;
	private TarjetaUQ tarjGold;
	private int frecuenciaCine;
	private int puntos;

	public Cliente(String nombre, String id) {
		setNombre(nombre);
		setId(id);
		setTarjBasic(null);
		setTarjGold(null);
		setPuntos(0);
		setFrecuenciaCine(0);
	}

	public void addTarjBasic(double dinero) {
		if (getTarjBasic() == null)
			setTarjBasic(new TarjetaUQ(dinero));
	}

	public void addTarjGold(double dinero) {
		if (getTarjGold() == null)
			setTarjGold(new TarjetaUQ(dinero));
	}

	public void addFrecuenciasCine(int cantidad) {
		setFrecuenciaCine(getFrecuenciaCine() + cantidad);
	}

	public void reiniciarFrecuenciasCine() {
		setFrecuenciaCine(0);
	}

	public void addPuntos(int puntos) {
		setPuntos(getPuntos() + puntos);
	}

	public int removePuntos(int puntos) {
		int code = ERROR;
		if (getPuntos() >= puntos) {
			setPuntos(getPuntos() - puntos);
			code = 0;
		}
		return code;
	}

	public int removeDineroBasic(double dinero) {
		int code = ERROR;
		if (getTarjBasic() != null) {
			code = getTarjBasic().removeDinero(dinero);
		}
		return code;
	}

	public int removeDineroGold(double dinero) {
		int code = ERROR;
		if (getTarjGold() != null) {
			code = getTarjGold().removeDinero(dinero);
		}
		return code;
	}

	public int addDineroGold(double dinero) {
		int code = ERROR;
		if (getTarjGold() != null) {
			getTarjGold().addDinero(dinero);
			code = 0;
		}
		return code;
	}

	public int addDineroBasic(double dinero) {
		int code = ERROR;
		if (getTarjBasic() != null) {
			getTarjBasic().addDinero(dinero);
			code = 0;
		}
		return code;
	}

	public int agregarTarjetaBasic(double dinero) {
		int code = ERROR;
		if (getTarjBasic() == null) {
			setTarjBasic(new TarjetaUQ(dinero));
			code = 0;
		}
		return code;
	}

	public int agregarTarjetaGold(double dinero) {
		int code = ERROR;
		if (getTarjGold() == null) {
			setTarjGold(new TarjetaUQ(dinero));
			code = 0;
		}
		return code;
	}

	private void setNombre(String nombre) {
		this.nombre = nombre;
	}

	private void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getId() {
		return id;
	}

	public TarjetaUQ getTarjGold() {
		return tarjGold;
	}

	public void setTarjGold(TarjetaUQ tarjGold) {
		this.tarjGold = tarjGold;
	}

	public TarjetaUQ getTarjBasic() {
		return tarjBasic;
	}

	public void setTarjBasic(TarjetaUQ tarjBasic) {
		this.tarjBasic = tarjBasic;
	}

	public int getFrecuenciaCine() {
		return frecuenciaCine;
	}

	public void setFrecuenciaCine(int frecuenciaCine) {
		this.frecuenciaCine = frecuenciaCine;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public String toString() {
		return getNombre() + " | " + getId() + " | Basic " + getTarjBasic() + " | Gold " + getTarjGold();
	}
}
