package co.edu.uniquindio.p1.cinema.model;

import java.util.Scanner;

import co.edu.uniquindio.p1.cinema.objetos.ListadeConfiteria;
import co.edu.uniquindio.p1.cinema.services.Herramientas;

public class Pruebas {
	public static void main(String[] args) {
		String tipo;
		int cantidad;
		double val;
		ListadeConfiteria x = new ListadeConfiteria();
		String mensaje = "--------------------------------------------" + "\n0. Ver Carrito" + "\n1. Agregar elemento"
				+ "\n2. Agregar cantidad" + "\n3. Cambiar cantidad" + "\n4. Ver precio"
				+ "\n--------------------------------------------" + "\nEscribe una opcion: ";
		while (true) {
			Scanner s = new Scanner(System.in);
			System.out.print(mensaje);
			int cadena = s.nextInt();
			switch (cadena) {
			case 0:
				System.err.println(x.getCarrito());
				break;
			case 1:
				System.out.print("Escribe un tipo de elemento: ");
				tipo = s.nextLine();
				tipo = s.nextLine();
				System.out.print("Escribe el valor unitario: ");
				val = s.nextDouble();
				System.out.print("Escribe una cantidad: ");
				cantidad = s.nextInt();
				x.agregarConfiteria(tipo, val, cantidad);
				break;
			case 2:
				System.out.print("Escribe un tipo de elemento: ");
				tipo = s.nextLine();
				tipo = s.nextLine();
				System.out.print("Escribe una cantidad: ");
				cantidad = s.nextInt();
				x.setCantidadDeLista(tipo, cantidad);
				break;
			case 3:
				System.out.print("Escribe un tipo de elemento: ");
				tipo = s.nextLine();
				tipo = s.nextLine();
				System.out.print("Escribe una cantidad: ");
				cantidad = s.nextInt();
				x.setCantidadDeLista(tipo, cantidad);
				break;
			case 4:
				System.out.print("Escribe un tipo de compra: ");
				tipo = s.nextLine();
				tipo = s.nextLine();
				System.err.print("Precio: $" + Herramientas.formatoSinDecimal(x.getPrecio(tipo)));
				break;
			default:
				System.exit(0);
			}
			s.close();
		}
	}

}
