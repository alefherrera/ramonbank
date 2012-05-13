package com.ramon.ramonbank.businesslogic;


import java.util.logging.Logger;
import com.ramon.ramonbank.dbaccess.tables.Cliente;
import com.ramon.ramonbank.dbaccess.tables.Cuenta;
import com.ramon.ramonbank.dbaccess.tables.Prestamo;
import com.ramon.ramonbank.exceptions.OperationException;
import com.ramon.ramonbank.businesslogic.utils.CONST_TIPOCUENTA;
import com.ramon.ramonbank.businesslogic.utils.CONST_PRESTAMOS;

public class ServiciosCliente {
	private Cliente _cliente;

	private Logger _log = Logger.getLogger("Log");

	public Cliente get_cliente() {
		return _cliente;
	}

	public void set_cliente(Cliente oCliente) {
		this._cliente = oCliente;
	}

	public ServiciosCliente(Cliente _cliente) {
		this._cliente = _cliente;
	}
	
	public ServiciosCliente(int _idCliente) throws OperationException{
		this._cliente.set_id(_idCliente);
		this._cliente = this._cliente.Load();
	}

	public int crearCuenta(int _tipo) throws OperationException {
		Cuenta _cuenta = new Cuenta();
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

		if (CONST_TIPOCUENTA.get_enum(_cuenta.get_tipo()).cantMax() > _cuenta
				.Cantidad()) {
			// Puedo crear la cuenta
			return _cuenta.Insert();
		} else {
			throw new OperationException("El cliente tiene "
					+ _cuenta.Cantidad() + " "
					+ CONST_TIPOCUENTA.get_enum(_cuenta.get_tipo()).cantMax()
					+ "/s");
		}
	}

	public int solicitarPrestamo(int _cantPrestamo, int _cantCuotas,
			int _idCuenta) throws OperationException {
		if (_cantCuotas <= 0) {
			throw new OperationException("La cantidad de cuotas es incorrecta");
		}

		if (this._cliente == null) {
			throw new OperationException("El objeto cliente es null");
		}
		// TODO: Minimo para prestamos, sacar el hardcore del minimo
		if (_cantPrestamo < CONST_PRESTAMOS.MINIMO.cantMinimo()) {
			throw new OperationException("El minimo de un prestamo es 5000");
		}
		Cuenta _cuenta = new Cuenta();
		_cuenta.set_id(_idCuenta);
		_cuenta = _cuenta.Load();
		double _costoMovimiento = CONST_TIPOCUENTA.get_enum(_cuenta.get_tipo())
				.costoMovimiento();

		// Cobro en caso de tener un costo de movimiento
		if (_cuenta.get_saldo() > _cantPrestamo * _costoMovimiento) {
			_cuenta.set_saldo(_cuenta.get_saldo()
					- (_cantPrestamo * _costoMovimiento));
			_cuenta.Update();
		} else {
			throw new OperationException(
					"No hay suficiente saldo para realizar el movimiento");
		}

		// TODO:Registrar movimiento, falta la clase

		// TODO:TODO: Sacar el hardcore del prestamo
		Prestamo _prestamo = new Prestamo();
		_prestamo.set_interes(10);
		_prestamo.set_idCliente(_cuenta.get_idCliente());
		_prestamo.set_cantCuotas(_cantCuotas);
		_prestamo.set_idCuenta(_cuenta.get_id());
		_prestamo.set_monto(_cantPrestamo);
		return _prestamo.Insert();
	}

	public int solicitarPrestamo(int _cantPrestamo, int _cantCuotas)
			throws OperationException {
		if (_cantCuotas <= 0) {
			throw new OperationException("La cantidad de cuotas es incorrecta");
		}

		if (this._cliente == null) {
			throw new OperationException("El objeto cliente es null");
		}

		if (_cantPrestamo < CONST_PRESTAMOS.MINIMO.cantMinimo()) {
			throw new OperationException("El minimo de un prestamo es 5000");
		}

		// TODO: Sacar el hardcore del prestamo
		Prestamo _prestamo = new Prestamo();
		_prestamo.set_interes(15);
		_prestamo.set_idCliente(_cliente.get_id());
		_prestamo.set_cantCuotas(_cantCuotas);
		_prestamo.set_idCuenta(0);
		_prestamo.set_monto(_cantPrestamo);
		return _prestamo.Insert();
	}

	public int pagarPrestamo(Prestamo _prestamo, Cuenta _cuenta,
			int _cantidadCuotas) throws OperationException {
		if (this._cliente == null) {
			throw new OperationException("El objeto cliente es null");
		}

		// TODO: PagoPrestamo, cargar idCuenta en 0

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

//		Prestamo _prestamo = new Prestamo();
//		_prestamo.set_id(_idPrestamo);
//		_prestamo.Load();
//
//		// TODO: Traer Prestamos pagados de este prestamo y hacer la resta
//		PrestamoPagado _prestamosP = new PrestamoPagado();
//		int _cuotasPagadas = _prestamosP.cuotasPagas();
//		
//		if(_cuotasPagadas == _prestamo.get_cantCuotas()){
//			throw new OperationException("El prestamo ya se encuentra pago.")
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
//		
//		// TODO: PagoPrestamo, cargar idCuenta en 0
		return 0;
	}

	
}
