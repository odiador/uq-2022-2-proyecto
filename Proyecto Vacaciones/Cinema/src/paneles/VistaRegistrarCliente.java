package paneles;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import custom.ClicListener;
import custom.Evento;
import custom.NumberTextField;
import custom.Panel;
import custom.Text;
import custom.TextField;
import tools.TablaClientes;

public class VistaRegistrarCliente extends Panel implements ClicListener {

	private Text textoName, textoCedu, botonAceptar;
	private TextField textfieldName;
	private NumberTextField textfieldCedu;

	public VistaRegistrarCliente() {
		setTextoName(new Text("Ingresa un nombre: "));
		setTextoCedu(new Text("Ingresa una cédula: "));
		setTextfieldName(new TextField("Escribe un nombre"));
		setTextfieldCedu(new NumberTextField(9999999999d));
		setBotonAceptar(new Text("Agregar"));

		getTextoName().setHorizontalAlignment(SwingConstants.LEFT);
		getTextoCedu().setHorizontalAlignment(SwingConstants.LEFT);

		getBotonAceptar().setOpaque(true);
		getBotonAceptar().addClicListener(this);
		getBotonAceptar().setCursor(new Cursor(Cursor.HAND_CURSOR));
		getBotonAceptar().setCanBeSelectioned(true);
		getBotonAceptar().setBackground(Text.brighter);

		getTextfieldName().setPreferredSize(400, 40);
		getTextfieldCedu().setPreferredSize(400, 40);
		getBotonAceptar().setPreferredSize(400, 40);

		Panel principal = new Panel();
		principal.setLayout(new GridBagLayout());
		setLayout(new BorderLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 0, 10, 0);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		principal.add(getTextoName(), gbc);

		gbc.gridy = 1;
		principal.add(getTextfieldName(), gbc);

		gbc.gridy = 2;
		principal.add(getTextoCedu(), gbc);

		gbc.gridy = 3;
		principal.add(getTextfieldCedu(), gbc);

		add(principal, BorderLayout.CENTER);
		add(getBotonAceptar(), BorderLayout.SOUTH);
	}

	public Text getTextoName () {
		return textoName;
	}

	public void setTextoName (Text textoName) {
		this.textoName = textoName;
	}

	public Text getTextoCedu () {
		return textoCedu;
	}

	public void setTextoCedu (Text textoCedu) {
		this.textoCedu = textoCedu;
	}

	public TextField getTextfieldName () {
		return textfieldName;
	}

	public void setTextfieldName (TextField textfieldCantidad) {
		this.textfieldName = textfieldCantidad;
	}

	public NumberTextField getTextfieldCedu () {
		return textfieldCedu;
	}

	public void setTextfieldCedu (NumberTextField textfieldCedu) {
		this.textfieldCedu = textfieldCedu;
	}

	public Text getBotonAceptar () {
		return botonAceptar;
	}

	public void setBotonAceptar (Text botonAceptar) {
		this.botonAceptar = botonAceptar;
	}

	public static void imprimir (String info) {
		JOptionPane.showMessageDialog(null, info);
	}

	@Override
	public void botonPresionado (Evento e) {
		String name = getTextfieldName().getText();
		if (name == null) {
			imprimir("Escribe un nombre");
			return;
		}
		try {
			TablaClientes.agregarCliente(name.trim(), getTextfieldCedu().getNumber());
			imprimir("Persona agregada");
		} catch (SQLException e1) {
			int error = e1.getErrorCode();
			String errorString = (error == 0) ? "No hay conexión con el servidor"
					: (error == 1062) ? "No se admiten cédulas repetidas" : "Valor Inesperado: " + error;
			imprimir(errorString);
		}
	}
}
