package com.ramon.ramonbank.dbaccess;

import java.sql.ResultSet;

public interface ITables {
	
	public ResultSet Select();
	
	public int Insert();
	
	public boolean Update();
	
	public boolean Delete();
	
	public ITables Load();
	
	public int Cantidad();
}
