package scope;

import java.io.Serializable;
import java.util.ArrayList;


import com.ramon.ramonbank.dbaccess.tables.Cuentas;
import com.ramon.ramonbank.servicios.utils.TIPO_CUENTA;

public class CuentasBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	ArrayList<Cuentas> cuentas;	

	public CuentasBean() {
	}
	
	public ArrayList<Cuentas> getCuentas() {
		return cuentas;
	}

	public void setCuentas(ArrayList<Cuentas> cuentas) {
		this.cuentas = cuentas;
	}

	public TIPO_CUENTA[] getTipos(){
		return TIPO_CUENTA.values();
	}
	
}
