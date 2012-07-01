package com.ramon.ramonbank.dbaccess;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Logger;

import com.ramon.ramonbank.exceptions.OperationException;
import com.ramon.ramonbank.utils.Fecha;

public abstract class Tables implements ITables {

	// private String Query;
	protected ExecuteQuery execute = new ExecuteQuery();
	private Logger _log = Logger.getLogger("Log");
	protected ArrayList<Object> Lista = new ArrayList<Object>();
	
	
	public ResultSet Custom(String query){
		return execute.ExecSelect(query);
	}
	@Override
	public ResultSet Select(ArrayList<Object> Lista) {
		return execute.ExecSelect(GenerarQuery("Select", Lista));
	}

	@Override
	public int Insert(ArrayList<Object> Lista) {
		return execute.ExecInsert(GenerarQuery("Insert", Lista));
	}

	@Override
	public boolean Update(ArrayList<Object> Lista) {
		return execute.ExecUpdate_Delete(GenerarQuery("Update", Lista));
	}

	@Override
	public boolean Delete(ArrayList<Object> Lista) {
		return execute.ExecUpdate_Delete(GenerarQuery("Delete", Lista));
	}

	@Override
	public ITables Load() throws OperationException {
		return null;
	}
	
	public ArrayList<? extends ITables>LoadList() throws OperationException{
		return null;
	}

	@Override
	public int Cantidad() {
		return 0;
	}

	private String GenerarQuery(String Tipo, ArrayList<Object> Lista) {
		String Query;
		Query = "call " + this.getClass().getSimpleName() + "_";

		Query += Tipo;

		Query += " (";
		for (Object Obj : Lista) {
			if (Obj.getClass().equals(Integer.class)) {
				Query += "'" + ((Integer)Obj).intValue() + "'";
			} else if (Obj.getClass().equals(String.class)) {
				Query += "'" + Obj.toString() + "'";
			} else if (Obj.getClass().equals(Double.class)) {
				Query += "'" + ((Double)Obj).doubleValue() + "'";
			} else if (Obj.getClass().equals(Boolean.class)) {
				Query += ((Boolean)Obj).booleanValue();
			} else if (Obj.getClass().equals(Fecha.class)) {
				if (((Fecha)Obj).get_Fecha().equals("0"))
				{
					Query += ((Fecha)Obj).get_Fecha();
				}else
					Query += "'" + ((Fecha)Obj).get_FechaIngles() + "'";				
			}
			if(Obj != null){
				Query += ",";
			}
		}
		Query = Query.substring(0, Query.length() - 1);
		Query += ")";

		return Query;
	}

	

}
