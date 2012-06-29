package com.ramon.ramonbank.dbaccess.tables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ramon.ramonbank.businesslogic.utils.TIPO_CUENTA;
import com.ramon.ramonbank.dbaccess.ITables;
import com.ramon.ramonbank.dbaccess.Tables;
import com.ramon.ramonbank.exceptions.OperationException;

public class Cuentas extends Tables {

	private int _id;
	private int _idCliente;
	private int _tipo;
	private int _filtroEstado;
	private boolean _estado;
	private double _saldo;
	private double _descubierto;

	private Logger _log = Logger.getLogger("Log");

	public Cuentas() {
		_id = -1;
		_idCliente = -1;
		_tipo = -1;
		_estado = true;
		_filtroEstado = -1;
		_saldo = -1;
		_descubierto = -1;
	}

	public void set_estado(boolean _estado) {
		this._estado = _estado;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public int get_idCliente() {
		return _idCliente;
	}

	public void set_idCliente(int _idCliente) {
		this._idCliente = _idCliente;
	}

	public int get_tipo() {
		return _tipo;
	}

	public void set_tipo(int _tipo) {
		this._tipo = _tipo;
	}

	public int get_filtroEstado() {
		return _filtroEstado;
	}

	public void set_filtroEstado(int i) {
		this._filtroEstado = i;
	}

	public double get_saldo() {
		return _saldo;
	}

	public void set_saldo(double _saldo) {
		this._saldo = _saldo;
	}

	public double get_descubierto() {
		return _descubierto;
	}

	public void set_descubierto(double _descubierto) {
		this._descubierto = _descubierto;
	}
	public String get_tipo_nombre(){
		return TIPO_CUENTA.get_enum(this._tipo).nombre();
	}
	public double get_limite_descubierto(){
		//TODO: En la muestra de cuentas mostrar el límite
		return TIPO_CUENTA.get_enum(this._tipo).limiteDescubierto();
	}

	// Acceso a BD
	public ResultSet Select() {
		Lista.clear();
		Lista.add(this._id);		
		Lista.add(this._idCliente);
		Lista.add(this._tipo);
		Lista.add(this._filtroEstado);
		Lista.add(this._saldo);
		Lista.add(this._descubierto);	
		return super.Select(Lista);
	}

	public int Insert() {
		Lista.clear();		
		Lista.add(this._idCliente);
		Lista.add(this._tipo);
		Lista.add(this._estado);
		Lista.add(this._saldo);
		Lista.add(this._descubierto);	
		return super.Insert(Lista);
	}

	public boolean Update() {
		Lista.clear();	
		Lista.add(this._id);	
		Lista.add(this._idCliente);
		Lista.add(this._tipo);		
		Lista.add(this._estado);
		Lista.add(this._saldo);
		Lista.add(this._descubierto);	
		return super.Update(Lista);		
	}

	public boolean Delete() {
		Lista.clear();
		Lista.add(this._id);		
		return super.Delete(Lista);	
	}

	public Cuentas Load() throws OperationException {
		ResultSet rs = this.Select();
		Cuentas oCuenta = new Cuentas();

		try {
			if (rs.next()) {
				oCuenta.set_id(rs.getInt("id"));
				oCuenta.set_idCliente(rs.getInt("idCliente"));
				oCuenta.set_tipo(rs.getInt("Tipo"));
				// Como la base de datos tiene un boolean, tengo que
				// transformarlo a int
				oCuenta.set_estado(rs.getBoolean("Estado"));
				oCuenta.set_saldo(rs.getInt("Saldo"));
				oCuenta.set_descubierto(rs.getInt("Descubierto"));
			} else {
				throw new OperationException("No se encontro ningun "
						+ this.getClass().getName());
			}

		} catch (SQLException e) {
			_log.log(Level.WARNING, e.getStackTrace().toString());
		}
		return oCuenta;
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
	
	public ArrayList<Cuentas> LoadList() throws OperationException{
		ArrayList<Cuentas> arrayCuentas = new ArrayList<Cuentas>();
		ResultSet rs = this.Select();
		Cuentas oCuenta;

		try {
			while(rs.next()){
				oCuenta = new Cuentas();
				oCuenta.set_id(rs.getInt("id"));
				oCuenta.set_idCliente(rs.getInt("idCliente"));
				oCuenta.set_tipo(rs.getInt("Tipo"));
				// Como la base de datos tiene un boolean, tengo que
				// transformarlo a int
				oCuenta.set_estado(rs.getBoolean("Estado"));
				oCuenta.set_saldo(rs.getInt("Saldo"));
				oCuenta.set_descubierto(rs.getInt("Descubierto"));
				
				arrayCuentas.add(oCuenta);
			}
		} catch (SQLException e) {
			_log.log(Level.WARNING, e.getStackTrace().toString());
		}
		return arrayCuentas;
	}
	}
