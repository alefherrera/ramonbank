package com.ramon.ramonbank.dbaccess.tables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ramon.ramonbank.dbaccess.ITables;
import com.ramon.ramonbank.dbaccess.Tables;
import com.ramon.ramonbank.exceptions.OperationException;
import com.ramon.ramonbank.utils.Fecha;

public class PagoPrestamo extends Tables {
	

	private Logger _log = Logger.getLogger("Log");

	

	private int _id;
	private Fecha _fecha;
	private int _origen;
	private int _idPrestamo;
	private int _cantCuotas;
	private int _monto;
	private Fecha _filtro_fechaDesde;
	private Fecha _filtro_fechaHasta;
	
	public PagoPrestamo()
	{
		
		this._id = -1;
		this._fecha = new Fecha();
		this._origen = -1;
		this._idPrestamo = -1;
		this._cantCuotas = -1;
		this._monto = -1;
		this._filtro_fechaDesde = new Fecha();
		this._filtro_fechaHasta = new Fecha();
	}
	
	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String get_fecha() {
		return _fecha.get_Fecha();
	}

	public void set_fecha(String fecha) {
		this._fecha.set_Fecha(fecha);
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



	public ResultSet Select() {
		Lista.clear();
		Lista.add(this._id);		
		Lista.add(this._filtro_fechaDesde);
		Lista.add(this._filtro_fechaHasta);
		Lista.add(this._origen);
		Lista.add(this._idPrestamo);
		Lista.add(this._cantCuotas);
		Lista.add(this._monto);		
		return super.Select(Lista);		
	}
	
	
	public int Insert() {
		Lista.clear();
		Lista.add(this._origen);		
		Lista.add(this._idPrestamo);
		Lista.add(this._cantCuotas);
		Lista.add(this._monto);				
		return super.Insert(Lista);		
	}

	public boolean Update() {
		Lista.clear();
		Lista.add(this._id);		
		Lista.add(this._origen);
		Lista.add(this._idPrestamo);
		Lista.add(this._cantCuotas);
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
