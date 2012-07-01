package com.ramon.ramonbank.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.jdt.internal.compiler.ast.ThisReference;

public class Fecha {
	private String _Fecha;

	public String get_Fecha() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		return dateFormat.format(this.get_Date());
	}

	public void set_Fecha(String _Fecha) {
		this._Fecha = _Fecha;
	}

	public Fecha() {
		this._Fecha = "0";
	}
	public Date get_Date(){
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		try {
			date = dateFormat.parse(this._Fecha);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public String get_FechaIngles(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		return dateFormat.format(this.get_Date());
	}
	
	public static int nroDiaActual() {
		DateFormat dateFormat = new SimpleDateFormat("dd");
		Date date = new Date();
		return Integer.parseInt(dateFormat.format(date));
	}
	
	public static Date now(){
		Date date = new Date();
		return date;
	}
	
}
