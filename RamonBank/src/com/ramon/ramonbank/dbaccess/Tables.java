package com.ramon.ramonbank.dbaccess;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Logger;

import com.ramon.ramonbank.exceptions.OperationException;
import com.ramon.ramonbank.utils.Fecha;

public abstract class Tables implements ITables {

	// private String Query;
	private ExecuteQuery execute;
	private Logger _log = Logger.getLogger("Log");
	protected ArrayList<Object> Lista = new ArrayList<Object>();
	

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int Cantidad() {
		// TODO Auto-generated method stub
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
				Query += ((Fecha)Obj).get_Fecha();				
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
