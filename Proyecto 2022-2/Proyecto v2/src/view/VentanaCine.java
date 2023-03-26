package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import model.procesosCine;

public class VentanaCine extends JFrame implements ActionListener, KeyListener {
	private Color azul = new Color(0, 0, 100);
	private Color bgCol = new Color(0, 0, 0,0);
	private Color white = new Color(255, 255, 255);
	private JPanel panelMatriz;
	private static JButton matrizBotones[][];
	private JPanel contentPane;
	private JTextField pantalla;
	private JButton bArriba;
	private JButton bAbaj;
	private JButton bIzq;
	private JButton bDere;
	private JButton bAceptar;
	private JButton bVaciar;
	private Aplicacion aplicacion;
	private procesosCine procesosCine;
	private ArrayList<Object> cedus;

	private int cant;
	private int posI = 0, posJ = 0;

	public VentanaCine (int cant,Aplicacion aplicacion,ArrayList<Object> cedus) {
		// Llama algunos procesos del cine
		procesosCine = new procesosCine(cant);
		this.cedus = cedus;
		this.cant = cant;
		this.aplicacion = aplicacion;
		//de titulo queda el nombre - cedula
		setTitle((String) cedus.get(0)+" - "+(String) cedus.get(1));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,1100,618);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(bgCol);


		setContentPane(contentPane);
		contentPane.setLayout(null);

		generarPanelSup();
		generarPanelMB(cant);
		generarPanelInf();
		JLabel fondo = new JLabel();
		fondo.setBounds(0, 0, this.getWidth(), this.getHeight());
		ImageIcon fCine = new ImageIcon("Imagenes/fondoCine.png");
		fondo.setIcon(new ImageIcon(fCine.getImage().getScaledInstance(fondo.getWidth(), fondo.getHeight(), Image.SCALE_SMOOTH)));
		contentPane.add(fondo);
	}

	void generarPanelSup() {

		JPanel panelArriba = new JPanel();
		panelArriba.setBounds(5,0,1065,55);
		contentPane.add(panelArriba);
		panelArriba.setLayout(null);
		panelArriba.setBackground(bgCol);
		int tamanio = getBounds().width;
		String mensaje = generarMensajeSala(tamanio);
		JLabel sala = new JLabel(mensaje);
		sala.setFont(new Font("Tahoma", Font.PLAIN, 12));
		sala.setBounds(10, 10, 1059, 13);
		sala.setBackground(white);
		sala.setForeground(white);
		panelArriba.add(sala);

		pantalla = new JTextField();
		pantalla.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pantalla.setText("Pantalla");
		pantalla.setEnabled(false);
		pantalla.setBackground(azul);
		pantalla.setForeground(white);
		pantalla.setBorder(null);
		pantalla.setHorizontalAlignment(SwingConstants.CENTER);
		pantalla.setBounds(184, 25, 700, 30);
		panelArriba.add(pantalla);

	}
	void generarPanelInf() {
		JPanel panelInferior = new JPanel();
		panelInferior.setBounds(10, 455, 1065, 140);
		contentPane.add(panelInferior);
		panelInferior.setLayout(null);
		panelInferior.setBackground(bgCol);
		/*
		 * Las flechas son propias del teclado:
		 * ↑: Alt+24 para flecha arriba
		 * ↓: Alt+25 para flecha abajo
		 * →: Alt+26 para flecha derecha
		 * ←: Alt+27 para flecha izquierda
		 */

		bIzq = new JButton("←");
		bIzq.setFont(new Font("Tahoma", Font.PLAIN, 15));
		bIzq.setBackground(white);
		bIzq.setBackground(new Color(0,0,0));
		bIzq.setForeground(white);
		bIzq.setBounds(449, 65, 49, 49);
		panelInferior.add(bIzq);

		bAbaj = new JButton("↓");
		bAbaj.setFont(new Font("Tahoma", Font.PLAIN, 15));
		bAbaj.setBackground(new Color(0,0,0));
		bAbaj.setForeground(white);
		bAbaj.setBounds(512, 65, 49, 49);
		panelInferior.add(bAbaj);

		bArriba = new JButton("↑");
		bArriba.setFont(new Font("Tahoma", Font.PLAIN, 15));
		bArriba.setBackground(new Color(0,0,0));
		bArriba.setForeground(white);
		bArriba.setBounds(512, 10, 49, 49);
		panelInferior.add(bArriba);
		bArriba.requestFocus();

		bDere = new JButton("→");
		bDere.setFont(new Font("Tahoma", Font.PLAIN, 15));
		bDere.setBackground(new Color(0,0,0));
		bDere.setForeground(white);
		bDere.setBounds(573, 65, 49, 49);
		panelInferior.add(bDere);

		bAceptar = new JButton("Aceptar");
		bAceptar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		bAceptar.setBounds(905, 25, 130, 30);
		bAceptar.setBackground(new Color(0,0,0));
		bAceptar.setForeground(new Color(255,255,255));
		bAceptar.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
		//boton default
		getRootPane().setDefaultButton(bAceptar);
		panelInferior.add(bAceptar);

		bVaciar = new JButton("Vaciar Cine");
		bVaciar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		bVaciar.setBounds(905, 70, 130, 30);
		bVaciar.setBackground(new Color(0,0,0));
		bVaciar.setForeground(new Color(255,255,255));
		bVaciar.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
		panelInferior.add(bVaciar);

		//teclas
		bDere.addKeyListener(this);
		bIzq.addKeyListener(this);
		bArriba.addKeyListener(this);
		bAbaj.addKeyListener(this);

		//botones
		bDere.addActionListener(this);
		bIzq.addActionListener(this);
		bArriba.addActionListener(this);
		bAbaj.addActionListener(this);
		bVaciar.addActionListener(this);
		bAceptar.addActionListener(this);
	}
	void generarPanelMB(int cant) {
		panelMatriz = new JPanel();
		panelMatriz.setBounds(10,70,1065,380);
		panelMatriz.setLayout(new GridLayout(13,16,5,5));
		panelMatriz.setBackground(bgCol);
		contentPane.add(panelMatriz);
		// Genera la matriz de botones
		generarMatrizBut();
		buscarPuesto();
	}

	// Flechas
	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	public void keyPressed(KeyEvent e) {
		int tecla = e.getKeyCode();
		// Uso de keyListener
		if(tecla == KeyEvent.VK_UP) {
			mover(true, false, false, false);
		}
		if(tecla == KeyEvent.VK_DOWN) {
			mover(false, false, false, true);
		}
		if(tecla == KeyEvent.VK_LEFT) {
			mover(false, false, true, false);
		}
		if(tecla == KeyEvent.VK_RIGHT) {
			mover(false, true, false, false);
		}
	}
	//botones
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==bVaciar) {
			procesosCine.vaciarCine();
			setVisible(false);
			new VentanaCine(procesosCine.canti,aplicacion,cedus).setVisible(true);
		}
		if(e.getSource()==bAbaj) {
			mover(false, false, false, true);
		}
		if(e.getSource()==bArriba) {
			mover(true, false, false, false);
		}
		if(e.getSource()==bDere) {
			mover(false, true, false, false);
		}
		if(e.getSource()==bIzq) {
			mover(false, false, true, false);
		}
		if(e.getSource()==bAceptar) {
			String arrTxT[] = new String[cant];
			for(int i = 0;i<cant;i++) {
				arrTxT[i] = matrizBotones[posI][posJ+i].getText();
			}
			ConfirmarCine confirmarCine = new ConfirmarCine(arrTxT,aplicacion,cedus);
			confirmarCine.setVisible(true);
			setVisible(false);
		}
	}
	public String generarMensajeSala(int cant) {
		return procesosCine.generarMensajeSala(cant);
	}
	public void mover(boolean arriba,boolean dere,boolean izq,boolean abaj) {

		for(int j = posJ;j<(posJ+cant);j++) {
			matrizBotones[posI][j].setBackground(obtenerColor(posI,j));
		}
		int[] x;
		if(arriba) {
			x = procesosCine.moverArriba(posI,posJ);
		} else if (dere) {
			x = procesosCine.moverDere(posI,posJ);
		} else if (izq) {
			x = procesosCine.moverIzq(posI,posJ);
		} else {
			x = procesosCine.moverAbajo(posI,posJ);
		}


		posI = x[0];
		posJ = x[1];
		for(int j = posJ;j<(posJ+cant);j++) {
			matrizBotones[posI][j].setBackground(new Color(0,255,0));
		}
	}
	public Color obtenerColor(int i, int j) {
		return procesosCine.obtenerColor(i,j);
	}
	public void buscarPuesto() {
		Object[] x = procesosCine.buscarPuesto(posI,posJ);
		boolean encuentra = (boolean) x[0];
		posI = (int) x[1];
		posJ = (int) x[2];
		if(encuentra) {
			for(int j = posJ;j<cant+posJ;j++) {
				matrizBotones[posI][j].setBackground(new Color(0, 255, 0));
			}
		} else {
			JOptionPane.showMessageDialog(null, "No hay celdas disponibles");
			aplicacion.setVisible(true);
			setVisible(false);
		}
	}
	public void generarMatrizBut() {
		matrizBotones = new JButton[13][16];
		for(char i = 'A';i<='M';i++) {
			for(int j = 0;j<16;j++) {
				int num = i-'A';
				matrizBotones[num][j] = new JButton();
				matrizBotones[num][j].setText(i+""+(j+1));
				matrizBotones[num][j].setEnabled(false);
				matrizBotones[num][j].setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
				matrizBotones[num][j].setForeground(white);
				matrizBotones[num][j].setBackground(obtenerColor(num,j));
				panelMatriz.add(matrizBotones[num][j]);
			}
		}
	}
}