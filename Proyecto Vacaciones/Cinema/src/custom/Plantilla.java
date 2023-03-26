package custom;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

import config.Constantes;

public abstract class Plantilla extends JFrame
		implements ClicListener, MouseMotionListener, MouseListener, KeyListener {

	public Plantilla() {
		configurarTam();
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		setPanel(new Panel());

		setPanelSuperior(new Panel(lineColor));
		setPanelBody(new Panel());

		setBorder(BorderFactory.createLineBorder(lineColor));

		setContentPane(getPanel());
		getPanel().setLayout(null);

		setCerrar(new Text("X"));
		setMinimizar(new Text("0"));
		setMaximizar(new Text(getMaximized() ? "2" : "1"));

		getCerrar().setCanBeSelectioned(true);
		getMinimizar().setCanBeSelectioned(true);
		getMaximizar().setCanBeSelectioned(true);

		getCerrar().setOpaque(true);
		getMinimizar().setOpaque(true);
		getMaximizar().setOpaque(true);

		getCerrar().setBackground(new Color(35, 35, 35));
		getMinimizar().setBackground(new Color(35, 35, 35));
		getMaximizar().setBackground(new Color(35, 35, 35));
		getPanelSuperior().setBackground(new Color(35, 35, 35));

		getCerrar().setFont(new Font("Untitled1", Font.PLAIN, 20));
		getMinimizar().setFont(new Font("Untitled1", Font.PLAIN, 20));
		getMaximizar().setFont(new Font("Untitled1", Font.PLAIN, 20));

		getCerrar().setTextInsets(7, 0, 0, 0);
		getMinimizar().setTextInsets(7, 0, 0, 0);
		getMaximizar().setTextInsets(7, 0, 0, 0);

		getMaximizar().addClicListener(this);
		getMinimizar().addClicListener(this);
		getCerrar().addClicListener(this);
		getPanelSuperior().addMouseMotionListener(this);
		getPanelSuperior().addMouseListener(this);

		getCerrar().setPreferredSize(new Dimension(50, 30));
		getMinimizar().setPreferredSize(new Dimension(50, 30));
		getMaximizar().setPreferredSize(new Dimension(50, 30));

		getPanel().setLayout(new BorderLayout());
		getPanelSuperior().setLayout(new GridBagLayout());

		setBodyLayout(new BorderLayout());

		getPanelBody().setBackground(lineColor);
		getPanelBody().setBorder(BorderFactory.createEmptyBorder(1, 0, 0, 0));
		getPanelSuperior().add(getCerrar(), crearGridBag(0, 0, 0, 0, GridBagConstraints.WEST));
		getPanelSuperior().add(getMinimizar(), crearGridBag(1, 0, 0, 0, GridBagConstraints.WEST));
		getPanelSuperior().add(getMaximizar(), crearGridBag(2, 0, 1, 0, GridBagConstraints.WEST));

		setBotonSeleccion(new JButton());

		getBotonSeleccion().setPreferredSize(new Dimension(0, 0));
		getBotonSeleccion().addKeyListener(this);
		getPanel().add(getBotonSeleccion(), BorderLayout.WEST);
		getPanel().add(getPanelBody(), BorderLayout.CENTER);
		getPanel().add(getPanelSuperior(), BorderLayout.NORTH);

		UIManager.put("ToolTip.background", Constantes.defaultBgColor);
		UIManager.put("ToolTip.foreground", Constantes.defaultTextColor);
		UIManager.put("ToolTip.font", Constantes.defaultFont);
	}

	public static GridBagConstraints crearGridBag (int x, int y, int weightx, int weighty, int position) {
		return Componente.crearGridBag(x, y, weightx, weighty, position);
	}

	public void setBorder (Border b) {
		getPanel().setBorder(b);
	}

	public Panel getPanel () {
		return panel;
	}

	private void setPanel (Panel panel) {
		this.panel = panel;
	}

	public void setBodyBg (Color c) {
		getPanelBody().setBackground(c);
	}

	public void setBodyLayout (LayoutManager lmg) {
		getPanelBody().setLayout(lmg);
	}

	public Component agregarBody (Component c) {
		return getPanelBody().add(c);
	}

	public void agregarBody (Component c, Object constraints) {
		getPanelBody().add(c, constraints);
	}

	public void agregarBody (Component c, Object constraints, int index) {
		getPanelBody().add(c, constraints, index);
	}

	public Component agregarBody (Component c, int index) {
		return getPanelBody().add(c, index);
	}

	public void agregarBody (PopupMenu popUp) {
		getPanelBody().add(popUp);
	}

	public Component agregarBody (String name, Component c) {
		return getPanelBody().add(name, c);
	}

	public Text getCerrar () {
		return cerrar;
	}

	private void setCerrar (Text cerrar) {
		this.cerrar = cerrar;
	}

	public boolean getMaximized () {
		return isMaximized;
	}

	public void setMaximized (boolean isMaximized) {
		this.isMaximized = isMaximized;
	}

	public Text getMaximizar () {
		return maximizar;
	}

	public void setMaximizar (Text maximizar) {
		this.maximizar = maximizar;
	}

	public Text getMinimizar () {
		return minimizar;
	}

	public void setMinimizar (Text minimizar) {
		this.minimizar = minimizar;
	}

	public abstract void configurarTam ();

	public Panel getPanelBody () {
		return panelBody;
	}

	public void setPanelBody (Panel panelBody) {
		this.panelBody = panelBody;
	}

	public void botonPresionado (Evento e) {
		if (e.getSource() == getCerrar()) {
			if (getCloseOperation() == WindowConstants.EXIT_ON_CLOSE) System.exit(0);
			if (getCloseOperation() == WindowConstants.DISPOSE_ON_CLOSE) dispose();
			if (getCloseOperation() == WindowConstants.HIDE_ON_CLOSE) setExtendedState(JFrame.ICONIFIED);
		}

		if (e.getSource() == getMinimizar()) setExtendedState(JFrame.ICONIFIED);

		if (e.getSource() == getMaximizar()) {
			actualizarMaximizar();
		}
	}

	private void actualizarMaximizar () {
		setMaximized(!getMaximized());
		getMaximizar().setText(getMaximized() ? "2" : "1");

		if (getMaximized()) {
			setExtendedState(JFrame.MAXIMIZED_BOTH);
			if (getMaximumDimensions() == null) {
				setMaximumDimensions(getSize());
			}
		} else setExtendedState(JFrame.NORMAL);
		for (VentanaListener l : lista) l.actuaLizarVentana();

	}

	public Panel getPanelSuperior () {
		return panelSuperior;
	}

	public void setPanelSuperior (Panel panelSuperior) {
		this.panelSuperior = panelSuperior;
	}

	@Override
	public void mouseDragged (MouseEvent e) {
		if (e.getSource() == getPanelSuperior()) {
			if (getExtendedState() == JFrame.NORMAL) {
				setLocation(getX() + (e.getXOnScreen() - getxArrastre()), getY() + (e.getYOnScreen() - getyArrastre()));
				setxArrastre(e.getXOnScreen());
				setyArrastre(e.getYOnScreen());
			} else {
				actualizarMaximizar();
				int posy = e.getYOnScreen() - e.getY() - getPanelSuperior().getY();
				if (getPanelSuperior().getX() + e.getX() < (getWidth() / 2)) setLocation(
						e.getXOnScreen() - e.getX() - getPanelSuperior().getX(), posy);
				else if (getMaximumDimensions().getWidth() - getPanelSuperior().getX() - e.getX() < (getWidth() / 2)) {
					setLocation((int) getMaximumDimensions().getWidth() - getWidth(), posy);
				} else setLocation(e.getXOnScreen() - getWidth() / 2, posy);
			}
		}
	}

	@Override
	public void mousePressed (MouseEvent e) {
		setxArrastre(e.getXOnScreen());
		setyArrastre(e.getYOnScreen());
		if (getExtendedState() == JFrame.NORMAL) setPosInicial(getLocation());
	}

	@Override
	public void mouseReleased (MouseEvent e) {
		if (e.getYOnScreen() <= 3 && getExtendedState() == JFrame.NORMAL) {
			if (getPosInicial() != null) setLocation(getPosInicial());
			actualizarMaximizar();
		}
	}

	@Override
	public void mouseEntered (MouseEvent e) {}

	@Override
	public void mouseExited (MouseEvent e) {}

	@Override
	public void mouseMoved (MouseEvent e) {

	}

	@Override
	public void mouseClicked (MouseEvent e) {

	}

	public Dimension getMaximumDimensions () {
		return maximumDimensions;
	}

	public void setMaximumDimensions (Dimension maximumDimensions) {
		this.maximumDimensions = maximumDimensions;
	}

	public int getxArrastre () {
		return xArrastre;
	}

	public void setxArrastre (int xArrastre) {
		this.xArrastre = xArrastre;
	}

	public int getyArrastre () {
		return yArrastre;
	}

	public void setyArrastre (int yArrastre) {
		this.yArrastre = yArrastre;
	}

	public Point getPosInicial () {
		return posInicial;
	}

	public void setPosInicial (Point posInicial) {
		this.posInicial = posInicial;
	}

	public void setDefaultCloseOperation (int operation) {
		setClose(operation);
		super.setDefaultCloseOperation(operation);
	}

	private void setClose (int operation) {
		this.operation = operation;

	}

	public int getCloseOperation () {
		return operation;
	}

	public void keyTyped (KeyEvent e) {}

	public void keyPressed (KeyEvent e) {}

	public void keyReleased (KeyEvent e) {}

	public void setKeyNavigationEnabled (boolean ret) {
		getBotonSeleccion().setEnabled(ret);
	}

	public void setDefaultNavigation (boolean b) {
		getBotonSeleccion().setFocusTraversalKeysEnabled(b);
	}

	private JButton getBotonSeleccion () {
		return botonSeleccion;
	}

	private void setBotonSeleccion (JButton botonSeleccion) {
		this.botonSeleccion = botonSeleccion;
	}

	public void addVentanaListener (VentanaListener l) {
		lista.add(l);
	}

	private int operation;

	private Panel panel;
	private Text cerrar, minimizar, maximizar;
	private Panel panelSuperior, panelBody;
	private Dimension maximumDimensions;
	private boolean isMaximized = false;
	private int xArrastre;
	private int yArrastre;
	private Point posInicial;

	private JButton botonSeleccion;
	private List<VentanaListener> lista = new ArrayList<VentanaListener>();

	public static final Color lineColor = new Color(200, 200, 200);;
}