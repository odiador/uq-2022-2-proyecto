package objetos;

import model.Herramientas;

public class Confiteria {
	private String tipoDeCompra;
	private double valorUnitario;
	private int cantidad;

	public Confiteria(String tipoDeCompra, double valorUnitario, int cantidad) {
		setCantidad(cantidad);
		setTipoDeCompra(tipoDeCompra);
		setValorUnitario(valorUnitario);
	}

	public Double getPrecio() {
		return getValUnitario() * getCantidad();
	}

	/**
	 * @return the valUnitario
	 */
	public double getValUnitario() {
		return valorUnitario;
	}

	/**
	 * @param valUnitario the valUnitario to set
	 */
	public void setValorUnitario(double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	/**
	 * @return the tipoDeCompra
	 */
	public String getTipoDeCompra() {
		return tipoDeCompra;
	}

	/**
	 * @param tipoDeCompra the tipoDeCompra to set
	 */
	public void setTipoDeCompra(String tipoDeCompra) {
		this.tipoDeCompra = tipoDeCompra;
	}

	/**
	 * @return the cantidad
	 */
	public int getCantidad() {
		return cantidad;
	}

	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String toString() {
		return "{Tipo: "+getTipoDeCompra()+", C/U: "+Herramientas.formatoSinDecimal(getValUnitario())+", Cantidad: "+getCantidad()+"}";
	}
}