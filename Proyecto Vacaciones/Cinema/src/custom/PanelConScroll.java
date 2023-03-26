package custom;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;

public class PanelConScroll extends JScrollPane {
	public PanelConScroll(Panel p) {
		super(p, VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_NEVER);
		getVerticalScrollBar().setUI(new Scroll_Bar());
		setBorder(BorderFactory.createEmptyBorder());
	}

	public static PanelConScroll crearScrollVertical (Panel p) {
		return new PanelConScroll(p);
	}
}
