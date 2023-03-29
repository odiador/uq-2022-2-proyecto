package model;

import java.util.HashSet;

public class Cinema {

	private String code;
	private HashSet<Client> clientList;
	private HashSet<Theather> theatherList;

	/**
	 * Es el constructor de la clase cinema
	 * 
	 * @param code
	 * @param clientList
	 * @param listaTeatros
	 */
	public Cinema(String code, HashSet<Client> clientList, HashSet<Theather> listaTeatros) {
		this.code = code;
		this.clientList = clientList;
		this.theatherList = listaTeatros;
	}

	public Cinema(String code) {
		super();
		this.code = code;
		this.clientList = new HashSet<Client>();
		this.theatherList = new HashSet<Theather>();
	}

	/**
	 * Obtiene el código del cinema
	 * 
	 * @return
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Cambia el código del cinema
	 * 
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Obtiene la lista de clientes del cinema
	 * 
	 * @return
	 */
	public HashSet<Client> getClientList() {
		return clientList;
	}

	/**
	 * Cambia la lista de clientes del cinema
	 * 
	 * @param clientList
	 */
	public void setClientList(HashSet<Client> clientList) {
		this.clientList = clientList;
	}

	/**
	 * Obtiene la lista de teatros del cinema
	 * 
	 * @return
	 */
	public HashSet<Theather> getListaTeatros() {
		return theatherList;
	}

	/**
	 * Cambia la lista de teatros del cinema
	 * 
	 * @param theatherList
	 */
	public void setTheatherList(HashSet<Theather> theatherList) {
		this.theatherList = theatherList;
	}

	/**
	 * Busca un cliente a partir de su id, si no se encuentra, se retorna un cliente
	 * vacío
	 * 
	 * @param id
	 * @return
	 */
	public Client searchClient(String id) {
		return getClientList().stream().filter(client -> client.getId().equals(id)).findFirst().orElse(Client.none());
	}

	/**
	 * Agrega un cliente a partir de su id, si se encuentra muestra una excepcion de
	 * tipo CinemaException
	 * 
	 * @param id
	 * @param name
	 * @throws CinemaException
	 */
	public void addClient(String id, String name) throws CinemaException {
		Client client = new Client(name, id);
		if (!clientList.add(client))
			throw new CinemaException("El cliente ya existe, no se puede agregar");
	}

	/**
	 * Agrega un cliente, si se encuentra muestra una excepcion de tipo
	 * CinemaException
	 * 
	 * @param client
	 * @param name
	 * @throws CinemaException
	 */
	public void addClient(Client client) throws CinemaException {
		if (!clientList.add(client))
			throw new CinemaException("El cliente ya existe, no se puede agregar");
	}

	/**
	 * Elimina un cliente a partir de su id, si no se encuentra muestra una
	 * excepcion de tipo CinemaException
	 * 
	 * @param id
	 * @throws CinemaException
	 */
	public void removeClient(String id) throws CinemaException {
		Client client = new Client(id);
		if (!clientList.remove(client))
			throw new CinemaException("El cliente no fue encontrado, no se puede eliminar");
	}

	public void addTheather(String code, int width, int height) throws CinemaException {
		Theather theather = new Theather(code, width, height);
		if (!theatherList.remove(theather)) {
			throw new CinemaException("El teatro no fue encontrado, no se puede eliminar");
		}
	}

	public void removeTheather(String code) throws CinemaException {
		Theather theather = new Theather(code);
		if (!theatherList.remove(theather))
			throw new CinemaException("El teatro no fue encontrado, no se puede eliminar");
	}
}
