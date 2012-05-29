package com.ramon.ramonbank.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Fecha {
	private String _Fecha;

	public String get_Fecha() {
		return _Fecha;
	}

	public void set_Fecha(String _Fecha) {
		this._Fecha = _Fecha;
	}

	public Fecha() {
		this._Fecha = "0";
	}

	public static int nroDiaActual() {
		DateFormat dateFormat = new SimpleDateFormat("dd");
		Date date = new Date();
		return Integer.parseInt(dateFormat.format(date));
	}
}
