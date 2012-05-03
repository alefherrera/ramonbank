package com.ramon.ramonbank.dbaccess;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.Connection;
import com.ramon.ramonbank.connection.ConnectionHandler;

public class Client implements ITables {

	private int _id;
	private String _dni;
	private String _nombre;
	private String _apellido;
	private String _direccion;
	private String _email;

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String get_dni() {
		return _dni;
	}

	public void set_dni(String _dni) {
		this._dni = _dni;
	}

	public String get_nombre() {
		return _nombre;
	}

	public void set_nombre(String _nombre) {
		this._nombre = _nombre;
	}

	public String get_apellido() {
		return _apellido;
	}

	public void set_apellido(String _apellido) {
		this._apellido = _apellido;
	}

	public String get_direccion() {
		return _direccion;
	}

	public void set_direccion(String _direccion) {
		this._direccion = _direccion;
	}

	public String get_email() {
		return _email;
	}

	public void set_email(String _email) {
		this._email = _email;
	}

	// Acceso a BD

	public ResultSet Select() {
		ConnectionHandler cnHandler = new ConnectionHandler();
		Connection con = cnHandler.getConnection();
		Statement st = null;
		try {
			st = con.createStatement();
			st.execute("CALL cliente_Select");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			cnHandler.closeResources(st, con);
		}
		return null;
	}

	public int Insert() {
		return 0;
	}

	public boolean Update() {
		return false;
	}

	public boolean Delete() {
		return false;
	}

	public Client Load() {
		return null;
	}

}
