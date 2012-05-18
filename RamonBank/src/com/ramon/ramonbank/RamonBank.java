package com.ramon.ramonbank;



import java.util.logging.Logger;

import com.ramon.ramonbank.businesslogic.ServiciosCliente;
import com.ramon.ramonbank.businesslogic.utils.TIPO_CUENTA;
import com.ramon.ramonbank.dbaccess.tables.Cliente;
import com.ramon.ramonbank.dbaccess.tables.Cuenta;
import com.ramon.ramonbank.dbaccess.tables.PagoPrestamo;
import com.ramon.ramonbank.dbaccess.tables.Prestamo;
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
		
		PagoPrestamo oPagoPrestamo = new PagoPrestamo();
		oPagoPrestamo.Select();
	}	
}
