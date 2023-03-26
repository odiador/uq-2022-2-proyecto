package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class ProcesosConfit {

	public ArrayList<Object> infoPersonas;
	public ArrayList<ArrayList<Object>> listaConfiteria;
	public data data = new data();
	public String auxiliar;
	public ProcesosConfit(ArrayList<Object> infoPersonas,String auxiliar) {
		this.infoPersonas = infoPersonas;
		this.auxiliar = auxiliar;
	}
	public void procesarCompraConfiteria(ArrayList<String> nomComida,ArrayList<Integer> cantidadComida,double price) {
		// Obtiene la fecha y hora del momento de compra
		data = new data();
		listaConfiteria = data.lista;
		LocalTime tiempo = LocalTime.now();
		LocalDate fecha = LocalDate.now();
		ArrayList<Object> facturaCompra = new ArrayList<Object>();
		facturaCompra.add(0,fecha.getYear());
		facturaCompra.add(0,fecha.getMonthValue());
		facturaCompra.add(0,fecha.getDayOfMonth());
		facturaCompra.add(0,tiempo.getSecond());
		facturaCompra.add(0,tiempo.getMinute());
		facturaCompra.add(0,tiempo.getHour());
		facturaCompra.add(0,infoPersonas.get(0));
		facturaCompra.add(0,infoPersonas.get(1));
		facturaCompra.add(0,"Confitería");
		facturaCompra.add(auxiliar);
		facturaCompra.add(nomComida);
		facturaCompra.add(cantidadComida);
		facturaCompra.add(price);

		listaConfiteria.add(facturaCompra);
		escribirInfo(listaConfiteria, 4);
	}
	public void escribirInfo(Object objeto, int pos) {
		data.escribirInfo(objeto, pos);
	}

}
