package confiteria;

import java.sql.SQLException;

import config.Utilities;
import objects.Cinema;
import objects.CustomException;
import tools.TablaCinema;

public class PruebasConsola {
	public static void main (String[] args) {
		Cinema c= Cinema.ninguno(); 
		try {
			try {
				c = TablaCinema.getCinemaById(0);
			} catch (CustomException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TablaCinema.imprimir(Utilities.matrizAStringHtml(c.getMatrizBoolean()));
//		
//		Cinema c = new Cinema(0, "Jhonys de Circasia: la reencarnacion del diablo", 20, 13);
//		for (int i = 8; i < 12; i++) c.addOcupied("A" + i);
//		for (int i = 1; i < 12; i++) c.addOcupied("F" + i);
//
//		c.addOcupied("C12");
//		try {
//			TablaCinema.agregarCinema(c);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
	}
}
