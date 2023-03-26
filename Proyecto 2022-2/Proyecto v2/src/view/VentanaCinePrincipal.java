package view;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import model.ProcesosCliente;

public class VentanaCinePrincipal extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField tfPuestos;
	private VentanaCine mB;
	private JButton bIr;
	private JButton bCancelar;
	private Aplicacion aplicacion;
	private JTextField tfCedu;

	public VentanaCinePrincipal(Aplicacion aplicacion) {
		/* La aplicación propia de la ventana Matriz pasa a ser la
		 * misma aplicación parámetro para llamarse en otras funciones
		*/
		this.aplicacion = aplicacion;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600,330);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		ImageIcon crispIcon = new ImageIcon("Imagenes/crispetast.png");
		setIconImage(crispIcon.getImage());
		setTitle("Stellar Cinema - Ir a Sala");

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel tCedu = new JLabel("Escribe la cédula de la persona");
		tCedu.setForeground(new Color(255, 255, 255));
		tCedu.setHorizontalAlignment(SwingConstants.RIGHT);
		tCedu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tCedu.setBounds(100, 125, 230, 30);
		contentPane.add(tCedu);

		bIr = new JButton("Ir a sala");
		//boton default
		getRootPane().setDefaultButton(bIr);
		bIr.addActionListener(this);
		bIr.setFont(new Font("Tahoma", Font.PLAIN, 16));
		bIr.setBounds(140, 230, 150, 40);
		bIr.setBackground(new Color(255, 255, 255));
		bIr.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(bIr);

		tfPuestos = new JTextField();
		tfPuestos.setToolTipText("Numero");
		tfPuestos.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfPuestos.setColumns(3);
		tfPuestos.setBounds(340, 175, 150, 30);
		contentPane.add(tfPuestos);

		JLabel title = new JLabel();
		ImageIcon tituloCine = new ImageIcon("Imagenes/ttlCine.png");
		title.setBounds(70, 30, 460, 55);
		title.setIcon(new ImageIcon(tituloCine.getImage().getScaledInstance(title.getWidth(), title.getHeight(), Image.SCALE_SMOOTH)));
		contentPane.add(title);

		JLabel tPuestos = new JLabel("Escribe la cantidad de puestos");
		tPuestos.setHorizontalAlignment(SwingConstants.RIGHT);
		tPuestos.setForeground(Color.WHITE);
		tPuestos.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tPuestos.setBounds(100, 175, 230, 30);
		contentPane.add(tPuestos);

		tfCedu = new JTextField();
		tfCedu.setToolTipText("Cédula");
		tfCedu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfCedu.setColumns(3);
		tfCedu.setBounds(340, 125, 150, 30);
		contentPane.add(tfCedu);

		bCancelar = new JButton("Cancelar");
		bCancelar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		bCancelar.setBorder(new EmptyBorder(5, 5, 5, 5));
		bCancelar.setBackground(Color.WHITE);
		bCancelar.setBounds(300, 230, 150, 40);
		bCancelar.addActionListener(this);
		contentPane.add(bCancelar);

		JLabel fondo = new JLabel();
		fondo.setBounds(0, 0, getWidth(), getHeight());
		ImageIcon fCine = new ImageIcon("Imagenes/fondoPrincipal.png");
		fondo.setIcon(new ImageIcon(fCine.getImage().getScaledInstance(fondo.getWidth(), fondo.getHeight(), Image.SCALE_SMOOTH)));
		contentPane.add(fondo);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==bIr) {
			try {
				int cantidad = Integer.parseInt(tfPuestos.getText());
				String cedu = tfCedu.getText();
				ArrayList<Object> cedus = buscarCliente(cedu);
				/* Guarda toda la info del cliente en un arrayList que
				 * sirve para manejar mejor la información. Si no
				 * encuentra al cliente, queda de tamaño 0
				 */
				if(cedus.size()!=0&&cantidad>0&&cantidad<=16) {
					tfCedu.setText("");
					tfPuestos.setText("");
					//llama a una nueva clase con la información obtenida
					mB = new VentanaCine(cantidad,aplicacion,cedus);
					mB.setVisible(true);
					setVisible(false);
				} else {
					JOptionPane.showMessageDialog(this, "Diligencia bien los datos","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
			catch (Exception evt) {}
		}
		if (e.getSource()==bCancelar) {
			tfCedu.setText("");
			tfPuestos.setText("");
			aplicacion.setVisible(true);
			setVisible(false);
		}
	}
	public ArrayList<Object> buscarCliente(String cedu) {
		ProcesosCliente ProcesosCliente = new ProcesosCliente();
		return ProcesosCliente.buscarCliente(cedu);
	}
}

