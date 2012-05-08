package com.ramon.ramonbank.dbaccess;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Client implements ITables {

	private int _id;
	private String _dni;
	private String _nombre;
	private String _apellido;
	private String _direccion;
	private String _email;
	private ExecuteQuery execute;

	public Client() {
		execute = new ExecuteQuery();
	}

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

		return execute.ExecSelect(GenerarString("call cliente_select"));
	}

	public int Insert() {
		return execute.ExecInsert(GenerarString("call cliente_insert"));
	}

	public boolean Update() {
		return execute.ExecUpdate_Delete(GenerarString("call cliente_update"));
	}

	public boolean Delete() {
		return execute.ExecUpdate_Delete(GenerarString("call cliente_delete"));
	}

	public Client Load() throws SQLException {
		ResultSet rs = this.Select();
		Client cliente = new Client();
		if (rs.next()) {
			cliente.set_id(rs.getInt("id"));
			cliente.set_dni(rs.getString("dni"));
			cliente.set_nombre(rs.getString("nombre"));
			cliente.set_apellido(rs.getString("apellido"));
			cliente.set_direccion(rs.getString("direccion"));
			cliente.set_email(rs.getString("eMail"));
		}
		return cliente;
	}

	private String GenerarString(String Query) {
		return Query + " (" + +this._id + "," + this._dni + "," + this._nombre
				+ "," + this._apellido + "," + this._direccion + ","
				+ this._email + ")";
	}

}
