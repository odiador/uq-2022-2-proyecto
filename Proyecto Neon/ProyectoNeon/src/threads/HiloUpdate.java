package threads;

import javax.swing.JComponent;

public class HiloUpdate extends Thread {
	JComponent c;

	public HiloUpdate(JComponent c) {
		this.c = c;
	}
	@SuppressWarnings("deprecation")
	public void run() {
		while (true) {
			System.out.print("");
			c.revalidate();
			try {
				sleep(30);
			} catch (InterruptedException e) {
				stop();
			}
		}

	}
}
