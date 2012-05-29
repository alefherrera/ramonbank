package com.ramon.ramonbank.businesslogic;

import com.ramon.ramonbank.dbaccess.tables.Clientes;
import com.ramon.ramonbank.exceptions.OperationException;
import com.ramon.ramonbank.utils.Validator;

public class Servicios {
	/**
	 * @param _cliente
	 * @return id del Cliente creado
	 * @throws OperationException
	 */
	public static int crearCliente(Clientes _cliente) throws OperationException{
		//Validaciones
		if(_cliente == null){
			throw new OperationException("El objeto cliente es null");
		}
		if(!Validator.valDNI(_cliente.get_dni())){
			throw new OperationException("DNI incorrecto");
		}
		if(!Validator.validMail(_cliente.get_email())){
			throw new OperationException("Mail incorrecto");
		}
		if(_cliente.get_apellido() == ""){
			throw new OperationException("Apellido incorrecto");
		}
		if(_cliente.get_nombre() == ""){
			throw new OperationException("Nombre incorrecto");
		}
		if(_cliente.get_direccion() == ""){
			throw new OperationException("Direccion incorrecta");
		}
		Clientes chkCliente = new Clientes();
		chkCliente.set_dni(_cliente.get_dni());
		if(chkCliente.Cantidad() != 0){
			throw new OperationException("El DNI del cliente ya se encuentra registrado");
		}
		
		//Proceso
		return _cliente.Insert();
	}

}