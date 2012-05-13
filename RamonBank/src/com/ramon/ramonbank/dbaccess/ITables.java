package com.ramon.ramonbank.dbaccess;

import java.sql.ResultSet;

import com.ramon.ramonbank.exceptions.OperationException;

public interface ITables {
	
	public ResultSet Select();
	
	public int Insert();
	
	public boolean Update();
	
	public boolean Delete();
	
	public ITables Load() throws OperationException;
	
	public int Cantidad();
}
