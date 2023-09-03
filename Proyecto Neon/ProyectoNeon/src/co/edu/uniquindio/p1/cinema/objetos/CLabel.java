package co.edu.uniquindio.p1.cinema.objetos;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.JLabel;

public class CLabel extends JLabel implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Runnable runnableClicked;
	private Runnable runnableEntered;
	private Runnable runnableExited;

	public CLabel(String text, Icon icon, int horizontalAlignment) {
		setText(text);
		setIcon(icon);
		setHorizontalAlignment(horizontalAlignment);
		updateUI();
		addMouseListener(this);
		setAlignmentX(LEFT_ALIGNMENT);
	}

	public CLabel(String text) {
		this(text, null, LEADING);
	}

	public CLabel(String text, int horizontalAlignment) {
		this(text, null, horizontalAlignment);
	}

	public CLabel(Icon image, int horizontalAlignment) {
		this(null, image, horizontalAlignment);
	}

	public CLabel(Icon image) {
		this(null, image, CENTER);
	}

	public CLabel() {
		this("", null, LEADING);
	}

	public void setOnClicAction(Runnable runnable) {
		this.runnableClicked = runnable;
	}

	public void setOnMouseEnteredAction(Runnable runnable) {
		this.runnableEntered = runnable;
	}

	public void setOnMouseExitedAction(Runnable runnable) {
		this.runnableExited = runnable;
	}

	public void updateColors() {
		if (getMousePosition() == null) {
			if (runnableExited != null)
				runnableExited.run();
		} else {
			if (runnableEntered != null)
				runnableEntered.run();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mouseClickedAction();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		mouseEnteredAction();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		mouseExitedAction();
	}

	private void mouseClickedAction() {
		if (getMousePosition() != null && runnableClicked != null)
			runnableClicked.run();
	}

	private void mouseEnteredAction() {
		if (runnableEntered != null)
			runnableEntered.run();
	}

	private void mouseExitedAction() {
		if (runnableExited != null)
			runnableExited.run();
	}

}
