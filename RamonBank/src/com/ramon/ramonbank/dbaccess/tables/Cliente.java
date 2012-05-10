package com.ramon.ramonbank.dbaccess.tables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ramon.ramonbank.RamonBank;
import com.ramon.ramonbank.dbaccess.ExecuteQuery;
import com.ramon.ramonbank.dbaccess.ITables;

public class Cliente implements ITables {

	private int _id;
	private String _dni;
	private String _nombre;
	private String _apellido;
	private String _direccion;
	private String _email;
	private ExecuteQuery execute;
	private Logger _log = Logger.getLogger("Log");

	public Cliente() {
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
		Query = "call clientes_select(";
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
		Query = "call clientes_insert(";
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
		Query = "call clientes_update(";
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
		Query = "call clientes_delete(";
		Query += "'";
		Query += this._id;
		Query += "')";

		return execute.ExecUpdate_Delete(Query);
	}

	public Cliente Load() {
		ResultSet rs = this.Select();
		Cliente oCliente = new Cliente();

		try {
			if (rs.next()) {
				oCliente.set_id(rs.getInt("id"));
				oCliente.set_dni(rs.getString("dni"));
				oCliente.set_nombre(rs.getString("nombre"));
				oCliente.set_apellido(rs.getString("apellido"));
				oCliente.set_direccion(rs.getString("direccion"));
				oCliente.set_email(rs.getString("eMail"));
			}
		} catch (SQLException e) {
			_log.log(Level.WARNING, e.getStackTrace().toString());
		}
		return oCliente;
	}
	
	public int Cantidad()
	{
		ResultSet rs = this.Select();
		try {
			rs.last();
		return rs.getRow();
		} catch (SQLException e) {
			_log.log(Level.WARNING, e.getStackTrace().toString());
		}
		return -1;
	}
}
