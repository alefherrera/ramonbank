package com.ramon.ramonbank.dbaccess.tables;

import java.sql.ResultSet;
import java.sql.SQLException;
//import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ramon.ramonbank.dbaccess.ExecuteQuery;
import com.ramon.ramonbank.dbaccess.ITables;
import com.ramon.ramonbank.exceptions.OperationException;

public class Prestamo implements ITables {
	public String get_filtro_fechaDesde() {
		return _filtro_fechaDesde;
	}


	public void set_filtro_fechaDesde(String _filtro_fechaDesde) {
		this._filtro_fechaDesde = _filtro_fechaDesde;
	}


	public String get_filtro_fechaHasta() {
		return _filtro_fechaHasta;
	}


	public void set_filtro_fechaHasta(String _filtro_fechaHasta) {
		this._filtro_fechaHasta = _filtro_fechaHasta;
	}

	private ExecuteQuery execute;

	private Logger _log = Logger.getLogger("Log");
	//private SimpleDateFormat _dateFormat = new SimpleDateFormat("yyyy-dd-MM");
	
	private int _id;
	private String _fechaAlta;
	private double _monto;
	private int _cantCuotas;
	private double _interes;
	private int _idCliente;
	private int _idCuenta;
	private String _filtro_fechaDesde;
	private String _filtro_fechaHasta;
	
	public Prestamo() {
		execute = new ExecuteQuery();
		
		this._id = -1;
		this._fechaAlta = "0";
		this._filtro_fechaDesde = "0";
		this._filtro_fechaHasta = "0";
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

	public String get_fechaAlta() {
		return _fechaAlta;
	}

	public void set_fechaAlta(String _fechaAlta) {
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
			Query += "',";
			Query += this._filtro_fechaDesde;
			Query += ",";
			Query += this._filtro_fechaHasta;
			Query += ",'";
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

			return execute.ExecSelect(Query);
		}

		public int Insert() {
			String Query = new String();
			Query = "call prestamos_insert(";
			Query += "'";
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

		public Prestamo Load() throws OperationException {
			ResultSet rs = this.Select();
			Prestamo oPrestamo = new Prestamo();

			try {
				if (rs.next()) {
					oPrestamo.set_id(rs.getInt("id"));
					oPrestamo.set_fechaAlta(rs.getTime("FechaAlta").toString());
					oPrestamo.set_monto(rs.getDouble("Monto"));
					oPrestamo.set_cantCuotas(rs.getInt("CantCuotas"));
					oPrestamo.set_interes(rs.getDouble("Interes"));
					oPrestamo.set_idCliente(rs.getInt("idCliente"));
					oPrestamo.set_idCuenta(rs.getInt("idCuenta"));
				} else {
					throw new OperationException("No se encontro ningun " + this.getClass().getName());
			}

			} catch (SQLException e) {
				_log.log(Level.WARNING, e.getStackTrace().toString() + "\n" + e.getMessage());
			}
			return oPrestamo;
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
