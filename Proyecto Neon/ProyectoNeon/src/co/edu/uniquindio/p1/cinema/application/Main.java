package co.edu.uniquindio.p1.cinema.application;

import co.edu.uniquindio.p1.cinema.view.Template;
import co.edu.uniquindio.p1.cinema.view.VentanaPrincipal;

public class Main {
	public static void main(String[] args) {
		Template template = new VentanaPrincipal(900, 600);
		template.setVisible(true);
	}
}
