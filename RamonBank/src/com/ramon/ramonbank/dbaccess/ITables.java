package com.ramon.ramonbank.dbaccess;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.ramon.ramonbank.exceptions.OperationException;

public interface ITables {
	
	public ResultSet Select(ArrayList<Object> Lista);
	
	public int Insert(ArrayList<Object> Lista);
	
	public boolean Update(ArrayList<Object> Lista);
	
	public boolean Delete(ArrayList<Object> Lista);
	
	public ITables Load() throws OperationException;
	
	public int Cantidad();
}
