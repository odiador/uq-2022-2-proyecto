package objects;

import java.time.LocalDateTime;

public class Compra {
	private String tipo, elemento, medioPago;
	private LocalDateTime momento;
	private Cliente cliente;
	private double vUnitario = -1;
	private int cantidad = -1;
	private String infoExtra;
	private int id = -1;

	public Compra(LocalDateTime momento, String tipo, String infoExtra, Cliente cliente, String medioPago,
			String producto, double vUnitario, int cantidad, int id) {
		this(momento, tipo, infoExtra, cliente, medioPago, producto, vUnitario, cantidad);
		setId(id);
	}

	public Compra(LocalDateTime momento, String tipo, String infoExtra, Cliente cliente, String medioPago,
			String producto, double vUnitario, int cantidad) {
		setInfoExtra(infoExtra);
		setTipo(tipo);
		setCliente(cliente);
		setMomento(momento);
		setElemento(producto);
		setMedioPago(medioPago);
		setvUnitario(vUnitario);
		setCantidad(cantidad);
	}

	public Compra() {}

	public boolean existe () {
		boolean b = true;
		if (b && getTipo() == null) b = false;
		if (b && !getCliente().existe()) b = false;
		if (b && getMedioPago() == null) b = false;
		if (b && getInfoExtra() == null) b = false;
		if (b && getElemento() == null) b = false;
		if (b && getvUnitario() == -1) b = false;
		if (b && getCantidad() == -1) b = false;
		return b;
	}

	public Compra(String tipo, Cliente cliente, String medioPago, String infoExtra, String producto, double vUnitario,
			int cantidad) {
		this(LocalDateTime.now(), tipo, infoExtra, cliente, medioPago, producto, vUnitario, cantidad);
	}

	public long getCC () {
		return getCliente().getCC();
	}

	public void setCC (long cc) {
		getCliente().setCC(cc);
	}

	public String getName () {
		return getCliente().getName();
	}

	public void setName (String name) {
		getCliente().setName(name);
	}

	public int getFrecuency () {
		return getCliente().getFrecuency();
	}

	public void setFrecuency (int frecuency) {
		getCliente().setFrecuency(frecuency);
	}

	public TarjetaUQ gettBasic () {
		return getCliente().gettBasic();
	}

	public void settBasic (TarjetaUQ tBasic) {
		getCliente().settBasic(tBasic);
	}

	public TarjetaUQ gettGold () {
		return getCliente().gettGold();
	}

	public void settGold (TarjetaUQ tGold) {
		getCliente().settGold(tGold);
	}

	public Cliente getCliente () {
		return cliente;
	}

	public void setCliente (Cliente cliente) {
		this.cliente = cliente;
	}

	public String getTipo () {
		return tipo;
	}

	public void setTipo (String tipo) {
		this.tipo = tipo;
	}

	public String getMedioPago () {
		return medioPago;
	}

	public void setMedioPago (String medioPago) {
		this.medioPago = medioPago;
	}

	public LocalDateTime getMomento () {
		return momento;
	}

	public void setMomento (LocalDateTime momento) {
		this.momento = momento;
	}

	public double getvUnitario () {
		return vUnitario;
	}

	public void setvUnitario (double vUnitario) {
		this.vUnitario = vUnitario;
	}

	public int getCantidad () {
		return cantidad;
	}

	public void setCantidad (int cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecio () {
		return getvUnitario() * getCantidad();
	}

	public String getElemento () {
		return elemento;
	}

	public void setElemento (String elemento) {
		this.elemento = elemento;
	}

	public static Compra ninguna () {
		return new Compra();
	}

	public static Compra parse (String s) {
		if (s.equals("?")) return ninguna();
		StringBuilder sb = new StringBuilder(s);
		sb.deleteCharAt(0);
		sb.deleteCharAt(sb.length() - 1);
		String[] result = sb.toString().split("_");
		LocalDateTime momento = LocalDateTime.parse(result[0]);
		String tipo = result[1];
		String infoExtra = result[2];
		Cliente cliente = Cliente.parse(result[3]);
		String medioPago = result[4];
		String producto = result[5];
		double vUnitario = Double.parseDouble(result[6]);
		int cantidad = Integer.parseInt(result[7]);
		int id = Integer.parseInt(result[8]);
		return new Compra(momento, tipo, infoExtra, cliente, medioPago, producto, vUnitario, cantidad, id);
	}

	@Override
	public String toString () {
		return !existe() ? "?"
				: "(" + getMomento() + "_" + getTipo() + "_" + getInfoExtra() + "_" + getCliente() + "_"
						+ getMedioPago() + "_" + getElemento() + "_" + getvUnitario() + "_" + getCantidad() + "_"
						+ getId() + ")";
	}

	public String getInfoExtra () {
		return infoExtra;
	}

	public void setInfoExtra (String infoExtra) {
		this.infoExtra = infoExtra;
	}

	public int getId () {
		return id;
	}

	public void setId (int id) {
		this.id = id;
	}

	public static int compararPorNombre (Compra a, Compra b) {
		return a.getName().compareTo(b.getName());
	}

	public static int compararPorTipo (Compra a, Compra b) {
		return a.getTipo().compareTo(b.getTipo());
	}

	public static int compararPorCedulaMenorMayor (Compra a, Compra b) {
		return ((Long) (a.getCC() - b.getCC())).intValue();
	}

	public static int compararPorPrecioMenorMayor (Compra a, Compra b) {
		return ((Double) (a.getPrecio() - b.getPrecio())).intValue();
	}

	public static int compararPorPrecioMayorMenor (Compra a, Compra b) {
		return ((Double) (b.getPrecio() - a.getPrecio())).intValue();
	}

	public static int compararPorCantidadMayorMenor (Compra a, Compra b) {
		return b.getCantidad() - a.getCantidad();
	}

	public static int compararPorCantidadMenorMayor (Compra a, Compra b) {
		return a.getCantidad() - b.getCantidad();
	}

	public static int compararPorHora (Compra a, Compra b) {
		return a.getMomento().compareTo(b.getMomento());
	}

	public static int compararPorHoraReves (Compra a, Compra b) {
		return b.getMomento().compareTo(a.getMomento());
	}

}
