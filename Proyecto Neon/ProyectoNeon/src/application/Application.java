package application;

import view.Template;

public class Application extends Template {

	public static void main(String[] args) {
		new Application().setVisible(true);
	}

	public Application() {

	}

	@Override
	public void configSize() {
		setSize(300, 300);
	}

}
