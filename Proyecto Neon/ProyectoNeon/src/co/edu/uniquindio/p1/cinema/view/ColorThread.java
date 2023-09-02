package co.edu.uniquindio.p1.cinema.view;

import co.edu.uniquindio.p1.cinema.objetos.CLabel;
import co.edu.uniquindio.p1.cinema.services.ColorManagement;

public class ColorThread extends Thread {
	private CLabel label;

	public ColorThread(CLabel label) {
		this.label = label;
	}

	@Override
	public void run() {
		while (true) {
			try {
				ColorManagement.updateColor();
				label.setForeground(ColorManagement.rgbColor);
				sleep(25);
			} catch (InterruptedException e) {
				interrupt();
				break;
			}
		}
	}
}
