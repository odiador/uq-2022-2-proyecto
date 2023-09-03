package co.edu.uniquindio.p1.cinema.view;

import java.awt.Dimension;

import javax.swing.BorderFactory;

import co.edu.uniquindio.p1.cinema.objetos.CLabel;
import co.edu.uniquindio.p1.cinema.services.ColorManagement;
import co.edu.uniquindio.p1.cinema.services.Herramientas;

public class ToolKit {

	static void configureButtonHover(final CLabel label) {
		label.setOpaque(true);
		label.setOnMouseEnteredAction(() -> {
			label.setBackground(ColorManagement.rgbColor);
			label.setForeground(Herramientas.black);
		});
		label.setOnMouseExitedAction(() -> {
			label.setBackground(Herramientas.black);
			label.setForeground(ColorManagement.rgbColor);
		});
	}

	static void configureButtonHoverWithBorder(final CLabel label) {
		label.setOpaque(true);
		label.setBackground(Herramientas.black);
		label.setForeground(ColorManagement.rgbColor);
		label.setOnMouseEnteredAction(() -> {
			label.setBorder(BorderFactory.createLineBorder(ColorManagement.rgbColor));
			label.setBackground(ColorManagement.rgbColor);
			label.setForeground(Herramientas.black);
		});
		label.setOnMouseExitedAction(() -> {
			label.setBorder(BorderFactory.createLineBorder(ColorManagement.rgbColor));
			label.setBackground(Herramientas.black);
			label.setForeground(ColorManagement.rgbColor);
		});
	}

	static void configureButtonHover(final CLabel label, Runnable eventHover, Runnable eventUnhover) {
		label.setOnMouseEnteredAction(eventHover);
		label.setOnMouseExitedAction(eventUnhover);

	}

	static CLabel initSuperiorButton(final String text) {
		final CLabel label = new CLabel(text);
		label.setOpaque(true);
		label.setHorizontalAlignment(CLabel.CENTER);
		label.setFont(Herramientas.FUENTE_PIRANA);
		label.setBackground(Herramientas.black);
		label.setVerticalAlignment(CLabel.CENTER);
		label.setForeground(ColorManagement.rgbColor);
		label.setPreferredSize(new Dimension(60, 25));
		label.setMinimumSize(new Dimension(60, 25));
		return label;
	}

}
