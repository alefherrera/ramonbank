package com.ramon.ramonbank.dbaccess.tables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ramon.ramonbank.dbaccess.ITables;
import com.ramon.ramonbank.dbaccess.Tables;
import com.ramon.ramonbank.exceptions.OperationException;
import com.ramon.ramonbank.utils.Fecha;


public class PlazosFijos extends Tables {

	 
	private Logger _log = Logger.getLogger("Log");
	
	
	private int _id;
	private Fecha _fechaAlta;
	private Fecha _fechaVencimiento;
	private int _idCliente;
	private int _origen;
	private int _nroCuentaOrigen;
	private int _acreditacion;
	private int _nroCuentaDestino;
	private double _monto;
	private double _interes;
	private Fecha _filtro_fechaDesde;
	private Fecha _filtro_fechaHasta;
	private Fecha _filtro_fechaVencimientoDesde;
	private Fecha _filtro_fechaVencimientoHasta;
	 
	public int get_id() {
		return _id;
	}


	public void set_id(int _id) {
		this._id = _id;
	}


	public String get_fechaAlta() {
		return _fechaAlta.get_Fecha();
	}


	public void set_fechaAlta(String _Fecha) {
		this._fechaAlta.set_Fecha(_Fecha);
	}


	public String get_fechaVencimiento() {
		return _fechaVencimiento.get_Fecha();
	}


	public void set_fechaVencimiento(String _Fecha) {
		this._fechaVencimiento.set_Fecha(_Fecha);
	}


	public int get_idCliente() {
		return _idCliente;
	}


	public void set_idCliente(int _idCliente) {
		this._idCliente = _idCliente;
	}


	public int get_origen() {
		return _origen;
	}


	public void set_origen(int _origen) {
		this._origen = _origen;
	}


	public int get_nroCuentaOrigen() {
		return _nroCuentaOrigen;
	}


	public void set_nroCuentaOrigen(int _nroCuentaOrigen) {
		this._nroCuentaOrigen = _nroCuentaOrigen;
	}


	public double get_acreditacion() {
		return _acreditacion;
	}


	public void set_acreditacion(int d) {
		this._acreditacion = d;
	}


	public int get_nroCuentaDestino() {
		return _nroCuentaDestino;
	}


	public void set_nroCuentaDestino(int _nroCuentaDestino) {
		this._nroCuentaDestino = _nroCuentaDestino;
	}


	public double get_monto() {
		return _monto;
	}


	public void set_monto(double _monto) {
		this._monto = _monto;
	}


	public double get_interes() {
		return _interes;
	}


	public void set_interes(double _interes) {
		this._interes = _interes;
	}



	public String get_filtro_fechaDesde() {
		return _filtro_fechaDesde.get_Fecha();
	}


	public void set_filtro_fechaDesde(String _Fecha) {
		this._filtro_fechaDesde.set_Fecha(_Fecha);
	}


	public String get_filtro_fechaHasta() {
		return _filtro_fechaHasta.get_Fecha();
	}


	public void set_filtro_fechaHasta(String _Fecha) {
		this._filtro_fechaHasta.set_Fecha(_Fecha);
	}


	public String get_filtro_fechaVencimientoDesde() {
		return _filtro_fechaVencimientoDesde.get_Fecha();
	}


	public void set_filtro_fechaVencimientoDesde(String _Fecha) {
		this._filtro_fechaVencimientoDesde.set_Fecha(_Fecha);
	}


	public String get_filtro_fechaVencimientoHasta() {
		return _filtro_fechaVencimientoHasta.get_Fecha();
	}


	public void set_filtro_fechaVencimientoHasta(String _Fecha) {
		this._filtro_fechaVencimientoHasta.set_Fecha(_Fecha);
	}


	
	public PlazosFijos() {		
		this._id = -1;
		this._fechaAlta = new Fecha();
		this._fechaVencimiento = new Fecha();
		this._idCliente = -1;
		this._origen = -1;
		this._nroCuentaOrigen = -1;
		this._monto = -1;
		this._interes = -1;
		this._filtro_fechaDesde = new Fecha();
		this._filtro_fechaHasta = new Fecha();
		this._filtro_fechaVencimientoDesde = new Fecha();
		this._filtro_fechaVencimientoHasta = new Fecha();
	}
	

	public ResultSet Select() {
		
		Lista.clear();
		Lista.add(this._id);		
		Lista.add(this._filtro_fechaDesde);
		Lista.add(this._filtro_fechaHasta);
		Lista.add(this._filtro_fechaVencimientoDesde);
		Lista.add(this._filtro_fechaVencimientoHasta);
		Lista.add(this._idCliente);
		Lista.add(this._origen);
		Lista.add(this._nroCuentaOrigen);
		Lista.add(this._monto);
		Lista.add(this._interes);
		return super.Select(Lista);	

	}


	public int Insert() {
		Lista.clear();		
		Lista.add(this._fechaVencimiento);
		Lista.add(this._idCliente);
		Lista.add(this._origen);
		Lista.add(this._nroCuentaOrigen);
		Lista.add(this._monto);
		Lista.add(this._interes);
		return super.Insert(Lista);	
	}

	public boolean Update() {
		Lista.clear();
		Lista.add(this._id);
		Lista.add(this._fechaVencimiento);
		Lista.add(this._idCliente);
		Lista.add(this._origen);
		Lista.add(this._nroCuentaOrigen);
		Lista.add(this._monto);
		Lista.add(this._interes);
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
		PlazosFijos oPlazosFijos = new PlazosFijos();

		try {
			if (rs.next()) {
				oPlazosFijos.set_id(rs.getInt("id"));
				oPlazosFijos.set_fechaAlta(rs.getTime("FechaAlta").toString());
				oPlazosFijos.set_fechaVencimiento(rs.getTime("FechaAlta").toString());
				oPlazosFijos.set_idCliente(rs.getInt("idCliente"));
				oPlazosFijos.set_origen(rs.getInt("Origen"));
				oPlazosFijos.set_nroCuentaOrigen(rs.getInt("NroCuentaOrigen"));
				oPlazosFijos.set_monto(rs.getDouble("Monto"));
				oPlazosFijos.set_monto(rs.getDouble("Interes"));
			} else {
				throw new OperationException("No se encontro ningun " + this.getClass().getName());
		}

		} catch (SQLException e) {
			_log.log(Level.WARNING, e.getStackTrace().toString() + "\n" + e.getMessage());
		}
		return oPlazosFijos;
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
	
	public ArrayList<PlazosFijos> LoadList() throws OperationException {
		ArrayList<PlazosFijos> arrayPlazosFijos = new ArrayList<PlazosFijos>();

		ResultSet rs = this.Select();
		PlazosFijos oPlazosFijos;

		try {
			while (rs.next()) {
				oPlazosFijos = new PlazosFijos();
				oPlazosFijos.set_id(rs.getInt("id"));
				oPlazosFijos.set_fechaAlta(rs.getTime("FechaAlta").toString());
				oPlazosFijos.set_fechaVencimiento(rs.getTime("FechaAlta").toString());
				oPlazosFijos.set_idCliente(rs.getInt("idCliente"));
				oPlazosFijos.set_origen(rs.getInt("Origen"));
				oPlazosFijos.set_nroCuentaOrigen(rs.getInt("NroCuentaOrigen"));
				oPlazosFijos.set_monto(rs.getDouble("Monto"));
				oPlazosFijos.set_monto(rs.getDouble("Interes"));
				arrayPlazosFijos.add(oPlazosFijos);
			}
		}

		catch (SQLException e) {
			_log.log(Level.WARNING,
					e.getStackTrace().toString() + "\n" + e.getMessage());
		}
		return arrayPlazosFijos;
	}

}