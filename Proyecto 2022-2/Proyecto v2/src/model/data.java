package model;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JOptionPane;
@SuppressWarnings({ "unchecked", "serial" })
public class data implements Serializable {
	public Color verde = new Color(0,255,0);
	public Color azul = new Color(0, 0, 173);
	public Color gris = new Color(100,100,100);
	public Color bgCol = new Color(0, 0, 0);
	public Color white = new Color(255, 255, 255);
	public ArrayList<Object> base;
	public boolean cine[][];
	public ArrayList<String> cc;
	public ArrayList<String> nombres;
	public ArrayList<Integer> frecuencia;
	public ArrayList<Integer> puntos;
	public ArrayList<Boolean> tieneTarjetaGold;
	public ArrayList<Boolean> tieneTarjetaBasic;
	public ArrayList<Double> dineroTarjeta;
	public ArrayList<Object> clientes;
	public ArrayList<Object> lugaresCine[][];
	public ArrayList<Object> historialCompras;
	public ArrayList<ArrayList<Object>> lista;
	public LocalDate fechaCine = LocalDate.of(2022, 11, 24);
	public int anio;
	public ArrayList<Object> infoImp;
	public data() {
/* La información se maneja en un ArrayList de tipo objeto que contiene
 * todo:
 * - En la posición 0 está una matriz de booleans que dice si las posiciones
 * del cine están o no ocupadas
 * - En la 1 está todo lo relacionado con el cliente (ArrayList<Object>):
 * 	■ 0:	Nombre (String)
 * 	■ 1:	Cedula (String)
 * 	■ 2: 	Veces que ha ido a cine en el año (lo del año no está hecho)
 * 			(int)
 * 	■ 3: 	Puntos (int)
 * 	■ 4: 	Si tiene tarjeta basic (boolean)
 * 	■ 5: 	Si tiene tarjeta gold (boolean)
 * 	■ 6: 	El dinero en la tarjeta (double)
 * - En la 2 está la información de compra de cada lugar de cine en una
 * matriz (ArrayList<Object>):
 * 	Si la silla esta desocupada el sitio está vacío, si no:
 * 	■ 0:	Hora (int)
 * 	■ 1: 	Minutos (int)
 * 	■ 2: 	Segundos (int)
 * 	■ 3: 	Día (int)
 * 	■ 4: 	Numero de mes (int)
 * 	■ 5: 	Año (int)
 * 	■ 6: 	Tipo(s) de pago (ArrayList<String>)
 * 	■ 7: 	Nombre (String)
 * 	■ 8: 	Cedula (String)
 * 	■ 9: 	Veces que ha ido a cine en el año (en ese momento)  (int)
 * 	■ 10:	Puntos (en ese momento) (int)
 * 	■ 11:	Si tiene tarjeta basic (en ese momento) (boolean)
 * 	■ 12: 	Si tiene tarjeta gold (en ese momento) (boolean)
 * 	■ 13: 	El dinero en la tarjeta (en ese momento) (double)
 * - En la 3 hay datos importantes para todas las clases
 * (ArrayList<Object>), a futuro se pueden usar mas cosas:
 * 	■ 0:	Año "lectivo" (int)
 */
		try {
			// Intenta leer el archivo .dat
			ObjectInputStream carpetaleer = new ObjectInputStream(new FileInputStream("src/model/carpeta.dat"));
			base = (ArrayList<Object>) carpetaleer.readObject();
			carpetaleer.close();

			cine = (boolean[][]) base.get(0);
			clientes = (ArrayList<Object>) base.get(1);
			lugaresCine = (ArrayList<Object>[][]) base.get(2);
			infoImp = (ArrayList<Object>) base.get(3);
			lista = (ArrayList<ArrayList<Object>>) base.get(4);

			nombres = (ArrayList<String>) clientes.get(0);
			cc = (ArrayList<String>) clientes.get(1);
			frecuencia = (ArrayList<Integer>) clientes.get(2);
			puntos = (ArrayList<Integer>) clientes.get(3);
			tieneTarjetaBasic = (ArrayList<Boolean>) clientes.get(4);
			tieneTarjetaGold = (ArrayList<Boolean>) clientes.get(5);
			dineroTarjeta = (ArrayList<Double>) clientes.get(6);

			anio = (int) infoImp.get(0);

			if(anio!=LocalDate.now().getYear()){
				int tam = frecuencia.size();
				frecuencia = new ArrayList<Integer>();
				for(int i = 0;i<tam;i++) {
					frecuencia.set(0, 0);
				}

			}


		}
		catch (Exception e){
			//si no lo encuentra lo crea con todo vacío
			try {
				nombres = new ArrayList<String>();
				cc = new ArrayList<String>();
				frecuencia = new ArrayList<Integer>();
				puntos = new ArrayList<Integer>();
				tieneTarjetaBasic = new ArrayList<Boolean>();
				tieneTarjetaGold = new ArrayList<Boolean>();
				dineroTarjeta = new ArrayList<Double>();
				lugaresCine = new ArrayList[13][16];
				fechaCine = LocalDate.of(2022,12,24);
				anio = LocalDate.now().getYear();
				lista = new ArrayList<ArrayList<Object>>();

				for(int i = 0;i<13;i++) {
					for(int j = 0;j<16;j++) {
						lugaresCine[i][j] = new ArrayList<Object>();
					}
				}
				base = new ArrayList<Object>();
				cine = new boolean[13][16];
				clientes = new ArrayList<Object>();
				infoImp = new ArrayList<Object>();

				clientes.add(nombres);
				clientes.add(cc);
				clientes.add(frecuencia);
				clientes.add(puntos);
				clientes.add(tieneTarjetaBasic);
				clientes.add(tieneTarjetaGold);
				clientes.add(dineroTarjeta);

				infoImp.add(anio);

				ObjectOutputStream carpetaEscribir = new ObjectOutputStream(new FileOutputStream("src/model/carpeta.dat"));
				base.add(cine);
				base.add(clientes);
				base.add(lugaresCine);
				base.add(infoImp);
				base.add(lista);
				carpetaEscribir.writeObject(base);
				carpetaEscribir.close();
			}
			catch (Exception x){}

		}

	}
	// Sobreescribe algo de la base de datos
	public void escribirInfo(Object objeto, int i){
		base.set(i, objeto);
		cine = (boolean[][]) base.get(0);
		clientes = (ArrayList<Object>) base.get(1);
		lugaresCine = (ArrayList<Object>[][]) base.get(2);
		infoImp = (ArrayList<Object>) base.get(3);
		lista = (ArrayList<ArrayList<Object>>) base.get(4);
		guardarBase(base);
	}
	// Sobreescribe algo del cliente

	public void escribirCliente(Object objeto, int i){
		clientes.set(i, ((ArrayList<Object>) (objeto)));
		nombres = (ArrayList<String>) clientes.get(0);
		cc = (ArrayList<String>) clientes.get(1);
		frecuencia = (ArrayList<Integer>) clientes.get(2);
		puntos = (ArrayList<Integer>) clientes.get(3);
		tieneTarjetaBasic = (ArrayList<Boolean>) clientes.get(4);
		tieneTarjetaGold = (ArrayList<Boolean>) clientes.get(5);
		dineroTarjeta = (ArrayList<Double>) clientes.get(6);
		base.set(1, clientes);
		guardarBase(base);
	}
	//guarda la informacion en el archivo .dat
	public void guardarBase(ArrayList<Object> info) {
		try {
			ObjectOutputStream carpetaEscribir = new ObjectOutputStream(new FileOutputStream("src/model/carpeta.dat"));
			carpetaEscribir.writeObject(info);
			base = info;
			carpetaEscribir.close();
	}
	catch (Exception x){}
	}

	public ArrayList<Object> buscarCliente (String cedula){
		new data();
		int pos = buscarPosCliente(cedula);
		ArrayList<Object> res = new ArrayList<Object>();
		if(pos!=-7) {
			res.add(nombres.get(pos));
			res.add(cc.get(pos));
			res.add(frecuencia.get(pos));
			res.add(puntos.get(pos));
			res.add(tieneTarjetaBasic.get(pos));
			res.add(tieneTarjetaGold.get(pos));
			res.add(dineroTarjeta.get(pos));
		}
		return res;
	}
	public int buscarPosCliente (String cedula) {
		//Si no lo encuentra manda un -7
		new data();
		int pos = -7;
		for(int i = 0;i<cc.size()&&pos==-7;i++) {
			if(cc.get(i).equals(cedula)) {
				pos = i;
			}
		}
		return pos;
	}
}