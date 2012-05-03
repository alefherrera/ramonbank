package com.ramon.ramonbank;

import com.ramon.ramonbank.utils.ConsoleReader;
import com.ramon.ramonbank.dbaccess.Client;

public class MainRamonBank {
	public static void main(String[] args) {
		System.out.println(ConsoleReader.readLn());
		System.out.println(ConsoleReader.readLn());
		Client cliente = new Client();
		cliente.Select();
	}
}
