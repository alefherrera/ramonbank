package com.ramon.ramonbank.dbaccess.tables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.ramon.ramonbank.dbaccess.ExecuteQuery;
import com.ramon.ramonbank.dbaccess.ITables;
import com.ramon.ramonbank.exceptions.OperationException;

public class Cuenta implements ITables {

	private int _id;
	private int _idCliente;
	private int _tipo;
	private int _estado;
	private double _saldo;
	private double _descubierto;

	private ExecuteQuery execute;
	private Logger _log = Logger.getLogger("Log");

	public Cuenta() {
		execute = new ExecuteQuery();
		_id = -1;
		_idCliente = -1;
		_tipo = -1;
		_estado = -1;
		_saldo = -1;
		_descubierto = -1;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public int get_idCliente() {
		return _idCliente;
	}

	public void set_idCliente(int _idCliente) {
		this._idCliente = _idCliente;
	}

	public int get_tipo() {
		return _tipo;
	}

	public void set_tipo(int _tipo) {
		this._tipo = _tipo;
	}

	public int get_estado() {
		return _estado;
	}

	public void set_estado(int i) {
		this._estado = i;
	}

	public double get_saldo() {
		return _saldo;
	}

	public void set_saldo(double _saldo) {
		this._saldo = _saldo;
	}

	public double get_descubierto() {
		return _descubierto;
	}

	public void set_descubierto(double _descubierto) {
		this._descubierto = _descubierto;
	}

	public ExecuteQuery getExecute() {
		return execute;
	}

	public void setExecute(ExecuteQuery execute) {
		this.execute = execute;
	}

	// Acceso a BD
	public ResultSet Select() {
		String Query = new String();
		Query = "call cuentas_select(";
		Query += "'";
		Query += this._id;
		Query += "','";
		Query += this._idCliente;
		Query += "','";
		Query += this._tipo;
		Query += "','";
		Query += this._estado;
		Query += "','";
		Query += this._saldo;
		Query += "','";
		Query += this._descubierto;
		Query += "')";

		return execute.ExecSelect(Query);
	}

	public int Insert() {
		String Query = new String();
		Query = "call cuentas_insert(";
		Query += "'";
		Query += this._idCliente;
		Query += "','";
		Query += this._tipo;
		Query += "',";
		// El query manda true o false, en la base de datos es boolean, se hace
		// asi por filtro del select
		Query += this._estado == 1 ? 1 : 0;
		Query += ",'";
		Query += this._saldo;
		Query += "','";
		Query += this._descubierto;
		Query += "')";

		return execute.ExecInsert(Query);
	}

	public boolean Update() {
		String Query = new String();
		Query = "call cuentas_update(";
		Query += "'";
		Query += this._id;
		Query += "','";
		Query += this._idCliente;
		Query += "','";
		Query += this._tipo;
		Query += "',";
		// El query manda true o false, en la base de datos es boolean, se hace
		// asi por filtro del select
		Query += this._estado == 1 ? 1 : 0;
		Query += ",'";
		Query += this._saldo;
		Query += "','";
		Query += this._descubierto;
		Query += "')";

		return execute.ExecUpdate_Delete(Query);
	}

	public boolean Delete() {
		String Query = new String();
		Query = "call cuentas_delete(";
		Query += "'";
		Query += this._id;
		Query += "')";

		return execute.ExecUpdate_Delete(Query);
	}

	public Cuenta Load() throws OperationException {
		ResultSet rs = this.Select();
		Cuenta oCuenta = new Cuenta();

		try {
			if (rs.next()) {
				oCuenta.set_id(rs.getInt("id"));
				oCuenta.set_idCliente(rs.getInt("idCliente"));
				oCuenta.set_tipo(rs.getInt("Tipo"));
				// Como la base de datos tiene un boolean, tengo que
				// transformarlo a int
				oCuenta.set_estado(rs.getBoolean("Estado") ? 1 : 0);
				oCuenta.set_saldo(rs.getInt("Saldo"));
				oCuenta.set_descubierto(rs.getInt("Descubierto"));
			} else {
				throw new OperationException("No se encontro ningun "
						+ this.getClass().getName());
			}

		} catch (SQLException e) {
			_log.log(Level.WARNING, e.getStackTrace().toString());
		}
		return oCuenta;
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
