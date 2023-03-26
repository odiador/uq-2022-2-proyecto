package objects;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class TarjetaUQ {
	public TarjetaUQ(LocalDateTime fechaHora, double dinero, int puntos) {
		setFechaHora(fechaHora);
		setDinero(dinero);
		setPuntos(puntos);
	}

	public TarjetaUQ(double dinero, int puntos) {
		if (dinero >= 0 && puntos >= 0) {
			setFechaHora(LocalDateTime.now());
			setDinero(dinero);
			setPuntos(puntos);
		}
	}

	public TarjetaUQ(double dinero) {
		this(dinero, 0);
	}

	public TarjetaUQ() {
		this(0, 0);
	}

	public static TarjetaUQ ninguna () {
		return new TarjetaUQ(-1);
	}

	public double getDinero () {
		return dinero;
	}

	public void setDinero (double dinero) {
		this.dinero = dinero;
	}

	public int getPuntos () {
		return puntos;
	}

	public void setPuntos (int puntos) {
		this.puntos = puntos;
	}

	public void addDinero (double dinero) throws CustomException {
		if (getPuntos() == -1) throw new CustomException("No hay tarjeta", CustomException.TARJETA_NO_EXISTE);
		setDinero(getDinero() + dinero);
	}

	public void addPuntos (int puntos) throws CustomException {
		if (getPuntos() == -1) throw new CustomException("No hay tarjeta", CustomException.TARJETA_NO_EXISTE);
		setPuntos(getPuntos() + puntos);
	}

	public int removePuntos (int puntos) throws CustomException {
		int code = ERROR;
		if (getPuntos() == -1) throw new CustomException("No hay tarjeta", CustomException.TARJETA_NO_EXISTE);
		if (getPuntos() >= puntos) {
			setPuntos(getPuntos() - puntos);
			code = 0;
		} else {
			throw new CustomException("No hay suficientes puntos (" + getPuntos() + "-" + puntos + ") = negative",
					CustomException.NO_HAY_PUNTOS);
		}
		return code;
	}

	public void removeDinero (double dinero) throws CustomException {
		if (getDinero() == -1) {
			throw new CustomException("No hay tarjeta", CustomException.TARJETA_NO_EXISTE);
		}
		if (getDinero() >= dinero) {
			setDinero(getDinero() - dinero);
		} else {
			throw new CustomException("No hay suficiente dinero (" + getDinero() + "-" + dinero + ") = negative",
					CustomException.NO_HAY_DINERO);
		}
	}

	public LocalDateTime getFechaHora () {
		return fechaHora;
	}

	public void setFechaHora (LocalDateTime fechaHora) {
		this.fechaHora = fechaHora;
	}

	public String getHoraCompleta () {
		if (getFechaHora() == null) return null;
		return getFechaHora().format(DateTimeFormatter.ofPattern("hh:mm:ss"));

	}

	public String getHoraCompleta (String format) {
		if (getFechaHora() == null) return null;
		String hora;
		try {
			hora = getFechaHora().format(DateTimeFormatter.ofPattern(format));
		} catch (IllegalArgumentException e) {
			hora = getFecha();
			e.printStackTrace();
		}
		return hora;
	}

	public String getFecha () {
		if (getFechaHora() == null) return null;
		return getFechaHora().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

	}

	public String getFecha (String format) {
		if (getFechaHora() == null) return null;
		if (format == null) return getFecha();
		String fecha;
		try {
			fecha = getFechaHora().format(DateTimeFormatter.ofPattern(format));
		} catch (IllegalArgumentException e) {
			fecha = getFecha();
			e.printStackTrace();
		}

		return fecha;
	}

	public int getSecond () {
		if (getFechaHora() == null) return -1;
		return getFechaHora().getSecond();
	}

	public int getMinute () {
		if (getFechaHora() == null) return -1;
		return getFechaHora().getMinute();
	}

	public int getHour () {
		if (getFechaHora() == null) return -1;
		return getFechaHora().getHour();
	}

	public int getDay () {
		if (getFechaHora() == null) return -1;
		return getFechaHora().getDayOfMonth();
	}

	public int getDayOfYear () {
		if (getFechaHora() == null) return -1;
		return getFechaHora().getDayOfYear();
	}

	public Month getMonth () {
		if (getFechaHora() == null) return null;
		return getFechaHora().getMonth();
	}

	public int getYear () {
		if (getFechaHora() == null) return -1;
		return getFechaHora().getYear();
	}

	public boolean getExisteTarjeta () {
		return getDinero() != -1 & getPuntos() != -1 && getFechaHora() != null;
	}

	public static TarjetaUQ parse (String tarj) {
		if (tarj.equals("n/a")) {
			return ninguna();
		}
		StringBuilder b = new StringBuilder(tarj);
		b.deleteCharAt(0);
		b.deleteCharAt(b.length() - 1);
		String[] tarjetaArr = b.toString().split("=");
		double dinero = Double.parseDouble(tarjetaArr[0]);
		int puntos = Integer.parseInt(tarjetaArr[1]);
		LocalDateTime dia = LocalDateTime.parse(tarjetaArr[2]);
		return new TarjetaUQ(dia, dinero, puntos);
	}

	public String toString () {
		return !getExisteTarjeta() ? "n/a" : "{" + getDinero() + "=" + getPuntos() + "=" + getFechaHora() + "}";
	}

	private static final int ERROR = Integer.MAX_VALUE;
	private double dinero = -1;
	private int puntos = -1;
	private LocalDateTime fechaHora;
}