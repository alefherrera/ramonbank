package com.ramon.ramonbank.servicios;

import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.catalina.util.DateTool;

import com.ramon.ramonbank.dbaccess.tables.Clientes;
import com.ramon.ramonbank.dbaccess.tables.Cuentas;
import com.ramon.ramonbank.dbaccess.tables.Movimientos;
import com.ramon.ramonbank.dbaccess.tables.PagoPrestamos;
import com.ramon.ramonbank.dbaccess.tables.PagoServicios;
import com.ramon.ramonbank.dbaccess.tables.PlazosFijos;
import com.ramon.ramonbank.dbaccess.tables.Prestamos;
import com.ramon.ramonbank.dbaccess.tables.Servicios;
import com.ramon.ramonbank.exceptions.OperationException;
import com.ramon.ramonbank.servicios.utils.MOVIMIENTO;
import com.ramon.ramonbank.servicios.utils.ORIGEN;
import com.ramon.ramonbank.servicios.utils.PLAZO_FIJO;
import com.ramon.ramonbank.servicios.utils.PRESTAMO;
import com.ramon.ramonbank.servicios.utils.TIPO_CUENTA;
import com.ramon.ramonbank.utils.Fecha;
import com.ramon.ramonbank.utils.Validator;

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
		_cuenta.set_saldo(0);
		_cuenta.set_descubierto(0);
		if (TIPO_CUENTA.get_enum(_cuenta.get_tipo()).cantMax() > _cuenta
				.Cantidad()) {
			// Puedo crear la cuenta
			return _cuenta.Insert();
		} else {
			throw new OperationException("El cliente tiene "
					+ _cuenta.Cantidad() + "/"
					+ TIPO_CUENTA.get_enum(_cuenta.get_tipo()).cantMax()
					+ "/s cuentas creadas");
		}
	}

	public ArrayList<Cuentas> listarCuentas(Cuentas cuenta)
			throws OperationException {
		if (this._cliente == null) {
			throw new OperationException("El objeto cliente es null");
		}
		if (cuenta == null) {
			throw new OperationException("El objeto cuenta es null");
		}
		cuenta.set_idCliente(this._cliente.get_id());
		return cuenta.LoadList();
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
		if (!(_cuenta.get_saldo() >= _cantPrestamo * _costoMovimiento)) {

			throw new OperationException(
					"No hay suficiente saldo para realizar el movimiento");
		}

		double montoTotal = _cantPrestamo - (_cantPrestamo * _costoMovimiento);
		Movimientos _movimiento = new Movimientos();
		_movimiento.set_idcuenta(_idCuenta);
		_movimiento.set_saldo(_cuenta.get_saldo());
		_movimiento.set_tipo(MOVIMIENTO.TIPO.DEPOSITO.id());
		_movimiento.set_origen(MOVIMIENTO.ORIGEN.PRESTAMO.id());
		_movimiento.set_monto(montoTotal);
		_movimiento.Insert();

		_cuenta.set_saldo(_cuenta.get_saldo() + montoTotal);
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
	 * Se paga con descubierto si es necesario
	 * 
	 * @param _cantPrestamo
	 * @param _cantCuotas
	 * @param _idCuenta
	 * @return
	 * @throws OperationException
	 */
	public int solicitarPrestamoDescubierto(int _cantPrestamo, int _cantCuotas,
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
		if (!(_cuenta.get_saldo() >= _cantPrestamo * _costoMovimiento)) {
			double resto = _cantPrestamo * _costoMovimiento
					- _cuenta.get_saldo();
			if (resto + _cuenta.get_descubierto() > _cuenta
					.get_limite_descubierto()) {
				throw new OperationException(
						"El l�mite del descubierto y saldo no es suficiente para realizar la operaci�n");
			} else {
				_cuenta.set_saldo(0);
				_cuenta.set_descubierto(_cuenta.get_descubierto() + resto);
			}
		} else {
			_cuenta.set_saldo(_cuenta.get_saldo()
					- (_cantPrestamo * _costoMovimiento));
			_cuenta.set_saldo(_cuenta.get_saldo() + _cantPrestamo);
		}

		double montoTotal = _cantPrestamo - (_cantPrestamo * _costoMovimiento);
		Movimientos _movimiento = new Movimientos();
		_movimiento.set_idcuenta(_idCuenta);
		_movimiento.set_saldo(_cuenta.get_saldo());
		_movimiento.set_tipo(MOVIMIENTO.TIPO.DEPOSITO.id());
		_movimiento.set_origen(MOVIMIENTO.ORIGEN.PRESTAMO.id());
		_movimiento.set_monto(montoTotal);
		_movimiento.Insert();

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

		double montoTotal = _cantPrestamo - (_cantPrestamo * _costoMovimiento);
		Movimientos _movimiento = new Movimientos();
		_movimiento.set_idcuenta(_cuenta.get_id());
		_movimiento.set_saldo(_cuenta.get_saldo());
		_movimiento.set_tipo(MOVIMIENTO.TIPO.DEPOSITO.id());
		_movimiento.set_origen(MOVIMIENTO.ORIGEN.PRESTAMO.id());
		_movimiento.set_monto(montoTotal);
		_movimiento.Insert();

		_cuenta.set_saldo(_cuenta.get_saldo() + montoTotal);

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
	 * Se paga con descubierto si es necesario
	 * 
	 * @param _cantPrestamo
	 * @param _cantCuotas
	 * @param _cuenta
	 * @return
	 * @throws OperationException
	 */
	public int solicitarPrestamoDescubierto(int _cantPrestamo, int _cantCuotas,
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
			double resto = _cantPrestamo * _costoMovimiento
					- _cuenta.get_saldo();
			if (resto + _cuenta.get_descubierto() > _cuenta
					.get_limite_descubierto()) {
				throw new OperationException(
						"El l�mite del descubierto y saldo no es suficiente para realizar la operaci�n");
			} else {
				_cuenta.set_saldo(0);
				_cuenta.set_descubierto(_cuenta.get_descubierto() + resto);
			}
		} else {
			_cuenta.set_saldo(_cuenta.get_saldo()
					- (_cantPrestamo * _costoMovimiento));
			_cuenta.set_saldo(_cuenta.get_saldo() + _cantPrestamo);
		}

		double montoTotal = _cantPrestamo - (_cantPrestamo * _costoMovimiento);

		Movimientos _movimiento = new Movimientos();
		_movimiento.set_idcuenta(_cuenta.get_id());
		_movimiento.set_saldo(_cuenta.get_saldo());
		_movimiento.set_tipo(MOVIMIENTO.TIPO.DEPOSITO.id());
		_movimiento.set_origen(MOVIMIENTO.ORIGEN.PRESTAMO.id());
		_movimiento.set_monto(montoTotal);
		_movimiento.Insert();

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
	public int pagarPrestamo(Prestamos _prestamo, String cantidadCuotas,
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
		if (cantidadCuotas == "") {
			throw new OperationException("Debe ingresar la cantidad de cuotas");
		}
		if (!Validator.isNumeric(cantidadCuotas)) {
			throw new OperationException("Cantidad de cuotas debe ser num�rico");
		}
		int _cantidadCuotas = Integer.parseInt(cantidadCuotas);
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
		PagoPrestamos _prestamosP = new PagoPrestamos();
		_prestamosP.set_idPrestamo(_prestamo.get_id());

		int cuotasPagadas = _prestamosP.CantidadCuotas();

		if (cuotasPagadas == _prestamo.get_cantCuotas()) {
			throw new OperationException("El prestamo ya se encuentra pago.");
		}

		int cuotasPagar = _prestamo.get_cantCuotas() - cuotasPagadas;

		if (_cantidadCuotas > cuotasPagar) {
			throw new OperationException("Cantidad de cuotas incorrecta: "
					+ _cantidadCuotas + " ingresadas, "
					+ String.valueOf(cuotasPagar) + " a pagar.");
		}

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

		double montoTotal = _monto + (_monto * _costoMovimiento);
		// Registro movimiento
		Movimientos _movimiento = new Movimientos();
		_movimiento.set_idcuenta(_cuenta.get_id());
		_movimiento.set_monto(montoTotal);
		_movimiento.set_saldo(_cuenta.get_saldo());
		_movimiento.set_tipo(MOVIMIENTO.TIPO.EXTRACCION.id());
		_movimiento.set_origen(MOVIMIENTO.ORIGEN.PRESTAMO.id());
		_movimiento.Insert();

		_cuenta.set_saldo(_cuenta.get_saldo() - montoTotal);
		_cuenta.Update();

		// Pago el prestamo
		PagoPrestamos _pagoPrestamo = new PagoPrestamos();
		_pagoPrestamo.set_cantCuotas(_cantidadCuotas);
		_pagoPrestamo.set_idPrestamo(_prestamo.get_id());
		_pagoPrestamo.set_origen(ORIGEN.CUENTA.id());
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
	public int pagarPrestamo(Prestamos _prestamo, String cantidadCuotas)
			throws OperationException {
		// Validaciones
		if (this._cliente == null) {
			throw new OperationException("El objeto cliente es null");
		}
		if (_prestamo == null) {
			throw new OperationException("El objeto prestamo es null");
		}

		if (cantidadCuotas == "") {
			throw new OperationException("Debe ingresar la cantidad de cuotas");
		}
		if (!Validator.isNumeric(cantidadCuotas)) {
			throw new OperationException("Cantidad de cuotas debe ser num�rico");
		}
		int _cantidadCuotas = Integer.parseInt(cantidadCuotas);

		if (_cantidadCuotas <= 0) {
			throw new OperationException("Cantidad de cuotas incorrecta: "
					+ _cantidadCuotas);
		}

		if (_prestamo.Cantidad() == 0) {
			throw new OperationException("El prestamo no existe");
		}

		_prestamo = _prestamo.Load();

		if (!(_prestamo.get_idCliente() == _cliente.get_id())) {
			throw new OperationException(
					"El cliente no coincide con el cliente que pidio el prestamo");
		}

		// Proceso
		PagoPrestamos _prestamosP = new PagoPrestamos();
		_prestamosP.set_idPrestamo(_prestamo.get_id());

		int cuotasPagadas = _prestamosP.CantidadCuotas();

		if (cuotasPagadas == _prestamo.get_cantCuotas()) {
			throw new OperationException("El prestamo ya se encuentra pago.");
		}

		int cuotasPagar = _prestamo.get_cantCuotas() - cuotasPagadas;

		if (_cantidadCuotas > cuotasPagar) {
			throw new OperationException("Cantidad de cuotas incorrecta: "
					+ _cantidadCuotas + " ingresadas, "
					+ String.valueOf(cuotasPagar) + " a pagar.");
		}

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
		_pagoPrestamo.set_origen(ORIGEN.CUENTA.id());
		_pagoPrestamo.set_monto(_monto);
		return _pagoPrestamo.Insert();
	}

	/**
	 * Devuelve los prestamos del cliente
	 * 
	 * @param prestamo
	 *            Filtro de prestamos, new para crear sin filtros.
	 * @return
	 */
	public ArrayList<Prestamos> listarPrestamos(Prestamos prestamo)
			throws OperationException {
		if (this._cliente == null) {
			throw new OperationException("El objeto cliente es null");
		}
		if (prestamo == null) {
			throw new OperationException("El objeto prestamo es null");
		}
		prestamo.set_idCliente(this._cliente.get_id());

		// TODO: Modificar el select de prestamos para que filtre los que estan
		// activos o no, de acuerdo al filtroactivo
		return prestamo.LoadList();

	}

	/**
	 * Pago un servicio, solo desde una cuenta
	 * 
	 * @param _idServicio
	 * @param _cuenta
	 * @return
	 * @throws OperationException
	 */
	public int pagarServicio(int _idServicio, Cuentas _cuenta)
			throws OperationException {
		// Validaciones
		if (this._cliente == null) {
			throw new OperationException("El objeto cliente es null");
		}
		if (_cuenta == null) {
			throw new OperationException("El objeto cuenta es null");
		}

		Servicios _servicio = new Servicios();
		_servicio.set_id(_idServicio);

		if (_servicio.Cantidad() == 0) {
			throw new OperationException("El servicio no existe");
		}

		if (_cuenta.Cantidad() <= 0) {
			throw new OperationException("La cuenta es incorrecta");
		}

		_cuenta = _cuenta.Load();
		_servicio = _servicio.Load();

		if (_cuenta.get_idCliente() != _cliente.get_id()) {
			throw new OperationException("El cliente y la cuenta no concuerdan");
		}

		// Proceso
		double _monto = _servicio.get_monto();
		double _costoMovimiento = TIPO_CUENTA.get_enum(_cuenta.get_tipo())
				.costoMovimiento();

		if (_cuenta.get_saldo() < _monto + (_monto * _costoMovimiento)) {
			throw new OperationException(
					"No hay suficiente saldo para realizar el pago del servicio");
		}

		double montoTotal = _monto + (_monto * _costoMovimiento);

		Movimientos _movimiento = new Movimientos();
		_movimiento.set_idcuenta(_cuenta.get_id());
		_movimiento.set_monto(montoTotal);
		_movimiento.set_origen(MOVIMIENTO.ORIGEN.SERVICIO.id());
		_movimiento.set_tipo(MOVIMIENTO.TIPO.EXTRACCION.id());
		_movimiento.set_saldo(_cuenta.get_saldo());
		_movimiento.Insert();

		_cuenta.set_saldo(_cuenta.get_saldo() - montoTotal);
		_cuenta.Update();

		PagoServicios _pagoServicios = new PagoServicios();
		_pagoServicios.set_nroCuenta(_cuenta.get_id());
		_pagoServicios.set_idServicio(_servicio.get_id());
		return _pagoServicios.Insert();
	}

	/**
	 * Pago un servicio desde una cuenta, si es necesario uso descubierto
	 * 
	 * @param _idServicio
	 * @param _cuenta
	 * @return
	 * @throws OperationException
	 */
	public int pagarServicioDescubierto(int _idServicio, Cuentas _cuenta)
			throws OperationException {
		// Validaciones
		if (this._cliente == null) {
			throw new OperationException("El objeto cliente es null");
		}
		if (_cuenta == null) {
			throw new OperationException("El objeto cuenta es null");
		}

		Servicios _servicio = new Servicios();
		_servicio.set_id(_idServicio);

		if (_servicio.Cantidad() == 0) {
			throw new OperationException("El servicio no existe");
		}

		if (_cuenta.Cantidad() <= 0) {
			throw new OperationException("La cuenta es incorrecta");
		}

		_cuenta = _cuenta.Load();
		_servicio = _servicio.Load();

		if (_cuenta.get_idCliente() != _cliente.get_id()) {
			throw new OperationException("El cliente y la cuenta no concuerdan");
		}

		// Proceso
		double _monto = _servicio.get_monto();
		double _costoMovimiento = TIPO_CUENTA.get_enum(_cuenta.get_tipo())
				.costoMovimiento();
		double totalPago = _monto + (_monto * _costoMovimiento);

		if (!(_cuenta.get_saldo() >= totalPago)) {
			double resto = totalPago - _cuenta.get_saldo();
			if (resto + _cuenta.get_descubierto() > _cuenta
					.get_limite_descubierto()) {
				throw new OperationException(
						"El l�mite del descubierto y saldo no es suficiente para realizar la operaci�n");
			} else {
				_cuenta.set_saldo(0);
				_cuenta.set_descubierto(_cuenta.get_descubierto() + resto);
			}

		} else {
			_cuenta.set_saldo(_cuenta.get_saldo() - (_monto * _costoMovimiento));
			_cuenta.set_saldo(_cuenta.get_saldo() - _monto);
		}

		double montoTotal = _monto + (_monto * _costoMovimiento);

		Movimientos _movimiento = new Movimientos();
		_movimiento.set_idcuenta(_cuenta.get_id());
		_movimiento.set_monto(montoTotal);
		_movimiento.set_origen(MOVIMIENTO.ORIGEN.SERVICIO.id());
		_movimiento.set_tipo(MOVIMIENTO.TIPO.EXTRACCION.id());
		_movimiento.set_saldo(_cuenta.get_saldo());
		_movimiento.Insert();

		_cuenta.Update();

		PagoServicios _pagoServicios = new PagoServicios();
		_pagoServicios.set_nroCuenta(_cuenta.get_id());
		_pagoServicios.set_idServicio(_servicio.get_id());
		return _pagoServicios.Insert();
	}

	/**
	 * Deposito de efectivo en una cuenta
	 * 
	 * @param _cuenta
	 *            Cuenta a la que se deposita
	 * @param _monto
	 *            Cantidad que se deposita
	 * @return
	 * @throws OperationException
	 */
	public void depositar(Cuentas _cuenta, int _monto)
			throws OperationException {
		if (_cuenta == null) {
			throw new OperationException("El objeto cuenta es null");
		}
		if (this._cliente == null) {
			throw new OperationException("El objeto cliente es null");
		}
		if (_monto <= 0) {
			throw new OperationException("La cantidad es incorrecta");
		}
		if (_cuenta.Cantidad() == 0) {
			throw new OperationException("La cuenta es incorrecta");
		}
		_cuenta = _cuenta.Load();
		if (_cuenta.get_idCliente() != this._cliente.get_id()) {
			throw new OperationException("El cliente y la cuenta no concuerdan");
		}

		double _costoMovimiento = TIPO_CUENTA.get_enum(_cuenta.get_tipo())
				.costoMovimiento();

		_cuenta.set_saldo(_cuenta.get_saldo() + _monto
				- (_monto * _costoMovimiento));
		_cuenta.Update();

		double montoTotal = _monto - (_monto * _costoMovimiento);
		Movimientos _movimiento = new Movimientos();
		_movimiento.set_idcuenta(_cuenta.get_id());
		_movimiento.set_monto(montoTotal);
		_movimiento.set_origen(MOVIMIENTO.ORIGEN.CAJA.id());
		_movimiento.set_tipo(MOVIMIENTO.TIPO.DEPOSITO.id());
		_movimiento.set_saldo(_cuenta.get_saldo());
		_movimiento.Insert();

		return;
	}

	/**
	 * Extraigo plata, si es necesario cobro el movimiento
	 * 
	 * @param _cuenta
	 * @param _monto
	 * @throws OperationException
	 */
	public void extraer(Cuentas _cuenta, int _monto) throws OperationException {
		if (_cuenta == null) {
			throw new OperationException("El objeto cuenta es null");
		}
		if (this._cliente == null) {
			throw new OperationException("El objeto cliente es null");
		}
		if (_monto <= 0) {
			throw new OperationException("La cantidad es incorrecta");
		}
		if (_cuenta.Cantidad() == 0) {
			throw new OperationException("La cuenta es incorrecta");
		}
		_cuenta = _cuenta.Load();
		if (_cuenta.get_idCliente() != this._cliente.get_id()) {
			throw new OperationException("El cliente y la cuenta no concuerdan");
		}

		double _costoMovimiento = TIPO_CUENTA.get_enum(_cuenta.get_tipo())
				.costoMovimiento();

		if (_cuenta.get_saldo() < _monto + _monto * _costoMovimiento) {
			throw new OperationException(
					"No hay suficiente saldo para realizar la transferencia");
		}

		_cuenta.set_saldo(_cuenta.get_saldo() - _monto - _monto
				* _costoMovimiento);
		_cuenta.Update();

		double montoTotal = _monto + (_monto * _costoMovimiento);
		Movimientos _movimiento = new Movimientos();
		_movimiento.set_idcuenta(_cuenta.get_id());
		_movimiento.set_monto(montoTotal);
		_movimiento.set_origen(MOVIMIENTO.ORIGEN.CAJA.id());
		_movimiento.set_tipo(MOVIMIENTO.TIPO.EXTRACCION.id());
		_movimiento.set_saldo(_cuenta.get_saldo());
		_movimiento.Insert();

		return;
	}

	/**
	 * Transferencia de saldo de una cuenta a otra, cobro movimiento si es
	 * necesario, a las dos
	 * 
	 * @param _cuentaDesde
	 * @param _cuentaHasta
	 */
	public void transferir(Cuentas _cuentaDesde, Cuentas _cuentaHasta,
			int _monto) throws OperationException {
		if (_cuentaDesde == null) {
			throw new OperationException("El objeto cuenta desde es null");
		}
		if (_cuentaHasta == null) {
			throw new OperationException("El objeto cuenta hasta es null");
		}
		if (this._cliente == null) {
			throw new OperationException("El objeto cliente es null");
		}
		if (_monto <= 0) {
			throw new OperationException("La cantidad es incorrecta");
		}
		if (_cuentaDesde.Cantidad() == 0) {
			throw new OperationException("La cuenta desde no existe");
		}
		if (_cuentaHasta.Cantidad() == 0) {
			throw new OperationException("La cuenta hasta no existe");
		}

		_cuentaDesde = _cuentaDesde.Load();
		_cuentaHasta = _cuentaHasta.Load();

		if (_cuentaDesde.get_idCliente() != this._cliente.get_id()) {
			throw new OperationException("El cliente y la cuenta no concuerdan");
		}

		// Validaciones y proceso desde
		double _costoMovimiento = TIPO_CUENTA.get_enum(_cuentaDesde.get_tipo())
				.costoMovimiento();

		if (_cuentaDesde.get_saldo() < _monto + _monto * _costoMovimiento) {
			throw new OperationException(
					"No hay suficiente saldo para realizar la transferencia");
		}

		_cuentaDesde.set_saldo(_cuentaDesde.get_saldo() - _monto - _monto
				* _costoMovimiento);
		_cuentaDesde.Update();

		double montoTotal = _monto + (_monto * _costoMovimiento);
		Movimientos _movimiento = new Movimientos();
		_movimiento.set_idcuenta(_cuentaDesde.get_id());
		_movimiento.set_monto(montoTotal);
		_movimiento.set_origen(MOVIMIENTO.ORIGEN.TRANSFERENCIA.id());
		_movimiento.set_tipo(MOVIMIENTO.TIPO.EXTRACCION.id());
		_movimiento.set_saldo(_cuentaDesde.get_saldo());
		_movimiento.Insert();

		// Validaciones y proceso hasta
		_costoMovimiento = TIPO_CUENTA.get_enum(_cuentaHasta.get_tipo())
				.costoMovimiento();
		_cuentaHasta.set_saldo(_cuentaHasta.get_saldo() + _monto - _monto
				* _costoMovimiento);
		_cuentaHasta.Update();

		montoTotal = _monto - (_monto * _costoMovimiento);
		_movimiento = new Movimientos();
		_movimiento.set_idcuenta(_cuentaHasta.get_id());
		_movimiento.set_monto(montoTotal);
		_movimiento.set_origen(MOVIMIENTO.ORIGEN.TRANSFERENCIA.id());
		_movimiento.set_tipo(MOVIMIENTO.TIPO.DEPOSITO.id());
		_movimiento.set_saldo(_cuentaHasta.get_saldo());
		_movimiento.Insert();

		return;
	}

	/**
	 * Plazo fijo desde cuenta, se retira en efectivo
	 * 
	 * @param _cuentaOrigen
	 *            Cuenta de Origen de los fondos
	 * @param _monto
	 * @param vencimiento
	 * @return
	 * @throws OperationException
	 */
	public int plazoFijo(Cuentas _cuentaOrigen, int _monto, Fecha vencimiento)
			throws OperationException {
		if (this._cliente == null) {
			throw new OperationException("El objeto cliente es null");
		}

		if (vencimiento.get_Date().before(Fecha.now())) {
			throw new OperationException("La fecha es incorrecta");
		}
		if (_monto <= 0) {
			throw new OperationException("La cantidad es incorrecta");
		}
		if (_cuentaOrigen.Cantidad() == 0) {
			throw new OperationException("No existe la cuenta");
		}
		_cuentaOrigen = _cuentaOrigen.Load();

		if (_cuentaOrigen.get_idCliente() != this._cliente.get_id()) {
			throw new OperationException("La cuenta y el cliente no concuerdan");
		}

		// Validaciones y proceso desde
		double _costoMovimiento = TIPO_CUENTA
				.get_enum(_cuentaOrigen.get_tipo()).costoMovimiento();
		double _montoTotal = _monto + (_monto * _costoMovimiento);

		if (_cuentaOrigen.get_saldo() < _montoTotal) {
			throw new OperationException(
					"No hay suficiente saldo para realizar la transacci�n");
		}

		long msDif = vencimiento.get_Date().getTime() - Fecha.now().getTime();
		int _dias = (int) ((((msDif / 1000) / 60) / 60) / 24);

		PlazosFijos _plazoFijo = new PlazosFijos();
		_plazoFijo.set_monto(_monto);
		_plazoFijo.set_acreditacion(PLAZO_FIJO.ACREDITACION.CAJA.get_key());
		_plazoFijo.set_fechaVencimiento(vencimiento.get_Fecha());
		_plazoFijo.set_idCliente(_cuentaOrigen.get_idCliente());
		_plazoFijo.set_interes(PLAZO_FIJO.INTERESES.get_intereses(_dias)
				.interes() * 100);
		_plazoFijo.set_nroCuentaDestino(0);
		_plazoFijo.set_nroCuentaOrigen(_cuentaOrigen.get_id());
		_plazoFijo.set_origen(ORIGEN.CUENTA.id());
		int idPlazo = _plazoFijo.Insert();

		_cuentaOrigen.set_saldo(_cuentaOrigen.get_saldo() - _montoTotal);
		_cuentaOrigen.Update();

		Movimientos _movimiento = new Movimientos();
		_movimiento.set_idcuenta(_cuentaOrigen.get_id());
		_movimiento.set_monto(_montoTotal);
		_movimiento.set_origen(MOVIMIENTO.ORIGEN.PLAZO_FIJO.id());
		_movimiento.set_tipo(MOVIMIENTO.TIPO.EXTRACCION.id());
		_movimiento.set_saldo(_cuentaOrigen.get_saldo());

		return idPlazo;
	}

	/**
	 * Plazo fijo desde cuenta, se retira en una cuenta
	 * 
	 * @param _cuentaOrigen
	 * @param _monto
	 * @param vencimiento
	 * @return
	 * @throws OperationException
	 */
	public int plazoFijo(Cuentas _cuentaOrigen, int _monto, Fecha vencimiento,
			Cuentas _cuentaDestino) throws OperationException {
		if (this._cliente == null) {
			throw new OperationException("El objeto cliente es null");
		}

		if (vencimiento.get_Date().before(Fecha.now())) {
			throw new OperationException("La fecha es incorrecta");
		}
		if (_monto <= 0) {
			throw new OperationException("La cantidad es incorrecta");
		}
		if (_cuentaOrigen.Cantidad() == 0) {
			throw new OperationException("No existe la cuenta origen");
		}
		if (_cuentaDestino.Cantidad() == 0) {
			throw new OperationException("No existe la cuenta destino");
		}
		_cuentaDestino = _cuentaDestino.Load();
		_cuentaOrigen = _cuentaOrigen.Load();

		if (_cuentaOrigen.get_idCliente() != this._cliente.get_id()) {
			throw new OperationException("La cuenta y el cliente no concuerdan");
		}

		// Validaciones y proceso desde
		double _costoMovimiento = TIPO_CUENTA
				.get_enum(_cuentaOrigen.get_tipo()).costoMovimiento();
		double _montoTotal = _monto + (_monto * _costoMovimiento);

		if (_cuentaOrigen.get_saldo() < _montoTotal) {
			throw new OperationException(
					"No hay suficiente saldo para realizar la transacci�n");
		}

		long msDif = vencimiento.get_Date().getTime() - Fecha.now().getTime();
		int _dias = (int) ((((msDif / 1000) / 60) / 60) / 24);

		PlazosFijos _plazoFijo = new PlazosFijos();
		_plazoFijo.set_monto(_monto);
		_plazoFijo.set_acreditacion(PLAZO_FIJO.ACREDITACION.CUENTA.get_key());
		_plazoFijo.set_fechaVencimiento(vencimiento.get_Fecha());
		_plazoFijo.set_idCliente(_cuentaOrigen.get_idCliente());
		_plazoFijo.set_interes(PLAZO_FIJO.INTERESES.get_intereses(_dias)
				.interes() * 100);
		_plazoFijo.set_nroCuentaDestino(_cuentaDestino.get_id());
		_plazoFijo.set_nroCuentaOrigen(_cuentaOrigen.get_id());
		_plazoFijo.set_origen(ORIGEN.CUENTA.id());

		int idPlazo = _plazoFijo.Insert();

		_cuentaOrigen.set_saldo(_cuentaOrigen.get_saldo() - _montoTotal);
		_cuentaOrigen.Update();

		Movimientos _movimiento = new Movimientos();
		_movimiento.set_idcuenta(_cuentaOrigen.get_id());
		_movimiento.set_monto(_montoTotal);
		_movimiento.set_origen(MOVIMIENTO.ORIGEN.PLAZO_FIJO.id());
		_movimiento.set_tipo(MOVIMIENTO.TIPO.EXTRACCION.id());
		_movimiento.set_saldo(_cuentaOrigen.get_saldo());

		return idPlazo;
	}

	/**
	 * Plazo fijo en efectivo, se retira en cuenta
	 * 
	 * @param _cuenta
	 * @param _monto
	 * @param vencimiento
	 * @param _cuentaDestino
	 * @return
	 * @throws OperationException
	 */
	public int plazoFijo(int _monto, Fecha vencimiento, Cuentas _cuentaDestino)
			throws OperationException {
		if (this._cliente == null) {
			throw new OperationException("El objeto cliente es null");
		}
		if (vencimiento.get_Date().before(Fecha.now())) {
			throw new OperationException("La fecha es incorrecta");
		}
		if (_monto <= 0) {
			throw new OperationException("La cantidad es incorrecta");
		}
		if (_cuentaDestino.Cantidad() == 0) {
			throw new OperationException("No existe la cuenta destino");
		}
		_cuentaDestino = _cuentaDestino.Load();

		long msDif = vencimiento.get_Date().getTime() - Fecha.now().getTime();
		int _dias = (int) ((((msDif / 1000) / 60) / 60) / 24);

		PlazosFijos _plazoFijo = new PlazosFijos();
		_plazoFijo.set_monto(_monto);
		_plazoFijo.set_acreditacion(PLAZO_FIJO.ACREDITACION.CUENTA.get_key());
		_plazoFijo.set_fechaVencimiento(vencimiento.get_Fecha());
		_plazoFijo.set_idCliente(this._cliente.get_id());
		_plazoFijo.set_interes(PLAZO_FIJO.INTERESES.get_intereses(_dias)
				.interes() * 100);
		_plazoFijo.set_nroCuentaDestino(_cuentaDestino.get_id());
		_plazoFijo.set_nroCuentaOrigen(0);
		_plazoFijo.set_origen(ORIGEN.EFECTIVO.id());

		return _plazoFijo.Insert();
	}

	/**
	 * Plazo fijo en efectivo, se retira en efectivo
	 * 
	 * @param _monto
	 * @param vencimiento
	 * @return
	 * @throws OperationException
	 */
	public int plazoFijo(int _monto, Fecha vencimiento)
			throws OperationException {
		if (this._cliente == null) {
			throw new OperationException("El objeto cliente es null");
		}
		if (vencimiento.get_Date().before(Fecha.now())) {
			throw new OperationException("La fecha es incorrecta");
		}
		if (_monto <= 0) {
			throw new OperationException("La cantidad es incorrecta");
		}

		long msDif = vencimiento.get_Date().getTime() - Fecha.now().getTime();
		int _dias = (int) ((((msDif / 1000) / 60) / 60) / 24);

		PlazosFijos _plazoFijo = new PlazosFijos();
		_plazoFijo.set_monto(_monto);
		_plazoFijo.set_acreditacion(PLAZO_FIJO.ACREDITACION.CAJA.get_key());
		_plazoFijo.set_fechaVencimiento(vencimiento.get_Fecha());
		_plazoFijo.set_idCliente(this._cliente.get_id());
		_plazoFijo.set_interes(PLAZO_FIJO.INTERESES.get_intereses(_dias)
				.interes() * 100);
		_plazoFijo.set_nroCuentaDestino(0);
		_plazoFijo.set_nroCuentaOrigen(0);
		_plazoFijo.set_origen(ORIGEN.EFECTIVO.id());

		return _plazoFijo.Insert();
	}
}