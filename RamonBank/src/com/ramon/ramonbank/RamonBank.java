package com.ramon.ramonbank;



import java.util.logging.Logger;

import com.ramon.ramonbank.businesslogic.ServiciosCliente;
import com.ramon.ramonbank.businesslogic.utils.TIPO_CUENTA;
import com.ramon.ramonbank.dbaccess.tables.Cliente;
import com.ramon.ramonbank.dbaccess.tables.Cuenta;
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
		
		Cliente oCliente = new Cliente();
		oCliente.set_dni("36610363");
		
		try {
			oCliente = oCliente.Load();
		} catch (OperationException e) {
			e.printStackTrace();
		}
		ServiciosCliente _serv = new ServiciosCliente(oCliente);
		try {
			_serv.solicitarPrestamo(5000, 1, 2);
			Prestamo _prestamo = new Prestamo();
			_prestamo.Load();
			System.out.println(_prestamo.get_monto());
		} catch (OperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}	
}
