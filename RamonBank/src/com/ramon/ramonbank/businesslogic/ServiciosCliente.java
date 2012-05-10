package com.ramon.ramonbank.businesslogic;


import java.util.logging.Logger;
import com.ramon.ramonbank.dbaccess.tables.Cliente;
import com.ramon.ramonbank.dbaccess.tables.Cuenta;
import com.ramon.ramonbank.dbaccess.tables.Prestamo;
import com.ramon.ramonbank.exceptions.OperationException;
import com.ramon.ramonbank.businesslogic.utils.CONST_TIPOCUENTA;


public class ServiciosCliente {
	private Cliente _cliente;
	private Cuenta _cuenta;
	private Prestamo _prestamo;
	
	private Logger _log = Logger.getLogger("Log");

	public Cliente get_cliente() {
		return _cliente;
	}

	public void set_cliente(Cliente oCliente) {
		this._cliente = oCliente;
	}

	public Cuenta get_cuenta() {
		return _cuenta;
	}

	public void set_cuenta(Cuenta oCuenta) {
		this._cuenta = oCuenta;
	}

	public ServiciosCliente() {
	}

	public ServiciosCliente(Cliente _cliente, Cuenta _cuenta) {
		this._cliente = _cliente;
		this._cuenta = _cuenta;
	}
	public ServiciosCliente(Cliente _cliente, Prestamo _prestamo) {
		this._cliente = _cliente;
		this._prestamo = _prestamo;
	}
	public ServiciosCliente(Cliente _cliente, Cuenta _cuenta, Prestamo _prestamo) {
		this._cliente = _cliente;
		this._cuenta = _cuenta;
		this._prestamo = _prestamo;
	}

	public int crearCuenta() throws OperationException {
		_cuenta.set_idCliente(_cliente.get_id());
		// Validaciones iniciales
		if (this._cliente == null) {
			throw new OperationException("El objeto cliente es null");
		}
		if (this._cuenta == null) {
			throw new OperationException("El objeto cuenta es null");
		}
		if (this._cliente.Cantidad() == 0) {
			throw new OperationException("El cliente no existe");
		}

		// Verifico cantidad de cuentas, 1 maximo caja de ahorro, 5 maximo
		// cuenta corriente
		Cuenta oCuenta = new Cuenta();
		_cuenta.set_tipo(this._cuenta.get_tipo());
		if (CONST_TIPOCUENTA.CANTIDADMAX_TIPO.get(this._cuenta.get_tipo()) > _cuenta
				.Cantidad()) {
			// Puedo crear la cuenta
			return _cuenta.Insert();
		} else {
			throw new OperationException("El cliente tiene "
					+ _cuenta.Cantidad() + " "
					+ CONST_TIPOCUENTA.NOMBRE_TIPO.get(this._cuenta.get_tipo())
					+ "/s");
		}
	}
	
	public int solicitarPrestamo() throws OperationException{
		if (this._prestamo == null) {
			throw new OperationException("El objeto prestamo es null");
		}
		
		
		return 0;
	}
}
