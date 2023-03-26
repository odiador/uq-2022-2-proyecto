package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

import javax.swing.JOptionPane;

import objects.Cliente;
import objects.Compra;
import objects.CustomException;

/**
 * Métodos de la base de datos, los códigos de error de las conexiones son:
 * <li>Sin conexión: 0
 * <li>Las cédulas no se pueden repetir: 1062
 *
 */
public class TablaCompras {
	public static final String ruta = "jdbc:mysql://localhost:3306/cinemadatabase";

	public static void imprimir (String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}

	/**
	 * El código de error es 1 si no hay errores
	 * 
	 * @param name es el nombre del cliente
	 * @param cc   es la cédula del cliente
	 * @throws SQLException en caso de haber
	 */
	public static void agregarCompra (Compra compra) throws SQLException {
		Connection c = DriverManager.getConnection(ruta, "root", "");
		PreparedStatement pst = c.prepareStatement("insert into compra values(?,?,?,?,?,?,?,?,?,?)");
		pst.setInt(1, 0);
		pst.setString(2, compra.getTipo());
		pst.setString(3, compra.getElemento());
		pst.setString(4, compra.getInfoExtra());
		pst.setString(5, compra.getCliente().toString());
		pst.setString(6, compra.getMomento().toString());
		pst.setString(7, compra.getMedioPago());
		pst.setDouble(8, compra.getvUnitario());
		pst.setInt(9, compra.getCantidad());
		pst.setDouble(10, compra.getPrecio());
		pst.executeUpdate();
		c.close();
	}

	public static void eliminarHistorial () throws SQLException {
		Connection c = DriverManager.getConnection(ruta, "root", "");
		PreparedStatement pst = c.prepareStatement("DELETE FROM compra");
		pst.executeUpdate();
		c.close();
	}

	public static void eliminarHistorialDe (String tipo) throws SQLException {
		Connection c = DriverManager.getConnection(ruta, "root", "");
		PreparedStatement pst = c.prepareStatement("DELETE FROM compra WHERE `compra`.`TIPO` = ?");
		pst.setString(1, tipo);
		pst.executeUpdate();
		c.close();
	}

	public static Compra[] getAllCompras () throws SQLException {
		Compra compras[] = new Compra[getCantOfCompras()];
		Connection c = DriverManager.getConnection(ruta, "root", "");
		Statement state = c.createStatement();
		ResultSet rs = state.executeQuery("SELECT * FROM `compra`");
		for (int i = 0; i < compras.length; i++) {
			rs.next();

			int id = rs.getInt(1);
			String tipo = rs.getString(2);
			String elemento = rs.getString(3);
			String info = rs.getString(4);
			Cliente cliente = Cliente.parse(rs.getString(5));
			LocalDateTime time = LocalDateTime.parse(rs.getString(6));
			String medioPago = rs.getString(7);
			double vUnitario = rs.getDouble(8);
			int cantidad = rs.getInt(9);

			compras[i] = new Compra(time, tipo, info, cliente, medioPago, elemento, vUnitario, cantidad, id);
		}
		c.close();
		return compras;
	}

	public static Compra getCompraById (int id) throws SQLException, CustomException {
		Compra compra = new Compra();
		Connection c = DriverManager.getConnection(ruta, "root", "");
		Statement state = c.createStatement();
		ResultSet rs = state.executeQuery("SELECT * FROM `compra` WHERE `ID`=" + id);
		if (rs.next()) compra = new Compra(LocalDateTime.parse(rs.getString(6)), rs.getString(2), rs.getString(4),
				Cliente.parse(rs.getString(5)), rs.getString(7), rs.getString(3), rs.getDouble(8), rs.getInt(9),
				rs.getInt(1));
		c.close();
		if (compra.toString().equals("?")) {
			throw new CustomException(CustomException.COMPRA_NO_EXISTE,
					"La compra con id: " + id + " no fue encontrado");
		}
		return compra;
	}

	public static String eliminarCompraByID (int id) throws SQLException, CustomException {
		Compra cl = getCompraById(id);

		if (!cl.existe()) throw new CustomException("No hay compra que eliminar", CustomException.COMPRA_NO_EXISTE);

		Connection c = DriverManager.getConnection(ruta, "root", "");
		PreparedStatement pst = c.prepareStatement("DELETE FROM compra WHERE `compra`.`ID` = ?");
		pst.setInt(1, id);
		pst.executeUpdate();
		c.close();
		return cl.getName();
	}

	public static int getCantOfCompras () throws SQLException {
		Connection c = DriverManager.getConnection(ruta, "root", "");
		Statement state = c.createStatement();
		ResultSet rs = state.executeQuery("SELECT COUNT(*) AS count FROM compra");
		int ret = -1;
		while (rs.next()) ret = rs.getInt("count");
		c.close();
		return ret;
	}
}
