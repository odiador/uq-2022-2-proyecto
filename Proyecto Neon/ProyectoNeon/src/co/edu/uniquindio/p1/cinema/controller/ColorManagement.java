package co.edu.uniquindio.p1.cinema.controller;

import java.awt.Color;

public class ColorManagement {
	public static int sentidoColor = 0;
	public static Color rgbColor = new Color(175, 255, 175);

	public static void updateColor() {
		try {
			switch (sentidoColor) {
			case 0:
				if (rgbColor.getGreen() != 255) {
					rgbColor = new Color(rgbColor.getRed(),
							rgbColor.getGreen() + 10, rgbColor.getBlue());
				} else
					sentidoColor++;
				break;
			case 1:
				if (rgbColor.getRed() != 175) {
					rgbColor = new Color(rgbColor.getRed() - 10,
							rgbColor.getGreen(), rgbColor.getBlue());
				} else
					sentidoColor++;
				break;
			case 2:
				if (rgbColor.getBlue() != 255) {
					rgbColor = new Color(rgbColor.getRed(),
							rgbColor.getGreen(), rgbColor.getBlue() + 10);
				} else
					sentidoColor++;
				break;
			case 3:
				if (rgbColor.getGreen() != 175) {
					rgbColor = new Color(rgbColor.getRed(),
							rgbColor.getGreen() - 10, rgbColor.getBlue());
				} else
					sentidoColor++;
				break;
			case 4:
				if (rgbColor.getRed() != 255) {
					rgbColor = new Color(rgbColor.getRed() + 10,
							rgbColor.getGreen(), rgbColor.getBlue());
				} else
					sentidoColor++;
				break;
			case 5:
				if (rgbColor.getBlue() != 175) {
					rgbColor = new Color(rgbColor.getRed(),
							rgbColor.getGreen(), rgbColor.getBlue() - 10);
				} else
					sentidoColor = 0;
				break;
			}
		} catch (Exception e) {
			rgbColor = new Color(175, 255, 175);
		}
	}
}
