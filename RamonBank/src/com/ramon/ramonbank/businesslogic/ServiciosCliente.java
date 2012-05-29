package com.ramon.ramonbank.businesslogic;

import java.util.Calendar;
import java.util.logging.Logger;
import com.ramon.ramonbank.dbaccess.tables.Clientes;
import com.ramon.ramonbank.dbaccess.tables.Cuentas;
import com.ramon.ramonbank.dbaccess.tables.Movimientos;
import com.ramon.ramonbank.dbaccess.tables.PagoPrestamos;
import com.ramon.ramonbank.dbaccess.tables.Prestamos;
import com.ramon.ramonbank.exceptions.OperationException;
import com.ramon.ramonbank.utils.Fecha;
import com.ramon.ramonbank.businesslogic.utils.MOVIMIENTO;
import com.ramon.ramonbank.businesslogic.utils.PAGO_PRESTAMO;
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

	public ServiciosCliente(int _idCliente) throws OperationException {
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
					+ TIPO_CUENTA.get_enum(_cuenta.get_tipo()).cantMax() + "/s");
		}
	}

	/**
	 * Prestamo a una cuenta especifica, se pagan costos de movimiento si es
	 * necesario
	 * 
	 * @param _cantPrestamo
	 * @param _cantCuotas
	 * @param _idCuenta
	 * @return id del registro de prestamo
	 * @throws OperationException
	 */
	public int solicitarPrestamo(int _cantPrestamo, int _cantCuotas,
			int _idCuenta) throws OperationException {
		if (_cantCuotas <= 0) {
			throw new OperationException("La cantidad de cuotas es incorrecta");
		}

		if (this._cliente == null) {
			throw new OperationException("El objeto cliente es null");
		}

		if (_idCuenta <= 0) {
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
		if (!(_cuenta.get_saldo() > _cantPrestamo * _costoMovimiento)) {

			throw new OperationException(
					"No hay suficiente saldo para realizar el movimiento");
		}
		Movimientos _movimiento = new Movimientos();
		_movimiento.set_idcuenta(_idCuenta);
		_movimiento.set_saldo(_cuenta.get_saldo());
		_movimiento.set_tipo(MOVIMIENTO.TIPO.DEPOSITO.id());
		_movimiento.set_origen(MOVIMIENTO.ORIGEN.PRESTAMO.id());
		_movimiento.set_monto(_cantPrestamo);
		_movimiento.Insert();

		_cuenta.set_saldo(_cuenta.get_saldo()
				- (_cantPrestamo * _costoMovimiento));
		_cuenta.set_saldo(_cuenta.get_saldo() + _cantPrestamo);

		_cuenta.Update();

		Prestamos _prestamo = new Prestamos();
		// Son decimales, mas facil de manejar para codigo, en la base de datos
		// guardo enteros
		_prestamo.set_interes(PRESTAMO.INTERES_SIN_CUENTA.number() * 100);
		_prestamo.set_idCliente(_cuenta.get_idCliente());
		_prestamo.set_cantCuotas(_cantCuotas);
		_prestamo.set_idCuenta(_cuenta.get_id());
		_prestamo.set_monto(_cantPrestamo);
		return _prestamo.Insert();
	}

	/**
	 * Prestamo a una cuenta especifica, se pagan costos de movimiento si es
	 * necesario
	 * 
	 * @param _cantPrestamo
	 * @param _cantCuotas
	 * @param _cuenta
	 * @return id del registro de prestamo
	 * @throws OperationException
	 */
	public int solicitarPrestamo(int _cantPrestamo, int _cantCuotas,
			Cuentas _cuenta) throws OperationException {
		if (_cantCuotas <= 0) {
			throw new OperationException("La cantidad de cuotas es incorrecta");
		}

		if (this._cliente == null) {
			throw new OperationException("El objeto cliente es null");
		}
		if (_cuenta == null) {
			throw new OperationException("El objeto cuenta es null");
		}
		if (_cantPrestamo < PRESTAMO.MINIMO.number()) {
			throw new OperationException("El minimo de un prestamo es 5000");
		}

		if (_cuenta.Cantidad() <= 0) {
			throw new OperationException("La cuenta es incorrecta");
		}

		_cuenta = _cuenta.Load();
		double _costoMovimiento = TIPO_CUENTA.get_enum(_cuenta.get_tipo())
				.costoMovimiento();

		// Cobro en caso de tener un costo de movimiento
		if (!(_cuenta.get_saldo() >= _cantPrestamo * _costoMovimiento)) {
			throw new OperationException(
					"No hay suficiente saldo para realizar el movimiento");
		}
		Movimientos _movimiento = new Movimientos();
		_movimiento.set_idcuenta(_cuenta.get_id());
		_movimiento.set_saldo(_cuenta.get_saldo());
		_movimiento.set_tipo(MOVIMIENTO.TIPO.DEPOSITO.id());
		_movimiento.set_origen(MOVIMIENTO.ORIGEN.PRESTAMO.id());
		_movimiento.set_monto(_cantPrestamo);
		_movimiento.Insert();

		_cuenta.set_saldo(_cuenta.get_saldo()
				- (_cantPrestamo * _costoMovimiento));
		_cuenta.set_saldo(_cuenta.get_saldo() + _cantPrestamo);

		_cuenta.Update();

		Prestamos _prestamo = new Prestamos();
		// Son decimales, mas facil de manejar para codigo, en la base de datos
		// guardo enteros
		_prestamo.set_interes(PRESTAMO.INTERES_SIN_CUENTA.number() * 100);
		_prestamo.set_idCliente(_cuenta.get_idCliente());
		_prestamo.set_cantCuotas(_cantCuotas);
		_prestamo.set_idCuenta(_cuenta.get_id());
		_prestamo.set_monto(_cantPrestamo);
		return _prestamo.Insert();
	}

	/**
	 * Prestamo en efectivo, no se asigna dinero a ninguna cuenta
	 * 
	 * @param _cantPrestamo
	 * @param _cantCuotas
	 * @return id del registro de prestamo
	 * @throws OperationException
	 */
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
		// Son decimales, mas facil de manejar para codigo, en la base de datos
		// guardo enteros
		_prestamo.set_interes(PRESTAMO.INTERES_CON_CUENTA.number() * 100);
		_prestamo.set_idCliente(_cliente.get_id());
		_prestamo.set_cantCuotas(_cantCuotas);
		_prestamo.set_idCuenta(0);
		_prestamo.set_monto(_cantPrestamo);

		return _prestamo.Insert();
	}

	/**
	 * Paga prestamo con fondos de una cuenta
	 * 
	 * @param _prestamo
	 * @param _cantidadCuotas
	 * @param _cuenta
	 * @return id del nuevo registro del pago de prestamo
	 * @throws OperationException
	 */
	public int pagarPrestamo(Prestamos _prestamo, int _cantidadCuotas,
			Cuentas _cuenta) throws OperationException {
		// Validaciones
		if (this._cliente == null) {
			throw new OperationException("El objeto cliente es null");
		}
		if (_prestamo == null) {
			throw new OperationException("El objeto prestamo es null");
		}
		if (_cuenta == null) {
			throw new OperationException("El objeto cuenta es null");
		}
		if (_cantidadCuotas <= 0) {
			throw new OperationException("Cantidad de cuotas incorrecta: "
					+ _cantidadCuotas);
		}
		if (_prestamo.Cantidad() == 0) {
			throw new OperationException("El prestamo no existe");
		}
		if (_cuenta.Cantidad() == 0) {
			throw new OperationException("La cuenta no existe");
		}
		_prestamo = _prestamo.Load();
		_cuenta = _cuenta.Load();

		if (!(_prestamo.get_idCliente() == _cliente.get_id())) {
			throw new OperationException(
					"El cliente no coincide con el cliente que pidio el prestamo");
		}
		if (!(_cuenta.get_idCliente() == _cliente.get_id())) {
			throw new OperationException(
					"El cliente no coincide con el cliente due�o de la cuenta");
		}

		// Proceso
		// TODO: Meter las validaciones de cantidad de cuotas a pagar de la
		// sobrecarga

		// Registro movimiento
		// TODO: Chequear si tiene suficiente saldo para pagar las cuotas +
		// costo de movimiento

		double _costoMovimiento = TIPO_CUENTA.get_enum(_cuenta.get_tipo())
				.costoMovimiento();
		double _monto = _prestamo.get_monto()
				* (PRESTAMO.INTERES_CON_CUENTA.number() + 1);
		_monto = _monto / _cantidadCuotas;

		// Si es mas del dia 10 se paga un 2% extra
		// TODO: Deshardcodear esto
		if (Fecha.nroDiaActual() > 10) {
			_monto = _prestamo.get_monto() * 1.02;
		}

		// Verifico si tiene suficiente saldo para pagar movimiento y monto
		if (!(_cuenta.get_saldo() >= (_monto * _costoMovimiento) + _monto)) {
			throw new OperationException(
					"No hay suficiente saldo para realizar el movimiento");
		}

		Movimientos _movimiento = new Movimientos();
		_movimiento.set_idcuenta(_cuenta.get_id());
		_movimiento.set_monto(_monto);
		_movimiento.set_saldo(_cuenta.get_saldo());
		_movimiento.set_tipo(MOVIMIENTO.TIPO.EXTRACCION.id());
		_movimiento.set_origen(MOVIMIENTO.ORIGEN.PRESTAMO.id());
		_movimiento.Insert();

		_cuenta.set_saldo(_cuenta.get_saldo() - (_monto * _costoMovimiento));
		_cuenta.set_saldo(_cuenta.get_saldo() - _monto);
		_cuenta.Update();

		// Pago el prestamo
		PagoPrestamos _pagoPrestamo = new PagoPrestamos();
		_pagoPrestamo.set_cantCuotas(_cantidadCuotas);
		_pagoPrestamo.set_idPrestamo(_prestamo.get_id());
		_pagoPrestamo.set_origen(PAGO_PRESTAMO.CUENTA.id());
		_pagoPrestamo.set_monto(_monto);

		return _pagoPrestamo.Insert();
	}

	/**
	 * Se paga prestamo en efectivo
	 * 
	 * @param _prestamo
	 * @param _cantidadCuotas
	 * @return id del registro del pago prestamo
	 * @throws OperationException
	 */
	public int pagarPrestamo(Prestamos _prestamo, int _cantidadCuotas)
			throws OperationException {
		// Validaciones
		if (this._cliente == null) {
			throw new OperationException("El objeto cliente es null");
		}
		if (_prestamo == null) {
			throw new OperationException("El objeto prestamo es null");
		}
		if (_cantidadCuotas <= 0) {
			throw new OperationException("Cantidad de cuotas incorrecta: "
					+ _cantidadCuotas);
		}

		if (_prestamo.Cantidad() == 0) {
			throw new OperationException("El prestamo no existe");
		}

		// Proceso
		_prestamo = _prestamo.Load();

		// TODO: Traer Prestamos pagados de este prestamo y hacer la resta
		PagoPrestamos _prestamosP = new PagoPrestamos();
		_prestamosP.set_idPrestamo(_prestamo.get_id());
		//
		// int _cuotasPagadas = _prestamosP.Cantidad();
		//
		// if(_cuotasPagadas == _prestamo.get_cantCuotas()){
		// throw new OperationException("El prestamo ya se encuentra pago.");
		// }
		//
		// int _cuotasPagar = _prestamo.get_cantCuotas()-_cuotasPagadas;
		//
		// if (_cantidadCuotas > _cuotasPagar) {
		// throw new OperationException("Cantidad de cuotas incorrecta: "
		// + _cantidadCuotas + " ingresadas, "
		// + String.valueOf(_cuotasPagar) + " a pagar.");
		// }
		//
		// double _totalPagar =
		// _prestamo.get_monto()*(0.1*_prestamo.get_interes());
		//

		double _monto = _prestamo.get_monto()
				* (PRESTAMO.INTERES_SIN_CUENTA.number() + 1);

		// Si es mas del dia 10 se paga un 2% extra
		// TODO: Deshardcodear esto
		if (Fecha.nroDiaActual() > 10) {
			_monto = _prestamo.get_monto() * 1.02;
		}
		_monto = _monto / _cantidadCuotas;

		PagoPrestamos _pagoPrestamo = new PagoPrestamos();
		_pagoPrestamo.set_cantCuotas(_cantidadCuotas);
		_pagoPrestamo.set_idPrestamo(_prestamo.get_id());
		_pagoPrestamo.set_origen(PAGO_PRESTAMO.CUENTA.id());
		// TODO: Reemplazar el monto por el calculo en base a la cantidad de
		// cuentas
		_pagoPrestamo.set_monto(_monto);
		return _pagoPrestamo.Insert();
	}
}
