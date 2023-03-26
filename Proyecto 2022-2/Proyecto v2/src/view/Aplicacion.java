package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.data;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;

public class Aplicacion extends JFrame implements ActionListener, KeyListener {
	private JPanel contentPane;
	private JButton bSala,bBuscarCliente,bHistorialdeCompras,bDarTarjeta,
	bRecargarTarjeta,bRegistarCliente,bConsolidadoVentas,bFacturarConfiteria,matrizBut[][];
	private JLabel bienvenida,titulo;
	private int posI = 0, posJ = 0;
	//Crea o lee la información
	private data data = new data();
	private Color verde = data.verde;
	private Color white = data.white;
	private RegistarCliente registarCliente;
	private VentanaCinePrincipal matriz;
	private VentanaConfiPrincipal VentanaConfiPrincipal;
	private BuscarCliente buscarCliente;
	private DarTarjeta DarTarjeta;
	private RecargarTarjeta RecargarTarjeta;
	private JButton btnNewButton;
	private HistorialDeCompras HistorialDeCompras;
	private MostrarConsVentas MostrarConsVentas;

	public Aplicacion() {
		/* Declara otras clases para cuando necesiten llamarse
		El this es la propia Aplicacion y le sirve a la otra ventana para
		que la ventana principal se pueda llamar otra vez desde cualquier
		otra clase
		*/
		MostrarConsVentas = new MostrarConsVentas(this);
		HistorialDeCompras = new HistorialDeCompras(this);
		VentanaConfiPrincipal = new VentanaConfiPrincipal(this);
		DarTarjeta = new DarTarjeta(this);
		registarCliente = new RegistarCliente(this);
		matriz = new VentanaCinePrincipal(this);
		buscarCliente = new BuscarCliente(this);
		RecargarTarjeta = new RecargarTarjeta(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		setLocationRelativeTo(null);
		setResizable(false);

		ImageIcon crispIcon = new ImageIcon("Imagenes/crispetast.png");
		setIconImage(crispIcon.getImage());
		setTitle("Stellar Cinema");

		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);


		titulo = new JLabel();
		ImageIcon tituloCine = new ImageIcon("Imagenes/ttlCine.png");
		titulo.setBounds(117, 85, 461, 55);
		titulo.setIcon(new ImageIcon(tituloCine.getImage().getScaledInstance(titulo.getWidth(), titulo.getHeight(), Image.SCALE_SMOOTH)));
		contentPane.add(titulo);

		bienvenida = new JLabel("Bienvenid@ a");
		bienvenida.setHorizontalAlignment(SwingConstants.CENTER);
		bienvenida.setFont(new Font("Tahoma", Font.PLAIN, 30));
		bienvenida.setForeground(new Color(255, 255, 255));
		bienvenida.setBounds(234, 47, 227, 47);
		contentPane.add(bienvenida);

		bSala = new JButton("Ir a Sala");
		bSala.setFont(new Font("Tahoma", Font.PLAIN, 16));
		bSala.setBounds(135, 165, 200, 40);
		bSala.setBackground(verde);
		bSala.setBorder(null);
		getRootPane().setDefaultButton(bSala);
		contentPane.add(bSala);

		bRegistarCliente = new JButton("Registar Cliente");
		bRegistarCliente.setFont(new Font("Tahoma", Font.PLAIN, 16));
		bRegistarCliente.setBackground(new Color(255, 255, 255));
		bRegistarCliente.setBorder(null);
		bRegistarCliente.setBounds(135, 230, 200, 40);
		contentPane.add(bRegistarCliente);

		bRecargarTarjeta = new JButton("Recargar Tarjeta");
		bRecargarTarjeta.setFont(new Font("Tahoma", Font.PLAIN, 16));
		bRecargarTarjeta.setBorder(null);
		bRecargarTarjeta.setBackground(new Color(255, 255, 255));
		bRecargarTarjeta.setBounds(375, 295, 200, 40);
		contentPane.add(bRecargarTarjeta);

		bDarTarjeta = new JButton("Dar Tarjeta a Cliente");
		bDarTarjeta.setFont(new Font("Tahoma", Font.PLAIN, 16));
		bDarTarjeta.setBorder(null);
		bDarTarjeta.setBackground(new Color(255, 255, 255));
		bDarTarjeta.setBounds(135, 295, 200, 40);
		contentPane.add(bDarTarjeta);

		bFacturarConfiteria = new JButton("Facturar Confitería");
		bFacturarConfiteria.setFont(new Font("Tahoma", Font.PLAIN, 16));
		bFacturarConfiteria.setBorder(null);
		bFacturarConfiteria.setBackground(new Color(255, 255, 255));
		bFacturarConfiteria.setBounds(375, 165, 200, 40);
		contentPane.add(bFacturarConfiteria);

		bConsolidadoVentas = new JButton("Consolidado de Ventas");
		bConsolidadoVentas.setFont(new Font("Tahoma", Font.PLAIN, 16));
		bConsolidadoVentas.setBorder(null);
		bConsolidadoVentas.setBackground(new Color(255, 255, 255));
		bConsolidadoVentas.setBounds(375, 360, 200, 40);
		contentPane.add(bConsolidadoVentas);

		bHistorialdeCompras = new JButton("Historial de Compras");
		bHistorialdeCompras.setFont(new Font("Tahoma", Font.PLAIN, 16));
		bHistorialdeCompras.setBorder(null);
		bHistorialdeCompras.setBackground(new Color(255, 255, 255));
		bHistorialdeCompras.setBounds(135, 360, 200, 40);
		contentPane.add(bHistorialdeCompras);

		bBuscarCliente = new JButton("Buscar Cliente");
		bBuscarCliente.setFont(new Font("Tahoma", Font.PLAIN, 16));
		bBuscarCliente.setBorder(null);
		bBuscarCliente.setBackground(new Color(255, 255, 255));
		bBuscarCliente.setBounds(375, 230, 200, 40);
		contentPane.add(bBuscarCliente);

		matrizBut = new JButton[4][2];
		matrizBut[0][0] = bSala;
		matrizBut[0][1] = bFacturarConfiteria;
		matrizBut[1][0] = bRegistarCliente;
		matrizBut[1][1] = bBuscarCliente;
		matrizBut[2][0] = bDarTarjeta;
		matrizBut[2][1] = bRecargarTarjeta;
		matrizBut[3][0] = bHistorialdeCompras;
		matrizBut[3][1] = bConsolidadoVentas;

		btnNewButton = new JButton("Ver datos");
		btnNewButton.setBounds(10, 11, 89, 23);
		btnNewButton.addActionListener(this);
		btnNewButton.addKeyListener(this);
		contentPane.add(btnNewButton);
		for(int i = 0;i<4;i++) {
			for(int j = 0;j<2;j++) {
				/*
				 * Hace que cada boton escuche el teclado y hace
				 * que también tenga su propia funcionalidad cuando se
				 * hunde
				 */
				matrizBut[i][j].addKeyListener(this);
				matrizBut[i][j].addActionListener(this);
			}
		}
		JLabel fondo = new JLabel();
		fondo.setBounds(0, 0, 700, 500);
		ImageIcon fCine = new ImageIcon("Imagenes/fondoPrincipal.png");
		fondo.setIcon(new ImageIcon(fCine.getImage().getScaledInstance(fondo.getWidth(), fondo.getHeight(), Image.SCALE_SMOOTH)));
		contentPane.add(fondo);

	}
	//Lo que pasa cuando se hunde un boton
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==bSala) {
			matriz.setVisible(true);
			setVisible(false);
		}
		if(e.getSource()==bBuscarCliente) {
			buscarCliente.setVisible(true);
			setVisible(false);
		}
		if(e.getSource()==bConsolidadoVentas) {
			MostrarConsVentas.setVisible(true);
			setVisible(false);
		}
		if(e.getSource()==bDarTarjeta) {
			DarTarjeta.setVisible(true);
			setVisible(false);
		}
		if(e.getSource()==bFacturarConfiteria) {
			VentanaConfiPrincipal.setVisible(true);
			setVisible(false);
		}
		if(e.getSource()==bHistorialdeCompras) {
			HistorialDeCompras.setVisible(true);
			setVisible(false);
		}
		if(e.getSource()==bRecargarTarjeta) {
			RecargarTarjeta.setVisible(true);
			setVisible(false);
		}
		if(e.getSource()==bRegistarCliente) {
			registarCliente.setVisible(true);
			setVisible(false);
		}
		if(e.getSource()==btnNewButton) {
			VentanaTodo MostrarTodo = new VentanaTodo(this);
			MostrarTodo.setVisible(true);
			setVisible(false);
		}
	}

	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	public void keyPressed(KeyEvent e) {
		int x = e.getKeyCode();
		/* Cambia el boton default (para cuando se da enter),
		 * El boton anterior pasa a blanco y el otro a verde
		 * (se selecciona)
		 */

		if(x == KeyEvent.VK_UP) {
			if(posI>0) {
				matrizBut[posI][posJ].setBackground(white);
				posI--;
				getRootPane().setDefaultButton(matrizBut[posI][posJ]);

			}
		}
		if(x == KeyEvent.VK_RIGHT) {
			if(posJ==0) {
				matrizBut[posI][posJ].setBackground(white);
				posJ++;
				getRootPane().setDefaultButton(matrizBut[posI][posJ]);
			}
		}
		if(x == KeyEvent.VK_LEFT) {
			if(posJ==1) {
				matrizBut[posI][posJ].setBackground(white);
				posJ--;
				getRootPane().setDefaultButton(matrizBut[posI][posJ]);
			}
		}
		if(x == KeyEvent.VK_DOWN) {
			if(posI<3) {
				matrizBut[posI][posJ].setBackground(white);
				posI++;
				getRootPane().setDefaultButton(matrizBut[posI][posJ]);
			}
		}
		matrizBut[posI][posJ].setBackground(verde);
	}
}