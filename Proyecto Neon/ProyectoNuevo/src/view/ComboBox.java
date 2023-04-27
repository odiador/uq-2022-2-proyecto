package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

public class ComboBox extends Componente implements ClicListener {

	private JLabel label;
	private String texts[];
	private Icon icon;
	private int selection = 0;

	public ComboBox(ArrayList<String> lista) {
		this(Utilities.listArrToStringArr(lista), 0);
	}

	public ComboBox(ArrayList<String> lista, Font f) {
		this(Utilities.listArrToStringArr(lista), 0);
		getLabel().setFont(f);
	}

	public ComboBox(String cads[]) {
		this(cads, 0);
	}

	public ComboBox(String cads[], Font f) {
		this(cads, 0);
		getLabel().setFont(f);
	}

	public ComboBox(String cads[], Font f, int horizontalAlligment) {
		this(cads, horizontalAlligment);
		getLabel().setFont(f);
	}

	public ComboBox(String cads[], int horizontalAlligment) {
		setLabel(new JLabel(cads[0], horizontalAlligment));
		setTexts(cads);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setLayout(new BorderLayout());
		setBorder(new LineBorder(new Color(240, 240, 240)));
		getLabel().setFont(Constantes.defaultFont);
		getLabel().setVerticalAlignment(0);
		setForeground(Constantes.defaultTextColor);
		addClicListener(this);
		add(getLabel(), BorderLayout.CENTER);
	}

	public void setVerticalAlignment (int alignment) {
		getLabel().setVerticalAlignment(alignment);
	}

	public void setHorizontalAlignment (int alignment) {
		getLabel().setHorizontalAlignment(alignment);
	}

	public void setTexts (String[] texts) {
		this.texts = texts;
	}

	public String[] getTexts () {
		return texts;
	}

	public void setText (String s) {
		getLabel().setText(s);
	}

	public String getText () {
		return getLabel().getText();
	}

	public void setForeground (Color fg) {
		if (getLabel() != null) {
			getLabel().setForeground(fg);
			return;
		}
		super.setForeground(fg);
	}

	public void setFont (Font f) {
		if (getLabel() != null) {
			getLabel().setFont(f);
			return;
		}
		super.setFont(f);
	}

	public JLabel getLabel () {
		return label;
	}

	public void setLabel (JLabel label) {
		this.label = label;
	}

	public Icon getIcon () {
		return icon;
	}

	public void setIcon (Icon icon) {
		this.icon = icon;
	}

	public void addComboListener (ComboListener l) {
		listeners.add(l);
	}

	public void removeAllTextfieldListeners () {
		List<ComboListener> lista = getListeners();
		for (int i = 0; i < lista.size(); i++) getListeners().remove(0);
	}

	private List<ComboListener> listeners = new ArrayList<ComboListener>();

	@Override
	public void botonPresionado (Evento e) {
		setSelection(getSelection() + 1);
		if (getSelection() >= getTexts().length) setSelection(0);
		setText(getTexts()[getSelection()]);
		for (ComboListener l : getListeners()) l.comboUpdate(new EventoCombo(this, getText(), getSelection()));
	}

	public int getSelection () {
		return selection;
	}

	public void setSelection (int selection) {
		this.selection = selection;
	}

	public void setText (int index) {
		setText(getTexts()[index]);
		setSelection(index);
	}

	public List<ComboListener> getListeners () {
		return listeners;
	}

	public void setListeners (List<ComboListener> listeners) {
		this.listeners = listeners;
	}
}
