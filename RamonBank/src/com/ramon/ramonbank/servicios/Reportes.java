package com.ramon.ramonbank.servicios;

import java.util.ArrayList;

import com.ramon.ramonbank.dbaccess.tables.Clientes;
import com.ramon.ramonbank.dbaccess.tables.Cuentas;
import com.ramon.ramonbank.dbaccess.tables.Movimientos;
import com.ramon.ramonbank.dbaccess.tables.PagoServicios;
import com.ramon.ramonbank.dbaccess.tables.PlazosFijos;
import com.ramon.ramonbank.exceptions.OperationException;
import com.ramon.ramonbank.servicios.utils.MOVIMIENTO;
import com.ramon.ramonbank.servicios.utils.PLAZO_FIJO;
import com.ramon.ramonbank.servicios.utils.MOVIMIENTO.ORIGEN;
import com.ramon.ramonbank.utils.Fecha;

public class Reportes {
	/**
	 * Ultimos movimientos de la cuenta
	 * @param cuenta
	 * @param cantidad Cantidad para poner en el top
	 * @return
	 * @throws OperationException
	 */
	public static ArrayList<Movimientos> ultimosMovimientos(Cuentas cuenta,
			int cantidad) throws OperationException {
		if (cuenta == null) {
			throw new OperationException("El objeto cuenta es null");
		}
		if (cuenta.Cantidad() == 0) {
			throw new OperationException("La cuenta es inexistente");
		}
		Movimientos movimiento = new Movimientos();
		movimiento.set_idcuenta(cuenta.get_id());

		return movimiento.ultimosCargados(cantidad);
	}

	/**
	 * Depósitos y/o Extracciones realizadas entre dos fechas dadas de una
	 * cuenta.
	 * 
	 * @param cuenta
	 * @param fechaDesde
	 * @param fechaHasta
	 * @param tipoMovimiento
	 *            MOVIMIENTO.TIPO. (los dos parámetros posibles)
	 * @return
	 */
	public static ArrayList<Movimientos> movimientosCuenta(Cuentas cuenta,
			Fecha fechaDesde, Fecha fechaHasta, int tipoMovimiento)
			throws OperationException {
		if (cuenta == null) {
			throw new OperationException("Cuenta es null");
		}
		if (cuenta.Cantidad() != 1) {
			throw new OperationException("Cuenta incorrecta");
		}
		if (fechaDesde.get_Date().after(fechaHasta.get_Date())) {
			throw new OperationException(
					"La fecha desde es mayor a la fecha hasta");
		}
		cuenta = cuenta.Load();

		Movimientos movimiento = new Movimientos();
		movimiento.set_filtro_fechaDesde(fechaDesde.get_Fecha());
		movimiento.set_filtro_fechaHasta(fechaHasta.get_Fecha());
		movimiento.set_idcuenta(cuenta.get_id());
		movimiento.set_tipo(tipoMovimiento);

		return movimiento.LoadList();
	}

	/**
	 * Lista de plazos fijos
	 * @param cliente
	 * @param vencidos true si queres vencidos, false si queres no vencidos (dependen de la fecha actual)
	 * @return
	 * @throws OperationException
	 */
	public static ArrayList<PlazosFijos> reportePlazosFijos(Clientes cliente,
			boolean vencidos) throws OperationException {
		if(cliente == null){
			throw new OperationException("Cliente es null");
		}
		if(cliente.Cantidad() == 0){
			throw new OperationException("El cliente no existe");
		}
		cliente = cliente.Load();
		
		
		PlazosFijos plazofijo = new PlazosFijos();
		plazofijo.set_idCliente(cliente.get_id());
		if(vencidos){
			plazofijo.set_filtro_fechaVencimientoHasta(Fecha.now().toString());
		}
		else{
			plazofijo.set_filtro_fechaVencimientoDesde(Fecha.now().toString());
		}
		
		return plazofijo.LoadList();
	}
	
	/**
	 * Pago de servicios por cliente
	 * @param cliente
	 * @return
	 * @throws OperationException
	 */
	public static ArrayList<PagoServicios> serviciosPagados(Clientes cliente) throws OperationException{
		if(cliente == null){
			throw new OperationException("Cliente es null");
		}
		if(cliente.Cantidad() == 0){
			throw new OperationException("El cliente no existe");
		}
		cliente = cliente.Load();
		Cuentas cuenta = new Cuentas();
		cuenta.set_idCliente(cliente.get_id());
		ArrayList<Cuentas> arrayCuentas = new ArrayList<Cuentas>();
		arrayCuentas = cuenta.LoadList();
		
		ArrayList<PagoServicios> pagoservicios = new ArrayList<PagoServicios>();
		for(Cuentas c : arrayCuentas ){
			PagoServicios pservicio = new PagoServicios();
			pservicio.set_nroCuenta(c.get_id());
			pservicio = pservicio.Load();
			pagoservicios.add(pservicio);
		}
		
		return pagoservicios;
	}
	
	/**
	 * Transferencias de una cuenta
	 * @param cuenta 
	 * @return
	 * @throws OperationException
	 */
	public static ArrayList<Movimientos> transferenciasCuenta(Cuentas cuenta)
			throws OperationException {
		if (cuenta == null) {
			throw new OperationException("Cuenta es null");
		}
		if (cuenta.Cantidad() != 1) {
			throw new OperationException("Cuenta incorrecta");
		}

		cuenta = cuenta.Load();

		Movimientos movimiento = new Movimientos();
		movimiento.set_idcuenta(cuenta.get_id());
		movimiento.set_origen(MOVIMIENTO.ORIGEN.TRANSFERENCIA.id());
		
		return movimiento.LoadList();
	}
}