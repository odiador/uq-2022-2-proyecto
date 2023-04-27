package model;

import java.time.LocalDate;

public class UqCard {
	private double balance = -1d;
	private int points = -1;
	private LocalDate purchaseDate;

	/**
	 * Es el constructor de la clase UqCard
	 * 
	 * @param balance
	 * @param points
	 * @param purchaseDate
	 */
	public UqCard(double balance, int points, LocalDate purchaseDate) {
		super();
		this.balance = balance;
		this.points = points;
		this.purchaseDate = purchaseDate;
	}

	/**
	 * Es el constructor de la clase UqCard sin parametros
	 */
	public UqCard() {
	}

	/**
	 * Es el constructor de la clase UqCard sin la fecha de compra
	 * 
	 * @param balance
	 * @param points
	 */
	public UqCard(double balance, int points) {
		super();
		this.balance = balance;
		this.points = points;
		this.purchaseDate = LocalDate.now();
	}

	/**
	 * Obtiene balance de la clase UqCard.java
	 * 
	 * @return
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * Cambia balance a partir de su balance {@code balance}
	 * 
	 * @param balance
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}

	/**
	 * Obtiene points de la clase UqCard.java
	 * 
	 * @return
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * Cambia points a partir de su points {@code points}
	 * 
	 * @param points
	 */
	public void setPoints(int points) {
		this.points = points;
	}

	/**
	 * Obtiene purchaseDate de la clase UqCard.java
	 * 
	 * @return
	 */
	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}

	/**
	 * Cambia purchaseDate a partir de su purchaseDate {@code purchaseDate}
	 * 
	 * @param purchaseDate
	 */
	public void setPurchaseDate(LocalDate purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	/**
	 * Determina si la tarjeta existe a partir de que la fecha de compra no sea
	 * null, su balance no sea negativo y sus puntos tampoco lo sean
	 * 
	 * @return
	 */
	public boolean getExists() {
		return getBalance() >= 0 && getPoints() >= 0 && getPurchaseDate() != null;
	}

}
