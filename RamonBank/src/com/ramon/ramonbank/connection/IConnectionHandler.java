package com.ramon.ramonbank.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public interface IConnectionHandler {

	/**
	 * Abre una conexion y devuelve el objeto Connection
	 * */
	public Connection getConnection();
	
	/**
	 * Libera recursos de acceso a datos y cierra la conexion
	 * @param rs
	 * @param st
	 * @param cn
	 * */
	public void closeResources(ResultSet rs, Statement st, Connection cn);
	
	/**
	 * Libera recursos de acceso a datos y cierra la conexion
	 * @param ps
	 * @param cn
	 * */
	public void closeResources(PreparedStatement ps, Connection cn);
	
	/**
	 * Libera recursos de acceso a datos y cierra la conexion
	 * @param st
	 * @param cn
	 * */
	public void closeResources(Statement st, Connection cn);
	
}
