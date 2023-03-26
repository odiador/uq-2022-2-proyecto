package custom;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JSeparator;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import config.Utilities;

public class FormattedTextField extends Componente implements FocusListener, KeyListener, DocumentListener {

	public AbstractFormatter getFormatter () {
		return getTf().getFormatter();
	}

	public FormattedTextField() {
		this("");
	}

	public FormattedTextField(String initialValue) {
		this(new Color(100, 100, 100), initialValue);
	}

	public FormattedTextField(Color c) {
		this(c, "");
	}

	public FormattedTextField(Color defaultColor, String initialValue) {
		setTf(new JFormattedTextField(DateFormat.getDateInstance(DateFormat.SHORT)));

		setFont(new Font("Coolvetica Rg", Font.PLAIN, 20));
		setLayout(new BorderLayout());
		setSeparator(new JSeparator());
		getTf().setVerifyInputWhenFocusTarget(true);
		getTf().setOpaque(false);
		getTf().addFocusListener(this);
		getTf().addKeyListener(this);
		getTf().getDocument().addDocumentListener(this);
		getTf().setBorder(null);

		setDefaultColor(defaultColor);
		setFocused(false);

		add(getTf(), BorderLayout.CENTER);
		add(getSeparator(), BorderLayout.SOUTH);
	}

	public void setHorizontalAllignment (int allignment) {
		getTf().setHorizontalAlignment(allignment);
	}

	public void setEditable (boolean b) {
		getTf().setEditable(b);
	}

	public boolean isEditable () {
		return getTf().isEditable();
	}

	@Override
	public void focusGained (FocusEvent e) {
		setFocused(true);
	}

	@Override
	public void focusLost (FocusEvent e) {
		setFocused(false);
	}

	public void setText (String text) {
		getTf().setText(text);
	}

	public long getNumber () {
		return Long.parseLong(Utilities.toString(getText().replace('.', '-').split("-")));
	}

	public int getIntNumber () {
		return ((Long) getNumber()).intValue();
	}

	public String getText () {
		return getTf().getText();
	}

	public void setValue (Object value) {
		getTf().setValue(value);
	}

	public Object getValue () {
		return getTf().getValue();
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

	public JFormattedTextField getTf () {
		return tf;
	}

	public void setTf (JFormattedTextField tf) {
		this.tf = tf;
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

	public static MaskFormatter createMaskFormat (String value, String placeHolder) {
		MaskFormatter mask = null;
		try {
			mask = new MaskFormatter(value);
			mask.setAllowsInvalid(false);
			mask.setPlaceholderCharacter('_');
			mask.setValidCharacters("1234567890");
			mask.setPlaceholder(placeHolder);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return mask;
	}

	public static NumberFormatter createNumberFormat (double min, double max) {
		NumberFormatter formatoNum = new NumberFormatter(NumberFormat.getNumberInstance());
		formatoNum.setValueClass(Double.class);
		formatoNum.setMinimum(min);
		formatoNum.setMaximum(max);
		formatoNum.setAllowsInvalid(false);
		return formatoNum;
	}

	@Override
	public void keyTyped (KeyEvent e) {}

	@Override
	public void keyPressed (KeyEvent e) {
		if (isEditable() & e.getKeyChar() == 8 & getText().length() == 1) {
			setText("0");
		}
	}

	@Override
	public void keyReleased (KeyEvent e) {}

	public void addTextfieldListener (TextfieldListener listener) {
		getTextfieldListeners().add(listener);
	}

	public void removeAllTextfieldListeners () {
		List<TextfieldListener> lista = getTextfieldListeners();
		for (int i = 0; i < lista.size(); i++) getTextfieldListeners().remove(0);
	}

	@Override
	public void insertUpdate (DocumentEvent e) {
		if (isUpdateActivated()) for (TextfieldListener l : getTextfieldListeners())
			l.textfieldUpdate(new EventoTf(this, getText()));
	}

	@Override
	public void removeUpdate (DocumentEvent e) {}

	@Override
	public void changedUpdate (DocumentEvent e) {}

	public List<TextfieldListener> getTextfieldListeners () {
		return lista;
	}

	public void setUpdateActivated (boolean b) {
		updateActivated = b;
	}

	public boolean isUpdateActivated () {
		return updateActivated;
	}

	private boolean updateActivated = true;
	private JFormattedTextField tf;
	private Color defaultColor;
	private JSeparator separator;
	private boolean isFocused;
	private List<TextfieldListener> lista = new ArrayList<TextfieldListener>();
}
