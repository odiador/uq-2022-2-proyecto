package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import model.ProcesosCliente;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
public class BuscarCliente extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JLabel tTitulo;
	private JTextField textField;
	private JButton bBuscar;
	private JButton btnCancelar;
	private Aplicacion aplicacion;


	public BuscarCliente(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 570, 350);
		setLocationRelativeTo(this);
		setBackground(new Color(0));


		ImageIcon crispIcon = new ImageIcon("Imagenes/crispetast.png");
		setIconImage(crispIcon.getImage());
		setTitle("Stellar Cinema - Buscar Cliente");

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(getBackground());
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel title = new JLabel();
		ImageIcon tituloCine = new ImageIcon("Imagenes/ttlCine.png");
		title.setBounds(55, 50, 460, 55);
		title.setIcon(new ImageIcon(tituloCine.getImage().getScaledInstance(title.getWidth(), title.getHeight(), Image.SCALE_SMOOTH)));
		contentPane.add(title);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setBounds(320, 150, 150, 30);
		contentPane.add(textField);
		textField.setColumns(10);

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

		JLabel fondo = new JLabel();
		fondo.setBounds(0, 0, getWidth(), getHeight());
		ImageIcon fCine = new ImageIcon("Imagenes/fondoPrincipal.png");
		fondo.setIcon(new ImageIcon(fCine.getImage().getScaledInstance(fondo.getWidth(), fondo.getHeight(), Image.SCALE_SMOOTH)));
		contentPane.add(fondo);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnCancelar) {
			aplicacion.setVisible(true);
			setVisible(false);
		}
		if(e.getSource()==bBuscar) {
			String cedu = textField.getText();
			textField.setText("");
			ArrayList<Object> res = buscarCliente(cedu);
			if(res.size()!=0) {
				JOptionPane.showMessageDialog(null, "Si se encontro la persona:\n"+res);
			} else {
				JOptionPane.showMessageDialog(null, "No se encontro nada");
			}
			aplicacion.setVisible(true);
			setVisible(false);
		}
	}
	public ArrayList<Object> buscarCliente(String cedu) {
		ProcesosCliente ProcesosCliente = new ProcesosCliente();
		return ProcesosCliente.buscarCliente(cedu);
	}
}
