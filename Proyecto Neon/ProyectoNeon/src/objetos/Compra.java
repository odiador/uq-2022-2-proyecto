package objetos;

import java.time.LocalDate;
import java.time.LocalTime;

public class Compra {
	private int anio;
	private int mes;
	private int dia;
	private int hora;
	private int minuto;
	private int segundo;
	private tiposDeCompra tipo;
	private double valormedio1;
	private double valormedio2;
	private double valorTotal;
	private Cliente cliente;
	private int cantidadPagos;
	private String medioDePago1;
	private String medioDePago2;

	public static enum tiposDeCompra {
		Boletas, Confiteria
	}

	public Compra(tiposDeCompra tipo,double valorTotal,double valormedio1) {
		LocalTime tiempoInfo = LocalTime.now();
		LocalDate diaInfo = LocalDate.now();
		setAnio(diaInfo.getYear());
		setMes(diaInfo.getMonthValue());
		setDia(diaInfo.getDayOfMonth());
		setHora(tiempoInfo.getHour());
		setMinuto(tiempoInfo.getMinute());
		setSegundo(tiempoInfo.getSecond());

	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public int getHora() {
		return hora;
	}

	public void setHora(int hora) {
		this.hora = hora;
	}

	public int getMinuto() {
		return minuto;
	}

	public void setMinuto(int minuto) {
		this.minuto = minuto;
	}

	public int getSegundo() {
		return segundo;
	}

	public void setSegundo(int segundo) {
		this.segundo = segundo;
	}

	public tiposDeCompra getTipo() {
		return tipo;
	}

	public void setTipo(tiposDeCompra tipo) {
		this.tipo = tipo;
	}

	public double getValormedio1() {
		return valormedio1;
	}

	public void setValormedio1(double valormedio1) {
		this.valormedio1 = valormedio1;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public int getCantidadPagos() {
		return cantidadPagos;
	}

	public void setCantidadPagos(int cantidadPagos) {
		this.cantidadPagos = cantidadPagos;
	}

	public String getMedioDePago1() {
		return medioDePago1;
	}

	public void setMedioDePago1(String medioDePago1) {
		this.medioDePago1 = medioDePago1;
	}

	public String getMedioDePago2() {
		return medioDePago2;
	}

	public void setMedioDePago2(String medioDePago2) {
		this.medioDePago2 = medioDePago2;
	}

	public double getValormedio2() {
		return valormedio2;
	}

	public void setValormedio2(double valormedio2) {
		this.valormedio2 = valormedio2;
	}

}
