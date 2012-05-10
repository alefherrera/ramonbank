package com.ramon.ramonbank;



import java.util.logging.Logger;

import com.ramon.ramonbank.businesslogic.ServiciosCliente;
import com.ramon.ramonbank.businesslogic.utils.CONST_TIPOCUENTA;
import com.ramon.ramonbank.dbaccess.tables.Cliente;
import com.ramon.ramonbank.dbaccess.tables.Cuenta;
import com.ramon.ramonbank.exceptions.OperationException;
import com.ramon.ramonbank.utils.RBLogger;

public class RamonBank {


	private Logger _log = Logger.getLogger("Log");
	private static RamonBank _instance;

	public static RamonBank get_instance() {
		return _instance;
	}
	
	public static void main(String[] args) {
		_instance = new RamonBank();
	}

	public RamonBank() {
		RBLogger.load();
		
		Cliente oCliente = new Cliente();
		oCliente.set_dni("36610363");
		
		Cuenta oCuenta = new Cuenta();
		oCuenta.set_idCliente(1);
		oCuenta.set_saldo(2412412);
		oCuenta.set_tipo(CONST_TIPOCUENTA.CAJA_AHORROS);
		ServiciosCliente oOper = new ServiciosCliente(oCliente, oCuenta);
		try {
			oOper.crearCuenta();
		} catch (OperationException e) {
			e.printStackTrace();
		}
	
	}
}
