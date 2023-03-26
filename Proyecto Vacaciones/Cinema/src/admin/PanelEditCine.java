package admin;

import static config.Utilities.getIdCine;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import custom.CustomListener;
import custom.NumberTextField;
import custom.Panel;
import custom.Text;
import custom.TextField;
import objects.Cinema;
import objects.CustomException;
import tools.TablaCinema;

public class PanelEditCine extends Panel {

	private NumberTextField tfWidth, tfHeight;
	private NumberTextField tfDiaCine, tfMesCine, tfAnioCine, tfHoraCine, tfMinutoCine;
	private TextField tfName;
	private Text bAceptar, bVolver;
	private PanelDinamicoAdmin panelDinamicoAdmin;
	private Text lblDiaPrevio, lblMaxCine;
	private Text bVistaPrevia;

	public PanelEditCine(PanelDinamicoAdmin panelDinamicoAdmin) {

		setPanelDinamicoAdmin(panelDinamicoAdmin);
		Cinema c = new Cinema(getIdCine(), "Offline");
		try {
			c = TablaCinema.getCinemaById(getIdCine());
		} catch (SQLException | CustomException e) {}

		lblMaxCine = new Text();
		lblDiaPrevio = new Text();
		setTfDiaCine(new NumberTextField(31));
		setTfMesCine(new NumberTextField(12));
		setTfAnioCine(new NumberTextField(9999));
		setTfHoraCine(new NumberTextField(23));
		setTfMinutoCine(new NumberTextField(59));

		setTfName(new TextField("Escribe el nombre del cine"));
		if (!c.getName().equals("Offline")) {
			LocalDateTime l = c.getMomentoCine();
			getTfName().setText(c.getName());
			getTfAnioCine().setText(l.getYear() + "");
			getTfMesCine().setText(l.getMonthValue() + "");
			getTfDiaCine().setText(l.getDayOfMonth() + "");
			getTfHoraCine().setText(l.getHour() + "");
			getTfMinutoCine().setText(l.getMinute() + "");
		}
		setTfWidth(new NumberTextField(99, c.getWidth() + ""));
		setTfHeight(new NumberTextField(99, c.getHeight() + ""));

		actualizarLblMomento();
		actualizarLblCine();

		setbAceptar(new Text("Aceptar"));
		setbVolver(new Text("Volver"));
		setbVistaPrevia(new Text("Vista Previa"));

		lblDiaPrevio.setOpaque(true);
		lblMaxCine.setOpaque(true);

		lblDiaPrevio.setBackground(defaultColor.brighter());
		lblMaxCine.setBackground(defaultColor.brighter());

		getbAceptar().configAsButton(e -> editarCine());
		getbVolver().configAsButton(e -> getPanelDinamicoAdmin().goToPrincipalCine());
		getbVistaPrevia().configAsButton(e -> irAVistaPrevia());

		getTfWidth().addTextfieldListener(e -> actualizarLblCine());
		getTfHeight().addTextfieldListener(e -> actualizarLblCine());
		getTfDiaCine().addTextfieldListener(e -> actualizarLblMomento());
		getTfMesCine().addTextfieldListener(e -> actualizarLblMomento());
		getTfAnioCine().addTextfieldListener(e -> actualizarLblMomento());
		getTfHoraCine().addTextfieldListener(e -> actualizarLblMomento());
		getTfMinutoCine().addTextfieldListener(e -> actualizarLblMomento());

		getTfDiaCine().setHorizontalAllignment(SwingConstants.CENTER);
		getTfMesCine().setHorizontalAllignment(SwingConstants.CENTER);
		getTfAnioCine().setHorizontalAllignment(SwingConstants.CENTER);
		getTfHoraCine().setHorizontalAllignment(SwingConstants.CENTER);
		getTfMinutoCine().setHorizontalAllignment(SwingConstants.CENTER);

		getTfName().setPreferredSize(400, 40);
		getTfWidth().setPreferredSize(400, 40);
		getTfHeight().setPreferredSize(400, 40);
		getbAceptar().setPreferredSize(400, 40);
		getTfDiaCine().setPreferredSize(133, 40);
		getTfMesCine().setPreferredSize(133, 40);
		getTfAnioCine().setPreferredSize(133, 40);
		getTfHoraCine().setPreferredSize(133, 40);
		getTfMinutoCine().setPreferredSize(133, 40);

		Panel panelContenedor = new Panel();
		panelContenedor.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 0, 5, 0);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridwidth = 3;

		gbc.gridx = 0;
		gbc.gridy = 0;
		panelContenedor.add(new Text("Escribe el nombre del cine", SwingConstants.LEFT), gbc);

		gbc.gridy = 1;
		panelContenedor.add(getTfName(), gbc);

		gbc.gridy = 2;
		panelContenedor.add(new Text("Escribe el ancho del cine", SwingConstants.LEFT), gbc);

		gbc.gridy = 3;
		panelContenedor.add(getTfWidth(), gbc);

		gbc.gridy = 4;
		panelContenedor.add(new Text("Escribe el alto del cine", SwingConstants.LEFT), gbc);

		gbc.gridy = 5;
		panelContenedor.add(getTfHeight(), gbc);

		gbc.gridy = 6;
		panelContenedor.add(new Text("Escribe el día de cine (DD/MM/AAAA)", SwingConstants.LEFT), gbc);

		gbc.gridy = 7;
		gbc.gridwidth = 1;

		Panel panelConSeparacion = new Panel();
		panelConSeparacion.add(getTfDiaCine());
		panelConSeparacion.add(new Text(" / "), BorderLayout.EAST);

		panelContenedor.add(panelConSeparacion, gbc);
		gbc.gridx = 1;

		panelConSeparacion = new Panel();
		panelConSeparacion.add(getTfMesCine());
		panelConSeparacion.add(new Text(" / "), BorderLayout.EAST);

		panelContenedor.add(panelConSeparacion, gbc);
		gbc.gridx = 2;
		panelContenedor.add(getTfAnioCine(), gbc);
		gbc.gridy = 8;
		gbc.gridx = 0;
		gbc.gridwidth = 3;
		panelContenedor.add(new Text("Escribe la hora del cine (HH:MM)", SwingConstants.LEFT), gbc);
		gbc.gridy = 9;
		gbc.gridwidth = 1;

		panelConSeparacion = new Panel();
		panelConSeparacion.add(getTfHoraCine());
		panelConSeparacion.add(new Text(" : "), BorderLayout.EAST);

		panelContenedor.add(panelConSeparacion, gbc);
		gbc.gridx = 1;
		panelContenedor.add(getTfMinutoCine(), gbc);
		gbc.gridwidth = 3;
		gbc.gridx = 0;
		gbc.gridy = 10;
		panelContenedor.add(lblDiaPrevio, gbc);
		gbc.gridy = 11;
		panelContenedor.add(lblMaxCine, gbc);

		Panel panelContenedor2 = new Panel();
		panelContenedor2.setLayout(new GridLayout(1, 3));
		panelContenedor2.add(getbVolver());
		panelContenedor2.add(getbVistaPrevia());
		panelContenedor2.add(getbAceptar());

		add(panelContenedor);
		add(panelContenedor2, BorderLayout.SOUTH);

	}

	private void actualizarLblCine () {
		int j = getTfWidth().getIntNumber();
		int i = getTfHeight().getIntNumber();
		if (i == 0 || j == 0) {
			lblMaxCine.setText("Silla maxima: ?");
			return;
		}
		char caracter = (char) ('A' + i - 1);
		lblMaxCine.setText("Silla maxima: " + caracter + "" + j);
	}

	private void actualizarLblMomento () {
		String anio = getTfAnioCine().getIntNumber() + "";
		String mes = getTfMesCine().getIntNumber() < 10 ? "0" + getTfMesCine().getIntNumber()
				: getTfMesCine().getIntNumber() + "";
		String dia = getTfDiaCine().getIntNumber() < 10 ? "0" + getTfDiaCine().getIntNumber()
				: getTfDiaCine().getIntNumber() + "";
		String hora = getTfHoraCine().getIntNumber() < 10 ? "0" + getTfHoraCine().getIntNumber()
				: getTfHoraCine().getIntNumber() + "";
		String minuto = getTfMinutoCine().getIntNumber() < 10 ? "0" + getTfMinutoCine().getIntNumber()
				: getTfMinutoCine().getIntNumber() + "";
		try {
			LocalDateTime.parse(anio + "-" + mes + "-" + dia + "T" + hora + ":" + minuto);
		} catch (DateTimeParseException e) {
			lblDiaPrevio.setText("Hora: ?");
			return;
		}

		int horaInt = Integer.parseInt(hora);
		if (horaInt < 12) lblDiaPrevio
				.setText("Hora: " + anio + "/" + mes + "/" + dia + ", " + hora + ":" + minuto + " a. m.");
		else {
			if (horaInt != 12) horaInt -= 12;
			lblDiaPrevio.setText("Hora: " + anio + "/" + mes + "/" + dia + ", "
					+ (horaInt < 10 ? "0" + horaInt : "" + horaInt) + ":" + minuto + " p. m.");
		}
	}

	private void irAVistaPrevia () {
		getbVistaPrevia().setBackground(defaultColor.brighter());
		int width = getTfWidth().getIntNumber();
		int height = getTfHeight().getIntNumber();
		if (width == 0 || height == 0) {
			JOptionPane.showMessageDialog(null,
					"<html><center><font color = #FF0000>Ninguna cantidad puede ser 0</font></center></html>");
			return;
		}
		getPanelDinamicoAdmin().goToVistaPreviaEditCine(width, height);
	}

	private void editarCine () {
		int width = getTfWidth().getIntNumber();
		int height = getTfHeight().getIntNumber();
		LocalDateTime time;
		try {
			time = LocalDateTime.of(getTfAnioCine().getIntNumber(), getTfMesCine().getIntNumber(),
					getTfDiaCine().getIntNumber(), getTfHoraCine().getIntNumber(), getTfMinutoCine().getIntNumber());
		} catch (DateTimeException e1) {
			JOptionPane.showMessageDialog(null,
					"<html><center><font color = #0000FF>Escribe bien el momento del cine</font></center></html>");
			return;
		}

		try {
			TablaCinema.changeNameofCinema(getIdCine(), getTfName().getText());
			TablaCinema.changeDimensionsofCinema(getIdCine(), width, height);
			TablaCinema.changeMomentofCinema(getIdCine(), time);
			for (CustomListener l : getPanelDinamicoAdmin().getCustomListeners()) l.accionRealizada();
			JOptionPane.showMessageDialog(null,
					"<html><center><font color = #0000FF>El cine ha sido actualizado</font></center></html>");

		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null,
					"<html><center><font color = #FF0000>" + e1 + "</font></center></html>");
		} catch (CustomException e1) {
			JOptionPane.showMessageDialog(null,
					"<html><center><font color = #FF0000>" + e1.getCausa() + "</font></center></html>");
		}
	}

	public Text getbVolver () {
		return bVolver;
	}

	public void setbVolver (Text bVolver) {
		this.bVolver = bVolver;
	}

	public NumberTextField getTfWidth () {
		return tfWidth;
	}

	public void setTfWidth (NumberTextField tfWidth) {
		this.tfWidth = tfWidth;
	}

	public NumberTextField getTfHeight () {
		return tfHeight;
	}

	public void setTfHeight (NumberTextField tfHeight) {
		this.tfHeight = tfHeight;
	}

	public Text getbAceptar () {
		return bAceptar;
	}

	public void setbAceptar (Text bAceptar) {
		this.bAceptar = bAceptar;
	}

	public PanelDinamicoAdmin getPanelDinamicoAdmin () {
		return panelDinamicoAdmin;
	}

	public void setPanelDinamicoAdmin (PanelDinamicoAdmin panelDinamicoAdmin) {
		this.panelDinamicoAdmin = panelDinamicoAdmin;
	}

	public NumberTextField getTfDiaCine () {
		return tfDiaCine;
	}

	public void setTfDiaCine (NumberTextField tfDiaCine) {
		this.tfDiaCine = tfDiaCine;
	}

	public NumberTextField getTfMesCine () {
		return tfMesCine;
	}

	public void setTfMesCine (NumberTextField tfMesCine) {
		this.tfMesCine = tfMesCine;
	}

	public NumberTextField getTfAnioCine () {
		return tfAnioCine;
	}

	public void setTfAnioCine (NumberTextField tfAnioCine) {
		this.tfAnioCine = tfAnioCine;
	}

	public NumberTextField getTfHoraCine () {
		return tfHoraCine;
	}

	public void setTfHoraCine (NumberTextField tfHoraCine) {
		this.tfHoraCine = tfHoraCine;
	}

	public NumberTextField getTfMinutoCine () {
		return tfMinutoCine;
	}

	public void setTfMinutoCine (NumberTextField tfMinutoCine) {
		this.tfMinutoCine = tfMinutoCine;
	}

	public TextField getTfName () {
		return tfName;
	}

	public void setTfName (TextField tfName) {
		this.tfName = tfName;
	}

	public Text getbVistaPrevia () {
		return bVistaPrevia;
	}

	public void setbVistaPrevia (Text bVistaPrevia) {
		this.bVistaPrevia = bVistaPrevia;
	}

}
