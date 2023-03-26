package admin;

import custom.Panel;
import custom.Plantilla;

public class PanelDinamicoAdmin extends Panel {
	private static final class PlantillaExtension extends Plantilla {
		@Override
		public void configurarTam () {
			setSize(1000, 600);
		}
	}

	public static void main (String[] args) {
		Plantilla p = new PlantillaExtension();
		p.agregarBody(new PanelDinamicoAdmin());
		p.setVisible(true);
	}

	private Panel tempPanel;

	public PanelDinamicoAdmin() {
		goToPrincipal();
	}

	public void goToPrincipal () {
		removeAll();
		add(new PanelAdmin(this));
		revalidate();
	}

	public void goToPrincipalBase () {
		removeAll();
		add(new PanelAdminBase(this));
		revalidate();
	}

	public void goToPrincipalCine () {
		removeAll();
		add(new PanelAdminCine(this));
		revalidate();
	}

	public void goToEditCine () {
		removeAll();
		add(new PanelEditCine(this));
		revalidate();
	}

	public void goToVistaPreviaEditCine (int width, int height) {
		setTempPanel((Panel) getComponents()[0]);
		removeAll();
		add(new PanelPreviaEditCine(width, height, this));
		revalidate();
	}

	public void volverDePreviaEditCine () {
		removeAll();
		add(getTempPanel());
		repaint();
		revalidate();
	}

	public void goToChangeCine () {
		removeAll();
		add(new PanelChangeCine(this));
		revalidate();
	}

	public Panel getTempPanel () {
		return tempPanel;
	}

	public void setTempPanel (Panel tempPanel) {
		this.tempPanel = tempPanel;
	}

}
