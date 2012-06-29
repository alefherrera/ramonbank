package com.ramon.ramonbank.servicios.utils;

import com.ramon.ramonbank.servicios.utils.MOVIMIENTO.TIPO;


public enum PAGO_PRESTAMO {
    EFECTIVO(1),
    CUENTA(2);
    
    private int _number;
    PAGO_PRESTAMO(int _number){
    	this._number = _number;
    }
    
    public int id(){return this._number;}
    
    public static PAGO_PRESTAMO get_enum(int _key) {
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