package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

import javax.swing.JOptionPane;

import objects.Cinema;
import objects.CustomException;

/**
 * Métodos de la base de datos, los códigos de error de las conexiones son:
 * <li>Sin conexión: 0
 * <li>Las cédulas no se pueden repetir: 1062
 *
 */
public class TablaCinema {
	public static final String ruta = "jdbc:mysql://localhost:3306/cinemadatabase?connectTimeout=5000&socketTimeout=5000";

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
	public static void agregarCinema (Cinema cine) throws SQLException {
		Connection c = DriverManager.getConnection(ruta, "root", "");
		PreparedStatement pst = c.prepareStatement("insert into salas values(?,?,?,?,?,?)");
		pst.setInt(1, cine.getId());
		pst.setString(2, cine.getName());
		pst.setInt(3, cine.getWidth());
		pst.setInt(4, cine.getHeight());
		pst.setString(5, cine.getOcuppied());
		pst.setString(6, cine.getMomentoCine().toString());
		pst.executeUpdate();
		c.close();
	}

	public static String eliminarCinema (int id) throws SQLException, CustomException {
		Cinema cine = getCinemaById(id);
		if (!cine.getExiste()) throw new CustomException("No hay cine que eliminar", CustomException.SALA_NO_EXISTE);
		Connection c = DriverManager.getConnection(ruta, "root", "");
		PreparedStatement pst = c.prepareStatement("DELETE FROM salas WHERE `salas`.`ID` = ?");
		pst.setInt(1, id);
		pst.executeUpdate();
		c.close();
		return cine.getName();
	}

	public static void eliminarAllCinema () throws SQLException {
		Connection c = DriverManager.getConnection(ruta, "root", "");
		PreparedStatement pst = c.prepareStatement("DELETE FROM salas");
		pst.executeUpdate();
		c.close();
	}

	public static boolean[][] getMatrizBooleanById (int id) throws SQLException, CustomException {
		return getCinemaById(id).getMatrizBoolean();
	}

	public static String getNameById (int id) throws SQLException, CustomException {
		return getCinemaById(id).getName();
	}

	public static Cinema getCinemaById (int id) throws SQLException, CustomException {
		Cinema cine = Cinema.ninguno();
		Connection c = DriverManager.getConnection(ruta, "root", "");
		Statement state = c.createStatement();
		ResultSet rs = state.executeQuery("SELECT * FROM `salas` WHERE `ID`=" + id);
		if (rs.next()) cine = new Cinema(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5),
				LocalDateTime.parse(rs.getString(6)));
		c.close();
		if (!cine.getExiste()) {
			throw new CustomException(CustomException.SALA_NO_EXISTE, "El cine con id: " + id + " no fue encontrado");
		}
		return cine;
	}

	public static Cinema[] getAllCinemas () throws SQLException {
		Cinema cines[] = new Cinema[getCantOfCinemas()];
		Connection c = DriverManager.getConnection(ruta, "root", "");
		Statement state = c.createStatement();
		ResultSet rs = state.executeQuery("SELECT * FROM `salas`");
		for (int i = 0; i < cines.length; i++) {
			rs.next();

			int id = rs.getInt(1);
			String name = rs.getString(2);
			int width = rs.getInt(3);
			int height = rs.getInt(4);
			String occupied = rs.getString(5);
			LocalDateTime time = LocalDateTime.parse(rs.getString(6));
			cines[i] = new Cinema(id, name, width, height, occupied, time);
		}
		c.close();
		return cines;
	}

	public static int getCantOfCinemas () throws SQLException {
		Connection c = DriverManager.getConnection(ruta, "root", "");
		Statement state = c.createStatement();
		ResultSet rs = state.executeQuery("SELECT COUNT(*) AS count FROM salas");
		int ret = -1;
		while (rs.next()) ret = rs.getInt("count");
		c.close();
		return ret;
	}

	public static void changeMomentofCinema (int id, LocalDateTime time) throws SQLException, CustomException {
		Cinema cine = getCinemaById(id);
		cine.setMomentoCine(time);
		updateCine(cine);
	}

	public static void changeNameofCinema (int id, String name) throws SQLException, CustomException {
		Cinema cine = getCinemaById(id);
		cine.setName(name);
		updateCine(cine);
	}

	public static void changeOccupiedChairs (int id, String occupied) throws SQLException, CustomException {
		Cinema cine = getCinemaById(id);
		cine.setOcuppied(occupied);
		updateCine(cine);
	}

	public static void changeDimensionsofCinema (int id, int width, int height) throws SQLException, CustomException {
		changeWidthofCinema(id, width);
		changeHeightofCinema(id, height);
	}

	public static void changeHeightofCinema (int id, int height) throws SQLException, CustomException {
		Cinema cine = getCinemaById(id);
		cine.setHeight(height);
		updateCine(cine);
	}

	public static void changeWidthofCinema (int id, int width) throws SQLException, CustomException {
		Cinema cine = getCinemaById(id);
		cine.setWidth(width);
		updateCine(cine);
	}

	public static void updateCine (int id, String name, int width, int height, String occupied,
			LocalDateTime momentoCine) throws SQLException {
		Connection c = DriverManager.getConnection(ruta, "root", "");
		PreparedStatement state = c.prepareStatement("UPDATE `salas` SET `NAME` = ?, `WIDTH` = ?, `HEIGHT` = ?, "
				+ "`OCCUPIED` = ?, " + "`DATE` = ? WHERE `salas`.`ID` = ?");
		state.setString(1, name);
		state.setInt(2, width);
		state.setInt(3, height);
		state.setString(4, occupied);
		state.setString(5, momentoCine.toString());
		state.executeUpdate();
		c.close();
	}

	public static void updateCine (Cinema cine) throws SQLException {
		Connection c = DriverManager.getConnection(ruta, "root", "");
		PreparedStatement state = c.prepareStatement("UPDATE `salas` SET `NAME` = ?, `WIDTH` = ?, `HEIGHT` = ?, "
				+ "`OCCUPIED` = ?, " + "`DATE` = ? WHERE `salas`.`ID` = ?");
		state.setString(1, cine.getName());
		state.setInt(2, cine.getWidth());
		state.setInt(3, cine.getHeight());
		state.setString(4, cine.getOcuppied());
		state.setString(5, cine.getMomentoCine().toString());
		state.setInt(6, cine.getId());
		state.executeUpdate();
		c.close();
	}

	public static void updateSillas (int idCine, String occupied) throws SQLException {
		Connection c = DriverManager.getConnection(ruta, "root", "");
		PreparedStatement state = c.prepareStatement("UPDATE `salas` SET `OCCUPIED` = ? WHERE `salas`.`ID` = ?");
		state.setInt(1, idCine);
		state.setString(2, occupied);
		state.executeUpdate();
		c.close();
	}
}
