package custom;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class TextField extends Componente implements FocusListener, DocumentListener {

	public TextField() {
		this(defaultFont);
	}

	public TextField(Font f) {
		this("Escribe algo", f);
	}

	public TextField(String defaultText) {
		this(defaultText, defaultFont);
	}

	public TextField(String defaultText, Font f) {
		this(defaultText, new Color(100, 100, 100), f);
	}

	public TextField(String defaultText, Color defaultColor, Font f) {
		setTf(new JTextField(defaultText));
		setFont(f);
		setLayout(new BorderLayout());
		setSeparator(new JSeparator());
		getTf().setOpaque(false);
		getTf().addFocusListener(this);
		getTf().getDocument().addDocumentListener(this);
		getTf().setBorder(null);

		setDefaultColor(defaultColor);
		setFocused(false);
		setDefaultText(defaultText);

		add(getTf(), BorderLayout.CENTER);
		add(getSeparator(), BorderLayout.SOUTH);
	}

	@Override
	public void focusGained (FocusEvent e) {
		setFocused(true);
		if (getText() == null) {
			setText("");
		}
	}

	public void setHorizontalAllignment (int allignment) {
		getTf().setHorizontalAlignment(allignment);
	}

	@Override
	public void focusLost (FocusEvent e) {
		setFocused(false);
		if (getText() == null) {
			setText(defaultText);
		}

	}

	public void setText (String text) {
		getTf().setText(text);
	}

	public String getText () {
		String texto = getTf().getText();
		return (texto.equals(defaultText) || texto.equals("")) ? null : getTf().getText();
	}

	public void setForeground (Color fg) {
		if (getTf() != null) {
			getTf().setForeground(fg);
			return;
		}
		super.setForeground(fg);
	}

	public void setFont (Font f) {
		if (getTf() != null) {
			getTf().setFont(f);
			return;
		}
		super.setFont(f);
	}

	public JTextField getTf () {
		return tf;
	}

	public void setTf (JTextField tf) {
		this.tf = tf;
	}

	public String getDefaultText () {
		return defaultText;
	}

	public void setDefaultText (String defaultText) {
		this.defaultText = defaultText;
		setText(defaultText);
	}

	public Color getDefaultColor () {
		return defaultColor;
	}

	public void setDefaultColor (Color defaultColor) {
		this.defaultColor = defaultColor;
	}

	public JSeparator getSeparator () {
		return separator;
	}

	public void setSeparator (JSeparator separator) {
		this.separator = separator;
	}

	public boolean isFocused () {
		return isFocused;
	}

	public void setFocused (boolean isFocused) {
		this.isFocused = isFocused;
		Color c = isFocused ? new Color(200, 200, 200) : defaultColor;
		setForeground(c);
		getSeparator().setBackground(c);
		getSeparator().setForeground(c);
	}

	public void addTextfieldListener (TextfieldListener listener) {
		getTextfieldListeners().add(listener);
	}

	public void removeAllTextfieldListeners () {
		List<TextfieldListener> lista = getTextfieldListeners();
		for (int i = 0; i < lista.size(); i++) getTextfieldListeners().remove(0);
	}

	@Override
	public void insertUpdate (DocumentEvent e) {
		for (TextfieldListener l : getTextfieldListeners()) l.textfieldUpdate(new EventoTf(this, getText()));
	}

	@Override
	public void removeUpdate (DocumentEvent e) {}

	@Override
	public void changedUpdate (DocumentEvent e) {}

	public List<TextfieldListener> getTextfieldListeners () {
		return lista;
	}

	private JTextField tf;
	private String defaultText = "Escribe algo";
	private Color defaultColor;
	private JSeparator separator;
	private boolean isFocused;
	public static final Font defaultFont = new Font("Coolvetica Rg", Font.PLAIN, 20);
	private List<TextfieldListener> lista = new ArrayList<TextfieldListener>();
}
