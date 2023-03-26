package mostrardatos;

import java.awt.BorderLayout;
import java.sql.SQLException;

import config.Constantes;
import custom.PanelConScroll;
import custom.Plantilla;
import custom.TableText;
import custom.Text;
import objects.Cinema;
import objects.Cliente;
import tools.TablaCinema;
import tools.TablaClientes;

public class VentanasDatos {
	private static final class VentanaClientes extends Plantilla {
		public VentanaClientes() {
			Cliente[] c;
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			try {
				c = TablaClientes.getAllClients();
			} catch (SQLException e) {
				return;
			}
			agregarBody(new PanelConScroll(new PanelVerClientes(c)));
		}

		@Override
		public void configurarTam () {
			setSize(1100, 500);
		}
	}

	private static final class VentanaCines extends Plantilla {
		public VentanaCines() {
			Cinema[] c;
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			try {
				c = TablaCinema.getAllCinemas();
			} catch (SQLException e) {
				return;
			}
			Text bCerrar = new Text("Cerrar");
			bCerrar.configAsButton(e -> dispose());
			bCerrar.setPreferredSize(300, 40);
			setBodyLayout(new BorderLayout(0, 5));
			setBodyBg(Constantes.defaultBgColor);
			agregarBody(new TableText("Cantidad de Cines: " + c.length), BorderLayout.NORTH);
			agregarBody(new PanelVerCines(c));
			agregarBody(bCerrar, BorderLayout.SOUTH);
		}

		@Override
		public void configurarTam () {
			setSize(1100, 500);
		}
	}

	public static void main (String[] args) {
		Plantilla p = new VentanaClientes();
		p.setVisible(true);
	}

	public static void mostrarVentanaDatosClientes () {
		new VentanaClientes().setVisible(true);
	}

	public static void mostrarVentanaDatosCines () {
		new VentanaCines().setVisible(true);
	}
}
