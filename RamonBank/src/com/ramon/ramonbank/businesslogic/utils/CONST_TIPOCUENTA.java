package com.ramon.ramonbank.businesslogic.utils;

//import java.util.Collections;
//import java.util.HashMap;
//import java.util.Map;
//
//public class CONST_TIPOCUENTA {
//	public static final int CAJA_AHORROS = 1;
//	public static final int CUENTA_CORRIENTE = 2;
//
//    public static final Map<Integer, String> NOMBRE;
//    static {
//        Map<Integer, String> aMap = new HashMap<Integer,String>();
//        aMap.put(CAJA_AHORROS, "Caja de Ahorros");
//        aMap.put(CUENTA_CORRIENTE, "Cuenta Corriente");
//        NOMBRE = Collections.unmodifiableMap(aMap);
//    }
//    
//    public static final Map<Integer, Integer> CANTIDAD_MAX;
//    static {
//        Map<Integer, Integer> aMap = new HashMap<Integer,Integer>();
//        aMap.put(CAJA_AHORROS, 1);
//        aMap.put(CUENTA_CORRIENTE, 5);
//        CANTIDAD_MAX = Collections.unmodifiableMap(aMap);
//    }
//    
//    public static final Map<Integer, Double> COSTO_MOVIMIENTO;
//    static {
//        Map<Integer, Double> aMap = new HashMap<Integer,Double>();
//        aMap.put(CAJA_AHORROS, 0.0);
//        aMap.put(CUENTA_CORRIENTE, 0.06);
//        COSTO_MOVIMIENTO = Collections.unmodifiableMap(aMap);
//    }
//}

public enum CONST_TIPOCUENTA {
	CAJA_AHORROS(1, 1, "Caja de Ahorros", 0.0), CUENTA_CORRIENTE(2, 5,
			"Cuenta Corriente", 0.06);

	private int _key;
	private int _cantidadMax;
	private String _nombre;
	private double _costoMovimiento;

	CONST_TIPOCUENTA(int _key, int _cantidadMax, String _nombre,
			double _costoMovimiento) {
		this._key = _key;
		this._cantidadMax = _cantidadMax;
		this._nombre = _nombre;
		this._costoMovimiento = _costoMovimiento;
	}

	public int cantMax() {
		return this._cantidadMax;
	}

	public int id() {
		return this._key;
	}

	public String nombre() {
		return this._nombre;
	}

	public double costoMovimiento() {
		return this._costoMovimiento;
	}

	public static CONST_TIPOCUENTA get_enum(int _key) {
		switch (_key) {
		case 1:
			return CAJA_AHORROS;
		case 2:
			return CUENTA_CORRIENTE;
		default:
			break;
		}
		return CAJA_AHORROS;
	}
}