package view;


import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import model.ProcesosCliente;

import javax.swing.JComboBox;
@SuppressWarnings("rawtypes")
public class DarTarjeta extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JButton bBuscar;
	private JTextField textField;
	private JButton btnCancelar;
	private JComboBox comboBox;
	private Aplicacion Aplicacion;


	public DarTarjeta(Aplicacion Aplicacion) {
		this.Aplicacion = Aplicacion;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 570, 350);
		setLocationRelativeTo(this);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);


		ImageIcon crispIcon = new ImageIcon("Imagenes/crispetast.png");
		setIconImage(crispIcon.getImage());
		setTitle("Stellar Cinema - Dar Tarjeta a Cliente");

		JLabel title = new JLabel();
		ImageIcon tituloCine = new ImageIcon("Imagenes/ttlCine.png");
		title.setBounds(55, 40, 460, 55);
		title.setIcon(new ImageIcon(tituloCine.getImage().getScaledInstance(title.getWidth(), title.getHeight(), Image.SCALE_SMOOTH)));
		contentPane.add(title);

		JLabel lblNewLabel = new JLabel("Ingrese la cedula de la persona");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(45, 150, 250, 30);
		contentPane.add(lblNewLabel);

		bBuscar = new JButton("Buscar");
		bBuscar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		bBuscar.setBounds(300, 250, 100, 30);
		getRootPane().setDefaultButton(bBuscar);
		bBuscar.addActionListener(this);
		contentPane.add(bBuscar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCancelar.setBounds(420, 250, 100, 30);
		btnCancelar.addActionListener(this);
		contentPane.add(btnCancelar);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setBounds(320, 150, 150, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		String s[] = {"Dar Tarjeta Basic","Dar Tarjeta Gold"};
		comboBox = new JComboBox(s);
		comboBox.setBounds(320, 191, 150, 30);
		contentPane.add(comboBox);

		JLabel fondo = new JLabel();
		fondo.setBounds(0, 0, getWidth(), getHeight());
		ImageIcon fCine = new ImageIcon("Imagenes/fondoPrincipal.png");
		fondo.setIcon(new ImageIcon(fCine.getImage().getScaledInstance(fondo.getWidth(), fondo.getHeight(), Image.SCALE_SMOOTH)));
		contentPane.add(fondo);

	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==bBuscar) {
			String cedu = textField.getText();
			textField.setText("");
			String msjCombo = (String) comboBox.getSelectedItem();
			if(msjCombo.equals("Dar Tarjeta Basic")) {
				darTarjCliente("Basic",true,cedu);
			}
			if(msjCombo.equals("Dar Tarjeta Gold")) {
				darTarjCliente("Gold",true,cedu);
			}

			Aplicacion.setVisible(true);
			setVisible(false);
		}
		if(e.getSource()==btnCancelar) {
			Aplicacion.setVisible(true);
			setVisible(false);

		}

	}
	public void darTarjCliente(String que,boolean valor,String cedu) {
		ProcesosCliente ProcesosCliente = new ProcesosCliente();
		ProcesosCliente.darTarjCliente(que,valor,cedu);
	}
}
