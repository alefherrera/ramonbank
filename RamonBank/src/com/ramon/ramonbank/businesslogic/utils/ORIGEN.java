package com.ramon.ramonbank.businesslogic.utils;

import com.ramon.ramonbank.businesslogic.utils.MOVIMIENTO.TIPO;


public enum ORIGEN {
    EFECTIVO(1),
    CUENTA(2);
    
    private int _number;
    ORIGEN(int _number){
    	this._number = _number;
    }
    
    public int id(){return this._number;}
    
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