package principal;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.List;

import config.Constantes;
import custom.Panel;
import custom.Text;

public class PanelMenu extends Panel implements MouseWheelListener, KeyListener {

	private Text[] paneles;
	private int selection;
	private String[] textospaneles;
	private List<MenuListener> menuListeners;
	private Color defColor;

	public PanelMenu(int selection, String[] textospaneles) {
		this(selection, textospaneles, Constantes.defaultBgColor);
	}

	public PanelMenu(int selection, String[] textospaneles, Color defColor) {
		setDefColor(defColor);
		setMenuListeners(new ArrayList<MenuListener>());
		setTextospaneles(textospaneles);
		setSelection(selection);

		setLayout(new GridBagLayout());
		addMouseMotionListener(this);
		addMouseWheelListener(this);
		addKeyListener(this);
		addMouseListener(this);
		paneles = new Text[textospaneles.length];

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.BOTH;
		for (int i = 0; i <= textospaneles.length; i++) {
			gbc.gridy = i;
			if (i != textospaneles.length) {
				paneles[i] = new Text(textospaneles[i]);
				paneles[i].setPreferredSize(10, 35);
				paneles[i].setOpaque(true);
				add(paneles[i], gbc);
			} else {
				gbc.weighty = 1;
				gbc.anchor = GridBagConstraints.PAGE_START;
				add(new Panel(getDefColor()), gbc);
			}
		}
		resetBg();
		paneles[selection].setBackground(getDefColor().brighter());

	}

	private void resetBg () {
		for (int i = 0; i < paneles.length; i++) paneles[i].setBackground(getDefColor());

	}

	public void mouseReleased (MouseEvent e) {
		if (getMousePosition() != null) seleccionarElemento();
	}

	public void mouseDragged (MouseEvent e) {
		seleccionarElemento();
	}

	private void seleccionarElemento () {
		for (int i = 0; i < paneles.length; i++) {
			if (paneles[i].getMousePosition() != null && i != getSelection()) {

				resetBg();
				paneles[i].setBackground(getDefColor().brighter());

				setSelection(i);
				for (MenuListener l : getMenuListeners()) l.cambioDeSeleccion(new EventoMenu(i));
				break;
			}
		}
	}

	@Override
	public void mouseWheelMoved (MouseWheelEvent e) {
		if (e.getWheelRotation() == 0) return;
		resetBg();
		if (e.getWheelRotation() > 0) moverSelArriba();
		else moverSelAbajo();

		paneles[getSelection()].setBackground(getDefColor().brighter());
		for (MenuListener l : getMenuListeners()) l.cambioDeSeleccion(new EventoMenu(getSelection()));
	}

	private void moverSelArriba () {
		int sel = getSelection() + 1;
		if (sel >= paneles.length) sel = 0;
		setSelection(sel);
	}

	private void moverSelAbajo () {
		int sel = getSelection() - 1;
		if (sel < 0) sel = paneles.length - 1;
		setSelection(sel);
	}

	public int getSelection () {
		return selection;
	}

	public void setSelection (int selection) {
		this.selection = selection;
	}

	public String[] getTextospaneles () {
		return textospaneles;
	}

	public void setTextospaneles (String[] textospaneles) {
		this.textospaneles = textospaneles;
	}

	public List<MenuListener> getMenuListeners () {
		return menuListeners;
	}

	public void addMenuListener (MenuListener l) {
		getMenuListeners().add(l);
	}

	private void setMenuListeners (List<MenuListener> menuListeners) {
		this.menuListeners = menuListeners;
	}

	public Color getDefColor () {
		return defColor;
	}

	public void setDefColor (Color defColor) {
		this.defColor = defColor;
	}

	@Override
	public void keyTyped (KeyEvent e) {}

	@Override
	public void keyPressed (KeyEvent e) {
		int code = e.getKeyCode();
		if (movArriba(code)) moverSelAbajo();
		else if (movAbajo(code)) moverSelArriba();
		else return;
		resetBg();
		paneles[getSelection()].setBackground(getDefColor().brighter());
		for (MenuListener l : getMenuListeners()) l.cambioDeSeleccion(new EventoMenu(getSelection()));

	}

	public boolean movArriba (int tecla) {
		return tecla == KeyEvent.VK_W || tecla == KeyEvent.VK_UP;
	}

	public boolean movAbajo (int tecla) {
		return tecla == KeyEvent.VK_S || tecla == KeyEvent.VK_DOWN;
	}

	@Override
	public void keyReleased (KeyEvent e) {}
}
