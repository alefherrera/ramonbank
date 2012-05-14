package com.ramon.ramonbank.businesslogic.utils;

public enum CONST_MOVIMIENTO_ORIGEN {
	CAJA(1,"Extraccion"), 
	TRANSFERENCIA(2, "Deposito"),
	SERVICIO(3, "Servicio"),
	PRESTAMO(4, "Prestamo"),
	PLAZO_FIJO(5, "Plazo Fijo");
	
	private int _key;
	private String _nombre;


	public String nombre() {
		return this._nombre;
	}

	public int id() {
		return this._key;
	}
	
	CONST_MOVIMIENTO_ORIGEN(int _key, String _nombre){
		this._key  = _key;
		this._nombre = _nombre;
	}

	public static CONST_MOVIMIENTO_ORIGEN get_enum(int _key) {
		switch (_key) {
		case 1:
			return CAJA;
		case 2:
			return TRANSFERENCIA;
		case 3:
			return SERVICIO;
		case 4:
			return PRESTAMO;
		case 5:
			return PLAZO_FIJO;
		default:
			break;
		}
		return CAJA;
	}
}