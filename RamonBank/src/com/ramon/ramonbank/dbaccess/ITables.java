package com.ramon.ramonbank.dbaccess;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ITables {
	
	public ResultSet Select() throws SQLException;
	
	public int Insert() throws SQLException;
	
	public boolean Update() throws SQLException;
	
	public boolean Delete() throws SQLException;
	
	public ITables Load() throws SQLException;
	
}
