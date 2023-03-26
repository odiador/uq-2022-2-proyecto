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
import model.data;
import javax.swing.JComboBox;


public class HistorialDeCompras extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField tfCedu;
	private JButton bAceptar;
	private JButton bCancelar;
	private data data;
	private Aplicacion aplicacion;

	public HistorialDeCompras(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;
		data = new data();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 300);
		setLocationRelativeTo(null);
		setResizable(false);

		ImageIcon crispIcon = new ImageIcon("Imagenes/crispetast.png");
		setIconImage(crispIcon.getImage());
		setTitle("Stellar Cinema - Historial de Compras");

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.setBackground(new Color (0,0,0));
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
		bCancelar.setBounds(298, 190, 150, 40);
		bCancelar.addActionListener(this);
		contentPane.add(bCancelar);


		bAceptar = new JButton("Aceptar");
		//boton default
		getRootPane().setDefaultButton(bAceptar);
		bAceptar.addActionListener(this);
		bAceptar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		bAceptar.setBounds(138, 190, 150, 40);
		bAceptar.setBackground(new Color(255, 255, 255));
		getRootPane().setDefaultButton(bAceptar);
		bAceptar.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(bAceptar);

		String[] x = {"Boleta","Confitería"};

	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==bAceptar) {
			String aux = tfCedu.getText();
			if(buscarCliente(aux)) {
				data = new data();
				MostrarHistorialCliente MostrarHistorialCliente = new MostrarHistorialCliente(data.lista,aux,aplicacion);
				MostrarHistorialCliente.setVisible(true);
				setVisible(false);
			} else {
				JOptionPane.showMessageDialog(null, "No se encontró la persona");
			}
		}
		if(e.getSource()==bCancelar) {
			aplicacion.setVisible(true);
			setVisible(false);
		}

	}
	public boolean buscarCliente (String cedu) {
		data = new data();
		boolean x = false;
		for(int i = 0;i<data.lista.size()&&!x;i++) {
			if(((String)(data.lista.get(i).get(1))).equals(cedu)) {
				x = true;
			}
		}
		return x;
	}
}
