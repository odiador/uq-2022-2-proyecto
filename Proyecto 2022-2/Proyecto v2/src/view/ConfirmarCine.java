package view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import model.ProcesosCompra;
import model.data;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.JComboBox;

public class ConfirmarCine extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton mensajes[][];
	private JButton bCerrar;
	private JButton bAceptar;
	private data data = new data();
	private boolean[][] m = data.cine;
	private int canti = m.length,cantj = m[0].length;
	boolean mRes[][] = new boolean[canti][cantj];
	private Aplicacion aplicacion;
	private double price;
	private ArrayList<Object> infoPersona;
	private String[] sillas;
	private int cant;
	private ProcesosCompra procesosCompra;
	private String auxiliar;
	private JComboBox comboBox;

	public ConfirmarCine(String[] txt,Aplicacion aplicacion,ArrayList<Object> cedus) {

		cant = txt.length;
		sillas = txt;
		infoPersona = cedus;
		this.aplicacion = aplicacion;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		procesosCompra = new ProcesosCompra(txt,cedus);
		// Obtiene el precio
		price = calcPrecio(false,false);
		// Mensaje del precio
		String mensaje = generarMensajeHtml(price,false,false);
		//titulo: nombre - cedula
		setTitle((String) infoPersona.get(0)+" - "+(String) infoPersona.get(1));
		setSize(906, 526);
		setLocationRelativeTo(null);
		setResizable(false);
		setBackground(new Color(0,0,51));

		ImageIcon crispIcon = new ImageIcon("Imagenes/crispetast.png");
		setIconImage(crispIcon.getImage());

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 857, 276);
		panel.setBackground(new Color(0, 0, 0, 0));

		contentPane.add(panel);

		ArrayList<String> listaPagos = new ArrayList<String>();

		listaPagos.add("Escoja el método de pago...");
		listaPagos.add("Efectivo");
		listaPagos.add("Tarjeta de crédito");
		listaPagos.add("PSE");
		if((boolean) cedus.get(5)) {
			listaPagos.add("Tarjeta Basic");
		}
		if((boolean) cedus.get(4)) {
			listaPagos.add("Tarjeta Gold");
		}
		String[] formaPago = new String[listaPagos.size()];
		for(int i = 0;i<formaPago.length;i++) {
			formaPago[i] = listaPagos.get(i);
		}

		int canti = m.length;
		int cantj = m[0].length;
		panel.setLayout(new GridLayout(canti, cantj, 2, 2));
		mensajes = new JButton[canti][cantj];
		//muestra los puestos para confirmar
		for(int i = 0;i<canti;i++) {
			for(int j = 0;j<cantj;j++) {
				mensajes[i][j] = new JButton("");
				mRes[i][j] = m[i][j];
				if(m[i][j]) {
					mensajes[i][j].setBackground(Color.gray);
				} else {
					mensajes[i][j].setBackground(data.azul);
					String pos = (char) ('A'+i)+""+(j+1);
					for(int x = 0;x<cant;x++) {
						if(pos.equals(sillas[x])) {
							mRes[i][j] = true;
							mensajes[i][j].setBackground(Color.green);
						}
					}
				}
				mensajes[i][j].setBorder(new EmptyBorder(5, 5, 5, 5));
				mensajes[i][j].setEnabled(false);
				mensajes[i][j].setFont(new Font("Tahoma", Font.PLAIN, 14));
				mensajes[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				panel.add(mensajes[i][j]);

			}
		}
		bAceptar = new JButton("Confirmar");
		bAceptar.setBackground(new Color(255, 255, 255));
		bAceptar.addActionListener(this);
		bAceptar.setFont(new Font("Tahoma", Font.PLAIN, 25));
		bAceptar.setBounds(692, 369, 175, 50);
		bAceptar.setBorder(new EmptyBorder(0, 0, 0, 0));
		getRootPane().setDefaultButton(bAceptar);
		contentPane.add(bAceptar);

		bCerrar = new JButton("Rechazar");
		bCerrar.setBackground(new Color(255, 255, 255));
		bCerrar.addActionListener(this);
		bCerrar.setFont(new Font("Tahoma", Font.PLAIN, 25));
		bCerrar.setBounds(692, 429, 175, 50);
		bCerrar.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.add(bCerrar);
		ImageIcon fCine = new ImageIcon("Imagenes/fondoPrincipal.png");

		comboBox = new JComboBox(formaPago);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboBox.setBounds(554, 313, 313, 37);
		contentPane.add(comboBox);

		JTextArea txtMensaje = new JTextArea();
		txtMensaje.setText(mensaje);
		txtMensaje.setFont(new Font("Tahoma", Font.PLAIN, 27));
		txtMensaje.setForeground(Color.BLACK);
		txtMensaje.setColumns(10);
		txtMensaje.setEditable(false);

		JPanel panelConfirmarCompra = new JPanel();
		panelConfirmarCompra.setBounds(10, 296, 534, 183);
		panelConfirmarCompra.setLayout(new BorderLayout(0,0));
		contentPane.add(panelConfirmarCompra);

		JScrollPane scr = new JScrollPane();
		scr.setViewportView(txtMensaje);
		panelConfirmarCompra.add(scr,BorderLayout.CENTER);

		JButton btnActua = new JButton("Actualizar Precio");
		btnActua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				auxiliar=(String)comboBox.getSelectedItem();
				if(!auxiliar.equals("Escoja el método de pago...")) {
					price = calcPrecio(false,false);
					String mensaje = generarMensajeHtml(price,false,false);
					if(auxiliar.equals("Tarjeta Basic")) {
						price = calcPrecio(true,false);
						mensaje = generarMensajeHtml(price,true,false);
					}
					else if(auxiliar.equals("Tarjeta Gold")) {
						price = calcPrecio(false,true);
						mensaje = generarMensajeHtml(price,false,true);
					}
					txtMensaje.setText(mensaje);
				} else {
					JOptionPane.showMessageDialog(ConfirmarCine.this, "Escribe un método de pago");
				}
			}
		});
		btnActua.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnActua.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnActua.setBackground(Color.WHITE);
		btnActua.setBounds(564, 369, 118, 50);
		contentPane.add(btnActua);

		JLabel fondo = new JLabel();
		fondo.setBounds(0, 0, 892, 489);
		fondo.setIcon(new ImageIcon(fCine.getImage().getScaledInstance(fondo.getWidth(), fondo.getHeight(), Image.SCALE_SMOOTH)));
		contentPane.add(fondo);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==bAceptar) {
			auxiliar=(String)comboBox.getSelectedItem();
			if(!auxiliar.equals("Escoja el método de pago...")) {
				if(auxiliar.equals("Tarjeta Basic")) {
					price = calcPrecio(true,false);
				}
				else if(auxiliar.equals("Tarjeta Gold")) {
					price = calcPrecio(false,true);
				} else {
					price = calcPrecio(false,false);
				}
				procesarCompraBoletas(sillas);
				aplicacion.setVisible(true);
				setVisible(false);
			} else {
				JOptionPane.showMessageDialog(this, "Escribe un método de pago");
			}

		}
		if(e.getSource()==bCerrar) {
			aplicacion.setVisible(true);
			setVisible(false);
		}
	}
	public double calcPrecio(boolean tieneBasic,boolean tieneG) {
		return procesosCompra.calcPrecio(tieneBasic,tieneG);
	}
	public String generarMensajeHtml(double price,boolean tieneB,boolean tieneG) {
		return procesosCompra.generarMensajeHtml(price,tieneB,tieneG);
	}
	public void procesarCompraBoletas(String txt[]) {
		procesosCompra.procesarCompraBoletas(mRes, price,auxiliar,cant,sillas);

	}
}