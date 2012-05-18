package com.ramon.ramonbank.dbaccess.tables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ramon.ramonbank.dbaccess.Tables;
import com.ramon.ramonbank.exceptions.OperationException;

public class Cliente extends Tables {

	private int _id;
	private String _dni;
	private String _nombre;
	private String _apellido;
	private String _direccion;
	private String _email;
	private Logger _log = Logger.getLogger("Log");

	public Cliente() {
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
		
		Lista.clear();
		Lista.add(this._id);		
		Lista.add(this._dni);
		Lista.add(this._nombre);
		Lista.add(this._apellido);
		Lista.add(this._direccion);
		Lista.add(this._email);	
		return super.Select(Lista);	
		
	}

	public int Insert() {
		
		Lista.clear();
		Lista.add(this._id);
		Lista.add(this._nombre);
		Lista.add(this._apellido);
		Lista.add(this._direccion);
		Lista.add(this._email);	
		return super.Insert(Lista);
	}

	public boolean Update() {
		
		Lista.clear();
		Lista.add(this._id);
		Lista.add(this._dni);
		Lista.add(this._nombre);
		Lista.add(this._apellido);
		Lista.add(this._direccion);
		Lista.add(this._email);	
		return super.Update(Lista);
	}

	public boolean Delete() {
		Lista.clear();
		Lista.add(this._id);		
		return super.Delete(Lista);	
	}

	public Cliente Load() throws OperationException {
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
			} else {
					throw new OperationException("No se encontro ningun " + this.getClass().getName());
			}

		} catch (SQLException e) {
			_log.log(Level.WARNING, e.getStackTrace().toString());
		}
		return oCliente;
	}

	public int Cantidad() {
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
