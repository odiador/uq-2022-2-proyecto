package objetos;

public class TarjetaUQ {
	private static final int ERROR = Integer.MAX_VALUE;
	private double dinero;

	public TarjetaUQ(double dinero) {
		setDinero(dinero);
	}

	public double getDinero() {
		return dinero;
	}

	public void setDinero(double dinero) {
		this.dinero = dinero;
	}

	public void addDinero(double dinero) {
		setDinero(getDinero() + dinero);
	}

	public int removeDinero(double dinero) {
		int code = ERROR;
		if (getDinero() >= dinero) {
			setDinero(getDinero() - dinero);
			code = 0;
		}
		return code;
	}

	public String toString() {
		return getDinero() + "";
	}
}