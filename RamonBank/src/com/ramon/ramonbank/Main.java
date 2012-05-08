package com.ramon.ramonbank;


import com.ramon.ramonbank.dbaccess.Client;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Client oClient = new Client();
		oClient.set_nombre("pito");
		oClient.Insert();
		System.out.println(oClient.Load().get_nombre());
	}
}
