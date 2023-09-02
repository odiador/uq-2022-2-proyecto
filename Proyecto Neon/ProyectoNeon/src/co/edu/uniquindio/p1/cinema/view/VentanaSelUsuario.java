package co.edu.uniquindio.p1.cinema.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import co.edu.uniquindio.p1.cinema.controller.ColorManagement;
import co.edu.uniquindio.p1.cinema.controller.Data;
import co.edu.uniquindio.p1.cinema.model.Herramientas;
import co.edu.uniquindio.p1.cinema.objetos.CLabel;
import co.edu.uniquindio.p1.cinema.objetos.CustomTextfield;

public class VentanaSelUsuario extends Template {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static VentanaSelUsuario instance;
	private CustomTextfield tfCliente;
	private JPanel panelCentral, panel;
	private CLabel txtSeleccionaId, btnSiguiente;

	public static VentanaSelUsuario getInstance() {
		return instance;
	}

	public VentanaSelUsuario(int width, int height) {
		super(width, height);
		instance = this;
		initComponents();
		configComponents();
		setLayouts();
	}

	private void setLayouts() {
		designCenter();
		panel.add(panelCentral, BorderLayout.CENTER);
		panel.add(btnSiguiente, BorderLayout.SOUTH);
		setCenter(panel);
	}

	private void designCenter() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelCentral.add(txtSeleccionaId, gbc);
		gbc.gridy++;
		panelCentral.add(tfCliente, gbc);
	}

	private void configComponents() {
		setFonts();
		setDesigns();
		btnSiguiente.setOnClicAction(() -> btnSiguienteAction());
	}

	private void setDesigns() {
		panelCentral.setOpaque(false);
		panel.setOpaque(false);

		txtSeleccionaId.setForeground(ColorManagement.rgbColor);
		panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
		btnSiguiente.setBorder(BorderFactory.createLineBorder(ColorManagement.rgbColor));
		txtSeleccionaId.setHorizontalAlignment(0);
		btnSiguiente.setHorizontalAlignment(0);
		btnSiguiente.setBackground(Herramientas.black);
		btnSiguiente.setForeground(ColorManagement.rgbColor);
		ToolKit.configureButtonHover(btnSiguiente);
	}

	private void setFonts() {
		Font font = Herramientas.FUENTE_COOLVETICA.deriveFont(25f);
		tfCliente.setFont(font);
		txtSeleccionaId.setFont(font);
		btnSiguiente.setFont(font);
	}

	private void btnSiguienteAction() {
		if (tfCliente.getText().trim().isEmpty()) {
			Herramientas.abrirPopUp("Informacion", "Escribe un documento de identidad");
			return;
		}
		Data data = new Data();
		ArrayList<Object> cliente = data.buscarCliente(tfCliente.getText());
		if (cliente.isEmpty()) {
			Herramientas.abrirPopUp("Informacion", "El cliente no fue encontrado");
			return;
		}
		VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(1000, 720);
		ventanaPrincipal.setVisible(true);
		setVisible(false);
	}

	private void initComponents() {
		panelCentral = new JPanel(new GridBagLayout());
		panel = new JPanel(new BorderLayout());
		tfCliente = new CustomTextfield(Herramientas.setFormatodeNumero(0d, 9999999999d));
		btnSiguiente = new CLabel("Siguiente");
		txtSeleccionaId = new CLabel("Escribe tu documento de identificacion");
	}

	@Override
	public void updateStellarColors() {
		txtSeleccionaId.setForeground(ColorManagement.rgbColor);
		tfCliente.actualizarColor(ColorManagement.rgbColor);
	}

}
