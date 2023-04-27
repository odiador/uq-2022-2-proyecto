package view;

import java.util.EventObject;

public class EventoTf extends EventObject {

	private String text;

	public EventoTf(Object source, String text) {
		super(source);
		setText(text);
	}

	public Long getNumber () {
		return Long.parseLong(Utilities.toString(getText().replace('.', '-').split("-")));
	}

	public int getIntNumber () {
		return ((Long) getNumber()).intValue();
	}

	public String getText () {
		return text;
	}

	public void setText (String text) {
		this.text = text;
	}
}
