package com.ramon.ramonbank.dbaccess;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ramon.ramonbank.connection.ConnectionHandler;

public class ExecuteQuery {

	public ResultSet ExecSelect(String Query)
	{
		ConnectionHandler cnHandler = new ConnectionHandler();
		Connection con = cnHandler.getConnection();
		Statement st = null;
		try {
			st = con.createStatement();
			return st.executeQuery(Query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
				return rs.getInt(0);
			}
			return 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			cnHandler.closeResources(st, con);
		}		
	}
	
}
