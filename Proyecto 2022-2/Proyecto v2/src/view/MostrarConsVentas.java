package view;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import model.ProcesosCliente;
import model.data;

import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

public class MostrarConsVentas extends JFrame {

	private JPanel contentPane;
	private JTable table;

	private ArrayList<String> tiposCompra;
	private ArrayList<String> horas;
	private ArrayList<String> dias;
	private ArrayList<Integer> cantidadB;
	private ArrayList<String> medPago;
	private ArrayList<String> cedus;
	private ArrayList<String> names;
	private ArrayList<String> sillas,confiteria;
	private ArrayList<Double> precios;
	private ArrayList<ArrayList<Object>> historial;
	private String[][] x1;
	private data data;
	/**
	 * Create the frame.
	 */
	public MostrarConsVentas(Aplicacion aplicacion) {
		data = new data();
		historial = data.lista;
		ImageIcon crispIcon = new ImageIcon("Imagenes/crispetast.png");
		setIconImage(crispIcon.getImage());
		setTitle("Stellar Cinema - Historial Cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 787, 435);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		String columnasB[] = {"Tipo de Compra","Hora","Día","Cédula","Nombre","Sillas","Confitería","Cantidad","Medio de Pago","Precio"};
		obtenerHistorial(historial);
		generarTablas();
		table = new JTable(x1,columnasB);
		table.setEnabled(false);
		JScrollPane scr = new JScrollPane(table);
		scr.setBounds(10, 70, 666, 325);
		contentPane.add(scr);

		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aplicacion.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton.setBounds(688, 375, 85, 21);
		contentPane.add(btnNewButton);
	}
	public void obtenerHistorial(ArrayList<ArrayList<Object>> historial) {
		tiposCompra = new ArrayList<String>();
		horas = new ArrayList<String>();
		dias = new ArrayList<String>();
		cantidadB  = new ArrayList<Integer>();
		medPago = new ArrayList<String>();
		cedus = new ArrayList<String>();
		names = new ArrayList<String>();
		sillas = new ArrayList<String>();
		confiteria = new ArrayList<String>();
		precios = new ArrayList<Double>();
		for(int i = 0;i<historial.size();i++) {
			if(((String) historial.get(i).get(0)).equals("Boleta")) {
				tiposCompra.add((String) historial.get(i).get(0));
				horas.add(((int) historial.get(i).get(3))+":"+((int) historial.get(i).get(4))+":"+((int) historial.get(i).get(5)));
				dias.add((int) historial.get(i).get(6)+"/"+(int) historial.get(i).get(7)+"/"+(int) historial.get(i).get(8));
				cedus.add((String) historial.get(i).get(1));
				names.add((String) historial.get(i).get(2));
				confiteria.add("");
				sillas.add((String) historial.get(i).get(12));
				cantidadB.add((int) historial.get(i).get(9));
				medPago.add((String) historial.get(i).get(10));
				precios.add((Double) historial.get(i).get(11));
			} else {
				for(int j = 0;j<((ArrayList<String>) historial.get(i).get(10)).size();j++) {
					tiposCompra.add((String) historial.get(i).get(0));
					horas.add(((int) historial.get(i).get(3))+":"+((int) historial.get(i).get(4))+":"+((int) historial.get(i).get(5)));
					dias.add((int) historial.get(i).get(6)+"/"+(int) historial.get(i).get(7)+"/"+(int) historial.get(i).get(8));
					cedus.add((String) historial.get(i).get(1));
					names.add((String) historial.get(i).get(2));
					sillas.add("");
					confiteria.add(((ArrayList<String>) historial.get(i).get(10)).get(j));
					cantidadB.add(((ArrayList<Integer>) historial.get(i).get(11)).get(j));
					medPago.add((String) historial.get(i).get(9));
					precios.add((Double) historial.get(i).get(12));
				}
			}
		}
	}
	public void generarTablas() {
		x1 = new String[tiposCompra.size()][10];
		for(int i = 0;i<tiposCompra.size();i++) {
			x1[i][0] = tiposCompra.get(i);
			x1[i][1] = horas.get(i);
			x1[i][2] = dias.get(i);
			x1[i][3] = cedus.get(i);
			x1[i][4] = names.get(i);
			x1[i][5] = sillas.get(i);
			x1[i][6] = eliminarFinal(confiteria.get(i));
			x1[i][7] = cantidadB.get(i)+"";
			x1[i][8] = medPago.get(i);
			x1[i][9] = precios.get(i)+"";
		}
	}
	public static String eliminarFinal(String cadenaComa) {
		String res = "";
		for(int i = 0;i<cadenaComa.length()-2;i++) {
			res+=cadenaComa.charAt(i)+"";
		}
		return res;
	}
}
