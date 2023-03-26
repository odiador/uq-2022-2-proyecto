package vista;

import static paneles.VistaPedirCedu.OPCIONES.BUSCAR;
import static paneles.VistaPedirCedu.OPCIONES.CONFITERIA;
import static paneles.VistaPedirCedu.OPCIONES.ELIMINAR;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JTabbedPane;

import admin.PanelDinamicoAdmin;
import config.Constantes;
import custom.*;
import mostrardatos.*;
import paneles.*;
import principal.*;
import tools.Imgs;

public class Aplicacion extends Plantilla implements MenuListener, KeyListener, VentanaListener, CustomListener {

	public static final void mostrarAplicacion () {
		new Aplicacion().setVisible(true);
	}

	public static final void MostrarAplicacion (int i) {
		new Aplicacion(i).setVisible(true);
	}

	public Aplicacion() {
		this(0);
	}

	public Aplicacion(int select) {
		setSelectionTab(select);

		setPaneles(new Panel[getTxtBotonesMenu().length]);

		setPanelHeader(new Header("Juan Manuel Amador Roa", Imgs.IMAGEN_DEFAULT));

		setPanelMenu(new PanelMenu(select, txtBotonesMenu, new Color(40, 40, 40)));
		setPanelPestanias(new JTabbedPane());

		getPaneles()[0] = new ComprarBoleta();
		getPaneles()[1] = new VistaPedirCedu(CONFITERIA);
		getPaneles()[2] = new VistaRegistrarCliente();
		getPaneles()[3] = new VistaPedirCedu(ELIMINAR);
		getPaneles()[4] = new VistaPedirCedu(BUSCAR);
		getPaneles()[5] = new AgregarTarjeta();
		getPaneles()[6] = new RecargarTarjeta();
		getPaneles()[7] = new PanelDinamicoHist();
		getPaneles()[8] = new PanelDinamicoCons();
		getPaneles()[9] = new PanelMostrarDatos();
		getPaneles()[10] = new PanelDinamicoAdmin();

		getPaneles()[0].addCustomListener(this);
		getPaneles()[10].addCustomListener(this);

		for (int i = 0; i < getTxtBotonesMenu().length; i++)
			getPanelPestanias().addTab(getTxtBotonesMenu()[i], getPaneles()[i]);

		getPanelPestanias().setSelectedIndex(getSelectionTab());

		getPanelPestanias().setLayout(new CardLayout());
		getPanelPestanias().addKeyListener(this);

		getPanelHeader().setPreferredSize(300, 160);

		Panel contenedor = new Panel(Constantes.defaultLineColor, 0, 0);
		contenedor.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 1));
		contenedor.add(getPanelHeader(), BorderLayout.NORTH);
		contenedor.add(getPanelMenu());
		getPanelMenu().addMenuListener(this);
		addVentanaListener(this);

		agregarBody(contenedor, BorderLayout.WEST);
		agregarBody(getPanelPestanias(), BorderLayout.CENTER);
	}

	@Override
	public void configurarTam () {
		setSize(1000, 600);
	}

	public Text configurarBoton (String text) {
		Text btn = new Text(text);
		btn.setCanBeSelectioned(true);
		btn.setOpaque(true);
		btn.addClicListener(this);
		btn.setBackground(new Color(75, 75, 75));
		return btn;

	}

	public PanelMenu getPanelMenu () {
		return panelMenu;
	}

	public void setPanelMenu (PanelMenu panelMenu) {
		this.panelMenu = panelMenu;
	}

	public JTabbedPane getPanelPestanias () {
		return panelPestanias;
	}

	public void setPanelPestanias (JTabbedPane panelPestanias) {
		this.panelPestanias = panelPestanias;
	}

	public Panel getPanelHeader () {
		return panelHeader;
	}

	public void setPanelHeader (Panel panelHeader) {
		this.panelHeader = panelHeader;
	}

	public Panel[] getPaneles () {
		return paneles;
	}

	public void setPaneles (Panel paneles[]) {
		this.paneles = paneles;
	}

	public static final String[] getTxtBotonesMenu () {
		return txtBotonesMenu;
	}

	public int getSelectionTab () {
		return selectionTab;
	}

	public void setSelectionTab (int selectionTab) {
		this.selectionTab = selectionTab;
	}

	public void keyPressed (KeyEvent e) {
		getPanelMenu().keyPressed(e);
	}

	@Override
	public void cambioDeSeleccion (EventoMenu e) {
		setSelectionTab(e.getIndex());
		getPanelPestanias().setSelectedIndex(e.getIndex());
	}

	@Override
	public void actuaLizarVentana () {
		System.out.println(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner());
	}

	@Override
	public void accionRealizada () {

		getPanelPestanias().removeTabAt(0);

		getPaneles()[0] = new ComprarBoleta();
		getPaneles()[0].addCustomListener(this);
		getPanelPestanias().insertTab(getTxtBotonesMenu()[0], null, getPaneles()[0], null, 0);
		getPaneles()[0].revalidate();
		getPaneles()[0].repaint();
		getPanelPestanias().revalidate();
		if (getSelectionTab() == 0) {
			new Thread(new Runnable() {

				@Override
				public void run () {
					try {
						Thread.sleep(100);
						getPanelPestanias().setSelectedIndex(2);
						Thread.sleep(100);
						getPanelPestanias().setSelectedIndex(0);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}).start();
		}

	}

	private JTabbedPane panelPestanias;
	private PanelMenu panelMenu;
	private Panel panelHeader, paneles[];
	private static final String txtBotonesMenu[] = { "Comprar Boleta", "Comprar Confitería", "Registrar Cliente",
			"Eliminar cliente", "Buscar Cliente", "Comprar Tarjeta", "Recargar Tarjeta", "Historial de Compras",
			"Consolidado de Ventas", "Ver Datos", "Admin Commands" };
	private int selectionTab = 0;
}
