package view;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import model.ProcesosCliente;

import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

public class MostrarHistorialCliente extends JFrame {

	private JPanel contentPane;
	private JTable table;

	private ArrayList<String> tiposCompra,tiposCompraC;
	private ArrayList<String> horas,horasC;
	private ArrayList<String> dias,diasC;
	private ArrayList<Integer> cantidadB,cantidadC;
	private ArrayList<String> medPago,medPagoC;
	private ArrayList<String> cedus,cedusC;
	private ArrayList<String> names,namesC;
	private ArrayList<String> sillas,confiteria;
	private ArrayList<Double> precios,preciosC;
	private String[][] x1,x2;
	ArrayList<ArrayList<Object>> historialBol = new ArrayList<ArrayList<Object>>();
	ArrayList<ArrayList<Object>> historialConf = new ArrayList<ArrayList<Object>>();
	/**
	 * Create the frame.
	 */
	public MostrarHistorialCliente(ArrayList<ArrayList<Object>> historial,String cedu,Aplicacion aplicacion) {
		ImageIcon crispIcon = new ImageIcon("Imagenes/crispetast.png");
		setIconImage(crispIcon.getImage());
		setTitle("Stellar Cinema - Historial Cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 787, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		String columnasB[] = {"Tipo de Compra","Hora","Día","Cédula","Nombre","Sillas","Cantidad","Medio de Pago","Precio"};
		String columnasC[] = {"Tipo de Compra","Hora","Día","Cédula","Nombre","Confitería","Cantidad","Medio de Pago","Precio"};

		generarHistoriales(historial);
		obtenerHistorial(cedu);
		generarTablas();
		table = new JTable(x1,columnasB);
		table.setEnabled(false);
		JScrollPane scr = new JScrollPane(table);
		scr.setBounds(10, 70, 666, 325);
		contentPane.add(scr);

		table = new JTable(x2,columnasC);
		JScrollPane scr2 = new JScrollPane(table);
		scr2.setBounds(10, 438, 666, 325);
		contentPane.add(scr2);

		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aplicacion.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton.setBounds(686, 742, 85, 21);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel = new JLabel("Tabla Boletas");
		lblNewLabel.setBounds(33, 26, 131, 13);
		contentPane.add(lblNewLabel);

		JLabel lblTablaConfitera = new JLabel("Tabla Confitería");
		lblTablaConfitera.setBounds(33, 417, 131, 13);
		contentPane.add(lblTablaConfitera);
	}
	public void generarHistoriales(ArrayList<ArrayList<Object>> historial) {
		for(int i = 0;i<historial.size();i++) {
			if(((String) historial.get(i).get(0)).equals("Boleta")) {
				historialBol.add(historial.get(i));
			}
			if(((String) historial.get(i).get(0)).equals("Confitería")) {
				historialConf.add(historial.get(i));
			}
		}
	}
	public void obtenerHistorial(String cedu) {
		tiposCompra = new ArrayList<String>();
		horas = new ArrayList<String>();
		dias = new ArrayList<String>();
		cantidadB  = new ArrayList<Integer>();
		medPago = new ArrayList<String>();
		cedus = new ArrayList<String>();
		names = new ArrayList<String>();
		sillas = new ArrayList<String>();
		precios = new ArrayList<Double>();
		for(int i = 0;i<historialBol.size();i++) {
			if(((String) historialBol.get(i).get(1)).equals(cedu)) {
				tiposCompra.add((String) historialBol.get(i).get(0));
				horas.add(((int) historialBol.get(i).get(3))+":"+((int) historialBol.get(i).get(4))+":"+((int) historialBol.get(i).get(5)));
				dias.add((int) historialBol.get(i).get(6)+"/"+(int) historialBol.get(i).get(7)+"/"+(int) historialBol.get(i).get(8));
				cedus.add(cedu);
				names.add((String) historialBol.get(i).get(2));
				sillas.add((String) historialBol.get(i).get(12));
				cantidadB.add((int) historialBol.get(i).get(9));
				medPago.add((String) historialBol.get(i).get(10));
				precios.add((Double) historialBol.get(i).get(11));
			}
		}
		tiposCompraC = new ArrayList<String>();
		horasC = new ArrayList<String>();
		diasC = new ArrayList<String>();
		cedusC = new ArrayList<String>();
		namesC = new ArrayList<String>();
		confiteria = new ArrayList<String>();
		cantidadC  = new ArrayList<Integer>();
		medPagoC = new ArrayList<String>();
		preciosC = new ArrayList<Double>();

		for(int i = 0;i<historialConf.size();i++) {
			if(((String) historialConf.get(i).get(1)).equals(cedu)) {
				for(int j = 0;j<((ArrayList<String>) historialConf.get(i).get(10)).size();j++) {
					tiposCompraC.add((String) historialConf.get(i).get(0));
					horasC.add(((int) historialConf.get(i).get(3))+":"+((int) historialConf.get(i).get(4))+":"+((int) historialConf.get(i).get(5)));
					diasC.add((int) historialConf.get(i).get(6)+"/"+(int) historialConf.get(i).get(7)+"/"+(int) historialConf.get(i).get(8));
					cedusC.add(cedu);
					namesC.add((String) historialConf.get(i).get(2));
					confiteria.add(((ArrayList<String>) historialConf.get(i).get(10)).get(j));
					cantidadC.add(((ArrayList<Integer>) historialConf.get(i).get(11)).get(j));
					medPagoC.add((String) historialConf.get(i).get(9));
					preciosC.add((Double) historialConf.get(i).get(12));
				}

			}
		}
	}
	public void generarTablas() {
		x1 = new String[tiposCompra.size()][9];
		for(int i = 0;i<tiposCompra.size();i++) {
			x1[i][0] = tiposCompra.get(i);
			x1[i][1] = horas.get(i);
			x1[i][2] = dias.get(i);
			x1[i][3] = cedus.get(i);
			x1[i][4] = names.get(i);
			x1[i][5] = sillas.get(i);
			x1[i][6] = cantidadB.get(i)+"";
			x1[i][7] = medPago.get(i);
			x1[i][8] = precios.get(i)+"";
		}
		x2 = new String[tiposCompraC.size()][9];
		for(int i = 0;i<tiposCompraC.size();i++) {
			x2[i][0] = tiposCompraC.get(i);
			x2[i][1] = horasC.get(i);
			x2[i][2] = diasC.get(i);
			x2[i][3] = cedusC.get(i);
			x2[i][4] = namesC.get(i);
			x2[i][5] = eliminarFinal(confiteria.get(i));
			x2[i][6] = cantidadC.get(i)+"";
			x2[i][7] = medPagoC.get(i);
			x2[i][8] = preciosC.get(i)+"";
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
