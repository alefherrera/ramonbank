package com.ramon.ramonbank.dbaccess.tables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ramon.ramonbank.dbaccess.ExecuteQuery;
import com.ramon.ramonbank.dbaccess.ITables;

public class Prestamo implements ITables {
	private ExecuteQuery execute;

	private Logger _log = Logger.getLogger("Log");
	
	private int _id;
	private Date _fechaAlta;
	private double _monto;
	private int _cantCuotas;
	private double _interes;
	private int _idCliente;
	private int _idCuenta;
	
	public Prestamo() {
		execute = new ExecuteQuery();
		SimpleDateFormat _dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		
		this._id = -1;
		try {
			this._fechaAlta = _dateFormat.parse("01-01-1900");
		} catch (ParseException e) {
			_log.log(Level.SEVERE, "Error al parsear formato de fecha \n" + e.getMessage());
		}
		
		this._monto = -1;
		this._cantCuotas = -1;
		this._interes = -1;
		this._idCliente = -1;
		this._idCuenta = -1;
	}

	
	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public Date get_fechaAlta() {
		return _fechaAlta;
	}

	public void set_fechaAlta(Date _fechaAlta) {
		this._fechaAlta = _fechaAlta;
	}

	public double get_monto() {
		return _monto;
	}

	public void set_monto(double _monto) {
		this._monto = _monto;
	}

	public int get_cantCuotas() {
		return _cantCuotas;
	}

	public void set_cantCuotas(int _cantCuotas) {
		this._cantCuotas = _cantCuotas;
	}

	public double get_interes() {
		return _interes;
	}

	public void set_interes(double _interes) {
		this._interes = _interes;
	}

	public int get_idCliente() {
		return _idCliente;
	}

	public void set_idCliente(int _idCliente) {
		this._idCliente = _idCliente;
	}

	public int get_idCuenta() {
		return _idCuenta;
	}

	public void set_idCuenta(int _idCuenta) {
		this._idCuenta = _idCuenta;
	}

		// Acceso a BD
		public ResultSet Select() {
			String Query = new String();
			Query = "call prestamos_select(";
			Query += "'";
			Query += this._id;
			Query += "','";
			Query += this._fechaAlta;
			Query += "','";
			Query += this._monto;
			Query += "','";
			Query += this._cantCuotas;
			Query += "','";
			Query += this._interes;
			Query += "','";
			Query += this._idCliente;
			Query += "','";
			Query += this._idCuenta;
			Query += "','";
			Query += "')";

			return execute.ExecSelect(Query);
		}

		public int Insert() {
			String Query = new String();
			Query = "call prestamos_insert(";
			Query += "'";
			Query += this._fechaAlta;
			Query += "','";
			Query += this._monto;
			Query += "','";
			Query += this._cantCuotas;
			Query += "','";
			Query += this._interes;
			Query += "','";
			Query += this._idCliente;
			Query += "','";
			Query += this._idCuenta;
			Query += "')";

			return execute.ExecInsert(Query);
		}

		public boolean Update() {
			String Query = new String();
			Query = "call prestamos_update(";
			Query += "'";
			Query += this._id;
			Query += "','";
			Query += this._fechaAlta;
			Query += "','";
			Query += this._monto;
			Query += "','";
			Query += this._cantCuotas;
			Query += "','";
			Query += this._interes;
			Query += "','";
			Query += this._idCliente;
			Query += "','";
			Query += this._idCuenta;
			Query += "')";

			return execute.ExecUpdate_Delete(Query);
		}

		public boolean Delete() {
			String Query = new String();
			Query = "call prestamos_delete(";
			Query += "'";
			Query += this._id;
			Query += "')";

			return execute.ExecUpdate_Delete(Query);
		}

		public Cuenta Load() {
			ResultSet rs = this.Select();
			Cuenta oCuenta = new Cuenta();

			try {
				if (rs.next()) {
					oCuenta.set_id(rs.getInt("id"));
					oCuenta.set_idCliente(rs.getInt("idCliente"));
					oCuenta.set_tipo(rs.getInt("Tipo"));
					//Como la base de datos tiene un boolean, tengo que transformarlo a int
					oCuenta.set_estado(rs.getBoolean("Estado")?1:0);
					oCuenta.set_saldo(rs.getInt("Saldo"));
					oCuenta.set_descubierto(rs.getInt("Descubierto"));
				}
			} catch (SQLException e) {
				_log.log(Level.WARNING, e.getStackTrace().toString());
			}
			return oCuenta;
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
