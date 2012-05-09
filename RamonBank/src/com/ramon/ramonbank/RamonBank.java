package com.ramon.ramonbank;



import java.util.logging.Logger;

import com.ramon.ramonbank.dbaccess.Client;
import com.ramon.ramonbank.utils.RBLogger;

public class RamonBank {

	private Logger _log = Logger.getLogger("Log");
	private static RamonBank _instance;

	public static void main(String[] args) {
		_instance = new RamonBank();
	}

	public RamonBank() {
		RBLogger.load();
		
		Client oClient = new Client();
		oClient.set_id(3);
		oClient.set_email("PEPE");
		oClient.Delete();
		System.out.println(oClient.Load().get_nombre());
	}
}
