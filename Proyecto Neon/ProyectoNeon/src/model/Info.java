package model;

import objetos.ListaClientes;

public class Info {
	private ListaClientes infoClientes;
	private boolean sillasLLenas[][];

	public Info(ListaClientes infoClientes, boolean sillasLLenas[][]) {
		setInfoClientes(infoClientes);
		setSillasLLenas(sillasLLenas);
		if (getSillasLLenas() == null) {
			setSillasLLenas(new boolean[13][16]);
		}
		if (getInfoClientes() == null)
			setInfoClientes(new ListaClientes(null));

	}

	public ListaClientes getInfoClientes() {
		return infoClientes;
	}

	public void setInfoClientes(ListaClientes infoClientes) {
		this.infoClientes = infoClientes;
	}

	public boolean[][] getSillasLLenas() {
		return sillasLLenas;
	}

	public void setSillasLLenas(boolean sillasLLenas[][]) {
		this.sillasLLenas = sillasLLenas;
	}
}
