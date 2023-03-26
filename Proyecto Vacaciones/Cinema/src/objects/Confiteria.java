package objects;

public class Confiteria {

	private String tipo;
	private double valUnitario;
	private int cantidad;

	public Confiteria(String tipo, double valUnitario) {
		this(tipo, valUnitario, 0);
	}

	public Confiteria(String tipo, double valUnitario, int cantidad) {
		setTipo(tipo);
		setCantidad(cantidad);
		setValUnitario(valUnitario);
	}

	public double getPrecio () {
		return getPrecio(getCantidad());
	}

	public double getPrecio (int cantidad) {
		setCantidad(cantidad);
		return getValUnitario() * getCantidad();
	}

	public double getValUnitario () {
		return valUnitario;
	}

	public void setValUnitario (double valUnitario) {
		this.valUnitario = valUnitario;
	}

	public String getProducto () {
		return tipo;
	}

	public void setTipo (String tipo) {
		this.tipo = tipo;
	}

	public int getCantidad () {
		return cantidad;
	}

	public void setCantidad (int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString () {
		return "<" + getProducto() + "-" + getValUnitario() + "-" + getCantidad() + ">";
	}
}