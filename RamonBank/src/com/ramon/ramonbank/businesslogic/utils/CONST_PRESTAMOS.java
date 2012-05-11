package com.ramon.ramonbank.businesslogic.utils;


public enum CONST_PRESTAMOS {
    MINIMO	(5000);
    
    private double _cantMinimo;
    CONST_PRESTAMOS(double _cantMinimo){
    	this._cantMinimo = _cantMinimo;
    }
    
    public double cantMinimo(){return this._cantMinimo;}
    
}