package model;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ProcesosCliente {
	data data = new data();
	public ArrayList<String> cc;
	public ArrayList<String> nombres;
	public ArrayList<Integer> frecuencia;
	public ArrayList<Integer> puntos;
	public ArrayList<Boolean> tieneTarjetaGold;
	public ArrayList<Boolean> tieneTarjetaBasic;
	public ArrayList<Double> dineroTarjeta;
	public ArrayList<Object> clientes;
	public ArrayList<Object> base;
	public ProcesosCliente() {
		actualizarInfo();
	}
	public void actualizarInfo() {
		data = new data();
		base = data.base;
		clientes = data.clientes;

		nombres = data.nombres;
		cc = data.cc;
		frecuencia = data.frecuencia;
		puntos = data.puntos;
		tieneTarjetaBasic = data.tieneTarjetaBasic;
		tieneTarjetaGold = data.tieneTarjetaGold;
		dineroTarjeta = data.dineroTarjeta;
	}
	public ArrayList<Object> buscarCliente(String cedu){
		actualizarInfo();
		return data.buscarCliente(cedu);
	}
	public boolean recargarTarjeta(String cedu,double valor){
		actualizarInfo();
		boolean res = false;
		int pos = buscarPosCliente(cedu);
		if(pos!=-7) {
			if(tieneTarjetaBasic.get(pos)||tieneTarjetaGold.get(pos)) {
				dineroTarjeta.set(pos, (dineroTarjeta.get(pos)+valor));
				JOptionPane.showMessageDialog(null, "Se recargó la tarjeta a "+nombres.get(pos)
						+ "\nAhora tiene "+dineroTarjeta.get(pos));
			} else {
				JOptionPane.showMessageDialog(null, nombres.get(pos)+" no tiene alguna tarjeta","Error",JOptionPane.ERROR_MESSAGE);
				res = true;
			}
		} else {
			JOptionPane.showMessageDialog(null, "No se encontro la persona","Error",JOptionPane.ERROR_MESSAGE);
			res = true;
		}
		clientes.set(6, dineroTarjeta);
		data.clientes = clientes;
		base.set(1,clientes);
		guardarBase(base);
		return res;
	}
	public void agregarCliente(String nombre, String cedula,int cant,int pts, boolean tieneBasic, boolean tieneG,double plata){
		actualizarInfo();
		nombres.add(nombre);
		cc.add(cedula);
		frecuencia.add(cant);
		puntos.add(pts);
		tieneTarjetaBasic.add(tieneBasic);
		tieneTarjetaGold.add(tieneG);
		dineroTarjeta.add(plata);

		clientes.set(0,nombres);
		clientes.set(1,cc);
		clientes.set(2,frecuencia);
		clientes.set(3,puntos);
		clientes.set(4,tieneTarjetaBasic);
		clientes.set(5,tieneTarjetaGold);
		clientes.set(6,dineroTarjeta);

		data.clientes = clientes;
		base.set(1, clientes);
		escribirInfo(clientes,1);
		guardarBase(base);
	}

	public void darTarjCliente(String que,boolean objeto,String cedu){
		actualizarInfo();
		int pos = buscarPosCliente(cedu);
		if(pos!=-7) {
			if(que.equals("Basic")) {
				tieneTarjetaBasic.set(pos, objeto);
				JOptionPane.showMessageDialog(null, "Se entregó a "+nombres.get(pos)+" la tarjet basic");
			}
			if(que.equals("Gold")) {
				tieneTarjetaGold.set(pos, objeto);
				JOptionPane.showMessageDialog(null, "Se entregó a "+nombres.get(pos)+" la tarjeta gold");
			}
		} else {
			JOptionPane.showMessageDialog(null, "No se pudo encontrar la persona","Error",JOptionPane.ERROR_MESSAGE);
		}
		clientes.set(4, tieneTarjetaBasic);
		clientes.set(5, tieneTarjetaGold);
		data.clientes = clientes;
		base.set(1, clientes);
		guardarBase(base);
	}
	public int buscarPosCliente(String cc) {
		return data.buscarPosCliente(cc);
	}
	public void guardarBase (ArrayList<Object> base) {
		data.guardarBase(base);
	}
	public void escribirInfo(Object base, int i) {
		data.escribirInfo(base, i);
	}
	public void pagarConTarjeta (double cuanto,String cedu){
		actualizarInfo();
		int pos = buscarPosCliente(cedu);
		dineroTarjeta.set(pos, dineroTarjeta.get(pos)-cuanto);
		clientes.set(6, dineroTarjeta);
		data.clientes = clientes;
		base.set(1, clientes);
		guardarBase(base);
	}
}
