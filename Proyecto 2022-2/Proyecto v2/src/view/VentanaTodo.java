package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import model.data;

import javax.swing.JTextArea;
import java.awt.BorderLayout;

public class VentanaTodo extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton mensajes[][];
	private data data;
	private boolean[][] m;
	private Aplicacion aplicacion;
	private ArrayList<Object>[][] lugaresCine;
	private JButton bVolver;
	private ArrayList<ArrayList<Object>> listaConfiteria;
	private int canti,cantj;

	public VentanaTodo(Aplicacion aplicacion) {

		data = new data();
		this.aplicacion = aplicacion;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900, 660);
		setLocationRelativeTo(null);
		setBackground(new Color(0,0,51));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(0,0,0));
		setContentPane(contentPane);
		contentPane.setLayout(null);


		ImageIcon crispIcon = new ImageIcon("Imagenes/crispetast.png");
		setIconImage(crispIcon.getImage());
		setTitle("Stellar Cinema - Base de Datos");

		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 765, 303);
		panel.setBackground(new Color(0,0,0,0));

		contentPane.add(panel);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 323, 866, 127);
		panel_1.setBackground(new Color(0,0,0,0));
		contentPane.add(panel_1);


		JPanel confit = new JPanel();
		confit.setBounds(10, 470, 866, 127);
		confit.setBackground(new Color(0,0,0,0));
		contentPane.add(confit);

		m = data.cine;
		lugaresCine = data.lugaresCine;
		listaConfiteria = data.lista;
		canti = m.length;
		cantj = m[0].length;
		panel.setLayout(new GridLayout(canti, cantj, 2, 2));
		//panel.setLayout(new GridLayout(1, 0, 0, 0));

		mensajes = new JButton[canti][cantj];
		for(int i = 0;i<canti;i++) {
			for(int j = 0;j<cantj;j++) {
				mensajes[i][j] = new JButton("");
				if(m[i][j]) {
					mensajes[i][j].setBackground(Color.gray);
				} else {
					mensajes[i][j].setBackground(data.azul);
				}
				mensajes[i][j].setBorder(new EmptyBorder(5, 5, 5, 5));
				if(lugaresCine[i][j].size()==0) {
					mensajes[i][j].setEnabled(false);
				} else {
					mensajes[i][j].setEnabled(true);
					mensajes[i][j].addActionListener(this);
				}

				mensajes[i][j].setFont(new Font("Tahoma", Font.PLAIN, 14));
				mensajes[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				panel.add(mensajes[i][j]);

			}
		}
		panel_1.setLayout(new BorderLayout());

		JTextArea textArea = new JTextArea();
		textArea.setColumns(10);
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textArea.setEditable(false);

		JScrollPane scr = new JScrollPane();
		scr.setViewportView(textArea);
		panel_1.add(scr,BorderLayout.CENTER);

		bVolver = new JButton("Volver");
		bVolver.addActionListener(this);
		bVolver.setBounds(785, 290, 89, 23);
		getRootPane().setDefaultButton(bVolver);
		contentPane.add(bVolver);
		for(int i = 0;i<data.nombres.size();i++) {
			textArea.setText(textArea.getText()+"("+(i+1)+") Nombre: "+data.nombres.get(i)
					+ " - Cedula: "+data.cc.get(i)
					+ " - Idas a cine: "+data.frecuencia.get(i)
					+ " - Puntos: "+data.puntos.get(i)
					+ " - ¿Tiene tarjeta basic? "+data.tieneTarjetaBasic.get(i)
					+ " - ¿Tiene tarjeta gold? "+data.tieneTarjetaGold.get(i)
					+ " - Dinero en la tarjeta: "+data.dineroTarjeta.get(i)
					+"\n");
		}
		confit.setLayout(new BorderLayout());

		JTextArea textAreaConfit = new JTextArea();
		textAreaConfit.setColumns(10);
		textAreaConfit.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textAreaConfit.setEditable(false);

		JScrollPane scrConfit = new JScrollPane();
		scrConfit.setViewportView(textAreaConfit);
		confit.add(scrConfit,BorderLayout.CENTER);

		for(int i = 0;i<listaConfiteria.size();i++) {
			for(int j = 0;j<listaConfiteria.get(i).size();j++) {
				textAreaConfit.setText(textAreaConfit.getText()+" - "+listaConfiteria.get(i).get(j));
			}
			textAreaConfit.setText(textAreaConfit.getText()+"\n");
		}
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==bVolver) {
			aplicacion.setVisible(true);
			setVisible(false);
		}
		for(int i = 0;i<canti;i++) {
			for(int j = 0;j<cantj;j++) {
				if(e.getSource()==mensajes[i][j]) {
					JOptionPane.showMessageDialog(null, lugaresCine[i][j]);
				}
			}
		}
	}
}