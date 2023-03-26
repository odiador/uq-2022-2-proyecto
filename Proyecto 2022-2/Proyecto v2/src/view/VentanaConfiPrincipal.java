package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import model.ProcesosCliente;

public class VentanaConfiPrincipal extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField tfCedu;
	private JButton bAceptar;
	private JButton bCancelar;
	private Aplicacion aplicacion;


	/**
	 * Create the frame.
	 */
	public VentanaConfiPrincipal(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 330);
		setResizable(false);
		ImageIcon crispIcon = new ImageIcon("Imagenes/crispetast.png");
		setIconImage(crispIcon.getImage());
		setTitle("Stellar Cinema - Facturar Confitería");
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(0,0,0));
		contentPane.setLayout(null);

		setContentPane(contentPane);

		JLabel title = new JLabel();
		ImageIcon tituloCine = new ImageIcon("Imagenes/ttlCine.png");
		title.setBounds(70, 30, 460, 55);
		title.setIcon(new ImageIcon(tituloCine.getImage().getScaledInstance(title.getWidth(), title.getHeight(), Image.SCALE_SMOOTH)));
		contentPane.add(title);


		JLabel tCedu = new JLabel("Escribe la cédula de la persona");
		tCedu.setForeground(new Color(255, 255, 255));
		tCedu.setHorizontalAlignment(SwingConstants.RIGHT);
		tCedu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tCedu.setBounds(100, 125, 230, 30);
		contentPane.add(tCedu);


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


		bAceptar = new JButton("Aceptar");
		//boton default
		getRootPane().setDefaultButton(bAceptar);
		bAceptar.addActionListener(this);
		bAceptar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		bAceptar.setBounds(140, 230, 150, 40);
		bAceptar.setBackground(new Color(255, 255, 255));
		getRootPane().setDefaultButton(bAceptar);
		bAceptar.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(bAceptar);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==bAceptar) {
			try {
				String cedu = tfCedu.getText();
				ArrayList<Object> cedus = buscarCliente(cedu);
				/* Guarda toda la info del cliente en un arrayList que
				 * sirve para manejar mejor la información. Si no
				 * encuentra al cliente, queda de tamaño 0
				 */
				if(cedus.size()!=0) {
					tfCedu.setText("");
					//llama a una nueva clase con la información obtenida
					VentanaConfiteria VentanaConfiteria = new VentanaConfiteria(aplicacion,cedus);
					VentanaConfiteria.setVisible(true);
					setVisible(false);
				} else {
					JOptionPane.showMessageDialog(this, "Diligencia bien los datos","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
			catch (Exception evt) {}
		}
		if (e.getSource()==bCancelar) {
			tfCedu.setText("");;
			aplicacion.setVisible(true);
			setVisible(false);
		}
	}
	public ArrayList<Object> buscarCliente (String cedu) {
		ProcesosCliente ProcesosCliente = new ProcesosCliente();
		return ProcesosCliente.buscarCliente(cedu);
	}
}
