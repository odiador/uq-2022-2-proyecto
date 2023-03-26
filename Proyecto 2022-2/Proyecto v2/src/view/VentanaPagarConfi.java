package view;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.ProcesosCliente;
import model.ProcesosConfit;
import model.data;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;

public class VentanaPagarConfi extends JFrame {

	private JPanel panel;
	private JTextField textFieldValorPagar;
	private JTextField textFieldIdentificacion;

	private double valorTotal,valorNuevo;
	ArrayList<Integer> cantidadComida;
	ArrayList<String> nomComida;
	ArrayList <String> listaComida;
	ImageIcon carrito=VentanaConfiteria.carrito;

	public static double determinarValorTotal(ArrayList<String>nomComida, ArrayList<Integer> cantidadComida) {

		double valorTotal=0;
		for(int i = 0; i<nomComida.size();i++) {
			if(nomComida.get(i).equals("Crispetas pequeñas, ")) {
				valorTotal+=7500*cantidadComida.get(i);
				continue;
			}
			if(nomComida.get(i).equals("Crispetas medianas, ")) {
				valorTotal+=10000*cantidadComida.get(i);

			}
			if(nomComida.get(i).equals("Crispetas grandes, ")) {
				valorTotal+=11000*cantidadComida.get(i);
				continue;
			}
			if(nomComida.get(i).equals("Crispetas extragrandes, ")) {
				valorTotal+=13000*cantidadComida.get(i);
				continue;
			}
			if(nomComida.get(i).equals("Crispetas familiar, ")) {
				valorTotal+=14500*cantidadComida.get(i);
				continue;
			}
			if(nomComida.get(i).equals("Gaseosa pequeña, ")) {
				valorTotal+=2500*cantidadComida.get(i);
				continue;
			}
			if(nomComida.get(i).equals("Gaseosa mediana, ")) {
				valorTotal+=4000*cantidadComida.get(i);
				continue;
			}
			if(nomComida.get(i).equals("Gaseosa grande, ")) {
				valorTotal+=5000*cantidadComida.get(i);
				continue;
			}
			if(nomComida.get(i).equals("Agua, ")) {
				valorTotal+=2000*cantidadComida.get(i);
				continue;
			}
			if(nomComida.get(i).equals("Té, ")) {
				valorTotal+=3500*cantidadComida.get(i);
				continue;
			}
			if(nomComida.get(i).equals("Perro, ")) {
				valorTotal+=5000*cantidadComida.get(i);
				continue;
			}
			if(nomComida.get(i).equals("Sándwich, ")) {
				valorTotal+=3500*cantidadComida.get(i);
				continue;
			}
			if(nomComida.get(i).equals("Caja de dulces, ")) {
				valorTotal+=2000*cantidadComida.get(i);
				continue;
			}
			if(nomComida.get(i).equals("Combo 1, ")) {
				valorTotal+=12000*cantidadComida.get(i);
				continue;
			}
			if(nomComida.get(i).equals("Combo 2, ")) {
				valorTotal+=20000*cantidadComida.get(i);
				continue;
			}
			if(nomComida.get(i).equals("Combo 3, ")) {
				valorTotal+=30000*cantidadComida.get(i);
				continue;
			}
			else{
				valorTotal+=13000*cantidadComida.get(i);
				continue;
			}
		}
		return valorTotal;
	}

	public VentanaPagarConfi(Aplicacion aplicacion, VentanaConfiteria VentanaConfit,ArrayList<Object> infoPersonas) {
		setTitle("Facturar Confitería - Ventana de pago | "+infoPersonas.get(0)+" - "+infoPersonas.get(1));
		setBounds(100, 100, 500, 400);
		setResizable(false);
		setLocationRelativeTo(null);

		ImageIcon crispIcon = new ImageIcon("Imagenes/crispetast.png");
		setIconImage(crispIcon.getImage());

		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(null);

		setContentPane(panel);

		cantidadComida = VentanaConfiteria.cantidadComida;
		nomComida = VentanaConfiteria.nomComida;
		listaComida= VentanaConfiteria.listaComida;
		valorTotal=determinarValorTotal(nomComida, cantidadComida);
		valorNuevo = valorTotal;

		JLabel lblPagar = new JLabel("Total a Pagar");
		lblPagar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPagar.setBounds(10, 43, 116, 35);
		panel.add(lblPagar);

		JLabel lblFormaPagar = new JLabel("Forma de pago");
		lblFormaPagar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFormaPagar.setBounds(10, 89, 131, 35);
		panel.add(lblFormaPagar);
		ArrayList<String> listaPagos = new ArrayList<String>();
		listaPagos.add("Escoja el método de pago...");
		listaPagos.add("Efectivo");
		listaPagos.add("Tarjeta de crédito");
		listaPagos.add("PSE");
		if((boolean) infoPersonas.get(5)) {
			listaPagos.add("Tarjeta Basic");
		}
		if((boolean) infoPersonas.get(4)) {
			listaPagos.add("Tarjeta Gold");
		}
		String[] formaPago = new String[listaPagos.size()];
		for(int i = 0;i<formaPago.length;i++) {
			formaPago[i] = listaPagos.get(i);
		}
		JComboBox comboBoxMetodoPago = new JComboBox(formaPago);
		comboBoxMetodoPago.setFont(new Font("Tahoma", Font.PLAIN, 16));
		comboBoxMetodoPago.setBounds(151, 93, 230, 26);
		panel.add(comboBoxMetodoPago);

		JButton btnActualizarValor = new JButton("Actualizar Valor");
		btnActualizarValor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String auxiliar=(String)comboBoxMetodoPago.getSelectedItem();
				valorNuevo = valorTotal;
				if(auxiliar.equals("Tarjeta Basic")) {
					valorNuevo=valorTotal-(valorTotal*0.05);
				}
				else if(auxiliar.equals("Tarjeta Gold")) {
					valorNuevo=valorTotal-(valorTotal*0.2);
				}
				textFieldValorPagar.setText(valorNuevo+"");
			}
		});
		btnActualizarValor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnActualizarValor.setBounds(319, 53, 157, 26);
		panel.add(btnActualizarValor);

		textFieldValorPagar = new JTextField();
		textFieldValorPagar.setBounds(151, 53, 121, 20);
		textFieldValorPagar.setEditable(false);
		textFieldValorPagar.setText(valorTotal+"");
		panel.add(textFieldValorPagar);
		textFieldValorPagar.setColumns(10);


		JButton btnVentConfiteria = new JButton("Ir Atras");
		btnVentConfiteria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaConfiteria ventConfiteria = new VentanaConfiteria(aplicacion,infoPersonas);
				ventConfiteria.setVisible(true);
				setVisible(false);
			}
		});
		btnVentConfiteria.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnVentConfiteria.setBounds(10, 317, 89, 35);
		panel.add(btnVentConfiteria);

		JButton btnPagar = new JButton("Pagar");
		btnPagar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnPagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String auxiliar=(String)(comboBoxMetodoPago.getSelectedItem());
				if((auxiliar.equals("Tarjeta Basic")||auxiliar.equals("Tarjeta Gold"))&&!auxiliar.equals("Escoja el método de pago...")&&cantidadComida.size()!=0) {
					if((double) infoPersonas.get(6)>=valorNuevo) {
						double nuevo = (double) (infoPersonas.get(6))-valorNuevo;
						imprimir("El pago fue realizado de manera efectiva,\n"+(String) infoPersonas.get(0)
						+" ahora tiene $"+nuevo);
						pagarConTarjeta(valorNuevo, (String) infoPersonas.get(1));
						aplicacion.setVisible(true);
						setVisible(false);
						procesarCompraConfiteria(infoPersonas,auxiliar,valorNuevo);
					} else {
						imprimir("No se pudo hacer el pago");
					}
				} else if(!auxiliar.equals("Escoja el método de pago...")&&cantidadComida.size()!=0){
					imprimir("El pago fue realizado de manera efectiva");
					procesarCompraConfiteria(infoPersonas,auxiliar,valorNuevo);
					aplicacion.setVisible(true);
					setVisible(false);
				} else {
					imprimir("No se pudo hacer el pago");
				}
			}
		});
		btnPagar.setBounds(387, 317, 89, 35);
		panel.add(btnPagar);

		JButton btnCarrito = new JButton();
		btnCarrito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String listComida=VentanaConfiteria.mostrarArrayList(listaComida);
				if(listComida.length()>0) {
					JOptionPane.showMessageDialog(null, listComida);
				}
				else {
					JOptionPane.showMessageDialog(null, "No hay ningún objeto");
				}
			}
		});
		btnCarrito.setBounds(282, 52, 27, 23);
		btnCarrito.setIcon(new ImageIcon(carrito.getImage().getScaledInstance(btnCarrito.getWidth(), btnCarrito.getHeight(), Image.SCALE_SMOOTH)));
		panel.add(btnCarrito);

	}

	public String strToHtml(String texto) {
		return "<html><p>"+ texto +"</p></html> ";
	}
	public void pagarConTarjeta (double cuanto,String cedu){
		ProcesosCliente ProcesosCliente = new ProcesosCliente();
		ProcesosCliente.pagarConTarjeta(cuanto,cedu);
	}
	public void imprimir(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}
	public void procesarCompraConfiteria (ArrayList<Object> infoPersonas,String auxiliar,double price) {
		ProcesosConfit ProcesosConfit = new ProcesosConfit(infoPersonas,auxiliar);
		listaComida.clear();
		ProcesosConfit.procesarCompraConfiteria(nomComida,cantidadComida,price);
	}
}
