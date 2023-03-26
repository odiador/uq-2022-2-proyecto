package objects;

/**
 * 0: error inesperado <br>
 * 10: el cliente no existe <br>
 * 2: la tarjeta ya existe <br>
 * 1: la tarjeta no existe <br>
 * -1: no puede ser negativo
 * 
 * @author juana
 *
 */
public class CustomException extends java.lang.Exception {
	public static final int NUMERO_NEGATIVO = -1;
	public static final int INESPERADO = 0;
	public static final int TARJETA_NO_EXISTE = 1;
	public static final int TARJETA_YA_EXISTE = 2;
	public static final int NO_HAY_PUNTOS = 3;
	public static final int NO_HAY_DINERO = 4;
	public static final int MISMO_MEDIO_PAGO = 5;
	public static final int CANTIDAD_ES_0 = 6;
	
	public static final int COMPRA_NO_EXISTE = 8;
	public static final int SALA_NO_EXISTE = 9;
	public static final int CLIENTE_NO_EXISTE = 10;

	private int code;
	private String causa;

	public CustomException() {
		this("Unexpected Error", 0);
	}

	public CustomException(int code, String causa) {
		this(causa, code);
	}

	public CustomException(String causa, int code) {
		super(causa);
		setCausa(causa);
		setCode(code);
	}

	public int getErrorCode () {
		return code;
	}

	public void setCode (int code) {
		this.code = code;
	}

	public String getCausa () {
		return causa;
	}

	public void setCausa (String causa) {
		this.causa = causa;
	}
}
