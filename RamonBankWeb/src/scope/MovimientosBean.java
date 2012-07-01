package scope;

import java.io.Serializable;
import java.util.ArrayList;


import com.ramon.ramonbank.dbaccess.tables.Movimientos;

public class MovimientosBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	ArrayList<Movimientos> movimientos;

	

	public MovimientosBean() {
	}
	
	public void setMovimientos(ArrayList<Movimientos> movimientos) {
		this.movimientos = movimientos;
	}
	public ArrayList<Movimientos> getMovimientos() {
		return movimientos;
	}

	
}
