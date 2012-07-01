package com.ramon.ramonbank.servicios.utils;

public enum ORIGEN {
	EFECTIVO(1, "Efectivo"), CUENTA(2, "Caja");

	private int _number;
	private String _nombre;

	ORIGEN(int _number, String _nombre) {
		this._number = _number;
		this.set_nombre(_nombre);
	}

	public int id() {
		return this._number;
	}

	public String get_nombre() {
		return _nombre;
	}

	public void set_nombre(String _nombre) {
		this._nombre = _nombre;
	}

	public static ORIGEN get_enum(int _key) {
		switch (_key) {
		case 1:
			return EFECTIVO;
		case 2:
			return CUENTA;
		default:
			break;
		}
		return EFECTIVO;
	}

}