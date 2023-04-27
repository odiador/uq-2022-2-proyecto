package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

import model.Herramientas;
import model.Herramientas.tiposDeSilla;
import model.data;
import threads.HiloCargando;

public class VentanaCine extends ParteSuperior {

	private JPanel panelBotones, aceptar, volver;
	private JPanel[][] matrizBotones, tabs;
	private JLabel[][] matrizLabels = new JLabel[13][16], tabLabels;
	private JLabel lAceptar, lVolver;
	private data data;
	private int cant;
	private int pI = 20, pJ = 20;
	private ArrayList<Object> cliente;
	private VentanaPrincipal ventanaAnterior;
	private JPanel tab;

	public void conFigurarVentana() {
		setSize(1280, 646);
		cambiarTitulo(null, Herramientas.FUENTE_TITULO_DEFAULT);
		setTitulo("Ventana Cine");
	}

	public VentanaCine(ArrayList<Object> cliente, int cant, VentanaPrincipal ventanaAnterior) {
		this.ventanaAnterior = ventanaAnterior;
		this.cliente = cliente;
		this.cant = cant;
		data = new data();

		panelBotones = new JPanel();
		panelBotones.setSize(850, 500);
		panelBotones.setLocation(30, panelSuperior.getHeight() + panelSuperior.getY() + 20);
		panelBotones.setLayout(new GridLayout(13, 16, 5, 5));
		panelBotones.setBackground(Herramientas.black);
		panelBotones.setBorder(
				new CompoundBorder(new LineBorder(Herramientas.WHITE), new LineBorder(Herramientas.black, 3)));
		contentPane.add(panelBotones);

		matrizBotones = new JPanel[13][16];
		LineBorder linea = new LineBorder(Herramientas.WHITE);
		for (int i = 0; i < 13; i++) {
			for (int j = 0; j < 16; j++) {
				matrizBotones[i][j] = new JPanel();
				matrizBotones[i][j].setBorder(linea);
				matrizBotones[i][j].setLayout(new BorderLayout(0, 0));
				matrizBotones[i][j].setBackground(Herramientas.black);

				matrizLabels[i][j] = new JLabel((char) ('A' + i) + "" + (j + 1));
				matrizLabels[i][j].setFont(crearFuente(Herramientas.RUTA_FONT_DEFAULT, 20));
				matrizLabels[i][j].setForeground(Herramientas.WHITE);
				matrizLabels[i][j].setHorizontalAlignment(0);

				if (data.cine[i][j]) {
					matrizBotones[i][j].setBackground(Herramientas.black);
					matrizLabels[i][j].setForeground(Herramientas.black);
					matrizBotones[i][j].setBorder(null);
				} else {
					if (estaLibreMouse(i, j) != -7) {
						matrizBotones[i][j].addMouseListener(this);
						matrizLabels[i][j].setCursor(Herramientas.HAND_CURSOR);
					}
					matrizBotones[i][j].add(matrizLabels[i][j], "Center");
				}

				panelBotones.add(matrizBotones[i][j]);
			}
		}
		mSig();
		pintarLibres(true);

		tab = new JPanel();
		tab.setLocation(panelBotones.getX() + panelBotones.getWidth() + 20, panelBotones.getY());
		tab.setSize(getWidth() - tab.getX() - 20, panelBotones.getHeight() - 200);
		tab.setBackground(Herramientas.black);
		String cad[][] = obtenerCadenaPreview();

		tabLabels = new JLabel[cad.length][cad[0].length];
		tabs = new JPanel[cad.length][cad[0].length];
		tab.setLayout(null);

		tabLabels[0][0] = new JLabel();

		tabLabels[0][0].setForeground(Herramientas.black);
		tabLabels[0][0].setBorder(null);
		tabLabels[0][0].setHorizontalAlignment(0);

		tabs[0][0] = new JPanel();
		tabs[0][0].setLayout(new BorderLayout(0, 0));
		tabs[0][0].setBackground(Herramientas.WHITE);
		tabs[0][0].setBounds(0, 0, tab.getWidth(), tab.getHeight() / cad.length);

		tabs[0][0].add(tabLabels[0][0], "Center");
		tab.add(tabs[0][0]);
		Dimension tabSize = new Dimension(tab.getWidth() / cad[0].length, tab.getHeight() / cad.length);

		for (int i = 1; i < tabLabels.length; i++) {
			for (int j = 0; j < tabLabels[0].length; j++) {
				tabLabels[i][j] = new JLabel();
				tabLabels[i][j].setForeground(Herramientas.WHITE);
				tabLabels[i][j].setHorizontalAlignment(0);
				tabLabels[i][j].setBorder(linea);

				tabs[i][j] = new JPanel();
				tabs[i][j].setBackground(Herramientas.black);
				tabs[i][j].setLayout(new BorderLayout(0, 0));
				tabs[i][j].setSize(tabSize);
				tabs[i][j].setLocation(j * tabSize.width, i * tabSize.height);

				tabs[i][j].add(tabLabels[i][j], "Center");
				tab.add(tabs[i][j]);
			}
		}
		actualizarLabel();
		contentPane.add(tab);

		aceptar = new JPanel();
		aceptar.setBounds(tab.getX() + 20, tab.getY() + tab.getHeight() + 20, (tab.getWidth() / 2) - 40, 40);
		aceptar.setLayout(new BorderLayout(0, 0));
		contentPane.add(aceptar);
		lAceptar = new JLabel("Aceptar");
		lAceptar.setForeground(Herramientas.WHITE);
		lAceptar.setHorizontalAlignment(0);
		aceptar.setBackground(Herramientas.BLACK);
		aceptar.setBorder(new LineBorder(col));
		aceptar.setCursor(Herramientas.HAND_CURSOR);
		aceptar.addMouseListener(this);
		lAceptar.setFont(Herramientas.FUENTE_TITULO_DEFAULT);
		aceptar.add(lAceptar, "Center");

		volver = new JPanel();
		volver.setBounds(aceptar.getX() + aceptar.getWidth() + 40, tab.getY() + tab.getHeight() + 20,
				(tab.getWidth() / 2) - 40, 40);
		volver.setLayout(new BorderLayout(0, 0));
		contentPane.add(volver);
		lVolver = new JLabel("Volver");
		lVolver.setForeground(Herramientas.WHITE);
		volver.setBackground(Herramientas.BLACK);
		volver.setBorder(new LineBorder(col));
		volver.addMouseListener(this);
		volver.setCursor(Herramientas.HAND_CURSOR);
		lVolver.setHorizontalAlignment(0);
		lVolver.setFont(Herramientas.FUENTE_TITULO_DEFAULT);
		volver.add(lVolver, "Center");

	}

	public void cambioDeColor() {
		LineBorder linea = new LineBorder(col);
		panelBotones.setBorder(new CompoundBorder(linea, new LineBorder(Herramientas.black, 3)));
		for (int i = 0; i < 13; i++) {
			for (int j = 0; j < 16; j++) {
				if (!data.cine[i][j]) {
					matrizBotones[i][j].setBorder(linea);
					matrizBotones[i][j].setBackground(Herramientas.black);
					matrizLabels[i][j].setForeground(col);
				}
			}
		}
		for (int i = 0; i < cant; i++) {
			matrizBotones[pI][pJ + i].setBorder(null);
			matrizBotones[pI][pJ + i].setBackground(col);
			matrizLabels[pI][pJ + i].setForeground(Herramientas.black);
		}

		tabs[0][0].setBackground(col);
		tabLabels[0][0].setForeground(Herramientas.black);
		for (int i = 1; i < tabLabels.length; i++) {
			for (int j = 0; j < tabLabels[0].length; j++) {
				tabLabels[i][j].setBorder(linea);
				tabLabels[i][j].setForeground(col);

			}
		}
		lAceptar.setForeground(col);
		aceptar.setBackground(Herramientas.BLACK);
		aceptar.setBorder(linea);

		lVolver.setForeground(col);
		volver.setBackground(Herramientas.BLACK);
		volver.setBorder(linea);
	}

	public void mouseExited(MouseEvent e) {
		super.mouseExited(e);
		if (e.getSource() == aceptar) {
			Border linea = new LineBorder(col);
			aceptar.setBorder(linea);
			aceptar.setBackground(Herramientas.BLACK);
			lAceptar.setForeground(col);
		}
		if (e.getSource() == volver) {
			Border linea = new LineBorder(col);
			volver.setBorder(linea);
			volver.setBackground(Herramientas.BLACK);
			lVolver.setForeground(col);
		}
	}

	public void mouseEntered(MouseEvent e) {
		super.mouseEntered(e);
		for (int i = 0; i < 13; i++) {
			for (int j = 0; j < 16; j++) {
				if (e.getSource() == matrizBotones[i][j]) {
					int num = estaLibreMouse(i, j);
					if (num != -7) {
						pintarLibres(false);
						pI = i;
						pJ = j - num;
						pintarLibres(true);
						actualizarLabel();
					}
				}
			}
		}
		if (e.getSource() == aceptar) {
			aceptar.setBorder(null);
			aceptar.setBackground(col);
			lAceptar.setForeground(Herramientas.BLACK);
		}
		if (e.getSource() == volver) {
			volver.setBorder(null);
			volver.setBackground(col);
			lVolver.setForeground(Herramientas.BLACK);
		}
	}

	public void cambiarMaximizado(boolean esGrande) {
	}

	public boolean estaLibre(int pI, int pJ) {
		boolean res = true;
		if (pJ > 16 - cant)
			res = false;

		if (pJ < 0)
			res = false;
		for (int i = 0; i < cant && res; i++) {
			if (data.cine[pI][pJ + i]) {
				res = false;
			}
		}
		return res;
	}

	public int estaLibreMouse(int pI, int pJ) {
		int pos = -7;
		for (int i = 0; i < cant && pos == -7; i++) {
			if (estaLibre(pI, pJ - i)) {
				pos = i;
			}
		}
		return pos;
	}

	public void pintarLibres(boolean estaLibre) {
		if (estaLibre) {
			for (int i = 0; i < cant; i++) {
				matrizBotones[pI][pJ + i].setBackground(col);
				matrizLabels[pI][pJ + i].setForeground(Herramientas.black);
				matrizBotones[pI][pJ + i].setBorder(null);
			}
		} else {
			for (int i = 0; i < cant; i++) {
				matrizLabels[pI][pJ + i].setForeground(col);
				matrizBotones[pI][pJ + i].setBackground(Herramientas.black);
				matrizBotones[pI][pJ + i].setBorder(new LineBorder(col));
			}
		}
	}

	public void keyPressed(KeyEvent e) {
		super.keyPressed(e);
		int code = e.getKeyCode();
		int[] teclas = { KeyEvent.VK_W, KeyEvent.VK_UP, KeyEvent.VK_S, KeyEvent.VK_DOWN, KeyEvent.VK_D,
				KeyEvent.VK_RIGHT, KeyEvent.VK_A, KeyEvent.VK_LEFT, KeyEvent.VK_TAB };
		if (estaXEnY(teclas, code)) {
			int pAuxI = pI;
			int pAuxJ = pJ;
			if (code == teclas[0] || code == teclas[1]) {
				pintarLibres(false);
				mArriba();
				pintarLibres(true);
			}
			if (code == teclas[2] || code == teclas[3]) {
				pintarLibres(false);
				mAbaj();
				pintarLibres(true);
			}
			if (code == teclas[4] || code == teclas[5]) {
				pintarLibres(false);
				mDere();
				pintarLibres(true);
			}
			if (code == teclas[6] || code == teclas[7]) {
				pintarLibres(false);
				mIzq();
				pintarLibres(true);
			}
			if (code == teclas[8]) {
				pintarLibres(false);
				if (e.isShiftDown()) {
					mAnt();
				} else {
					mSig();
				}
				pintarLibres(true);
			}
			if (pI != pAuxI || pJ != pAuxJ) {
				actualizarLabel();
			}
		}
		if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE) {
			terminar();
		}
		if (code == KeyEvent.VK_ESCAPE) {
			ventanaAnterior.actualizarColores();
			ventanaAnterior.setVisible(true);
			setVisible(false);
		}

		if (code == KeyEvent.VK_PAGE_DOWN) {
			if (e.isControlDown()) {
				data.vaciarCine();
				VentanaCine vCine = new VentanaCine(cliente, cant, ventanaAnterior);
				vCine.actualizarColores();
				vCine.setVisible(true);
				setVisible(false);
			}
		}
	}

	public void mArriba() {
		if (pI != 0) {
			pI--;
			if (!estaLibre(pI, pJ)) {
				mArriba();
			}
		} else {
			pI = 13;
			mArriba();
		}
	}

	public void mAbaj() {
		if (pI != 12) {
			pI++;
			if (!estaLibre(pI, pJ)) {
				mAbaj();
			}
		} else {
			pI = -1;
			mAbaj();
		}
	}

	public void mDere() {
		if (pJ != 16 - cant) {
			pJ++;
			if (!estaLibre(pI, pJ)) {
				mDere();
			}
		} else {
			pJ = -1;
			mDere();
		}
	}

	public void mIzq() {
		if (pJ != 0) {
			pJ--;
			if (!estaLibre(pI, pJ)) {
				mIzq();
			}
		} else {
			pJ = 16 - cant + 1;
			mIzq();
		}
	}

	public void mSig() {
		if (pJ >= 16 - cant) {
			pI++;
			if (pI >= 13)
				pI = 0;
			if (!hayEspacioEnlaFila()) {
				mSig();
			}
			pJ = -1;
		}
		int pJAux = pJ;
		mDere();
		if (pJ < pJAux) {
			pJ = 16 - cant;
			mSig();
		}
	}

	public void mAnt() {
		if (pJ <= 0) {
			pI--;
			if (pI <= -1)
				pI = 12;
			if (!hayEspacioEnlaFila()) {
				mAnt();
			}
			pJ = 16 - cant + 1;
		}
		int pJAux = pJ;
		mIzq();
		if (pJ > pJAux) {
			pJ = 0;
			mAnt();
		}
	}

	public boolean hayEspacioEnlaFila() {
		for (int i = 0; i < 16 - cant + 1; i++) {
			if (estaLibre(pI, i)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void autoDestruccion() {
		System.exit(0);
	}

	public boolean estaXEnY(int[] arr, int num) {
		for (int i = 0; i < arr.length; i++) {
			if (num == arr[i]) {
				return true;
			}
		}
		return false;
	}

	public double calcPrecio() {
		return cant * obtenerValorUnitario();
	}

	public double obtenerValorUnitario() {
		int vUnitario = 12000;
		if (obtenerTipoSilla() == tiposDeSilla.Preferencial) {
			vUnitario = 16000;
		}
		return vUnitario;
	}

	public double calcPrecioTarjGold() {
		return calcPrecio() * 0.8;
	}

	public double calcPrecioTarjBasic() {
		return calcPrecio() * 0.95;
	}

	public tiposDeSilla obtenerTipoSilla() {
		String cadena = matrizLabels[pI][pJ].getText();
		char letra = cadena.charAt(0);
		if (letra >= 'J') {
			return tiposDeSilla.Preferencial;
		}
		return tiposDeSilla.General;
	}

	public String[] obtenerSillas() {
		String[] sillas = new String[cant];
		for (int i = 0; i < cant; i++) {
			sillas[i] = matrizLabels[pI][pJ + i].getText();
		}
		return sillas;
	}

	public String obtenerSillasString() {
		String[] sillas = obtenerSillas();
		String sillasString = "";
		for (int i = 0; i < cant - 1; i++) {
			sillasString += sillas[i] + " ";
		}
		sillasString += sillas[cant - 1] + "";
		return sillasString;
	}

	public double calcPuntosBasic() {
		return calcPrecioTarjBasic() / 100d;
	}

	public double calcPuntosGold() {
		return (calcPrecioTarjGold() / 100d) * 2;
	}

	public String[][] obtenerCadenaPreview() {
		String cad[][] = { { "Vista Previa", "Preview" }, { "Sillas", obtenerSillasString() + " (" + cant + ")" },
				{ "Tipo de Silla", obtenerTipoSilla().name() },
				{ "Valor Unitario", "$" + Herramientas.formatoSinDecimal(obtenerValorUnitario()) },
				{ "Precio sin dcto", "$" + Herramientas.formatoSinDecimal(calcPrecio()) },
				{ "Precio (tarjeta Basic)", "$" + Herramientas.formatoSinDecimal(calcPrecioTarjBasic()) },
				{ "Puntos (tarjeta Basic)", Herramientas.formatoSinDecimal(calcPuntosBasic()) },
				{ "Precio (tarjeta Gold)", "$" + Herramientas.formatoSinDecimal(calcPrecioTarjGold()) },
				{ "Puntos (tarjeta Gold)", Herramientas.formatoSinDecimal(calcPuntosGold()) } };

		return cad;
	}

	public void actualizarLabel() {
		String[][] cad = obtenerCadenaPreview();
		cambiarTamLabel(0, 0, cad);
		for (int i = 1; i < cad.length; i++) {
			for (int j = 0; j < cad[0].length; j++) {
				cambiarTamLabel(i, j, cad);
			}
		}
	}

	public void mouseReleased(MouseEvent e) {
		super.mouseReleased(e);
		for (int i = 0; i < 13; i++) {
			for (int j = 0; j < 16; j++) {
				if (e.getSource() == matrizBotones[i][j]) {
					Rectangle bounds = matrizBotones[i][j].getBounds();
					bounds.x += panelBotones.getX();
					bounds.y += panelBotones.getY();
					if (Herramientas.estaEnRangoDe(matrizBotones[i][j])) {
						terminar();
					}
				}
			}
		}
		if (e.getSource() == aceptar) {
			if (Herramientas.estaEnRangoDe(aceptar)) {
				terminar();
			}
		}
		if (e.getSource() == volver) {
			if (Herramientas.estaEnRangoDe(volver)) {
				ventanaAnterior.setVisible(true);
				ventanaAnterior.actualizarColores();
				setVisible(false);
			}
		}

	}

	public void cambiarTamLabel(int i, int j, String cad[][]) {

		tabLabels[i][j].setText(cad[i][j]);
		tabLabels[i][j].setToolTipText(cad[i][j]);
		tabLabels[i][j].setFont(Herramientas.FUENTE_TITULO_DEFAULT.deriveFont(100f));
		Double proporcionDouble = (tabLabels[i][j].getPreferredSize().getHeight() + 0d)
				/ (tabLabels[i][j].getPreferredSize().getWidth() + 0d);
		FontMetrics fM = tabLabels[i][j].getFontMetrics(tabLabels[i][j].getFont());
		while (fM.stringWidth(tabLabels[i][j].getText()) * proporcionDouble > tab.getHeight() / tabLabels.length) {
			tabLabels[i][j]
					.setFont(tabLabels[i][j].getFont().deriveFont((tabLabels[i][j].getFont().getSize() - 1) + 0f));
			fM = tabLabels[i][j].getFontMetrics(tabLabels[i][j].getFont());
		}
		while (fM.stringWidth(tabLabels[i][j].getText()) > tab.getWidth() / tabLabels[0].length) {
			tabLabels[i][j]
					.setFont(tabLabels[i][j].getFont().deriveFont((tabLabels[i][j].getFont().getSize() - 1) + 0f));
			fM = tabLabels[i][j].getFontMetrics(tabLabels[i][j].getFont());
		}
		tabLabels[i][j].setFont(tabLabels[i][j].getFont().deriveFont((tabLabels[i][j].getFont().getSize() - 2) + 0f));

	}

	public void terminar() {
		for (int i = 0; i < cant; i++) {
			data.cine[pI][pJ + i] = true;
		}
		aceptar.setBackground(col);
		aceptar.setBorder(null);
		lAceptar.setForeground(Herramientas.BLACK);
		HiloCargando miHiloCargando = new HiloCargando(aceptar, lAceptar, "Cargando");
		interrumpirHiloColor();
		miHiloCargando.start();
		data.escribirInfo(data.cine, 0);
		VentanaCine vCine = new VentanaCine(cliente, cant, ventanaAnterior);
		vCine.actualizarColores();
		vCine.setVisible(true);
		miHiloCargando.interrupt();
		setVisible(false);
	}
}