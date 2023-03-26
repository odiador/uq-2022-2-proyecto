package cine;

import java.awt.event.KeyEvent;

import custom.Plantilla;

public class Pruebas extends Plantilla {
	private PanelGrid matriz;

	public Pruebas() {
		boolean[][] ocupadas = new boolean[13][16];
		matriz = new PanelGrid(3, ocupadas);
		matriz.addGridListener(new GridListener() {

			@Override
			public void movimientoMatriz (EventoMatriz e) {
				System.out.println(e.calcValUnitarioString());

			}
		});
		agregarBody(matriz);
	}

	public void keyPressed (KeyEvent e) {
		matriz.keyPressed(e);
	}

	public static void main (String[] args) {
		new Pruebas().setVisible(true);
	}

	@Override
	public void configurarTam () {
		setSize(1000, 600);
	}
}
