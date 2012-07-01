package com.ramon.ramonbank.dbaccess.tables;

import java.sql.ResultSet;
import java.sql.SQLException;
//import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ramon.ramonbank.dbaccess.Tables;
import com.ramon.ramonbank.exceptions.OperationException;
import com.ramon.ramonbank.utils.Fecha;

public class PagoServicios extends Tables {
	
	private Logger _log = Logger.getLogger("Log");
	//private SimpleDateFormat _dateFormat = new SimpleDateFormat("yyyy-dd-MM");
	
	private int _id;
	private Fecha _fechaAlta;
	private int _nroCuenta;
	private int _idServicio;
	private Fecha _filtro_fechaDesde;
	private Fecha _filtro_fechaHasta;
	private String _Servicio;
	
	public PagoServicios() {
		this._id = -1;
		this._fechaAlta = new Fecha();
		this._filtro_fechaDesde = new Fecha();
		this._filtro_fechaHasta = new Fecha();
		this._nroCuenta = -1;
		this._idServicio = -1;
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
	public int get_nroCuenta() {
		return _nroCuenta;
	}

	public void set_nroCuenta(int _nroCuenta) {
		this._nroCuenta = _nroCuenta;
	}

	
	public int get_idServicio() {
		return _idServicio;
	}


	public void set_idServicio(int _idServicio) {
		this._idServicio = _idServicio;
	}

		public String get_Servicio() {
		return _Servicio;
	}



	public void set_Servicio(String _Servicio) {
		this._Servicio = _Servicio;
	}



		// Acceso a BD
		public ResultSet Select() {
			Lista.clear();
			Lista.add(this._id);		
			Lista.add(this._filtro_fechaDesde);
			Lista.add(this._filtro_fechaHasta);
			Lista.add(this._nroCuenta);
			Lista.add(this._idServicio);
			return super.Select(Lista);	
		}

		public int Insert() {
			Lista.clear();
			Lista.add(this._nroCuenta);
			Lista.add(this._idServicio);
			return super.Insert(Lista);				
		}

		public boolean Update() {
			Lista.clear();
			Lista.add(this._id);
			Lista.add(this._nroCuenta);
			Lista.add(this._idServicio);
			return super.Update(Lista);	
		}

		public boolean Delete() {
			Lista.clear();
			Lista.add(this._id);		
			return super.Delete(Lista);
		}

		public PagoServicios Load() throws OperationException {
			ResultSet rs = this.Select();
			PagoServicios oPagoServicio = new PagoServicios();

			try {
				if (rs.next()) {
					oPagoServicio.set_id(rs.getInt("id"));
					oPagoServicio.set_fechaAlta(rs.getDate("Fecha").toString());
					oPagoServicio.set_nroCuenta(rs.getInt("NroCuenta"));
					oPagoServicio.set_idServicio(rs.getInt("idServicio"));
					oPagoServicio.set_Servicio(rs.getString("Descripcion"));
				}

			} catch (SQLException e) {
				_log.log(Level.WARNING, e.getStackTrace().toString() + "\n" + e.getMessage());
			}
			return oPagoServicio;
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