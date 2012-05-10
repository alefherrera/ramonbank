package com.ramon.ramonbank.businesslogic.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CONST_TIPOCUENTA {
	public static final int CAJA_AHORROS = 1;
	public static final int CUENTA_CORRIENTE = 2;

    public static final Map<Integer, String> NOMBRE_TIPO;
    static {
        Map<Integer, String> aMap = new HashMap<Integer,String>();
        aMap.put(CAJA_AHORROS, "Caja de Ahorros");
        aMap.put(CUENTA_CORRIENTE, "Cuenta Corriente");
        NOMBRE_TIPO = Collections.unmodifiableMap(aMap);
    }
    
    public static final Map<Integer, Integer> CANTIDADMAX_TIPO;
    static {
        Map<Integer, Integer> aMap = new HashMap<Integer,Integer>();
        aMap.put(CAJA_AHORROS, 1);
        aMap.put(CUENTA_CORRIENTE, 5);
        CANTIDADMAX_TIPO = Collections.unmodifiableMap(aMap);
    }
}