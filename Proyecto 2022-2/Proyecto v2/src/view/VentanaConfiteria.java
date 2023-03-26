package view;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Color;

public class VentanaConfiteria extends JFrame implements ActionListener,KeyListener {

	private JPanel panel;
	private JButton btnCrispPeq, btnCrispMed, btnCrispGra, btnGaseosaPeq, btnTe, btnAgua, btnGaseosaMed,
	btnGaseosaGran, btnCrispExtra, btnCrispFam, btnPerro, btnSandwich, btnDulces, btnCombo1, btnCombo2,
	btnCombo3, btnCombo4, btnAtras, btnCarrito,btnContinuar;
	public Color colN = new Color (0,0,0);
	private Aplicacion aplicacion;
	private ArrayList<Object> infoPersona;
	public static ImageIcon carrito = new ImageIcon("Imagenes/carritoCompras.png");

	public static ArrayList<String> nomComida;
	public static ArrayList<Integer> cantidadComida;
	public static ArrayList <String> listaComida;


	private String listComida="";
	private JButton btnEliminarUltimoElemento;


	public VentanaConfiteria(Aplicacion aplicacion,ArrayList<Object> infoPersona) {
		nomComida = new ArrayList<String>();
		cantidadComida = new ArrayList<Integer>();
		listaComida = new ArrayList <String>();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200, 650);
		this.infoPersona = infoPersona;
		this.aplicacion = aplicacion;

		ImageIcon crispIcon = new ImageIcon("Imagenes/crispetast.png");
		setIconImage(crispIcon.getImage());
		setTitle("Stellar Cinema - Facturar Confitería | "+(String) infoPersona.get(0)+" - "+(String) infoPersona.get(1));
		setLocationRelativeTo(null);


		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		setResizable(false);

		JLabel lblTitulo = new JLabel("Presione un botón para agregar ");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(10, 11, 1166, 34);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 28));
		panel.add(lblTitulo);

		JLabel lblCrispeta = new JLabel("Crispetas");
		lblCrispeta.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCrispeta.setHorizontalAlignment(SwingConstants.CENTER);
		lblCrispeta.setBounds(10, 98, 220, 20);
		panel.add(lblCrispeta);


		JLabel lblBebidas = new JLabel("Bebidas");
		lblBebidas.setHorizontalAlignment(SwingConstants.CENTER);
		lblBebidas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBebidas.setBounds(240, 98, 188, 21);
		panel.add(lblBebidas);

		JLabel lblOtrasComidas = new JLabel("Otras comidas");
		lblOtrasComidas.setHorizontalAlignment(SwingConstants.CENTER);
		lblOtrasComidas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblOtrasComidas.setBounds(438, 98, 188, 21);
		panel.add(lblOtrasComidas);

		JLabel lblCombos = new JLabel("Combos");
		lblCombos.setHorizontalAlignment(SwingConstants.CENTER);
		lblCombos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCombos.setBounds(831, 98, 188, 21);
		panel.add(lblCombos);

		btnContinuar = new JButton("Continuar");
		btnContinuar.addActionListener(this);
		getRootPane().setDefaultButton(btnContinuar);
		btnContinuar.setBounds(975, 502, 201, 73);
		btnContinuar.setBackground(new Color(255,255,255));
		btnContinuar.setFont(new Font("Tahoma", Font.PLAIN, 28));
		panel.add(btnContinuar);

		btnCrispPeq = new JButton("Crispetas Pequeñas: $7500");
		btnCrispPeq.setForeground(new Color(255, 255, 255));
		btnCrispPeq.setBackground(new Color(0, 255, 0));
		btnCrispPeq.addActionListener(this);
		getRootPane().setDefaultButton(btnCrispPeq);
		btnCrispPeq.addKeyListener(this);
		btnCrispPeq.setBounds(10, 129, 220, 58);
		panel.add(btnCrispPeq);

		btnCrispMed = new JButton("Crispetas mediana: $10000");
		btnCrispMed.setBackground(new Color(0, 0, 0));
		btnCrispMed.setForeground(new Color(255, 255, 255));
		btnCrispMed.addActionListener(this);
		btnCrispMed.addKeyListener(this);
		btnCrispMed.setBounds(10, 227, 220, 58);
		panel.add(btnCrispMed);

		btnCrispGra = new JButton("Crispetas grandes: $11000");
		btnCrispGra.setForeground(new Color(255, 255, 255));
		btnCrispGra.setBackground(new Color(0, 0, 0));
		btnCrispGra.addActionListener(this);
		btnCrispGra.addKeyListener(this);
		btnCrispGra.setBounds(10, 327, 220, 58);
		panel.add(btnCrispGra);

		btnCrispExtra = new JButton("Crispetas extragrandes: $13000");
		btnCrispExtra.setBackground(new Color(0, 0, 0));
		btnCrispExtra.setForeground(new Color(255, 255, 255));
		btnCrispExtra.addActionListener(this);
		btnCrispExtra.addKeyListener(this);
		btnCrispExtra.setBounds(10, 421, 220, 58);
		panel.add(btnCrispExtra);

		btnCrispFam = new JButton("Crispetas familiar: $14500");
		btnCrispFam.setForeground(new Color(255, 255, 255));
		btnCrispFam.setBackground(new Color(0, 0, 0));
		btnCrispFam.addActionListener(this);
		btnCrispFam.addKeyListener(this);
		btnCrispFam.setBounds(10, 516, 220, 58);
		panel.add(btnCrispFam);

		btnGaseosaPeq = new JButton("Gaseosa pequeña: $2500");
		btnGaseosaPeq.setBackground(new Color(0, 0, 0));
		btnGaseosaPeq.setForeground(new Color(255, 255, 255));
		btnGaseosaPeq.addActionListener(this);
		btnGaseosaPeq.addKeyListener(this);
		btnGaseosaPeq.setBounds(240, 129, 188, 58);
		panel.add(btnGaseosaPeq);

		btnGaseosaMed = new JButton("Gaseosa Mediana: $4000");
		btnGaseosaMed.setBackground(new Color(0, 0, 0));
		btnGaseosaMed.setForeground(new Color(255, 255, 255));
		btnGaseosaMed.addActionListener(this);
		btnGaseosaMed.addKeyListener(this);
		btnGaseosaMed.setBounds(240, 227, 188, 58);
		panel.add(btnGaseosaMed);

		btnGaseosaGran = new JButton("Gaseosa Grande: $5000");
		btnGaseosaGran.setForeground(new Color(255, 255, 255));
		btnGaseosaGran.setBackground(new Color(0, 0, 0));
		btnGaseosaGran.addActionListener(this);
		btnGaseosaGran.addKeyListener(this);
		btnGaseosaGran.setBounds(240, 327, 188, 58);
		panel.add(btnGaseosaGran);

		btnTe = new JButton("Té: $3500");
		btnTe.setBackground(new Color(0, 0, 0));
		btnTe.setForeground(new Color(255, 255, 255));
		btnTe.addActionListener(this);
		btnTe.addKeyListener(this);
		btnTe.setBounds(240, 421, 188, 58);
		panel.add(btnTe);

		btnAgua = new JButton("Agua: $2000");
		btnAgua.setForeground(new Color(255, 255, 255));
		btnAgua.setBackground(new Color(0, 0, 0));
		btnAgua.addActionListener(this);
		btnAgua.addKeyListener(this);
		btnAgua.setBounds(240, 516, 188, 58);
		panel.add(btnAgua);

		btnPerro = new JButton("Perro: $5000");
		btnPerro.setBackground(new Color(0, 0, 0));
		btnPerro.setForeground(new Color(255, 255, 255));
		btnPerro.addActionListener(this);
		btnPerro.addKeyListener(this);
		btnPerro.setBounds(438, 129, 188, 58);
		panel.add(btnPerro);

		btnSandwich = new JButton("Sandwich: $3500");
		btnSandwich.setForeground(new Color(255, 255, 255));
		btnSandwich.setBackground(new Color(0, 0, 0));
		btnSandwich.addActionListener(this);
		btnSandwich.addKeyListener(this);
		btnSandwich.setBounds(438, 227, 188, 58);
		panel.add(btnSandwich);

		btnDulces = new JButton("Caja de dulces: $2000");
		btnDulces.setForeground(new Color(255, 255, 255));
		btnDulces.setBackground(new Color(0, 0, 0));
		btnDulces.addActionListener(this);
		btnDulces.addKeyListener(this);
		btnDulces.setBounds(438, 327, 188, 58);
		panel.add(btnDulces);

		btnCombo1 = new JButton(strToHtml("Combo 1: Crispetas pequeñas, perro, gaseosa pequeña: $12000"));
		btnCombo1.setForeground(new Color(255, 255, 255));
		btnCombo1.setBackground(new Color(0, 0, 0));
		btnCombo1.addActionListener(this);
		btnCombo1.addKeyListener(this);
		btnCombo1.setBounds(682, 129, 231, 99);
		panel.add(btnCombo1);

		btnCombo2 = new JButton(strToHtml("Combo 2:  Crispetas medianas, 2 gaseosas pequeñas, 2 perros: $20000"));
		btnCombo2.setForeground(new Color(255, 255, 255));
		btnCombo2.setBackground(new Color(0, 0, 0));
		btnCombo2.addActionListener(this);
		btnCombo2.addKeyListener(this);
		btnCombo2.setBounds(945, 129, 231, 99);
		panel.add(btnCombo2);

		btnCombo3 = new JButton(strToHtml("Combo 3: Crispeta familiar, 3 gaseosas grandes, 3 perros: $30000"));
		btnCombo3.setForeground(new Color(255, 255, 255));
		btnCombo3.setBackground(new Color(0, 0, 0));
		btnCombo3.addActionListener(this);
		btnCombo3.addKeyListener(this);
		btnCombo3.setBounds(682, 286, 231, 99);
		panel.add(btnCombo3);

		btnCombo4 = new JButton(strToHtml("Combo 4: Crispetas pequeñas, gaseosa pequeña, sandwich, caja de dulces: $13000"));
		btnCombo4.setForeground(new Color(255, 255, 255));
		btnCombo4.setBackground(new Color(0, 0, 0));
		btnCombo4.addActionListener(this);
		btnCombo4.addKeyListener(this);
		btnCombo4.setBounds(945, 286, 231, 99);
		panel.add(btnCombo4);

		btnCarrito = new JButton();
		btnCarrito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(listaComida.size()>0) {
					listComida=mostrarArrayList(listaComida);
					JOptionPane.showMessageDialog(null, listComida);
				}else {
					JOptionPane.showMessageDialog(null, "No se ha añadido nada al carrito");
				}
			}
		});
		btnCarrito.setBackground(new Color(255,255,255));
		btnCarrito.addKeyListener(this);
		btnCarrito.setBounds(494, 432, 220, 143);
		btnCarrito.setIcon(new ImageIcon(carrito.getImage().getScaledInstance(btnCarrito.getWidth(), btnCarrito.getHeight(), Image.SCALE_SMOOTH)));
		panel.add(btnCarrito);

		btnAtras = new JButton("Ir Atras");
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 28));
		btnAtras.addActionListener(this);
		btnAtras.setBackground(new Color(255,255,255));
		btnAtras.setBounds(756, 502, 201, 73);
		panel.add(btnAtras);

		btnEliminarUltimoElemento = new JButton("Eliminar Elemento");
		btnEliminarUltimoElemento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				nomComida.remove(nomComida.size()-1);
				cantidadComida.remove(cantidadComida.size()-1);
				listaComida.remove(listaComida.size()-1);
				}
				catch (Exception x) {
					JOptionPane.showMessageDialog(null, "No se ha añadido nada al carrito","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnEliminarUltimoElemento.setBackground(new Color(255,255,255));
		btnEliminarUltimoElemento.setBounds(494, 579, 142, 23);
		panel.add(btnEliminarUltimoElemento);

		JLabel lblFondo = new JLabel();
		lblFondo.setBounds(0, 0, 1186, 623);
		ImageIcon fondo= new ImageIcon("Imagenes/Crispetas.png");
		lblFondo.setIcon(new ImageIcon(fondo.getImage().getScaledInstance(lblFondo.getWidth(), lblFondo.getHeight(), Image.SCALE_SMOOTH)));
		panel.add(lblFondo);

	}

	public String strToHtml(String texto) {
		return "<html><p>"+ texto +"</p></html> ";
	}

	public static String mostrarArrayList(ArrayList <String> lista) {
		String respuesta="";
		for(int i = 0; i<lista.size(); i++) {
			if(i<lista.size()-1) {
				respuesta+=lista.get(i)+"\n";
			}
			else {
				respuesta+=lista.get(i);
			}
		}
		return respuesta;
	}

	public void actionPerformed(ActionEvent e) {
		String x;
		if (e.getSource()==btnCrispPeq) {
			x = "Crispetas pequeñas, ";
			VentanaCantidad ventCant = new VentanaCantidad(this,x);
			ventCant.setVisible(true);
			setVisible(false);
		}
		if (e.getSource()==btnCrispMed) {
			x = "Crispetas medianas, ";
			VentanaCantidad ventCant = new VentanaCantidad(this,x);
			ventCant.setVisible(true);
			setVisible(false);
		}
		if (e.getSource()==btnCrispGra) {
			x ="Crispetas grandes, ";
			VentanaCantidad ventCant = new VentanaCantidad(this,x);
			ventCant.setVisible(true);
			setVisible(false);
		}
		if (e.getSource()==btnCrispExtra) {
			x = "Crispetas extragrandes, ";
			VentanaCantidad ventCant = new VentanaCantidad(this,x);
			ventCant.setVisible(true);
			setVisible(false);
		}
		if (e.getSource()==btnCrispFam) {
			x = "Crispetas familiar, ";
			VentanaCantidad ventCant = new VentanaCantidad(this,x);
			ventCant.setVisible(true);
			setVisible(false);
		}
		if (e.getSource()==btnGaseosaPeq) {
			x = "Gaseosa pequeña, ";
			VentanaCantidad ventCant = new VentanaCantidad(this,x);
			ventCant.setVisible(true);
			setVisible(false);
		}
		if (e.getSource()==btnGaseosaMed) {
			x = "Gaseosa mediana, ";
			VentanaCantidad ventCant = new VentanaCantidad(this,x);
			ventCant.setVisible(true);
			setVisible(false);
		}
		if (e.getSource()==btnGaseosaGran) {
			x = "Gaseosa grande, ";
			VentanaCantidad ventCant = new VentanaCantidad(this,x);
			ventCant.setVisible(true);
			setVisible(false);
		}
		if (e.getSource()==btnTe) {
			x = "Té, ";
			VentanaCantidad ventCant = new VentanaCantidad(this,x);
			ventCant.setVisible(true);
			setVisible(false);
		}
		if (e.getSource()==btnAgua) {
			x = "Agua, ";
			VentanaCantidad ventCant = new VentanaCantidad(this,x);
			ventCant.setVisible(true);
			setVisible(false);
		}
		if (e.getSource()==btnPerro) {
			x = "Perro, ";
			VentanaCantidad ventCant = new VentanaCantidad(this,x);
			ventCant.setVisible(true);
			setVisible(false);
		}
		if (e.getSource()==btnSandwich) {
			x = "Sándwich, ";
			VentanaCantidad ventCant = new VentanaCantidad(this,x);
			ventCant.setVisible(true);
			setVisible(false);
		}
		if (e.getSource()==btnDulces) {
			x = "Caja de dulces, ";
			VentanaCantidad ventCant = new VentanaCantidad(this,x);
			ventCant.setVisible(true);
			setVisible(false);
		}
		if (e.getSource()==btnCombo1) {
			x = "Combo 1, ";
			VentanaCantidad ventCant = new VentanaCantidad(this,x);
			ventCant.setVisible(true);
			setVisible(false);
		}
		if (e.getSource()==btnCombo2) {
			x = "Combo 2, ";
			VentanaCantidad ventCant = new VentanaCantidad(this,x);
			ventCant.setVisible(true);
			setVisible(false);
		}
		if (e.getSource()==btnCombo3) {
			x = "Combo 3, ";
			VentanaCantidad ventCant = new VentanaCantidad(this,x);
			ventCant.setVisible(true);
			setVisible(false);
		}
		if (e.getSource()==btnCombo4) {
			x = "Combo 4, ";
			VentanaCantidad ventCant = new VentanaCantidad(this,x);
			ventCant.setVisible(true);
			setVisible(false);
		}
		if (e.getSource()==btnCarrito) {
			if(listaComida.size()>0) {
				listComida=mostrarArrayList(listaComida);
				JOptionPane.showMessageDialog(null, listComida);
			}else {
				JOptionPane.showMessageDialog(null, "No se ha añadido nada al carrito");
			}
		}
		if(e.getSource()==btnContinuar) {
			//VentanaPagarConfi ventPagar= new VentanaPagarConfi(aplicacion,this,infoPersona,nomComida,cantidadComida,listaComida);
			VentanaPagarConfi ventPagar= new VentanaPagarConfi(aplicacion,this,infoPersona);
			ventPagar.setVisible(true);
			setVisible(false);
		}
		if(e.getSource()==btnAtras) {
			aplicacion.setVisible(true);
			setVisible(false);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	public void keyPressed(KeyEvent e) {
		int x = e.getKeyCode();
		Color colS = new Color (0,255,0);

		;
		/* Cambia el boton default (para cuando se da enter),
		 * El boton anterior pasa a blanco y el otro a verde
		 * (se selecciona)
		 */
		getRootPane().getDefaultButton().setBackground(colN);
		if(getRootPane().getDefaultButton().equals(btnCarrito)||getRootPane().getDefaultButton().equals(btnEliminarUltimoElemento)||getRootPane().getDefaultButton().equals(btnAtras)||getRootPane().getDefaultButton().equals(btnContinuar)) {
			getRootPane().getDefaultButton().setBackground(new Color(255,255,255));
		}
		if(x == KeyEvent.VK_RIGHT) {
			if(getRootPane().getDefaultButton().equals(btnCrispPeq)) {
				getRootPane().setDefaultButton(btnGaseosaPeq);
			} else if (getRootPane().getDefaultButton().equals(btnCrispMed)) {
				getRootPane().setDefaultButton(btnGaseosaMed);
			} else if (getRootPane().getDefaultButton().equals(btnCrispGra)) {
				getRootPane().setDefaultButton(btnGaseosaGran);
			} else if (getRootPane().getDefaultButton().equals(btnCrispExtra)) {
				getRootPane().setDefaultButton(btnTe);
			} else if (getRootPane().getDefaultButton().equals(btnCrispFam)) {
				getRootPane().setDefaultButton(btnAgua);
			} else if (getRootPane().getDefaultButton().equals(btnGaseosaPeq)) {
				getRootPane().setDefaultButton(btnPerro);
			} else if (getRootPane().getDefaultButton().equals(btnGaseosaMed)) {
				getRootPane().setDefaultButton(btnSandwich);
			} else if (getRootPane().getDefaultButton().equals(btnGaseosaGran)) {
				getRootPane().setDefaultButton(btnDulces);
			} else if (getRootPane().getDefaultButton().equals(btnTe)) {
				getRootPane().setDefaultButton(btnCarrito);
			} else if (getRootPane().getDefaultButton().equals(btnAgua)) {
				getRootPane().setDefaultButton(btnCarrito);
			} else if (getRootPane().getDefaultButton().equals(btnPerro)) {
				getRootPane().setDefaultButton(btnCombo1);
			} else if (getRootPane().getDefaultButton().equals(btnSandwich)) {
				getRootPane().setDefaultButton(btnCombo1);
			} else if (getRootPane().getDefaultButton().equals(btnDulces)) {
				getRootPane().setDefaultButton(btnCombo3);
			} else if (getRootPane().getDefaultButton().equals(btnCombo1)) {
				getRootPane().setDefaultButton(btnCombo2);
			} else if (getRootPane().getDefaultButton().equals(btnCombo3)) {
				getRootPane().setDefaultButton(btnCombo4);
			} else if (getRootPane().getDefaultButton().equals(btnCarrito)) {
				getRootPane().setDefaultButton(btnAtras);
			} else if (getRootPane().getDefaultButton().equals(btnAtras)) {
				getRootPane().setDefaultButton(btnContinuar);
			} else if (getRootPane().getDefaultButton().equals(btnEliminarUltimoElemento)) {
				getRootPane().setDefaultButton(btnAtras);
			}
		}
		if(x == KeyEvent.VK_DOWN) {
			if(getRootPane().getDefaultButton().equals(btnCrispPeq)) {
				getRootPane().setDefaultButton(btnCrispMed);
			} else if (getRootPane().getDefaultButton().equals(btnCrispMed)) {
				getRootPane().setDefaultButton(btnCrispGra);
			} else if (getRootPane().getDefaultButton().equals(btnCrispGra)) {
				getRootPane().setDefaultButton(btnCrispExtra);
			} else if (getRootPane().getDefaultButton().equals(btnCrispExtra)) {
				getRootPane().setDefaultButton(btnCrispFam);
			} else if (getRootPane().getDefaultButton().equals(btnGaseosaPeq)) {
				getRootPane().setDefaultButton(btnGaseosaMed);
			} else if (getRootPane().getDefaultButton().equals(btnGaseosaMed)) {
				getRootPane().setDefaultButton(btnGaseosaGran);
			} else if (getRootPane().getDefaultButton().equals(btnGaseosaGran)) {
				getRootPane().setDefaultButton(btnTe);
			} else if (getRootPane().getDefaultButton().equals(btnTe)) {
				getRootPane().setDefaultButton(btnAgua);
			} else if (getRootPane().getDefaultButton().equals(btnPerro)) {
				getRootPane().setDefaultButton(btnSandwich);
			} else if (getRootPane().getDefaultButton().equals(btnSandwich)) {
				getRootPane().setDefaultButton(btnDulces);
			} else if (getRootPane().getDefaultButton().equals(btnDulces)) {
				getRootPane().setDefaultButton(btnCarrito);
			} else if (getRootPane().getDefaultButton().equals(btnCarrito)) {
				getRootPane().setDefaultButton(btnEliminarUltimoElemento);
			} else if (getRootPane().getDefaultButton().equals(btnCombo1)) {
				getRootPane().setDefaultButton(btnCombo3);
			} else if (getRootPane().getDefaultButton().equals(btnCombo3)) {
				getRootPane().setDefaultButton(btnAtras);
			} else if (getRootPane().getDefaultButton().equals(btnCombo2)) {
				getRootPane().setDefaultButton(btnCombo4);
			} else if (getRootPane().getDefaultButton().equals(btnCombo4)) {
				getRootPane().setDefaultButton(btnContinuar);
			}
		}
		if(x == KeyEvent.VK_UP) {
			if (getRootPane().getDefaultButton().equals(btnCrispMed)) {
				getRootPane().setDefaultButton(btnCrispPeq);
			} else if (getRootPane().getDefaultButton().equals(btnCrispGra)) {
				getRootPane().setDefaultButton(btnCrispMed);
			} else if (getRootPane().getDefaultButton().equals(btnCrispExtra)) {
				getRootPane().setDefaultButton(btnCrispGra);
			} else if (getRootPane().getDefaultButton().equals(btnCrispFam)) {
				getRootPane().setDefaultButton(btnCrispExtra);
			} else if (getRootPane().getDefaultButton().equals(btnGaseosaMed)) {
				getRootPane().setDefaultButton(btnGaseosaPeq);
			} else if (getRootPane().getDefaultButton().equals(btnGaseosaGran)) {
				getRootPane().setDefaultButton(btnGaseosaMed);
			} else if (getRootPane().getDefaultButton().equals(btnTe)) {
				getRootPane().setDefaultButton(btnGaseosaGran);
			} else if (getRootPane().getDefaultButton().equals(btnAgua)) {
				getRootPane().setDefaultButton(btnTe);
			} else if (getRootPane().getDefaultButton().equals(btnSandwich)) {
				getRootPane().setDefaultButton(btnPerro);
			} else if (getRootPane().getDefaultButton().equals(btnDulces)) {
				getRootPane().setDefaultButton(btnSandwich);
			} else if (getRootPane().getDefaultButton().equals(btnCarrito)) {
				getRootPane().setDefaultButton(btnDulces);
			} else if (getRootPane().getDefaultButton().equals(btnEliminarUltimoElemento)) {
				getRootPane().setDefaultButton(btnCarrito);
			} else if (getRootPane().getDefaultButton().equals(btnCombo3)) {
				getRootPane().setDefaultButton(btnCombo1);
			} else if (getRootPane().getDefaultButton().equals(btnCombo4)) {
				getRootPane().setDefaultButton(btnCombo2);
			} else if (getRootPane().getDefaultButton().equals(btnContinuar)) {
				getRootPane().setDefaultButton(btnCombo4);
			} else if (getRootPane().getDefaultButton().equals(btnAtras)) {
				getRootPane().setDefaultButton(btnCombo3);
			}
		}
		if(x == KeyEvent.VK_LEFT) {
			if (getRootPane().getDefaultButton().equals(btnGaseosaPeq)) {
				getRootPane().setDefaultButton(btnCrispPeq);
			} else if (getRootPane().getDefaultButton().equals(btnGaseosaMed)) {
				getRootPane().setDefaultButton(btnCrispMed);
			} else if (getRootPane().getDefaultButton().equals(btnGaseosaGran)) {
				getRootPane().setDefaultButton(btnCrispGra);
			} else if (getRootPane().getDefaultButton().equals(btnTe)) {
				getRootPane().setDefaultButton(btnCrispExtra);
			} else if (getRootPane().getDefaultButton().equals(btnAgua)) {
				getRootPane().setDefaultButton(btnCrispFam);
			} else if (getRootPane().getDefaultButton().equals(btnPerro)) {
				getRootPane().setDefaultButton(btnGaseosaPeq);
			} else if (getRootPane().getDefaultButton().equals(btnSandwich)) {
				getRootPane().setDefaultButton(btnGaseosaMed);
			} else if (getRootPane().getDefaultButton().equals(btnDulces)) {
				getRootPane().setDefaultButton(btnGaseosaGran);
			} else if (getRootPane().getDefaultButton().equals(btnCarrito)) {
				getRootPane().setDefaultButton(btnTe);
			} else if (getRootPane().getDefaultButton().equals(btnEliminarUltimoElemento)) {
				getRootPane().setDefaultButton(btnAgua);
			} else if (getRootPane().getDefaultButton().equals(btnCombo1)) {
				getRootPane().setDefaultButton(btnPerro);
			} else if (getRootPane().getDefaultButton().equals(btnCombo3)) {
				getRootPane().setDefaultButton(btnDulces);
			} else if (getRootPane().getDefaultButton().equals(btnCombo4)) {
				getRootPane().setDefaultButton(btnCombo3);
			} else if (getRootPane().getDefaultButton().equals(btnCombo2)) {
				getRootPane().setDefaultButton(btnCombo1);
			} else if (getRootPane().getDefaultButton().equals(btnContinuar)) {
				getRootPane().setDefaultButton(btnAtras);
			} else if (getRootPane().getDefaultButton().equals(btnAtras)) {
				getRootPane().setDefaultButton(btnCarrito);
			}
		}
		getRootPane().getDefaultButton().setBackground(colS);
	}
}