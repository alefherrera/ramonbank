package com.ramon.ramonbank.utils;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class  RBLogger {
	private static Logger _log = Logger.getLogger("Log");
	
	public static void load(){
		final String LOG_PATH = "./log/log.txt"; // Nombre del archivo de log
		FileHandler fh;
		
		try {
			fh = new FileHandler(LOG_PATH, true);
			_log.addHandler(fh);
			_log.setLevel(Level.ALL);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		LogManager.getLogManager().addLogger(_log);
	}
	
}
