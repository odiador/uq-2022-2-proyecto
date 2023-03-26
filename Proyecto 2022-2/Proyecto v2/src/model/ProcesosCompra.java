package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ProcesosCompra {
	public data data = new data();
	public String sillas[];
	public ArrayList<Object> arr;
	public ArrayList<Integer> frecuencia;
	public ArrayList<Integer> puntos;
	public ArrayList<String> nombres;
	public ArrayList<ArrayList<Object>> listaConfiteria;
	public ArrayList<Boolean> tieneTarjetaGold;
	public ArrayList<Object> clientes;
	public LocalDate fechaCine;
	public ArrayList[][] lugaresCine;
	public ProcesosCompra(String sillas[],ArrayList<Object> cedus) {
		clientes = data.clientes;
		frecuencia = data.frecuencia;
		nombres = data.nombres;
		this.sillas = sillas;
		arr = cedus;
		puntos = data.puntos;
		tieneTarjetaGold = data.tieneTarjetaGold;
		fechaCine = data.fechaCine;
		lugaresCine = data.lugaresCine;
	}
	public double calcPrecio (boolean tieneBasic,boolean tieneG) {
		double dcto = 0;
		char letra = sillas[0].charAt(0);
		double res = -7;
		if(arr.size()!=0) {
			if(tieneBasic) { //Si tiene basic
				dcto = 0.05;
			}
			if(tieneG) { //Si tiene gold
				dcto = 0.2;
			}
			dcto = 1-dcto;
			int cantidad = sillas.length;
			res = (cantidad*16000)*dcto; //caso preferencial
			if(letra>='A'&&letra<='I') {
				res = (cantidad*12000)*dcto; //caso general
			}
		}
		return res;
	}
	/**
	 *
	 * @param mRes La matriz que dice si estan ocupados o desocupados
	 * @param price E
	 */
	public void procesarCompraBoletas(boolean mRes[][],double price,String tipoCompra,int cant,String[] sillas) {
		// Obtiene la fecha y hora del momento de compra
		LocalTime tiempo = LocalTime.now();
		LocalDate fecha = LocalDate.now();
		data data = new data();
		listaConfiteria = data.lista;
		int diferencia = fechaCine.getDayOfYear()-fecha.getDayOfYear();
		// Diferencia de dias entre la función y la fecha actual
		if(diferencia>=0&&diferencia<=30) {
			escribirInfo(mRes, 0);
			int indice = buscarPosCliente((String) arr.get(1));
			// Sube las idas a cine
			frecuencia.set(indice, frecuencia.get(indice)+1);
			// Suben los puntos en caso de haber
			int cantPtsExtra = 0;
			if ((boolean) arr.get(4)) {
				cantPtsExtra = (int) (price/100);
			}
			if((boolean) arr.get(5)) {
				cantPtsExtra = (int) (price/100)*2;
			} else if(frecuencia.get(indice)==30) {
				frecuencia.set(indice, 0);
				tieneTarjetaGold.set(indice, true);
			}
			puntos.set(indice, puntos.get(indice)+cantPtsExtra);
			clientes.set(2, frecuencia);
			clientes.set(3, puntos);
			clientes.set(5, tieneTarjetaGold);
			data.clientes = clientes;
			escribirInfo(clientes,1);
			ArrayList<Object> mensajes = new ArrayList<Object>();

			mensajes.add(0,fecha.getYear());
			mensajes.add(0,fecha.getMonthValue());
			mensajes.add(0,fecha.getDayOfMonth());
			mensajes.add(0,tiempo.getSecond());
			mensajes.add(0,tiempo.getMinute());
			mensajes.add(0,tiempo.getHour());
			mensajes.add(0,arr.get(0));
			mensajes.add(0,arr.get(1));
			mensajes.add(0,"Boleta");
			mensajes.add(cant);
			mensajes.add(tipoCompra);
			mensajes.add(price);
			char letra = sillas[0].charAt(0);
			for(int j = 0;j<16;j++) {
				for(int x = 0;x<sillas.length;x++) {
					String pos = (char) (letra)+""+((j)+1);
					if(pos.equals(sillas[x])) {
						lugaresCine[(int) (letra-'A')][j] = mensajes;
					}
				}
			}
			String mensajeSillas = "";
			for(int i = 0;i<sillas.length;i++) {
				mensajeSillas+=sillas[i]+" ";
			}
			mensajes.add(mensajeSillas);
			listaConfiteria.add(mensajes);
			escribirInfo(listaConfiteria, 4);
			escribirInfo(lugaresCine, 2);
		} else {
			JOptionPane.showMessageDialog(null, "La compra se hace fuera del rango de tiempo","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	// html para que de saltos de linea


	public String generarMensajeHtml(double price,boolean tieneB,boolean tieneG) {

		String mensaje = "Entradas: ";
		for (int i = 0;i<sillas.length;i++) {
			mensaje+=sillas[i]+" ";
		}
		mensaje += "\nCantidad: "+sillas.length+"\nTipo de silla: ";
		char letra = sillas[0].charAt(0);
		if(letra>='A'&&letra<='I') mensaje += "general ("+letra+")";
		else mensaje += "preferencial ("+letra+")";
		mensaje +="\nPrecio: "+price;
		if(tieneB||tieneG) {
			int cantPtsExtra = 0;
			if (tieneB) {
				cantPtsExtra = (int) (price/100);
			}
			if(tieneG) {
				cantPtsExtra = (int) (price/100)*2;
			}
			mensaje+="\nPuntos a Agregar: "+cantPtsExtra;
		}
		return mensaje;
	}
	public int buscarPosCliente(String name) {
		return data.buscarPosCliente(name);
	}
	public void escribirCliente(Object objeto, int pos) {
		data.escribirCliente(objeto, pos);
	}
	public void escribirInfo(Object objeto, int pos) {
		data.escribirInfo(objeto, pos);
	}
}
