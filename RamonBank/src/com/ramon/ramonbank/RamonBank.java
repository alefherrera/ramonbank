package com.ramon.ramonbank;



import com.ramon.ramonbank.dbaccess.Client;
import com.ramon.ramonbank.utils.RBLogger;

public class RamonBank {

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
