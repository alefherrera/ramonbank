package com.ramon.ramonbank.businesslogic.utils;


public enum CONST_PRESTAMOS {
    MINIMO	(5000),
    INTERES_SIN_CUENTA(0.15),
    INTERES_CON_CUENTA(0.1);
    
    private double _number;
    CONST_PRESTAMOS(double _number){
    	this._number = _number;
    }
    
    public double number(){return this._number;}
    
}