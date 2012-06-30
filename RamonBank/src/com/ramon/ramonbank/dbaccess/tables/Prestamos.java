package com.ramon.ramonbank.dbaccess.tables;

import java.sql.ResultSet;
import java.sql.SQLException;
//import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ramon.ramonbank.dbaccess.ITables;
import com.ramon.ramonbank.dbaccess.Tables;
import com.ramon.ramonbank.exceptions.OperationException;
import com.ramon.ramonbank.utils.Fecha;

public class Prestamos extends Tables {
	
	private Logger _log = Logger.getLogger("Log");
	//private SimpleDateFormat _dateFormat = new SimpleDateFormat("yyyy-dd-MM");
	
	private int _id;
	private Fecha _fechaAlta;
	private double _monto;
	private int _cantCuotas;
	private double _interes;
	private int _idCliente;
	private int _idCuenta;
	private Fecha _filtro_fechaDesde;
	private Fecha _filtro_fechaHasta;
	private int _filtroActivo;
	private int _cantCuotasPagadas;
	

	public Prestamos() {
		
		this._id = -1;
		this._fechaAlta = new Fecha();
		this._filtro_fechaDesde = new Fecha();
		this._filtro_fechaHasta = new Fecha();
		this._monto = -1;
		this._cantCuotas = -1;
		this._interes = -1;
		this._idCliente = -1;
		this._idCuenta = -1;
		this._filtroActivo = -1;
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

	public int get_filtroActivo() {
		return _filtroActivo;
	}

	public void set_filtroActivo(int _filtroActivo) {
		this._filtroActivo = _filtroActivo;
	}
	

		public int get_cantCuotasPagadas() {
		return _cantCuotasPagadas;
	}


	public void set_cantCuotasPagadas(int _cantCuotasPagadas) {
		this._cantCuotasPagadas = _cantCuotasPagadas;
	}


		// Acceso a BD
		public ResultSet Select() {
			Lista.clear();
			Lista.add(this._id);		
			Lista.add(this._filtro_fechaDesde);
			Lista.add(this._filtro_fechaHasta);
			Lista.add(this._monto);
			Lista.add(this._cantCuotas);
			Lista.add(this._interes);
			Lista.add(this._idCliente);
			Lista.add(this._idCuenta);
			Lista.add(this._filtroActivo);
			return super.Select(Lista);	
		}

		public int Insert() {
			Lista.clear();
			Lista.add(this._monto);
			Lista.add(this._cantCuotas);
			Lista.add(this._interes);
			Lista.add(this._idCliente);
			Lista.add(this._idCuenta);
			return super.Insert(Lista);				
		}

		public boolean Update() {
			Lista.clear();
			Lista.add(this._id);
			Lista.add(this._monto);
			Lista.add(this._cantCuotas);
			Lista.add(this._interes);
			Lista.add(this._idCliente);
			Lista.add(this._idCuenta);
			return super.Update(Lista);	
		}

		public boolean Delete() {
			Lista.clear();
			Lista.add(this._id);		
			return super.Delete(Lista);
		}

		public Prestamos Load() throws OperationException {
			ResultSet rs = this.Select();
			Prestamos oPrestamo = new Prestamos();

			try {
				if (rs.next()) {
					oPrestamo.set_id(rs.getInt("id"));
					oPrestamo.set_fechaAlta(rs.getTime("FechaAlta").toString());
					oPrestamo.set_monto(rs.getDouble("Monto"));
					oPrestamo.set_cantCuotas(rs.getInt("CantCuotas"));
					oPrestamo.set_interes(rs.getDouble("Interes"));
					oPrestamo.set_idCliente(rs.getInt("idCliente"));
					oPrestamo.set_idCuenta(rs.getInt("idCuenta"));
					
					PagoPrestamos ppagados = new PagoPrestamos();
					ppagados.set_idPrestamo(oPrestamo.get_id());
					
					oPrestamo.set_cantCuotasPagadas(ppagados.CantidadCuotas());
				} else {
					throw new OperationException("No se encontro ningun " + this.getClass().getName());
			}

			} catch (SQLException e) {
				_log.log(Level.WARNING, e.getStackTrace().toString() + "\n" + e.getMessage());
			}
			return oPrestamo;
		}
		
		public ArrayList<Prestamos> LoadList() throws OperationException {
			ArrayList<Prestamos> arrayPrestamos = new ArrayList<Prestamos>();

			ResultSet rs = this.Select();
			Prestamos oPrestamo;

			try {
				while (rs.next()) {
					oPrestamo = new Prestamos();
					oPrestamo.set_id(rs.getInt("id"));
					oPrestamo.set_fechaAlta(rs.getTime("FechaAlta").toString());
					oPrestamo.set_monto(rs.getDouble("Monto"));
					oPrestamo.set_cantCuotas(rs.getInt("CantCuotas"));
					oPrestamo.set_interes(rs.getDouble("Interes"));
					oPrestamo.set_idCliente(rs.getInt("idCliente"));
					oPrestamo.set_idCuenta(rs.getInt("idCuenta"));
					
					PagoPrestamos ppagados = new PagoPrestamos();
					ppagados.set_idPrestamo(oPrestamo.get_id());
					
					oPrestamo.set_cantCuotasPagadas(ppagados.CantidadCuotas());
					arrayPrestamos.add(oPrestamo);
				}
			}

			catch (SQLException e) {
				_log.log(Level.WARNING,
						e.getStackTrace().toString() + "\n" + e.getMessage());
			}
			return arrayPrestamos;
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
