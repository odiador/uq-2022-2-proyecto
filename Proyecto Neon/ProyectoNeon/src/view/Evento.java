package view;

public class Evento {

	private Object source;

	public Evento(Object source) {
		setSource(source);
	}

	public String toString () {
		return "[source=" + getSource().toString() + "]";
	}

	public Object getSource () {
		return source;
	}

	private void setSource (Object source) {
		this.source = source;
	}
}
