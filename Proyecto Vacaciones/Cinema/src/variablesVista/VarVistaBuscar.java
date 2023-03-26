package variablesVista;

import custom.Text;
import objects.Cliente;

public class VarVistaBuscar {

	private Text lblNombre, lblCedu, lblFrec, botonBasic, botonGold, botonVolver;
	private Cliente cliente;

	public Cliente getCliente () {
		return cliente;
	}

	public void setCliente (Cliente c) {
		cliente = c;
	}

	public Text getBotonGold () {
		return botonGold;
	}

	public void setBotonGold (Text c) {
		botonGold = c;
	}

	public Text getLblNombre () {
		return lblNombre;
	}

	public void setLblNombre (Text c) {
		lblNombre = c;
	}

	public Text getLblFrec () {
		return lblFrec;
	}

	public void setLblFrec (Text c) {
		lblFrec = c;
	}

	public Text getBotonBasic () {
		return botonBasic;
	}

	public void setBotonBasic (Text c) {
		botonBasic = c;
	}

	public Text getLblCedu () {
		return lblCedu;
	}

	public void setLblCedu (Text c) {
		lblCedu = c;
	}

	public Text getBotonVolver () {
		return botonVolver;
	}

	public void setBotonVolver (Text botonVolver) {
		this.botonVolver = botonVolver;
	}
}
