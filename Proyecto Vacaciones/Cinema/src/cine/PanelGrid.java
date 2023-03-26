package cine;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;

import config.Constantes;
import custom.Panel;
import custom.Text;

public class PanelGrid extends Panel implements MouseListener, MouseMotionListener, KeyListener {
	private Text[][] botonesSillas;
	private List<GridListener> gridListeners;
	private boolean[][] arr;
	private int cantidad;
	private int posI;
	private int posJ;

	public PanelGrid(boolean[][] arr) {
		this(0, arr, false);
	}

	public PanelGrid(int cantidad, boolean[][] arr) {
		this(cantidad, arr, true);
	}

	public PanelGrid(int cantidad, boolean[][] arr, boolean movimiento) {
		gridListeners = new ArrayList<GridListener>();
		setCantidad(cantidad);
		setArr(arr);

		if (movimiento) addMouseListener(this);
		if (movimiento) addMouseMotionListener(this);
		setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
		setLayout(new GridLayout(arr.length, arr[0].length, 0, 0));
		setBotonesSillas(new Text[arr.length][arr[0].length]);
		for (int i = 0; i < arr.length; i++) for (int j = 0; j < arr[0].length; j++) {
			String silla = (char) ('A' + i) + "" + (j + 1);
			getBotonesSillas()[i][j] = new Text(silla);
			getBotonesSillas()[i][j].setBorder(BorderFactory.createLineBorder(Constantes.defaultBgColor));

			if (!arr[i][j]) {
				getBotonesSillas()[i][j].setOpaque(true);
				getBotonesSillas()[i][j].setBackground(VentanaSala.COL_NO);
			}
			add(getBotonesSillas()[i][j]);
		}
		if (movimiento) {
			pintarLibre(0, 0, VentanaSala.COL_SI);
			for (GridListener l : gridListeners) l.movimientoMatriz(new EventoMatriz(getSelectedChairs()));
		}

	}

	public Text[][] getBotonesSillas () {
		return botonesSillas;
	}

	public void setBotonesSillas (Text[][] botonesSillas) {
		this.botonesSillas = botonesSillas;
	}

	public void setMatrizListener (List<GridListener> matrizListeners) {
		this.gridListeners = matrizListeners;
	}

	public String[] getSelectedChairs () {
		String sillasString = "";
		boolean[][] ocupadas = getArr();
		for (int i = 0; i < getCantidad(); i++) {
			int[] nums = calcLibreSiguiente(posI, posJ, ocupadas);
			ocupadas[nums[0]][nums[1]] = true;
			sillasString += getBotonesSillas()[nums[0]][nums[1]].getText() + "-";
		}
		return sillasString.split("-");
	}

	public void mouseReleased (MouseEvent e) {}

	public void clic () {
		bigFor: for (int i = 0; i < arr.length; i++) for (int j = 0; j < arr[0].length; j++) {
			if (getBotonesSillas()[i][j].getMousePosition() != null) {
				pintarLibre(posI, posJ, VentanaSala.COL_NO);
				pintarLibre(i, j, VentanaSala.COL_SI);
				break bigFor;
			}
		}
	}

	public EventoMatriz getEventoMatriz () {
		return new EventoMatriz(getSelectedChairs());
	}

	@Override
	public void mouseDragged (MouseEvent e) {
		int i = posI;
		int j = posJ;
		clic();
		if (i != posI || j != posJ) for (GridListener l : gridListeners)
			l.movimientoMatriz(new EventoMatriz(getSelectedChairs()));
	}

	public void mousePressed (MouseEvent e) {
		int i = posI;
		int j = posJ;
		clic();
		if (i != posI || j != posJ) for (GridListener l : gridListeners)
			l.movimientoMatriz(new EventoMatriz(getSelectedChairs()));
	}

	public void addGridListener (GridListener l) {
		gridListeners.add(l);
	}

	public boolean[][] getArr () {
		boolean[][] ret = new boolean[arr.length][arr[0].length];
		for (int i = 0; i < arr.length; i++) for (int j = 0; j < arr[0].length; j++) ret[i][j] = arr[i][j];
		return ret;
	}

	public void setArr (boolean[][] arr) {
		this.arr = arr;
	}

	public int getCantidad () {
		return cantidad;
	}

	public void setCantidad (int cantidad) {
		this.cantidad = cantidad;
	}

	public boolean movDerecha (int code) {
		return code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D;

	}

	public boolean movIzquierda (int code) {
		return code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A;
	}

	public boolean movArriba (int code) {
		return code == KeyEvent.VK_UP || code == KeyEvent.VK_W;
	}

	public boolean movAbajo (int code) {
		return code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S;
	}

	public void keyPressed (KeyEvent e) {
		int c = e.getKeyCode();
		int i = posI;
		int j = posJ;
		pintarLibre(i, j, VentanaSala.COL_NO);
		if (movDerecha(c) || (c == KeyEvent.VK_TAB && !e.isShiftDown())) {
			j++;
			if (j >= getArr()[0].length) {
				j = 0;
				i++;
			}
			if (i >= getArr().length) i = 0;
			int[] posiciones = calcLibreSiguiente(i, j);
			i = posiciones[0];
			j = posiciones[1];
		}
		if (movIzquierda(c) || (c == KeyEvent.VK_TAB && e.isShiftDown())) {
			j--;
			if (j < 0) {
				j = getArr()[0].length - 1;
				i--;
			}
			if (i < 0) i = getArr().length - 1;
			int[] posiciones = calcLibreAnterior(i, j);
			i = posiciones[0];
			j = posiciones[1];
		}
		if (movArriba(c)) {
			i--;
			if (i < 0) i = getArr().length - 1;
			i = calcLibreArriba(i, j);
		}
		if (movAbajo(c)) {
			i++;
			if (i >= getArr().length) i = 0;
			i = calcLibreAbajo(i, j);
		}
		pintarLibre(i, j, VentanaSala.COL_SI);
		for (GridListener l : gridListeners) l.movimientoMatriz(new EventoMatriz(getSelectedChairs()));
	}

	public int[] buscarLibre (int i, int j) {
		return buscarLibre(i, j, getArr());
	}

	public int[] buscarLibre (int i, int j, boolean[][] arr) {
		int ret[] = { -1, -1 };
		if (i < 0 | j < 0) return ret;
		if (arr[i][j]) {
			j++;
			if (j >= arr[0].length) {
				j = 0;
				i++;
			}
			if (i >= arr.length) i = 0;
			ret = buscarLibre(i, j, arr);
		} else {
			ret[0] = i;
			ret[1] = j;
		}
		return ret;
	}

	public void pintarLibre (int pI, int pJ) {
		pintarLibre(pI, pJ, VentanaSala.COL_SI);
	}

	public void pintarLibre (int pI, int pJ, Color pintar) {
		boolean[][] arr = getArr();
		int[] libre = null;

		for (int i = 0; i < getCantidad(); i++) {
			libre = buscarLibre(pI, pJ, arr);
			if (libre[0] == -1) break;
			arr[libre[0]][libre[1]] = true;
			getBotonesSillas()[libre[0]][libre[1]].setBackground(pintar);
			if (i != 0) continue;
			posI = libre[0];
			posJ = libre[1];
		}
	}

	public int[] calcLibreSiguiente (int pI, int pJ) {
		return calcLibreSiguiente(pI, pJ, getArr());
	}

	public int[] calcLibreSiguiente (int pI, int pJ, boolean[][] arr) {
		int[] ret = { -1, -1 };
		if (arr[pI][pJ]) {
			int i = pI;
			int j = pJ;
			j++;
			if (j >= arr[0].length) {
				i++;
				j = 0;
			}
			if (i >= arr.length) i = 0;
			ret = calcLibreSiguiente(i, j, arr);
		} else {
			ret[0] = pI;
			ret[1] = pJ;
		}
		return ret;
	}

	public int calcLibreAbajo (int pI, int pJ) {
		int ret = -1;
		boolean[][] arr = getArr();
		if (arr[pI][pJ]) {
			int i = pI;
			i++;
			if (i >= arr.length) i = 0;
			ret = calcLibreAbajo(i, pJ);
		} else ret = pI;
		return ret;
	}

	public int calcLibreArriba (int pI, int pJ) {
		int ret = -1;
		boolean[][] arr = getArr();
		if (arr[pI][pJ]) {
			int i = pI;
			i--;
			if (i < 0) i = arr.length - 1;
			ret = calcLibreArriba(i, pJ);
		} else ret = pI;
		return ret;
	}

	public int[] calcLibreAnterior (int pI, int pJ) {
		int[] ret = { -1, -1 };
		boolean[][] arr = getArr();
		if (arr[pI][pJ]) {
			int i = pI;
			int j = pJ;
			j--;
			if (j < 0) {
				i--;
				j = arr[0].length - 1;
			}
			if (i < 0) i = arr.length - 1;
			ret = calcLibreAnterior(i, j);
		} else {
			ret[0] = pI;
			ret[1] = pJ;
		}
		return ret;
	}

	public void keyTyped (KeyEvent e) {}

	public void keyReleased (KeyEvent e) {}
}
