package view;

import java.sql.SQLException;

public interface PagoListener {
	public void pagoRealizado(String infoExtra);

	public void pagoNoRealizado(SQLException e);

	public void volver();
}
