package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import objects.Cliente;
import objects.CustomException;
import objects.TarjetaUQ;

/**
 * Métodos de la base de datos, los códigos de error de las conexiones son:
 * <li>Sin conexión: 0
 * <li>Las cédulas no se pueden repetir: 1062
 *
 */
public class TablaClientes {
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
	public static void agregarCliente (String name, long cc) throws SQLException {
		Connection c = DriverManager.getConnection(ruta, "root", "");
		PreparedStatement pst = c.prepareStatement("insert into clients values(?,?,?,?,?)");
		pst.setString(1, name);
		pst.setLong(2, cc);
		pst.setInt(3, 0);
		pst.setString(4, TarjetaUQ.ninguna().toString());
		pst.setString(5, TarjetaUQ.ninguna().toString());
		pst.executeUpdate();
		c.close();
	}

	public static void updateCliente (long cc, String name, int frecuency, TarjetaUQ tBasic, TarjetaUQ tGold)
			throws SQLException {
		Connection c = DriverManager.getConnection(ruta, "root", "");
		PreparedStatement pst = c.prepareStatement(
				"UPDATE `clients` SET `NAME` = ?, `FRECUENCY` = ?, `BASIC` = ?, `GOLD` = ? WHERE `clients`.`CC` = ?");
		pst.setString(1, name);
		pst.setInt(2, frecuency);
		pst.setString(3, tBasic.toString());
		pst.setString(4, tGold.toString());
		pst.setLong(5, cc);
		pst.executeUpdate();
		c.close();
	}

	public static String eliminarCliente (long cc) throws SQLException, CustomException {
		Cliente cl = getCliente(cc);
		if (!cl.existe()) throw new CustomException("No hay cliente que eliminar", 10);
		Connection c = DriverManager.getConnection(ruta, "root", "");
		PreparedStatement pst = c.prepareStatement("DELETE FROM clients WHERE `clients`.`CC` = ?");
		pst.setLong(1, cc);
		pst.executeUpdate();
		c.close();
		return cl.getName();
	}

	public static Cliente getCliente (long cc) throws SQLException {
		Cliente ret = Cliente.ninguno();
		Connection c = DriverManager.getConnection(ruta, "root", "");
		Statement state = c.createStatement();
		ResultSet rs = state.executeQuery("SELECT * FROM `clients` WHERE `CC`=" + cc);
		if (rs.next()) ret = new Cliente(rs.getString(1), rs.getLong(2), rs.getInt(3), TarjetaUQ.parse(rs.getString(4)),
				TarjetaUQ.parse(rs.getString(5)));
		c.close();
		return ret;
	}

	public static Cliente[] getAllClients () throws SQLException {
		Cliente clientes[] = new Cliente[getCantOfClients()];
		Connection c = DriverManager.getConnection(ruta, "root", "");
		Statement state = c.createStatement();
		ResultSet rs = state.executeQuery("SELECT * FROM `clients`");
		for (int i = 0; i < clientes.length; i++) {
			rs.next();
			clientes[i] = new Cliente(rs.getString(1), rs.getLong(2), rs.getInt(3), TarjetaUQ.parse(rs.getString(4)),
					TarjetaUQ.parse(rs.getString(5)));
		}
		c.close();
		return clientes;
	}

	public static int getCantOfClients () throws SQLException {
		Connection c = DriverManager.getConnection(ruta, "root", "");
		Statement state = c.createStatement();
		ResultSet rs = state.executeQuery("SELECT COUNT(*) AS count FROM clients");
		int ret = -1;
		while (rs.next()) ret = rs.getInt("count");
		return ret;
	}

	public static TarjetaUQ gettBasic (long cc) throws SQLException, CustomException {
		Cliente c = getCliente(cc);
		if (c.toString().equals("n/a")) throw new CustomException("El cliente no existe", 10);
		return c.gettBasic();
	}

	public static TarjetaUQ gettGold (long cc) throws SQLException, CustomException {
		Cliente c = getCliente(cc);
		if (c.toString().equals("n/a")) throw new CustomException("El cliente no existe", 10);
		return c.gettGold();
	}

	private static void updateBasic (long cc, TarjetaUQ t) throws SQLException {
		Connection c = DriverManager.getConnection(ruta, "root", "");
		PreparedStatement state = c.prepareStatement("UPDATE `clients` SET `BASIC` = ? WHERE `clients`.`CC` = ?");
		state.setString(1, t.toString());
		state.setLong(2, cc);
		state.executeUpdate();
		c.close();
	}

	private static void updateGold (long cc, TarjetaUQ t) throws SQLException {
		Connection c = DriverManager.getConnection(ruta, "root", "");
		PreparedStatement state = c.prepareStatement("UPDATE `clients` SET `GOLD` = ? WHERE `clients`.`CC` = ?");
		state.setString(1, t.toString());
		state.setLong(2, cc);
		state.executeUpdate();
		c.close();
	}

	public static void addBasic (long cc, TarjetaUQ t) throws SQLException, CustomException {
		if (gettBasic(cc).getExisteTarjeta()) throw new CustomException("Ya existe la tarjeta basic", 2);
		if (t == null || !t.getExisteTarjeta()) t = new TarjetaUQ();
		updateBasic(cc, t);
	}

	public static void addBasic (long cc) throws SQLException, CustomException {
		addBasic(cc, 0, 0);
	}

	public static void addBasic (long cc, double dinero, int puntos) throws SQLException, CustomException {
		addBasic(cc, new TarjetaUQ(dinero, puntos));
	}

	public static void removeBasic (long cc) throws SQLException, CustomException {
		TarjetaUQ tarjetaBase = gettBasic(cc);
		if (!tarjetaBase.getExisteTarjeta()) throw new CustomException("No hay tarjeta basic que eliminar", 2);
		updateBasic(cc, new TarjetaUQ(-1));
	}

	public static void setDineroBasic (long cc, double dinero) throws SQLException, CustomException {
		TarjetaUQ tarjetaBase = gettBasic(cc);
		if (dinero < 0) throw new CustomException("El dinero no puede ser negativo", -1);
		if (!tarjetaBase.getExisteTarjeta()) throw new CustomException("No hay tarjeta basic", 2);
		tarjetaBase.setDinero(dinero);
		updateBasic(cc, tarjetaBase);
	}

	public static void addDineroBasic (long cc, double dinero) throws SQLException, CustomException {
		TarjetaUQ tarjetaBase = gettBasic(cc);
		tarjetaBase.addDinero(dinero);
		updateBasic(cc, tarjetaBase);
	}

	public static void removeDineroBasic (long cc, double dinero) throws SQLException, CustomException {
		TarjetaUQ tarjetaBase = gettBasic(cc);
		tarjetaBase.removeDinero(dinero);
		updateBasic(cc, tarjetaBase);
	}

	public static void addPuntosBasic (long cc, int puntos) throws SQLException, CustomException {
		TarjetaUQ tarjetaBase = gettBasic(cc);
		tarjetaBase.addPuntos(puntos);
		updateBasic(cc, tarjetaBase);
	}

	public static void removePuntosBasic (long cc, int puntos) throws SQLException, CustomException {
		TarjetaUQ tarjetaBase = gettBasic(cc);
		tarjetaBase.removePuntos(puntos);
		updateBasic(cc, tarjetaBase);
	}

	public static void addGold (long cc, TarjetaUQ t) throws SQLException, CustomException {
		System.out.println(cc);
		if (gettGold(cc).getExisteTarjeta()) throw new CustomException("Ya existe la tarjeta gold", 2);
		if (t == null) t = new TarjetaUQ();
		updateGold(cc, t);
	}

	public static void addGold (long cc) throws SQLException, CustomException {
		addGold(cc, 0, 0);
	}

	public static void addGold (long cc, double dinero, int puntos) throws SQLException, CustomException {
		addGold(cc, new TarjetaUQ(dinero, puntos));
	}

	public static void removeGold (long cc) throws SQLException, CustomException {
		if (!gettGold(cc).getExisteTarjeta()) throw new CustomException("No hay tarjeta gold que eliminar", 1);
		updateGold(cc, new TarjetaUQ(-1));
	}

	public static void setDineroGold (long cc, double dinero) throws SQLException, CustomException {
		TarjetaUQ tarjetaBase = gettGold(cc);
		if (dinero < 0) throw new CustomException("El dinero no puede ser negativo", -1);
		if (!tarjetaBase.getExisteTarjeta()) throw new CustomException("No hay tarjeta gold", 1);
		tarjetaBase.setDinero(dinero);
		updateGold(cc, tarjetaBase);
	}

	public static void addDineroGold (long cc, double dinero) throws SQLException, CustomException {
		TarjetaUQ tarjetaBase = gettGold(cc);
		tarjetaBase.addDinero(dinero);
		updateGold(cc, tarjetaBase);
	}

	public static void removeDineroGold (long cc, double dinero) throws SQLException, CustomException {
		TarjetaUQ tarjetaBase = gettGold(cc);
		tarjetaBase.removeDinero(dinero);
		updateGold(cc, tarjetaBase);
	}

	public static void addPuntosGold (long cc, int puntos) throws SQLException, CustomException {
		TarjetaUQ tarjetaBase = gettGold(cc);
		tarjetaBase.addPuntos(puntos);
		updateGold(cc, tarjetaBase);
	}

	public static void removePuntosGold (long cc, int puntos) throws SQLException, CustomException {
		TarjetaUQ tarjetaBase = gettGold(cc);
		tarjetaBase.removePuntos(puntos);
		updateGold(cc, tarjetaBase);
	}
}
