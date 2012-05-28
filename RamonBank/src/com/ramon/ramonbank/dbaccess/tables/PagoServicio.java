package com.ramon.ramonbank.dbaccess.tables;

import java.sql.ResultSet;
import java.sql.SQLException;
//import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ramon.ramonbank.dbaccess.Tables;
import com.ramon.ramonbank.exceptions.OperationException;
import com.ramon.ramonbank.utils.Fecha;

public class PagoServicio extends Tables {
	
	private Logger _log = Logger.getLogger("Log");
	//private SimpleDateFormat _dateFormat = new SimpleDateFormat("yyyy-dd-MM");
	
	private int _id;
	private Fecha _fechaAlta;
	private double _nroCuenta;
	private Fecha _filtro_fechaDesde;
	private Fecha _filtro_fechaHasta;

	
	public PagoServicio() {
		
		this._id = -1;
		this._fechaAlta = new Fecha();
		this._nroCuenta = -1;
	}

	
	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String get_fechaAlta() {
		return _fechaAlta.get_Fecha();
	}

	public void set_fechaAlta(String fecha) {
		this._fechaAlta.set_Fecha(fecha);
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
	public double get_nroCuenta() {
		return _nroCuenta;
	}

	public void set_nroCuenta(double _nroCuenta) {
		this._nroCuenta = _nroCuenta;
	}



		// Acceso a BD
		public ResultSet Select() {
			Lista.clear();
			Lista.add(this._id);		
			Lista.add(this._filtro_fechaDesde);
			Lista.add(this._filtro_fechaHasta);
			Lista.add(this._nroCuenta);
			return super.Select(Lista);	
		}

		public int Insert() {
			Lista.clear();
			Lista.add(this._nroCuenta);
			return super.Insert(Lista);				
		}

		public boolean Update() {
			Lista.clear();
			Lista.add(this._id);
			Lista.add(this._nroCuenta);
			return super.Update(Lista);	
		}

		public boolean Delete() {
			Lista.clear();
			Lista.add(this._id);		
			return super.Delete(Lista);
		}

		public PagoServicio Load() throws OperationException {
			ResultSet rs = this.Select();
			PagoServicio oPrestamo = new PagoServicio();

			try {
				if (rs.next()) {
					oPrestamo.set_id(rs.getInt("id"));
					oPrestamo.set_fechaAlta(rs.getTime("FechaAlta").toString());
					oPrestamo.set_nroCuenta(rs.getInt("NroCuenta"));
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