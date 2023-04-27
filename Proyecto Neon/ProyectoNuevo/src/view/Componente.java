package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Componente extends JPanel implements MouseListener, MouseMotionListener {
	public static final int brighter = 0;
	public static final int darker = 1;
	public static final Color defaultColor = Color.white;

	public Componente() {
		setOpaque(false);
		setLayout(new BorderLayout());
		setBackground(defaultColor);
	}

	public void actualizarColor(Color bg) {
		setBackground(bg);
		setColorAux(bg);
	}

	public void setTextInsets(int top, int left, int bottom, int right) {
		setBorder(BorderFactory.createEmptyBorder(top, left, bottom, right));
	}

	public void setBackground(int bg) {
		if (bg == brighter)
			setBackground(getColor().brighter());
		if (bg == darker)
			setBackground(getColor().darker());

	}

	public void setBackground(Color bg) {
		setColor(bg);
		super.setBackground(bg);
	}

	public void setPreferredSize(int weight, int height) {
		setPreferredSize(new Dimension(weight, height));
	}

	public void addClicListener(ClicListener b) {
		if (getMouseListeners().length == 0)
			addMouseListener(this);
		getListaClicListener().add(b);
	}

	public void removeAllBotonListeners() {
		getListaClicListener().clear();
		if (!isCanBeSelectioned() & getMouseMotionListeners().length == 0) {
			for (MouseListener l : getMouseListeners())
				removeMouseListener(l);
		}
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
		if (isCanBeArrastred()) {
			System.out.println("Location: " + getLocation());
		}
		for (ClicListener b : getListaClicListener())
			if (getMousePosition() != null)
				b.botonPresionado(new Evento(this));

	}

	public Point getCenteredLocation(int width, int height) {
		return new Point((int) ((getWidth() - width) / 2), (int) ((getHeight() - height) / 2));
	}

	public Point getCenteredLocation(Dimension d) {
		return new Point((int) ((getWidth() - d.getWidth()) / 2), (int) ((getHeight() - d.getHeight()) / 2));
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (isCanBeSelectioned()) {
			setColorAux(getColor());
			setBackground(getColor().brighter());
			setSelectioned(true);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (isCanBeSelectioned()) {
			setBackground(getColorAux());
			setSelectioned(false);
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (isCanBeArrastred())
			setLocation(getX() - (getWidth() / 2) + e.getX(), getY() - (getHeight() / 2) + e.getY());
	}

	public void mouseMoved(MouseEvent e) {
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean isCanBeSelectioned() {
		return canBeSelectioned;
	}

	public void setCanBeSelectioned(boolean b) {
		canBeSelectioned = b;
		if (b) {
			if (getMouseListeners().length == 0) {
				addMouseListener(this);
			}
		} else {
			if (getMouseMotionListeners().length == 0) {
				for (MouseListener l : getMouseListeners())
					removeMouseListener(l);
			}
		}
	}

	public List<ClicListener> llenarLista(List<ClicListener> lista) {
		List<ClicListener> listaRet = new ArrayList<ClicListener>();
		for (int i = 0; i < lista.size(); i++) {
			listaRet.add(lista.get(i));
		}
		return listaRet;
	}

	public void setCanBeArrastred(boolean canBeArrastred) {
		this.canBeArrastred = canBeArrastred;
		if (canBeArrastred) {
			setListaAuxListener(llenarLista(getListaClicListener()));
			getListaClicListener().clear();
			if (getMouseListeners().length == 0) {
				addMouseListener(this);
			}
			if (getMouseMotionListeners().length == 0) {
				addMouseMotionListener(this);
			}
		} else {
			for (MouseMotionListener l : getMouseMotionListeners())
				removeMouseMotionListener(l);
			if (getClicListeners().size() != 0) {
				setListaClicListener(llenarLista(getClicListeners()));
				getClicListeners().clear();
			}
			if (!isCanBeSelectioned() & getListaClicListener().size() == 0) {
				for (MouseListener l : getMouseListeners())
					removeMouseListener(l);
			}
		}
	}

	public Color getColorAux() {
		return colorAux;
	}

	public void setColorAux(Color colorAux) {
		this.colorAux = colorAux;
	}

	public static GridBagConstraints crearGridBag(int x, int y) {
		return crearGridBag(x, y, 0, 0);
	}

	public static GridBagConstraints crearGridBag(int x, int y, int weightx, int weighty) {
		return crearGridBag(x, y, weightx, weighty, GridBagConstraints.CENTER);
	}

	public static GridBagConstraints crearGridBag(int x, int y, int weightx, int weighty, int position) {
		return crearGridBag(x, y, weightx, weighty, position, 0, 0);
	}

	public static GridBagConstraints crearGridBag(int x, int y, int weightx, int weighty, int position, int espaciadox,
			int espaciadoy) {
		return crearGridBag(x, y, weightx, weighty, position, espaciadox, espaciadoy, GridBagConstraints.NONE);
	}

	public static GridBagConstraints crearGridBag(int x, int y, int weightx, int weighty, int espaciadox,
			int espaciadoy) {
		return crearGridBag(x, y, weightx, weighty, GridBagConstraints.CENTER, espaciadox, espaciadoy,
				GridBagConstraints.NONE);
	}

	public static GridBagConstraints crearGridBag(int x, int y, int weightx, int weighty, Insets insets) {
		GridBagConstraints gbc = crearGridBag(x, y, weightx, weighty, GridBagConstraints.CENTER);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = insets;
		return gbc;
	}

	public static GridBagConstraints crearGridBag(int x, int y, int weightx, int weighty, int position, int espaciadox,
			int espaciadoy, int fill) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.weightx = weightx;
		gbc.weighty = weighty;
		gbc.ipadx = espaciadox;
		gbc.ipady = espaciadoy;
		gbc.anchor = position;
		gbc.fill = fill;
		return gbc;
	}

	public List<ClicListener> getListaClicListener() {
		return listaClicListener;
	}

	public void setListaClicListener(List<ClicListener> listaClicListener) {
		this.listaClicListener = listaClicListener;
	}

	public List<ClicListener> getClicListeners() {
		return listaAuxListener;
	}

	private void setListaAuxListener(List<ClicListener> listaAuxListener) {
		this.listaAuxListener = listaAuxListener;
	}

	public boolean isSelectioned() {
		return isSelectioned;
	}

	private void setSelectioned(boolean isSelectioned) {
		this.isSelectioned = isSelectioned;
	}

	public boolean isCanBeArrastred() {
		return canBeArrastred;
	}

	public void configAsButton(ClicListener l) {
		configAsButton(l, getColor().brighter());
	}

	public void configAsButton(ClicListener l, Color c) {
		addClicListener(l);
		setOpaque(true);
		setCanBeSelectioned(true);
		setBackground(c);
	}

	private List<ClicListener> listaClicListener = new ArrayList<ClicListener>(),
			listaAuxListener = new ArrayList<ClicListener>();
	private Color color, colorAux;
	private boolean canBeArrastred = false;
	private boolean canBeSelectioned = false;
	private boolean isSelectioned = false;
}
