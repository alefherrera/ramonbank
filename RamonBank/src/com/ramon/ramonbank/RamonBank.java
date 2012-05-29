package com.ramon.ramonbank;



import java.util.logging.Logger;

import com.ramon.ramonbank.businesslogic.ServiciosCliente;
import com.ramon.ramonbank.businesslogic.utils.TIPO_CUENTA;
import com.ramon.ramonbank.dbaccess.tables.Clientes;
import com.ramon.ramonbank.dbaccess.tables.Cuentas;
import com.ramon.ramonbank.dbaccess.tables.PagoPrestamos;
import com.ramon.ramonbank.dbaccess.tables.Prestamos;
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
		
		PagoPrestamos oPagoPrestamo = new PagoPrestamos();
		oPagoPrestamo.Select();
	}	
}
