package com.ramon.ramonbank.dbaccess.tables;

import java.sql.ResultSet;
import java.sql.SQLException;
//import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ramon.ramonbank.dbaccess.Tables;
import com.ramon.ramonbank.exceptions.OperationException;
import com.ramon.ramonbank.utils.Fecha;

public class Servicios extends Tables {

	private Logger _log = Logger.getLogger("Log");
	// private SimpleDateFormat _dateFormat = new
	// SimpleDateFormat("yyyy-dd-MM");

	private int _id;
	private String _descripcion;
	private double _monto;

	public Servicios() {

		this._id = -1;
		this._descripcion = "";
		this._monto = -1;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String get_descripcion() {
		return _descripcion;
	}

	public void set_descripcion(String _descripcion) {
		this._descripcion = _descripcion;
	}

	public double get_monto() {
		return _monto;
	}

	public void set_monto(double _monto) {
		this._monto = _monto;
	}

	// Acceso a BD
	public ResultSet Select() {
		Lista.clear();
		Lista.add(this._id);
		Lista.add(this._descripcion);
		Lista.add(this._monto);
		return super.Select(Lista);
	}

	public int Insert() {
		Lista.clear();
		Lista.add(this._descripcion);
		Lista.add(this._monto);
		return super.Insert(Lista);
	}

	public boolean Update() {
		Lista.clear();
		Lista.add(this._id);
		Lista.add(this._descripcion);
		Lista.add(this._monto);
		return super.Update(Lista);
	}

	public boolean Delete() {
		Lista.clear();
		Lista.add(this._id);
		return super.Delete(Lista);
	}

	public Servicios Load() throws OperationException {
		ResultSet rs = this.Select();
		Servicios oPagoServicio = new Servicios();

		try {
			if (rs.next()) {
				oPagoServicio.set_id(rs.getInt("id"));
				oPagoServicio.set_descripcion(rs.getString("Descripcion"));
				oPagoServicio.set_monto(rs.getDouble("MontoFijo"));
			} else {
				throw new OperationException("No se encontro ningun "
						+ this.getClass().getName());
			}

		} catch (SQLException e) {
			_log.log(Level.WARNING,
					e.getStackTrace().toString() + "\n" + e.getMessage());
		}
		return oPagoServicio;
	}

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