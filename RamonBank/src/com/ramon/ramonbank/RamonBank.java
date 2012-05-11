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
		//RBLogger.load();
		System.setProperty("https.proxyHost", "proxy2.frgp2"); 
		System.setProperty("https.proxyPort", "3128");  
		
		Cliente oCliente = new Cliente();
		oCliente.set_dni("36610363");
		
		oCliente = oCliente.Load();
		System.out.println(oCliente.get_nombre());
	}
}
