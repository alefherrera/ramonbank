package com.ramon.ramonbank.dbaccess.tables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ramon.ramonbank.RamonBank;
import com.ramon.ramonbank.dbaccess.ExecuteQuery;
import com.ramon.ramonbank.dbaccess.ITables;
import com.ramon.ramonbank.exceptions.OperationException;


public class Movimiento implements ITables {
	
	private int _id;
	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public int get_idcuenta() {
		return _idcuenta;
	}

	public void set_idcuenta(int _idcuenta) {
		this._idcuenta = _idcuenta;
	}

	public String get_fecha() {
		return _fecha;
	}

	public void set_fecha(String _fecha) {
		this._fecha = _fecha;
	}

	public int get_tipo() {
		return _tipo;
	}

	public void set_tipo(int _tipo) {
		this._tipo = _tipo;
	}

	public int get_origen() {
		return _origen;
	}

	public void set_origen(int _origen) {
		this._origen = _origen;
	}

	public double get_saldo() {
		return _saldo;
	}

	public void set_saldo(double _saldo) {
		this._saldo = _saldo;
	}

	public double get_monto() {
		return _monto;
	}

	public void set_monto(double _monto) {
		this._monto = _monto;
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

	public Logger get_log() {
		return _log;
	}

	public void set_log(Logger _log) {
		this._log = _log;
	}

	private int _idcuenta;
	private String _fecha;
	private int _tipo;
	private int _origen;
	private double _saldo;
	private double _monto;
	private String _filtro_fechaDesde;
	private String _filtro_fechaHasta;

	private ExecuteQuery execute;

	private Logger _log = Logger.getLogger("Log");
	
	public Movimiento() {
		execute = new ExecuteQuery();
		
		this._id = -1;
		this._idcuenta = -1;
		this._fecha = "0";
		this._tipo = -1;
		this._origen = -1;
		this._saldo = -1;
		this._monto = -1;
		this._filtro_fechaDesde = "0";
		this._filtro_fechaHasta = "0";
	}
	
	@Override
	public ResultSet Select() {
		String Query = new String();
		Query = "call movimientos_select(";
		Query += "'";
		Query += this._id;
		Query += "',";
		Query += this._filtro_fechaDesde;
		Query += ",";
		Query += this._filtro_fechaHasta;
		Query += ",'";
		Query += this._tipo;
		Query += "','";
		Query += this._origen;
		Query += "','";
		Query += this._saldo;
		Query += "','";
		Query += this._monto;
		Query += "')";

		return execute.ExecSelect(Query);

	}

	@Override
	public int Insert() {
		String Query = new String();
		Query = "call movimientos_insert(";
		Query += "'";
		Query += this._idcuenta;
		Query += "','";
		Query += this._tipo;
		Query += "','";
		Query += this._origen;
		Query += "','";
		Query += this._saldo;
		Query += "','";
		Query += this._monto;
		Query += "')";

		return execute.ExecInsert(Query);

	}

	@Override
	public boolean Update() {
		String Query = new String();
		Query = "call movimientos_update(";
		Query += "'";
		Query += this._id;
		Query += "','";		
		Query += this._idcuenta;
		Query += "','";
		Query += this._tipo;
		Query += "','";
		Query += this._origen;
		Query += "','";
		Query += this._saldo;
		Query += "','";
		Query += this._monto;
		Query += "')";

		return execute.ExecUpdate_Delete(Query);
	}

	@Override
	public boolean Delete() {
		String Query = new String();
		Query = "call movimientos_delete(";
		Query += "'";
		Query += this._id;
		Query += "')";

		return execute.ExecUpdate_Delete(Query);
	}

	@Override
	public ITables Load() throws OperationException {
		ResultSet rs = this.Select();
		Movimiento oMovimiento = new Movimiento();

		try {
			if (rs.next()) {
				oMovimiento.set_id(rs.getInt("id"));
				oMovimiento.set_idcuenta(rs.getInt("idCuenta"));
				oMovimiento.set_fecha(rs.getTime("Fecha").toString());
				oMovimiento.set_tipo(rs.getInt("Tipo"));
				oMovimiento.set_origen(rs.getInt("Origen"));
				oMovimiento.set_saldo(rs.getDouble("Saldo"));
				oMovimiento.set_monto(rs.getDouble("Monto"));			
			} else {
				throw new OperationException("No se encontro ningun " + this.getClass().getName());
		}

		} catch (SQLException e) {
			_log.log(Level.WARNING, e.getStackTrace().toString() + "\n" + e.getMessage());
		}
		return oMovimiento;
	}

	@Override
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
