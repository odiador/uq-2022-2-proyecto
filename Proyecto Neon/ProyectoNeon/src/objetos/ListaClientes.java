package objetos;

import java.util.ArrayList;
import java.util.List;

public class ListaClientes {

	public static void main(String[] args) {
		ListaClientes claseLista = new ListaClientes(null);
		for (int i = 0; i < 5; i++) {
			claseLista.addCliente("Carlos", "1000" + i);
		}
		claseLista.addTarjBasicCliente("10002", 10000);
		System.out.println(claseLista.addDineroBasicCliente("10002", 10000));
		claseLista.removeDineroBasicCliente("10002", 300);
		System.out.println(claseLista);
	}

	List<Cliente> lista;
	public static final int NO_SE_ENCONTRO = -1;

	public ListaClientes(List<Cliente> lista) {
		if (lista == null) {
			setLista(new ArrayList<>());
		} else {
			setLista(lista);
		}
	}

	public int buscarCliente(String id) {
		int pos = -1;
		int index = 0;
		for (Cliente c : getLista()) {
			if (id.equals(c.getId())) {
				pos = index;
			}
			index++;
		}
		return pos;
	}

	public int addCliente(String nombre, String id) {
		int code = Integer.MAX_VALUE;
		if (!seEncontroCliente(id)) {
			getLista().add(new Cliente(nombre, id));
			code = 0;
		}
		return code;
	}

	public int addTarjBasicCliente(String id, double dinero) {
		int code = NO_SE_ENCONTRO;
		if (seEncontroCliente(id)) {
			getCliente(buscarCliente(id)).addTarjBasic(dinero);
			code = 0;
		}
		return code;
	}

	public int addTarjGoldCliente(String id, double dinero) {
		int code = NO_SE_ENCONTRO;
		if (seEncontroCliente(id)) {
			getCliente(buscarCliente(id)).addTarjGold(dinero);
			code = 0;
		}
		return code;
	}

	public int addDineroBasicCliente(String id, double dinero) {
		int code = NO_SE_ENCONTRO;
		if (seEncontroCliente(id)) {
			code = getCliente(buscarCliente(id)).addDineroBasic(dinero);
		}
		return code;
	}

	public int addDineroGoldCliente(String id, double dinero) {
		int code = NO_SE_ENCONTRO;
		if (seEncontroCliente(id)) {
			code = getCliente(buscarCliente(id)).addDineroGold(dinero);
		}
		return code;
	}

	public int removeDineroBasicCliente(String id, double dinero) {
		int code = NO_SE_ENCONTRO;
		if (seEncontroCliente(id)) {
			code = getCliente(buscarCliente(id)).removeDineroBasic(dinero);
		}
		return code;
	}

	public int removeDineroGoldCliente(String id, double dinero) {
		int code = NO_SE_ENCONTRO;
		if (seEncontroCliente(id)) {
			code = getCliente(buscarCliente(id)).removeDineroGold(dinero);
		}
		return code;
	}

	public boolean seEncontroCliente(String id) {
		return buscarCliente(id) != NO_SE_ENCONTRO;
	}

	public Cliente getCliente(int index) {
		return getLista().get(index);
	}

	public Cliente getCliente(String id) {
		if (seEncontroCliente(id)) {
			return getCliente(buscarCliente(id));
		}
		return null;
	}

	public List<Cliente> getLista() {
		return lista;
	}

	public void setLista(List<Cliente> lista) {
		this.lista = lista;
	}

	public String toString() {
		String ret = "";
		for (int i = 0; i < lista.size(); i++) {
			ret += "[" + lista.get(i).toString() + "]\n";
		}
		return ret;
	}
}