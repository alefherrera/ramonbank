package scope;

import java.io.Serializable;
import java.util.ArrayList;


import com.ramon.ramonbank.dbaccess.tables.Servicios;


public class ServiciosBean implements Serializable {

private static final long serialVersionUID = 1L;
	
	ArrayList<Servicios> servicios;	

	public ServiciosBean() {
	}
	
	public ArrayList<Servicios> getServicios() {
		return servicios;
	}

	public void setServicios(ArrayList<Servicios> servicios) {
		this.servicios = servicios;
	}

	
}
