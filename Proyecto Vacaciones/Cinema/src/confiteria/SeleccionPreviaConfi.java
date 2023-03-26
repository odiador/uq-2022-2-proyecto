package confiteria;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;

import config.Constantes;
import config.Utilities;
import custom.Panel;
import custom.Text;
import objects.Confiteria;

public class SeleccionPreviaConfi extends Panel {

	private int cantidad;
	private String tipo;
	private double vUnitario;
	private Text lblTipo, lblCantidad, lblValUnitario, lblPrecio, lblPrecioTotal;

	private Text infoTipo, infoCantidad, infoValUnitario, infoPrecio, infoPrecioTotal;
	private Confiteria c;
	private double total;

	public SeleccionPreviaConfi(Confiteria c, int cantidad, double total) {
		setTotal(total);
		setConfiteria(c);
		setCantidad(cantidad);
		setTipo(c.getProducto());
		setvUnitario(c.getValUnitario());

		Font f = Constantes.defaultFont.deriveFont(25f);

		setLblTipo(new Text("Tipo:", f));
		setLblValUnitario(new Text("Valor Unitario:", f));
		setLblCantidad(new Text("Cantidad:", f));
		setLblPrecio(new Text("Precio:", f));
		setLblPrecioTotal(new Text("Total:", f));

		setInfoTipo(new Text(getTipo(), f));
		setInfoValUnitario(new Text("$" + Utilities.format("%.0f", getvUnitario()), f));
		setInfoCantidad(new Text(getCantidad() + "", f));
		setInfoPrecio(new Text("$" + Utilities.format("%.0f", getPrecio()), f));
		setInfoPrecioTotal(new Text("$" + Utilities.format(getPrecio() + getTotal()), f));

		getLblTipo().setOpaque(true);
		getInfoTipo().setOpaque(true);

		getLblValUnitario().setOpaque(true);
		getInfoValUnitario().setOpaque(true);

		getLblCantidad().setOpaque(true);
		getInfoCantidad().setOpaque(true);

		getLblPrecio().setOpaque(true);
		getInfoPrecio().setOpaque(true);

		getLblPrecioTotal().setOpaque(true);
		getInfoPrecioTotal().setOpaque(true);

		setBackground(Color.white);
		setBorder(BorderFactory.createLineBorder(Color.white));
		setLayout(new GridLayout(5, 2, 1, 1));

		add(getLblTipo());
		add(getInfoTipo());

		add(getLblValUnitario());
		add(getInfoValUnitario());

		add(getLblCantidad());
		add(getInfoCantidad());

		add(getLblPrecio());
		add(getInfoPrecio());

		add(getLblPrecioTotal());
		add(getInfoPrecioTotal());
	}

	public void actualizar (int cantidad) {
		setCantidad(cantidad);

		getInfoCantidad().setText(getCantidad() + "");
		getInfoPrecio().setText("$" + Utilities.format("%.0f", getPrecio()));
		getInfoPrecioTotal().setText("$" + Utilities.format(getPrecio() + getTotal()));
	}

	public double getPrecio () {
		return c.getPrecio(getCantidad());
	}

	public int getCantidad () {
		return cantidad;
	}

	public void setCantidad (int cantidad) {
		this.cantidad = cantidad;
	}

	public String getTipo () {
		return tipo;
	}

	public void setTipo (String tipo) {
		this.tipo = tipo;
	}

	public double getvUnitario () {
		return vUnitario;
	}

	public void setvUnitario (double vUnitario) {
		this.vUnitario = vUnitario;
	}

	public Confiteria getConfiteria () {
		return c;
	}

	public void setConfiteria (Confiteria c) {
		this.c = c;
	}

	public double getTotal () {
		return total;
	}

	public void setTotal (double total) {
		this.total = total;
	}

	public Text getLblTipo () {
		return lblTipo;
	}

	public void setLblTipo (Text lblTipo) {
		this.lblTipo = lblTipo;
	}

	public Text getLblCantidad () {
		return lblCantidad;
	}

	public void setLblCantidad (Text lblCantidad) {
		this.lblCantidad = lblCantidad;
	}

	public Text getLblValUnitario () {
		return lblValUnitario;
	}

	public void setLblValUnitario (Text lblValUnitario) {
		this.lblValUnitario = lblValUnitario;
	}

	public Text getLblPrecio () {
		return lblPrecio;
	}

	public void setLblPrecio (Text lblPrecio) {
		this.lblPrecio = lblPrecio;
	}

	public Text getLblPrecioTotal () {
		return lblPrecioTotal;
	}

	public void setLblPrecioTotal (Text lblPrecioTotal) {
		this.lblPrecioTotal = lblPrecioTotal;
	}

	public Text getInfoTipo () {
		return infoTipo;
	}

	public void setInfoTipo (Text infoTipo) {
		this.infoTipo = infoTipo;
	}

	public Text getInfoCantidad () {
		return infoCantidad;
	}

	public void setInfoCantidad (Text infoCantidad) {
		this.infoCantidad = infoCantidad;
	}

	public Text getInfoValUnitario () {
		return infoValUnitario;
	}

	public void setInfoValUnitario (Text infoValUnitario) {
		this.infoValUnitario = infoValUnitario;
	}

	public Text getInfoPrecio () {
		return infoPrecio;
	}

	public void setInfoPrecio (Text infoPrecio) {
		this.infoPrecio = infoPrecio;
	}

	public Text getInfoPrecioTotal () {
		return infoPrecioTotal;
	}

	public void setInfoPrecioTotal (Text infoPrecioTotal) {
		this.infoPrecioTotal = infoPrecioTotal;
	}

}
