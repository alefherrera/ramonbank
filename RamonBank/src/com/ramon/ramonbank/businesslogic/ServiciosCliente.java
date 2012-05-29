package com.ramon.ramonbank.businesslogic;


import java.util.logging.Logger;
import com.ramon.ramonbank.dbaccess.tables.Clientes;
import com.ramon.ramonbank.dbaccess.tables.Cuentas;
import com.ramon.ramonbank.dbaccess.tables.Movimientos;
import com.ramon.ramonbank.dbaccess.tables.PagoPrestamos;
import com.ramon.ramonbank.dbaccess.tables.Prestamos;
import com.ramon.ramonbank.exceptions.OperationException;
import com.ramon.ramonbank.businesslogic.utils.MOVIMIENTO;
import com.ramon.ramonbank.businesslogic.utils.TIPO_CUENTA;
import com.ramon.ramonbank.businesslogic.utils.PRESTAMO;

public class ServiciosCliente {
	private Clientes _cliente;

	private Logger _log = Logger.getLogger("Log");

	public Clientes get_cliente() {
		return _cliente;
	}

	public void set_cliente(Clientes oCliente) {
		this._cliente = oCliente;
	}

	public ServiciosCliente(Clientes _cliente) {
		this._cliente = _cliente;
	}
	
	public ServiciosCliente(int _idCliente) throws OperationException{
		this._cliente.set_id(_idCliente);
		this._cliente = this._cliente.Load();
	}

	public int crearCuenta(int _tipo) throws OperationException {
		Cuentas _cuenta = new Cuentas();
		_cuenta.set_tipo(_tipo);
		_cuenta.set_idCliente(_cliente.get_id());
		// Validaciones iniciales
		if (this._cliente == null) {
			throw new OperationException("El objeto cliente es null");
		}

		if (this._cliente.Cantidad() == 0) {
			throw new OperationException("El cliente no existe");
		}

		// Verifico cantidad de cuentas, 1 maximo caja de ahorro, 5 maximo
		// cuenta corriente

		if (TIPO_CUENTA.get_enum(_cuenta.get_tipo()).cantMax() > _cuenta
				.Cantidad()) {
			// Puedo crear la cuenta
			return _cuenta.Insert();
		} else {
			throw new OperationException("El cliente tiene "
					+ _cuenta.Cantidad() + " "
					+ TIPO_CUENTA.get_enum(_cuenta.get_tipo()).cantMax()
					+ "/s");
		}
	}

	
	//PRESTAMO CON CUENTA
	public int solicitarPrestamo(int _cantPrestamo, int _cantCuotas,
			int _idCuenta) throws OperationException {
		if (_cantCuotas <= 0) {
			throw new OperationException("La cantidad de cuotas es incorrecta");
		}

		if (this._cliente == null) {
			throw new OperationException("El objeto cliente es null");
		}
		
		if (_idCuenta <= 0){
			throw new OperationException("La cuenta es incorrecta");
		}
			
		
		if (_cantPrestamo < PRESTAMO.MINIMO.number()) {
			throw new OperationException("El minimo de un prestamo es 5000");
		}
		
		
		Cuentas _cuenta = new Cuentas();
		_cuenta.set_id(_idCuenta);
		_cuenta = _cuenta.Load();
		double _costoMovimiento = TIPO_CUENTA.get_enum(_cuenta.get_tipo())
				.costoMovimiento();

		// Cobro en caso de tener un costo de movimiento
		if (_cuenta.get_saldo() > _cantPrestamo * _costoMovimiento) {
			Movimientos _movimiento = new Movimientos();
			_movimiento.set_idcuenta(_idCuenta);
			_movimiento.set_saldo(_cuenta.get_saldo());
			_movimiento.set_tipo(MOVIMIENTO.TIPO.DEPOSITO.id());
			_movimiento.set_origen(MOVIMIENTO.ORIGEN.PRESTAMO.id());
			_movimiento.set_monto(_cantPrestamo);
			_movimiento.Insert();
			
			_cuenta.set_saldo(_cuenta.get_saldo()
					- (_cantPrestamo * _costoMovimiento));
			_cuenta.set_saldo(_cuenta.get_saldo()+_cantPrestamo);
			
			_cuenta.Update();
		} else {
			throw new OperationException(
					"No hay suficiente saldo para realizar el movimiento");
		}

		
		Prestamos _prestamo = new Prestamos();
		//Son decimales, mas facil de manejar para codigo, en la base de datos guardo enteros
		_prestamo.set_interes(PRESTAMO.INTERES_SIN_CUENTA.number()*100);
		_prestamo.set_idCliente(_cuenta.get_idCliente());
		_prestamo.set_cantCuotas(_cantCuotas);
		_prestamo.set_idCuenta(_cuenta.get_id());
		_prestamo.set_monto(_cantPrestamo);
		return _prestamo.Insert();
	}

	//PRESTAMO CON CUENTA
	public int solicitarPrestamo(int _cantPrestamo, int _cantCuotas)
			throws OperationException {
		if (_cantCuotas <= 0) {
			throw new OperationException("La cantidad de cuotas es incorrecta");
		}

		if (this._cliente == null) {
			throw new OperationException("El objeto cliente es null");
		}

		if (_cantPrestamo < PRESTAMO.MINIMO.number()) {
			throw new OperationException("El minimo de un prestamo es 5000");
		}

		
		Prestamos _prestamo = new Prestamos();
		//Son decimales, mas facil de manejar para codigo, en la base de datos guardo enteros
		_prestamo.set_interes(PRESTAMO.INTERES_CON_CUENTA.number()*100);
		_prestamo.set_idCliente(_cliente.get_id());
		_prestamo.set_cantCuotas(_cantCuotas);
		_prestamo.set_idCuenta(0);
		_prestamo.set_monto(_cantPrestamo);
		return _prestamo.Insert();
	}

	public int pagarPrestamo(Prestamos _prestamo,
			int _cantidadCuotas) throws OperationException {
		if (this._cliente == null) {
			throw new OperationException("El objeto cliente es null");
		}
		if (_cantidadCuotas <= 0) {
			throw new OperationException("Cantidad de cuotas incorrecta: "
					+ _cantidadCuotas);
		}
		if(_prestamo.Cantidad() == 0){
			throw new OperationException("El prestamo no existe");
		}
		
		// TODO: PagoPrestamo, cargar idCuenta en 0
		PagoPrestamos _pagoPrestamo = new PagoPrestamos();
		_pagoPrestamo.set_cantCuotas(_cantidadCuotas);
		_pagoPrestamo.set_idPrestamo(_prestamo.get_id());
		
		return 0;
	}

	public int pagarPrestamo(int _idPrestamo, int _cantidadCuotas)
			throws OperationException {
		if (this._cliente == null) {
			throw new OperationException("El objeto cliente es null");
		}

		if (_cantidadCuotas <= 0) {
			throw new OperationException("Cantidad de cuotas incorrecta: "
					+ _cantidadCuotas);
		}
		
		Prestamos _prestamo = new Prestamos();
		_prestamo.set_id(_idPrestamo);
		
		if(_prestamo.Cantidad() == 0){
			throw new OperationException("El prestamo no existe");
		}

		_prestamo = _prestamo.Load();

		// TODO: Traer Prestamos pagados de este prestamo y hacer la resta
		PagoPrestamos _prestamosP = new PagoPrestamos();
		_prestamosP.set_idPrestamo(_idPrestamo);
//		
//		int _cuotasPagadas = _prestamosP.Cantidad();
//		
//		if(_cuotasPagadas == _prestamo.get_cantCuotas()){
//			throw new OperationException("El prestamo ya se encuentra pago.");
//		}
//		
//		int _cuotasPagar = _prestamo.get_cantCuotas()-_cuotasPagadas;
//		
//		if (_cantidadCuotas > _cuotasPagar) {
//			throw new OperationException("Cantidad de cuotas incorrecta: "
//					+ _cantidadCuotas + " ingresadas, "
//					+ String.valueOf(_cuotasPagar) + " a pagar.");
//		}
//		
//		double _totalPagar = _prestamo.get_monto()*(0.1*_prestamo.get_interes());
//		if(Calendar.getInstance().getTime().getDate())
		
		// TODO: PagoPrestamo, cargar idCuenta en 0
		return 0;
	}

	
}
