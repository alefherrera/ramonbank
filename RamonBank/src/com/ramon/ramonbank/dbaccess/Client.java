package com.ramon.ramonbank.dbaccess;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ramon.ramonbank.RamonBank;

public class Client implements ITables {

	private int _id;
	private String _dni;
	private String _nombre;
	private String _apellido;
	private String _direccion;
	private String _email;
	private ExecuteQuery execute;
	private Logger _log = Logger.getLogger("Log");
	
	public Client() {
		execute = new ExecuteQuery();
		_id = -1;
		_dni = "";
		_nombre = "";
		_apellido = "";
		_direccion = "";
		_email = "";
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
		String Query = new String();
		Query = "call cliente_select(";
		Query += "'";
		Query += this._id;
		Query += "','";
		Query += this._dni;
		Query += "','";
		Query += this._nombre;
		Query += "','";
		Query += this._apellido;
		Query += "','";
		Query += this._direccion;
		Query += "','";
		Query += this._email;
		Query += "')";

		return execute.ExecSelect(Query);
	}

	public int Insert() {
		String Query = new String();
		Query = "call cliente_insert(";
		Query += "'";
		Query += this._dni;
		Query += "','";
		Query += this._nombre;
		Query += "','";
		Query += this._apellido;
		Query += "','";
		Query += this._direccion;
		Query += "','";
		Query += this._email;
		Query += "')";

		return execute.ExecInsert(Query);
	}

	public boolean Update() {
		String Query = new String();
		Query = "call cliente_update(";
		Query += "'";
		Query += this._id;
		Query += "','";
		Query += this._dni;
		Query += "','";
		Query += this._nombre;
		Query += "','";
		Query += this._apellido;
		Query += "','";
		Query += this._direccion;
		Query += "','";
		Query += this._email;
		Query += "')";

		return execute.ExecUpdate_Delete(Query);
	}

	public boolean Delete() {
		String Query = new String();
		Query = "call cliente_delete(";
		Query += "'";
		Query += this._id + "')";
		Query += "')";
		
		return execute.ExecUpdate_Delete(Query);
	}

	public Client Load() {
		ResultSet rs = this.Select();
		Client cliente = new Client();
		try {
			rs.next();
			cliente.set_id(rs.getInt("id"));
			cliente.set_dni(rs.getString("dni"));
			cliente.set_nombre(rs.getString("nombre"));
			cliente.set_apellido(rs.getString("apellido"));
			cliente.set_direccion(rs.getString("direccion"));
			cliente.set_email(rs.getString("eMail"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cliente;
	}
}
