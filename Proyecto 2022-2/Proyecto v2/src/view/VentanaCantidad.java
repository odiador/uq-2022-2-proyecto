package view;
import java.awt.EventQueue;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.*;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;


public class VentanaCantidad extends JFrame {

	public JButton btnAgregar;
	private JPanel Panel;
	private JTextField textCantidad;
	public ArrayList<String> nomComida;
	public ArrayList<Integer> cantComida;
	public ArrayList<String> listaComida;
	private VentanaConfiteria ventanaConfiteria;


	public VentanaCantidad(VentanaConfiteria ventanaConfiteria, String x) {
		this.ventanaConfiteria = ventanaConfiteria;
		nomComida = ventanaConfiteria.nomComida;
		cantComida = ventanaConfiteria.cantidadComida;
		listaComida = ventanaConfiteria.listaComida;

		setTitle("Facturar Confitería - Cantidad");
		setBounds(100, 100, 450, 300);
		setResizable(false);
		setLocationRelativeTo(null);

		ImageIcon crispIcon = new ImageIcon("Imagenes/crispetast.png");
		setIconImage(crispIcon.getImage());

		Panel = new JPanel();
		Panel.setBackground(new Color(255, 255, 255));
		Panel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(Panel);
		Panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Cantidad");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(70, 105, 75, 35);
		Panel.add(lblNewLabel);

		textCantidad = new JTextField();
		textCantidad.setBounds(155, 114, 96, 20);
		Panel.add(textCantidad);
		textCantidad.setText("1");
		textCantidad.setColumns(10);


		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(261, 113, 89, 23);
		getRootPane().setDefaultButton(btnAgregar);
		btnAgregar.setBackground(new Color(255,255,255));
		btnAgregar.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
			if(Integer.parseInt(textCantidad.getText())!=0 && !textCantidad.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Agregado al carrito");
				agregarAcarrito(x);
				setVisible(false);
				ventanaConfiteria.setVisible(true);
			}
			else {
				JOptionPane.showMessageDialog(null, "No se ha ingresado ninguna cantidad, intetelo de nuevo");
			}
			}
		});
		Panel.add(btnAgregar);

		JButton btnAtras = new JButton("Ir atrás");
		btnAtras.setBackground(new Color(255,255,255));
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaConfiteria.setVisible(true);
				setVisible(false);
			}
		});
		btnAtras.setBounds(10, 229, 89, 23);
		Panel.add(btnAtras);
	}

	public void agregarAcarrito(String cadena) {
		int pos = -7;
		for(int i = 0;i<nomComida.size();i++) {
			if(nomComida.get(i).equals(cadena)) {
				pos = i;
				break;
			}
		}

		if(pos==-7) {
			nomComida.add(cadena);
			cantComida.add(Integer.parseInt(textCantidad.getText()));
			listaComida.add(nomComida.get(nomComida.size()-1)+cantComida.get(cantComida.size()-1)+" Unidad/es\n");
		} else {
			cantComida.set(pos,Integer.parseInt(textCantidad.getText())+cantComida.get(pos));
			listaComida.set(pos,nomComida.get(pos)+""+cantComida.get(pos)+" Unidad/es\n");
		}
	}
}
