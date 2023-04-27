package objetos;

import java.util.ArrayList;
import java.util.List;

public class ListadeConfiteria {

	private List<Confiteria> carrito;

	public ListadeConfiteria() {
		carrito = new ArrayList<>();
	}

	public int getSize() {
		return getCarrito().size();
	}

	public boolean estaVacia() {
		return getSize() == 0;
	}

	public Confiteria getElementodeCarrito(int i) {
		return getCarrito().get(i);
	}

	public int buscarPosTipo(String tipo) {
		int pos = -1;
		elFor: for (int i = 0; i < getCarrito().size(); i++) {
			if (getCarrito().get(i).getTipoDeCompra().equals(tipo)) {
				pos = i;
				break elFor;
			}
		}
		return pos;
	}

	public void agregarConfiteria(String tipoDeCompra, Double valorUnitario, int cantidad) {
		if (buscarPosTipo(tipoDeCompra) == -1) {
			Confiteria compra = new Confiteria(tipoDeCompra, valorUnitario, cantidad);
			getCarrito().add(compra);
		} else {
			addCantidadDeLista(tipoDeCompra, cantidad);
		}
	}

	public double getPrecioTotal() {
		double precio = 0;
		for (int i = 0; i < getCarrito().size(); i++) {
			precio += getCarrito().get(i).getPrecio();
		}
		return precio;
	}

	public void setCantidadDeLista(String tipo, int cantidad) {
		int pos = buscarPosTipo(tipo);
		if (pos == -1) {
			error("No se encontró la persona");
			return;
		}
		getElementodeCarrito(pos).setCantidad(cantidad);
	}

	public void addCantidadDeLista(String tipo, int cantidad) {
		int pos = buscarPosTipo(tipo);
		if (pos == -1) {
			error("No se encontró la persona");
			return;
		}
		getElementodeCarrito(pos).setCantidad(getElementodeCarrito(pos).getCantidad() + cantidad);
	}

	public void error(String mensaje) {
		new Exception("Error: " + mensaje).printStackTrace();
	}

	public double getPrecio(String tipo) {
		int pos = buscarPosTipo(tipo);
		if (pos == -1) {
			error("No se encontró la persona");
			return -1;
		}
		return getElementodeCarrito(pos).getPrecio();
	}

	/**
	 * @return the carrito
	 */
	public List<Confiteria> getCarrito() {
		return carrito;
	}

	public String toString() {
		return getCarrito().toString() + ", precio total: " + getPrecioTotal();
	}
}