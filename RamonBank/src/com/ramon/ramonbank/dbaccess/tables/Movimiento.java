package com.ramon.ramonbank.dbaccess.tables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ramon.ramonbank.dbaccess.ITables;
import com.ramon.ramonbank.dbaccess.Tables;
import com.ramon.ramonbank.exceptions.OperationException;
import com.ramon.ramonbank.utils.Fecha;


public class Movimiento extends Tables {
	
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
		return _fecha.get_Fecha();
	}

	public void set_fecha(String fecha) {
		this._fecha.set_Fecha(fecha);
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
		return _filtro_fechaDesde.get_Fecha();
	}

	public void set_filtro_fechaDesde(String filtro_fechaDesde) {
		this._filtro_fechaDesde.set_Fecha(filtro_fechaDesde);
	}

	public String get_filtro_fechaHasta() {
		return _filtro_fechaHasta.get_Fecha();
	}

	public void set_filtro_fechaHasta(String filtro_fechaHasta) {
		this._filtro_fechaHasta.set_Fecha(filtro_fechaHasta);
	}

	private int _idcuenta;
	private Fecha _fecha;
	private int _tipo;
	private int _origen;
	private double _saldo;
	private double _monto;
	private Fecha _filtro_fechaDesde;
	private Fecha _filtro_fechaHasta;

	private Logger _log = Logger.getLogger("Log");
	
	public Movimiento() {		
		this._id = -1;
		this._idcuenta = -1;
		this._fecha = new Fecha();
		this._tipo = -1;
		this._origen = -1;
		this._saldo = -1;
		this._monto = -1;
		this._filtro_fechaDesde = new Fecha();
		this._filtro_fechaHasta = new Fecha();
	}
	

	public ResultSet Select() {
		
		Lista.clear();
		Lista.add(this._id);		
		Lista.add(this._filtro_fechaDesde);
		Lista.add(this._filtro_fechaHasta);
		Lista.add(this._tipo);
		Lista.add(this._origen);
		Lista.add(this._saldo);
		Lista.add(this._monto);
		return super.Select(Lista);	

	}


	public int Insert() {
		Lista.clear();
		Lista.add(this._idcuenta);
		Lista.add(this._tipo);
		Lista.add(this._origen);
		Lista.add(this._saldo);
		Lista.add(this._monto);
		return super.Insert(Lista);	
	}

	public boolean Update() {
		Lista.clear();
		Lista.add(this._id);
		Lista.add(this._idcuenta);
		Lista.add(this._tipo);
		Lista.add(this._origen);
		Lista.add(this._saldo);
		Lista.add(this._monto);
		return super.Update(Lista);		
	}

	public boolean Delete() {
		Lista.clear();
		Lista.add(this._id);		
		return super.Delete(Lista);	
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
