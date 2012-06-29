package com.ramon.ramonbank.servicios.utils;


public enum PRESTAMO {
    MINIMO	(5000),
    INTERES_SIN_CUENTA(0.15),
    INTERES_CON_CUENTA(0.1);
    
    private double _number;
    PRESTAMO(double _number){
    	this._number = _number;
    }
    
    public double number(){return this._number;}
    
}