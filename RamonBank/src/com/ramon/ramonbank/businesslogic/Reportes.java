package com.ramon.ramonbank.businesslogic;

import java.util.ArrayList;

import com.ramon.ramonbank.dbaccess.tables.Cuentas;
import com.ramon.ramonbank.dbaccess.tables.Movimientos;
import com.ramon.ramonbank.exceptions.OperationException;

public class Reportes {
	public static ArrayList<Movimientos> ultimosMovimientos(Cuentas cuenta, int cantidad) throws OperationException{
		if(cuenta == null){
			throw new OperationException("El objeto cuenta es null");
		}
		if(cuenta.Cantidad() == 0){
			throw new OperationException("La cuenta es inexistente");
		}
		Movimientos movimiento = new Movimientos();
		movimiento.set_idcuenta(cuenta.get_id());
		//TODO: CONSULTA SQL Ultimos movimientos por filtro mandado, TOP 5
		return movimiento.ultimosCargados(cantidad);
	}
}
