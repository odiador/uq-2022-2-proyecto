package co.edu.uniquindio.p1.cinema.model;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Comunicacion implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Comunicacion() {
		try {
			ObjectInputStream carpetaleer = new ObjectInputStream(new FileInputStream("src/model/carpeta.dat"));
			carpetaleer.close();
		} catch (Exception e) {

		}
	}

}
