package model;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import view.Aplicacion;
import view.ConfirmarCine;
import view.VentanaCine;

public class procesosCine {
	public int posI = 0;
	public int posJ = 0;
	public int canti;
	public data data = new data();
	public boolean m[][] = data.cine;
	public Color verde = data.verde;
	public Color azul = data.azul;
	public Color gris = data.gris;
	public Color bgCol = data.bgCol;
	public Color white = data.white;
	public procesosCine(int cant) {
		canti = cant;
	}

	// Para no hacer "Sala ----------------------------------"
	public String generarMensajeSala(int tamanio) {
		String mensaje = "Sala ";
		for(int i = 0;i<(tamanio/4.4194);i++) {
			mensaje+="-";
		}
		return mensaje;

	}
	public boolean estaLibre(int pI,int pJ) {
		//busca si un lugar está disponible teniendo en cuenta la cantidad
		//de puestos
		boolean res = true;
		for (int i = 0;i<canti;i++) {
			if(m[pI][pJ+i]) {
				res = false;
			}
		}
		return res;
	}
	// Busca el color a un lugar especifio dependiendo de si es general,
	// preferencial o está ocupado
	public Color obtenerColor(int i,int j) {
		Color col = gris;
		if(!m[i][j]) {
			col = azul;
			if(i>='J'-'A') {
				col = new Color(10,10,100);
			}
		}

		return col;
	}
	//ya sirve
	public void vaciarCine() {
		data = new data();
		boolean vacia[][]= new boolean[m.length][m[0].length];
		for(int i = 0;i<data.nombres.size();i++) {
			data.frecuencia.set(i,0);
			data.puntos.set(i,0);
		}
		for(int i = 0;i<data.lugaresCine.length;i++) {
			for(int j = 0;j<data.lugaresCine[0].length;j++) {
				data.lugaresCine[i][j] = new ArrayList<Object>();
			}
		}
		data.escribirInfo(vacia, 0);
		data.escribirCliente(data.frecuencia, 2);
		data.escribirCliente(data.puntos, 3);
		data.escribirInfo(data.lugaresCine, 2);
	}
	// Busca sitio libre en la matriz, si no encuentra se devuelve al
	//inicio
	public Object[] buscarPuesto(int posI,int posJ) {
		boolean encuentra = false;
		for(int i = 0;i<13&&!encuentra;i++) {
			for(int j = 0;j<16-canti+1&&!encuentra;j++) {
				if(estaLibre(i,j)) {
					encuentra = true;
					posI = i;
					posJ = j;
				}
			}
		}
		if(encuentra) {
			int x[] = moverDere(posI,posJ-1);
			posI = x[0];
			posJ = x[1];
		}
		Object[] res = {encuentra,posI,posJ};
		return res;

	}
	//Selección de sitio
	public int[] moverDere(int posI, int posJ) {
		posJ++;
		if(posJ<=(16-canti)) {
			if(!estaLibre(posI, posJ)) {
				//vuelve a mover a la derecha si no encuentra puesto
				int x[] = moverDere(posI,posJ);
				posI = x[0];
				posJ = x[1];
			}
		} else {
			//si y no puede bajar, vuelve a la posición 0
			int x[] = moverDere(posI,-1);
			posI = x[0];
			posJ = x[1];
		}
		int x[] = {posI,posJ};
		return x;
	}
	public int[] moverIzq(int posI, int posJ) {
		posJ--;
		if(posJ>=0) {
			if(!estaLibre(posI, posJ)) {
				int x[] = moverIzq(posI,posJ);
				posI = x[0];
				posJ = x[1];
			}
		} else {
			int x[] = moverIzq(posI,16-canti+1);
			posI = x[0];
			posJ = x[1];
		}
		int x[] = {posI,posJ};
		return x;
	}
	public int[] moverArriba(int posI,int posJ) {
		posI--;
		if(posI>=0) {
			if(!estaLibre(posI, posJ)) {
				int[] x = moverArriba(posI,posJ);
				posI = x[0];
				posJ = x[1];
			}
		} else {
			int[] x = moverArriba(13,posJ);
			posI = x[0];
			posJ = x[1];
		}
		int res[] = {posI,posJ};
		return res;
	}
	public int[] moverAbajo(int posI, int posJ) {
		posI++;
		if(posI<=12) {
			if(!estaLibre(posI, posJ)) {
					int x[] = moverAbajo(posI,posJ);
					posI = x[0];
					posJ = x[1];
			}
		} else {
			int[] x = moverAbajo(-1,posJ);
			posI = x[0];
			posJ = x[1];
		}
		int res[] = {posI,posJ};
		return res;
	}
}
