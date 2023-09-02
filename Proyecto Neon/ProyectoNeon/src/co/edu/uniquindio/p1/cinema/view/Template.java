package co.edu.uniquindio.p1.cinema.view;

import static co.edu.uniquindio.p1.cinema.services.ColorManagement.rgbColor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.LineBorder;

import co.edu.uniquindio.p1.cinema.objetos.CLabel;
import co.edu.uniquindio.p1.cinema.services.Herramientas;

public abstract class Template extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane, centerPane, upperBar, rightUpperPane;
	private CLabel closeBtn, minimizeBtn, resizeBtn, labelStellar;
	private JLabel titleLbl;
	private JSeparator separator;
	private ColorThread thread;
	private Dimension defaultSize;
	private int posX;
	private int posY;

	public Template(int width, int height) {
		initComponents();
		configureFrame(width, height);
		addComponents();
	}

	private void initComponents() {
		initContentPane();
		initSuperiorComponents();
		initCenter();
	}

	private void configureFrame(int width, int height) {
		defaultSize = new Dimension(width, height);
		setSize(defaultSize);
		setUndecorated(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setContentPane(contentPane);
	}

	private void addComponents() {
		addCompToUpperBar();
		contentPane.add(upperBar, BorderLayout.NORTH);
		contentPane.add(centerPane, BorderLayout.CENTER);
		centerPane.add(labelStellar, BorderLayout.NORTH);
	}

	private void initContentPane() {
		contentPane = new JPanel();
		contentPane.setBackground(Herramientas.BLACK);
		contentPane.setBorder(new LineBorder(rgbColor));
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);
	}

	private void initSuperiorComponents() {
		closeBtn = ToolKit.initSuperiorButton("X");
		resizeBtn = ToolKit.initSuperiorButton("1");
		minimizeBtn = ToolKit.initSuperiorButton("0");

		separator = new JSeparator();
		separator.setBackground(null);
		separator.setForeground(rgbColor);

		upperBar = new JPanel();
		upperBar.setBackground(Herramientas.black);
		upperBar.addMouseMotionListener(createMotionBarMotionListener());
		upperBar.addMouseListener(createMotionBarMouseListener());

		rightUpperPane = new JPanel();
		rightUpperPane.setBackground(Herramientas.black);

		ToolKit.configureButtonHover(closeBtn);
		ToolKit.configureButtonHover(resizeBtn);
		ToolKit.configureButtonHover(minimizeBtn);

		closeBtn.setOnClicAction(() -> dispose());
		resizeBtn.setOnClicAction(() -> {
			resizeBtn.setText(getExtendedState() == NORMAL ? "2" : "1");
			setExtendedState(getExtendedState() == NORMAL ? MAXIMIZED_BOTH : NORMAL);
		});
		minimizeBtn.setOnClicAction(() -> setExtendedState(ICONIFIED));

		rightUpperPane.setLayout(new BorderLayout());
		upperBar.setLayout(new GridBagLayout());
	}

	private MouseListener createMotionBarMouseListener() {
		return new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				if (upperBar.getMousePosition() != null && isResizable() && e.getLocationOnScreen().y <= 0
						&& getExtendedState() != MAXIMIZED_BOTH)
					setExtendedState(MAXIMIZED_BOTH);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				pressedMotionBarAction(e);
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}

		};
	}

	private MouseMotionListener createMotionBarMotionListener() {
		return new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				dragMotionAction(e);
			}

		};
	}

	private void pressedMotionBarAction(MouseEvent e) {
		posX = e.getX() + upperBar.getBounds().x;
		posY = e.getY();
	}

	private void dragMotionAction(MouseEvent e) {
		int posX = e.getXOnScreen();
		int posY = e.getYOnScreen();
		if (getExtendedState() != MAXIMIZED_BOTH) {
			setLocation(posX - this.posX, posY - this.posY);
		}
		if (isResizable()) {
			if (getExtendedState() == MAXIMIZED_BOTH) {
				if (posX <= (defaultSize.width / 2)) {
					this.posX = posX;
				} else if (posX >= Herramientas.SCREEN_SIZE.width - (defaultSize.width / 2)) {
					this.posX = posX - Herramientas.SCREEN_SIZE.width + defaultSize.width;
				} else {
					this.posX = defaultSize.width / 2;
				}
				this.posY = e.getY();
				setSize(new Dimension(800, 600));
			}
		}
	}

	private void initCenter() {
		centerPane = new JPanel();
		centerPane.setLayout(new BorderLayout(30, 30));
		centerPane.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));
		centerPane.setBackground(Herramientas.black);
		labelStellar = new CLabel("STELLAR CINEMA");
		labelStellar.setFont(Herramientas.FUENTE_STELLAR.deriveFont(50f));
		labelStellar.setHorizontalAlignment(0);
		labelStellar.setVerticalAlignment(0);
		labelStellar.setForeground(rgbColor);
		labelStellar.setOnMouseEnteredAction(() -> {
			thread = new ColorThread(labelStellar);
			thread.start();
			labelStellar.setText("stellar cinema");
		});
		labelStellar.setOnMouseExitedAction(() -> {
			thread.interrupt();
			labelStellar.setText("STELLAR CINEMA");
			updateColors();
			updateStellarColors();
		});
		labelStellar.setOnClicAction(() -> {
			updateColors();
			updateStellarColors();
		});
	}

	public abstract void updateStellarColors();

	private void addCompToUpperBar() {
		final GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 0;

		upperBar.add(closeBtn, constraints);
		constraints.gridx++;
		upperBar.add(resizeBtn, constraints);
		constraints.gridx++;
		upperBar.add(minimizeBtn, constraints);
		constraints.gridx++;
		constraints.weightx = 1;
		upperBar.add(rightUpperPane, constraints);
		constraints.gridx = 0;
		constraints.gridy++;
		constraints.gridwidth = 4;
		upperBar.add(separator, constraints);
	}

	private void updateColors() {
		closeBtn.setForeground(rgbColor);
		resizeBtn.setForeground(rgbColor);
		minimizeBtn.setForeground(rgbColor);
		contentPane.setBorder(new LineBorder(rgbColor));
		separator.setForeground(rgbColor);
	}

	public void setCenter(JComponent component) {
		centerPane.add(component);
	}

	@Override
	public void setTitle(String title) {
		super.setTitle(title);
		titleLbl = new JLabel(title);
		titleLbl.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 20));
		titleLbl.setForeground(rgbColor);
		titleLbl.setFont(Herramientas.FUENTE_COOLVETICA);
		rightUpperPane.add(titleLbl);
	}
}
