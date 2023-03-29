package model;

import java.util.HashSet;
import java.util.Objects;

public class Theather {
	private String code;
	private int width = -1;
	private int height = -1;
	private HashSet<String> occupiedChairs = new HashSet<String>();

	/**
	 * Es el constructor de la clase Theather
	 * 
	 * @param code
	 * @param width
	 * @param height
	 * @param occupiedChairs
	 */
	public Theather(String code, int width, int height, HashSet<String> occupiedChairs) {
		this.code = code;
		this.occupiedChairs = occupiedChairs;
		this.width = width;
		this.height = height;
	}

	/**
	 * Es el constructor de la clase Theather sin sillas ocupadas
	 * 
	 * @param width
	 * @param height
	 */
	public Theather(String code, int width, int height) {
		this.code = code;
		this.width = width;
		this.height = height;
	}

	public Theather(String code) {
		this.code = code;
	}

	public Theather() {
	}

	/**
	 * Agrega una silla especificada, si ya se encuentra muestra un error
	 * 
	 * @param chair
	 * @return
	 * @throws ChairException
	 */
	public void addChair(String chair) throws ChairException {
		if (!occupiedChairs.add(chair))
			throw new ChairException("La silla no fue agregada (ya existe)");
	}

	/**
	 * 
	 * Elimina una silla especificada, si no se encuentra muestra un error
	 * 
	 * @param chair
	 * @throws ChairException
	 */
	public void removeChair(String chair) throws ChairException {
		if (!occupiedChairs.remove(chair))
			throw new ChairException("La silla no fue eliminada (no existe)");
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Obtiene occupiedChairs de la clase Theather.java
	 * 
	 * @return
	 */
	public HashSet<String> getOccupiedChairs() {
		return occupiedChairs;
	}

	/**
	 * Cambia occupiedChairs a partir de su occupiedChairs {@code occupiedChairs}
	 * 
	 * @param occupiedChairs
	 */
	public void setOccupiedChairs(HashSet<String> occupiedChairs) {
		this.occupiedChairs = occupiedChairs;
	}

	/**
	 * Obtiene width de la clase Theather.java
	 * 
	 * @return
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Cambia width a partir de su width {@code width}
	 * 
	 * @param width
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Obtiene height de la clase Theather.java
	 * 
	 * @return
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Cambia height a partir de su height {@code height}
	 * 
	 * @param height
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public int hashCode() {
		return Objects.hash(code);
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
		Theather other = (Theather) obj;
		return Objects.equals(code, other.code);
	}

}
