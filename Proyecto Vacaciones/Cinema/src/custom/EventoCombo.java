package custom;

import java.util.EventObject;

public class EventoCombo extends EventObject {

	private String text;
	private int index;

	public EventoCombo(Object source, String text,int index) {
		super(source);
		setIndex(index);
		setText(text);
	}

	public String getText () {
		return text;
	}

	public void setText (String text) {
		this.text = text;
	}

	public int getIndex () {
		return index;
	}

	public void setIndex (int index) {
		this.index = index;
	}
}
