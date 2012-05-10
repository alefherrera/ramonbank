package com.ramon.ramonbank.dbaccess;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ramon.ramonbank.connection.ConnectionHandler;

public class ExecuteQuery {


	private Logger _log = Logger.getLogger("Log");
	
	public ResultSet ExecSelect(String Query)
	{
		ConnectionHandler cnHandler = new ConnectionHandler();
		Connection con = cnHandler.getConnection();
		Statement st = null;
		try {
			st = con.createStatement();
			return st.executeQuery(Query);
		} catch (SQLException e) {
			_log.log(Level.WARNING, e.getStackTrace().toString() + "\n"+ Query);
			return null;
		} finally {
			cnHandler.closeResources(st, con);
		}		
	}
	
	public int ExecInsert(String Query)
	{
		ConnectionHandler cnHandler = new ConnectionHandler();
		Connection con = cnHandler.getConnection();
		Statement st = null;
		try {
			st = con.createStatement();
			 ResultSet rs = st.executeQuery(Query);
			if(rs.next())
			{
				return rs.getInt("LAST");
			}
			return 0;
		} catch (SQLException e) {
			_log.log(Level.WARNING, e.getMessage() + "\n"+ Query + "\n");
			return -1;
		} finally {
			cnHandler.closeResources(st, con);
		}		
	}
	
	public boolean ExecUpdate_Delete(String Query)
	{
		ConnectionHandler cnHandler = new ConnectionHandler();
		Connection con = cnHandler.getConnection();
		Statement st = null;
		try {
			st = con.createStatement();
			return st.execute(Query);
		} catch (SQLException e) {
			_log.log(Level.WARNING, e.getMessage().toString() + "\n"+ e.getStackTrace() + "\n" + Query);
			return false;
		} finally {
			cnHandler.closeResources(st, con);
		}		
	}
	
}
