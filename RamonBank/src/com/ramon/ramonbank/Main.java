package com.ramon.ramonbank;

import java.sql.SQLException;

import com.ramon.ramonbank.dbaccess.Client;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Client oClient = new Client();
		try {
			System.out.println(oClient.Load().get_apellido());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
