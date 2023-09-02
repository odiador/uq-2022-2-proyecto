package co.edu.uniquindio.p1.cinema.model;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.text.NumberFormatter;

import co.edu.uniquindio.p1.cinema.view.PopUp;

public class Herramientas {

	public static final int SCALE_FAST = Image.SCALE_FAST;
	public static final int SCALE_AREA_AVERAGING = Image.SCALE_AREA_AVERAGING;
	public static final int SCALE_SMOOTH = Image.SCALE_SMOOTH;
	public static final int SCALE_DEFAULT = Image.SCALE_DEFAULT;
	public static final int SCALE_REPLICATE = Image.SCALE_REPLICATE;

	public static final int ORIENTACION_VERTICAL = SwingConstants.VERTICAL;
	public static final int ORIENTACION_HORIZONTAL = SwingConstants.HORIZONTAL;

	public static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();

	public static final Cursor HAND_CURSOR = new Cursor(Cursor.HAND_CURSOR);
	public static final Cursor TEXT_CURSOR = new Cursor(Cursor.TEXT_CURSOR);

	public static final Color WHITE = Color.white;
	public static final Color white = WHITE;

	public static final Color BLACK = Color.black;
	public static final Color black = BLACK;

	public static final Color GRAY = new Color(100, 100, 100);
	public static final Color gray = GRAY;

	public static final String RUTA_FONT_DEFAULT = "fonts/Bubble Bobble.otf";
	public static final Font FUENTE_TITULO_DEFAULT = crearFuente(RUTA_FONT_DEFAULT, 20);
	public static final Font FUENTE_STELLAR = crearFuente("fonts/PAC-FONT.ttf", 20);
	public static final Font FUENTE_PIRANA = crearFuente("fonts/pirana.ttf", 17);;
	public static final Font FUENTE_COOLVETICA = crearFuente("fonts/coolvetica rg.otf", 20);

	public static final ImageIcon IMAGEN_DEFAULT = new ImageIcon("images/Stellar Cinema.png");

	public static final String[] mediosDePago = { "Efectivo", "Puntos", "Saldo Tarjeta Basic", "Saldo Tarjeta Gold",
			"PSE" };
	public static final String[] mediosDePagoConfiteria = { "Efectivo", "Saldo Tarjeta Basic", "Saldo Tarjeta Gold",
			"PSE" };

	public static ImageIcon escalarImagen(ImageIcon imagen, int width, int height, int tipoDeEscala) {
		return new ImageIcon(imagen.getImage().getScaledInstance(width, height, tipoDeEscala));
	}

	public static enum OPCIONES {
		CINE, CONFI, REGISTRAR, BUSCAR, DAR, RECARGAR, CONSOLIDADO, HISTORIAL, VER, ninguna
	}

	public static enum tiposDeSilla {
		General, Preferencial
	}

	public static enum tiposDeCompra {
		Confiteria, Boletas
	}

	public static Font crearFuente(String ruta, int tamanio) {
		Font fuente = null;
		try {
			InputStream rutaLeer = new BufferedInputStream(new FileInputStream(ruta));
			Font fuenteBase = Font.createFont(Font.TRUETYPE_FONT, rutaLeer);
			fuente = fuenteBase.deriveFont(Font.PLAIN, tamanio);
		} catch (Exception e) {
			fuente = new Font("Arial", Font.PLAIN, tamanio);
			System.out.println("Error en la fuente de ruta " + e.getMessage());
		}
		return fuente;
	}

	public static String formatoSinDecimal(double numero) {
		return new DecimalFormat("#").format(numero);
	}

	public static String conseguirTextoFormatoSinDecimal(String cadena) {
		String total = "";
		for (String parcial : cadena.replace('.', '-').split("-"))
			total += parcial;
		return total;
	}

	public static NumberFormatter setFormatodeNumero(double min, double max) {
		NumberFormatter formatoNum = new NumberFormatter(NumberFormat.getNumberInstance());
		formatoNum.setValueClass(Double.class);
		formatoNum.setMinimum(min);
		formatoNum.setMaximum(max);
		formatoNum.setAllowsInvalid(false);
		return formatoNum;
	}

	public static String obtenerLabelCentrado(String cadena, String alignment) {
		return "<html><style>div {text-align: " + alignment + ";}</style><div>" + cadena + "</div></html>";
	}

	public static void abrirPopUp(String tipo, String mensaje) {
		PopUp popUp = new PopUp(tipo, mensaje);
		popUp.setPopUp(true);
		popUp.contentPane.addMouseListener(popUp);
		popUp.setVisible(true);
	}

	public static void cambiarTamLabel(Rectangle bounds, JLabel label) {
		int width = label.getFontMetrics(label.getFont()).stringWidth(label.getText());
		boolean entro = false;
		while (width > bounds.width) {
			entro = true;
			label.setFont(label.getFont().deriveFont(label.getFont().getSize() - 1f));
			width = label.getFontMetrics(label.getFont()).stringWidth(label.getText());
		}
		if (entro) {
			label.setFont(label.getFont().deriveFont(label.getFont().getSize() - 4f));
		}
	}

	public static boolean estaEnRangoDe(JComponent c) {
		return c.getMousePosition() != null;
	}

	public static ImageIcon getImage(String cad) {
		ImageIcon img = new ImageIcon("images/" + cad + ".png");
		if (img.getImageLoadStatus() != 8) {
			img = IMAGEN_DEFAULT;
		}
		return img;
	}

	public static void eliminarCadConsola(String txt) {
		for (int i = 0; i < txt.length(); i++)
			System.out.print("\b");
	}

	public static String pasarHoraString(int hh) {
		if (hh < 10) {
			return "0" + hh;
		}
		return "" + hh;
	}
}