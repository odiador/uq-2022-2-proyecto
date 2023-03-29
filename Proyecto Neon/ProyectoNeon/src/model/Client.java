package model;

import java.util.Objects;

public class Client {
	private String name;
	private String id;
	private int frequency = -1;
	private UqCard tGold = new UqCard();
	private UqCard tBasic = new UqCard();

	/**
	 * Es el constructor de la clase Cliente
	 * 
	 * @param name
	 * @param id
	 * @param frequency
	 * @param tBasic
	 * @param tGold
	 */
	public Client(String name, String id, int frequency, UqCard tBasic, UqCard tGold) {
		this(name, id, frequency);
		this.tBasic = tBasic;
		this.tGold = tGold;
	}

	/**
	 * Es el constructor de la clase cliente sin las tarjetas UQ
	 * 
	 * @param name
	 * @param id
	 * @param frequency
	 */
	public Client(String name, String id, int frequency) {
		this(name, id);
		this.frequency = frequency;
	}

	/**
	 * Es el constructor de la clase cliente sin las tarjetas UQ con frecuencia 0
	 * 
	 * @param name
	 * @param id
	 */
	public Client(String name, String id) {
		super();
		this.name = name;
		this.id = id;
	}

	public Client() {
	}

	public Client(String id) {
		this.id = id;
	}

	/**
	 * Obtiene name de la clase Client
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Cambia name a partir de su name {@code name}
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Obtiene id de la clase Client
	 * 
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * Cambia id a partir de su id {@code id}
	 * 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Obtiene frequency de la clase Client
	 * 
	 * @return
	 */
	public int getFrequency() {
		return frequency;
	}

	/**
	 * Cambia frequency a partir de su frequency {@code frequency}
	 * 
	 * @param frequency
	 */
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	/**
	 * Obtiene tGold de la clase Client
	 * 
	 * @return
	 */
	public UqCard gettGold() {
		return tGold;
	}

	/**
	 * Cambia tGold a partir de su tGold {@code tGold}
	 * 
	 * @param tGold
	 */
	public void settGold(UqCard tGold) {
		this.tGold = tGold;
	}

	/**
	 * Obtiene tBasic de la clase Client
	 * 
	 * @return
	 */
	public UqCard gettBasic() {
		return tBasic;
	}

	/**
	 * Cambia tBasic a partir de su tBasic {@code tBasic}
	 * 
	 * @param tBasic
	 */
	public void settBasic(UqCard tBasic) {
		this.tBasic = tBasic;
	}

	/**
	 * Determina si un cliente existe o no a partir de su nombre e id
	 * 
	 * @return
	 */
	public boolean getExists() {
		return getName() != null && getId() != null && getFrequency() >= 0;
	}

	/**
	 * 
	 * @return
	 */
	public boolean getTBasicExists() {
		return gettBasic().getExists();
	}

	/**
	 * 
	 * @return
	 */
	public boolean getTGoldExists() {
		return gettGold().getExists();
	}

	public static Client none() {
		return new Client();
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Client other = (Client) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return getExists()
				? String.format("Client [name=%s, id=%s, frequency=%s, tGold=%s, tBasic=%s]", name, id, frequency,
						tGold, tBasic)
				: "Client [?]";
	}
}
