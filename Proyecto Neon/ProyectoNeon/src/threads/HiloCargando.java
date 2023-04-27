package threads;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class HiloCargando extends Thread {
	private JLabel label;
	private String mensaje;
	private JPanel panel;
	private String cadena;
	private int punt = 1;

	public HiloCargando(JPanel panel, JLabel label, String cadena) {
		setCadena(cadena);
		setMensaje(label.getText());
		setLabel(label);
		setPanel(panel);
	}

	@SuppressWarnings("deprecation")
	public void run() {
		while (true) {
			label.setText(cadena + getPuntos());
			label.update(label.getGraphics());
			panel.update(panel.getGraphics());
			if (punt < 3) {
				punt++;
			} else {
				punt = 1;
			}
			try {
				sleep(100);
			} catch (InterruptedException e) {
				label.setText(mensaje);
				stop();
			}
		}
	}

	public String getPuntos() {
		String msg = "";
		for (int i = 0; i < punt; i++) {
			msg += ".";
		}
		return msg;
	}

	private void setCadena(String cadena) {
		this.cadena = cadena;
	}

	private void setLabel(JLabel label) {
		this.label = label;
	}

	private void setPanel(JPanel panel) {
		this.panel = panel;
	}

	private void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}
