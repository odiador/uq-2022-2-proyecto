package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import model.ProcesosCliente;
import model.data;
@SuppressWarnings("rawtypes")
public class RegistarCliente extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField tfNombre;
	private JTextField tfCedula;
	private JLabel tBasic;

	private JComboBox cBasico;
	private JLabel tGold;
	private JComboBox cGold;
	private JButton bRegistrar;
	private JButton bCancelar;
	private Color white = new Color(255,255,255);
	private Aplicacion aplicacion;
	public RegistarCliente(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 570, 350);
		setLocationRelativeTo(this);
		contentPane = new JPanel();
		contentPane.setForeground(Color.BLACK);
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));


		ImageIcon crispIcon = new ImageIcon("Imagenes/crispetast.png");
		setIconImage(crispIcon.getImage());
		setTitle("Stellar Cinema - Registrar Cliente");

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel tCedula = new JLabel("Escribe la cédula de la persona");
		tCedula.setHorizontalAlignment(SwingConstants.RIGHT);
		tCedula.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tCedula.setForeground(white);
		tCedula.setBounds(36, 124, 250, 30);
		contentPane.add(tCedula);

		JLabel tNombre = new JLabel("Escribe el nombre de la persona");
		tNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		tNombre.setForeground(white);
		tNombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tNombre.setBounds(36, 84, 250, 30);
		contentPane.add(tNombre);

		tfNombre = new JTextField();
		tfNombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfNombre.setBounds(316, 84, 200, 30);
		contentPane.add(tfNombre);
		tfNombre.setColumns(10);

		tfCedula = new JTextField();
		tfCedula.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfCedula.setColumns(10);
		tfCedula.setBounds(316, 124, 200, 30);
		contentPane.add(tfCedula);

		String[] s = {"No","Si"};

		tBasic = new JLabel("¿Desea registrar tarjeta basic?");
		tBasic.setHorizontalAlignment(SwingConstants.RIGHT);
		tBasic.setForeground(white);
		tBasic.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tBasic.setBounds(36, 164, 250, 30);
		contentPane.add(tBasic);

		cBasico = new JComboBox();
		cBasico.setModel(new DefaultComboBoxModel(s));
		cBasico.setBounds(316, 164, 200, 30);
		contentPane.add(cBasico);

		tGold = new JLabel("¿Desea registrar tarjeta gold?");
		tGold.setHorizontalAlignment(SwingConstants.RIGHT);
		tGold.setForeground(white);
		tGold.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tGold.setBounds(36, 204, 250, 30);
		contentPane.add(tGold);


		cGold = new JComboBox();
		cGold.setModel(new DefaultComboBoxModel(s));
		cGold.setBounds(316, 204, 200, 30);
		contentPane.add(cGold);

		bRegistrar = new JButton("Registrar");
		bRegistrar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		bRegistrar.setBounds(230, 254, 150, 30);
		getRootPane().setDefaultButton(bRegistrar);
		bRegistrar.setBackground(getBackground());
		bRegistrar.addActionListener(this);
		contentPane.add(bRegistrar);

		bCancelar = new JButton("Cancelar");
		bCancelar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		bCancelar.setBackground(getBackground());
		bCancelar.setBounds(390, 254, 150, 30);
		bCancelar.addActionListener(this);
		contentPane.add(bCancelar);


		JLabel title = new JLabel();
		ImageIcon tituloCine = new ImageIcon("Imagenes/ttlCine.png");
		title.setBounds(55, 20, 460, 55);
		title.setIcon(new ImageIcon(tituloCine.getImage().getScaledInstance(title.getWidth(), title.getHeight(), Image.SCALE_SMOOTH)));
		contentPane.add(title);


		JLabel fondo = new JLabel();
		fondo.setBounds(0, 0, getWidth(), getHeight());
		ImageIcon fCine = new ImageIcon("Imagenes/fondoPrincipal.png");
		fondo.setIcon(new ImageIcon(fCine.getImage().getScaledInstance(fondo.getWidth(), fondo.getHeight(), Image.SCALE_SMOOTH)));
		contentPane.add(fondo);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==bRegistrar) {
			if(!(tfNombre.getText().equals("")||tfCedula.getText().equals(""))) {
				String nombre = tfNombre.getText();
				String cedula = tfCedula.getText();
				int frecuencia = 0;
				int puntos = 0;
				String msgBasic = (String) cBasico.getSelectedItem();
				String msgGold = (String) cGold.getSelectedItem();
				double dineroTarjeta = 0;
				boolean tieneBasic = true, tieneG = true;
				if(msgBasic.equals("No")) tieneBasic = false;
				if(msgGold.equals("No")) tieneG = false;

				int posC = buscarPosCliente(cedula);
				if(posC==-7) {
					agregarCliente(nombre, cedula, frecuencia, puntos, tieneBasic, tieneG, dineroTarjeta);
					JOptionPane.showMessageDialog(null, "Se registró exitosamente el cliente","Información",JOptionPane.INFORMATION_MESSAGE);
					tfCedula.setText("");
					tfNombre.setText("");
					aplicacion.setVisible(true);
					setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "Ya hay otro cliente registrado con la misma cédula","Advertencia",JOptionPane.WARNING_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Faltan datos por llenar","Error",JOptionPane.ERROR_MESSAGE);
			}
		}
		if(e.getSource()==bCancelar) {
			aplicacion.setVisible(true);
			setVisible(false);
		}
	}
	public int buscarPosCliente(String cedu) {
		ProcesosCliente ProcesosCliente = new ProcesosCliente();
		return ProcesosCliente.buscarPosCliente(cedu);
	}
	public void agregarCliente(String nombre, String cedula,int cant,int pts, boolean tieneBasic, boolean tieneG,double plata) {
		ProcesosCliente ProcesosCliente = new ProcesosCliente();
		ProcesosCliente.agregarCliente(nombre,cedula,cant,pts,tieneBasic,tieneG,plata);
	}
}
