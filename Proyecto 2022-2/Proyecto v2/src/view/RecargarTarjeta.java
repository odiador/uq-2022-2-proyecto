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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import model.ProcesosCliente;
import model.data;

public class RecargarTarjeta extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JLabel tTitulo;
	private JTextField tfCedu;
	private JTextField tfValor;
	private JButton bBuscar;
	private JButton btnCancelar;
	private Aplicacion Aplicacion;


	public RecargarTarjeta(Aplicacion Aplicacion) {
		this.Aplicacion = Aplicacion;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 570, 350);
		setLocationRelativeTo(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		ImageIcon crispIcon = new ImageIcon("Imagenes/crispetast.png");
		setIconImage(crispIcon.getImage());
		setTitle("Stellar Cinema - Recargar tarjeta a Cliente");

		JLabel title = new JLabel();
		ImageIcon tituloCine = new ImageIcon("Imagenes/ttlCine.png");
		title.setBounds(55, 50, 460, 55);
		title.setIcon(new ImageIcon(tituloCine.getImage().getScaledInstance(title.getWidth(), title.getHeight(), Image.SCALE_SMOOTH)));
		contentPane.add(title);

		tfCedu = new JTextField();
		tfCedu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfCedu.setBounds(320, 150, 150, 30);
		contentPane.add(tfCedu);
		tfCedu.setColumns(10);

		JLabel lblNewLabel = new JLabel("Ingrese la cedula de la persona");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(45, 150, 250, 30);
		contentPane.add(lblNewLabel);

		JLabel tValor = new JLabel("Ingrese el valor a recargar");
		tValor.setHorizontalAlignment(SwingConstants.RIGHT);
		tValor.setForeground(Color.WHITE);
		tValor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tValor.setBounds(45, 200, 250, 30);
		contentPane.add(tValor);

		tfValor = new JTextField();
		tfValor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfValor.setColumns(10);
		tfValor.setBounds(320, 200, 150, 30);
		contentPane.add(tfValor);

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

		JLabel fondo = new JLabel();
		fondo.setBounds(0, 0, getWidth(), getHeight());
		ImageIcon fCine = new ImageIcon("Imagenes/fondoPrincipal.png");
		fondo.setIcon(new ImageIcon(fCine.getImage().getScaledInstance(fondo.getWidth(), fondo.getHeight(), Image.SCALE_SMOOTH)));
		contentPane.add(fondo);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==bBuscar) {
			boolean hayError = false;
			if(!(tfCedu.getText().equals("")||tfValor.getText().equals(""))) {
				String cedu = tfCedu.getText();
				try {

					double val = Double.parseDouble(tfValor.getText());
					hayError = recargarTarjeta(cedu,val);
				}
				catch (Exception error) {
					hayError = true;
				}
			} else {
				JOptionPane.showMessageDialog(null, "Ingresa bien los datos","Error",JOptionPane.ERROR_MESSAGE);
				hayError = true;
			}
			if(!hayError) {
			Aplicacion.setVisible(true);
			setVisible(false);
			}
		}
		if(e.getSource()==btnCancelar) {
			Aplicacion.setVisible(true);
			setVisible(false);
		}
	}
	public boolean recargarTarjeta(String cedu, double valor) {
		ProcesosCliente ProcesosCliente = new ProcesosCliente();
		return ProcesosCliente.recargarTarjeta(cedu, valor);

	}
}
