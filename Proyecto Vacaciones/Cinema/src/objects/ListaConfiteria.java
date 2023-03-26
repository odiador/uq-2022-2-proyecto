package objects;

import java.util.ArrayList;

public class ListaConfiteria {

	ArrayList<Confiteria> lista = new ArrayList<Confiteria>();

	public ListaConfiteria() {
		lista = new ArrayList<Confiteria>();
	}

	public void addItemCarrito (Confiteria e) {
		if (!searchItem(e)) lista.add(e);
		else {
			int pos = getPosCarrito(e);
			Confiteria anterior = lista.get(pos);
			Confiteria nueva = new Confiteria(e.getProducto(), anterior.getValUnitario(),
					anterior.getCantidad() + e.getCantidad());
			lista.set(getPosCarrito(e), nueva);
		}
	}
	public Confiteria get (int i) {
		return lista.get(i);
	}

	public int getSize () {
		return lista.size();
	}

	public int getPosCarrito (Confiteria e) {
		int pos = -1;
		for (int i = 0; i < lista.size(); i++) if (lista.get(i).getProducto().equals(e.getProducto())) pos = i;
		return pos;
	}

	public double getTotalCarrito () {
		double ret = 0d;
		for (Confiteria c : lista) ret += c.getPrecio();
		return ret;
	}

	public boolean searchItem (Confiteria e) {
		String tipo = e.getProducto();
		for (Confiteria c : lista) if (tipo.equals(c.getProducto())) return true;
		return false;
	}

	@Override
	public String toString () {
		return lista.toString();
	}
}
