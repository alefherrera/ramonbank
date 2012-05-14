package com.ramon.ramonbank.businesslogic.utils;

public enum CONST_MOVIMIENTO_TIPO {
	EXTRACCION(1,"Extraccion"), 
	DEPOSITO(2, "Deposito");


	private int _key;
	private String _nombre;


	public String nombre() {
		return this._nombre;
	}

	public int id() {
		return this._key;
	}
	
	CONST_MOVIMIENTO_TIPO(int _key, String _nombre){
		this._key  = _key;
		this._nombre = _nombre;
	}

	public static CONST_MOVIMIENTO_TIPO get_enum(int _key) {
		switch (_key) {
		case 1:
			return EXTRACCION;
		case 2:
			return DEPOSITO;
		default:
			break;
		}
		return EXTRACCION;
	}
}