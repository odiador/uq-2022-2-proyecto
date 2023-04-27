package model;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;


public class Comunicacion implements Serializable{
	public Comunicacion() {
		try {
			ObjectInputStream carpetaleer = new ObjectInputStream(new FileInputStream("src/model/carpeta.dat"));
			carpetaleer.close();
		}
		catch (Exception e) {

		}
	}

}
