
package com.ramon.ramonbank.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionHandler implements IConnectionHandler {

	public ConnectionHandler() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver de base de datos no encontrada.");
		}
	}

	@Override
	public Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://nacho692.dyndns.org:3306/ramonbank","ale","ale123");
		} catch (SQLException e) {
			System.out.println("Error al abrir conexion con base de datos.");
			e.printStackTrace();
		}
		return connection;
	}


	@Override
	public void closeResources(Statement st, Connection cn) {
		
	}
	

	@Override
	public void closeResources(ResultSet rs, Statement st, Connection cn) {
		try {
			if (rs != null)
				rs.close();									
		} catch (SQLException e) {
			System.out.println("Error al cerrar recursos de acceso a datos.");
			e.printStackTrace();
		}
		closeResources(st, cn);		
	}


	@Override
	public void closeResources(PreparedStatement ps, Connection cn) {
		try {
			if (ps != null)
				ps.close();									
		} catch (SQLException e) {
			System.out.println("Error al cerrar recursos de acceso a datos.");
			e.printStackTrace();
		}
		closeConnection(cn);
	}
	
	private void closeConnection(Connection cn) {
		try {
			if (cn != null)
				cn.close();						
		} catch (SQLException e) {
			System.out.println("Error al cerrar la conexion a BD.");
			e.printStackTrace();
		}		
	}

}
