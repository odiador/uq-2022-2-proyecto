package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Panel extends Componente {
	private List<CustomListener> listeners = new ArrayList<CustomListener>();

	public Panel(Color c, int hgap, int vgap) {
		setOpaque(true);
		setLayout(new BorderLayout(hgap, vgap));
		setBackground(c);
	}

	public void ejecutarListeners() {
		for (CustomListener l : getCustomListeners())
			l.accionRealizada();
	}

	public void addCustomListener(CustomListener l) {
		getCustomListeners().add(l);
	}

	public void removeCustomListeners() {
		getCustomListeners().clear();
	}

	public Panel(Color c) {
		this(c, 0, 0);
	}

	public Panel() {
		this(Componente.defaultColor);
	}

	public List<CustomListener> getCustomListeners() {
		return listeners;
	}
}
