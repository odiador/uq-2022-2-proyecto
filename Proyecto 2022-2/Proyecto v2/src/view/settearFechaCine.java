package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.data;

public class settearFechaCine extends JFrame implements ActionListener {

	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private int diaCine,diaCompra;
	private LocalDate fechaCine, fechaCompra;

	public static void main (String args[]) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					settearFechaCine frame = new settearFechaCine();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public settearFechaCine() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0,500,400);
		setLocationRelativeTo(null);
		setResizable(false);
		JPanel contentPane = new JPanel();
		getContentPane().add(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(0, 0, 0));

		textField = new JTextField();
		textField.setBounds(33, 101, 96, 19);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(151, 101, 96, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(265, 101, 96, 19);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		btnNewButton = new JButton("Accept");
		btnNewButton.setBounds(317, 221, 85, 21);
		btnNewButton.addActionListener(this);
		contentPane.add(btnNewButton);

		btnNewButton_1 = new JButton("Get Dif");
		btnNewButton_1.setBounds(222, 221, 85, 21);
		btnNewButton_1.addActionListener(this);
		contentPane.add(btnNewButton_1);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnNewButton) {
			fechaCompra = LocalDate.now();
			diaCompra = fechaCompra.getDayOfYear();
			fechaCine = LocalDate.of
					(Integer.parseInt(textField.getText()),
					Integer.parseInt(textField_1.getText()),
					Integer.parseInt(textField_2.getText()));
			diaCine = fechaCine.getDayOfYear();
		}
		if(e.getSource()==btnNewButton_1) {
			new data().fechaCine = fechaCine;
		}
	}
}
