package com.ramon.ramonbank.dbaccess.tables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ramon.ramonbank.dbaccess.ExecuteQuery;
import com.ramon.ramonbank.dbaccess.ITables;
import com.ramon.ramonbank.exceptions.OperationException;

public class PagoPrestamo implements ITables {
	
	private ExecuteQuery execute;

	private Logger _log = Logger.getLogger("Log");

	

	private int _id;
	private String _fecha;
	private int _origen;
	private int _idPrestamo;
	private int _cantCuotas;
	private int _monto;
	private String _filtro_fechaDesde;
	private String _filtro_fechaHasta;
	

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String get_fecha() {
		return _fecha;
	}

	public void set_fecha(String _fecha) {
		this._fecha = _fecha;
	}

	public int get_origen() {
		return _origen;
	}

	public void set_origen(int _origen) {
		this._origen = _origen;
	}

	public int get_idPrestamo() {
		return _idPrestamo;
	}

	public void set_idPrestamo(int _idcliente) {
		this._idPrestamo = _idcliente;
	}

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


	public int get_cantCuotas() {
		return _cantCuotas;
	}

	public void set_cantCuotas(int _cantCuotas) {
		this._cantCuotas = _cantCuotas;
	}

	public int get_monto() {
		return _monto;
	}

	public void set_monto(int _monto) {
		this._monto = _monto;
	}



	@Override
	public ResultSet Select() {
		String Query = new String();
		Query = "call pagoprestamos_select(";
		Query += "'";
		Query += this._id;
		Query += "',";
		Query += this._filtro_fechaDesde;
		Query += ",";
		Query += this._filtro_fechaHasta;
		Query += ",'";
		Query += this._origen;
		Query += "','";
		Query += this._idPrestamo;
		Query += ",'";
		Query += this._cantCuotas;
		Query += "','";
		Query += this._monto;
		Query += "')";

		return execute.ExecSelect(Query);
	}
	
	
	@Override
	public int Insert() {
		String Query = new String();
		Query = "call pagoprestamos_insert(";
		Query += "'";
		Query += this._origen;
		Query += "','";
		Query += this._idPrestamo;
		Query += ",'";
		Query += this._cantCuotas;
		Query += "','";
		Query += this._monto;
		Query += "')";

		return execute.ExecInsert(Query);
	}

	@Override
	public boolean Update() {
		String Query = new String();
		Query = "call pagoprestamos_update(";
		Query += "'";
		Query += this._id;
		Query += "','";
		Query += this._origen;
		Query += "','";
		Query += this._idPrestamo;
		Query += ",'";
		Query += this._cantCuotas;
		Query += "','";
		Query += this._monto;
		Query += "')";

		return execute.ExecUpdate_Delete(Query);
	}

	@Override
	public boolean Delete() {
		String Query = new String();
		Query = "call pagoprestamos_delete(";
		Query += "'";
		Query += this._id;
		Query += "')";

		return execute.ExecUpdate_Delete(Query);
	}

	@Override
	public ITables Load() throws OperationException {
		ResultSet rs = this.Select();
		PagoPrestamo oPagoPrestamo = new PagoPrestamo();

		try {
			if (rs.next()) {
				oPagoPrestamo.set_id(rs.getInt("id"));
				oPagoPrestamo.set_fecha(rs.getTime("Fecha").toString());
				oPagoPrestamo.set_origen(rs.getInt("Origen"));
				oPagoPrestamo.set_idPrestamo(rs.getInt("idPrestamo"));
				oPagoPrestamo.set_cantCuotas(rs.getInt("CantCuotas"));
				oPagoPrestamo.set_monto(rs.getInt("Monto"));
			} else {
				throw new OperationException("No se encontro ningun " + this.getClass().getName());
		}

		} catch (SQLException e) {
			_log.log(Level.WARNING, e.getStackTrace().toString() + "\n" + e.getMessage());
		}
		return oPagoPrestamo;
	}

	@Override
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
